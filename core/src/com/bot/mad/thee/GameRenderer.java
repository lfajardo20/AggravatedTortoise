package com.bot.mad.thee;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.bot.mad.thee.MapMakerP.Parser;

public class GameRenderer extends ScreenAdapter {
    private Game game;
    private OrthographicCamera camera;
    private Parser parser;
    private Map map;

    private Player player;

    private SpriteBatch batch;

    private boolean isRunning;
    private boolean isPaused;

    private FPSLogger fpsLogger;

    public GameRenderer(Game game) {
        this.game = game;

        isRunning = false;

        camera = new OrthographicCamera(MyWorld.IMAGINARY_WIDTH, MyWorld.IMAGINARY_HEIGHT);

        parser = new Parser();
        map = parser.load()[0];

        player = new Player(camera);

        batch = new SpriteBatch();
        isRunning = true;
        isPaused = false;
        fpsLogger = new FPSLogger();

        Gdx.input.setInputProcessor(new GameInputProcessor(this));
    }

    private double accumulator = 0.0;
    private double currentTime = TimeUtils.millis() / 1000.0;
    private float dt = 1.0f / 60.0f;


    @Override
    public void render(float delta) {
        super.render(delta);

            double newTime = TimeUtils.millis() / 1000.0;
            double frameTime = Math.min(newTime - currentTime, 0.25);

            if(frameTime > 0.25)
                frameTime = 0.25;

            currentTime = newTime;

            accumulator += frameTime;

            while(accumulator >= dt) {
                //myWorld.getWorld().step(Math.min(Gdx.graphics.getDeltaTime(), 0.15f), 6, 2);
                MyWorld.world.step(dt, 6, 2);
                accumulator -= dt;
            }
            update(delta);
            float alpha = (float) accumulator / dt;
            player.interpolate(alpha);

            camera.update();
            draw();
            fpsLogger.log();
    }

    private void draw() {
        Gdx.gl.glClearColor(0.3f,0.2f,0.35f,0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setColor(Color.WHITE);
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        drawMap();
        MyWorld.debugRenderer.render(MyWorld.world, camera.combined);
        player.draw();
        batch.end();
    }

    private void drawMap() {
        int width = (int) map.getGridSize().x;
        int height = (int) map.getGridSize().y;

        int minimumX = (int) (camera.position.x - camera.viewportWidth/2)-3;
        int minimumY = (int) (camera.position.y - camera.viewportHeight/2)-3;
        int maxX = (int) (camera.position.x + camera.viewportWidth/2)+3;
        int maxY = (int) (camera.position.y + camera.viewportHeight/2)+3;

        for(int i=0; i < map.getSprites().size; i++) {
            Sprite sprite = map.getSprites().get(i);
            batch.setProjectionMatrix(camera.combined);
            batch.draw(sprite, sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        }

        /*for(int x=minimumX; x < width; x++) {
            for(int y=minimumY; y < height; y++) {

                batch.setProjectionMatrix(camera.combined);

                if (x >= 0 && 0 <= y) {
                    if (mapHandler.getTile(x, y) != null) {
                        Tile tile = mapHandler.getTile(x, y);
                        int tileX = (int) ((x * tile.getWidth()) + tile.getWidth());
                        int tileY = (int) ((y * tile.getHeight()) + tile.getHeight());


                    // Useful for optimization
                    // checks whether a sprite is on the screen, if not, it isn't drawn
                    if (tileX > minimumX && tileX < maxX &&
                            tileY > minimumY && tileY < maxY) {

                            batch.draw(tile.getTexture(), x * tile.getWidth(), y * tile.getHeight(),
                                    tile.getWidth(), tile.getHeight());
                        }
                    }
                }*/
    }

    /* Useful for Optimization */
    private void setBodiesState() {
       /* Array<Body> bodies = mapHandler.getBodies();

        float viewX = 3;
        float viewY = 3;

        float maxX = player.getX() + viewX;
        float minX = player.getX() - viewX;
        float maxY = player.getY() + viewY;
        float minY = player.getY() - viewY;

        for(int i=0; i < bodies.size; i++) {

            float x = bodies.get(i).getPosition().x;
            float y = bodies.get(i).getPosition().y;
            boolean isActive = false;

            isActive = x > minX && x < maxX && y > minY && y < maxY ? true : false;
            bodies.get(i).setActive(isActive);
        }*/
    }

    private void update(float delta) {
        player.update(delta);
        setBodiesState();
        //logBodies();
    }

    private void logBodies() {
        Array<Body> bodies = new Array<Body>();
        MyWorld.world.getBodies(bodies);

        int amount = 0;
        for(int i=0; i < bodies.size; i++) {
            if(bodies.get(i).isActive()) amount++;
        }

        Gdx.app.log("Active bodies ", amount+"");
    }

    public void reset() {
        player.reset();
    }

    public void pauseWorld() {
        isPaused = !isPaused;
        isRunning = isPaused ? false : true;
    }

    public void quit() {
        AssetManager.dispose();
        AssetManager.loadMenuUIS();
        game.setScreen(new GameMenu(game));
        dispose();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        camera.update();
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void hide() {
        super.hide();
    }

    @Override
    public void pause() {
        super.pause();

        isRunning = false;
    }

    @Override
    public void resume() {
        super.resume();

        isRunning = true;
    }

    @Override
    public void dispose() {
        super.dispose();

        batch.dispose();
    }
}

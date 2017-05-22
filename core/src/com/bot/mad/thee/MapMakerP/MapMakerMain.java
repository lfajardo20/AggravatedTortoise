package com.bot.mad.thee.MapMakerP;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.bot.mad.thee.AssetManager;
import com.bot.mad.thee.GameMenu;
import com.bot.mad.thee.Map;

public class MapMakerMain extends ScreenAdapter {
    private Game game;
    private Stage stage;
    private float width;
    private float height;
    private ShapeRenderer shapeRenderer;
    private StretchViewport mapUIViewport;
    private OrthographicCamera camera;
    private SpriteBatch spriteBatchMapUI;
    private OrthographicCamera uiCamera;

    private ToolboxUI toolboxUI;

    private BitmapFont font;
    private Map map;

    private StageManager stageManager;

    public MapMakerMain(Game game) {
        this.game = game;

        width = 16;
        height = 9;

        stageManager = new StageManager();
        map = new Map();
        camera = new OrthographicCamera();
        uiCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        mapUIViewport = new StretchViewport(width, height, camera);

        camera.position.set(0, 0, 0);
        spriteBatchMapUI = new SpriteBatch();

        shapeRenderer = new ShapeRenderer();
        stage = new Stage(new StretchViewport(width, height));
        stageManager.setStage(stage);

        font = AssetManager.COORDINATE_FONT;
        toolboxUI = new ToolboxUI(this);

        addUIs();
        setListeners();
    }

    public void resetUI() {
        setListeners();
        stageManager.setStage(stage);
    }

    private void drawWorld() {
        mapUIViewport.apply();
        drawGrid(camera);
        drawWorldBlocks(spriteBatchMapUI);

        drawCoordinates(spriteBatchMapUI);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        Gdx.gl.glClearColor(0.0f, 0.3f, 1.0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();

        drawWorld();

        stageManager.getActiveStage().getViewport().apply();
        stageManager.getActiveStage().getBatch().
                setProjectionMatrix(stageManager.getActiveStage().getCamera().combined);
        stageManager.getActiveStage().act();
        stageManager.getActiveStage().draw();
    }

    public Map getMap() { return map; }

    public void setMap(Map map) { this.map = map; }

    private void drawWorldBlocks(SpriteBatch batch) {
        if(map.getSprites().size > 0) {
            batch.setProjectionMatrix(mapUIViewport.getCamera().combined);
            batch.begin();
            for(Sprite sprite : map.getSprites()) {
                sprite.draw(batch);
            }
            batch.end();
        }
    }

    private void drawGrid(OrthographicCamera camera) {
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

        Vector2 gridSize = map.getGridSize();
        /* Optimizations. Grid can be resized without limit(except for the max number a typical
         * java float can hold)
         */
        for(float maxY=(height*camera.zoom); maxY >= 0; maxY--) {
            for(float maxX=(width*camera.zoom); maxX >= 0; maxX--) {
                float  x = (int) (((camera.position.x-(camera.viewportWidth/2)*camera.zoom)) + maxX);
                float y = (int) (((camera.position.y-(camera.viewportHeight/2)*camera.zoom)) + maxY);

                if(x >= 0 && x <= gridSize.x &&
                        y >= 0 && y <= gridSize.y) {
                    shapeRenderer.line(x, y, gridSize.x, y);
                    shapeRenderer.line(x, y, x, gridSize.y);
                }
            }
        }
        shapeRenderer.end();
    }

    private void drawCoordinates(SpriteBatch batch) {
        int x = (int) camera.position.x;
        int y = (int) camera.position.y;

        batch.setProjectionMatrix(uiCamera.projection);
        batch.begin();
        font.draw(batch, "X: " + x, 0-uiCamera.viewportWidth/2, 0+uiCamera.viewportHeight/2);
        font.draw(batch, "Y: " + y, 0-uiCamera.viewportWidth/2, 0+uiCamera.viewportHeight/2-80);
        batch.end();
    }

    public void exit() {
        // Used by outside classes that need to reset screen
        dispose();
        game.setScreen(new GameMenu(game));
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    private void addUIs() {
        // Add MapMakerUis
        stage.addActor(new AddButtonUI());
        stage.addActor(toolboxUI);
        stage.addActor(new CrosshairUI());

        stage.addActor(new SaveUI(this));
        stage.addActor(new LoadButtonUI(this, stageManager));
    }

    private void setListeners() {
        InputMultiplexer inputMultiplexer = new InputMultiplexer();

        inputMultiplexer.addProcessor(new GestureDetector(new MMGestureListener(this)));
        inputMultiplexer.addProcessor(new MMInputProcessor(this, toolboxUI));
        inputMultiplexer.addProcessor(stage);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);

        mapUIViewport.update(width, height);
        stageManager.getActiveStage().getViewport().update(width, height);
    }

    @Override
    public void dispose() {
        super.dispose();
        AssetManager.dispose();
        AssetManager.loadMenuUIS();
    }
}

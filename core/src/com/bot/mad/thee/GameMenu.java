package com.bot.mad.thee;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.bot.mad.thee.MenuUis.MapMakerButton;
import com.bot.mad.thee.MenuUis.StartButton;

/**
 * Title screen. This screen has two main textures(which will be changed eventually)
 * the Start texture and the Map Maker texture.
 */

public class GameMenu extends ScreenAdapter {
    Stage stage;

    public GameMenu(final Game game) {
        // Sets stage to a width of 16 and height of 9
        // This is necessary, in order to maintain proper aspect ratio
        // across multiple screen dimensions
        stage = new Stage(new StretchViewport(16, 9));
        stage.getViewport().apply();

        // Makes it so the stage can handle input
        Gdx.input.setInputProcessor(stage);

        // Initialize textures and add them to the stage
        StartButton startButton = new StartButton(game);
        MapMakerButton mapMakerButton = new MapMakerButton(game);
        stage.addActor(startButton);
        stage.addActor(mapMakerButton);
    }

    @Override
    public void render(float delta) {
        // Main loop, this is where the magic happens
        super.render(delta);

        Gdx.gl.glClearColor(0f,2f,4f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.getBatch().setProjectionMatrix(stage.getCamera().combined);
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        // Resizes stage accordingly, in the event of a screen rotation or screen
        // size change e.g. maximizing window on desktop
        super.resize(width, height);
        stage.getViewport().update(width, height);
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
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void dispose() {
        super.dispose();

        stage.dispose();
    }
}

package com.bot.mad.thee.MenuUis;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.bot.mad.thee.AssetManager;
import com.bot.mad.thee.GameRenderer;

/**
 * Start button. Extends the actor class from Libgdx(required for setting to stage)
 * then loads texture with appropriate x, y, width, and height values. Finally, sets
 * a listener so we can listen to input events.
 */

public class StartButton extends Actor {

    private Texture texture;
    private float width;
    private float height;
    private float x;
    private float y;
    private Game game;

    public StartButton(final Game game) {
        this.game = game;

        texture = AssetManager.START_BUTTON;

        /* The x, y, width, and height values(for the actor) are relative to
         * the stage width and height.
         */
        width = 2;
        height = 1;
        x = 4;
        y = 4.5f;
        this.setBounds(x, y, width, height);
        this.addListener(getMyListener());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        // Same as render but for actors
        // Basically, draws the "actor"
        batch.draw(texture, x, y, width, height);
    }

    private InputListener getMyListener() {
        return new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                // If user touches actor, dispose of current assets
                // and load next screen
                AssetManager.dispose();
                game.setScreen(new GameRenderer(game));
                return true;
            }
        };
    }
}

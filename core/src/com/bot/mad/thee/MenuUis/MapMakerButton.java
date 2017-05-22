package com.bot.mad.thee.MenuUis;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.bot.mad.thee.AssetManager;
import com.bot.mad.thee.MapMakerP.MapMakerMain;

/**
 * MapMaker button. Extends the actor class from Libgdx(required for setting to stage)
 * then loads texture with appropriate x, y, width, and height values. Finally, sets
 * a listener so we can listen to input events.
 */

public class MapMakerButton extends Actor {
    Texture texture;

    private int width;
    private int height;
    private float x;
    private float y;
    private Game game;

    public MapMakerButton(final Game game) {
        this.game = game;

        texture = AssetManager.MAP_MAKER_BUTTON;

        /* The x, y, width, and height values(for the actor) are relative to
         * the stage width and height.
         */
        width = 2;
        height = 1;
        x = 9;
        y = 4.5f;
        this.setBounds(x, y, width, height);
        setListeners();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        // Same as render but for actors
        // Basically, draws the "actor"
        batch.draw(texture, x, y, width, height);
    }

    private void setListeners() {
        this.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                /* Dispose of current assets, load the assets needed by the next screen,
                 * then set screen to Map Maker.
                 */
                AssetManager.dispose();
                AssetManager.loadMapMakerUIS();
                game.setScreen(new MapMakerMain(game));

                return true;
            }
        });
    }
}

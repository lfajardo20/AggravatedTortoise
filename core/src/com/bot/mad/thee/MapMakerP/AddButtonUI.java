package com.bot.mad.thee.MapMakerP;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.bot.mad.thee.AssetManager;

public class AddButtonUI extends Actor {

    private Texture texture;
    private float width;
    private float height;
    private float x;
    private float y;

    public AddButtonUI() {
        texture = AssetManager.ADD_BUTTON;
        width = .5f;
        height = .5f;
        x = 15.5f;
        y = 8.5f;

        this.setBounds(x, y, width, height);
        this.addListener(getInputListener());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, x, y, width, height);
    }

    private InputListener getInputListener() {
        return new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("Touched add button", "");
                Gdx.input.setOnscreenKeyboardVisible(true);

                return true;
            }
        };
    }
}

package com.bot.mad.thee.MapMakerP;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.bot.mad.thee.AssetManager;

class ToolboxBackgroundUI extends Actor {

    private Texture texture;
    private float width;
    private float height;
    private float x;
    private float y;

    ToolboxBackgroundUI() {
        texture = AssetManager.TOOLBOX;
        width = 16f;
        height = 2;
        x = 0;
        y = 0;
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
                Gdx.app.log("Touched toolbox background", "");
                return true;
            }
        };
    }
}

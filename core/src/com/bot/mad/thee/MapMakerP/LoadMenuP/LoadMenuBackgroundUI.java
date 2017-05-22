package com.bot.mad.thee.MapMakerP.LoadMenuP;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.bot.mad.thee.AssetManager;

public class LoadMenuBackgroundUI extends Actor {

    private float x;
    private float y;
    private float width;
    private float height;
    private Texture texture;

    public LoadMenuBackgroundUI() {
        x = 1;
        y = 2;
        width = 14;
        height = 6;
        texture = AssetManager.LOADMENUBACKGROUND;
        this.addListener(getInputListener());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        batch.draw(texture, x, y, width, height);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    private InputListener getInputListener() {
        return new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("Touched Loading Background", "");
                return true;
            }
        };
    }
}

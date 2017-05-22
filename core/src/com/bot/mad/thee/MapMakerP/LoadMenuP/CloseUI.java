package com.bot.mad.thee.MapMakerP.LoadMenuP;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.bot.mad.thee.AssetManager;
import com.bot.mad.thee.MapMakerP.MapMakerMain;

public class CloseUI extends LoadMenuSprite {

    public CloseUI(MapMakerMain mapMakerMain) {
        super(mapMakerMain, AssetManager.CLOSEICON);
        x = 14f;
        y = 7;
        width = 1;
        height = 1;
        setBounds();
        this.addListener(getInputListener());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        batch.draw(texture, x, y, width, height);
    }

    private InputListener getInputListener() {
        return new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("Touched CloseUI sprite", "");
                mapMakerMain.resetUI();
                return true;
            }
        };
    }
}

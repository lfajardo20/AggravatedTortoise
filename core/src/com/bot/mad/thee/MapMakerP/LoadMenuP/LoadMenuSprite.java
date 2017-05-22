package com.bot.mad.thee.MapMakerP.LoadMenuP;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.bot.mad.thee.MapMakerP.MapMakerMain;

class LoadMenuSprite extends Actor {
    MapMakerMain mapMakerMain;
    public float width;
    public float height;
    public float x;
    public float y;
    public Texture texture;

    LoadMenuSprite(MapMakerMain mapMakerMain, Texture texture) {
        this.mapMakerMain = mapMakerMain;
        width = 0;
        height = 0;
        x = 0;
        y = 0;
        this.texture = texture;
    }

    public void setBounds() {  this.setBounds(x, y, width, height);}

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        batch.draw(texture, x, y, width, height);
    }

}

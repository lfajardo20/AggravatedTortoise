package com.bot.mad.thee;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;

public class Map {
    private float gridWidth = 16.0f;
    private float gridHeight = 9.0f;
    private Array<Sprite> sprites = new Array<Sprite>();
    private Array<Body> bodies = new Array<Body>();

    public Map() {

    }

    public void addSprite(Sprite sprite) {
        this.sprites.add(sprite);
    }

    public void addBody(Body body) { this.bodies.add(body); }

    public void setWorldSize(Vector2 grid) {
        this.gridWidth = grid.x;
        this.gridHeight = grid.y;
    }

    public Array<Sprite> getSprites() {
        return this.sprites;
    }

    public Array<Body> getBodies() { return this.bodies; }

    public void setGridWidth(float gridWidth) {
        this.gridWidth = gridWidth;
    }

    public void setGridHeight(float gridHeight) {
        this.gridHeight = gridHeight;
    }

    public Vector2 getGridSize() {
        return new Vector2(this.gridWidth, this.gridHeight);
    }
}

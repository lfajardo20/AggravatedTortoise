package com.bot.mad.thee.MapMakerP;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.bot.mad.thee.AssetManager;

class SpriteActor extends Actor {
    private Texture texture;
    private float x;
    private float y;
    private float width;
    private float height;

    private ToolboxUI toolboxUI;

    private int index;
    private int state;

    SpriteActor() {
        state = 0;
        index = 0;
        x = 0;
        y = 0;
        width = 0;
        height = 0;
        this.addListener(getInputListener());
    }

    SpriteActor(Texture texture, ToolboxUI toolboxUI) {
        this();
        this.toolboxUI = toolboxUI;
        this.texture = texture;
    }

    public Texture getTexture() { return texture; }

    public void setIndex(int index) { this.index = index; }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        // update();
        drawStatus(batch);
        batch.draw(texture, x, y, width, height);
    }

    private void drawStatus(Batch batch) {
        if(toolboxUI.getIndex() != index) {
            state = 0;
        }
        switch (state) {
            case 1:
                batch.draw(AssetManager.SPRITETOUCHED, x-.4f, 0.1f, 1.8f, 1.8f);
                break;
            case 2:
                batch.draw(AssetManager.STICKY, x-.4f, 0.1f, 1.8f, 1.8f);
                break;
        }
    }

    public void setState(int state) { this.state = state; }

    public int getState() { return state; }

    public void setBounds() {
        this.setBounds(x, y, width, height);
    }

    private InputListener getInputListener() {
        return new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("Touched sprite actor #", index+"");
                state++;
                toolboxUI.setIndex(index);

                switch (state) {
                    case 1:

                        break;
                    case 2:

                        break;
                    case 3:
                        state = 0;
                        break;
                }

                return true;
            }
        };
    }

    public void setX(float x) { this.x = x; }

    public void setY(float y) { this.y = y; }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void setWidth(float width) { this.width = width; }

    public void setHeight(float height) { this.height = height; }

    public void setSize(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public void setTexture(Texture texture) { this.texture = texture; }


}

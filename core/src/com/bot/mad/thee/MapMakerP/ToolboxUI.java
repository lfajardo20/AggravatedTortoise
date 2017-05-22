package com.bot.mad.thee.MapMakerP;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;

public class ToolboxUI extends Group {
    private ToolboxBackgroundUI toolboxBackgroundUI;
    private ToolboxArrowUI toolboxArrowUI;

    private FileHandle[] fileHandle;
    private Array<Sprite> sprites;
    private int index = 0;
    private Array<SpriteActor> spriteActors;
    private boolean isAndroid;

    private MapMakerMain mapMakerMain;

    public ToolboxUI(MapMakerMain mapMakerMain) {
        this.mapMakerMain = mapMakerMain;

        switch(Gdx.app.getType()) {
            case Android:
                // android specific code
                isAndroid = true;
                break;
            case Desktop:
                // desktop specific code
                isAndroid = false;
                break;
        }
        spriteActors = new Array<SpriteActor>();
        toolboxBackgroundUI = new ToolboxBackgroundUI();
        toolboxArrowUI = new ToolboxArrowUI(this);

        addActor(toolboxBackgroundUI);
        addActor(toolboxArrowUI);
        refreshSprites();
    }

    public void drawState(MapMakerMain mapMakerMain, Vector3 coordinate) {
        switch (spriteActors.get(index).getState()) {
            case 1:
                {
                Sprite sprite = new Sprite(getCurrentTexture());
                sprite.setSize(1, 1);
                sprite.setPosition((int) coordinate.x, (int) coordinate.y);
                mapMakerMain.getMap().addSprite(sprite);
                spriteActors.get(index).setState(0);
                }
                break;
            case 2:
                {
                Sprite sprite = new Sprite(getCurrentTexture());
                sprite.setSize(1, 1);
                sprite.setPosition((int) coordinate.x, (int) coordinate.y);
                mapMakerMain.getMap().addSprite(sprite);
                }
                break;
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

    }

    public void changeVisibility() {
        toolboxBackgroundUI.setVisible(!toolboxBackgroundUI.isVisible());
        if(spriteActors.size > 0) {
            for(SpriteActor spriteActor : spriteActors) {
                spriteActor.setVisible(!spriteActor.isVisible());
            }
        }
    }

    public Texture getCurrentTexture() {
        Texture texture = null;
        if(index < spriteActors.size && index >= 0)
            texture = spriteActors.get(index).getTexture();
        return texture;
    }

    public int getIndex() { return index; }

    public void setIndex(int i) { index = i; }

    public void refreshSprites() {
        fileHandle = isAndroid ? Gdx.files.internal("Images/").list() : Gdx.files.local("Images/").list();
        float x = 1;
        float y = .5f;
        float width = 1;
        float height = 1;
        int count = 0;

        spriteActors = new Array<SpriteActor>();

        for(FileHandle file: fileHandle) {
            // do something interesting here
            if(!file.isDirectory()) {
                SpriteActor spriteActor = new SpriteActor(new Texture(file), this);

                spriteActor.setX(x);
                x+=1.5f;
                spriteActor.setY(y);
                spriteActor.setSize(width, height);
                spriteActor.setBounds();
                spriteActor.setIndex(count);
                spriteActors.add(spriteActor);
                addActor(spriteActor);
                count++;
            }
        }
        Gdx.app.log("Files found", count+"");
    }
}

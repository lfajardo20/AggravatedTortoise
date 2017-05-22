package com.bot.mad.thee.MapMakerP;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.FileTextureData;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.StringBuilder;
import com.bot.mad.thee.AssetManager;

/*
 * Save Button and its functionality. Saves current map's data in a new file and adds .map extension to file.
 * The map's data is saved simply by acquiring all the sprite's width, height, x, and y values and the map's
 * width and height and appending them to the file. The Parser class is where the file is actually read and loaded.
 */
public class SaveUI extends Actor {

    private Texture texture;
    private float width;
    private float height;
    private float x;
    private float y;

    private final String extension = ".map";
    private Input.TextInputListener myTextInputListener;
    private MapMakerMain mapMakerMain;

    public SaveUI(MapMakerMain mapMakerMain) {
        this.mapMakerMain = mapMakerMain;

        texture = AssetManager.SAVEICON;
        width = .5f;
        height = .5f;
        x = 15.5f;
        y = 7.5f;
        this.setBounds(x, y, width, height);

        this.addListener(getInputListener());
        setTextInputListener();
    }

    private void saveMap(String fileName) {
        Gdx.app.log("Saving", "");
        FileHandle fileHandle = Gdx.files.local("Maps/" + fileName + extension);
        fileHandle.writeString("", false);

        fileHandle.writeString("gridwidth="+mapMakerMain.getMap().getGridSize().x+";", true);
        fileHandle.writeString("gridheight="+mapMakerMain.getMap().getGridSize().y+";", true);

        for(int i=0; i < mapMakerMain.getMap().getSprites().size; i++) {
            StringBuilder mapData = new StringBuilder();
            Sprite sprite = mapMakerMain.getMap().getSprites().get(i);
            String path = ((FileTextureData) sprite.getTexture().getTextureData()).getFileHandle().path();
            mapData.append("{");
            // mapData.append("sprite: ");
            mapData.append("path: "+path+";");
            mapData.append("x ="+sprite.getX()+";");
            mapData.append("y ="+sprite.getY()+";");
            mapData.append("width ="+sprite.getWidth()+";");
            mapData.append("height ="+sprite.getHeight()+";");
            mapData.append('}');
            mapData.append("\n");
            fileHandle.writeString(mapData.toString(), true);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, x, y, width, height);
    }

    private InputListener getInputListener() {
        return new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("Touched save icon", "");
                Gdx.input.getTextInput(myTextInputListener, "File name: ", "", "");

                return true;
            }
        };
    }

    private void setTextInputListener() {
        myTextInputListener = new Input.TextInputListener() {
            @Override
            public void input(String text) {
                saveMap(text);
            }

            @Override
            public void canceled() {

            }
        };
    }
}

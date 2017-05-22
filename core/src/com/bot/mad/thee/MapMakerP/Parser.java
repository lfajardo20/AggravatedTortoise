package com.bot.mad.thee.MapMakerP;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.StringBuilder;
import com.bot.mad.thee.Map;
import com.bot.mad.thee.MyWorld;


public class Parser {
    private String spritePath = null;
    private float spriteX = 0;
    private float spriteY = 0;
    private float spriteWidth = 1;
    private float spriteHeight = 1;
    private float gridWidth = 16;
    private float gridHeight = 9;
    private Map maps[];
    private int mapIndex = 0;
    private Sprite sprite = null;
    private char data[];

    private StringBuilder variable = new StringBuilder();
    private StringBuilder value = new StringBuilder();

    public Parser() {

    }

    public Map[]  load() {
        FileHandle fileHandle[] = Gdx.files.internal("Maps/").list();
        if (fileHandle == null) return null;
        maps = new Map[fileHandle.length];

        for(mapIndex=0; mapIndex < fileHandle.length; mapIndex++) {
            maps[mapIndex] = new Map();
            String map = fileHandle[mapIndex].readString();
            Gdx.app.log("File length", map.length() + "");
            data = map.toCharArray();

            for (int i = 0; i < data.length; ) {
                i = evaluateVariable(i);
                i = evaluateValue(i);

                addData(variable.toString(), value.toString());
            }
            Gdx.app.log("Finished loading one map", ".....");
        }
        return maps;
        /*mapMakerMain.setGridSize(gridWidth, gridHeight);
        if(sprites.size > 0) {
            mapMakerMain.setWorld(sprites);
        }*/
    }

    private int evaluateVariable(int i) {
        variable = new StringBuilder();
        boolean variableParsed = false;
        for(;i < data.length && !variableParsed; i++){
            switch (data[i]) {
                case '{':
                    variableParsed = true;
                    i = evaluateVariable(++i)-1;
                    break;
                case '}':
                    // Creates sprites
                    sprite = new Sprite(new Texture(spritePath));
                    sprite.setSize(spriteWidth, spriteHeight);
                    sprite.setPosition(spriteX, spriteY);
                    maps[mapIndex].addSprite(sprite);

                    // Creates bodies for physics engine
                    BodyDef bodyDef = new BodyDef();
                    bodyDef.type = BodyDef.BodyType.StaticBody;
                    bodyDef.position.set(spriteX, spriteY);

                    Body body = MyWorld.world.createBody(bodyDef);
                    maps[mapIndex].addBody(body);

                    // Create a polygon shape and add a fixture to our shape
                    PolygonShape polygonShape = new PolygonShape();
                    Vector2[] vertices = new Vector2[4];
                    for(int j=0; j < vertices.length; j++) {
                        vertices[j] = new Vector2();
                    }
                    vertices[0].set(0, 0);
                    vertices[1].set(0, 0+spriteHeight);
                    vertices[2].set(0+spriteWidth, 0+spriteHeight);
                    vertices[3].set(0+spriteWidth, 0);
                    polygonShape.set(vertices);

                    FixtureDef fixtureDef = new FixtureDef();
                    fixtureDef.shape = polygonShape;

                    body.createFixture(fixtureDef);
                    polygonShape.dispose();

                    variableParsed = true;
                    i = evaluateVariable(++i)-1;
                    reset();
                    break;
                case ' ':
                    break;
                case ';':
                case '=':
                case ':':
                    variableParsed = true;
                    break;
                default:
                    variable.append(data[i]);
                    break;
            }
        }
        return i;
    }

    private int evaluateValue(int i) {
        value = new StringBuilder();
        boolean valueParsed = false;
        for (;i < data.length && !valueParsed; i++){
            switch (data[i]) {
                case '=':
                case ' ':
                    break;
                case ';':
                    valueParsed = true;
                    break;
                default:
                    value.append(data[i]);
                    break;
            }
        }

        Gdx.app.log("Value", value.toString());
        return i;
    }

    private void addData(String variable, String value) {
        // Converts the string to all upper case letters to avoid overhead using equalsIgnoreCase() several times.
        variable = variable.toUpperCase();
        if (variable.equals("GRIDWIDTH")) {
            gridWidth = Float.parseFloat(value);
            return;
        } else if (variable.equals("GRIDHEIGHT")) {
            gridHeight = Float.parseFloat(value);
            return;
        }

        if (variable.equals("WIDTH")) {
            spriteWidth = Float.parseFloat(value);
        } else if (variable.equals("HEIGHT")) {
            spriteHeight = Float.parseFloat(value);
        } else if (variable.equals("PATH")) {
            spritePath = value;
        } else if (variable.equals("X")) {
            spriteX = Float.parseFloat(value);
        } else if (variable.equals("Y")) {
            spriteY = Float.parseFloat(value);
        }
    }

    private void reset() {
        Gdx.app.log("New sprite!", "");
        spritePath = null;
        spriteX = 0;
        spriteY = 0;
        spriteWidth = 1;
        spriteHeight = 1;
        sprite = null;
    }

}

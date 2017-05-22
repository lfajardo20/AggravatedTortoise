package com.bot.mad.thee;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

/**
 * This class handles all textures within the app. Comes in handy, since you only need to call the Assets
 * you need and then free them up by calling AssetManager.dispose(). The class keeps track of which textures
 * have been loaded so you don't have to know which specific textures you wish to dispose(Also, this avoids a
 * nasty run-time exception which occurs when the dispose function has been called on a texture that is already
 * disposed).
 */

public class AssetManager {
    private static boolean loaded = false;
    private static boolean loadedMapMakerUIS = false;
    private static boolean loadedMenuUIS = false;
    private static boolean loadedSprites = false;
    public static Texture PLAYER_BLUE;
    public static Texture PLAYER_RED;
    public static Texture GROUND;
    public static Texture BIG_GROUND;
    public static Texture TOOLBOXARROW;

    // Menu UI's
    public static Texture START_BUTTON;
    public static Texture MAP_MAKER_BUTTON;

    // Map Maker UI's
    public static Texture ADD_BUTTON;
    public static Texture TOOLBOX;
    public static Texture CROSSHAIR;
    public static Texture SAVEICON;
    public static Texture LOADICON;
    public static Texture STICKYON;
    public static Texture STICKYOFF;
    public static Texture STICKY;
    public static Texture SPRITETOUCHED;
    public static Texture CLOSEICON;
    public static Texture LOADMENUBACKGROUND;
    public static Texture COLLISIONON;

    public static BitmapFont COORDINATE_FONT;

    public static void loadMapMakerUIS() {
        if(!loadedMapMakerUIS) {
            ADD_BUTTON = new Texture(Gdx.files.internal("Images/MapMakerUis/addbutton.png"));
            TOOLBOX = new Texture(Gdx.files.internal("Images/MapMakerUis/toolbox.png"));
            TOOLBOXARROW = new Texture(Gdx.files.internal("Images/MapMakerUis/toolboxarrow.png"));
            CROSSHAIR = new Texture(Gdx.files.internal("Images/MapMakerUis/crosshair.png"));
            SAVEICON = new Texture(Gdx.files.internal("Images/MapMakerUis/saveicon.png"));
            LOADICON = new Texture(Gdx.files.internal("Images/MapMakerUis/loadicon.png"));
            STICKYON = new Texture(Gdx.files.internal("Images/MapMakerUis/stickyon.png"));
            STICKYOFF = new Texture(Gdx.files.internal("Images/MapMakerUis/stickyoff.png"));
            STICKY = new Texture(Gdx.files.internal("Images/MapMakerUis/sticky.png"));
            SPRITETOUCHED = new Texture(Gdx.files.internal("Images/MapMakerUis/spritetouched.png"));
            CLOSEICON = new Texture(Gdx.files.internal("Images/MapMakerUis/closeicon.png"));
            LOADMENUBACKGROUND = new Texture(Gdx.files.internal("Images/MapMakerUis/loadmenubackground.png"));
            COLLISIONON = new Texture(Gdx.files.internal("Images/MapMakerUis/collisionon.png"));

            FileHandle file = Gdx.files.internal("data.ttf");
            FreeTypeFontGenerator generator = new FreeTypeFontGenerator(file);
            FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
            parameter.size = 58;
            COORDINATE_FONT = generator.generateFont(parameter);
            generator.dispose();

            loadedMapMakerUIS = true;
        }
    }

    public static void loadMenuUIS() {
        if(!loadedMenuUIS) {
            START_BUTTON = new Texture(Gdx.files.internal("Images/MenuUis/startbutton.png"));
            MAP_MAKER_BUTTON = new Texture(Gdx.files.internal("Images/MenuUis/mapmakerbutton.png"));
            loadedMenuUIS = true;
        }
    }

    public static void loadSprites() {
        if(!loadedSprites) {
            PLAYER_BLUE = new Texture(Gdx.files.internal("Images/blueplayer.png"));
            PLAYER_RED = new Texture(Gdx.files.internal("Images/redplayer.png"));
            GROUND = new Texture(Gdx.files.internal("Images/ground.png"));
            BIG_GROUND = new Texture(Gdx.files.internal("Images/bigGround.png"));

            loadedSprites = true;
        }
    }

    public static void loadAll() {
        if(!loaded) {
            if(!loadedMapMakerUIS) {
                loadMapMakerUIS();
            }
            if(!loadedMenuUIS) {
                loadMenuUIS();
            }
            if(!loadedSprites) {
                loadSprites();
            }

            loaded = true;
        }
    }

    public static void dispose() {
        if(!loaded) {
            if(loadedSprites) {
                PLAYER_BLUE.dispose();
                PLAYER_RED.dispose();
                GROUND.dispose();
                BIG_GROUND.dispose();

                loadedSprites = false;
            }

            if(loadedMenuUIS) {
                START_BUTTON.dispose();
                MAP_MAKER_BUTTON.dispose();

                loadedMenuUIS = false;
            }
            if(loadedMapMakerUIS) {
                TOOLBOX.dispose();
                TOOLBOXARROW.dispose();
                CROSSHAIR.dispose();
                SAVEICON.dispose();
                LOADICON.dispose();
                STICKYON.dispose();
                STICKYOFF.dispose();
                STICKY.dispose();
                SPRITETOUCHED.dispose();
                CLOSEICON.dispose();
                LOADMENUBACKGROUND.dispose();
                COLLISIONON.dispose();

                COORDINATE_FONT.dispose();

                loadedMapMakerUIS = false;
            }

            loaded = false;
        }
    }
}

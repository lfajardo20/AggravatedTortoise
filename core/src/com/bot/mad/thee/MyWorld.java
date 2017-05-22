package com.bot.mad.thee;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

/* This class represents the game world's width and height and the physic's engine world.
 * Note:
 * Changed to static fields at last-minute, in order to make the code work with all
 * the changes. A non-static procedure might be more preferable.
 */
public class MyWorld {
    private float PIXELS_PER_METRE = 32; // just for reference
    public final static float IMAGINARY_WIDTH = 16; // in Imaginary units after conversion
    public final static float IMAGINARY_HEIGHT = 9; // in Imaginary units after conversion

    public static World world = new World(new Vector2(0, -11), true);
    public static Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();
}

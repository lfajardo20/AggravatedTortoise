package com.bot.mad.thee.MapMakerP;

import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

public class MMGestureListener implements GestureDetector.GestureListener {
    private MapMakerMain mapMakerMain;

    public MMGestureListener(MapMakerMain mapMakerMain) {
        this.mapMakerMain = mapMakerMain;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        float maxZoom = 5f;
        float minZoom = 1f;

        if (initialDistance >= distance) {
            if (mapMakerMain.getCamera().zoom <= maxZoom)
                mapMakerMain.getCamera().zoom += 0.02;
        } else {
            if(mapMakerMain.getCamera().zoom >= minZoom)
                mapMakerMain.getCamera().zoom -= 0.02;
        }

        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }
}

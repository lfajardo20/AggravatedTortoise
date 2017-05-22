package com.bot.mad.thee;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class GameInputProcessor implements InputProcessor {
    GameRenderer gameRenderer;

    public GameInputProcessor(GameRenderer gameRenderer) {
        this.gameRenderer = gameRenderer;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.R:
                gameRenderer.reset();
                break;
            case Input.Keys.P:
                gameRenderer.pauseWorld();
                break;
            case Input.Keys.ESCAPE:
                gameRenderer.quit();
                break;
            case Input.Keys.BACK:
                gameRenderer.quit();
                break;
        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(pointer == 1) { gameRenderer.reset(); }

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
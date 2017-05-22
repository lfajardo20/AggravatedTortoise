package com.bot.mad.thee.MapMakerP;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

class MMInputProcessor implements InputProcessor {
    private MapMakerMain mapMakerMain;

    private StringBuilder response;
    private Vector2 lastTouch;
    private boolean isXSet;
    private boolean firstInputDiscarded;

    private final float maxZoom = 5f;
    private final float minZoom = 1f;
    private ToolboxUI toolboxUI;

    MMInputProcessor(MapMakerMain mapMakerMain, ToolboxUI toolboxUI) {
        this.toolboxUI = toolboxUI;
        this.mapMakerMain = mapMakerMain;

        response = new StringBuilder();
        lastTouch = new Vector2();
        isXSet = false;
        firstInputDiscarded = false;
    }

    /* Mostly used for recording change of width and height in grid
     * and used to show user the amount; however, i removed some outdated code
     * and this no longer displays the amount but still records it.
     */
    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.ESCAPE:
            case Input.Keys.BACK:
                mapMakerMain.exit();
                break;
            case Input.Keys.NUM_0:
                response.append("0");
                break;
            case Input.Keys.NUM_1:
                response.append("1");
                break;
            case Input.Keys.NUM_2:
                response.append("2");
                break;
            case Input.Keys.NUM_3:
                response.append("3");
                break;
            case Input.Keys.NUM_4:
                response.append("4");
                break;
            case Input.Keys.NUM_5:
                response.append("5");
                break;
            case Input.Keys.NUM_6:
                response.append("6");
                break;
            case Input.Keys.NUM_7:
                response.append("7");
                break;
            case Input.Keys.NUM_8:
                response.append("8");
                break;
            case Input.Keys.NUM_9:
                response.append("9");
                break;
            case Input.Keys.ENTER:
                try {
                    if (!isXSet) {
                        Gdx.app.log("X Recieved", response.toString());
                        mapMakerMain.getMap().setGridWidth(Float.parseFloat(response.toString()));
                        isXSet = true;
                        response = new StringBuilder();
                    } else {
                        Gdx.app.log("Y Recieved", response.toString());
                        mapMakerMain.getMap().setGridHeight(Float.parseFloat(response.toString()));
                        response = new StringBuilder();
                        isXSet = false;
                    }
                } catch (NumberFormatException exc) {
                    Gdx.app.log("Input was NULL", "No Values Changed");
                }
                break;
            case Input.Keys.MINUS:
                float requestedZoom = mapMakerMain.getCamera().zoom * 1.5f;
                if (requestedZoom >= minZoom && requestedZoom <= maxZoom) {
                    mapMakerMain.getCamera().zoom = requestedZoom;
                    Gdx.app.log("Zoom", mapMakerMain.getCamera().zoom + "");
                }
                break;
            case Input.Keys.PLUS:
                float requestedZoom2 = mapMakerMain.getCamera().zoom / 1.5f;
                if (requestedZoom2 >= minZoom && requestedZoom2 <= maxZoom) {
                    mapMakerMain.getCamera().zoom = requestedZoom2;
                    Gdx.app.log("Zoom", mapMakerMain.getCamera().zoom + "");
                }
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
        // pointer index starts at 0( 0 == 1 finger)
        switch (pointer) {
            case 0:
                lastTouch.set(screenX, screenY);

                Vector3 coordinate = new Vector3(screenX, screenY, 0);
                coordinate.set(mapMakerMain.getCamera().unproject(coordinate));
                Vector2 gridSize = mapMakerMain.getMap().getGridSize();

                if (coordinate.x >= 0 && coordinate.x <= gridSize.x &&
                        coordinate.y >= 0 && coordinate.y <= gridSize.y) {
                    toolboxUI.drawState(mapMakerMain, coordinate);
                }
                break;
        }

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (pointer < 1) {
            // Discard first input - fixes bug where camera is placed elsewhere after map maker screen change
            firstInputDiscarded = true;
        }

        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (pointer < 1 && lastTouch != null && firstInputDiscarded) {
            Vector2 newTouch = new Vector2(screenX, screenY);
            // delta will now hold the difference between the last and the current touch positions
            // delta.x > 0 means the touch moved to the right, delta.x < 0 means a move to the left
            Vector2 delta = newTouch.cpy().sub(lastTouch);
            lastTouch = newTouch;
            float slow = 40f;
            mapMakerMain.getCamera().translate((-delta.x / slow) * mapMakerMain.getCamera().zoom,
                    (delta.y / slow) * mapMakerMain.getCamera().zoom, 0);
        }
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
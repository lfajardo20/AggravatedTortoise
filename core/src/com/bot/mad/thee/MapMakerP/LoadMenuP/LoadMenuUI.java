package com.bot.mad.thee.MapMakerP.LoadMenuP;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.bot.mad.thee.MapMakerP.MapMakerMain;

public class LoadMenuUI extends Group {

    private MapMakerMain mapMakerMain;
    private ShapeRenderer shapeRenderer;
    private OrthographicCamera camera;
    private float x;
    private float y;
    private float width;
    private float height;

    public LoadMenuUI(MapMakerMain mapMakerMain) {
        this.mapMakerMain = mapMakerMain;
        shapeRenderer = new ShapeRenderer();
        width = 0;
        height = 0;
        camera = mapMakerMain.getCamera();
        x = 0;
        y = 0;

        this.setBounds(x, y, width, height);
        this.addListener(getInputListener());
        this.addActor(new LoadMenuBackgroundUI());
        this.addActor(new CloseUI(mapMakerMain));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }
    private InputListener getInputListener() {
        return new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                Gdx.app.log("Touched Loading Menu", "");
                return true;
            }
        };
    }
}

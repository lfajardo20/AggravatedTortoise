package com.bot.mad.thee.MapMakerP;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.bot.mad.thee.AssetManager;
import com.bot.mad.thee.MapMakerP.LoadMenuP.LoadMenuUI;
import com.bot.mad.thee.MyWorld;


public class LoadButtonUI extends Actor {

    private Texture texture;
    private float width;
    private float height;
    private float x;
    private float y;

    private Parser parser;

    private MapMakerMain mapMakerMain;
    private StageManager stageManager;
    private Stage loadMenuStage;

    public LoadButtonUI(MapMakerMain mapMakerMain, StageManager stageManager) {
        this.mapMakerMain = mapMakerMain;
        this.stageManager = stageManager;

        texture = AssetManager.LOADICON;
        width = .5f;
        height = .5f;
        x = 15.5f;
        y = 5.5f;
        this.setBounds(x, y, width, height);
        this.addListener(getInputListener());

        loadMenuStage = new Stage(new StretchViewport(MyWorld.IMAGINARY_WIDTH, MyWorld.IMAGINARY_HEIGHT));
        loadMenuStage.addActor(new LoadMenuUI(mapMakerMain));


        parser = new Parser();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, x, y, width, height);
    }

    private InputListener getInputListener() {
        return new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("load button", "");
                stageManager.setStage(loadMenuStage);
                mapMakerMain.setMap(parser.load()[0]);
                // mapMakerMain.setMaps(parser.load());
                stageManager.setStage(loadMenuStage);
                Gdx.input.setInputProcessor(loadMenuStage);

                return true;
            }
        };
    }
}

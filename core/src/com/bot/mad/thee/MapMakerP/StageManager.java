package com.bot.mad.thee.MapMakerP;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class StageManager {
    private Stage activeStage;

    public StageManager() {

    }

    public void setStage(Stage stage) {
        this.activeStage = stage;
    }

    public Stage getActiveStage() {
        return activeStage;
    }
}

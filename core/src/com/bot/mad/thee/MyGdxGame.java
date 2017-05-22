package com.bot.mad.thee;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

/* Main Class. Sets back key(so screen can be backed out). Loads assets, necessary
 * for title screen and then sets screen.
 */
public class MyGdxGame extends Game {

	@Override
	public void create () {
        Gdx.input.setCatchBackKey(true);
		AssetManager.loadMenuUIS();
		setScreen(new GameMenu(this));
	}

	@Override
	public void dispose () {

	}
}

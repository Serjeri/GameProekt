package com.my.game;

import com.badlogic.gdx.Game;
import com.my.game.Screen.MenuScreen;


public class MyGdxGame extends Game {

	@Override
	public void create() {
		setScreen(new MenuScreen(this));
	}
}

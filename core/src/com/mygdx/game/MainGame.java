package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.game.screens.MainMenuScreen;
import com.mygdx.game.screens.WelcomeScreen;

public class MainGame extends Game {
	private BitmapFont font;

	@Override
	public void create() {
		font = new BitmapFont();
		setScreen(new WelcomeScreen(this));
	}

	// Getter method for the font
	public BitmapFont getFont() {
		return font;
	}
	private boolean processInput = true;


	public boolean canProcessInput() {
		return processInput;
	}

	public void setProcessInput(boolean processInput) {
		this.processInput = processInput;
	}

}

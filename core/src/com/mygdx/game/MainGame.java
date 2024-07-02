package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.screens.CharacterSelectionScreen;
import com.mygdx.game.screens.WelcomeScreen;


public class MainGame extends Game {
	SpriteBatch batch;
	@Override
	public void create () {
		batch = new SpriteBatch();
		this.setScreen(new WelcomeScreen(this));
		//this.setScreen(new CharacterSelectionScreen(this));
		//this.setScreen(new GamePlayScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}

	private boolean processInput = true;


	public boolean canProcessInput() {
		return processInput;
	}

	public void setProcessInput(boolean processInput) {
		this.processInput = processInput;
	}
}

package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.mygdx.game.MainGame;

public class WelcomeScreen implements Screen, InputProcessor {

    private MainGame game;
    private OrthographicCamera camera;
    private SpriteBatch batch;

    private boolean showPressAnyButton = true;
    private Texture pressAnyButtonTexture;
    private Texture titleTexture;
    private Texture welcomeTexture;
    private boolean allowInput = true;


    public WelcomeScreen(MainGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        batch = new SpriteBatch();
        welcomeTexture = new Texture("background.png");
        pressAnyButtonTexture = new Texture("press_any_key_to_start.png");
        titleTexture = new Texture("game_title.png");

        Gdx.input.setInputProcessor(this);

        // Toggle message visibility every second
        Timer.schedule(new Task() {
            @Override
            public void run() {
                showPressAnyButton = !showPressAnyButton;
            }
        }, 1, 1);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(welcomeTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Draw title image above the background
        float titleX = Gdx.graphics.getWidth() / 2f - titleTexture.getWidth() / 2f;
        float titleY = Gdx.graphics.getHeight() * 2 / 3f; // Adjust the factor as needed
        batch.draw(titleTexture, titleX, titleY);

        // Draw press any button image with adjusted size and position
        if (showPressAnyButton) {
            float imageX = Gdx.graphics.getWidth() / 2f - pressAnyButtonTexture.getWidth() / 2f;
            float imageY = Gdx.graphics.getHeight() / 3f - pressAnyButtonTexture.getHeight() / 2f;
            batch.draw(pressAnyButtonTexture, imageX, imageY);
        }
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
        batch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        batch.dispose();
        welcomeTexture.dispose();
        pressAnyButtonTexture.dispose();
        titleTexture.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (game.canProcessInput()) {
            game.setProcessInput(false);
            game.setScreen(new MainMenuScreen(game));
        }
        return true; // indicate that the input has been handled
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (game.canProcessInput()) {
            game.setProcessInput(false);
            game.setScreen(new MainMenuScreen(game));
        }
        return true; // indicate that the input has been handled
    }


    public void clearInputState() {
        Gdx.input.setInputProcessor(null);
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
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
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
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}

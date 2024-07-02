package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.MainGame;
import com.mygdx.game.soundeffectsmanager.SoundManager;

public class ControlsScreen implements Screen {

    private SpriteBatch batch;
    private Texture image;
    private OrthographicCamera camera;
    private Stage stage;
    private MainGame game;
    private MainMenuScreen mainMenuScreen;


    public ControlsScreen(MainGame game,MainMenuScreen mainMenuScreen) {
        this.game = game;
        this.mainMenuScreen = mainMenuScreen;
    }


    @Override
    public void show() {
        camera = new OrthographicCamera();
        batch = new SpriteBatch();
        image = new Texture("controls_screen.png");
        stage = new Stage();

        Gdx.input.setInputProcessor(stage);

        // Create a button with up and down textures
        Texture back_btn = new Texture("back.png");
        ImageButton button = new ImageButton(new TextureRegionDrawable(back_btn));

        // Set button position to bottom right corner
        button.setPosition(Gdx.graphics.getWidth() * 0.85f, Gdx.graphics.getHeight() * 0.1f);

        // Add a click listener to handle button click
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new SettingsScreen(game,mainMenuScreen));
                SoundManager.playSwitchSound();
            }
        });

        // Add button to the stage
        stage.addActor(button);
    }

    @Override
    public void render(float delta) {
        // Clear the screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(image, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        // Draw the stage (including the button)
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
        batch.setProjectionMatrix(camera.combined);
        stage.getViewport().update(width, height, true);
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
        image.dispose();
        stage.dispose();
    }
}

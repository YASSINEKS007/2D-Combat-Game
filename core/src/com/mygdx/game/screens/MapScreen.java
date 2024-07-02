package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MainGame;
import com.mygdx.game.soundeffectsmanager.SoundManager;

public class MapScreen implements Screen {
    private MainGame game;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Array<Texture> images;
    private int currentImageIndex = 0;
    private MainMenuScreen mainMenuScreen;
    private Stage stage;
    private ImageButton backButton;


    public MapScreen(MainGame game, MainMenuScreen mainMenuScreen) {
        this.game = game;
        this.mainMenuScreen = mainMenuScreen;
        createUI();
    }

    private void createUI() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch = new SpriteBatch();
        stage = new Stage();

        Gdx.input.setInputProcessor(stage);

        // Load your images
        images = new Array<>();
        images.add(new Texture("casa.jpg"));
        images.add(new Texture("marrakech.jpg"));
        images.add(new Texture("rabat.jpg"));

        // Create a button with up and down textures
        Texture back_btn = new Texture("back.png");
        backButton = new ImageButton(new TextureRegionDrawable(back_btn));

        // Set button position to bottom right corner
        backButton.setPosition(Gdx.graphics.getWidth() * 0.90f, Gdx.graphics.getHeight() * 0.02f);

        // Add a click listener to handle button click
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(mainMenuScreen);
                SoundManager.playSwitchSound();
            }
        });

        // Add button to the stage
        stage.addActor(backButton);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(images.get(currentImageIndex), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        stage.act(delta);
        stage.draw();

        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            switchToPreviousImage();
            SoundManager.playSwitchSound();

        } else if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            switchToNextImage();
            SoundManager.playSwitchSound();

        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            game.setScreen(new CharacterSelectionScreen(game, currentImageIndex));
        }

    }

    private void switchToPreviousImage() {
        currentImageIndex = (currentImageIndex - 1 + images.size) % images.size;
    }

    private void switchToNextImage() {
        currentImageIndex = (currentImageIndex + 1) % images.size;
    }
    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
        camera.update();
    }


    @Override
    public void pause() {
        // Handle screen pause
    }

    @Override
    public void resume() {
        // Handle screen resume
    }

    @Override
    public void hide() {
        // Handle screen hide
    }

    @Override
    public void dispose() {
        batch.dispose();
        for (Texture texture : images) {
            texture.dispose();
        }
    }
}

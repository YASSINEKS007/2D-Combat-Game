package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.MainGame;
import com.mygdx.game.soundeffectsmanager.SoundManager;

public class SettingsScreen implements Screen {

    private MainGame game;
    private OrthographicCamera camera;
    private Stage stage;
    private SpriteBatch batch;
    private MainMenuScreen mainMenuScreen;
    private Texture titleTexture;
    private ImageButton Controls_btn;
    private CustomSlider customSlider;
    private CustomSlider customSlider2;
    private Preferences preferences;

    private Sound switchSound; // Declare a Sound variable

    public SettingsScreen(MainGame game, MainMenuScreen mainMenuScreen) {
        this.game = game;
        this.mainMenuScreen = mainMenuScreen;
    }


    @Override
    public void show() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        titleTexture = new Texture("settings_screen_title.png");

        stage = new Stage(new ScreenViewport(camera));
        Gdx.input.setInputProcessor(stage);

        batch = new SpriteBatch();

        preferences = Gdx.app.getPreferences("MyGamePreferences"); // Initialize preferences

        createUI();

    }
    public static float getSavedSlider1Value() {
        // Retrieve and return the last saved value for slider1
        return Gdx.app.getPreferences("MyGamePreferences").getFloat("slider1Value", 0.5f);
    }

    public static float getSavedSlider2Value() {
        // Retrieve and return the last saved value for slider2
        return Gdx.app.getPreferences("MyGamePreferences").getFloat("slider2Value", 0.5f);
    }

    private void createUI() {
        Table table = new Table();
        table.setFillParent(true);
        table.padTop(-150);

        // Load texture for the center image
        Texture centerImageTexture = new Texture("Music_label.png");
        TextureRegion centerImageRegion = new TextureRegion(centerImageTexture);
        Image centerImage = new Image(centerImageRegion);

        // Add the center image to the top of the screen with additional top padding
        table.add(centerImage).top().padTop(Gdx.graphics.getHeight() * 0.1f);

        Image myImage = new Image(new Texture("slide_bar_track.png"));

        float w = myImage.getWidth();
        float h = myImage.getHeight();

        customSlider = new CustomSlider(100, 100, w, h,mainMenuScreen,true);

        // Add the custom slider to the same row
        table.add(customSlider).padTop(Gdx.graphics.getHeight() * 0.1f).row();

        Texture centerImageTexture2 = new Texture("sound_effects_label.png");
        TextureRegion centerImageRegion2 = new TextureRegion(centerImageTexture2);
        Image centerImage2 = new Image(centerImageRegion2);

        // Add the center image to the top of the screen with additional top padding
        table.add(centerImage2).top().padRight(Gdx.graphics.getWidth() * 0.2f).padTop(Gdx.graphics.getHeight() * 0.1f).padLeft(100);

        customSlider2 = new CustomSlider(100, 100, w, h,mainMenuScreen,false);
        customSlider2.setValue(SoundManager.getSoundEffectsVolume()); // Set initial value

        // Add the custom slider to the same row with additional bottom padding
        table.add(customSlider2).padTop(Gdx.graphics.getHeight() * 0.11f).padBottom(Gdx.graphics.getHeight() * 0.02f).row();
        try {
            float savedSlider1Value = preferences.getFloat("slider1Value", 0.5f);
            float savedSlider2Value = preferences.getFloat("slider2Value", 0.7f);

            customSlider.setValue(savedSlider1Value);
            customSlider2.setValue(savedSlider2Value);

            // Update the SoundManager volume
            SoundManager.setSoundEffectsVolume(savedSlider2Value);


        } catch (Exception e) {
            // Handle exception, e.g., log it
            Gdx.app.error("SettingsScreen", "Error loading preferences", e);
        }



        // Assuming you have an Image instance
        Texture Controls = new Texture("Controls_label.png");
        Controls_btn = new ImageButton(new TextureRegionDrawable(new TextureRegion(Controls)));
        table.add(Controls_btn).top().padTop(Gdx.graphics.getHeight() * 0.1f).colspan(2).row();

        Controls_btn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("Controls", "Back button clicked!");
                game.setScreen(new ControlsScreen(game, mainMenuScreen));
                SoundManager.playSwitchSound();

            }
        });

        Texture back_btn = new Texture("back.png");
        ImageButton button = new ImageButton(new TextureRegionDrawable(back_btn));
        button.setName("backButton");  // Set a name for the button

        // Set button position to bottom right corner
        button.setPosition(Gdx.graphics.getWidth() * 0.85f, Gdx.graphics.getHeight() * 0.1f);

        // Add a click listener to handle button click
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(mainMenuScreen);
                SoundManager.playSwitchSound();
            }
        });

        // Add button to the stage
        stage.addActor(button);

        // Add the table to the stage
        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Handle input events
        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            customSlider.updateKnobPosition(touchPos.x, touchPos.y);
            customSlider2.updateKnobPosition(touchPos.x, touchPos.y);

            // Get the values of each CustomSlider
            float slider1Value = customSlider.getValue();
            float slider2Value = customSlider2.getValue();

            // Now you can use slider1Value and slider2Value as needed
            Gdx.app.log("Slider Values", "Slider 1: " + slider1Value + ", Slider 2: " + slider2Value);
        }

        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        Texture welcomeTexture = new Texture("empty.png");
        batch.draw(welcomeTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Draw title image above the background
        float titleX = Gdx.graphics.getWidth() / 2f - titleTexture.getWidth() / 2f;
        float titleY = Gdx.graphics.getHeight() * 5 / 6f; // Move the title even higher
        batch.draw(titleTexture, titleX, titleY);

        // Draw the arrow using the Arrow class
        batch.end();

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }


    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
        camera.setToOrtho(false, width, height);

        // Calculate the new position of the back button
        float backButtonXPercentage = 0.85f;
        float backButtonYPercentage = 0.1f;
        float backButtonX = width * backButtonXPercentage;
        float backButtonY = height * backButtonYPercentage;

        // Retrieve the back button actor and update its position
        ImageButton backButton = stage.getRoot().findActor("backButton");
        if (backButton != null) {
            backButton.setPosition(backButtonX, backButtonY);
        }
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
        stage.dispose();
        batch.dispose();
        switchSound.dispose(); // Dispose of the sound when the screen is disposed
    }

    public class CustomSlider extends Actor implements Disposable {

        private Texture backgroundTexture;
        private Texture knobTexture;

        private Rectangle knobBounds;
        private float value;
        private boolean controlsMusic;  // Flag to determine whether the slider controls music or sound effects

        private MainMenuScreen mainMenuScreen; // Reference to MainMenuScreen

        public CustomSlider(float x, float y, float width, float height, MainMenuScreen mainMenuScreen, boolean controlsMusic) {
            this.mainMenuScreen = mainMenuScreen; // Set the reference
            this.controlsMusic = controlsMusic;   // Set the flag

            // Load your textures
            backgroundTexture = new Texture("slide_bar_track.png");
            knobTexture = new Texture("slide_bar_notch.png");

            // Set the bounds for the knob
            knobBounds = new Rectangle(x, y, knobTexture.getWidth(), knobTexture.getHeight());

            // Set the size of the actor (slider)
            setSize(width, height);
            setPosition(x, y);

            // Update the knob position based on the initial value
            float knobX = getX() + (getWidth() - knobBounds.width) * value;
            knobBounds.x = Math.max(getX(), Math.min(getX() + getWidth() - knobBounds.width, knobX));
        }

        @Override
        public void draw(Batch batch, float parentAlpha) {
            // Draw the background
            batch.draw(backgroundTexture, getX(), getY(), getWidth(), getHeight());

            // Draw the knob based on the current value
            float knobX = getX() + (getWidth() - knobBounds.width) * value;

            // Lower the knob by 2 pixels
            float knobY = getY() - 5f;

            batch.draw(knobTexture, knobX, knobY, knobBounds.width, knobBounds.height);
        }

        @Override
        public void act(float delta) {
            super.act(delta);

            // Handle touch input
            if (Gdx.input.isTouched()) {
                Vector2 touchPos = new Vector2(Gdx.input.getX(), Gdx.input.getY());
                touchPos = stage.getViewport().unproject(touchPos);
                updateKnobPosition(touchPos.x, touchPos.y);
            }
        }

        public void setValue(float newValue) {
            // Ensure the value is between 0 and 1
            value = Math.max(0f, Math.min(1f, newValue));
        }
        public float getValue() {
            return value;
        }

        public boolean isTouched(Vector2 touchPos) {
            // Check if the knob is touched
            return knobBounds.contains(touchPos);
        }

        public void updateKnobPosition(float inputX, float inputY) {
            // Check if the input is within the bounds of the slider
            if (inputX >= getX() && inputX <= getX() + getWidth() &&
                    inputY >= getY() && inputY <= getY() + getHeight()) {
                float knobX = inputX - knobBounds.width / 2f;
                knobX = Math.max(getX(), Math.min(getX() + getWidth() - knobBounds.width, knobX));
                knobBounds.x = knobX;

                // Update the value based on the knob's position
                value = (knobBounds.x - getX()) / (getWidth() - knobBounds.width);
                setValue(value);

                // Update the corresponding volume in the mainMenuScreen
                if (controlsMusic) {
                    SoundManager.setBackgroundMusicVolume(customSlider.getValue());
                    preferences.putFloat("slider1Value", customSlider.getValue()).flush();

                } else {
                    SoundManager.setSoundEffectsVolume(customSlider2.getValue());
                    preferences.putFloat("slider2Value", customSlider2.getValue()).flush();

                }
            }
        }

        @Override
        public void dispose() {
            // Dispose of your textures when done
            backgroundTexture.dispose();
            knobTexture.dispose();
        }
    }

}

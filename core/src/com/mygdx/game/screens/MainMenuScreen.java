package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.MainGame;
import com.mygdx.game.soundeffectsmanager.SoundManager;

public class MainMenuScreen implements Screen, InputProcessor {

    private MainGame game;
    private OrthographicCamera camera;
    private Stage stage;
    private SpriteBatch batch;

    private Texture titleTexture;
    private ImageButton newGameButton;
    private ImageButton settingsButton;
    private ImageButton quitButton;
    private Table table;
    private int selectedIndex = 0; // Initialize with the index of the first option
    private Arrow arrow;



    public MainMenuScreen(MainGame game) {
        this.game = game;
    }

    private void handleEnterKey(int selectedIndex) {
        switch (selectedIndex) {
            case 0:
                Gdx.app.log("MainMenuScreen", "New Game option selected!");
                game.setScreen(new MapScreen(game,this)); // Change to the NewGameScreen
                break;
            case 1:
                Gdx.app.log("MainMenuScreen", "Settings option selected!");
                game.setScreen(new SettingsScreen(game, this)); // Pass the MainGame instance
                break;
            case 2:
                Gdx.app.log("MainMenuScreen", "Exit option selected!");
                Gdx.app.exit(); // Close the application
                break;
        }
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        stage = new Stage(new ScreenViewport(camera)); // Use ScreenViewport
        Gdx.input.setInputProcessor(stage);

        titleTexture = new Texture("game_title.png");

        batch = new SpriteBatch();

        SoundManager.playBackgroundMusic();
        SoundManager.setBackgroundMusicVolume(SettingsScreen.getSavedSlider1Value());

        // Create image buttons
        Texture newGameTexture = new Texture("new_game.png");
        Texture settingsTexture = new Texture("settings.png");
        Texture quitTexture = new Texture("exit.png");

        newGameButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(newGameTexture)));
        settingsButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(settingsTexture)));
        quitButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(quitTexture)));

        // Add image buttons to a table for layout
        table = new Table();
        table.setFillParent(true); // Makes the table the size of the stage
        table.padTop(250);

        // Add buttons to the table
        table.add(newGameButton).padBottom(50).row();
        table.add(settingsButton).padBottom(50).row();
        table.add(quitButton).padBottom(50).row();

        // Add the table to the stage
        stage.addActor(table);

        arrow = new Arrow("pointer.png", newGameButton, selectedIndex);
        Gdx.input.setInputProcessor(this); // Set input processor to this screen


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        Texture welcomeTexture = new Texture("background.png");
        batch.draw(welcomeTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Draw title image above the background
        float titleX = Gdx.graphics.getWidth() / 2f - titleTexture.getWidth() / 2f;
        float titleY = Gdx.graphics.getHeight() * 2 / 3f; // Adjust the factor as needed
        batch.draw(titleTexture, titleX, titleY);

        // Draw the arrow using the Arrow class
        arrow.draw(batch);

        batch.end();

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

        // Update the arrow position
        arrow.updatePosition(newGameButton.getX() - arrow.getWidth() - 10, newGameButton.getY(),
                Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), table);



        // Handle input in render method only if the MainMenuScreen is the active screen
        if (game.getScreen() == this && game.canProcessInput()) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
                selectedIndex = Math.max(selectedIndex - 1, 0); // Move one step up
            } else if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
                selectedIndex = Math.min(selectedIndex + 1, table.getCells().size - 1); // Move one step down
            } else if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
                handleEnterKey(selectedIndex);
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            SoundManager.playSwitchSound();

        } else if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            SoundManager.playSwitchSound();

        }
    }



    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);

        // Use the original screen height for calculating buttonHeight
        float originalHeight = stage.getViewport().getScreenHeight();

        // Increase the separation between title and buttons
        float titleButtonSeparation = 50f;

        // Calculate the position of the title image
        float titleX = width / 2 - titleTexture.getWidth() / 2;
        float titleY = originalHeight * 2 / 3f;

        // Recalculate the position of the buttons based on the new screen dimensions
        float buttonX = width / 2 - newGameButton.getWidth() / 2;
        float buttonY = titleY - titleTexture.getHeight() - titleButtonSeparation;

        newGameButton.setPosition(buttonX, buttonY);
        settingsButton.setPosition(buttonX, buttonY - newGameButton.getHeight() - titleButtonSeparation);
        quitButton.setPosition(buttonX, buttonY - 2 * (newGameButton.getHeight() + titleButtonSeparation));


    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null); // Clear input processor

    }

    @Override
    public void dispose() {
        stage.dispose();
        batch.dispose();
        arrow.dispose(); // Dispose arrow resources
    }

    @Override
    public boolean keyDown(int keycode) {
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            selectedIndex = Math.max(selectedIndex - 1, 0); // Move one step up
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            selectedIndex = Math.min(selectedIndex + 1, table.getCells().size - 1); // Move one step down
        } else if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            handleEnterKey(selectedIndex);
            SoundManager.playSwitchSound();
        }
        return true; // indicate that the input has been handled
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
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
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

    // Arrow class to handle arrow logic
    private class Arrow {
        private Texture texture;
        private float x;
        private float y;
        private float speed = 5f;
        private int selectedIndex;

        public Arrow(String texturePath, ImageButton referenceButton, int selectedIndex) {
            texture = new Texture(texturePath);
            x = referenceButton.getX() - texture.getWidth() - 10; // Adjust the offset as needed
            y = referenceButton.getY();
            this.selectedIndex = selectedIndex;
        }

        public void draw(SpriteBatch batch) {
            batch.draw(texture, x, y);
        }

        public void updatePosition(float newX, float newY, float screenWidth, float screenHeight, Table table) {
            // Allow navigation between buttons using up and down keys
            if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
                selectedIndex = Math.max(selectedIndex - 1, 0); // Move one step up
                SoundManager.playSwitchSound();
            } else if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
                selectedIndex = Math.min(selectedIndex + 1, table.getCells().size - 1); // Move one step down
                SoundManager.playSwitchSound();
            }

            // Get the selected button's Y position and height from the table
            Cell cell = table.getCells().get(selectedIndex);
            float buttonY = cell.getActor().getY();
            float buttonHeight = cell.getActor().getHeight();

            // Calculate the new position based on the selected button's Y position and height
            x = MathUtils.clamp(newX, 0, screenWidth - getWidth());
            y = MathUtils.clamp(buttonY + (buttonHeight - getHeight()) / 2f, 0, screenHeight - getHeight());
        }




        private float calculateY(float baseY, int index, Table table) {
            // Calculate the Y position based on the selected index
            float buttonY = baseY - index * (newGameButton.getHeight() + 10);

            // Adjust the Y position to be centered on the button
            buttonY += (table.getCell(newGameButton).getActor().getHeight() - getHeight()) / 2f;

            return buttonY;
        }

        public float getWidth() {
            return texture.getWidth();
        }

        public float getHeight() {
            return texture.getHeight();
        }

        public void dispose() {
            texture.dispose();
        }
    }
}


package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.MainGame;


public class PauseScreen implements Screen,InputProcessor {

    private MainGame game;
    private OrthographicCamera camera;
    private Stage stage;
    private SpriteBatch batch;
    private MainMenuScreen mainMenuScreen;
    private Texture titleTexture;
    private Sound switchSound; // Declare a Sound variable
    private ImageButton restartButton;
    private ImageButton resumeButton;

    private ImageButton mainMenuButton;

    private ImageButton settingsButton;
    private ImageButton quitButton;
    private Table table;
    private Arrow arrow;
    private int selectedIndex = 0; // Initialize with the index of the first option

    public PauseScreen(MainGame game) {
        this.game = game;
        switchSound = Gdx.audio.newSound(Gdx.files.internal("click_sound.mp3"));
    }


    private void handleEnterKey(int selectedIndex) {
        switch (selectedIndex) {
            case 0:
                Gdx.app.log("Pause", "Resume option selected!");
                break;
            case 1:
                Gdx.app.log("Pause", "Restart option selected!");
                break;
            case 2:
                Gdx.app.log("Pause", "Settings option selected!");
                game.setScreen(new SettingsScreen(game, this.mainMenuScreen));
                break;
            case 3:
                Gdx.app.log("Pause", "Main Menu option selected!");
                game.setScreen(new MainMenuScreen(game));
                break;

            case 4:
                Gdx.app.log("Pause", "Exit option selected!");
                Gdx.app.exit(); // Close the application
                break;
        }
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        stage = new Stage(new ScreenViewport(camera));
        Gdx.input.setInputProcessor(stage);

        titleTexture = new Texture("game_title.png");

        batch = new SpriteBatch();


        // Create image buttons
        Texture resumeTexture = new Texture("new_game.png");
        Texture restartTexture = new Texture("new_game.png");
        Texture mainMenuTexture = new Texture("new_game.png");
        Texture settingsTexture = new Texture("settings.png");
        Texture quitTexture = new Texture("exit.png");

        resumeButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(resumeTexture)));
        restartButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(restartTexture)));
        mainMenuButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(mainMenuTexture)));
        settingsButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(settingsTexture)));
        quitButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(quitTexture)));

        table = new Table();
        table.setFillParent(true);
        table.padTop(250);

        // Add buttons to the table
        table.add(resumeButton).padBottom(50).row();
        table.add(restartButton).padBottom(50).row();
        table.add(mainMenuButton).padBottom(50).row();
        table.add(settingsButton).padBottom(50).row();
        table.add(quitButton).padBottom(50).row();

        // Add the table to the stage
        stage.addActor(table);

        arrow = new Arrow("pointer.png", restartButton, selectedIndex);
        Gdx.input.setInputProcessor(this); // Set input processor to this screen


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        Texture welcomeTexture = new Texture("transparent_rectangle.png");
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
        arrow.updatePosition(restartButton.getX() - arrow.getWidth() - 10, restartButton.getY(),
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
            playSwitchSound();

        } else if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            playSwitchSound();

        }
    }

    private void playSwitchSound() {
        switchSound.play();
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
        float buttonX = width / 2 - restartButton.getWidth() / 2;
        float buttonY = titleY - titleTexture.getHeight() - titleButtonSeparation;

        restartButton.setPosition(buttonX, buttonY);
        settingsButton.setPosition(buttonX, buttonY - restartButton.getHeight() - titleButtonSeparation);
        quitButton.setPosition(buttonX, buttonY - 2 * (restartButton.getHeight() + titleButtonSeparation));


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
    public boolean keyDown(int keycode) {
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            selectedIndex = Math.max(selectedIndex - 1, 0); // Move one step up
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            selectedIndex = Math.min(selectedIndex + 1, table.getCells().size - 1); // Move one step down
        } else if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            handleEnterKey(selectedIndex);
            playSwitchSound();
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
                playSwitchSound();
            } else if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
                selectedIndex = Math.min(selectedIndex + 1, table.getCells().size - 1); // Move one step down
                playSwitchSound();
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
            float buttonY = baseY - index * (restartButton.getHeight() + 10);

            // Adjust the Y position to be centered on the button
            buttonY += (table.getCell(restartButton).getActor().getHeight() - getHeight()) / 2f;

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

    @Override
    public void dispose() {
    }
}


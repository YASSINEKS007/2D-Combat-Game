package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.MainGame;
import com.mygdx.game.entities.*;
import com.mygdx.game.entities.Characters.*;
import com.mygdx.game.helper.Constants;
import com.mygdx.game.entities.arenas.Arena;


public class GamePlayScreen implements Screen {



    Sprite mapSprite;
    SpriteBatch batch;
    OrthographicCamera camera;

    MainGame game;

    Fighter p1;
    Fighter p2;

    HealthBar p1HealthBar;
    HealthBar p2HealthBar;

    StaminaBar p1StaminaBar;
    StaminaBar p2StaminaBar;

    float worldWidth;
    float worldHeight;

    private boolean gamePaused;

    private Texture pauseTexture;


    // Selected characters indexes
    int playerOneIndex;
    int playerTwoIndex;

    private int mapIndex;
    private PauseScreen pauseScreen; // Add this variable



    public GamePlayScreen(MainGame game, int playerOneIndex, int playerTwoIndex, int mapIndex){

        this.playerOneIndex = playerOneIndex;
        this.playerTwoIndex = playerTwoIndex;
        pauseScreen = new PauseScreen(game ); // Initialize the PauseScreen


        this.mapIndex = mapIndex;

        this.game = game;
        gamePaused = false;


        // Loading Arena
        Arena arena = new Arena(mapIndex);
        mapSprite = arena.getMapSprite();

        worldWidth = arena.getMapSprite().getWidth();
        worldHeight = arena.getMapSprite().getHeight();


        camera = new OrthographicCamera(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        camera.position.set((camera.viewportWidth)/ 2f , camera.viewportHeight / 2f, 0);
        camera.update();

        batch = new SpriteBatch();

        p1 = new PlayerOne(200, 200, 1, arena, loadSelectedCharacter(playerOneIndex));
        p2 = new PlayerTwo(Constants.SCREEN_WIDTH - 200 - p1.getCharacter().getCharacterWidth(), 200, 2, arena, loadSelectedCharacter(playerTwoIndex));
        p1.setAdversary(p2);
        p2.setAdversary(p1);
        p1.setCamera(camera);
        p2.setCamera(camera);
        p1HealthBar = new HealthBar(p1);
        p2HealthBar = new HealthBar(p2);

        p1StaminaBar = new StaminaBar(p1);
        p2StaminaBar = new StaminaBar(p2);


        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(p1.getDoubleKeyTap());
        multiplexer.addProcessor(p2.getDoubleKeyTap());
        Gdx.input.setInputProcessor(multiplexer);

        arena.getArenaMusic().play();



        pauseTexture = new Texture(Gdx.files.internal("pauseMenu/paused_title.png"));
    }


    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        float distance = Math.abs(p1.getPlayerHitBox().x- (p2.getPlayerHitBox().x+p2.getCharacter().getCharacterWidth()));
        float distanceToNearestPlayer = Math.min(p1.getPlayerHitBox().x, p2.getPlayerHitBox().x+ p2.getCharacter().getCharacterWidth());

        camera.position.set(distanceToNearestPlayer + distance/2f, camera.viewportHeight / 2f,0);
        // camera.position.set(Constants.WORLD_WIDTH - (p1.getPlayerHitBox().x - (p2.getPlayerHitBox().x + Constants.PLAYER_WIDTH)) /2f,  camera.viewportHeight / 2f,0);
        // Limit camera movement
        if (camera.position.x < (camera.viewportWidth)/ 2f ) camera.position.set((camera.viewportWidth)/ 2f, camera.viewportHeight / 2f, 0) ;
        if (camera.position.x > worldWidth - (camera.viewportWidth)/ 2f)
            camera.position.set(worldWidth - (camera.viewportWidth)/ 2f, camera.viewportHeight / 2f, 0);

        camera.update();

        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        mapSprite.draw(batch);
        p2.draw(batch, camera);
        p1.draw(batch, camera);

        batch.end();

        p1HealthBar.draw(batch, camera);
        p2HealthBar.draw(batch, camera);

        p1StaminaBar.draw(batch);
        p2StaminaBar.draw(batch);

        // check if escape is pressed and pause the game accordingly

        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            gamePaused = !gamePaused;
        }
        if (gamePaused){
            game.setScreen(pauseScreen);
            System.out.println("Game is Paused!");
        }else{
            update();
        }

    }

    public void update(){


        p2.update();
        p1.update();


        p1HealthBar.update();
        p2HealthBar.update();

        p1StaminaBar.update();
        p2StaminaBar.update();
    }



    public PlayerCharacter loadSelectedCharacter(int selectedCharacterIndex){

        PlayerCharacter selectedCharacter = null;
        switch (selectedCharacterIndex){
            case 0:
                selectedCharacter = new YamiNoRonin();
                break;
            case 1:
                selectedCharacter = new KingArthur();
                break;
            case 2:
                selectedCharacter = new Drogo();
                break;
            case 3:
                selectedCharacter = new SirBrienne();
                break;
        }
        return selectedCharacter;
    }

    @Override
    public void resize(int width, int height) {

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

    }
}

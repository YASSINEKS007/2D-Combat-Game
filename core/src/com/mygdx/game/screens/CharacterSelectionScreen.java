package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.MainGame;
import com.mygdx.game.soundeffectsmanager.SoundManager;
import input_processors.CharacterSelectionInputProcessor;

import java.util.HashMap;
import java.util.Map;

public class CharacterSelectionScreen implements Screen {
    private Sprite backGround;

    private final Texture p1Indicator;
    private final Texture p2Indicator;

    private TextureRegion p1Texture;
    private TextureRegion p2Texture;

    private Texture p1CharacterStats;
    private Texture p2CharacterStats;

    private Texture p1NameTag;
    private Texture p2NameTag;

    private Animation<TextureRegion> yamiIdleAnimation;
    private Animation<TextureRegion> arthurIdleAnimation;
    private Animation<TextureRegion> brienneIdleAnimation;
    private Animation<TextureRegion> drogoIdleAnimation;


    private int p1CharacterIndex;
    private int p2CharacterIndex;

    private Map<Integer, Boolean> p1SelectedCharactersMap;
    private Map<Integer, Boolean> p2SelectedCharactersMap;
    private Map<Integer, Texture> charactersStatsMap;
    private Map<Integer, Texture> charactersNameTagsMap;
    private final Map<Integer, Integer> indicatorCoordinatesMap;
    private final Map<Integer, String> selectedCharactersFileNamesMap;
    private Map<Integer, Animation<TextureRegion>> charactersAnimationsMap;



    private Sound navigationSFX;
    private Sound selectSFX;
    private Sound errorSFX;

    MainGame game;

    Batch batch;
    private float stateTime;
    private  int mapIndex;

    public CharacterSelectionScreen(MainGame game, int mapIndex) {
        this.game = game;
        this.mapIndex = mapIndex;


        backGround = new Sprite(new Texture(Gdx.files.internal("character_selection_sprites/character_selection_background.png")));
        batch = new SpriteBatch();
        p1CharacterIndex = 0;
        p2CharacterIndex = 1;

        Gdx.input.setInputProcessor(new CharacterSelectionInputProcessor(this));

        p1Indicator  = new Texture(Gdx.files.internal("character_selection_sprites/character_selection_p1_indicator.png"));
        p2Indicator  = new Texture(Gdx.files.internal("character_selection_sprites/character_selection_p2_indicator.png"));


        // Setting up Hash Maps
            // Initialising coordinates
        indicatorCoordinatesMap = new HashMap<>();
        indicatorCoordinatesMap.put(0,400);
        indicatorCoordinatesMap.put(1,525);
        indicatorCoordinatesMap.put(2,650);
        indicatorCoordinatesMap.put(3,775);

            // Initialising selected Characters
        p1SelectedCharactersMap = new HashMap<>();
        p1SelectedCharactersMap.put(0,false);
        p1SelectedCharactersMap.put(1,false);
        p1SelectedCharactersMap.put(2,false);
        p1SelectedCharactersMap.put(3,false);

        p2SelectedCharactersMap = new HashMap<>();
        p2SelectedCharactersMap.put(0,false);
        p2SelectedCharactersMap.put(1,false);
        p2SelectedCharactersMap.put(2,false);
        p2SelectedCharactersMap.put(3,false);

            // Initialising file names
        selectedCharactersFileNamesMap = new HashMap<>();
        selectedCharactersFileNamesMap.put(0,"yami_selected_by_");
        selectedCharactersFileNamesMap.put(1,"arthur_selected_by_");
        selectedCharactersFileNamesMap.put(2,"drogo_selected_by_");
        selectedCharactersFileNamesMap.put(3,"brienne_selected_by_");

            // loading animations
        charactersAnimationsMap = loadAnimations();

            // loading Stats
        charactersStatsMap  = loadStats();

            // loading Name Tags
        charactersNameTagsMap  = loadNameTags();


        // loading Sound effects

        navigationSFX = Gdx.audio.newSound(Gdx.files.internal("sound_effects/menu_navigation.mp3"));
        selectSFX = Gdx.audio.newSound(Gdx.files.internal("sound_effects/selection_confirmed.mp3"));
        errorSFX = Gdx.audio.newSound(Gdx.files.internal("sound_effects/error.mp3"));

        stateTime = 0f;






    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0, 0, 0, 1);




        batch.begin();

        batch.setColor(1,1,1,1);
        backGround.draw(batch);

        // locking selected characters
        if(p1SelectedCharactersMap.get(p1CharacterIndex)){
            batch.draw(new Texture(Gdx.files.internal("character_selection_sprites/"+selectedCharactersFileNamesMap.get(p1CharacterIndex)+"p1.png")),indicatorCoordinatesMap.get(p1CharacterIndex),115);
        }

        if(p2SelectedCharactersMap.get(p2CharacterIndex)){
            batch.draw(new Texture(Gdx.files.internal("character_selection_sprites/"+selectedCharactersFileNamesMap.get(p2CharacterIndex)+"p2.png")),indicatorCoordinatesMap.get(p2CharacterIndex),115);
        }

        // drawing selection cursor
        batch.draw(p1Indicator, indicatorCoordinatesMap.get(p1CharacterIndex), 115);
        batch.draw(p2Indicator, indicatorCoordinatesMap.get(p2CharacterIndex), 115);

        // drawing  character stats
        p1CharacterStats = charactersStatsMap.get(p1CharacterIndex);
        batch.draw(p1CharacterStats, 70, 406);

        p2CharacterStats = charactersStatsMap.get(p2CharacterIndex);
        batch.draw(p2CharacterStats, 1260-70, 406, -p2CharacterStats.getWidth(),p2CharacterStats.getHeight());



        // drawing character previews
        stateTime += Gdx.graphics.getDeltaTime();
        p1Texture  = charactersAnimationsMap.get(p1CharacterIndex).getKeyFrame(stateTime, true);
        batch.draw(p1Texture, 200- (float) p1Texture.getRegionWidth() /2,80);

        p2Texture  = charactersAnimationsMap.get(p2CharacterIndex).getKeyFrame(stateTime, true);
        batch.draw(p2Texture, 1280-200 - (float) p2Texture.getRegionWidth() /2 + p2Texture.getRegionWidth(),80,-p2Texture.getRegionWidth(),p2Texture.getRegionHeight());

        // Drawing character reflections
        batch.setColor(1,1,1,0.6f);
        p1Texture  = charactersAnimationsMap.get(p1CharacterIndex).getKeyFrame(stateTime, true);  batch.draw(p1Texture, 200- (float) p1Texture.getRegionWidth() /2,80, p1Texture.getRegionWidth(), -p2Texture.getRegionHeight());

        batch.draw(p2Texture, 1280-200 - (float) p2Texture.getRegionWidth() /2 + p2Texture.getRegionWidth(),80,-p2Texture.getRegionWidth(),-p2Texture.getRegionHeight());


        // drawing Character name tags
        batch.setColor(1,1,1,1);
        p1NameTag = charactersNameTagsMap.get(p1CharacterIndex);
        batch.draw(p1NameTag,200- (float) p1NameTag.getWidth() /2, 15);

        p2NameTag = charactersNameTagsMap.get(p2CharacterIndex);
        batch.draw(p2NameTag,1280-200- (float) p2NameTag.getWidth() /2, 15);


        batch.end();


        // Switch to GameplayScreen once characters are chosen

        if(p1SelectedCharactersMap.get(p1CharacterIndex) && p2SelectedCharactersMap.get(p2CharacterIndex)){
            SoundManager.stopBackgroundMusic();
            game.setScreen(new GamePlayScreen(game, p1CharacterIndex, p2CharacterIndex, mapIndex));
        }



    }




    public Map<Integer, Animation<TextureRegion>> loadAnimations(){
        Map<Integer, Animation<TextureRegion>> charactersAnimationsMap = new HashMap<>();


        // Loading Yami's Animation
        Texture idleSheet = new Texture(Gdx.files.internal("sprites/mizu_sprites/idle.png"));
        TextureRegion[][] tmpIdleSheet = TextureRegion.split(idleSheet, idleSheet.getWidth() / 8, idleSheet.getHeight());
        TextureRegion[] idleFrames = new TextureRegion[8];
        int index = 0;
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 8; j++) {
                idleFrames[index++] = tmpIdleSheet[i][j];
            }
        }
        yamiIdleAnimation = new Animation<>(0.1f, idleFrames);
        charactersAnimationsMap.put(0, yamiIdleAnimation);

        // Loading Arthur's Animation
        idleSheet = new Texture(Gdx.files.internal("sprites/arthur_sprites/idle.png"));
        tmpIdleSheet = TextureRegion.split(idleSheet, idleSheet.getWidth() / 8, idleSheet.getHeight());
        idleFrames = new TextureRegion[8];
        index = 0;
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 8; j++) {
                idleFrames[index++] = tmpIdleSheet[i][j];
            }
        }
        arthurIdleAnimation = new Animation<>(0.15f, idleFrames);
        charactersAnimationsMap.put(1, arthurIdleAnimation);

        // Loading Drogo's Animation
        idleSheet = new Texture(Gdx.files.internal("sprites/drogo_sprites/idle.png"));
        tmpIdleSheet = TextureRegion.split(idleSheet, idleSheet.getWidth() / 10, idleSheet.getHeight());
        idleFrames = new TextureRegion[10];
        index = 0;
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 10; j++) {
                idleFrames[index++] = tmpIdleSheet[i][j];
            }
        }
        drogoIdleAnimation = new Animation<>(0.09f, idleFrames);
        charactersAnimationsMap.put(2, drogoIdleAnimation);

        // Loading Brienne's Animation
        idleSheet = new Texture(Gdx.files.internal("sprites/brienne_sprites/idle.png"));
        tmpIdleSheet = TextureRegion.split(idleSheet, idleSheet.getWidth() / 11, idleSheet.getHeight());
        idleFrames = new TextureRegion[11];
        index = 0;
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 11; j++) {
                idleFrames[index++] = tmpIdleSheet[i][j];
            }
        }
        brienneIdleAnimation = new Animation<>(0.1f, idleFrames);
        charactersAnimationsMap.put(3, brienneIdleAnimation);


        return charactersAnimationsMap;
    }

    public Map<Integer, Texture> loadStats(){
        Map<Integer, Texture> charactersStatsMap  = new HashMap<>();

        charactersStatsMap.put(0, new Texture(Gdx.files.internal("character_selection_sprites/yami_stats.png")));
        charactersStatsMap.put(1, new Texture(Gdx.files.internal("character_selection_sprites/arthur_stats.png")));
        charactersStatsMap.put(2, new Texture(Gdx.files.internal("character_selection_sprites/drogo_stats.png")));
        charactersStatsMap.put(3, new Texture(Gdx.files.internal("character_selection_sprites/brienne_stats.png")));

        return charactersStatsMap;
    }

    public Map<Integer, Texture> loadNameTags(){
        Map<Integer, Texture> charactersNameTags  = new HashMap<>();

        charactersNameTags.put(0, new Texture(Gdx.files.internal("character_selection_sprites/yami_name_tag.png")));
        charactersNameTags.put(1, new Texture(Gdx.files.internal("character_selection_sprites/arthur_name_tag.png")));
        charactersNameTags.put(2, new Texture(Gdx.files.internal("character_selection_sprites/drogo_name_tag.png")));
        charactersNameTags.put(3, new Texture(Gdx.files.internal("character_selection_sprites/brienne_name_tag.png")));

        return charactersNameTags;

    }

    @Override
    public void resize(int i, int i1) {

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

    // Getters
    public int getP1CharacterIndex() {
        return p1CharacterIndex;
    }

    public int getP2CharacterIndex() {
        return p2CharacterIndex;
    }

    public Map<Integer, Boolean> getP1SelectedCharactersMap() {
        return p1SelectedCharactersMap;
    }

    public Map<Integer, Boolean> getP2SelectedCharactersMap() {
        return p2SelectedCharactersMap;
    }

    public Sound getNavigationSFX() {
        return navigationSFX;
    }

    public Sound getSelectSFX() {
        return selectSFX;
    }

    public Sound getErrorSFX() {
        return errorSFX;
    }

    // Setters
    public void setP1CharacterIndex(int p1CharacterIndex) {
        this.p1CharacterIndex = p1CharacterIndex;
    }

    public void setP2CharacterIndex(int p2CharacterIndex) {
        this.p2CharacterIndex = p2CharacterIndex;
    }


}

package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.entities.arenas.Arena;
import com.mygdx.game.helper.Constants;
import input_processors.DoubleKeyTapInputProcessor;
import com.mygdx.game.entities.Characters.PlayerCharacter;
import com.mygdx.game.entities.player_state.FighterState;
import com.mygdx.game.entities.player_state.IdleState;

import java.awt.*;


public abstract class Fighter implements Entity {


    private FighterState state;
    private OrthographicCamera camera;


    private int yVelocity;
    private int xVelocity;




    private boolean isFlipped;
    private TextureRegion playerTexture;
    private Rectangle playerHitBox;

    private Rectangle attackHitBox;

    private int playerType;

    private float playerHealth;
    private float playerStamina;
    private Fighter adversary;

    private int attackHitBoxOffset;

    BitmapFont font;

    // Animation attributes
    Animation<TextureRegion> idleAnimation;
    Animation<TextureRegion> walkAnimation;
    Animation<TextureRegion> ascendingAnimation;
    Animation<TextureRegion> descendingAnimation;
    Animation<TextureRegion> attack1Animation;
    Animation<TextureRegion> takingDamageAnimation;
    Animation<TextureRegion> deathAnimation;
    Animation<TextureRegion> dashAnimation;
    Animation<TextureRegion> stepBackAnimation;

    // SFX
    private Sound dashSFX;
    private Sound takeDamageSFX;
    private Sound attackSFX;
    private Sound runningSFX;
    private Sound stepBackSFX;





    private float timeSinceLastKeyPressed;

    DoubleKeyTapInputProcessor doubleKeyTap;

    private Arena arena;
    private PlayerCharacter character;

    // Debugging stuff

    Texture characterHitBoxTexture = new Texture(Gdx.files.internal("sprites/character_hit_box.png"));
    Texture attackHitBoxTexture = new Texture(Gdx.files.internal("sprites/attack_hit_box.png"));


    public Fighter() {
    }

    public Fighter(int x, int y, int playerType, Arena arena, PlayerCharacter character) {

        state = new IdleState();
        this.character = character;
        this.playerType = playerType;
        this.arena = arena;
        playerHitBox = new Rectangle();
        playerHitBox.x = x;
        playerHitBox.y = y;
        yVelocity = 0;
        xVelocity = 0;
        playerHitBox.height = character.getCharacterHeight();
        playerHitBox.width = character.getCharacterWidth();
        isFlipped = false;




        attackHitBox = new Rectangle();


        attackHitBox.height = character.getAttackHitBoxHeight();
        attackHitBox.width = character.getAttackHitBoxWidth();

        attackHitBoxOffset = character.getAttackHitBoxOffset();
        attackHitBox.x = playerHitBox.x + attackHitBoxOffset;
        attackHitBox.y = playerHitBox.y;

        // Initializing player status

        playerHealth = 100;
        playerStamina = 100;

        font = new BitmapFont();



        idleAnimation = character.getIdleAnimation();
        walkAnimation = character.getWalkAnimation();
        ascendingAnimation = character.getAscendingAnimation();
        descendingAnimation = character.getDescendingAnimation();
        attack1Animation = character.getAttack1Animation();
        takingDamageAnimation = character.getTakingDamageAnimation();
        deathAnimation = character.getDeathAnimation();
        dashAnimation = character.getDashAnimation();
        stepBackAnimation = character.getStepBackAnimation();







        // Sound Effects

        dashSFX = Gdx.audio.newSound(Gdx.files.internal("sound_effects/Dash.mp3"));
        takeDamageSFX = Gdx.audio.newSound(Gdx.files.internal("sound_effects/Take Damage.mp3"));
        attackSFX = Gdx.audio.newSound(Gdx.files.internal("sound_effects/Attack.mp3"));
        runningSFX = Gdx.audio.newSound(Gdx.files.internal("sound_effects/Running.mp3"));
        stepBackSFX = Gdx.audio.newSound(Gdx.files.internal("sound_effects/Step Back.mp3"));
        // State times
        this.playerTexture = idleAnimation.getKeyFrame(0f,true);





        // Input processor
        timeSinceLastKeyPressed = 0f;
        doubleKeyTap = new DoubleKeyTapInputProcessor(this);


    }

    public void draw(SpriteBatch batch, OrthographicCamera camera) {
        // Display player
        //batch.draw(playerTexture, isFlipped? playerHitBox.x-character.getCharacterHitBoxOffset() + playerTexture.getRegionWidth() :playerHitBox.x-character.getCharacterHitBoxOffset(), playerHitBox.y, (isFlipped? -1:1)*playerTexture.getRegionWidth(), playerTexture.getRegionHeight());
        batch.draw(playerTexture, isFlipped? playerHitBox.x +character.getCharacterWidth()+ character.getCharacterHitBoxOffset():playerHitBox.x-character.getCharacterHitBoxOffset(), playerHitBox.y, (isFlipped? -1:1)*playerTexture.getRegionWidth(), playerTexture.getRegionHeight());
        //batch.draw(characterHitBoxTexture, playerHitBox.x, playerHitBox.y, character.getCharacterWidth(), playerTexture.getRegionHeight());

        //batch.draw(attackHitBoxTexture, attackHitBox.x, attackHitBox.y, character.getAttackHitBoxWidth(), playerTexture.getRegionHeight());
    }

    public void update() {
        handleInput();
        move();
        limitPlayer();
        increaseStamina();
        state.update(this);



        // testing double key press
        timeSinceLastKeyPressed += Gdx.graphics.getDeltaTime();
    }

    private void handleInput(){
        state.handleInput(this);
    }



    private void move() {
        playerHitBox.y += (int) (yVelocity * Gdx.graphics.getDeltaTime());
        playerHitBox.x += (int)(xVelocity* Gdx.graphics.getDeltaTime());


        // updating the attack hit box coordinates
        //attackHitBox.x = playerHitBox.x + attackHitBoxOffset;
        attackHitBox.x = playerHitBox.x +attackHitBoxOffset;
        attackHitBox.y = playerHitBox.y;
    }

    private void limitPlayer(){

        // Make sure player stays within screen bounds
        if (playerHitBox.x < 0) playerHitBox.x = 0;
        if (playerHitBox.x > arena.getMapSprite().getRegionWidth() - character.getCharacterWidth())
            playerHitBox.x = arena.getMapSprite().getRegionWidth() - character.getCharacterWidth();
        if (playerHitBox.y <= arena.getGroundLevel()) {
            yVelocity = 0;
            playerHitBox.y = arena.getGroundLevel();
        }else {
            // add gravity
            yVelocity -= 40;
        }

        // prevent players from going beyond the camera's view
        if (playerHitBox.x < camera.position.x - Constants.SCREEN_WIDTH/2f) playerHitBox.x = (int) (camera.position.x - Constants.SCREEN_WIDTH/2f);
        if (playerHitBox.x + character.getCharacterWidth() > camera.position.x + Constants.SCREEN_WIDTH/2f) playerHitBox.x = (int) (camera.position.x + Constants.SCREEN_WIDTH/2f) - character.getCharacterWidth();
    }







    public void takeDamage(){
        if (playerHealth- character.getDamageTakenPerHit()* adversary.getCharacter().getDamageDealtPerHit() >0){
        playerHealth -= character.getDamageTakenPerHit()* adversary.getCharacter().getDamageDealtPerHit();
        }else {
            playerHealth = 0;
        }
    }

    public abstract void flipHorizontally();

    public void increaseStamina(){
        if(playerStamina<100){
            playerStamina+=0.3f;
        }
    }





    public Rectangle getAttackHitBox() {
        return attackHitBox;
    }

    public void setState(FighterState state) {
        this.state = state;
    }

    public void setPlayerTexture(TextureRegion playerTexture) {
        this.playerTexture = playerTexture;
    }

    public void setAdversary(Fighter adversary) {
        this.adversary = adversary;
    }

    public float getPlayerHealth() {
        return playerHealth;
    }

    public void setFlipped(boolean flipped) {
        isFlipped = flipped;
    }

    public boolean isFlipped() {
        return isFlipped;
    }




    public Rectangle getPlayerHitBox() {
        return playerHitBox;
    }

    public Fighter getAdversary() {
        return adversary;
    }

    public void setAttackHitBoxOffset(int attackHitBoxOffset) {
        this.attackHitBoxOffset = attackHitBoxOffset;
    }

    public int getPlayerType() {
        return playerType;
    }



    public Animation<TextureRegion> getIdleAnimation() {
        return idleAnimation;
    }

    public Animation<TextureRegion> getWalkAnimation() {
        return walkAnimation;
    }

    public Animation<TextureRegion> getAscendingAnimation() {
        return ascendingAnimation;
    }

    public Animation<TextureRegion> getDescendingAnimation() {
        return descendingAnimation;
    }

    public Animation<TextureRegion> getAttack1Animation() {
        return attack1Animation;
    }

    public float getPlayerStamina() {
        return playerStamina;
    }

    public Animation<TextureRegion> getTakingDamageAnimation() {
        return takingDamageAnimation;
    }

    public Animation<TextureRegion> getDeathAnimation() {
        return deathAnimation;
    }

    public void setyVelocity(int yVelocity) {
        this.yVelocity = yVelocity;
    }

    public float getyVelocity() {
        return yVelocity;
    }

    public int getxVelocity() {
        return xVelocity;
    }

    public void setxVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    public void setPlayerHealth(float playerHealth) {
        this.playerHealth = playerHealth;
    }

    public void setCamera(OrthographicCamera camera) {
        this.camera = camera;
    }

    public float getTimeSinceLastKeyPressed() {
        return timeSinceLastKeyPressed;
    }

    public void setTimeSinceLastKeyPressed(float timeSinceLastKeyPressed) {
        this.timeSinceLastKeyPressed = timeSinceLastKeyPressed;
    }


    public DoubleKeyTapInputProcessor getDoubleKeyTap() {
        return doubleKeyTap;
    }

    public Animation<TextureRegion> getDashAnimation() {
        return dashAnimation;
    }

    public void setPlayerStamina(float playerStamina) {
        this.playerStamina = playerStamina;
    }

    public Animation<TextureRegion> getStepBackAnimation() {
        return stepBackAnimation;
    }

    public Sound getDashSFX() {
        return dashSFX;
    }

    public Sound getTakeDamageSFX() {
        return takeDamageSFX;
    }

    public Sound getAttackSFX() {
        return attackSFX;
    }

    public Sound getRunningSFX() {
        return runningSFX;
    }

    public Sound getStepBackSFX() {
        return stepBackSFX;
    }

    public PlayerCharacter getCharacter() {
        return character;
    }

    public TextureRegion getPlayerTexture() {
        return playerTexture;
    }
}

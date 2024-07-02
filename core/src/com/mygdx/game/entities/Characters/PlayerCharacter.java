package com.mygdx.game.entities.Characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PlayerCharacter {

    private int characterXVelocity;
    private int characterYVelocity;
    private int damageTakenPerHit;
    private float damageDealtPerHit;
    private int stepBackStaminaCost;
    private int dashStaminaCost;
    private int jumpStaminaCost;
    private int attackStaminaCost;
    private int characterHeight;
    private int characterWidth;
    private int characterHitBoxOffset;
    private int attackHitBoxHeight;
    private int attackHitBoxWidth;
    private int attackHitBoxOffset;

    // Animations
    private Animation<TextureRegion> idleAnimation;
    private Animation<TextureRegion> walkAnimation;
    private Animation<TextureRegion> ascendingAnimation;
    private Animation<TextureRegion> descendingAnimation;
    private Animation<TextureRegion> attack1Animation;
    private Animation<TextureRegion> takingDamageAnimation;
    private Animation<TextureRegion> deathAnimation;
    private Animation<TextureRegion> dashAnimation;
    private Animation<TextureRegion> stepBackAnimation;

    // Health Bar Texture

    private Texture healthBarTexture;


    public PlayerCharacter() {
    }

    public PlayerCharacter(int characterXVelocity, int characterYVelocity, int damageTakenPerHit, float damageDealtPerHit, int stepBackStaminaCost, int dashStaminaCost, int jumpStaminaCost, int attackStaminaCost, int characterHeight, int characterWidth, int characterHitBoxOffset, int attackHitBoxHeight, int attackHitBoxWidth, int attackHitBoxOffset) {
        this.characterXVelocity = characterXVelocity;
        this.characterYVelocity = characterYVelocity;
        this.damageTakenPerHit = damageTakenPerHit;
        this.damageDealtPerHit = damageDealtPerHit;
        this.stepBackStaminaCost = stepBackStaminaCost;
        this.dashStaminaCost = dashStaminaCost;
        this.jumpStaminaCost = jumpStaminaCost;
        this.attackStaminaCost = attackStaminaCost;
        this.characterHeight = characterHeight;
        this.characterWidth = characterWidth;
        this.characterHitBoxOffset = characterHitBoxOffset;
        this.attackHitBoxHeight = attackHitBoxHeight;
        this.attackHitBoxWidth = attackHitBoxWidth;
        this.attackHitBoxOffset = attackHitBoxOffset;
    }

    public PlayerCharacter(int characterXVelocity, int characterHeight, int characterWidth, int characterHitBoxOffset, int attackHitBoxHeight, int attackHitBoxWidth, int attackHitBoxOffset) {

        this.characterXVelocity = characterXVelocity;
        this.characterHeight = characterHeight;
        this.characterWidth = characterWidth;
        this.attackHitBoxHeight = attackHitBoxHeight;
        this.attackHitBoxWidth = attackHitBoxWidth;
        this.attackHitBoxOffset = attackHitBoxOffset;
        this.characterHitBoxOffset = characterHitBoxOffset;
    }



    public Animation<TextureRegion> loadAnimation(String path, int numberOfFrames, float frameDuration){
        Animation<TextureRegion> animation;
        Texture spriteSheet = new Texture(Gdx.files.internal(path));
        TextureRegion[][] tmpTR = TextureRegion.split(spriteSheet, spriteSheet.getWidth() / numberOfFrames, spriteSheet.getHeight());
        TextureRegion[] frames  = new TextureRegion[numberOfFrames];

        int index = 0;
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < numberOfFrames; j++) {
                frames[index++] = tmpTR[i][j];
            }
        }

        animation = new Animation<>(frameDuration, frames);
        return animation;
    }



    // Getters

    public int getCharacterXVelocity() {
        return characterXVelocity;
    }

    public int getCharacterHeight() {
        return characterHeight;
    }

    public int getCharacterWidth() {
        return characterWidth;
    }

    public int getAttackHitBoxHeight() {
        return attackHitBoxHeight;
    }

    public int getAttackHitBoxWidth() {
        return attackHitBoxWidth;
    }

    public int getCharacterHitBoxOffset() {
        return characterHitBoxOffset;
    }

    public int getAttackHitBoxOffset() {
        return attackHitBoxOffset;
    }

    public int getCharacterYVelocity() {
        return characterYVelocity;
    }

    public int getDamageTakenPerHit() {
        return damageTakenPerHit;
    }

    public float getDamageDealtPerHit() {
        return damageDealtPerHit;
    }

    public int getStepBackStaminaCost() {
        return stepBackStaminaCost;
    }

    public int getDashStaminaCost() {
        return dashStaminaCost;
    }

    public int getJumpStaminaCost() {
        return jumpStaminaCost;
    }

    public int getAttackStaminaCost() {
        return attackStaminaCost;
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

    public Animation<TextureRegion> getTakingDamageAnimation() {
        return takingDamageAnimation;
    }

    public Animation<TextureRegion> getDeathAnimation() {
        return deathAnimation;
    }

    public Animation<TextureRegion> getDashAnimation() {
        return dashAnimation;
    }

    public Animation<TextureRegion> getStepBackAnimation() {
        return stepBackAnimation;
    }

    public Texture getHealthBarTexture() {
        return healthBarTexture;
    }

    // Setters



    public void setCharacterXVelocity(int characterXVelocity) {
        this.characterXVelocity = characterXVelocity;
    }

    public void setCharacterHeight(int characterHeight) {
        this.characterHeight = characterHeight;
    }

    public void setCharacterWidth(int characterWidth) {
        this.characterWidth = characterWidth;
    }

    public void setAttackHitBoxHeight(int attackHitBoxHeight) {
        this.attackHitBoxHeight = attackHitBoxHeight;
    }

    public void setAttackHitBoxWidth(int attackHitBoxWidth) {
        this.attackHitBoxWidth = attackHitBoxWidth;
    }

    public void setCharacterHitBoxOffset(int characterHitBoxOffset) {
        this.characterHitBoxOffset = characterHitBoxOffset;
    }

    public void setAttackHitBoxOffset(int attackHitBoxOffset) {
        this.attackHitBoxOffset = attackHitBoxOffset;
    }

    public void setIdleAnimation(Animation<TextureRegion> idleAnimation) {
        this.idleAnimation = idleAnimation;
    }

    public void setWalkAnimation(Animation<TextureRegion> walkAnimation) {
        this.walkAnimation = walkAnimation;
    }

    public void setAscendingAnimation(Animation<TextureRegion> ascendingAnimation) {
        this.ascendingAnimation = ascendingAnimation;
    }

    public void setDescendingAnimation(Animation<TextureRegion> descendingAnimation) {
        this.descendingAnimation = descendingAnimation;
    }

    public void setAttack1Animation(Animation<TextureRegion> attack1Animation) {
        this.attack1Animation = attack1Animation;
    }

    public void setTakingDamageAnimation(Animation<TextureRegion> takingDamageAnimation) {
        this.takingDamageAnimation = takingDamageAnimation;
    }

    public void setDeathAnimation(Animation<TextureRegion> deathAnimation) {
        this.deathAnimation = deathAnimation;
    }

    public void setDashAnimation(Animation<TextureRegion> dashAnimation) {
        this.dashAnimation = dashAnimation;
    }

    public void setStepBackAnimation(Animation<TextureRegion> stepBackAnimation) {
        this.stepBackAnimation = stepBackAnimation;
    }

    public void setHealthBarTexture(Texture healthBarTexture) {
        this.healthBarTexture = healthBarTexture;
    }
}

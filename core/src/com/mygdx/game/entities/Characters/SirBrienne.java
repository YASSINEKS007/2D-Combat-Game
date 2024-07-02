package com.mygdx.game.entities.Characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SirBrienne extends PlayerCharacter {
    private static final int xVelocity = 500;
    private static final int yVelocity = 1100;
    private static final int damageTakenPerHit = 10;
    private static final float damageDealtPerHit = 1.3f;
    private static final int stepBackStaminaCost = 30;
    private static final int dashStaminaCost = 35;
    private  static final int jumpStaminaCost = 20;

    private static final int attackStaminaCost = 20;
    private static final int characterHeight = 225;
    private static final int characterWidth = 120;
    private static final int characterHitBoxOffset = 430;
    private static final int attackHitBoxHeight = 315;
    private static final int attackHitBoxWidth = 165;
    private static final int attackHitBoxOffset = 50;


    public SirBrienne() {
        super(xVelocity, yVelocity, damageTakenPerHit, damageDealtPerHit, stepBackStaminaCost, dashStaminaCost, jumpStaminaCost, attackStaminaCost, characterHeight, characterWidth,characterHitBoxOffset,  attackHitBoxHeight, attackHitBoxWidth, attackHitBoxOffset);
        // Loading Animations

        Animation<TextureRegion> idleAnimation = super.loadAnimation("sprites/brienne_sprites/Idle.png", 11, 0.1f);
        Animation<TextureRegion> walkAnimation = super.loadAnimation("sprites/brienne_sprites/Run.png", 8, 0.12f);
        Animation<TextureRegion> ascendingAnimation = super.loadAnimation("sprites/brienne_sprites/Jump.png", 3, 0.13f);
        Animation<TextureRegion> descendingAnimation = super.loadAnimation("sprites/brienne_sprites/Fall.png", 3, 0.13f);
        Animation<TextureRegion> attack1Animation = super.loadAnimation("sprites/brienne_sprites/Attack1.png", 7, 0.09f);
        Animation<TextureRegion> takingDamageAnimation = super.loadAnimation("sprites/brienne_sprites/Take Hit - white silhouette.png", 4, 0.07f);
        Animation<TextureRegion> deathAnimation = super.loadAnimation("sprites/brienne_sprites/Death.png", 11, 0.18f);
        Animation<TextureRegion> dashAnimation = super.loadAnimation("sprites/brienne_sprites/Dash.png", 2, 0.02f);
        Animation<TextureRegion> stepBackAnimation = super.loadAnimation("sprites/brienne_sprites/Step Back.png", 2, 0.05f);




        super.setIdleAnimation(idleAnimation);
        super.setWalkAnimation(walkAnimation);
        super.setAscendingAnimation(ascendingAnimation);
        super.setDescendingAnimation(descendingAnimation);
        super.setAttack1Animation(attack1Animation);
        super.setTakingDamageAnimation(takingDamageAnimation);
        super.setDeathAnimation(deathAnimation);
        super.setDashAnimation(dashAnimation);
        super.setStepBackAnimation(stepBackAnimation);



        Texture healthBarTexture = new Texture(Gdx.files.internal("health_bars/brienne_health_bar.png"));
        super.setHealthBarTexture(healthBarTexture);
    }
}

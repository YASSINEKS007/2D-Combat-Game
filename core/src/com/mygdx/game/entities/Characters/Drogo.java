package com.mygdx.game.entities.Characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Drogo extends PlayerCharacter {

    private static final int xVelocity = 800;
    private static final int yVelocity = 1250;
    private static final int damageTakenPerHit = 25;
    private static final float damageDealtPerHit = 1.2f;
    private static final int stepBackStaminaCost = 25;
    private static final int dashStaminaCost = 20;
    private  static final int jumpStaminaCost = 15;

    private static final int attackStaminaCost = 15;
    private static final int characterHeight = 250;
    private static final int characterWidth = 130;
    private static final int characterHitBoxOffset = 270;
    private static final int attackHitBoxHeight = 350;
    private static final int attackHitBoxWidth = 220;
    private static final int attackHitBoxOffset = 110;

    public Drogo() {
        super(xVelocity, yVelocity, damageTakenPerHit, damageDealtPerHit, stepBackStaminaCost, dashStaminaCost, jumpStaminaCost, attackStaminaCost, characterHeight, characterWidth,characterHitBoxOffset,  attackHitBoxHeight, attackHitBoxWidth, attackHitBoxOffset);
        // Loading Animations

        Animation<TextureRegion> idleAnimation = super.loadAnimation("sprites/drogo_sprites/Idle.png", 10, 0.1f);
        Animation<TextureRegion> walkAnimation = super.loadAnimation("sprites/drogo_sprites/Run.png", 8, 0.08f);
        Animation<TextureRegion> ascendingAnimation = super.loadAnimation("sprites/drogo_sprites/Jump.png", 3, 0.13f);
        Animation<TextureRegion> descendingAnimation = super.loadAnimation("sprites/drogo_sprites/Fall.png", 3, 0.13f);
        Animation<TextureRegion> attack1Animation = super.loadAnimation("sprites/drogo_sprites/Attack1.png", 7, 0.09f);
        Animation<TextureRegion> takingDamageAnimation = super.loadAnimation("sprites/drogo_sprites/Take Hit - white silhouette.png", 3, 0.07f);
        Animation<TextureRegion> deathAnimation = super.loadAnimation("sprites/drogo_sprites/Death.png", 11, 0.18f);
        Animation<TextureRegion> dashAnimation = super.loadAnimation("sprites/drogo_sprites/Dash.png", 2, 0.02f);
        Animation<TextureRegion> stepBackAnimation = super.loadAnimation("sprites/drogo_sprites/Step Back.png", 3, 0.05f);




        super.setIdleAnimation(idleAnimation);
        super.setWalkAnimation(walkAnimation);
        super.setAscendingAnimation(ascendingAnimation);
        super.setDescendingAnimation(descendingAnimation);
        super.setAttack1Animation(attack1Animation);
        super.setTakingDamageAnimation(takingDamageAnimation);
        super.setDeathAnimation(deathAnimation);
        super.setDashAnimation(dashAnimation);
        super.setStepBackAnimation(stepBackAnimation);


        Texture healthBarTexture = new Texture(Gdx.files.internal("health_bars/drogo_health_bar.png"));
        super.setHealthBarTexture(healthBarTexture);
    }
}

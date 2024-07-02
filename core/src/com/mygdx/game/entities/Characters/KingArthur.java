package com.mygdx.game.entities.Characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class KingArthur extends PlayerCharacter {
    private static final int xVelocity = 700;
    private static final int yVelocity = 1200;
    private static final int damageTakenPerHit = 15;
    private static final float damageDealtPerHit = 1.4f;
    private static final int stepBackStaminaCost = 30;
    private static final int dashStaminaCost = 35;
    private  static final int jumpStaminaCost = 20;

    private static final int attackStaminaCost = 20;
    private static final int characterHeight = 230;
    private static final int characterWidth = 90;
    private static final int characterHitBoxOffset = 380;
    private static final int attackHitBoxHeight = 213;
    private static final int attackHitBoxWidth = 260;
    private static final int attackHitBoxOffset = 110;


    public KingArthur() {
        super(xVelocity, yVelocity, damageTakenPerHit, damageDealtPerHit, stepBackStaminaCost, dashStaminaCost, jumpStaminaCost, attackStaminaCost, characterHeight, characterWidth,characterHitBoxOffset,  attackHitBoxHeight, attackHitBoxWidth, attackHitBoxOffset);
        // Loading Animations

        Animation<TextureRegion> idleAnimation = super.loadAnimation("sprites/arthur_sprites/Idle.png", 8, 0.1f);
        Animation<TextureRegion> walkAnimation = super.loadAnimation("sprites/arthur_sprites/Run.png", 8, 0.1f);
        Animation<TextureRegion> ascendingAnimation = super.loadAnimation("sprites/arthur_sprites/Jump.png", 2, 0.13f);
        Animation<TextureRegion> descendingAnimation = super.loadAnimation("sprites/arthur_sprites/Fall.png", 2, 0.13f);
        Animation<TextureRegion> attack1Animation = super.loadAnimation("sprites/arthur_sprites/Attack1.png", 4, 0.09f);
        Animation<TextureRegion> takingDamageAnimation = super.loadAnimation("sprites/arthur_sprites/Take Hit - white silhouette.png", 4, 0.07f);
        Animation<TextureRegion> deathAnimation = super.loadAnimation("sprites/arthur_sprites/Death.png", 6, 0.18f);
        Animation<TextureRegion> dashAnimation = super.loadAnimation("sprites/arthur_sprites/Dash.png", 2, 0.02f);
        Animation<TextureRegion> stepBackAnimation = super.loadAnimation("sprites/arthur_sprites/Step Back.png", 4, 0.05f);




        super.setIdleAnimation(idleAnimation);
        super.setWalkAnimation(walkAnimation);
        super.setAscendingAnimation(ascendingAnimation);
        super.setDescendingAnimation(descendingAnimation);
        super.setAttack1Animation(attack1Animation);
        super.setTakingDamageAnimation(takingDamageAnimation);
        super.setDeathAnimation(deathAnimation);
        super.setDashAnimation(dashAnimation);
        super.setStepBackAnimation(stepBackAnimation);


        Texture healthBarTexture = new Texture(Gdx.files.internal("health_bars/arthur_health_bar.png"));
        super.setHealthBarTexture(healthBarTexture);

    }
}

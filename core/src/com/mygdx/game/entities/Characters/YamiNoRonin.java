package com.mygdx.game.entities.Characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class YamiNoRonin extends PlayerCharacter {
    private static final int xVelocity = 900;

    private static final int yVelocity = 1350;
    private static final int damageTakenPerHit = 20;
    private static final float damageDealtPerHit = 1.1f;
    private static final int stepBackStaminaCost = 35;
    private static final int dashStaminaCost = 40;
    private  static final int jumpStaminaCost = 25;

    private static final int attackStaminaCost = 25;
    private static final int characterHeight = 250;
    private static final int characterWidth = 130;
    private static final int characterHitBoxOffset = 435;
    private static final int attackHitBoxHeight = 350;
    private static final int attackHitBoxWidth = 334;
    private static final int attackHitBoxOffset = 160;


    // Animations
    private final Animation<TextureRegion> idleAnimation;
    private final Animation<TextureRegion> walkAnimation;
    private final Animation<TextureRegion> ascendingAnimation;
    private final Animation<TextureRegion> descendingAnimation;
    private final Animation<TextureRegion> attack1Animation;
    private final Animation<TextureRegion> takingDamageAnimation;
    private final Animation<TextureRegion> deathAnimation;
    private final Animation<TextureRegion> dashAnimation;
    private final Animation<TextureRegion> stepBackAnimation;

    public YamiNoRonin() {
        super(xVelocity, yVelocity, damageTakenPerHit, damageDealtPerHit, stepBackStaminaCost, dashStaminaCost, jumpStaminaCost, attackStaminaCost, characterHeight, characterWidth,characterHitBoxOffset,  attackHitBoxHeight, attackHitBoxWidth, attackHitBoxOffset);

        // Loading Animations
        idleAnimation = super.loadAnimation("sprites/mizu_sprites/Idle.png",8,0.1f);
        walkAnimation = super.loadAnimation("sprites/mizu_sprites/Run.png", 8,0.08f);
        ascendingAnimation = super.loadAnimation("sprites/mizu_sprites/Jump.png", 2,0.13f);
        descendingAnimation = super.loadAnimation("sprites/mizu_sprites/Fall.png", 2,0.13f);
        attack1Animation = super.loadAnimation("sprites/mizu_sprites/Attack1.png", 6,0.09f);
        takingDamageAnimation = super.loadAnimation("sprites/mizu_sprites/Take Hit - white silhouette.png", 4,0.07f);
        deathAnimation = super.loadAnimation("sprites/mizu_sprites/Death.png", 6,0.18f);
        dashAnimation = super.loadAnimation("sprites/mizu_sprites/Dash.png", 4,0.02f);
        stepBackAnimation = super.loadAnimation("sprites/mizu_sprites/Step Back.png", 4,0.05f);




        super.setIdleAnimation(idleAnimation);
        super.setWalkAnimation(walkAnimation);
        super.setAscendingAnimation(ascendingAnimation);
        super.setDescendingAnimation(descendingAnimation);
        super.setAttack1Animation(attack1Animation);
        super.setTakingDamageAnimation(takingDamageAnimation);
        super.setDeathAnimation(deathAnimation);
        super.setDashAnimation(dashAnimation);
        super.setStepBackAnimation(stepBackAnimation);


        Texture healthBarTexture = new Texture(Gdx.files.internal("health_bars/yami_health_bar.png"));
        super.setHealthBarTexture(healthBarTexture);
    }

    // Getters


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
}

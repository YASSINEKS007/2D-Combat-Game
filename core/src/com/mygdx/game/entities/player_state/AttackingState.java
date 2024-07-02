package com.mygdx.game.entities.player_state;


import com.badlogic.gdx.Gdx;
import com.mygdx.game.entities.Fighter;

public class AttackingState extends FighterStateImpl {
    private float stateTime;
    private boolean isAlreadyHit;
    private boolean wasStaminaConsumed;

    public AttackingState() {
        stateTime = 0f;
        isAlreadyHit = false;
        wasStaminaConsumed = false;
    }

    @Override
    public void handleInput(Fighter fighter) {
        super.moveHorizontally(fighter);
        //super.die(fighter);

    }

    @Override
    public void update(Fighter fighter) {

        stateTime += Gdx.graphics.getDeltaTime();
        fighter.setPlayerTexture(fighter.getAttack1Animation().getKeyFrame(stateTime, false));

        // Consume Stamina
        if(!wasStaminaConsumed){
            fighter.setPlayerStamina(fighter.getPlayerStamina()- fighter.getCharacter().getAttackStaminaCost());
            fighter.getAttackSFX().play();
            wasStaminaConsumed = true;
        }

        // check if attack connects
        if (fighter.getAttackHitBox().intersects(fighter.getAdversary().getPlayerHitBox()) && stateTime >= 0.36f && !isAlreadyHit) {
            fighter.getTakeDamageSFX().play();
            isAlreadyHit = true;
            fighter.getAdversary().takeDamage();
            fighter.getAdversary().setState(new TakingDamageState());
        }

        // check if the animation is finished
        if(fighter.getAttack1Animation().isAnimationFinished(stateTime)){
            fighter.setState(new IdleState());
        }



    }


}

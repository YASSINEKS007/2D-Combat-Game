package com.mygdx.game.entities.player_state;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.entities.Fighter;

public class DashingState extends FighterStateImpl {

    private float dashDuration;
    private float stateTime;
    private boolean wasStaminaConsumed;

    public DashingState() {
        dashDuration = 0f;
        wasStaminaConsumed = false;


    }

    @Override
    public void handleInput(Fighter fighter) {

        super.handleInput(fighter);
        if (fighter.isFlipped()) {
            fighter.setxVelocity(-2000);
        } else {
            fighter.setxVelocity(2000);
        }
        dashDuration += Gdx.graphics.getDeltaTime();
        if(dashDuration>0.23f){
            fighter.setState(new IdleState());
        }

    }

    @Override
    public void update(Fighter fighter) {
        super.update(fighter);
        if(!wasStaminaConsumed) {
            fighter.setPlayerStamina(fighter.getPlayerStamina() - fighter.getCharacter().getDashStaminaCost());
            fighter.getDashSFX().play();
            wasStaminaConsumed =true;
        }

        stateTime += Gdx.graphics.getDeltaTime();
        fighter.setPlayerTexture(fighter.getDashAnimation().getKeyFrame(stateTime,false));
    }
}

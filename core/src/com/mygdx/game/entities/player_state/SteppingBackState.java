package com.mygdx.game.entities.player_state;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.entities.Fighter;

public class SteppingBackState extends FighterStateImpl{
    private float stateTime;
    private boolean wasStaminaConsumed;
    private float stepBackDuration;

    public SteppingBackState() {
        wasStaminaConsumed = false;
    }

    @Override
    public void handleInput(Fighter fighter) {
        super.handleInput(fighter);

        if (fighter.isFlipped()) {
            fighter.setxVelocity(1700);
        } else {
            fighter.setxVelocity(-1700);
        }
        stepBackDuration += Gdx.graphics.getDeltaTime();
        if(stepBackDuration>0.2f){
            fighter.setState(new IdleState());
        }
    }

    @Override
    public void update(Fighter fighter) {
        super.update(fighter);

        if(!wasStaminaConsumed) {
            fighter.setPlayerStamina(fighter.getPlayerStamina() - fighter.getCharacter().getStepBackStaminaCost());
            fighter.getStepBackSFX().play();
            wasStaminaConsumed =true;
        }

        stateTime += Gdx.graphics.getDeltaTime();
        fighter.setPlayerTexture(fighter.getStepBackAnimation().getKeyFrame(stateTime,false));
    }
}

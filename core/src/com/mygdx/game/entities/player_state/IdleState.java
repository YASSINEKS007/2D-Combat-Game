package com.mygdx.game.entities.player_state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.entities.Fighter;

public class IdleState extends FighterStateImpl {
    private float stateTime;

    public IdleState() {
        stateTime = 0f;

    }

    @Override
    public void handleInput(Fighter fighter) {
        super.moveHorizontally(fighter);
        super.die(fighter);
        fighter.flipHorizontally();







        // handle jump
        if(Gdx.input.isKeyJustPressed(fighter.getPlayerType() == 1 ? Input.Keys.W : Input.Keys.UP)){
            fighter.setyVelocity(fighter.getCharacter().getCharacterYVelocity());
            fighter.setPlayerStamina(fighter.getPlayerStamina()-fighter.getCharacter().getJumpStaminaCost());
            fighter.setState(new AscendingState());
        }

        // handle horizontal movement
        if (fighter.getyVelocity() == 0 && (fighter.getxVelocity() != 0)){
            fighter.setState(new RunningState());
        }


        // handle attack
        float currentPlayerStamina = fighter.getPlayerStamina();
        if(Gdx.input.isKeyJustPressed(fighter.getPlayerType() == 1 ? Input.Keys.S : Input.Keys.DOWN) && currentPlayerStamina -fighter.getCharacter().getAttackStaminaCost() > 0){
            fighter.setState(new AttackingState());
        }





    }

    @Override
    public void update(Fighter fighter) {

        // animate
        stateTime += Gdx.graphics.getDeltaTime();
        fighter.setPlayerTexture(fighter.getIdleAnimation().getKeyFrame(stateTime,true));

    }
}

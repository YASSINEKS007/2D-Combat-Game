package com.mygdx.game.entities.player_state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.entities.Fighter;

public abstract class FighterStateImpl implements FighterState {



    public FighterStateImpl() {

    }

    @Override
    public void handleInput(Fighter fighter) {

    }

    @Override
    public void update(Fighter fighter) {

    }



    public void moveHorizontally(Fighter fighter){
        fighter.setxVelocity(0);
        if (Gdx.input.isKeyPressed(fighter.getPlayerType() == 1 ? Input.Keys.A : Input.Keys.LEFT)) {
            fighter.setxVelocity(-fighter.getCharacter().getCharacterXVelocity());
        }
        if (Gdx.input.isKeyPressed(fighter.getPlayerType() == 1 ? Input.Keys.D : Input.Keys.RIGHT)) {
            fighter.setxVelocity(fighter.getCharacter().getCharacterXVelocity());
        }


    }

    public void die(Fighter fighter){
        if(fighter.getPlayerHealth() == 0  ){
            fighter.setState(new DeadState());
        }
    }


}

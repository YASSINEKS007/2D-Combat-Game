package com.mygdx.game.entities.player_state;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.entities.Fighter;

public class AscendingState extends FighterStateImpl {
    private float stateTime;

    public AscendingState() {
        stateTime = 0f;
    }

    @Override
    public void handleInput(Fighter fighter) {
        super.moveHorizontally(fighter);
        //super.die(fighter);
        fighter.flipHorizontally();
    }

    @Override
    public void update(Fighter fighter) {
        // switch to descending
        if(fighter.getyVelocity() < 0){
            fighter.setState(new DescendingState());
        }

        // update sprite
        stateTime += Gdx.graphics.getDeltaTime();
        fighter.setPlayerTexture(fighter.getAscendingAnimation().getKeyFrame(stateTime,true));


    }
}

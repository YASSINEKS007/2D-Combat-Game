package com.mygdx.game.entities.player_state;


import com.badlogic.gdx.Gdx;
import com.mygdx.game.entities.Fighter;

public class DescendingState extends FighterStateImpl {
    private float stateTime;

    public DescendingState() {
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
        // update sprite
        stateTime += Gdx.graphics.getDeltaTime();
        fighter.setPlayerTexture(fighter.getDescendingAnimation().getKeyFrame(stateTime,true));


        // Go Idle once landed
        if (fighter.getyVelocity() == 0){
            fighter.setState(new IdleState());
        }
    }
}

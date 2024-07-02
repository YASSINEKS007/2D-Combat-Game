package com.mygdx.game.entities.player_state;


import com.badlogic.gdx.Gdx;
import com.mygdx.game.entities.Fighter;

public class DeadState extends FighterStateImpl {
    private float stateTime;

    public DeadState() {
        stateTime=0f;
    }

    @Override
    public void handleInput(Fighter fighter) {

    }

    @Override
    public void update(Fighter fighter) {
        // animate
        stateTime += Gdx.graphics.getDeltaTime();
        fighter.setPlayerTexture(fighter.getDeathAnimation().getKeyFrame(stateTime,false));
    }
}

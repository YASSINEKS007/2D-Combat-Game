package com.mygdx.game.entities.player_state;


import com.badlogic.gdx.Gdx;
import com.mygdx.game.entities.Fighter;

public class TakingDamageState extends FighterStateImpl {

    private float stateTime;

    public TakingDamageState() {
        stateTime = 0f;

    }

    @Override
    public void handleInput(Fighter fighter) {
        //super.die(fighter);



    }

    @Override
    public void update(Fighter fighter) {
        System.out.println("Taking damage");

        stateTime += Gdx.graphics.getDeltaTime();
        fighter.setPlayerTexture(fighter.getTakingDamageAnimation().getKeyFrame(stateTime, false));
        if (fighter.isFlipped()) {
            fighter.setxVelocity(130);
        } else {
            fighter.setxVelocity(-130);
        }



        // Change state once the animation is finished

        if(fighter.getTakingDamageAnimation().isAnimationFinished(stateTime)){
            fighter.setState(new IdleState());
        }
    }
}

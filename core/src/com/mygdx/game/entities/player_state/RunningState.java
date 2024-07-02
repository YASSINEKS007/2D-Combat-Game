package com.mygdx.game.entities.player_state;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.entities.Fighter;


public class RunningState extends FighterStateImpl {
    private float stateTime;
    boolean soundPlayed;
    private long id;

    public RunningState() {
        stateTime = 0f;
        soundPlayed = false;
    }

    @Override
    public void handleInput(Fighter fighter) {

        super.moveHorizontally(fighter);
        //super.die(fighter);
        fighter.flipHorizontally();


        // handle jump
        if(Gdx.input.isKeyJustPressed(fighter.getPlayerType() == 1 ? Input.Keys.W : Input.Keys.UP)){
            fighter.setyVelocity(fighter.getCharacter().getCharacterYVelocity());
            fighter.setPlayerStamina(fighter.getPlayerStamina()-fighter.getCharacter().getJumpStaminaCost());
            fighter.getRunningSFX().stop(id);
            fighter.setState(new AscendingState());
        }

        // handle attack

        float currentPlayerStamina = fighter.getPlayerStamina();
        if(Gdx.input.isKeyJustPressed(fighter.getPlayerType() == 1 ? Input.Keys.S : Input.Keys.DOWN) && currentPlayerStamina -fighter.getCharacter().getAttackStaminaCost() > 0){
            fighter.getRunningSFX().stop(id);
            fighter.setState(new AttackingState());
        }

        // handle stop
        if (fighter.getxVelocity() == 0){
            fighter.getRunningSFX().stop(id);
            fighter.setState(new IdleState());
        }
    }

    @Override
    public void update(Fighter fighter) {
        if(!soundPlayed){
            id = fighter.getRunningSFX().play();
            fighter.getRunningSFX().setLooping(id, true);
            soundPlayed = true;
        }
        // Animate
        stateTime += Gdx.graphics.getDeltaTime();
        fighter.setPlayerTexture(fighter.getWalkAnimation().getKeyFrame(stateTime, true));

    }
}

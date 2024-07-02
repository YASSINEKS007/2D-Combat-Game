package input_processors;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.entities.Fighter;
import com.mygdx.game.entities.player_state.DashingState;
import com.mygdx.game.entities.player_state.SteppingBackState;

public class DoubleKeyTapInputProcessor implements InputProcessor {
    private final Fighter fighter;
    private int lastKeyPressed;
    public DoubleKeyTapInputProcessor(Fighter fighter) {
        this.fighter = fighter;
    }


    @Override
    public boolean keyDown(int keycode) {
        float timeSinceLastKeyPressed = fighter.getTimeSinceLastKeyPressed();

        int dashKey = fighter.getPlayerType() == 1 ? Input.Keys.D : Input.Keys.RIGHT;
        int stepBackKey = fighter.getPlayerType() == 1 ? Input.Keys.A : Input.Keys.LEFT;


        if (timeSinceLastKeyPressed < 0.4f && (keycode == dashKey || keycode == stepBackKey) && lastKeyPressed == keycode) {
            float staminaCost = (keycode == dashKey) ? fighter.getCharacter().getDashStaminaCost() : fighter.getCharacter().getStepBackStaminaCost();

            if (!fighter.isFlipped() && fighter.getPlayerStamina() - staminaCost > 0) {
                fighter.setState((keycode == dashKey) ? new DashingState() : new SteppingBackState());
            } else if (fighter.isFlipped() && fighter.getPlayerStamina() - staminaCost > 0) {
                fighter.setState((keycode == dashKey) ? new SteppingBackState() : new DashingState());
            } else {
                fighter.setTimeSinceLastKeyPressed(0f);
            }
        } else {
            fighter.setTimeSinceLastKeyPressed(0f);
        }



        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        lastKeyPressed = keycode;
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchCancelled(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(float v, float v1) {
        return false;
    }
}

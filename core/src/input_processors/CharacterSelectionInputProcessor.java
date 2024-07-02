package input_processors;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.screens.CharacterSelectionScreen;
import com.mygdx.game.soundeffectsmanager.SoundManager;

import java.util.Map;

public class CharacterSelectionInputProcessor implements InputProcessor {

    CharacterSelectionScreen characterSelectionScreen;
    private boolean p1HasSelected;
    private boolean p2HasSelected;


    public CharacterSelectionInputProcessor(CharacterSelectionScreen characterSelectionScreen) {
        this.characterSelectionScreen = characterSelectionScreen;
        p1HasSelected = false;
        p2HasSelected = false;
    }

    @Override
    public boolean keyDown(int keyCode) {
        int p2CharacterIndex = characterSelectionScreen.getP2CharacterIndex();
        int p1CharacterIndex = characterSelectionScreen.getP1CharacterIndex();
        Map<Integer, Boolean> p1SelectedCharactersMap = characterSelectionScreen.getP1SelectedCharactersMap();
        Map<Integer, Boolean> p2SelectedCharactersMap = characterSelectionScreen.getP2SelectedCharactersMap();


        if (keyCode == Input.Keys.SPACE){
            if(!p2SelectedCharactersMap.get(p1CharacterIndex)){
                p1HasSelected  = true;
                p1SelectedCharactersMap.put(p1CharacterIndex, true);
                characterSelectionScreen.getSelectSFX().play(SoundManager.getSoundEffectsVolume());
            }
            else{
                System.out.println("Character Already selected!!");
                characterSelectionScreen.getErrorSFX().play(SoundManager.getSoundEffectsVolume());
            }
        }

        if (keyCode == Input.Keys.ENTER){
            if(!p1SelectedCharactersMap.get(p2CharacterIndex)){
                p2HasSelected  = true;
                p2SelectedCharactersMap.put(p2CharacterIndex, true);
                characterSelectionScreen.getSelectSFX().play(SoundManager.getSoundEffectsVolume());
            }
            else{
                System.out.println("Character Already selected!!");
                characterSelectionScreen.getErrorSFX().play(SoundManager.getSoundEffectsVolume());
            }
        }

        if(keyCode == Input.Keys.RIGHT && !p2HasSelected){
            if(p2CharacterIndex < 3){
                characterSelectionScreen.setP2CharacterIndex(++p2CharacterIndex);
            }else{
                characterSelectionScreen.setP2CharacterIndex(0);
            }
            characterSelectionScreen.getNavigationSFX().play(SoundManager.getSoundEffectsVolume());
        }

        if(keyCode == Input.Keys.LEFT && !p2HasSelected){
            if(p2CharacterIndex > 0){
                characterSelectionScreen.setP2CharacterIndex(--p2CharacterIndex);
            }else{
                characterSelectionScreen.setP2CharacterIndex(3);
            }
            characterSelectionScreen.getNavigationSFX().play(SoundManager.getSoundEffectsVolume());
        }

        if(keyCode == Input.Keys.D && !p1HasSelected){
            if(p1CharacterIndex < 3){
                characterSelectionScreen.setP1CharacterIndex(++p1CharacterIndex);
            }else{
                characterSelectionScreen.setP1CharacterIndex(0);
            }
            characterSelectionScreen.getNavigationSFX().play(SoundManager.getSoundEffectsVolume());
        }

        if(keyCode == Input.Keys.A && !p1HasSelected){
            if(p1CharacterIndex > 0){
                characterSelectionScreen.setP1CharacterIndex(--p1CharacterIndex);
            }else{
                characterSelectionScreen.setP1CharacterIndex(3);
            }
            characterSelectionScreen.getNavigationSFX().play(SoundManager.getSoundEffectsVolume());
        }



        return false;
    }

    @Override
    public boolean keyUp(int keyCode) {
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

package com.mygdx.game.entities;

import com.mygdx.game.entities.Characters.PlayerCharacter;
import com.mygdx.game.entities.arenas.Arena;

public class PlayerTwo extends Fighter{
    public PlayerTwo() {
    }

    public PlayerTwo(int x, int y, int playerType, Arena arena, PlayerCharacter character) {
        super(x, y, playerType, arena, character);

        super.setAttackHitBoxOffset(-super.getCharacter().getAttackHitBoxOffset()+ character.getCharacterWidth()- character.getAttackHitBoxWidth());

        super.setFlipped(true);
    }

    @Override
    public void flipHorizontally() {
        if(super.getAdversary().getPlayerHitBox().x - super.getPlayerHitBox().x >0){
            if(super.isFlipped()) {
                super.setAttackHitBoxOffset(super.getCharacter().getAttackHitBoxOffset());
                super.setFlipped(false);
            }
        } else if (!super.isFlipped() && super.getAdversary().getPlayerHitBox().x - super.getPlayerHitBox().x < 0) {
            super.setAttackHitBoxOffset(-super.getCharacter().getAttackHitBoxOffset()+ super.getCharacter().getCharacterWidth()- getCharacter().getAttackHitBoxWidth());
            super.setFlipped(true);
        }
    }
}

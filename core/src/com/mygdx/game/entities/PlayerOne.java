package com.mygdx.game.entities;

import com.mygdx.game.entities.Characters.PlayerCharacter;
import com.mygdx.game.entities.arenas.Arena;

public class PlayerOne extends Fighter{


    public PlayerOne() {
    }

    public PlayerOne(int x, int y, int playerType, Arena arena, PlayerCharacter character) {
        super(x, y, playerType, arena, character);
    }

    @Override
    public void flipHorizontally() {
        if(super.getAdversary().getPlayerHitBox().x - super.getPlayerHitBox().x <0){
            if(!super.isFlipped()) {
                super.setAttackHitBoxOffset(-super.getCharacter().getAttackHitBoxOffset()+ getCharacter().getCharacterWidth()- getCharacter().getAttackHitBoxWidth());
                super.setFlipped(true);
            }
        } else if (super.isFlipped() && super.getAdversary().getPlayerHitBox().x - super.getPlayerHitBox().x > 0) {
            super.setAttackHitBoxOffset(super.getCharacter().getAttackHitBoxOffset());
            super.setFlipped(false);
        }

    }
}

package com.mygdx.game.entities.player_state;


import com.mygdx.game.entities.Fighter;

public interface FighterState {
    void  handleInput(Fighter fighter);
    void update(Fighter fighter);

}

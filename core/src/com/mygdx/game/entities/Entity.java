package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Entity {
    void draw(SpriteBatch batch, OrthographicCamera camera);
    void update();
}

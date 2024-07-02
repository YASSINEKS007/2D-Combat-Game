package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.helper.Constants;

public class StaminaBar {


    private final Fighter fighter;
    private final ShapeRenderer shapeRenderer;
    private static final int STAMINA_BAR_WIDTH = 155;
    private static final int STAMINA_BAR_HEIGHT = 15;
    private float currentStaminaWidth;

    public StaminaBar(Fighter fighter) {
        currentStaminaWidth = STAMINA_BAR_WIDTH;
        this.fighter  = fighter;
        shapeRenderer = new ShapeRenderer();
    }

    public void draw(SpriteBatch batch) {
        int x;
        int y;
        x = fighter.getPlayerType() == 1 ? 110+20 : Constants.SCREEN_WIDTH - STAMINA_BAR_WIDTH-110-20;
        y = Constants.SCREEN_HEIGHT - STAMINA_BAR_HEIGHT-60;
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1,1,1,1);
        shapeRenderer.rect(x, y, STAMINA_BAR_WIDTH, STAMINA_BAR_HEIGHT);
        shapeRenderer.setColor(0.26f,0.74f,0.9f,1);
        shapeRenderer.rect(fighter.getPlayerType() == 1 ? x : Constants.SCREEN_WIDTH-currentStaminaWidth-110-20, y, currentStaminaWidth,  STAMINA_BAR_HEIGHT);
        shapeRenderer.end();
    }
    public void update() {
        currentStaminaWidth = (STAMINA_BAR_WIDTH *fighter.getPlayerStamina())/100;
    }
}

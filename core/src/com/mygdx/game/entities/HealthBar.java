package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.helper.Constants;

public class HealthBar implements Entity{
    private final Fighter fighter;
    private final ShapeRenderer shapeRenderer;
    private static final int HEALTH_BAR_WIDTH = 380;
    private static final int HEALTH_BAR_HEIGHT = 25;
    private float currentHealthWidth;


    private Texture healthBarTexture;

    public HealthBar(Fighter fighter) {
        currentHealthWidth = HEALTH_BAR_WIDTH;
        this.fighter  = fighter;
        shapeRenderer = new ShapeRenderer();


        healthBarTexture = fighter.getCharacter().getHealthBarTexture();
    }

    @Override
    public void draw(SpriteBatch batch, OrthographicCamera camera) {
        int x;
        int y;

        x = fighter.getPlayerType() == 1 ? 110+20 : Constants.SCREEN_WIDTH - HEALTH_BAR_WIDTH-110-20;
        y = Constants.SCREEN_HEIGHT - HEALTH_BAR_HEIGHT-25;

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1,1,1,0.3f);
        shapeRenderer.rect(x, y, HEALTH_BAR_WIDTH, HEALTH_BAR_HEIGHT);
        shapeRenderer.setColor(1,0.52f,0.09f,1);
        shapeRenderer.rect(fighter.getPlayerType() == 1 ? x : Constants.SCREEN_WIDTH-currentHealthWidth-110-20, y, currentHealthWidth,  HEALTH_BAR_HEIGHT);
        shapeRenderer.end();
        batch.begin();
        batch.draw(healthBarTexture, fighter.getPlayerType() == 1 ? camera.position.x - (float) Constants.SCREEN_WIDTH /2 + 20: camera.position.x - (float) Constants.SCREEN_WIDTH /2  +Constants.SCREEN_WIDTH-20,Constants.SCREEN_HEIGHT-110-20, fighter.getPlayerType() == 1? healthBarTexture.getWidth() : -healthBarTexture.getWidth(), healthBarTexture.getHeight());
        batch.end();

    }

    @Override
    public void update() {
        currentHealthWidth = (HEALTH_BAR_WIDTH*fighter.getPlayerHealth())/100;
    }
}
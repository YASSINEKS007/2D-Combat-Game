package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.helper.Constants;

public class SlideBar implements Entity{
    private final Texture trackTexture;
    private final Texture notchTexture;
    private final int trackX;
    private final int trackY;
    private int notchX;
    private final int notchY;

    public SlideBar(Texture trackTexture, Texture notchTexture, int x, int y) {
        this.trackTexture = trackTexture;
        this.notchTexture = notchTexture;
        trackX = x;
        trackY = y;
        notchX = trackX-notchTexture.getWidth()/2;
        notchY = (int) (trackY - ((float) notchTexture.getHeight() /2) + ((float) trackTexture.getHeight() /2));
    }

    @Override
    public void draw(SpriteBatch batch, OrthographicCamera camera) {
        batch.draw(trackTexture, trackX, trackY);
        batch.draw(notchTexture, notchX, notchY);
    }

    @Override
    public void update() {
        if(Gdx.input.isTouched()){
            if (Gdx.input.getX() > trackX && Gdx.input.getX() < trackX +trackTexture.getWidth() && Constants.SCREEN_HEIGHT - Gdx.input.getY() > notchY && Constants.SCREEN_HEIGHT - Gdx.input.getY() < notchY + notchTexture.getHeight()){
                notchX = Gdx.input.getX()-notchTexture.getWidth()/2;
            }
        }
    }

    public int getValue() {
        return  (notchX + notchTexture.getWidth() / 2 - trackX)*100 / trackTexture.getWidth();
    }
}

package com.mygdx.game.entities.arenas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Arena {
    private final Sprite mapSprite;
    private final int arenaIndex;
    private final int groundLevel;

    private Music arenaMusic;


    public Arena(int arenaIndex) {
        this.arenaIndex = arenaIndex;
        mapSprite = loadMapSprite();
        mapSprite.setPosition(0,0);
        mapSprite.setSize(mapSprite.getWidth(), mapSprite.getHeight());

        arenaMusic  = loadMusic();
        groundLevel = 30;
        System.out.println(arenaIndex);
    }


    public Music loadMusic(){
        String musicName = null;

        if(arenaIndex == 0){
            musicName = "Emergency On Level 3 - Jeremy Korpas.mp3";
        }
        if(arenaIndex == 1){
            musicName = "Emergency On Level 3 - Jeremy Korpas.mp3";
        }
        if(arenaIndex == 2){
            musicName = "Emergency On Level 3 - Jeremy Korpas.mp3";
        }

        arenaMusic = Gdx.audio.newMusic(Gdx.files.internal("music/"+musicName));
        return arenaMusic;
    }

    public Sprite loadMapSprite(){

        if(arenaIndex == 1){
            System.out.println("Kesh Chosen!");
            return new Sprite(new Texture(Gdx.files.internal("backgrounds/Marrakesh.png")));
        }
        if(arenaIndex == 0){
            return new Sprite(new Texture(Gdx.files.internal("backgrounds/Casablanca.png")));
        }
        if(arenaIndex == 2){
            return new Sprite(new Texture(Gdx.files.internal("backgrounds/Rabat.jpg")));
        }

        return null;
    }

    public Sprite getMapSprite() {
        return mapSprite;
    }

    public int getGroundLevel() {
        return groundLevel;
    }

    public Music getArenaMusic() {
        return arenaMusic;
    }
}

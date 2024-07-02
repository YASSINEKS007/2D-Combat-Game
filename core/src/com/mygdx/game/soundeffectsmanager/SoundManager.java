package com.mygdx.game.soundeffectsmanager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.mygdx.game.screens.SettingsScreen;

public class SoundManager {
    private static float soundEffectsVolume = SettingsScreen.getSavedSlider2Value();
    private static Sound switchSound; // Declare a Sound variable
    private static float backgroundMusicVolume = SettingsScreen.getSavedSlider1Value();
    public static Music backgroundMusic = null; // Declare the Sound object


    static {
        // Initialize switchSound in a static block
        switchSound = Gdx.audio.newSound(Gdx.files.internal("click_sound.mp3"));
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("main_music.mp3"));
    }

    // Make the method public or package-private
    public static void playSwitchSound() {
        switchSound.play(soundEffectsVolume);
    }
    public static void playBackgroundMusic(){
        backgroundMusic.play();
    }
    public static void stopBackgroundMusic(){
        backgroundMusic.stop();
    }

    public static float getDefaultSoundEffectsVolume() {
        return 0.5f; // Example value, replace with your logic to get the default sound effects volume
    }


    public static float getSoundEffectsVolume() {
        return soundEffectsVolume;
    }

    public static void setSoundEffectsVolume(float soundEffectsVolume) {
        SoundManager.soundEffectsVolume = soundEffectsVolume;
    }

    public static float getBackgroundMusicVolume() {
        return backgroundMusicVolume;
    }

    public static void setBackgroundMusicVolume(float backgroundMusicVolume) {
        backgroundMusic.setVolume(backgroundMusicVolume);
    }
}

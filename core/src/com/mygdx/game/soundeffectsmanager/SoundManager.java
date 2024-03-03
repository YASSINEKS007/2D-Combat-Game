package com.mygdx.game.soundeffectsmanager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.mygdx.game.screens.SettingsScreen;

public class SoundManager {
    private static float soundEffectsVolume = SettingsScreen.getSavedSlider2Value();
    private static Sound switchSound; // Declare a Sound variable

    static {
        // Initialize switchSound in a static block
        switchSound = Gdx.audio.newSound(Gdx.files.internal("click_sound.mp3"));
    }

    // Make the method public or package-private
    public static void playSwitchSound() {
        switchSound.play(soundEffectsVolume);
    }

    public static float getDefaultSoundEffectsVolume() {
        // Return the default sound effects volume (you may replace this with your implementation)
        return 0.5f; // Example value, replace with your logic to get the default sound effects volume
    }


    public static float getSoundEffectsVolume() {
        return soundEffectsVolume;
    }

    public static void setSoundEffectsVolume(float soundEffectsVolume) {
        SoundManager.soundEffectsVolume = soundEffectsVolume;
    }
}

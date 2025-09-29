package com.ArduinoFactory.androidstudio.outils;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.widget.Button;

import com.ArduinoFactory.androidstudio.page_accessoire.Page_Internet;
import com.ArduinoFactory.androidstudio.R;

public class Outils_telecommande_tuto extends AppCompatActivity {

    private SoundPool soundPool;
    private int soundId;
    private boolean soundLoaded = false;
    private float volume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outils_telecommande_tuto);

        initSound();

        Button button_achat = findViewById(R.id.button_achat);
        button_achat.setOnClickListener(v -> {
            playSound();
            click_boutonAchat();
        });

        Button button_code = findViewById(R.id.button_code);
        button_code.setOnClickListener(v -> {
            playSound();
            click_boutonCode();
        });
    }

    private void click_boutonAchat() {
        Intent intent = new Intent(this, Page_Internet.class);
        intent.putExtra("url", getString(R.string.url_amazon_telecommande));
        startActivity(intent);
    }

    private void click_boutonCode() {
        Intent intent = new Intent(this, Page_Internet.class);
        intent.putExtra("url", getString(R.string.url_code_telecommande));
        startActivity(intent);
    }

    private void initSound() {
        AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        float currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        float maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        volume = currentVolume / maxVolume;

        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();

        soundPool = new SoundPool.Builder()
                .setMaxStreams(1)
                .setAudioAttributes(audioAttributes)
                .build();

        soundPool.setOnLoadCompleteListener((pool, sampleId, status) -> soundLoaded = true);
        soundId = soundPool.load(this, R.raw.son_bouton, 1); // Le fichier doit être présent dans res/raw/
    }

    private void playSound() {
        if (soundLoaded && soundPool != null) {
            soundPool.play(soundId, volume, volume, 1, 0, 1f);
        }
    }
}

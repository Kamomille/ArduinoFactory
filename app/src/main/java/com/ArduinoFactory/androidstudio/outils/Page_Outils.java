package com.ArduinoFactory.androidstudio.outils;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.ArduinoFactory.androidstudio.R;

public class Page_Outils extends AppCompatActivity {

    private SoundPool soundPool;
    private static final int MAX_STREAMS = 100;
    private static final int streamType = AudioManager.STREAM_MUSIC;
    private boolean loaded;
    private int soundIdBouton;
    private float volume;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page__outils);

        // bouton retour
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        // Gestion du volume
        AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        float currentVolumeIndex = (float) audioManager.getStreamVolume(streamType);
        float maxVolumeIndex = (float) audioManager.getStreamMaxVolume(streamType);
        this.volume = currentVolumeIndex / maxVolumeIndex;
        this.setVolumeControlStream(streamType);

        // ✅ Utilisation de SoundPool.Builder (plus besoin de check SDK_INT)
        AudioAttributes audioAttrib = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();

        this.soundPool = new SoundPool.Builder()
                .setAudioAttributes(audioAttrib)
                .setMaxStreams(MAX_STREAMS)
                .build();

        this.soundPool.setOnLoadCompleteListener((soundPool, sampleId, status) -> loaded = true);
        this.soundIdBouton = this.soundPool.load(this, R.raw.son_bouton, 1);

        // Boutons
        LinearLayout outil_resistance = findViewById(R.id.outil_resistance);
        outil_resistance.setOnClickListener(v -> openActivtity_outilsResistance());

        LinearLayout outil_telecommande = findViewById(R.id.outil_telecommande);
        outil_telecommande.setOnClickListener(v -> openActivtity_outilsTelecommande());

        LinearLayout outil_ia = findViewById(R.id.outil_ia);
        outil_ia.setOnClickListener(v -> openActivtity_outilsIA());
    }

    public void openActivtity_outilsResistance() {
        playSound();
        startActivity(new Intent(this, Outils_resistance.class));
    }

    public void openActivtity_outilsTelecommande() {
        playSound();
        Intent intent = new Intent(this, Outils_telecommande.class);
        intent.putExtra("af", "1");
        startActivity(intent);
    }

    public void openActivtity_outilsIA() {
        playSound();
        startActivity(new Intent(this, Outils_reconnaissance_composants.class));
    }

    public void playSound() {
        if (loaded) {
            float leftVolume = volume;
            float rightVolume = volume;
            // ✅ Plus besoin de variable streamId
            this.soundPool.play(this.soundIdBouton, leftVolume, rightVolume, 1, 0, 1f);
        }
    }
}

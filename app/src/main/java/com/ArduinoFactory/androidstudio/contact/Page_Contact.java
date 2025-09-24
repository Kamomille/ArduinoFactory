package com.ArduinoFactory.androidstudio.contact;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.ArduinoFactory.androidstudio.page_accessoire.Page_Internet;
import com.ArduinoFactory.androidstudio.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class Page_Contact extends AppCompatActivity {

    private SoundPool soundPool;
    // Maximum sound streams.
    private static final int MAX_STREAMS = 100;
    // Stream type.
    private static final int streamType = AudioManager.STREAM_MUSIC;
    private boolean loaded;
    private int soundIdBouton;
    private float volume;

    String Arduino_Factory_url = "https://arduinofactory.fr/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page__contact);

        AdView mAdView = findViewById(R.id.adView7);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        // bouton retour ----------------------
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        // AudioManager audio settings for adjusting the volume
        AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

        // Current volume index of particular stream type.
        float currentVolumeIndex = (float) audioManager.getStreamVolume(streamType);

        // Get the maximum volume index for a particular stream type.
        float maxVolumeIndex = (float) audioManager.getStreamMaxVolume(streamType);

        // Volume (0 --> 1)
        this.volume = currentVolumeIndex / maxVolumeIndex;

        // Suggests an audio stream whose volume should be changed by
        // the hardware volume controls.
        this.setVolumeControlStream(streamType);

        // ✅ Plus besoin de vérifier la version, toutes sont >= 21
        AudioAttributes audioAttrib = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();

        SoundPool.Builder builder = new SoundPool.Builder();
        builder.setAudioAttributes(audioAttrib).setMaxStreams(MAX_STREAMS);

        this.soundPool = builder.build();

        // When Sound Pool load complete.
        this.soundPool.setOnLoadCompleteListener((soundPool, sampleId, status) -> loaded = true);

        // Load sound file (destroy.wav) into SoundPool.
        this.soundIdBouton = this.soundPool.load(this, R.raw.son_bouton, 1);

        LinearLayout buttonSite = findViewById(R.id.buttonSite);
        buttonSite.setOnClickListener(v -> openActivtity_site());

        LinearLayout buttonContactezNous = findViewById(R.id.buttonContactezNous);
        buttonContactezNous.setOnClickListener(v -> openActivtity_contactezNous());

        LinearLayout buttonPropos = findViewById(R.id.buttonPropos);
        buttonPropos.setOnClickListener(v -> openActivtity_propos());
    }

    public void openActivtity_site() {
        playSound();
        Intent intent = new Intent(this, Page_Internet.class);
        intent.putExtra("url", Arduino_Factory_url);
        startActivity(intent);
    }

    public void openActivtity_contactezNous() {
        playSound();
        Intent intent = new Intent(this, Contactez_nous.class);
        startActivity(intent);
    }

    public void openActivtity_propos() {
        playSound();
        Intent intent = new Intent(this, A_propos.class);
        startActivity(intent);
    }

    public void playSound() {
        if (loaded) {
            float leftVolume = volume;
            float rightVolume = volume;
            // ✅ Inutile de stocker streamId si non utilisé
            this.soundPool.play(this.soundIdBouton, leftVolume, rightVolume, 1, 0, 1f);
        }
    }
}

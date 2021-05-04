package com.example.androidstudio.pages;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.example.androidstudio.MainActivity;
import com.example.androidstudio.Page_Internet;
import com.example.androidstudio.R;
import com.example.androidstudio.contact.A_propos;
import com.example.androidstudio.contact.Contactez_nous;

public class Page_Contact extends AppCompatActivity {

    private LinearLayout buttonSite, buttonContactezNous, buttonPropos;
    private SoundPool soundPool;
    private AudioManager audioManager;
    // Maximumn sound stream.
    private static final int MAX_STREAMS = 100;
    // Stream type.
    private static final int streamType = AudioManager.STREAM_MUSIC;
    private boolean loaded;
    private int soundIdBouton;
    private float volume;

    String Arduino_Factory_url="https://arduinofactory.fr/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page__contact);

        // bouton retour ----------------------
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        // AudioManager audio settings for adjusting the volume
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

        // Current volumn Index of particular stream type.
        float currentVolumeIndex = (float) audioManager.getStreamVolume(streamType);

        // Get the maximum volume index for a particular stream type.
        float maxVolumeIndex = (float) audioManager.getStreamMaxVolume(streamType);

        // Volumn (0 --> 1)
        this.volume = currentVolumeIndex / maxVolumeIndex;

        // Suggests an audio stream whose volume should be changed by
        // the hardware volume controls.
        this.setVolumeControlStream(streamType);

        // For Android SDK >= 21
        if (Build.VERSION.SDK_INT >= 21) {
            AudioAttributes audioAttrib = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            SoundPool.Builder builder = new SoundPool.Builder();
            builder.setAudioAttributes(audioAttrib).setMaxStreams(MAX_STREAMS);

            this.soundPool = builder.build();
        }
        // for Android SDK < 21
        else {
            // SoundPool(int maxStreams, int streamType, int srcQuality)
            this.soundPool = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);
        }

        // When Sound Pool load complete.
        this.soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                loaded = true;
            }
        });

        // Load sound file (destroy.wav) into SoundPool.
        this.soundIdBouton = this.soundPool.load(this, R.raw.son_bouton, 1);

    buttonSite = (LinearLayout) findViewById(R.id.buttonSite);
    buttonSite.setOnClickListener(new View.OnClickListener(){
        @Override public void onClick(View v){ openActivtity_site(); } } );
    buttonContactezNous = (LinearLayout) findViewById(R.id.buttonContactezNous);
    buttonContactezNous.setOnClickListener(new View.OnClickListener(){
        @Override public void onClick(View v){ openActivtity_contactezNous(); } } );
    buttonPropos = (LinearLayout) findViewById(R.id.buttonPropos);
    buttonPropos.setOnClickListener(new View.OnClickListener(){
        @Override public void onClick(View v){ openActivtity_propos(); } } );

    }
    public void openActivtity_site(){
        playSound();
        Intent intent = new Intent(this, Page_Internet.class);
        intent.putExtra("af",Arduino_Factory_url);
        startActivity(intent);
    }
    public void openActivtity_contactezNous(){
        playSound();
        Intent intent = new Intent(this, Contactez_nous.class);
        startActivity(intent);
    }
    public void openActivtity_propos(){
        playSound();
        Intent intent = new Intent(this, A_propos.class);
        startActivity(intent);
    }
    public void playSound()  {
        if(loaded)  {
            float leftVolumn = volume;
            float rightVolumn = volume;
            // Play sound of gunfire. Returns the ID of the new stream.
            int streamId = this.soundPool.play(this.soundIdBouton,leftVolumn, rightVolumn, 1, 0, 1f);
        }
    }


}
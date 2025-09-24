package com.ArduinoFactory.androidstudio.page_principal;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.ArduinoFactory.androidstudio.R;
import com.ArduinoFactory.androidstudio.achat.Achat_Main;
import com.ArduinoFactory.androidstudio.favoris.Page_Favoris;
import com.ArduinoFactory.androidstudio.Notification.Page_Notification;
import com.ArduinoFactory.androidstudio.contact.Page_Contact;
import com.ArduinoFactory.androidstudio.cours.Cours_Main;
import com.ArduinoFactory.androidstudio.outils.Page_Outils;
import com.ArduinoFactory.androidstudio.page_accessoire.Page_Parametre;
//import com.google.android.gms.ads.MobileAds;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private SoundPool soundPool;
    private int soundIdBouton;
    private boolean loaded = false;
    private float volume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // Cacher la barre du haut
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.hide();

        // Initialiser les boutons
        Button buttonOutils = findViewById(R.id.buttonOutils);
        Button buttonCours = findViewById(R.id.buttonCours);
        Button buttonAchat = findViewById(R.id.buttonAchat);
        Button buttonContact = findViewById(R.id.buttonContacter);
        ImageButton buttonParametre = findViewById(R.id.buttonParametre);
        ImageButton imageCours = findViewById(R.id.imageCours);
        ImageButton imageAchat = findViewById(R.id.imageAchat);
        ImageButton imageContact = findViewById(R.id.imageContacter);

        // Associer les clics
        buttonOutils.setOnClickListener(this::onClickOutils);
        buttonCours.setOnClickListener(this::onClickCours);
        buttonAchat.setOnClickListener(this::onClickAchat);
        buttonContact.setOnClickListener(this::onClickContacter);
        buttonParametre.setOnClickListener(this::onClickParametre);

        imageCours.setOnClickListener(this::onClickCours);
        imageAchat.setOnClickListener(this::onClickAchat);
        imageContact.setOnClickListener(this::onClickContacter);

        // Initialiser le SoundPool
        initSound();

        // Navigation inférieure
        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.navigation_dashboard) {
                openFavoris();
                return true;
            } else if (itemId == R.id.navigation_notifications) {
                openNotification();
                return true;
            }
            return false;
        });
    }

    private void initSound() {
        AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        float currentVolumeIndex = (float) audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        float maxVolumeIndex = (float) audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        volume = currentVolumeIndex / maxVolumeIndex;

        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        AudioAttributes audioAttrib = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();

        soundPool = new SoundPool.Builder()
                .setAudioAttributes(audioAttrib)
                .setMaxStreams(1)
                .build();

        soundPool.setOnLoadCompleteListener((soundPool, sampleId, status) -> loaded = true);
        soundIdBouton = soundPool.load(this, R.raw.son_bouton, 1);
    }

    private void playSound() {
        if (loaded && soundPool != null) {
            soundPool.play(soundIdBouton, volume, volume, 1, 0, 1f);
        }
    }

    // Méthodes de clic
    public void onClickOutils(View view) {
        playSound();
        openActivtity_outils();
    }

    public void onClickCours(View view) {
        playSound();
        openActivtity_cours();
    }

    public void onClickAchat(View view) {
        playSound();
        openActivtity_achat();
    }

    public void onClickContacter(View view) {
        playSound();
        openActivtity_contacter();
    }

    public void onClickParametre(View view) {
        playSound();
        openActivtity_parametre();
    }

    // Navigation vers d'autres pages
    public void openActivtity_outils() {
        startActivity(new Intent(this, Page_Outils.class));
    }

    public void openActivtity_cours() {
        startActivity(new Intent(this, Cours_Main.class));
    }

    public void openActivtity_achat() {
        startActivity(new Intent(this, Achat_Main.class));
    }

    public void openActivtity_contacter() {
        startActivity(new Intent(this, Page_Contact.class));
    }

    public void openActivtity_parametre() {
        startActivity(new Intent(this, Page_Parametre.class));
    }

    public void openFavoris() {
        Intent intent = new Intent(this, Page_Favoris.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }

    public void openNotification() {
        Intent intent = new Intent(this, Page_Notification.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }
}


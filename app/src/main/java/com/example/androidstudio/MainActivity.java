package com.example.androidstudio;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.androidstudio.achat.Achat_Main;
import com.example.androidstudio.pages.Page_Contact;
import com.example.androidstudio.pages.Page_Cours;
import com.example.androidstudio.pages.Page_Favoris;
import com.example.androidstudio.pages.Page_Notification;
import com.example.androidstudio.pages.Page_Outils;
import com.example.androidstudio.pages.Page_Parametre;
import com.google.android.material.bottomnavigation.BottomNavigationView;
//import com.google.firebase.messaging.FirebaseMessagingService;
//import com.google.firebase.messaging.RemoteMessage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity {

    private Button buttonOutils, buttonCours, buttonAchat, buttonContact;
    private ImageButton buttonParametre, imageOutils, imageCours, imageAchat, imageContact;
    private SoundPool soundPool;
    private AudioManager audioManager;
    // Maximumn sound stream.
    private static final int MAX_STREAMS = 100;
    // Stream type.
    private static final int streamType = AudioManager.STREAM_MUSIC;
    private boolean loaded;
    private int soundIdBouton;
    private float volume;


    // Pour gérer les fragment (dasboard, home, notif) -------------------------------------------------------N
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        buttonOutils = findViewById(R.id.buttonOutils);
        buttonCours = findViewById(R.id.buttonCours);
        buttonAchat = findViewById(R.id.buttonAchat);
        buttonContact = findViewById(R.id.buttonContacter);
        imageOutils = findViewById(R.id.imageOutils);
        imageOutils = findViewById(R.id.imageCours);
        imageOutils = findViewById(R.id.imageAchat);
        imageOutils = findViewById(R.id.imageContacter);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.nav_view);

        // cacher la barre du haut
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
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




    // Pour gérer la navigation avec les fragments (dasboard, home, notif) -----------------------------------------------

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.navigation_home:
                                break;
                            case R.id.navigation_dashboard:
                                openFavoris();
                                break;
                            case R.id.navigation_notifications:
                                openNotification();
                                break;
                        }
                        //getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_container,selectedFragment);
                        return false;
                    }
                });
        // Pour faire fonctionner les boutons et les imageBoutons -----------------------------------------------
}
    public void onClickOutils (View view){
        playSound();
        openActivtity_outils();
    }
    public void onClickCours (View view){
        playSound();
        openActivtity_cours();
    }
    public void onClickAchat (View view){
        playSound();
        openActivtity_achat();
    }
    public void onClickContacter (View view){
        playSound();
        openActivtity_contacter();
    }
    public void onClickParametre (View view){
        playSound();
        openActivtity_parametre();
    }

    public void openActivtity_outils(){
        Intent intent = new Intent(this, Page_Outils.class);
        startActivity(intent);
    }
    public void openActivtity_cours(){
        Intent intent = new Intent(this, Page_Cours.class);
        startActivity(intent);
    }
    public void openActivtity_achat(){
        Intent intent = new Intent(this, Achat_Main.class);
        startActivity(intent);
    }
    public void openActivtity_contacter(){
        Intent intent = new Intent(this, Page_Contact.class);
        startActivity(intent);
    }
    public void openActivtity_parametre() {
        Intent intent = new Intent(this, Page_Parametre.class);
        startActivity(intent);
    }
    public void openFavoris() {
        finish();
        this.startActivity(new Intent(this, Page_Favoris.class));
        this.overridePendingTransition(0, 0);
    }
    public void openNotification(){
        finish();
        this.startActivity(new Intent(this, Page_Notification.class));
        this.overridePendingTransition(0, 0);
    }
    public void playSound( )  {
        if(loaded)  {
            float leftVolumn = volume;
            float rightVolumn = volume;
            // Play sound of gunfire. Returns the ID of the new stream.
            int streamId = this.soundPool.play(this.soundIdBouton,leftVolumn, rightVolumn, 1, 0, 1f);
        }
    }

    }


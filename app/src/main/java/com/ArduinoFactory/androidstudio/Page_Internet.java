package com.ArduinoFactory.androidstudio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

public class Page_Internet extends AppCompatActivity {

    private WebView webview;
    private ProgressBar progressBar;
    private String url;

    private SoundPool soundPool;
    private int soundIdBouton;
    private boolean soundLoaded = false;
    private float volume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page__internet);

        // Initialisation du son
        initSound();

        // Initialisation de la ProgressBar
        progressBar = findViewById(R.id.progressBar);

        // Initialisation de la WebView
        webview = findViewById(R.id.webview);
        webview.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webview.setWebViewClient(new MyWebClient());

        // Lecture des extras
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null) {
            Bundle data = intent.getExtras();
            if (data.containsKey("url_achat")) url = data.getString("url_achat");
            else if (data.containsKey("af")) url = data.getString("af");
            else if (data.containsKey("li")) url = data.getString("li");
            else if (data.containsKey("yt")) url = data.getString("yt");
            else if (data.containsKey("it")) url = data.getString("it");
        }

        // Chargement de l'URL
        if (url != null) {
            webview.loadUrl(url);
            progressBar.setVisibility(View.VISIBLE);
        } else {
            Toast.makeText(this, "Erreur : URL manquante", Toast.LENGTH_LONG).show();
        }
    }

    // Gestion bouton retour dans WebView
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webview.canGoBack()) {
            webview.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private class MyWebClient extends WebViewClient {

        // API 21+ (moderne)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            playSound();
            view.loadUrl(request.getUrl().toString());
            return true;
        }

        // Pour compatibilité Android < API 24
        @SuppressWarnings("deprecation")
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            playSound();
            view.loadUrl(url);
            return true;
        }

        // Quand une page est chargée
        @Override
        public void onPageFinished(WebView view, String url) {
            progressBar.setVisibility(View.GONE);
        }

        // Gestion des erreurs modernes (API 23+)
        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            String description = error.getDescription().toString();
            Log.e("WebViewError", description);
            Toast.makeText(Page_Internet.this, "Erreur : " + description, Toast.LENGTH_LONG).show();
        }

        // Gestion des erreurs pour anciens appareils
        @SuppressWarnings("deprecation")
        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            Log.e("WebViewError", description);
            Toast.makeText(Page_Internet.this, "Erreur : " + description, Toast.LENGTH_LONG).show();
        }
    }

    // Initialisation du SoundPool
    private void initSound() {
        AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        float currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        float maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        volume = currentVolume / maxVolume;

        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        AudioAttributes audioAttrib = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();

        soundPool = new SoundPool.Builder()
                .setMaxStreams(1)
                .setAudioAttributes(audioAttrib)
                .build();

        soundPool.setOnLoadCompleteListener((soundPool, sampleId, status) -> soundLoaded = true);
        soundIdBouton = soundPool.load(this, R.raw.son_bouton, 1); // Assure-toi que ce fichier est bien présent
    }

    private void playSound() {
        if (soundLoaded && soundPool != null) {
            soundPool.play(soundIdBouton, volume, volume, 1, 0, 1f);
        }
    }
}

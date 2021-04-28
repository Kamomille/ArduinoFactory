package com.example.androidstudio;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.androidstudio.pages.Page_Contact;
import com.example.androidstudio.pages.Page_Outils;


public class Page_Internet extends AppCompatActivity {
    // Progressedialog permet d'afficher un texte : chargement de page avant que l'a page s'ouvre et webview module pour l'afficher
    private ProgressDialog progressDialog;
    private WebView webview;
    // On déclare url qui correspond a l'adresse que l'on va rechercher sur internet
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page__internet);

        webview=(WebView) findViewById(R.id.webview);
        WebSettings settings =webview.getSettings();
        settings.setJavaScriptEnabled(true);
        webview.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        progressDialog = ProgressDialog.show(Page_Internet.this,"Arduino Factory","Chargement...");
        webview.setWebViewClient(new Page_Internet.MyWebClient());
        Intent intent = this.getIntent();
        if (intent != null) {
            Bundle data = getIntent().getExtras();

            // Ici on récupère le bon url suivant si on a cliqué sur bouton poussoir grâce aux mots clé associé à l'url
            if (data.containsKey("url_achat")) {
                url = data.getString("url_achat");
            }
            if (data.containsKey("af")) {
                url = data.getString("af");
            }
            if (data.containsKey("li")) {
                url = data.getString("li");
            }
            if (data.containsKey("yt")) {
                url = data.getString("yt");
            }
            if (data.containsKey("it")) {
                url = data.getString("it");
            }

            webview.loadUrl(url);
        }
    }
    // permet de retourner à la page précédent quand on cliqu" sur retour sur le téléphone
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webview.canGoBack()) {
            webview.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    // Permet d'afficher la page avec le bon url.
    public class MyWebClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
        @Override
        // permet d'afficher le message : page en chargement puis de l'enlever quand la page est chargé.
        public void onPageFinished(WebView view, String url) {
            //  Log.i(TAG, "Finished loading URL: " +url);
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }
        @Override
        // permet d'affiche s'il y a une erreur
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            Log.e("Myapp", "Error: " + description);
            Toast.makeText(getApplicationContext(), "Erreur: "+description, Toast.LENGTH_LONG).show();
        }
    }

}
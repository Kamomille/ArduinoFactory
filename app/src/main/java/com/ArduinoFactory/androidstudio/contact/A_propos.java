package com.ArduinoFactory.androidstudio.contact;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.TextView;

import com.ArduinoFactory.androidstudio.page_accessoire.Page_Internet;
import com.ArduinoFactory.androidstudio.R;

public class A_propos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_propos);

        // bouton retour
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);

        // ===== LIVRE =====
        TextView livreFr = findViewById(R.id.lienduLivre);

        livreFr.setOnClickListener(v -> openUrl(getString(R.string.lien_livre)));

        // ===== JOIN US =====
        TextView lien = findViewById(R.id.lien);
        TextView youtube = findViewById(R.id.youtube);
        TextView instagram = findViewById(R.id.instragram);

        TextView rejoignez = findViewById(R.id.rejoignez);
        rejoignez.setPaintFlags(rejoignez.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        lien.setOnClickListener(v -> openUrl(getString(R.string.lien_url)));
        youtube.setOnClickListener(v -> openUrl(getString(R.string.lien_youtube)));
        instagram.setOnClickListener(v -> openUrl(getString(R.string.lien_instagram)));
    }

    private void openUrl(String url) {
        Intent intent = new Intent(this, Page_Internet.class);
        intent.putExtra("url", url);
        startActivity(intent);
    }
}

package com.ArduinoFactory.androidstudio.contact;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.TextView;

import com.ArduinoFactory.androidstudio.Page_Internet;
import com.ArduinoFactory.androidstudio.R;

public class A_propos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_propos);

        // bouton retour
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        TextView qui = findViewById(R.id.qui);
        qui.setPaintFlags(qui.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        TextView remerciement = findViewById(R.id.remerciement);
        remerciement.setPaintFlags(remerciement.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        TextView nom1 = findViewById(R.id.nom1);
        nom1.setText(getString(R.string.nom1));

        TextView nom2 = findViewById(R.id.nom2);
        nom2.setText(getString(R.string.nom2));

        TextView nom3 = findViewById(R.id.nom3);
        nom3.setText(getString(R.string.nom3));

        TextView nom4 = findViewById(R.id.nom4);
        nom4.setText(getString(R.string.nom4));

        TextView rejoignez = findViewById(R.id.rejoignez);
        rejoignez.setPaintFlags(rejoignez.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        TextView lien = findViewById(R.id.lien);
        lien.setPaintFlags(lien.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        lien.setOnClickListener(v -> openlien());

        TextView youtube = findViewById(R.id.youtube);
        youtube.setPaintFlags(youtube.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        youtube.setOnClickListener(v -> openyoutube());

        TextView instagram = findViewById(R.id.instragram);
        instagram.setPaintFlags(instagram.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        instagram.setOnClickListener(v -> openinstagram());
    }

    public void openlien() {
        String lien_site = getString(R.string.lien_url);
        Intent lien_intent = new Intent(this, Page_Internet.class);
        lien_intent.putExtra("li", lien_site);
        startActivity(lien_intent);
    }

    public void openyoutube() {
        String lien_youtube = getString(R.string.lien_youtube);
        Intent youtube_intent = new Intent(this, Page_Internet.class);
        youtube_intent.putExtra("yt", lien_youtube);
        startActivity(youtube_intent);
    }

    public void openinstagram() {
        String lien_instagram = getString(R.string.lien_instagram);
        Intent instagram_intent = new Intent(this, Page_Internet.class);
        instagram_intent.putExtra("it", lien_instagram);
        startActivity(instagram_intent);
    }
}

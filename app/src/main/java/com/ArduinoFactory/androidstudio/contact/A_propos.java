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
        lien.setOnClickListener(v -> openUrl(getString(R.string.lien_url)));

        TextView youtube = findViewById(R.id.youtube);
        youtube.setPaintFlags(youtube.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        youtube.setOnClickListener(v -> openUrl(getString(R.string.lien_youtube)));

        TextView instagram = findViewById(R.id.instragram);
        instagram.setPaintFlags(instagram.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        instagram.setOnClickListener(v -> openUrl(getString(R.string.lien_instagram)));
    }

    private void openUrl(String url) {
        Intent intent = new Intent(this, Page_Internet.class);
        intent.putExtra("url", url);
        startActivity(intent);
    }
}

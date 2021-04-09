package com.example.androidstudio.contact;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.androidstudio.Page_Internet;
import com.example.androidstudio.R;
import com.example.androidstudio.pages.Page_Contact;

public class A_propos extends AppCompatActivity {

    private TextView lien;
    String lien_url ="https://arduinofactory.fr/";
    String youtube_url ="https://www.youtube.com/channel/UCXKbpmuVZV6h8B0Frr0dtxA";
    String instagram_url ="https://www.instagram.com/arduino.factory/?hl=fr";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_propos);

        // Permet d'avoir une fleche retour en haut
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        TextView qui = (TextView) findViewById(R.id.qui);
        qui.setPaintFlags(qui.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        TextView remerciement = (TextView) findViewById(R.id.remerciement);
        remerciement.setPaintFlags(remerciement.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        TextView nom1 = (TextView) findViewById(R.id.nom1);
        nom1.setText("\u2022 Camille Bayon de Noyer");
        TextView nom2 = (TextView) findViewById(R.id.nom2);
        nom2.setText("\u2022 Cedric Chhunon");
        TextView nom3 = (TextView) findViewById(R.id.nom3);
        nom3.setText("\u2022 Pierre Huruguen");
        TextView nom4 = (TextView) findViewById(R.id.nom4);
        nom4.setText("\u2022 Julien Gouban");
        TextView rejoignez = (TextView) findViewById(R.id.rejoignez);
        rejoignez.setPaintFlags(rejoignez.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        TextView lien = (TextView) findViewById(R.id.lien);
        lien.setPaintFlags(lien.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        lien.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openlien();
            }
        }));
        TextView youtube = (TextView) findViewById(R.id.youtube);
        youtube.setPaintFlags(youtube.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        youtube.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openyoutube();
            }
        }));
        TextView instagram = (TextView) findViewById(R.id.instragram);
        instagram.setPaintFlags(youtube.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        instagram.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openinstagram();
            }
        }));
    }
    public void openlien(){
        Intent lien_intent= new Intent(this, Page_Internet.class);
        lien_intent.putExtra("li",lien_url);
        startActivity(lien_intent);

    }
    public void openyoutube(){
        Intent youtube_intent= new Intent(this,Page_Internet.class);
        youtube_intent.putExtra("yt",youtube_url);
        startActivity(youtube_intent);

    }
    public void openinstagram(){
        Intent instagram_intent= new Intent(this,Page_Internet.class);
        instagram_intent.putExtra("it",instagram_url);
        startActivity(instagram_intent);

    }

    // Permet de retourner à la page contact
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), Page_Contact.class);
        startActivityForResult(myIntent, 0);
        return true;
    }

}
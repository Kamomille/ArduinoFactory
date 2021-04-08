package com.example.androidstudio.pages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.androidstudio.Page_Internet;
import com.example.androidstudio.R;
import com.example.androidstudio.contact.A_propos;
import com.example.androidstudio.contact.Contactez_nous;

public class Page_Contact extends AppCompatActivity {

    private LinearLayout buttonSite, buttonContactezNous, buttonPropos;

    String Arduino_Factory_url="https://arduinofactory.fr/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page__contact);


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
        Intent intent = new Intent(this, Page_Internet.class);
        intent.putExtra("af",Arduino_Factory_url);
        startActivity(intent);
    }
    public void openActivtity_contactezNous(){
        Intent intent = new Intent(this, Contactez_nous.class);
        startActivity(intent);
    }
    public void openActivtity_propos(){
        Intent intent = new Intent(this, A_propos.class);
        startActivity(intent);
    }
}
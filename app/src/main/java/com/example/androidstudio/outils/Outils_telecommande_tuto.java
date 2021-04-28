package com.example.androidstudio.outils;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.example.androidstudio.Page_Internet;
import com.example.androidstudio.R;

public class Outils_telecommande_tuto extends AppCompatActivity {

    private Button button_achat, button_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outils_telecommande_tuto);

        button_achat= findViewById(R.id.button_achat);
        button_achat.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) { click_boutonAchat(); }});

        button_code= findViewById(R.id.button_code);
        button_code.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) { click_boutonCode(); }}));
    }

    public void click_boutonAchat(){
        Intent intent_achat= new Intent(this, Page_Internet.class);
        intent_achat.putExtra("url_achat","https://amzn.to/3tKZCI9");
        startActivity(intent_achat);
    }
    public void click_boutonCode(){
        Intent intent_achat= new Intent(this, Page_Internet.class);
        intent_achat.putExtra("url_achat","https://arduinofactory.fr/code-telecommande-bluetooth/");
        startActivity(intent_achat);
    }


}
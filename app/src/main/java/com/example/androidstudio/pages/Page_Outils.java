package com.example.androidstudio.pages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.androidstudio.outils.Outils_resistance;
import com.example.androidstudio.outils.Outils_telecommande;
import com.example.androidstudio.R;

public class Page_Outils extends AppCompatActivity {

    private LinearLayout outil_resistance, outil_telecommande;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page__outils);

        outil_resistance = (LinearLayout) findViewById(R.id.outil_resistance);
        outil_resistance.setOnClickListener(new View.OnClickListener(){
            @Override public void onClick(View v){ openActivtity_outilsResistance(); } } );

        outil_telecommande = (LinearLayout) findViewById(R.id.outil_telecommande);
        outil_telecommande.setOnClickListener(new View.OnClickListener(){
            @Override public void onClick(View v){ openActivtity_outilsTelecommande(); } } );
    }

    public void openActivtity_outilsResistance(){
        Intent intent = new Intent(this, Outils_resistance.class);
        startActivity(intent);
    }
    public void openActivtity_outilsTelecommande(){
        Intent intent = new Intent(this, Outils_telecommande.class);
        startActivity(intent);
    }

}

package com.example.androidstudio.outils;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.androidstudio.MainActivity;
import com.example.androidstudio.R;

public class Outils_telecommande_Bluetooth extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outils_telecommande__bluetooth);
        setContentView(R.layout.activity_outils_telecommande);

        // Permet d'avoir une fleche retour en haut --------------------------------------------
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    // Permet de retourner à la page outils ------------------------------------------------
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), Page_Outils.class);
        startActivityForResult(myIntent, 0);
        return true;
    }
}
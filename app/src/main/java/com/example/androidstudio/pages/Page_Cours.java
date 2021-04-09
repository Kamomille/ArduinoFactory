package com.example.androidstudio.pages;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.androidstudio.MainActivity;
import com.example.androidstudio.R;

public class Page_Cours extends AppCompatActivity {
    Button button;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;
    Button button9;
    Button button10;
    LinearLayout layout;

    View stock [] = new View[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page__menu_cours);

        // Permet d'avoir une fleche retour en haut
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button3= findViewById(R.id.button3);
        button4= findViewById(R.id.button4);
        button5= findViewById(R.id.button5);
        button6= findViewById(R.id.button6);
        button7= findViewById(R.id.button7);
        button8= findViewById(R.id.button8);
        button9= findViewById(R.id.button9);
        button10= findViewById(R.id.button10);
        layout = findViewById(R.id.layout);
        View layout2= getLayoutInflater().inflate(R.layout.activity_page__menu_cours_row_add,null, false);
        int index = layout.indexOfChild(button);
        layout.addView(layout2,index+1);
        stock[0]=button;
        stock[1]=button;
    }
    public void onClick(View view){


        View layout2= getLayoutInflater().inflate(R.layout.activity_page__menu_cours_row_add,null, false);
        int index = layout.indexOfChild(view);

        if (stock[0]==view && stock[0]!=stock[1]){
            layout.removeViewAt(index+1);
        }
        else{

            layout.addView(layout2,index+1);
            int indexstock = layout.indexOfChild(stock[0]);
            layout.removeViewAt(indexstock+1);

        }
        stock[1]=stock[0];
        stock[0]=view;


    }

    // Permet de retourner à la page menu
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }
}
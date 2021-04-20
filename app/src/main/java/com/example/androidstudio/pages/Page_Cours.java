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

    View stock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page__menu_cours);

        // bouton retour
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

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
        stock=button;
    }
    public void onClick(View view){


        View layout2= getLayoutInflater().inflate(R.layout.activity_page__menu_cours_row_add,null, false);
        int index = layout.indexOfChild(view);

        if(stock==null){
            layout.addView(layout2,index+1);
            stock=view;
        }
        else if (stock==view) {
            layout.removeViewAt(index + 1);
            stock = null;
        }
        else {
            layout.addView(layout2,index+1);
            int indexstock = layout.indexOfChild(stock);
            layout.removeViewAt(indexstock+1);
            stock=view;
        }



    }

    public void onClick_cours(View view){

    }
}
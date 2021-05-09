package com.example.androidstudio.favoris;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.androidstudio.MainActivity;
import com.example.androidstudio.Page_Internet;
import com.example.androidstudio.R;
import com.example.androidstudio.achat.Achat_Data;
import com.example.androidstudio.achat.Achat_Main;
import com.example.androidstudio.achat.RecyclerItemClickListener;
import com.example.androidstudio.achat.RecyclerView_Adapter;
import com.example.androidstudio.outils.Outils_resistance;
import com.example.androidstudio.pages.Page_Notification;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Page_Favoris extends AppCompatActivity {
    private Button Favoris;
    private TextView Texteview_Favoris;


    private RecyclerView recyclerView;

    private Favoris_RecyclerViewAdapter adapter;
    private ArrayList<Favoris_Data> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page__favoris);


        Texteview_Favoris = (TextView) findViewById(R.id.textView_Favoris);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.nav_view);
        bottomNavigationView.getMenu().getItem(1).setChecked(true);


        // ========================================================================================================================
        //                              recycler view
        // ========================================================================================================================


        recyclerView = findViewById(R.id.recyclerView);
        buildRecyclerView();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        onClick(view, position);
                    }
                    @Override public void onLongItemClick(View view, int position) {
                    }
                }));


        // ========================================================================================================================
        //
        // ========================================================================================================================



        SharedPreferences prefs = getSharedPreferences("coeur", MODE_PRIVATE);
        String name = prefs.getString("coeur_resistance", "No name defined");

        Texteview_Favoris.setText(name);



        // ========================================================================================================================
        //                       Gestion barre de navigation
        // ========================================================================================================================


        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_home:
                                openActivitity_MainActivity();
                                break;
                            case R.id.navigation_dashboard:

                                break;
                            case R.id.navigation_notifications:
                                openActivitity_Notification();
                                break;
                        }
                        return false;
                    }
                });

    }
    // Permet de retourner à la page menu
    public void openActivitity_MainActivity(){
        finish();
        this.startActivity(new Intent(this, MainActivity.class));
        this.overridePendingTransition(0, 0);
    }
    public void openActivitity_Notification(){
        finish();
        this.startActivity(new Intent(this, Page_Notification.class));
        this.overridePendingTransition(0, 0);
    }


    // ========================================================================================================================
    //                                      recycler view
    // ========================================================================================================================


    private void buildRecyclerView() {

        data = new ArrayList<>();

        SharedPreferences prefs = getSharedPreferences("coeur", MODE_PRIVATE);
        String coeur = prefs.getString("coeur_resistance", "Pas de favoris défini");
        if (coeur.equals("plein")){
            data.add(new Favoris_Data("Outils resistance",   R.drawable.outils_menu_resistance,  "Outils_resistance"));
        }


        //data.add(new Favoris_Data("Outils télécommande", R.drawable.outils_menu_telecommande,"https://amzn.to/3typI0J"));

        adapter = new Favoris_RecyclerViewAdapter(data, Page_Favoris.this);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(manager);

        recyclerView.setAdapter(adapter);
    }

    public void onClick(View v, int position){
        this.startActivity(new Intent(this, Outils_resistance.class));
    }
}
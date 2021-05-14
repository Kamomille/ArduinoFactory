package com.example.androidstudio.favoris;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.androidstudio.MainActivity;
import com.example.androidstudio.R;
import com.example.androidstudio.achat.RecyclerItemClickListener;
import com.example.androidstudio.outils.Outils_resistance;
import com.example.androidstudio.nouveauté.Page_Notification;
import com.example.androidstudio.outils.Outils_telecommande;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Page_Favoris extends AppCompatActivity {
    private TextView Texteview_Favoris;

    private RecyclerView recyclerView;
    private Favoris_RecyclerViewAdapter adapter;
    private ArrayList<Favoris_Data> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page__favoris);

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

        SharedPreferences prefs1 = getSharedPreferences("coeur_resistance", MODE_PRIVATE);
        SharedPreferences prefs2 = getSharedPreferences("coeur_telecommande", MODE_PRIVATE);
        String coeur_resistance = prefs1.getString("coeur_resistance", "Pas de favoris défini");
        String coeur_telecommande = prefs2.getString("coeur_telecommande", "Pas de favoris défini");

        if (coeur_resistance.equals("plein")){
            data.add(new Favoris_Data("Outils resistance",   R.drawable.outils_menu_resistance, "Outils_resistance"));
        }
        if (coeur_telecommande.equals("plein")){
            data.add(new Favoris_Data("Outils télécommande",   R.drawable.outils_menu_telecommande, "Outils_telecommande"));
        }

        adapter = new Favoris_RecyclerViewAdapter(data, Page_Favoris.this);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    public void onClick(View v, int position){
        String name_class = data.get(position).getNameClass();

        if (name_class.equals("Outils_resistance")){
            this.startActivity(new Intent(this, Outils_resistance.class));
        }
        if (name_class.equals("Outils_telecommande")){
            this.startActivity(new Intent(this, Outils_telecommande.class));
        }

    }
}
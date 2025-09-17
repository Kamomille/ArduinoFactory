package com.ArduinoFactory.androidstudio.nouveaute;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ArduinoFactory.androidstudio.MainActivity;
import com.ArduinoFactory.androidstudio.R;
import com.ArduinoFactory.androidstudio.achat.RecyclerItemClickListener;
import com.ArduinoFactory.androidstudio.favoris.Favoris_Data;
import com.ArduinoFactory.androidstudio.favoris.Favoris_RecyclerViewAdapter;
import com.ArduinoFactory.androidstudio.favoris.Page_Favoris;
import com.ArduinoFactory.androidstudio.outils.Outils_resistance;
import com.ArduinoFactory.androidstudio.outils.Outils_telecommande;
import com.ArduinoFactory.androidstudio.cours.Page_Menu_Cours;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Page_Notification extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Favoris_Data> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page__notification);

        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.getMenu().getItem(2).setChecked(true);

        // RecyclerView setup
        recyclerView = findViewById(R.id.recyclerView);
        buildRecyclerView();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        onClick(position);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        // Non utilisé
                    }
                })
        );

        // Gestion de la barre de navigation
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.navigation_home) {
                openActivitity_MainActivity();
                return true;
            } else if (item.getItemId() == R.id.navigation_dashboard) {
                openActivitity_Favoris();
                return true;
            }
            return false;
        });
    }

    public void openActivitity_MainActivity() {
        finish();
        Intent intent = new Intent(this, MainActivity.class);
        ActivityOptions options = ActivityOptions.makeCustomAnimation(this, 0, 0);
        startActivity(intent, options.toBundle());
    }

    public void openActivitity_Favoris() {
        finish();
        Intent intent = new Intent(this, Page_Favoris.class);
        ActivityOptions options = ActivityOptions.makeCustomAnimation(this, 0, 0);
        startActivity(intent, options.toBundle());
    }

    private void buildRecyclerView() {
        data = new ArrayList<>();

        data.add(new Favoris_Data(getString(R.string.outil_resistance), R.drawable.outils_menu_resistance, "Outils_resistance"));
        data.add(new Favoris_Data(getString(R.string.outil_telecommande), R.drawable.outils_menu_telecommande, "Outils_telecommande"));
        data.add(new Favoris_Data(getString(R.string.cours), R.drawable.livres, "page_accueil_cours"));

        Favoris_RecyclerViewAdapter adapter = new Favoris_RecyclerViewAdapter(data);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    public void onClick(int position) {
        String name_class = data.get(position).getNameClass();

        switch (name_class) {
            case "Outils_resistance":
                startActivity(new Intent(this, Outils_resistance.class));
                break;
            case "Outils_telecommande":
                startActivity(new Intent(this, Outils_telecommande.class));
                break;
            case "page_accueil_cours":
                startActivity(new Intent(this, Page_Menu_Cours.class));
                break;
        }
    }
}

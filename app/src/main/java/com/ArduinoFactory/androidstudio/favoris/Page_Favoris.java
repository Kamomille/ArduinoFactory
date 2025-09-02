package com.ArduinoFactory.androidstudio.favoris;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.ArduinoFactory.androidstudio.MainActivity;
import com.ArduinoFactory.androidstudio.R;
import com.ArduinoFactory.androidstudio.achat.RecyclerItemClickListener;
import com.ArduinoFactory.androidstudio.outils.Outils_resistance;
import com.ArduinoFactory.androidstudio.nouveauté.Page_Notification;
import com.ArduinoFactory.androidstudio.outils.Outils_telecommande;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Page_Favoris extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Favoris_RecyclerViewAdapter adapter;
    private ArrayList<Favoris_Data> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page__favoris);

        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.getMenu().getItem(1).setChecked(true);

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
                        // Rien à faire ici
                    }
                })
        );

        // ✅ Remplace setOnItemSelectedListener par la bonne méthode
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int id = item.getItemId(); // ✅ Cette méthode existe bien ici
            if (id == R.id.navigation_home) {
                openActivitity_MainActivity();
                return true;
            } else if (id == R.id.navigation_notifications) {
                openActivitity_Notification();
                return true;
            }
            return false;
        });
    }

    public void openActivitity_MainActivity() {
        finish();
        startActivity(new Intent(this, MainActivity.class));

    }

    public void openActivitity_Notification() {
        finish();
        startActivity(new Intent(this, Page_Notification.class));

    }

    private void buildRecyclerView() {
        data = new ArrayList<>();

        SharedPreferences prefs1 = getSharedPreferences("coeur_resistance", MODE_PRIVATE);
        SharedPreferences prefs2 = getSharedPreferences("coeur_telecommande", MODE_PRIVATE);

        String coeur_resistance = prefs1.getString("coeur_resistance", "Pas de favoris défini");
        String coeur_telecommande = prefs2.getString("coeur_telecommande", "Pas de favoris défini");

        if (coeur_resistance.equals("plein")) {
            data.add(new Favoris_Data("Outils resistance", R.drawable.outils_menu_resistance, "Outils_resistance"));
        }
        if (coeur_telecommande.equals("plein")) {
            data.add(new Favoris_Data("Outils télécommande", R.drawable.outils_menu_telecommande, "Outils_telecommande"));
        }

        adapter = new Favoris_RecyclerViewAdapter(data, this);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    public void onClick(int position) {
        String name_class = data.get(position).getNameClass();

        if (name_class.equals("Outils_resistance")) {
            startActivity(new Intent(this, Outils_resistance.class));
        } else if (name_class.equals("Outils_telecommande")) {
            startActivity(new Intent(this, Outils_telecommande.class));
        }
    }
}

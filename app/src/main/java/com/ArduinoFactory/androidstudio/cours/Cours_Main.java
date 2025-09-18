package com.ArduinoFactory.androidstudio.cours;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ArduinoFactory.androidstudio.R;

import java.util.ArrayList;

public class Cours_Main extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CoursAdapter adapter;
    private ArrayList<CoursData> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page__cours);

        // Affiche le bouton retour dans la barre d’action
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recyclerViewCours);

        // Grille 2 colonnes
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        /*
        // Ajoute un petit espacement uniforme entre les items
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.recycler_spacing);
        recyclerView.addItemDecoration(new SpacingItemDecoration(2, spacingInPixels, true));
        */
        buildData();
        adapter = new CoursAdapter(data, this::openCoursDetail);
        recyclerView.setAdapter(adapter);
    }

    private void buildData() {
        data = new ArrayList<>();
        // Ajouter ici tes composants et leurs images drawable
        data.add(new CoursData("Bouton poussoir", R.drawable.achat_bouton));
        data.add(new CoursData("Télémétrie sur Arduino", R.drawable.achat_distance));
        data.add(new CoursData("Moteur Courant Continu", R.drawable.achat_moteur_dc));
        // ... continue selon tes composants
    }

    private void openCoursDetail(CoursData item) {
        Intent intent = new Intent(this, Cours_Detail.class);
        intent.putExtra("page", item.getName());
        startActivity(intent);
    }
}

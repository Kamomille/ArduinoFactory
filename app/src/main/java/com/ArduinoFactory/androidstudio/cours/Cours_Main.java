package com.ArduinoFactory.androidstudio.cours;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ArduinoFactory.androidstudio.R;
import com.ArduinoFactory.androidstudio.achat.Achat_Data;

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
        data.add(new CoursData("Carte Arduino", R.drawable.achat_carte_arduino));
        data.add(new CoursData("Kit de démarrage", R.drawable.achat_kit));
        data.add(new CoursData("Arduino IDE", R.drawable.arduino_ide));
        data.add(new CoursData("Schema Arduino", R.drawable.schema_arduino));
        data.add(new CoursData("Librairie", R.drawable.librairie_arduino));
        data.add(new CoursData("La LED", R.drawable.achat_led));
        data.add(new CoursData("Resistance", R.drawable.resistance));
        data.add(new CoursData("Breadboard", R.drawable.breadboard_image));
        data.add(new CoursData("Bouton poussoir", R.drawable.achat_bouton));
        data.add(new CoursData("Capteur distance", R.drawable.achat_distance));
        data.add(new CoursData("Moteur courant continu", R.drawable.achat_moteur_dc));
        data.add(new CoursData("Moteur pas à pas", R.drawable.moteur_pas));
        data.add(new CoursData("Le Joystick", R.drawable.joystick));
        data.add(new CoursData("Ecran LCD", R.drawable.ecran_lcd));
        data.add(new CoursData("Buzzer", R.drawable.achat_buzzer));
        data.add(new CoursData("Module RTC", R.drawable.module_rtc));
        // ... continue selon tes composants
    }

    private void openCoursDetail(CoursData item) {
        Intent intent = new Intent(this, Cours_Detail.class);
        intent.putExtra("page", item.getName());
        startActivity(intent);
    }
}

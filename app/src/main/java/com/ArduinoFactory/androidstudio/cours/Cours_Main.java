package com.ArduinoFactory.androidstudio.cours;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ArduinoFactory.androidstudio.R;
import java.util.ArrayList;

public class Cours_Main extends AppCompatActivity {

    private ArrayList<CoursData> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page__cours);

        // Affiche le bouton retour dans la barre d’action
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewCours);

        // Grille 2 colonnes
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        buildData();
        CoursAdapter adapter = new CoursAdapter(data, this::openCoursDetail);
        recyclerView.setAdapter(adapter);
    }

    private void buildData() {
        data = new ArrayList<>();

// Ajouter ici tes composants et leurs images drawable
        data.add(new CoursData(getString(R.string.carte_arduino_cours), R.drawable.achat_carte_arduino));
        data.add(new CoursData(getString(R.string.kit_demarrage_cours), R.drawable.achat_kit));
        data.add(new CoursData(getString(R.string.arduino_ide_cours), R.drawable.arduino_ide));
        data.add(new CoursData(getString(R.string.schema_arduino_cours), R.drawable.schema_arduino));
        data.add(new CoursData(getString(R.string.librairie_cours), R.drawable.librairie_arduino));
        data.add(new CoursData(getString(R.string.led_cours), R.drawable.achat_led));
        data.add(new CoursData(getString(R.string.resistance_cours), R.drawable.resistance));
        data.add(new CoursData(getString(R.string.breadboard_cours), R.drawable.breadboard_image));
        data.add(new CoursData(getString(R.string.bouton_poussoir_cours), R.drawable.achat_bouton));
        data.add(new CoursData(getString(R.string.capteur_distance_cours), R.drawable.achat_distance));
        data.add(new CoursData(getString(R.string.moteur_dc_cours), R.drawable.achat_moteur_dc));
        data.add(new CoursData(getString(R.string.moteur_pas_cours), R.drawable.moteur_pas));
        data.add(new CoursData(getString(R.string.joystick_cours), R.drawable.joystick));
        data.add(new CoursData(getString(R.string.ecran_lcd_cours), R.drawable.ecran_lcd));
        data.add(new CoursData(getString(R.string.buzzer_cours), R.drawable.achat_buzzer));
        data.add(new CoursData(getString(R.string.module_rtc_cours), R.drawable.module_rtc));
    }

    private void openCoursDetail(CoursData item) {
        Intent intent = new Intent(this, Cours_Detail.class);
        intent.putExtra("page", item.getName());
        startActivity(intent);
    }
}

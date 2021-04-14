
package com.example.androidstudio.pages;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidstudio.MainActivity;
import com.example.androidstudio.R;

import java.util.ArrayList;

import static android.graphics.Color.parseColor;

public class achat_version2 extends AppCompatActivity {
    SearchView searchView;
    ListView listView;
    LinearLayout layout_achat, layout_recherche;

    ArrayList<String> list;
    ArrayAdapter<String > adapter;


    String[][] listeMateriel = {
            {"Kit Elegoo"},
            {"Carte Arduino"},
            {"Capteur distance"},
            {"Bouton poussoir"},
            {"Joystick"},
            {"Capteur de son"},
            {"Buzzer"},
            {"Shock"},
            {"Photorésistance"},
            {"Humidité"},
            {"Motorshield"},
            {"Carte méga"},
            {"Carte nano"},
            {"Carte raspberry"},
            {"Kit rapsberry"},
            {"Machine à souder"},
            {"Etain"},
            {"Kit soudure"},
            {"Dénudeur de fil"},
            {"Moteur dc"},
            {"Servomoteur"},
            {"Led"},
            {"Breadboard"},
            {"Prototype"},
            {"Module wifi"},
            {"Module bluetooth"}
    };

    int[] listeMateriel2 = {
            R.drawable.achat_kit,
            R.drawable.achat_carte_arduino,
            R.drawable.achat_distance,
            R.drawable.achat_bouton,
            R.drawable.achat_joystick,
            R.drawable.achat_capteur_son,
            R.drawable.achat_buzzer,
            R.drawable.achat_shock,
            R.drawable.achat_photoresistance,
            R.drawable.achat_humidite,
            R.drawable.achat_motorshield,
            R.drawable.achat_carte_mega,
            R.drawable.achat_carte_nano,
            R.drawable.achat_raspberry,
            R.drawable.achat_kit_raspberry,
            R.drawable.achat_machine_soudure,
            R.drawable.achat_etain,
            R.drawable.achat_kit_soudure,
            R.drawable.achat_pince_soudure,
            R.drawable.achat_moteur_dc,
            R.drawable.achat_servomoteur,
            R.drawable.achat_led,
            R.drawable.achat_breadboard,
            R.drawable.prototype,
            R.drawable.achat_module_wifi,
            R.drawable.achat_module_bluetooth
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achat_version2);

        // Permet d'avoir une fleche retour en haut
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        searchView = (SearchView) findViewById(R.id.searchView);
        listView = (ListView) findViewById(R.id.list_view);
        layout_achat = (LinearLayout) findViewById(R.id.layout_achat);
        layout_recherche = (LinearLayout) findViewById(R.id.layout_recherche);

        list = new ArrayList<>();
        for (int i = 0; i < listeMateriel.length; i++) { // recuperation des noms des composants
            list.add(listeMateriel[i][0]);
        }


        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);
        listView.setTextFilterEnabled(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //String text = String.valueOf(listeMateriel[position]);
                //searchView.setQueryHint(text);
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(list.contains(query)){
                    adapter.getFilter().filter(query);
                }else{
                    Toast.makeText(achat_version2.this, "No Match found",Toast.LENGTH_LONG).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                if (newText.isEmpty()){
                    layout_recherche.setVisibility(View.INVISIBLE);
                    layout_achat.setVisibility(View.VISIBLE);
                }
                else{
                    affichage_recherche(newText, adapter);
                }
                return false;
            }

        });
    }

    // Permet de retourner à la page menu
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }


    public boolean affichage_recherche (String newText, ArrayAdapter adapter) {
        layout_recherche.setVisibility(View.VISIBLE);
        layout_achat.setVisibility(View.INVISIBLE);

        layout_recherche.removeAllViewsInLayout();

        if (adapter.getCount() == 0){
            TextView texte = new Button(this);
            texte.setText("Aucun résultat");
            texte.setBackgroundColor(parseColor("#F4FCFE"));
            texte.setTextColor(parseColor("#0B789C"));
            layout_recherche.addView(texte);
        }
        else {
            for (int i = 0; i < adapter.getCount(); i++) {

                ImageButton imageButton = new ImageButton(this);
                Drawable drawable = getResources().getDrawable(listeMateriel2[i]);
                imageButton.setImageDrawable(drawable);
                imageButton.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageButton.setBackgroundColor(parseColor("#00000000"));
                layout_recherche.addView(imageButton);

                Button button = new Button(this);
                button.setText(adapter.getItem(i).toString());
                button.setBackgroundColor(parseColor("#0B789C"));
                button.setTextColor(parseColor("#FFFFFF"));
                layout_recherche.addView(button);

            }
        }

        //button.setLayoutParams(new LinearLayout.LayoutParams
         //       (LinearLayout.LayoutParams.WRAP_CONTENT,
         //               LinearLayout.LayoutParams.MATCH_PARENT));


        return true;
    }
}
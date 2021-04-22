
package com.example.androidstudio.CORBEILLE;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidstudio.Page_Internet;
import com.example.androidstudio.R;

import java.util.ArrayList;

import static android.graphics.Color.parseColor;


public class achat_version2 extends AppCompatActivity implements View.OnClickListener {

    SearchView searchView;
    ListView listView;
    LinearLayout layout_achat, layout_recherche;

    ArrayList<String> list;
    ArrayAdapter<String > adapter;

    String[][] listeMateriel = {
            {"Kit Elegoo",      "https://amzn.to/38Rlsl1"},
            {"Carte Arduino",   "https://amzn.to/3typI0J"},
            {"Capteur distance","https://amzn.to/3ty0oI3"},
            {"Bouton poussoir", "https://amzn.to/3cEPaLa"},
            {"Résistance",      "https://amzn.to/38UpVn3"},
            {"Joystick",        "https://amzn.to/3lAU3ZD"},
            {"Capteur de son",  "https://amzn.to/3c1Ur0m"},
            {"Buzzer",          "https://amzn.to/3sa2iyr"},
            {"Shock",           "https://amzn.to/3d7lK8X"},
            {"Photorésistance", "https://amzn.to/3raz1lU"},
            {"Humidité",        "https://amzn.to/2PgFrCD"},
            {"Motorshield",     "https://amzn.to/3bWX3wx"},
            {"Carte méga",      "https://amzn.to/2OL1SjC"},
            {"Carte nano",      "https://amzn.to/30YsU9A"},
            {"Carte raspberry", "https://amzn.to/3907o95"},
            {"Kit rapsberry",   "https://amzn.to/2OVNSUj"},
            {"Machine à souder","https://amzn.to/38TxeLW"},
            {"Etain",           "https://amzn.to/3vJkGjI"},
            {"Kit soudure",     "https://amzn.to/310olvr"},
            {"Dénudeur de fil", "https://amzn.to/3c2NT1C"},
            {"Moteur dc",       "https://amzn.to/3r21pXx"},
            {"Servomoteur",     "https://amzn.to/3lw1l0R"},
            {"Led",             "https://amzn.to/3c2W0Lz"},
            {"Breadboard",      "https://amzn.to/3c1Jdc8"},
            {"Prototype",       "https://amzn.to/38VLPpY"},
            {"Module wifi",     "https://amzn.to/3vDmPNY"},
            {"Module bluetooth","https://amzn.to/3tKZCI9"}
    };

    int[][] listeMateriel2 = {
            {R.drawable.achat_kit,              R.id.Elegoo,          R.id.image_kit_elegoo},
            {R.drawable.achat_carte_arduino,    R.id.carte_arduino,   R.id.Carte_arduino},
            {R.drawable.achat_distance,         R.id.capteur_distance,R.id.image_capteur_distance},
            {R.drawable.achat_bouton,           R.id.bouton_poussoir, R.id.image_bouton_poussoir},
            {R.drawable.achat_resistance,       R.id.resistance,      R.id.image_resistance},
            {R.drawable.achat_joystick,         R.id.joystick,        R.id.Joystick},
            {R.drawable.achat_capteur_son,      R.id.capteur_son,     R.id.Capteur_son},
            {R.drawable.achat_buzzer,           R.id.buzzer,          R.id.Buzzer},
            {R.drawable.achat_shock,            R.id.shock,           R.id.Shock},
            {R.drawable.achat_photoresistance,  R.id.photoresistance, R.id.Photoresistance},
            {R.drawable.achat_humidite,         R.id.humidite,        R.id.Humidite},
            {R.drawable.achat_motorshield,      R.id.Motorshield,     R.id.image_motorshield},
            {R.drawable.achat_carte_mega,       R.id.carte_mega,      R.id.Carte_mega},
            {R.drawable.achat_carte_nano,       R.id.carte_nano,      R.id.Carte_nano},
            {R.drawable.achat_raspberry,        R.id.raspberry,       R.id.Raspberry},
            {R.drawable.achat_kit_raspberry,    R.id.kit_raspberry,   R.id.Kit_raspberry},
            {R.drawable.achat_machine_soudure,  R.id.machine_souder,  R.id.Machine_souder},
            {R.drawable.achat_etain,            R.id.etain,           R.id.Etain},
            {R.drawable.achat_kit_soudure,      R.id.kit_soudure,     R.id.Kit_soudure},
            {R.drawable.achat_pince_soudure,    R.id.denudeur,        R.id.Denudeur},
            {R.drawable.achat_moteur_dc,        R.id.moteur_dc,       R.id.image_moteur_dc},
            {R.drawable.achat_servomoteur,      R.id.servomoteur,     R.id.image_servomoteur},
            {R.drawable.achat_led,              R.id.led,             R.id.image_led},
            {R.drawable.achat_breadboard,       R.id.breadboard,      R.id.Breadboard},
            {R.drawable.prototype,              R.id.prototype,       R.id.Prototype},
            {R.drawable.achat_module_wifi,      R.id.module_wifi,     R.id.Module_wifi  },
            {R.drawable.achat_module_bluetooth, R.id.module_bluetooth,R.id.Module_bluetooth}
    };

    Button listeMateriel_bouton[] = new Button[listeMateriel.length];
    ImageView listeMateriel_imageView[] = new ImageView[listeMateriel.length];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achat_version2);

        // bouton retour
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        searchView = (SearchView) findViewById(R.id.searchView);
        listView = (ListView) findViewById(R.id.list_view);
        layout_achat = (LinearLayout) findViewById(R.id.layout_achat);
        layout_recherche = (LinearLayout) findViewById(R.id.layout_recherche);

        for (int i = 0; i < listeMateriel.length; i++) {
            listeMateriel_bouton[i] = (Button) findViewById(listeMateriel2[i][1]);
            listeMateriel_bouton[i].setOnClickListener(this);
            listeMateriel_imageView[i] = (ImageView) findViewById(listeMateriel2[i][2]);
            listeMateriel_imageView[i].setOnClickListener(this);
        }


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
                String text = adapter.getItem(position).toString();;
                searchView.setQuery(text,false);
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
                String nomComposant = adapter.getItem(i).toString();

                LinearLayout linearLayout = new LinearLayout(this);
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                //LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT, 0);

                for (int j = 0; j < listeMateriel.length; j++) {
                    if (nomComposant.equals(listeMateriel[j][0])){
                        ImageButton imageButton = new ImageButton(this);
                        Drawable drawable = getResources().getDrawable(listeMateriel2[j][0]);
                        imageButton.setImageDrawable(drawable);
                        imageButton.setScaleType(ImageView.ScaleType.FIT_CENTER);
                        imageButton.setBackgroundColor(parseColor("#00000000"));
                        linearLayout.addView(imageButton);
                        break;
                    }
                }
                Button button = new Button(this);
                button.setText(nomComposant);
                button.setBackgroundColor(parseColor("#0B789C"));
                button.setTextColor(parseColor("#FFFFFF"));
                linearLayout.addView(button);

                layout_recherche.addView(linearLayout);

            }
        }
        return true;
    }



    public void openUrlPage(int i){
        Intent intent_achat= new Intent(this, Page_Internet.class);
        intent_achat.putExtra("url_achat",listeMateriel[i][1]);
        startActivity(intent_achat);
    }

    public void onClick(View v) {

        for (int i = 0; i < listeMateriel.length; i++) {
            if(v == findViewById(listeMateriel2[i][1])){ openUrlPage(i); }
            if(v == findViewById(listeMateriel2[i][2])){ openUrlPage(i); }
        }

    }

}











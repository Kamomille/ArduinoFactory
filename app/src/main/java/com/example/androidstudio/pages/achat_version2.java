
package com.example.androidstudio.pages;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.androidstudio.MainActivity;
import com.example.androidstudio.Page_Internet;
import com.example.androidstudio.R;

import java.util.ArrayList;

public class achat_version2 extends AppCompatActivity {

    // Voici la définiton de la barre de recherche avec la liste view qui défile grâce au nom dans String[].
    ListView listView;
    ArrayList<String> list;
    ArrayAdapter adapter;
    String[] version = {"Kit Elegoo","Carte Arduino","Capteur distance","Bouton poussoir","Joystick","Capteur son","Buzzer","Shock",
            "Photoresistance","Humidite","Motorshield","Carte_mega","Carte_nano","Raspberry","Kit_rapsberry","Machine_souder","Etain",
            "Kit_soudure","Denudeur","moteur_dc","servomoteur","resistance","led","Breadboard","Prototype","Module_wifi","Module_bluetooth"};
    SearchView searchView;


    private Button bouton_poussoir,capteur_distance,capteur_son,joystick,humidite; //capteur
    private Button resistance;
    private Button carte_arduino,carte_arduino2,carte_nano,carte_mega,motorshield,raspberry,kit_raspberry; //carte
    private Button moteur_dc,servomoteur; //moteur
    private Button led;
    private Button machine_souder,etain,kit_soudure,denudeur,prototypage; //soudure
    private Button breadboard;
    private Button module_wifi,module_bluetooth; //module
    private Button elegoo;
    private Button buzzer;
    private Button shock;
    private Button photoresistance;

    // Définition de toute les images cliquables
    // Un produit est associé à un bouton et une image cliquable donc j'essayé de garder les mêmes noms avec une majuscule pour l'image cliquable.
    private ImageView Bouton_poussoir, Capteur_distance, Capteur_son, Joystick, Humidite;
    private ImageView Kit_Elegoo;
    private ImageView Motorshield;
    private ImageView Resistance;
    private ImageView Carte_arduino;
    private ImageView Carte_arduino2;
    private ImageView Carte_mega;
    private ImageView Carte_nano;
    private ImageView Moteur_dc;
    private ImageView Servomoteur;
    private ImageView Led;
    private ImageView Machine_souder;
    private ImageView Etain;
    private ImageView Kit_soudure;
    private ImageView Denudeur;
    private ImageView Breadboard;
    private ImageView Prototypage;
    private ImageView Module_wifi;
    private ImageView Module_bluetooth;
    private ImageView Buzzer;
    private ImageView Shock;
    private ImageView Photoresistance;
    private ImageView Raspberry;
    private ImageView Kit_raspberry;

    // Association des lien amazon à une variable String pour chaque produit.
    String bouton_poussoir_url ="https://amzn.to/3cEPaLa";
    String capteur_distance_url="https://amzn.to/3ty0oI3";
    String kit_elegoo_url ="https://amzn.to/38Rlsl1";
    String carte_motorshield_url ="https://amzn.to/3bWX3wx";
    String resistance_url ="https://amzn.to/38UpVn3";
    String carte_arduino_url ="https://amzn.to/3typI0J";
    String carte_nano_url ="https://amzn.to/30YsU9A";
    String carte_mega_url =" https://amzn.to/2OL1SjC";
    String moteur_dc_url= "https://amzn.to/3r21pXx";
    String servomoteur_url ="https://amzn.to/3lw1l0R";
    String Led_url ="https://amzn.to/3c2W0Lz";
    String Machine_souder_url ="https://amzn.to/38TxeLW";
    String Etain_url ="https://amzn.to/3vJkGjI";
    String kit_soudure_url ="https://amzn.to/310olvr";
    String denudeur_url ="https://amzn.to/3c2NT1C";
    String breadboard_url ="https://amzn.to/3c1Jdc8";
    String prototypage_url = "https://amzn.to/38VLPpY";
    String module_wifi_url ="https://amzn.to/3vDmPNY";
    String module_Bluetooth_url="https://amzn.to/3tKZCI9";
    String joystick_url ="https://amzn.to/3lAU3ZD";
    String capteur_son_url ="https://amzn.to/3c1Ur0m";
    String buzzer_url ="https://amzn.to/3sa2iyr";
    String shock_url ="https://amzn.to/3d7lK8X";
    String photoresistance_url ="https://amzn.to/3raz1lU";
    String humidite_url ="https://amzn.to/2PgFrCD";
    String raspberry_url ="https://amzn.to/2OVNSUj";
    String kit_raspberry_url ="https://amzn.to/3907o95";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achat_version2);

        // Permet d'avoir une fleche retour en haut
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // Permet de récupérer les valeurs de searchview et listeview que l'utilisateur à entré.
        listView = findViewById(R.id.list_view);
        searchView = findViewById(R.id.searchView);
        // Création de la liste temporaire que l'on va adapter en fonction de ce que l'utilisateur tape
        list = new ArrayList<>();

        for (int i = 0;i<version.length;i++){
            list.add(version[i]);
        }
        // Adapter va permettre d'adapter la liste affiché en fontion de ce que l'utilisateur tape
        // Imaginons que l'utilisateur et ch dans la searchview et la liste contient voiture, chien et cheval
        // Alors l'adapter va afficher chien et cheval.
        adapter = new ArrayAdapter(com.example.androidstudio.pages.achat_version2.this,android.R.layout.simple_list_item_1,list);
        // On adapte la nouvelle liste.
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Text qui affiche sur quoi l'utilisateur à cliqué dans la liste view.
                Toast.makeText(com.example.androidstudio.pages.achat_version2.this, "Selected -> " + version[i], Toast.LENGTH_SHORT).show();

                // Tout les if corresponds au résultat que l'on obtient par rapport à ce que l'utilisateur à cliqué
                // Si l'utilisateur clique sur carte arduino il va lancer la fonction Carte Arduino.
                if (version[i]=="Kit Elegoo"){
                    openelegoo();
                }
                if (version[i]=="Carte Arduino"){
                    opencarte_arduino();
                }
                if (version[i]=="Capteur distance"){
                    opencapteur_distance();
                }
                if (version[i]=="Bouton poussoir"){
                    openbouton_poussoir();
                }
                if (version[i]=="Joystick"){
                    openjoystick();
                }
                if (version[i]=="Capteur son"){
                    opencapteur_son();
                }
                if (version[i]=="Buzzer"){
                    openbuzzer();
                }
                if (version[i]=="Shock"){
                    openshock();
                }
                if (version[i]=="Photoresistance"){
                    openphotoresistance();
                }
                if (version[i]=="Humidite"){
                    openhumidite();
                }
                if (version[i]=="Motorshield"){
                    openmotorshield();
                }
                if (version[i]=="Carte_mega"){
                    opencarte_mega();
                }
                if (version[i]=="Carte_nano"){
                    opencarte_nano();
                }
                if (version[i]=="Raspberry"){
                    openraspberry();
                }
                if (version[i]=="Kit_rapsberry"){
                    openkit_raspberry();
                }
                if (version[i]=="Machine_souder"){
                    openmachine_souder();
                }
                if (version[i]=="Kit_soudure"){
                    openKit_soudure();
                }
                if (version[i]=="Etain"){
                    openetain();
                }
                if (version[i]=="Denudeur"){
                    opendenudeur();
                }
                if (version[i]=="moteur_dc"){
                    openmoteur_dc();
                }
                if (version[i]=="servomoteur"){
                    openservomoteur();
                }
                if (version[i]=="resistance"){
                    openresistance();
                }
                if (version[i]=="led"){
                    openled();
                }
                if (version[i]=="Breadboard"){
                    openbreadboard();
                }
                if (version[i]=="Prototype"){
                    openprototype();
                }
                if (version[i]=="Module_wifi"){
                    openmodule_wifi();
                }
                if (version[i]=="Module_bluetooth"){
                    openmodule_bluetooth();
                }


            }
        });
        // Permet d'adapter la liste view avec le résultat de la searchview.
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                if(list.contains(s)){
                    adapter.getFilter().filter(s);
                }
                return true;
            }
        });
        // Permet d'associer chaque bouton et chaque imageview a sa fonction ( fonction en bas du code)
        kit_raspberry=(Button) findViewById(R.id.kit_raspberry);
        kit_raspberry.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openkit_raspberry();
            }
        }));
        Kit_raspberry=(ImageView) findViewById(R.id.Kit_raspberry);
        Kit_raspberry.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openkit_raspberry();
            }
        }));
        raspberry=(Button) findViewById(R.id.raspberry);
        raspberry.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openraspberry();
            }
        }));
        Raspberry=(ImageView) findViewById(R.id.Raspberry);
        Raspberry.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openraspberry();
            }
        }));
        humidite=(Button) findViewById(R.id.humidite);
        humidite.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openhumidite();
            }
        }));
        Humidite=(ImageView) findViewById(R.id.Humidite);
        Humidite.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openhumidite();
            }
        }));
        photoresistance=(Button) findViewById(R.id.photoresistance);
        photoresistance.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openphotoresistance();
            }
        }));
        Photoresistance=(ImageView) findViewById(R.id.Photoresistance);
        Photoresistance.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openphotoresistance();
            }
        }));
        shock=(Button) findViewById(R.id.shock);
        shock.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openshock();
            }
        }));
        Shock=(ImageView) findViewById(R.id.Shock);
        Shock.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openshock();
            }
        }));
        buzzer=(Button) findViewById(R.id.buzzer);
        buzzer.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openbuzzer();
            }
        }));
        Buzzer=(ImageView) findViewById(R.id.Buzzer);
        Buzzer.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openbuzzer();
            }
        }));
        capteur_son=(Button) findViewById(R.id.capteur_son);
        capteur_son.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opencapteur_son();
            }
        }));
        Capteur_son=(ImageView) findViewById(R.id.Capteur_son);
        Capteur_son.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opencapteur_son();
            }
        }));
        joystick=(Button) findViewById(R.id.joystick);
        joystick.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openjoystick();
            }
        }));
        Joystick=(ImageView) findViewById(R.id.Joystick);
        Joystick.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openjoystick();
            }
        }));
        module_bluetooth=(Button) findViewById(R.id.module_bluetooth);
        module_bluetooth.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openmodule_bluetooth();
            }
        }));
        Module_bluetooth=(ImageView) findViewById(R.id.Module_bluetooth);
        Module_bluetooth.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openmodule_bluetooth();
            }
        }));
        module_wifi=(Button) findViewById(R.id.module_wifi);
        module_wifi.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openmodule_wifi();
            }
        }));
        Module_wifi=(ImageView) findViewById(R.id.Module_wifi);
        Module_wifi.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openmodule_wifi();
            }
        }));
        prototypage=(Button) findViewById(R.id.prototype);
        prototypage.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openprototype();
            }
        }));
        Prototypage=(ImageView) findViewById(R.id.Prototype);
        Prototypage.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openprototype();
            }
        }));
        breadboard=(Button) findViewById(R.id.breadboard);
        breadboard.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openbreadboard();
            }
        }));
        Breadboard=(ImageView) findViewById(R.id.Breadboard);
        Breadboard.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openbreadboard();
            }
        }));
        denudeur=(Button) findViewById(R.id.denudeur);
        denudeur.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendenudeur();
            }
        }));
        Denudeur=(ImageView) findViewById(R.id.Denudeur);
        Denudeur.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendenudeur();
            }
        }));
        kit_soudure=(Button) findViewById(R.id.kit_soudure);
        kit_soudure.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKit_soudure();
            }
        }));
        Kit_soudure=(ImageView) findViewById(R.id.Kit_soudure);
        Kit_soudure.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKit_soudure();
            }
        }));
        etain=(Button) findViewById(R.id.etain);
        etain.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openetain();
            }
        }));
        Etain=(ImageView) findViewById(R.id.Etain);
        Etain.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openetain();
            }
        }));
        machine_souder=(Button) findViewById(R.id.machine_souder);
        machine_souder.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openmachine_souder();
            }
        }));
        Machine_souder=(ImageView) findViewById(R.id.Machine_souder);
        Machine_souder.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openmachine_souder();
            }
        }));
        led=(Button) findViewById(R.id.led);
        led.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openled();
            }
        }));
        Led=(ImageView) findViewById(R.id.image_led);
        Led.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openled();
            }
        }));
        servomoteur=(Button) findViewById(R.id.servomoteur);
        servomoteur.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openservomoteur();
            }
        }));
        Servomoteur=(ImageView) findViewById(R.id.image_servomoteur);
        Servomoteur.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openservomoteur();
            }
        }));
        moteur_dc=(Button) findViewById(R.id.moteur_dc);
        moteur_dc.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openmoteur_dc();
            }
        }));
        Moteur_dc=(ImageView) findViewById(R.id.image_moteur_dc);
        Moteur_dc.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openmoteur_dc();
            }
        }));

        carte_mega=(Button) findViewById(R.id.carte_mega);
        carte_mega.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opencarte_mega();
            }
        }));
        Carte_mega=(ImageView) findViewById(R.id.Carte_mega);
        Carte_mega.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opencarte_mega();
            }
        }));
        carte_nano=(Button) findViewById(((R.id.carte_nano)));
        carte_nano.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opencarte_nano();
            }
        }));

        Carte_nano=(ImageView) findViewById(R.id.Carte_nano);
        Carte_nano.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opencarte_nano();
            }
        }));
        carte_arduino2=(Button) findViewById((R.id.carte_arduino2));
        carte_arduino2.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opencarte_arduino();
            }
        }));
        carte_arduino=(Button) findViewById((R.id.carte_arduino));
        carte_arduino.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opencarte_arduino();
            }
        }));
        Carte_arduino2=(ImageView) findViewById(R.id.Carte_arduino2);
        Carte_arduino2.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opencarte_arduino();
            }
        }));
        Carte_arduino=(ImageView) findViewById(R.id.Carte_arduino);
        Carte_arduino.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opencarte_arduino();
            }
        }));
        resistance=(Button) findViewById(R.id.resistance);
        resistance.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openresistance();
            }
        }));
        Resistance=(ImageView) findViewById(R.id.image_resistance);
        Resistance.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openresistance();
            }
        }));
        Motorshield=(ImageView) findViewById(R.id.image_motorshield);
        Motorshield.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openmotorshield();
            }
        }));
        Kit_Elegoo=(ImageView) findViewById(R.id.image_kit_elegoo);
        Kit_Elegoo.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openelegoo();
            }
        }));
        Capteur_distance=(ImageView) findViewById(R.id.image_capteur_distance);
        Capteur_distance.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opencapteur_distance();
            }
        }));
        Bouton_poussoir=(ImageView) findViewById(R.id.image_bouton_poussoir);
        Bouton_poussoir.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openbouton_poussoir();
            }
        }));
        motorshield=(Button) findViewById(R.id.Motorshield);
        motorshield.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openmotorshield();
            }
        }));
        elegoo=(Button) findViewById(R.id.Elegoo);
        elegoo.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openelegoo();
            }
        }));
        capteur_distance=(Button) findViewById(R.id.capteur_distance);
        capteur_distance.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                opencapteur_distance();
            }
        }));
        bouton_poussoir=(Button) findViewById(R.id.bouton_poussoir);
        bouton_poussoir.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openbouton_poussoir();
            }
        }));

    }
    // Voici les fonctions auquel fait référence tout le code précédent.
    public void openbouton_poussoir(){
        // On créer une intention vers la page qui nous intérèsse, c'est à dire page_internet
        // pour afficher la page correspondant au bouton cliqué.
        Intent bouton_poussoir_intent = new Intent(this, Page_Internet.class);
        // On lie l'url que l'on veut ouvrir à une variable ici bp afin de s'y retrouver dans la page internet
        // Pour qu'il sache laquelle url il faut ouvrir.
        bouton_poussoir_intent.putExtra("bp",bouton_poussoir_url);
        startActivity(bouton_poussoir_intent);

    }
    public void opencapteur_distance(){
        Intent capteur_distance_intent = new Intent(this, Page_Internet.class);
        capteur_distance_intent.putExtra("cd",capteur_distance_url);
        startActivity(capteur_distance_intent);
    }
    public void openelegoo(){
        Intent elegoo_intent = new Intent(this,Page_Internet.class);
        elegoo_intent.putExtra("e",kit_elegoo_url);
        startActivity(elegoo_intent);
    }
    public void openmotorshield(){
        Intent motorshield_intent= new Intent(this,Page_Internet.class);
        motorshield_intent.putExtra("m",carte_motorshield_url);
        startActivity(motorshield_intent);
    }
    public void openresistance(){
        Intent resistance_intent= new Intent(this,Page_Internet.class);
        resistance_intent.putExtra("r",resistance_url);
        startActivity(resistance_intent);
    }
    public void opencarte_arduino(){
        Intent carte_arduino_intent= new Intent(this,Page_Internet.class);
        carte_arduino_intent.putExtra("ca",carte_arduino_url);
        startActivity(carte_arduino_intent);

    }
    public void opencarte_nano(){
        Intent carte_nano_intent= new Intent(this,Page_Internet.class);
        carte_nano_intent.putExtra("cn",carte_nano_url);
        startActivity(carte_nano_intent);

    }
    public void opencarte_mega(){
        Intent carte_mega_intent= new Intent(this,Page_Internet.class);
        carte_mega_intent.putExtra("cm",carte_mega_url);
        startActivity(carte_mega_intent);

    }
    public void openmoteur_dc(){
        Intent moteur_dc_intent= new Intent(this,Page_Internet.class);
        moteur_dc_intent.putExtra("dc",moteur_dc_url);
        startActivity(moteur_dc_intent);

    }
    public void openservomoteur(){
        Intent servomoteur_intent= new Intent(this,Page_Internet.class);
        servomoteur_intent.putExtra("se",servomoteur_url);
        startActivity(servomoteur_intent);

    }
    public void openled(){
        Intent led_intent= new Intent(this,Page_Internet.class);
        led_intent.putExtra("le",Led_url);
        startActivity(led_intent);

    }
    public void openmachine_souder(){
        Intent machine_souder_intent= new Intent(this,Page_Internet.class);
        machine_souder_intent.putExtra("ms",Machine_souder_url);
        startActivity(machine_souder_intent);

    }
    public void openetain(){
        Intent etain_intent= new Intent(this,Page_Internet.class);
        etain_intent.putExtra("et",Etain_url);
        startActivity(etain_intent);

    }
    public void openKit_soudure(){
        Intent Kit_soudure_intent= new Intent(this,Page_Internet.class);
        Kit_soudure_intent.putExtra("ks",kit_soudure_url);
        startActivity(Kit_soudure_intent);

    }
    public void opendenudeur(){
        Intent denudeur_intent= new Intent(this,Page_Internet.class);
        denudeur_intent.putExtra("d",denudeur_url);
        startActivity(denudeur_intent);

    }
    public void openbreadboard(){
        Intent breadboard_intent= new Intent(this,Page_Internet.class);
        breadboard_intent.putExtra("be",breadboard_url);
        startActivity(breadboard_intent);

    }
    public void openprototype(){
        Intent prototype_intent= new Intent(this,Page_Internet.class);
        prototype_intent.putExtra("pr",prototypage_url);
        startActivity(prototype_intent);

    }
    public void openmodule_wifi(){
        Intent module_wifi_intent= new Intent(this,Page_Internet.class);
        module_wifi_intent.putExtra("mw",module_wifi_url);
        startActivity(module_wifi_intent);

    }

    public void openmodule_bluetooth(){
        Intent module_bluetooth_intent= new Intent(this,Page_Internet.class);
        module_bluetooth_intent.putExtra("mb",module_Bluetooth_url);
        startActivity(module_bluetooth_intent);

    }
    public void openjoystick(){
        Intent joystick_intent= new Intent(this,Page_Internet.class);
        joystick_intent.putExtra("j",joystick_url);
        startActivity(joystick_intent);

    }
    public void opencapteur_son(){
        Intent capteur_son_intent= new Intent(this,Page_Internet.class);
        capteur_son_intent.putExtra("cs",capteur_son_url);
        startActivity(capteur_son_intent);

    }
    public void openbuzzer(){
        Intent buzzer_intent= new Intent(this,Page_Internet.class);
        buzzer_intent.putExtra("b",buzzer_url);
        startActivity(buzzer_intent);

    }
    public void openshock(){
        Intent shock_intent= new Intent(this,Page_Internet.class);
        shock_intent.putExtra("sh",shock_url);
        startActivity(shock_intent);

    }
    public void openphotoresistance(){
        Intent photoresistance_intent= new Intent(this,Page_Internet.class);
        photoresistance_intent.putExtra("ph",photoresistance_url);
        startActivity(photoresistance_intent);

    }
    public void openhumidite(){
        Intent humidite_intent= new Intent(this,Page_Internet.class);
        humidite_intent.putExtra("hu",humidite_url);
        startActivity(humidite_intent);

    }
    public void openraspberry(){
        Intent raspberry_intent= new Intent(this,Page_Internet.class);
        raspberry_intent.putExtra("ra",raspberry_url);
        startActivity(raspberry_intent);

    }
    public void openkit_raspberry(){
        Intent kit_raspberry_intent= new Intent(this,Page_Internet.class);
        kit_raspberry_intent.putExtra("kr",kit_raspberry_url);
        startActivity(kit_raspberry_intent);

    }
    // Permet de retourner à la page menu
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }

}

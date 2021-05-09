package com.example.androidstudio.achat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidstudio.Page_Internet;
import com.example.androidstudio.R;

import java.util.ArrayList;

public class Achat_Main extends AppCompatActivity {

    private RecyclerView recyclerView;

    private RecyclerView_Adapter adapter;
    private ArrayList<Achat_Data> data;
    private ArrayList<Achat_Data> filteredlist_onClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        recyclerView = findViewById(R.id.recyclerView);
        buildRecyclerView();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        onClick(view ,  position);
                    }
                    @Override public void onLongItemClick(View view, int position) {
                    }
                }));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.actionSearch);

        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return false;
            }});
        return true;
    }

    private void filter(String text) {
        ArrayList<Achat_Data> filteredlist = new ArrayList<>();

        for (Achat_Data item : data) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredlist.add(item);
            }
        }
        if (filteredlist.isEmpty()) {
            //Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show();
        }
        else {
            adapter.filterList(filteredlist);
        }
        filteredlist_onClick = filteredlist;
    }

    private void buildRecyclerView() {

        data = new ArrayList<>();

        data.add(new Achat_Data("Kit Elegoo",      R.drawable.achat_kit,             "https://amzn.to/38Rlsl1"));
        data.add(new Achat_Data("Carte Arduino",   R.drawable.achat_carte_arduino,   "https://amzn.to/3typI0J"));
        data.add(new Achat_Data("Capteur distance",R.drawable.achat_distance,        "https://amzn.to/3ty0oI3"));
        data.add(new Achat_Data("Bouton poussoir", R.drawable.achat_bouton,          "https://amzn.to/3cEPaLa"));
        data.add(new Achat_Data("Résistance",      R.drawable.achat_resistance,      "https://amzn.to/38UpVn3"));
        data.add(new Achat_Data("Joystick",        R.drawable.achat_joystick,        "https://amzn.to/3lAU3ZD"));
        data.add(new Achat_Data("Capteur de son",  R.drawable.achat_capteur_son,     "https://amzn.to/3c1Ur0m"));
        data.add(new Achat_Data("Buzzer",          R.drawable.achat_buzzer,          "https://amzn.to/3sa2iyr"));
        data.add(new Achat_Data("Shock",           R.drawable.achat_shock,           "https://amzn.to/3d7lK8X"));
        data.add(new Achat_Data("Photorésistance", R.drawable.achat_photoresistance, "https://amzn.to/3raz1lU"));
        data.add(new Achat_Data("Humidité",        R.drawable.achat_humidite,        "https://amzn.to/2PgFrCD"));
        data.add(new Achat_Data("Motorshield",     R.drawable.achat_motorshield,     "https://amzn.to/3bWX3wx"));
        data.add(new Achat_Data("Carte méga",      R.drawable.achat_carte_mega,      "https://amzn.to/2OL1SjC"));
        data.add(new Achat_Data("Carte nano",      R.drawable.achat_carte_nano,      "https://amzn.to/30YsU9A"));
        data.add(new Achat_Data("Carte raspberry", R.drawable.achat_raspberry,       "https://amzn.to/3907o95"));
        data.add(new Achat_Data("Kit raspberry",   R.drawable.achat_kit_raspberry,   "https://amzn.to/2OVNSUj"));
        data.add(new Achat_Data("Machine à souder",R.drawable.achat_machine_soudure, "https://amzn.to/38TxeLW"));
        data.add(new Achat_Data("Etain",           R.drawable.achat_etain,           "https://amzn.to/3vJkGjI"));
        data.add(new Achat_Data("Kit soudure",     R.drawable.achat_kit_soudure,     "https://amzn.to/310olvr"));
        data.add(new Achat_Data("Dénudeur de fil", R.drawable.achat_pince_soudure,   "https://amzn.to/3c2NT1C"));
        data.add(new Achat_Data("Moteur dc",       R.drawable.achat_moteur_dc,       "https://amzn.to/3r21pXx"));
        data.add(new Achat_Data("Servomoteur",     R.drawable.achat_servomoteur,     "https://amzn.to/3lw1l0R"));
        data.add(new Achat_Data("Led",             R.drawable.achat_led,             "https://amzn.to/3c2W0Lz"));
        data.add(new Achat_Data("Breadboard",      R.drawable.achat_breadboard,      "https://amzn.to/3c1Jdc8"));
        data.add(new Achat_Data("Prototype",       R.drawable.prototype,             "https://amzn.to/38VLPpY"));
        data.add(new Achat_Data("Module wifi",     R.drawable.achat_module_wifi,     "https://amzn.to/3vDmPNY"));
        data.add(new Achat_Data("Module bluetooth",R.drawable.achat_module_bluetooth,"https://amzn.to/3tKZCI9"));

        filteredlist_onClick = data;

        adapter = new RecyclerView_Adapter(data, Achat_Main.this);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(manager);

        recyclerView.setAdapter(adapter);
    }

    public void onClick(View v, int position){

        Intent intent_achat= new Intent(this, Page_Internet.class);
        intent_achat.putExtra("url_achat",filteredlist_onClick.get(position).getLien());
        startActivity(intent_achat);
    }

}
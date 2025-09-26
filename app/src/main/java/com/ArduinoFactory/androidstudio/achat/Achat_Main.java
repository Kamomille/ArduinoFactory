package com.ArduinoFactory.androidstudio.achat;

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

import com.ArduinoFactory.androidstudio.page_accessoire.Page_Internet;
import com.ArduinoFactory.androidstudio.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

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

        MobileAds.initialize(this, initializationStatus -> {});

        AdView mAdView = findViewById(R.id.adView4);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

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
                        // Not used
                    }
                }));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.actionSearch);

        SearchView searchView = (SearchView) searchItem.getActionView();

        if (searchView != null) {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    filter(newText);
                    return false;
                }
            });
        }

        return true;
    }

    private void filter(String text) {
        ArrayList<Achat_Data> filteredlist = new ArrayList<>();

        for (Achat_Data item : data) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredlist.add(item);
            }
        }
        adapter.filterList(filteredlist);
        filteredlist_onClick = filteredlist;
    }

    private void buildRecyclerView() {
        data = new ArrayList<>();

        data.add(new Achat_Data(getString(R.string.kit_elegoo), R.drawable.achat_kit, "https://amzn.to/38Rlsl1"));
        data.add(new Achat_Data(getString(R.string.carte_arduino), R.drawable.achat_carte_arduino, "https://amzn.to/3typI0J"));
        data.add(new Achat_Data(getString(R.string.capteur_distance), R.drawable.achat_distance, "https://amzn.to/3JAojEp"));
        data.add(new Achat_Data(getString(R.string.bouton_poussoir), R.drawable.achat_bouton, "https://amzn.to/3cEPaLa"));
        data.add(new Achat_Data(getString(R.string.resistance), R.drawable.achat_resistance, "https://amzn.to/38UpVn3"));
        data.add(new Achat_Data(getString(R.string.joystick), R.drawable.achat_joystick, "https://amzn.to/3lAU3ZD"));
        data.add(new Achat_Data(getString(R.string.capteur_son), R.drawable.achat_capteur_son, "https://amzn.to/3c1Ur0m"));
        data.add(new Achat_Data(getString(R.string.buzzer), R.drawable.achat_buzzer, "https://amzn.to/3sa2iyr"));
        data.add(new Achat_Data(getString(R.string.capteur_inclinaison), R.drawable.achat_shock, "https://amzn.to/3ON8JF8"));
        data.add(new Achat_Data(getString(R.string.photoresistance), R.drawable.achat_photoresistance, "https://amzn.to/3raz1lU"));
        data.add(new Achat_Data(getString(R.string.humidite), R.drawable.achat_humidite, "https://amzn.to/2PgFrCD"));
        data.add(new Achat_Data(getString(R.string.motorshield), R.drawable.achat_motorshield, "https://amzn.to/3bWX3wx"));
        data.add(new Achat_Data(getString(R.string.carte_mega), R.drawable.achat_carte_mega, "https://amzn.to/2OL1SjC"));
        data.add(new Achat_Data(getString(R.string.carte_nano), R.drawable.achat_carte_nano, "https://amzn.to/30YsU9A"));
        data.add(new Achat_Data(getString(R.string.carte_raspberry), R.drawable.achat_raspberry, "https://amzn.to/2OVNSUj"));
        data.add(new Achat_Data(getString(R.string.etain), R.drawable.achat_etain, "https://amzn.to/3vJkGjI"));
        data.add(new Achat_Data(getString(R.string.kit_soudure), R.drawable.achat_kit_soudure, "https://amzn.to/3T1bGmT"));
        data.add(new Achat_Data(getString(R.string.denudeur_fil), R.drawable.achat_pince_soudure, "https://amzn.to/3c2NT1C"));
        data.add(new Achat_Data(getString(R.string.moteur_dc), R.drawable.achat_moteur_dc, "https://amzn.to/3r21pXx"));
        data.add(new Achat_Data(getString(R.string.servomoteur), R.drawable.achat_servomoteur, "https://amzn.to/3lw1l0R"));
        data.add(new Achat_Data(getString(R.string.breadboard), R.drawable.achat_breadboard, "https://amzn.to/3c1Jdc8"));
        data.add(new Achat_Data(getString(R.string.prototype), R.drawable.prototype, "https://amzn.to/3I7RF8N"));
        data.add(new Achat_Data(getString(R.string.module_wifi), R.drawable.achat_module_wifi, "https://amzn.to/3vDmPNY"));
        data.add(new Achat_Data(getString(R.string.module_bluetooth) , R.drawable.achat_module_bluetooth, "https://amzn.to/3tKZCI9"));

        filteredlist_onClick = data;

        adapter = new RecyclerView_Adapter(data);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    public void onClick(int position) {

        Intent intent_achat = new Intent(this, Page_Internet.class);
        intent_achat.putExtra("url", filteredlist_onClick.get(position).getLien());
        startActivity(intent_achat);
    }

}

package com.example.androidstudio.achat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.androidstudio.R;

import java.util.ArrayList;
import java.util.List;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        List<Data> data = fill_with_data();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        RecyclerView_Adapter adapter = new RecyclerView_Adapter(data, getApplication());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public List<Data> fill_with_data() {

        List<Data> data = new ArrayList<>();
        data.add(new Data("bouton", R.drawable.achat_bouton));
        data.add(new Data("breadboard", R.drawable.achat_breadboard));
        data.add(new Data("buzzer", R.drawable.achat_buzzer));
        data.add(new Data("cap", R.drawable.achat_capteur_son));
        data.add(new Data("carte", R.drawable.achat_carte_arduino));
        data.add(new Data("etain", R.drawable.achat_etain));
        data.add(new Data("joy", R.drawable.achat_joystick));
        data.add(new Data("led", R.drawable.achat_led));
        data.add(new Data("motor", R.drawable.achat_motorshield));
        data.add(new Data("photoresitance", R.drawable.achat_photoresistance));
        data.add(new Data("st", R.drawable.achat_shock));
        data.add(new Data("wifi", R.drawable.achat_module_wifi));
        data.add(new Data("mod", R.drawable.achat_module_bluetooth));
        data.add(new Data("kit", R.drawable.achat_kit_soudure));

        return data;
    }
}
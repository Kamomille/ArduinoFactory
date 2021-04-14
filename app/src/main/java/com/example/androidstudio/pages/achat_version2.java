
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
        list.add("Kit Elegoo");
        list.add("Carte Arduino");
        list.add("Capteur distance");
        list.add("Bouton poussoir");
        list.add("Joystick");
        list.add("Capteur de son");
        list.add("Buzzer");
        list.add("Shock");
        list.add("Photorésistance");
        list.add("Humidité");
        list.add("Motorshield");
        list.add("Carte méga");
        list.add("Carte nano");
        list.add("Carte raspberry");
        list.add("Kit rapsberry");
        list.add("Machine à souder");
        list.add("Etain");
        list.add("Kit soudure");
        list.add("Dénudeur de fil");
        list.add("Moteur dc");
        list.add("Servomoteur");
        list.add("Led");
        list.add("Breadboard");
        list.add("Prototype");
        list.add("Module wifi");
        list.add("Module bluetooth");

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);
        listView.setTextFilterEnabled(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                searchView.setQueryHint("test");
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                if(list.contains(query)){
                    adapter.getFilter().filter(query);
                    //affichage_recherche(query, adapter);
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
                Button button = new Button(this);
                button.setText(adapter.getItem(i).toString());
                button.setBackgroundColor(parseColor("#0B789C"));
                button.setTextColor(parseColor("#FFFFFF"));
                layout_recherche.addView(button);

                ImageButton imageButton = new ImageButton(this);
                imageButton.setImageDrawable(Drawable.createFromPath("@drawable/achat_bouton"));
                layout_recherche.addView(imageButton);
            }
        }

        //setContentView(button);
        //button.setLayoutParams(new LinearLayout.LayoutParams
         //       (LinearLayout.LayoutParams.WRAP_CONTENT,
         //               LinearLayout.LayoutParams.MATCH_PARENT));

        //button.setId(1);

        //button.setText("XX");

        return true;
    }
}
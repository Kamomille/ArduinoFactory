package com.ArduinoFactory.androidstudio.page_accessoire;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.EditTextPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.ArduinoFactory.androidstudio.R;
import com.ArduinoFactory.androidstudio.page_principal.MainActivity;

public class Page_Parametre extends AppCompatActivity {

    EditTextPreference PrenomPreference = null;
    EditTextPreference NomPreference = null;
    SharedPreferences prefs;
    String prenom1 = "la";
    private String prenom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Sauvegarder un prénom fictif (inutile ici mais gardé si tu veux t'en servir plus tard)
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("firstname", prenom);
        editor.apply();

        setContentView(R.layout.activity_page__parametre);

        // Ajouter le fragment des paramètres
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings, new SettingsFragment())
                    .commit();
        }

        // Afficher la flèche "retour"
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    // Gérer le retour via la flèche
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }

    // Fragment des préférences
    public static class SettingsFragment extends PreferenceFragmentCompat {

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);

            // Gérer le clic sur le bouton vers Google Play
            Preference button = findPreference(getString(R.string.myCoolButton));
            if (button != null) {
                button.setOnPreferenceClickListener(preference -> {
                    Intent intent = new Intent(getActivity(), Page_Internet.class);
                    intent.putExtra("url", "https://play.google.com/store/apps/details?id=com.ArduinoFactory.androidstudio");
                    startActivity(intent);
                    return true;
                });
            }
        }

        // Ajouter un padding top pour simuler une marge
        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            // Convertir 90dp en pixels selon la densité d'écran
            int topPadding = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    90,
                    getResources().getDisplayMetrics()
            );

            // Appliquer le padding haut tout en gardant les autres
            view.setPadding(
                    view.getPaddingLeft(),
                    topPadding,
                    view.getPaddingRight(),
                    view.getPaddingBottom()
            );
        }
    }
}

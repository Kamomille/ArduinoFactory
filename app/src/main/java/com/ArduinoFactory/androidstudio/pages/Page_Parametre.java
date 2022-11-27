package com.ArduinoFactory.androidstudio.pages;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.EditTextPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.ArduinoFactory.androidstudio.MainActivity;
import com.ArduinoFactory.androidstudio.Page_Internet;
import com.ArduinoFactory.androidstudio.R;

public class Page_Parametre extends AppCompatActivity {
    EditTextPreference PrenomPreference =null;
    EditTextPreference NomPreference=null;
    SharedPreferences prefs;
    String prenom1 ="la";
    private String prenom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("firstname", prenom);
        editor.apply();
        setContentView(R.layout.activity_page__parametre);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings, new SettingsFragment())
                    .commit();
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }
    public static class SettingsFragment extends PreferenceFragmentCompat {


        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
            Preference button = findPreference(getString(R.string.myCoolButton));
            button.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    Intent intent = new Intent(getActivity() , Page_Internet.class);
                    intent.putExtra("af","https://play.google.com/store?hl=fr&gl=US");
                    startActivity(intent);
                   return true;
                }

            });
        }

    }
        }


package com.example.androidstudio.pages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.androidstudio.MainActivity;
import com.example.androidstudio.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Page_Favoris extends AppCompatActivity {
    private Button Favoris;
    private TextView Texteview_Favoris;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page__favoris);
        Texteview_Favoris = (TextView) findViewById(R.id.textView_Favoris);
        Favoris= findViewById(R.id.Favoris1);
        Favoris.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivtity_Favoris();

            }
        }));

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.nav_view);

        // Pour gérer la navigation avec les fragments (dasboard, home, notif) -----------------------------------------------

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_home:
                                openActivitity_MainActivity();
                                break;
                            case R.id.navigation_dashboard:

                                break;
                            case R.id.navigation_notifications:
                                openActivitity_Notification();
                                break;
                        }
                        return false;
                    }
                });


    }
    // Permet de retourner à la page menu
    public void openActivitity_MainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void openActivitity_Notification(){
        Intent intent = new Intent(this, Page_Notification.class);
        startActivity(intent);
    }
    public void openActivtity_Favoris(){
        Texteview_Favoris.setText("Vous êtes dans le fragment Favoris");
    }
}
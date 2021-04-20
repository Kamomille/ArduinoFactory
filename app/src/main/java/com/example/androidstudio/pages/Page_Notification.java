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

public class Page_Notification extends AppCompatActivity {
    private Button Notification;
    private TextView Texteview_Notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page__notification);
        Texteview_Notification = (TextView) findViewById(R.id.textView_Notification);
        Notification= findViewById(R.id.Notification1);
        Notification.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivtity_Notifbouton();

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
                                openActivitity_Favoris();

                                break;
                            case R.id.navigation_notifications:
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
    public void openActivitity_Favoris(){
        Intent intent = new Intent(this, Page_Favoris.class);
        startActivity(intent);
    }
    public void openActivtity_Notifbouton(){
        Texteview_Notification.setText("Vous êtes dans le fragment Notif");
    }
    }

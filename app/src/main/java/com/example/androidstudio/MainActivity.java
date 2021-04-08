package com.example.androidstudio;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.androidstudio.pages.Page_Contact;
import com.example.androidstudio.pages.Page_Cours;
import com.example.androidstudio.pages.Page_Outils;
import com.example.androidstudio.pages.Page_Parametre;
import com.example.androidstudio.pages.achat_version2;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    private Button buttonOutils, buttonCours, buttonAchat, buttonContact;
    private ImageButton buttonParametre, imageOutils, imageCours, imageAchat, imageContact;

    // Pour gérer les fragment (dasboard, home, notif) -------------------------------------------------------N
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        buttonOutils= findViewById(R.id.buttonOutils);
        buttonCours= findViewById(R.id.buttonCours);
        buttonAchat= findViewById(R.id.buttonAchat);
        buttonContact= findViewById(R.id.buttonContacter);
        imageOutils= findViewById(R.id.imageOutils);
        imageOutils= findViewById(R.id.imageCours);
        imageOutils= findViewById(R.id.imageAchat);
        imageOutils= findViewById(R.id.imageContacter);


        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.nav_view);

        // Pour gérer la navigation avec les fragments (dasboard, home, notif) -----------------------------------------------

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_home:
                                //setContentView(R.layout.activity_outils_resistance);
                                break;
                            case R.id.navigation_dashboard:
                                setContentView(R.layout.activity_page__favoris);
                                break;
                            case R.id.navigation_notifications:
                                setContentView(R.layout.fragment_notifications);
                                break;
                        }
                        return false;
                    }
                });


        // Pour faire fonctionner les boutons et les imageBoutons -----------------------------------------------

}
    public void onClickOutils (View view){
        openActivtity_outils();
    }
    public void onClickCours (View view){
        openActivtity_cours();
    }
    public void onClickAchat (View view){
        openActivtity_achat();
    }
    public void onClickContacter (View view){
        openActivtity_contacter();
    }
    public void onClickParametre (View view){
        openActivtity_parametre();
    }
    public void openActivtity_outils(){
        Intent intent = new Intent(this, Page_Outils.class);
        startActivity(intent);
    }
    public void openActivtity_cours(){
        Intent intent = new Intent(this, Page_Cours.class);
        startActivity(intent);
    }
    public void openActivtity_achat(){
        Intent intent = new Intent(this, achat_version2.class);
        startActivity(intent);
    }
    public void openActivtity_contacter(){
        Intent intent = new Intent(this, Page_Contact.class);
        startActivity(intent);
    }
    public void openActivtity_parametre(){
        Intent intent = new Intent(this, Page_Parametre.class);
        startActivity(intent);
    }
}



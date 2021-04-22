package com.example.androidstudio;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.service.notification.StatusBarNotification;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.androidstudio.pages.Page_Achat;
import com.example.androidstudio.pages.Page_Contact;
import com.example.androidstudio.pages.Page_Cours;
import com.example.androidstudio.pages.Page_Favoris;
import com.example.androidstudio.pages.Page_Notification;
import com.example.androidstudio.pages.Page_Outils;
import com.example.androidstudio.pages.Page_Parametre;
import com.example.androidstudio.pages.achat_version2;
import com.example.androidstudio.ui.dashboard.DashboardFragment;
import com.example.androidstudio.ui.home.HomeFragment;
import com.example.androidstudio.ui.notifications.NotificationsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Button buttonOutils, buttonCours, buttonAchat, buttonContact;
    private ImageButton buttonParametre, imageOutils, imageCours, imageAchat, imageContact;
    private static final String Notification_Titre = "Nouveau Cours sur Arduino Factory";
    private static final String Notification_Contenu = "Nouveau cours sur le Servomoteur";

    // Pour gérer les fragment (dasboard, home, notif) -------------------------------------------------------N
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NotificationChannel();
        CreateNotification();
        buttonOutils= findViewById(R.id.buttonOutils);
        buttonCours= findViewById(R.id.buttonCours);
        buttonAchat= findViewById(R.id.buttonAchat);
        buttonContact= findViewById(R.id.buttonContacter);
        imageOutils= findViewById(R.id.imageOutils);
        imageOutils= findViewById(R.id.imageCours);
        imageOutils= findViewById(R.id.imageAchat);
        imageOutils= findViewById(R.id.imageContacter);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.nav_view);

        // Pour gérer la navigation avec les fragments (dasboard, home, notif) -----------------------------------------------

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.navigation_home:
                                break;
                            case R.id.navigation_dashboard:
                                openFavoris();
                                break;
                            case R.id.navigation_notifications:
                                openNotification();
                                break;
                        }
                        //getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_container,selectedFragment);
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
    private void CreateNotification() {
        Intent intent = new Intent(this, Page_Cours.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "letunnel")
                .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                .setContentTitle(Notification_Titre)
                .setContentText(Notification_Contenu)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(100,builder.build());

    }
    private void NotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "studentChannel";
            String description = "Channel for student notification";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("letunnel",name,importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
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
    public void openActivtity_parametre() {
        Intent intent = new Intent(this, Page_Parametre.class);
        startActivity(intent);
    }
    public void openFavoris() {
        Intent intent = new Intent(this, Page_Favoris.class);
        startActivity(intent);
    }
    public void openNotification(){
        Intent intent = new Intent(this, Page_Notification.class);
        startActivity(intent);
    }
    }

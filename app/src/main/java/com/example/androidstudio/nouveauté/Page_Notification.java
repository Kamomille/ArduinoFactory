package com.example.androidstudio.nouveauté;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.androidstudio.MainActivity;
import com.example.androidstudio.R;
import com.example.androidstudio.achat.RecyclerItemClickListener;
import com.example.androidstudio.favoris.Favoris_Data;
import com.example.androidstudio.favoris.Favoris_RecyclerViewAdapter;
import com.example.androidstudio.favoris.Page_Favoris;
import com.example.androidstudio.outils.Outils_resistance;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Page_Notification extends AppCompatActivity {
    private Button Notification;
    private TextView Texteview_Notification;

    private RecyclerView recyclerView;
    private Favoris_RecyclerViewAdapter adapter;
    private ArrayList<Favoris_Data> data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page__notification);
        createNotificationChannel();
        Texteview_Notification = (TextView) findViewById(R.id.textView_Notification);
        Notification= findViewById(R.id.Notification1);
        Notification.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivtity_Notifbouton();
            }
        }));

        Button buttonShowNotification = findViewById(R.id.show);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "lemubitA")
                .setSmallIcon(R.drawable.accueil)
                .setContentTitle("Nouveau Cours")
                .setContentText("Un nouveau cours sur le servomoteur dans votre application")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        buttonShowNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notificationManager.notify(100,builder.build());

            }
        });

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.nav_view);
        bottomNavigationView.getMenu().getItem(2).setChecked(true);

        // ========================================================================================================================
        //                              recycler view
        // ========================================================================================================================


        recyclerView = findViewById(R.id.recyclerView);
        buildRecyclerView();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        onClick(view, position);
                    }
                    @Override public void onLongItemClick(View view, int position) {
                    }
                }));

        // ========================================================================================================================
        //                       Gestion barre de navigation
        // ========================================================================================================================

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
        finish();
        this.startActivity(new Intent(this, MainActivity.class));
        this.overridePendingTransition(0, 0);
    }
    public void openActivitity_Favoris(){
        finish();
        this.startActivity(new Intent(this, Page_Favoris.class));
        this.overridePendingTransition(0, 0);
    }
    public void openActivtity_Notifbouton(){
        Texteview_Notification.setText("Vous êtes dans le fragment Notif");
    }
    private void createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "studentChannel";
            String description = "Channel for student notification";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("lemubitA",name,importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


    // ========================================================================================================================
    //                                      recycler view
    // ========================================================================================================================


    private void buildRecyclerView() {

        data = new ArrayList<>();

        SharedPreferences prefs = getSharedPreferences("notif", MODE_PRIVATE);
        String notif1 = prefs.getString("notif1", "Pas de valeur défini");

        data.add(new Favoris_Data(notif1,  R.drawable.outils_vide));


        adapter = new Favoris_RecyclerViewAdapter(data, Page_Notification.this);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(manager);

        recyclerView.setAdapter(adapter);
    }

    public void onClick(View v, int position){

    }
}

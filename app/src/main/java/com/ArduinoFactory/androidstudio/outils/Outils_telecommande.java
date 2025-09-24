package com.ArduinoFactory.androidstudio.outils;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.ArduinoFactory.androidstudio.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.io.IOException;
//import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

public class Outils_telecommande extends AppCompatActivity {
    private TextView tv_status;
    private ImageButton bouton_volplus;
    private ImageButton bouton_function;
    private ImageButton bouton_back;
    private ImageButton bouton_pause;
    private ImageButton bouton_next;
    private ImageButton bouton_descendre;
    private ImageButton bouton_volmoins;
    private ImageButton bouton_monter;
    private ImageButton bouton_eq;
    private ImageButton bouton_rept;
    private Button Bouton_0, Bouton_1, Bouton_2, Bouton_3, Bouton_4, Bouton_5, Bouton_6, Bouton_7, Bouton_8, Bouton_9;
    private BluetoothAdapter my_bt_adapter = null;
    private MyBluetoothClass mybluetooth = null;
    private BluetoothSocket my_bt_soket = null;
    private OutputStream my_bt_out_stream = null;
    private String dev_address;
    private static final int REQUEST_BLUETOOTH_PERMISSION = 123;
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private Handler my_handler;
    private final static int STATUS = 1;
    int Etat_volplus = 0, Etat_function = 0, Etat_back = 0, Etat_pause = 0, Etat_next = 0, Etat_descendre = 0, Etat_volmoins = 0, Etat_monter = 0, Etat_bouton_0 = 0, Etat_eq = 0, Etat_rept = 0, Etat_bouton_1 = 0, Etat_bouton_2 = 0, Etat_bouton_3 = 0, Etat_bouton_4 = 0, Etat_bouton_5 = 0, Etat_bouton_6 = 0, Etat_bouton_7 = 0, Etat_bouton_8 = 0, Etat_bouton_9 = 0;
    int telecommandeSelect = 2;
    int numView = 1;
    int stateHeart=1;

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outils_telecommande);

        ViewFlipper viewFlipper = findViewById(R.id.outil_telecommande);
        viewFlipper.setDisplayedChild(2);



        tv_status = findViewById(R.id.TV_STATUS);
        ListView lv_devlist = findViewById(R.id.LV_DEVLIST);




        bouton_volplus = findViewById(R.id.Volplus);
        bouton_volplus.setOnClickListener((v -> {
            if (Etat_volplus == 0) {
                mybluetooth.writebyte((byte) 'A');
                Etat_volplus = 1;
            } else {
                mybluetooth.writebyte((byte) 'B');
                Etat_volplus = 0;
            }
        }));
        bouton_function = findViewById(R.id.function);
        bouton_function.setOnClickListener((v -> {
            if (Etat_function == 0) {
                mybluetooth.writebyte((byte) 'C');
                Etat_function = 1;
            } else {
                mybluetooth.writebyte((byte) 'D');
                Etat_function = 0;
            }
        }));
        bouton_back = findViewById(R.id.back);
        bouton_back.setOnClickListener((v -> {
            if (Etat_back == 0) {
                mybluetooth.writebyte((byte) 'E');
                Etat_back = 1;
            } else {
                mybluetooth.writebyte((byte) 'F');
                Etat_back = 0;
            }
        }));
        bouton_pause = findViewById(R.id.pause);
        bouton_pause.setOnClickListener((v -> {
            if (Etat_pause == 0) {
                mybluetooth.writebyte((byte) 'G');
                Etat_pause = 1;
            } else {
                mybluetooth.writebyte((byte) 'H');
                Etat_pause = 0;
            }
        }));
        bouton_next = findViewById(R.id.next);
        bouton_next.setOnClickListener((v -> {
            if (Etat_next == 0) {
                mybluetooth.writebyte((byte) 'I');
                Etat_next = 1;
            } else {
                mybluetooth.writebyte((byte) 'J');
                Etat_next = 0;
            }
        }));
        bouton_descendre = findViewById(R.id.descendre);
        bouton_descendre.setOnClickListener((v -> {
            if (Etat_descendre == 0) {
                mybluetooth.writebyte((byte) 'K');
                Etat_descendre = 1;
            } else {
                mybluetooth.writebyte((byte) 'L');
                Etat_descendre = 0;
            }
        }));
        bouton_volmoins = findViewById(R.id.volumemoins);
        bouton_volmoins.setOnClickListener((v -> {
            if (Etat_volmoins == 0) {
                mybluetooth.writebyte((byte) 'M');
                Etat_volmoins = 1;
            } else {
                mybluetooth.writebyte((byte) 'N');
                Etat_volmoins = 0;
            }
        }));
        bouton_monter = findViewById(R.id.monter);
        bouton_monter.setOnClickListener((v -> {
            if (Etat_monter == 0) {
                mybluetooth.writebyte((byte) 'O');
                Etat_monter = 1;
            } else {
                mybluetooth.writebyte((byte) 'P');
                Etat_monter = 0;
            }
        }));
        Bouton_0 = findViewById(R.id.bouton_0);
        Bouton_0.setOnClickListener((v -> {
            if (Etat_bouton_0 == 0) {
                mybluetooth.writebyte((byte) 'Q');
                Etat_bouton_0 = 1;
            } else {
                mybluetooth.writebyte((byte) 'R');
                Etat_bouton_0 = 0;
            }
        }));
        bouton_eq = findViewById(R.id.egaliser);
        bouton_eq.setOnClickListener((v -> {
            if (Etat_eq == 0) {
                mybluetooth.writebyte((byte) 'S');
                Etat_eq = 1;
            } else {
                mybluetooth.writebyte((byte) 'T');
                Etat_eq = 0;
            }
        }));
        bouton_rept = findViewById(R.id.repeter);
        bouton_rept.setOnClickListener((v -> {
            if (Etat_rept == 0) {
                mybluetooth.writebyte((byte) 'U');
                Etat_rept = 1;
            } else {
                mybluetooth.writebyte((byte) 'V');
                Etat_rept = 0;
            }
        }));
        Bouton_1 = findViewById(R.id.bouton_1);
        Bouton_1.setOnClickListener((v -> {
            if (Etat_bouton_1 == 0) {
                mybluetooth.writebyte((byte) 'W');
                Etat_bouton_1 = 1;
            } else {
                mybluetooth.writebyte((byte) 'X');
                Etat_bouton_1 = 0;
            }
        }));
        Bouton_2 = findViewById(R.id.bouton_2);
        Bouton_2.setOnClickListener((v -> {
            if (Etat_bouton_2 == 0) {
                mybluetooth.writebyte((byte) 'Y');
                Etat_bouton_2 = 1;
            } else {
                mybluetooth.writebyte((byte) 'Z');
                Etat_bouton_2 = 0;
            }
        }));
        Bouton_3 = findViewById(R.id.bouton_3);
        Bouton_3.setOnClickListener((v -> {
            if (Etat_bouton_3 == 0) {
                mybluetooth.writebyte((byte) 'a');
                Etat_bouton_3 = 1;
            } else {
                mybluetooth.writebyte((byte) 'b');
                Etat_bouton_3 = 0;
            }
        }));
        Bouton_4 = findViewById(R.id.bouton_4);
        Bouton_4.setOnClickListener((v -> {
            if (Etat_bouton_4 == 0) {
                mybluetooth.writebyte((byte) 'c');
                Etat_bouton_4 = 1;
            } else {
                mybluetooth.writebyte((byte) 'd');
                Etat_bouton_4 = 0;
            }
        }));
        Bouton_5 = findViewById(R.id.bouton_5);
        Bouton_5.setOnClickListener((v -> {
            if (Etat_bouton_5 == 0) {
                mybluetooth.writebyte((byte) 'e');
                Etat_bouton_5 = 1;
            } else {
                mybluetooth.writebyte((byte) 'f');
                Etat_bouton_5 = 0;
            }
        }));
        Bouton_6 = findViewById(R.id.bouton_6);
        Bouton_6.setOnClickListener((v -> {
            if (Etat_bouton_6 == 0) {
                mybluetooth.writebyte((byte) 'g');
                Etat_bouton_6 = 1;
            } else {
                mybluetooth.writebyte((byte) 'h');
                Etat_bouton_6 = 0;
            }
        }));
        Bouton_7 = findViewById(R.id.bouton_7);
        Bouton_7.setOnClickListener((v -> {
            if (Etat_bouton_7 == 0) {
                mybluetooth.writebyte((byte) 'i');
                Etat_bouton_7 = 1;
            } else {
                mybluetooth.writebyte((byte) 'j');
                Etat_bouton_7 = 0;
            }
        }));
        Bouton_8 = findViewById(R.id.bouton_8);
        Bouton_8.setOnClickListener((v -> {
            if (Etat_bouton_8 == 0) {
                mybluetooth.writebyte((byte) 'k');
                Etat_bouton_8 = 1;
            } else {
                mybluetooth.writebyte((byte) 'l');
                Etat_bouton_8 = 0;
            }
        }));
        Bouton_9 = findViewById(R.id.bouton_9);
        Bouton_9.setOnClickListener((v -> {
            byte bouton_9_valeur;
            if (Etat_bouton_9 == 0) {
                bouton_9_valeur = 'm';
                Etat_bouton_9 = 1;
            } else {
                bouton_9_valeur = 'n';
                Etat_bouton_9 = 0;
            }
            mybluetooth.writebyte(bouton_9_valeur);
        }));

        //=== Définir le handler  ==================================================================
        my_handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                if (msg.what == STATUS) {
                    tv_status.setText((String) msg.obj);
                }
            }
        };

        // Remplacez VOTRE_CODE_DE_PERMISSION par REQUEST_BLUETOOTH_PERMISSION
        // ===========  affecter un identificateur au module bluetooth ============================
        BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        my_bt_adapter = bluetoothManager.getAdapter();

        //my_bt_adapter = BluetoothAdapter.getDefaultAdapter();

        if (my_bt_adapter == null) {
            tv_status.setText(getString(R.string.interface_bluetooth));
        }

        // ============== démarrer le bluetooth s'il ne l'est pas =============================

        try {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT)
                    != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.BLUETOOTH_CONNECT}, 1);
            } else {
                // Permission déjà accordée : tu peux utiliser Bluetooth
                if (!my_bt_adapter.isEnabled()) {
                    Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(turnOn, 0);
                    finish();
                }
            }

        }
        catch (ArithmeticException e) {
            Intent intent = new Intent(this, Page_Outils.class);
            startActivity(intent);

        }
        // =============== afficher la liste des équipements associé dans la liste =================
        Set<BluetoothDevice> pairedDevices = my_bt_adapter.getBondedDevices();
        if (pairedDevices.isEmpty()) tv_status.setText(getString(R.string.liste));// Liste Vide


        ArrayList<String> pairedlist = new ArrayList<>();
        for (BluetoothDevice bt : pairedDevices)
            pairedlist.add(bt.getName() + "\n" + bt.getAddress());

        ArrayAdapter<String> my_list_adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pairedlist);

        lv_devlist.setAdapter(my_list_adapter);
        tv_status.setText(getString(R.string.Appareil)); // Choisir un device dans la liste
        lv_devlist.setOnItemClickListener(devlist_listener);



    }



    //======= Listner de la liste =====================================================
    private final AdapterView.OnItemClickListener devlist_listener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView av, View v, int arg2, long arg3) {
            String devchoisi = ((TextView) v).getText().toString();
            dev_address = devchoisi.substring(devchoisi.length() - 17);
            tv_status.setText(getString(R.string.connexion)); // texte connexion en cours
            // démarrer le Thread qui gère la connexion
            mybluetooth = new Outils_telecommande.MyBluetoothClass();
            mybluetooth.start();
        }
    };

    // définition de la classe BluetoothClass pour (connexion , lecture , écriture, déconnexion)
    class MyBluetoothClass extends Thread {
        @SuppressLint("MissingPermission")
        public void run() {
            boolean SOCKET_OK, CONX_OK, OUTS_OK, INPS_OK;

            // créer un objet bluetooth pour notre HC05
            BluetoothDevice HC05 = my_bt_adapter.getRemoteDevice(dev_address);
            //Créer un soket (pipeline) pour communiquer avec notre HC05
            SOCKET_OK = true;
            try {
                my_bt_soket = HC05.createInsecureRfcommSocketToServiceRecord(myUUID);
            } catch (IOException e) {
                //dev_address=null;
                SOCKET_OK = false;
            }
            if (SOCKET_OK) {
                // connecter le soket
                CONX_OK = true;
                try {
                    my_bt_soket.connect();
                } catch (IOException e) {
                    CONX_OK = false;
                }
                if (CONX_OK) {
                    OUTS_OK = true;
                    try {
                        my_bt_out_stream = my_bt_soket.getOutputStream();
                    } catch (IOException e) {
                        my_handler.obtainMessage(STATUS, -1, -1, "Echec création OUTPUT stream").sendToTarget();
                        OUTS_OK = false;
                    }
                    INPS_OK = true;
                    try {
                         my_bt_soket.getInputStream();
                    } catch (IOException e) {
                        my_handler.obtainMessage(STATUS, -1, -1, "Echec création INPUT STREAM").sendToTarget();
                        INPS_OK = false;
                    }

                    if (OUTS_OK && INPS_OK)
                        my_handler.obtainMessage(STATUS, -1, -1, "Connecté").sendToTarget();
                    runOnUiThread(() -> {
                        ViewFlipper viewFlipper2 = findViewById(R.id.outil_telecommande);
                        //viewFlipper2.setDisplayedChild(viewFlipper2.indexOfChild(findViewById(R.id.outil_telecommande_manette)));
                        viewFlipper2.setDisplayedChild(viewFlipper2.indexOfChild(findViewById(R.id.relativelayout2)));
                        numView = 2;
                    });

                } else {
                    my_handler.obtainMessage(STATUS, -1, -1, getString(R.string.rebrancher_capteur)).sendToTarget();
                }
            } else {
                my_handler.obtainMessage(STATUS, -1, -1, "Echec création Soket COMM").sendToTarget();
            }

        }


        void writebyte(byte b) {
            try {
                my_bt_out_stream.write(b);
            } catch (IOException e) {
                my_handler.obtainMessage(STATUS, -1, -1, getString(R.string.erreur_write)).sendToTarget();
            }
        }

        void disconnect() {
            try {
                my_bt_soket.close();
            } catch (IOException e) {
                my_handler.obtainMessage(STATUS, -1, -1, getString(R.string.echec_deconnexion)).sendToTarget();
            }
        }

    }

    // ========================================================================================================================
    //                              Menu
    // ========================================================================================================================


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_telecommande, menu);
        final MenuItem item = menu.findItem(R.id.coeur_vide);

        SharedPreferences prefs = getSharedPreferences("coeur_telecommande", MODE_PRIVATE);
        String coeur = prefs.getString("coeur_telecommande", "Pas de favoris défini");

        if (coeur.isEmpty()){
            SharedPreferences.Editor editor = getSharedPreferences("coeur_telecommande", MODE_PRIVATE).edit();
            editor.putString("coeur_telecommande", "vide").apply();
        }

        Drawable drawable;
        Resources res = getResources();
        if (coeur.equals("plein")){ drawable = ResourcesCompat.getDrawable(res, R.drawable.coeur_plein, null);}
        else { drawable = ResourcesCompat.getDrawable(res, R.drawable.coeur_vide, null); }
        item.setIcon(drawable);

        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull  int[] grantResults) {
        if (requestCode == REQUEST_BLUETOOTH_PERMISSION) {
            // Vérifie si la permission a été accordée
            Intent intent = new Intent(this, Page_Outils.class);
            startActivity(intent);
        }
        // Gérer d'autres demandes de permissions si nécessaire
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @SuppressLint("UseCompatLoadingForColorStateLists")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Resources res = getResources();
        if (item.getItemId() ==R.id.nav_deconnection ){
            if (numView == 1) {
                Toast.makeText(this, getString(R.string.connexion_capteur_bluetooth), Toast.LENGTH_LONG).show(); // Connecter vous d'abord au capteur bluetooth
            }
            if (numView == 2) {
                Toast.makeText(this, getString(R.string.deconnexion_capteur_bluetooth), Toast.LENGTH_LONG).show(); // Bluetooth deconnecté
                mybluetooth.disconnect();
                finish();
            }
            return true;
        }
        if (item.getItemId()== R.id.nav_telecommande) {
            int[] listeButtonCouleur = {R.color.white, R.color.rouge, R.color.violet, R.color.bleu, R.color.orange, R.color.rose, R.color.bleu_clair, R.color.jaune, R.color.vert_clair, R.color.vert};

            Button[] listeButton = {Bouton_0, Bouton_1, Bouton_2, Bouton_3, Bouton_4, Bouton_5, Bouton_6, Bouton_7, Bouton_8, Bouton_9};
            ImageButton[] listeImageButton = {bouton_volplus,bouton_function,bouton_back,bouton_pause,bouton_next,bouton_descendre,bouton_volmoins,bouton_monter,bouton_eq,bouton_rept};

            int[] listeDrawable_1 = {R.drawable.telecommande_plus, R.drawable.telecommande_func_stop,   R.drawable.telecommande_fleche_gauche, R.drawable.telecommande_pause,  R.drawable.telecommande_fleche_droite, R.drawable.telecommande_descendre, R.drawable.telecommande_moins2, R.drawable.telecommande_monter, R.drawable.telecommande_eq, R.drawable.telecommande_st_rept};
            int[] listeDrawable_2 = {R.drawable.telecommande_plus, R.drawable.telecommande_soleil_haut, R.drawable.telecommande_power,         R.drawable.telecommande_moins2, R.drawable.telecommande_soleil_bas,    R.drawable.telecommande_speed,     R.drawable.telecommande_flash,  R.drawable.telecommande_fade,  R.drawable.outils_vide,     R.drawable.telecommande_multicouleur};

            Drawable drawable;

            if (numView == 1) {
                Toast.makeText(this, getString(R.string.premiere_connexion_capteur_bluetooth), Toast.LENGTH_LONG).show(); // Connecter vous d'abord au capteur bluetooth
            }


            if (numView == 2) {
                if (telecommandeSelect == 1) { // ------------ Telecommande classique ------------
                    for (int i = 0; i < listeButton.length; i += 1) {
                        listeButton[i].setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.white));
                        listeButton[i].setText(String.valueOf(i));
                    }

                    for (int i = 0; i < listeImageButton.length; i += 1) {
                        drawable = ResourcesCompat.getDrawable(res, listeDrawable_1[i], null);
                        listeImageButton[i].setImageDrawable(drawable);
                    }
                    telecommandeSelect -= 1;
                }
                if (telecommandeSelect == 2) { // ------------ Telecommande couleur ------------
                    for (int i = 0; i < listeButton.length; i += 1) {
                        listeButton[i].setBackgroundTintList(ContextCompat.getColorStateList(this, listeButtonCouleur[i]));
                        listeButton[i].setText("");
                    }

                    for (int i = 0; i < listeImageButton.length; i += 1) {
                        drawable = ResourcesCompat.getDrawable(res, listeDrawable_2[i], null);
                        listeImageButton[i].setImageDrawable(drawable);
                    }
                    telecommandeSelect -= 1;
                }
                if (telecommandeSelect == 0) { telecommandeSelect = 2; }
            }
            return true;
        }

        if (item.getItemId() == R.id.nav_tuto ) {
            Intent intent = new Intent(this, Outils_telecommande_tuto.class);
            startActivity(intent);
            return true;
        }

        if (item.getItemId() == R.id.coeur_vide){
            SharedPreferences prefs = getSharedPreferences("coeur_telecommande", MODE_PRIVATE);
            String coeur_telecommande = prefs.getString("coeur_telecommande", "No favorite defined");

            if (coeur_telecommande.equals("vide")) {
                Drawable drawable2 = ResourcesCompat.getDrawable(res, R.drawable.coeur_plein, null);
                item.setIcon(drawable2);
                stateHeart -= 1;

                SharedPreferences.Editor editor = getSharedPreferences("coeur_telecommande", MODE_PRIVATE).edit();
                editor.putString("coeur_telecommande", "plein").apply();
            }
            else {
                Drawable drawable2 = ResourcesCompat.getDrawable(res, R.drawable.coeur_vide, null);
                item.setIcon(drawable2);
                stateHeart -= 1;

                SharedPreferences.Editor editor = getSharedPreferences("coeur_telecommande", MODE_PRIVATE).edit();
                editor.putString("coeur_telecommande", "vide").apply();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);

    }


}











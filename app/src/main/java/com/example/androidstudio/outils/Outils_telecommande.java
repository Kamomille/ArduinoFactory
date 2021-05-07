package com.example.androidstudio.outils;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
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

import com.example.androidstudio.R;
import com.example.androidstudio.pages.Page_Outils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

public class Outils_telecommande extends AppCompatActivity {
    private TextView tv_status;
    private ListView lv_devlist=null;
    private ImageButton bouton_deconnecter,bouton_volplus,bouton_function,bouton_back,bouton_pause,bouton_next,bouton_descendre,bouton_volmoins,bouton_monter,bouton_eq,bouton_rept;
    private Button Bouton_0,Bouton_1,Bouton_2,Bouton_3,Bouton_4,Bouton_5,Bouton_6,Bouton_7,Bouton_8,Bouton_9;
    private BluetoothAdapter my_bt_adapter=null;
    private MyBluetoothClass mybluetooth=null;
    private BluetoothSocket my_bt_soket = null;
    private OutputStream my_bt_out_stream = null;
    private InputStream my_bt_inp_stream = null;
    private String dev_address;
    private int Etat=0;
    ArrayList pairedlist = new ArrayList();
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private Handler my_handler;
    private final static int STATUS = 1;
    int Etat_volplus = 0,Etat_function = 0,Etat_back = 0,Etat_pause = 0,Etat_next = 0,Etat_descendre = 0,Etat_volmoins = 0,Etat_monter = 0,Etat_bouton_0 = 0,Etat_eq = 0,Etat_rept = 0,Etat_bouton_1 = 0,Etat_bouton_2 = 0,Etat_bouton_3 = 0,Etat_bouton_4 = 0,Etat_bouton_5 = 0,Etat_bouton_6 = 0,Etat_bouton_7 = 0,Etat_bouton_8 = 0,Etat_bouton_9 = 0;

    int telecommandeSelect = 2;
    int numView = 1;
    private int stateHeart = 1;

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outils_telecommande);

        ViewFlipper viewFlipper = (ViewFlipper) findViewById(R.id.outil_telecommande);
        viewFlipper.setDisplayedChild(2);
        
        //viewFlipper.setDisplayedChild(1);

        //if (viewFlipper.getDisplayedChild()==1 & Etat==1){
            //mybluetooth.disconnect();
            //openActivtity_outils();
        //}

        tv_status = (TextView) findViewById(R.id.TV_STATUS);
        lv_devlist = (ListView) findViewById(R.id.LV_DEVLIST);


        bouton_deconnecter= findViewById(R.id.deconnecter);
        bouton_deconnecter.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mybluetooth.disconnect();
                //openActivtity_outils();

            }
        }));
        bouton_volplus= findViewById(R.id.Volplus);
        bouton_volplus.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                byte volplus_valeur = 'A';
                if (Etat_volplus==0) {
                    volplus_valeur = 'A';
                    Etat_volplus=1;
                } else {
                    volplus_valeur = 'B';
                    Etat_volplus=0;
                }
                mybluetooth.writebyte(volplus_valeur);
            }
        }));
        bouton_function = findViewById(R.id.function);
        bouton_function.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                byte function_valeur = 'A';
                if (Etat_function==0) {
                    function_valeur = 'C';
                    Etat_function=1;
                } else {
                    function_valeur = 'D';
                    Etat_function=0;
                }
                mybluetooth.writebyte(function_valeur);
            }
        }));
        bouton_back = findViewById(R.id.back);
        bouton_back.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                byte back_valeur = 'A';
                if (Etat_back==0) {
                    back_valeur = 'E';
                    Etat_back=1;
                } else {
                    back_valeur = 'F';
                    Etat_back=0;
                }
                mybluetooth.writebyte(back_valeur);
            }
        }));
        bouton_pause = findViewById(R.id.pause);
        bouton_pause.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                byte pause_valeur = 'A';
                if (Etat_pause==0) {
                    pause_valeur = 'G';
                    Etat_pause=1;
                } else {
                    pause_valeur = 'H';
                    Etat_pause=0;
                }
                mybluetooth.writebyte(pause_valeur);
            }
        }));
        bouton_next = findViewById(R.id.next);
        bouton_next.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                byte next_valeur = 'A';
                if (Etat_next==0) {
                    next_valeur = 'I';
                    Etat_next=1;
                } else {
                    next_valeur = 'J';
                    Etat_next=0;
                }
                mybluetooth.writebyte(next_valeur);
            }
        }));
        bouton_descendre = findViewById(R.id.descendre);
        bouton_descendre.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                byte descendre_valeur = 'A';
                if (Etat_descendre==0) {
                    descendre_valeur = 'K';
                    Etat_descendre=1;
                } else {
                    descendre_valeur = 'L';
                    Etat_descendre=0;
                }
                mybluetooth.writebyte(descendre_valeur);
            }
        }));
        bouton_volmoins = findViewById(R.id.volumemoins);
        bouton_volmoins.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                byte volumemoins_valeur = 'A';
                if (Etat_volmoins==0) {
                    volumemoins_valeur= 'M';
                    Etat_volmoins=1;
                } else {
                    volumemoins_valeur = 'N';
                    Etat_volmoins=0;
                }
                mybluetooth.writebyte(volumemoins_valeur);
            }
        }));
        bouton_monter = findViewById(R.id.monter);
        bouton_monter.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                byte monter_valeur = 'A';
                if (Etat_monter==0) {
                    monter_valeur= 'O';
                    Etat_monter=1;
                } else {
                    monter_valeur = 'P';
                    Etat_monter=0;
                }
                mybluetooth.writebyte(monter_valeur);
            }
        }));
        Bouton_0 = findViewById(R.id.bouton_0);
        Bouton_0.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                byte bouton_0_valeur = 'A';
                if (Etat_bouton_0==0) {
                    bouton_0_valeur= 'Q';
                    Etat_bouton_0=1;
                } else {
                    bouton_0_valeur = 'R';
                    Etat_bouton_0=0;
                }
                mybluetooth.writebyte(bouton_0_valeur);
            }
        }));
        bouton_eq = findViewById(R.id.egaliser);
        bouton_eq.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                byte eq_valeur = 'A';
                if (Etat_eq==0) {
                    eq_valeur = 'S';
                    Etat_eq=1;
                } else {
                    eq_valeur = 'T';
                    Etat_eq=0;
                }
                mybluetooth.writebyte(eq_valeur);
            }
        }));
        bouton_rept = findViewById(R.id.repeter);
        bouton_rept.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                byte rept_valeur = 'A';
                if (Etat_rept==0) {
                    rept_valeur = 'U';
                    Etat_rept=1;
                } else {
                    rept_valeur = 'V';
                    Etat_rept=0;
                }
                mybluetooth.writebyte(rept_valeur);
            }
        }));
        Bouton_1 = findViewById(R.id.bouton_1);
        Bouton_1.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                byte bouton_1_valeur = 'A';
                if (Etat_bouton_1==0) {
                    bouton_1_valeur= 'W';
                    Etat_bouton_1=1;
                } else {
                    bouton_1_valeur = 'X';
                    Etat_bouton_1=0;
                }
                mybluetooth.writebyte(bouton_1_valeur);
            }
        }));
        Bouton_2 = findViewById(R.id.bouton_2);
        Bouton_2.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                byte bouton_2_valeur = 'A';
                if (Etat_bouton_2==0) {
                    bouton_2_valeur= 'Y';
                    Etat_bouton_2=1;
                } else {
                    bouton_2_valeur = 'Z';
                    Etat_bouton_2=0;
                }
                mybluetooth.writebyte(bouton_2_valeur);
            }
        }));
        Bouton_3 = findViewById(R.id.bouton_3);
        Bouton_3.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                byte bouton_3_valeur = 'A';
                if (Etat_bouton_3==0) {
                    bouton_3_valeur= 'a';
                    Etat_bouton_3=1;
                } else {
                    bouton_3_valeur = 'b';
                    Etat_bouton_3=0;
                }
                mybluetooth.writebyte(bouton_3_valeur);
            }
        }));
        Bouton_4 = findViewById(R.id.bouton_4);
        Bouton_4.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                byte bouton_4_valeur = 'A';
                if (Etat_bouton_4==0) {
                    bouton_4_valeur= 'c';
                    Etat_bouton_4=1;
                } else {
                    bouton_4_valeur = 'd';
                    Etat_bouton_4=0;
                }
                mybluetooth.writebyte(bouton_4_valeur);
            }
        }));
        Bouton_5= findViewById(R.id.bouton_5);
        Bouton_5.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                byte bouton_5_valeur = 'A';
                if (Etat_bouton_5==0) {
                    bouton_5_valeur= 'e';
                    Etat_bouton_5=1;
                } else {
                    bouton_5_valeur = 'f';
                    Etat_bouton_5=0;
                }
                mybluetooth.writebyte(bouton_5_valeur);
            }
        }));
        Bouton_6= findViewById(R.id.bouton_6);
        Bouton_6.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                byte bouton_6_valeur = 'A';
                if (Etat_bouton_6==0) {
                    bouton_6_valeur= 'g';
                    Etat_bouton_6=1;
                } else {
                    bouton_6_valeur = 'h';
                    Etat_bouton_6=0;
                }
                mybluetooth.writebyte(bouton_6_valeur);
            }
        }));
        Bouton_7= findViewById(R.id.bouton_7);
        Bouton_7.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                byte bouton_7_valeur = 'A';
                if (Etat_bouton_7==0) {
                    bouton_7_valeur= 'i';
                    Etat_bouton_7=1;
                } else {
                    bouton_7_valeur = 'j';
                    Etat_bouton_7=0;
                }
                mybluetooth.writebyte(bouton_7_valeur);
            }
        }));
        Bouton_8= findViewById(R.id.bouton_8);
        Bouton_8.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                byte bouton_8_valeur = 'A';
                if (Etat_bouton_8==0) {
                    bouton_8_valeur= 'k';
                    Etat_bouton_8=1;
                } else {
                    bouton_8_valeur = 'l';
                    Etat_bouton_8=0;
                }
                mybluetooth.writebyte(bouton_8_valeur);
            }
        }));
        Bouton_9= findViewById(R.id.bouton_9);
        Bouton_9.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                byte bouton_9_valeur = 'A';
                if (Etat_bouton_9==0) {
                    bouton_9_valeur= 'm';
                    Etat_bouton_9=1;
                } else {
                    bouton_9_valeur = 'n';
                    Etat_bouton_9=0;
                }
                mybluetooth.writebyte(bouton_9_valeur);
            }
        }));

        //=== Définir le handler  ==================================================================
        my_handler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case STATUS:
                        tv_status.setText((String) (msg.obj));
                        break;
                }
            }
        };


        // ===========  affecter un identificateur au module bluetooth ============================
        my_bt_adapter = BluetoothAdapter.getDefaultAdapter();
        if (my_bt_adapter == null) {
            tv_status.setText("Pas d'interface Bluetooth");
        }

        // ============== démarrer le bluetooth s'il ne l'est pas =============================
        if (!my_bt_adapter.isEnabled()) {
            Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(turnOn, 0);
        }
        while (!my_bt_adapter.isEnabled()) ; // attendre que le démarrage soit effectif

        // =============== afficher la liste des équipements associé dans la liste =================
        Set<BluetoothDevice> pairedDevices = my_bt_adapter.getBondedDevices();
        if (pairedDevices.isEmpty()) tv_status.setText("Liste Vide");

        ArrayList pairedlist = new ArrayList();
        for (BluetoothDevice bt : pairedDevices)
            pairedlist.add(bt.getName() + "\n" + bt.getAddress());
        ArrayAdapter my_list_adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, pairedlist);
        lv_devlist.setAdapter(my_list_adapter);
        tv_status.setText("Choisir un device dans la liste");
        lv_devlist.setOnItemClickListener(devlist_listener);
        if (pairedDevices.isEmpty()) {
            tv_status.setText("Liste Vide");
            pairedlist.add("Aller dans les paramètres bluetooth de votre téléphone et appareiller le capteur bluetooth");
            ArrayAdapter my_list_adapter2 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, pairedlist);
            lv_devlist.setAdapter(my_list_adapter2);
            lv_devlist.setSelector(android.R.color.transparent);
            lv_devlist.setOnItemClickListener(null);
        };

    }

    //======= Listner de la liste =====================================================
    private AdapterView.OnItemClickListener devlist_listener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView av, View v, int arg2, long arg3) {
            String devchoisi = ((TextView) v).getText().toString();
            dev_address = devchoisi.substring(devchoisi.length() - 17);
            tv_status.setText("Connexion en cours");
            // démarrer le Thread qui gère la connexion
            mybluetooth = new Outils_telecommande.MyBluetoothClass();
            mybluetooth.start();
        }
    };

    // définition de la classe BluetoothClass pour (connexion , lecture , écriture, déconnexion)
    class MyBluetoothClass extends Thread {
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
                    //SystemClock.sleep(500);
                    //my_bt_soket = HC05.createInsecureRfcommSocketToServiceRecord(myUUID2);
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
                        my_bt_inp_stream = my_bt_soket.getInputStream();
                    } catch (IOException e) {
                        my_handler.obtainMessage(STATUS, -1, -1, "Echec création INPUT STREAM").sendToTarget();
                        INPS_OK = false;
                    }
                    //ceci est un commantaire
                    if (OUTS_OK && INPS_OK)
                        my_handler.obtainMessage(STATUS, -1, -1, "Connecté").sendToTarget();
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            ViewFlipper viewFlipper2 = (ViewFlipper) findViewById(R.id.outil_telecommande);
                            //viewFlipper2.setDisplayedChild(viewFlipper2.indexOfChild(findViewById(R.id.outil_telecommande_manette)));
                            viewFlipper2.setDisplayedChild(viewFlipper2.indexOfChild(findViewById(R.id.relativelayout2)));
                            numView =2;
                        }
                    });

                } else {
                    my_handler.obtainMessage(STATUS, -1, -1, "Rebrancher le capteur et Réessayer").sendToTarget();
                }
            } else {
                my_handler.obtainMessage(STATUS, -1, -1, "Echec création Soket COMM").sendToTarget();
            }

        }


        void writebyte(byte b) {
            try {
                my_bt_out_stream.write(b);
            } catch (IOException e) {
                my_handler.obtainMessage(STATUS, -1, -1, "Erreur dans writebyte").sendToTarget();
            }
        }

        int readbyte() {
            int b = 0;
            try {
                b = my_bt_inp_stream.read();
                return b;
            } catch (IOException e) {
                my_handler.obtainMessage(STATUS, -1, -1, "Erreur dans readbyte").sendToTarget();
                return -1;
            }
        }

        int available() {
            int b = 0;
            try {
                b = my_bt_inp_stream.available();
                return b;
            } catch (IOException e) {
                my_handler.obtainMessage(STATUS, -1, -1, "Erreur dans available").sendToTarget();
                return -1;
            }
        }

        int readbytes(byte[] inpbuff) {
            int b = 0;
            try {
                b = my_bt_inp_stream.read(inpbuff);
                return b;
            } catch (IOException e) {
                my_handler.obtainMessage(STATUS, -1, -1, "Erreur de lecture").sendToTarget();
                return 0;
            }
        }

        void disconnect() {
            try {
                my_bt_soket.close();
            } catch (IOException e) {
                my_handler.obtainMessage(STATUS, -1, -1, "Echec Déconnexion").sendToTarget();
            }
        }

    }
    public void openActivtity_outils(){
        Intent intent = new Intent(this, Page_Outils.class);
        startActivity(intent);
    }
    // ========================================================================================================================
    //                              Menu
    // ========================================================================================================================


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_telecommande, menu);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_deconnection: // ------------------------------------------------------------------------------------------
                if (numView == 1) {
                    Toast.makeText(this, "Connecter vous d'abord au capteur bluetooth", Toast.LENGTH_LONG).show();
                }
                if (numView == 2) {
                    Toast.makeText(this, "Bluetooth deconnecté", Toast.LENGTH_LONG).show();
                    mybluetooth.disconnect();
                    finish();
                    //openActivtity_outils();
                }
                return true;

            case R.id.nav_telecommande: // ------------------------------------------------------------------------------------------


                int[] listeButtonCouleur = {R.color.white, R.color.rouge, R.color.violet, R.color.bleu, R.color.orange, R.color.rose, R.color.bleu_clair, R.color.jaune, R.color.vert_clair, R.color.vert};

                Button[] listeButton = {Bouton_0, Bouton_1, Bouton_2, Bouton_3, Bouton_4, Bouton_5, Bouton_6, Bouton_7, Bouton_8, Bouton_9};
                ImageButton[] listeImageButton = {bouton_volplus,bouton_function,bouton_back,bouton_pause,bouton_next,bouton_descendre,bouton_volmoins,bouton_monter,bouton_eq,bouton_rept};

                int[] listeDrawable_1 = {R.drawable.telecommande_plus, R.drawable.telecommande_func_stop,   R.drawable.telecommande_fleche_gauche, R.drawable.telecommande_pause,  R.drawable.telecommande_fleche_droite, R.drawable.telecommande_descendre, R.drawable.telecommande_moins2, R.drawable.telecommande_monter, R.drawable.telecommande_eq, R.drawable.telecommande_st_rept};
                int[] listeDrawable_2 = {R.drawable.telecommande_plus, R.drawable.telecommande_soleil_haut, R.drawable.telecommande_power,         R.drawable.telecommande_moins2, R.drawable.telecommande_soleil_bas,    R.drawable.telecommande_speed,     R.drawable.telecommande_flash,  R.drawable.telecommande_fade,  R.drawable.outils_vide,     R.drawable.telecommande_multicouleur};

                Resources res = getResources();
                Drawable drawable;

                if (numView == 1) {
                    Toast.makeText(this, "Connecter vous d'abord au capteur bluetooth", Toast.LENGTH_LONG).show();
                }


                if (numView == 2) {

                    if (telecommandeSelect == 1) { // ------------ Telecommande classique ------------

                        for (int i = 0; i < listeButton.length; i += 1) {
                            listeButton[i].setBackgroundTintList(getResources().getColorStateList(R.color.white));
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
                            listeButton[i].setBackgroundTintList(getResources().getColorStateList(listeButtonCouleur[i]));
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

            case R.id.nav_tuto: // ------------------------------------------------------------------------------------------
                Intent intent = new Intent(this, Outils_telecommande_tuto.class);
                startActivity(intent);
                return true;

            case R.id.coeur_vide: // ------------------------------------------------------------------------------------------

                Resources res2 = getResources();

                if (stateHeart == 1) {
                    Drawable drawable2 = ResourcesCompat.getDrawable(res2, R.drawable.coeur_plein, null);
                    item.setIcon(drawable2);
                    stateHeart -= 1;
                }
                if(stateHeart == 2){
                    Drawable drawable2 = ResourcesCompat.getDrawable(res2, R.drawable.coeur_vide, null);
                    item.setIcon(drawable2);
                    stateHeart -= 1;
                }
                if (stateHeart == 0) { stateHeart = 2; }
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


}











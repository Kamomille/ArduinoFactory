package com.example.androidstudio.outils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.androidstudio.R;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Outils_telecommande extends AppCompatActivity {
    private TextView tv_status;
    private TextView telecommande;
    private ListView lv_devlist;
    private ImageButton volplus;
    private ImageButton bouton_deconnecter;
    private ImageButton bouton_function;
    private LinearLayout linear2;

    private BluetoothAdapter my_bt_adapter;
    private MyBluetoothClass mybluetooth;
    private BluetoothSocket my_bt_soket = null;
    private OutputStream my_bt_out_stream = null;
    private InputStream my_bt_inp_stream = null;
    private String dev_address;
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private Handler my_handler;
    private final static int STATUS = 1;
    int Etat_volplus = 0;
    int Etat_function = 0;

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outils_telecommande);
        ViewFlipper viewFlipper = (ViewFlipper) findViewById(R.id.outil_telecommande);
        //viewFlipper.setDisplayedChild(2);
        viewFlipper.setDisplayedChild(1);

        tv_status = (TextView) findViewById(R.id.TV_STATUS);
        telecommande = (TextView) findViewById(R.id.Telecommande);
        lv_devlist = (ListView) findViewById(R.id.LV_DEVLIST);

        bouton_function = findViewById(R.id.deconnecter);
        bouton_function.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                byte ledcomm = 'A';
                if (Etat_function==0) {
                    ledcomm = 'C';
                    Etat_function=1;
                } else {
                    ledcomm = 'D';
                    Etat_function=0;
                }
                mybluetooth.writebyte(ledcomm);
            }
        }));
        bouton_deconnecter= findViewById(R.id.deconnecter);
        bouton_deconnecter.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                telecommande.setText("Déconnecté");
                mybluetooth.disconnect();
                SystemClock.sleep(1000);
                tv_status.setText("Choisir un device dans la liste");
                ViewFlipper viewFlipper3 = (ViewFlipper) findViewById(R.id.outil_telecommande);
                viewFlipper3.setDisplayedChild(2);
            }
        }));
        volplus= findViewById(R.id.Volplus);
        volplus.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                byte ledcomm = 'A';
                if (Etat_volplus==0) {
                    ledcomm = 'A';
                    Etat_volplus=1;
                } else {
                    ledcomm = 'B';
                    Etat_volplus=0;
                }
                mybluetooth.writebyte(ledcomm);
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
        if (pairedDevices.isEmpty()) {
            tv_status.setText("Liste Vide");
            pairedlist.add("Aller dans les paramètres bluetooth de votre téléphone et appareiller le capteur bluetooth");
            ArrayAdapter my_list_adapter2 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, pairedlist);
            lv_devlist.setAdapter(my_list_adapter2);
        };
        lv_devlist.setOnItemClickListener(devlist_listener);

    }

    //======= Listner de la liste =====================================================
    private AdapterView.OnItemClickListener devlist_listener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView av, View v, int arg2, long arg3) {
            String devchoisi = ((TextView) v).getText().toString();
            dev_address = devchoisi.substring(devchoisi.length() - 17);
            //dev_name = devchoisi.substring(0, devchoisi.length() - 17);
            tv_status.setText("CONNEXION EN COURS");
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
                        my_bt_inp_stream = my_bt_soket.getInputStream();
                    } catch (IOException e) {
                        my_handler.obtainMessage(STATUS, -1, -1, "Echec création INPUT STREAM").sendToTarget();

                        INPS_OK = false;
                    }
                    //ceci est un commantaire
                    if (OUTS_OK && INPS_OK)
                        my_handler.obtainMessage(STATUS, -1, -1, "Connecté").sendToTarget();
                    ViewFlipper viewFlipper2 = (ViewFlipper) findViewById(R.id.outil_telecommande);
                    //viewFlipper2.setDisplayedChild(viewFlipper2.indexOfChild(findViewById(R.id.outil_telecommande_manette)));
                    viewFlipper2.setDisplayedChild(viewFlipper2.indexOfChild(findViewById(R.id.relativelayout2)));
                } else {
                    my_handler.obtainMessage(STATUS, -1, -1, "Echec Connexion").sendToTarget();
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

}

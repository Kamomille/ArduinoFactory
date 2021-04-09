package com.example.androidstudio.outils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.app.FragmentTransaction;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
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

public class Outils_telecommande_Bluetooth extends AppCompatActivity {
    private TextView tv_status;
    private TextView tv_lm35;
    private TextView tv_sht31_t;
    private TextView tv_sht31_h;
    private TextView tv_lm75;
    private TextView tv_ds1820;
    private CheckBox ckb_led1;
    private CheckBox ckb_led2;
    private CheckBox ckb_led3;
    private ListView lv_devlist;
    private Button bouton_led;
    private Fragment fragment1;

    private BluetoothAdapter my_bt_adapter;
    private MyBluetoothClass mybluetooth;
    private BluetoothSocket my_bt_soket = null;
    private OutputStream my_bt_out_stream = null;
    private InputStream my_bt_inp_stream = null;
    private String dev_address;
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private Handler my_handler;
    private final static int STATUS = 1;

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outils_telecommande__bluetooth);
        ViewFlipper viewFlipper = (ViewFlipper) findViewById(R.id.outil_telecommande_bluetooth);
        viewFlipper.setDisplayedChild(2);
        //viewFlipper.setDisplayedChild(viewFlipper.indexOfChild(findViewById(R.id.outil_telecommande_manette)));

        tv_status = (TextView) findViewById(R.id.TV_STATUS);
        lv_devlist = (ListView) findViewById(R.id.LV_DEVLIST);
        ckb_led1 = (CheckBox) findViewById(R.id.CKB_LED);
        tv_lm35 = (TextView) findViewById(R.id.TV_STATUS);
        bouton_led= findViewById(R.id.Led);
        bouton_led.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                byte ledcomm = 'A';
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
            mybluetooth = new MyBluetoothClass();
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
                    if (OUTS_OK && INPS_OK)
                        my_handler.obtainMessage(STATUS, -1, -1, "Connecté").sendToTarget();
                    ViewFlipper viewFlipper = (ViewFlipper) findViewById(R.id.outil_telecommande_bluetooth);
                    viewFlipper.setDisplayedChild(viewFlipper.indexOfChild(findViewById(R.id.outil_telecommande_manette)));
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


    public void lm35(View view) {
        tv_lm35.setText("      ");
        byte[] inpbuff = new byte[32];
        int lsb = 0, msb = 0, M;
        float T;
        while (mybluetooth.available() > 0) mybluetooth.readbytes(inpbuff); // vider le buffer
        mybluetooth.writebyte((byte) 'C');
        lsb = mybluetooth.readbyte();
        msb = mybluetooth.readbyte();
        M = ((msb & 0xff) << 8) | (lsb & 0xFF);
        T = M * (float) 110 / (float) 1023;
        tv_lm35.setText(String.format("%.2f   °C", T));
    }

    public void ledcommand(View VW) {
        byte ledcomm = '0';
        if (ckb_led1.isChecked()) {
            ledcomm = 'A';
        } else {
            ledcomm = 'B';
        }
        mybluetooth.writebyte(ledcomm);
    }

    public void deconnecter(View view) {
        mybluetooth.disconnect();
        tv_status.setText("Déconnecté");
    }
    public void openbouton_telecommande(){
        Intent Outils_telecommande_manette_intent= new Intent(this,Outils_telecommande_Manette.class);
        startActivity(Outils_telecommande_manette_intent);

    }


}
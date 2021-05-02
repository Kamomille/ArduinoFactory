package com.example.androidstudio.outils;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.example.androidstudio.R;
import com.google.android.material.tabs.TabLayout;
import static android.graphics.Color.parseColor;

public class Outils_resistance extends AppCompatActivity implements View.OnClickListener {

    // ========================================================================================================================
    //                              Déclaration et initialisation
    // ========================================================================================================================

    Button liste_CarreCouleur[] = new Button[12];

    private ImageButton buttonCroix, boutonInfo;
    private LinearLayout layoutPopup, layoutPaletteCouleur;
    private ImageButton resistanceColor1,resistanceColor2,resistanceColor3,resistanceColor4, resistanceColor5,resistanceColor6;
    private LinearLayout bande1, bande2, bande3, bande4, bande5, bande6;
    private ImageButton button_flecheH1, button_flecheB1;
    private ImageButton button_flecheH2, button_flecheB2;
    private ImageButton button_flecheH3, button_flecheB3;
    private ImageButton button_flecheH4, button_flecheB4;
    private ImageButton button_flecheH5, button_flecheB5;
    private ImageButton button_flecheH6, button_flecheB6;
    private TextView textView, textView_TCR, nomBande;
    private int indiceListe = 11;
    private int NbBandeSelect = 4, NbBandeclick =0;
    private String a,b,c,d,e,l;

    private int stateHeart = 1;

    private String[][] listeCouleur = {
            //                       CS   multi  tol    TCR
            //si 4 bandes :         1et2   3     4
            //si 5 bandes :         1,2,3  4     5
            //si 6 bandes :         1,2,3  4     5       6
            {"#000000",    "0",  "0",   "20", "1000"}, //noir 0
            {"#CC6633",    "1",  "1",    "1",  "100"}, //marron 1
            {"#FE0000",    "2",  "2",    "2",   "50"}, //rouge 2
            {"#FE8000",    "3",  "3", "1000",   "15"}, //orange 3
            {"#FEFE00",    "4",  "4", "1000",   "25"}, //jaune 4
            {"#00EA00",    "5",  "5",  "0,5", "1000"}, //vert 5
            {"#0000FE",    "6",  "6", "0,25",   "10"}, //bleu 6
            {"#8000FE",    "7",  "7",  "0,1",    "5"}, //violet 7
            {"#999999",    "8",  "8", "0,05", "1000"}, //gris 8
            {"#FFFFFF",    "9",  "9", "1000",    "1"}, //blanc 9
            {"#CCCCCC", "1000", "-2",   "10", "1000"}, //argent 10
            {"#FFD700", "1000", "-1",    "5", "1000"}, //or 11
    }; // Remarque : je met 1000 pour signifier espace vide


    // ========================================================================================================================
    //                              Sauvegarde des valeurs lors de la rotation
    // ========================================================================================================================

    public static final String BUNDLE_STATE_listeCompteur= "currentListeCompteur";
    public static final String BUNDLE_STATE_NbBandeSelect = "currentNbBandeSelect";

    int[] listeCompteur = {2,2,2,2,2,2}; //car 6 bande max et car on commence avec la couleur rouge


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putIntArray(BUNDLE_STATE_listeCompteur, listeCompteur);
        outState.putInt(BUNDLE_STATE_NbBandeSelect, NbBandeSelect);
        super.onSaveInstanceState(outState);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outils_resistance);

        // bouton retour
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        if (savedInstanceState != null) {
            listeCompteur = savedInstanceState.getIntArray(BUNDLE_STATE_listeCompteur);
            NbBandeSelect = savedInstanceState.getInt(BUNDLE_STATE_NbBandeSelect);
        } else {
            listeCompteur = new int[]{2, 2, 2, 2, 2, 2};
            NbBandeSelect = 4;
        }

        // ========================================================================================================================
        //                              ID -> relier xml au code java
        // ========================================================================================================================

        textView = (TextView)findViewById(R.id.textView);
        layoutPopup = (LinearLayout) findViewById(R.id.layoutPopup);
        layoutPaletteCouleur = (LinearLayout) findViewById(R.id.layoutPaletteCouleur);
        textView_TCR = (TextView)findViewById(R.id.textView_TCR);
        nomBande = (TextView)findViewById(R.id.nomBande);
        buttonCroix = (ImageButton) findViewById(R.id.buttonCroix);
        boutonInfo = (ImageButton) findViewById(R.id.boutonInfo);


        liste_CarreCouleur[0] = (Button) findViewById(R.id.carreCouleur_1);
        liste_CarreCouleur[1] = (Button) findViewById(R.id.carreCouleur_2);
        liste_CarreCouleur[2] = (Button) findViewById(R.id.carreCouleur_3);
        liste_CarreCouleur[3] = (Button) findViewById(R.id.carreCouleur_4);
        liste_CarreCouleur[4] = (Button) findViewById(R.id.carreCouleur_5);
        liste_CarreCouleur[5] = (Button) findViewById(R.id.carreCouleur_6);
        liste_CarreCouleur[6] = (Button) findViewById(R.id.carreCouleur_7);
        liste_CarreCouleur[7] = (Button) findViewById(R.id.carreCouleur_8);
        liste_CarreCouleur[8] = (Button) findViewById(R.id.carreCouleur_9);
        liste_CarreCouleur[9] = (Button) findViewById(R.id.carreCouleur_10);
        liste_CarreCouleur[10] = (Button) findViewById(R.id.carreCouleur_11);
        liste_CarreCouleur[11] = (Button) findViewById(R.id.carreCouleur_12);
        for(int i=0; i<12; i++){ liste_CarreCouleur[i].setOnClickListener(this); }

        bande1 = (LinearLayout) findViewById(R.id.bande1);
        bande2 = (LinearLayout) findViewById(R.id.bande2);
        bande3 = (LinearLayout) findViewById(R.id.bande3);
        bande4 = (LinearLayout) findViewById(R.id.bande4);
        bande5 = (LinearLayout) findViewById(R.id.bande5);
        bande6 = (LinearLayout) findViewById(R.id.bande6);

        button_flecheH1 = (ImageButton)findViewById(R.id.flecheH1);
        button_flecheB1 = (ImageButton)findViewById(R.id.flecheB1);
        button_flecheH2 = (ImageButton)findViewById(R.id.flecheH2);
        button_flecheB2 = (ImageButton)findViewById(R.id.flecheB2);
        button_flecheH3 = (ImageButton)findViewById(R.id.flecheH3);
        button_flecheB3 = (ImageButton)findViewById(R.id.flecheB3);
        button_flecheH4 = (ImageButton)findViewById(R.id.flecheH4);
        button_flecheB4 = (ImageButton)findViewById(R.id.flecheB4);
        button_flecheH5 = (ImageButton)findViewById(R.id.flecheH5);
        button_flecheB5 = (ImageButton)findViewById(R.id.flecheB5);
        button_flecheH6 = (ImageButton)findViewById(R.id.flecheH6);
        button_flecheB6 = (ImageButton)findViewById(R.id.flecheB6);


        resistanceColor1 = (ImageButton)findViewById(R.id.resistanceColor1);
        resistanceColor2 = (ImageButton)findViewById(R.id.resistanceColor2);
        resistanceColor3 = (ImageButton)findViewById(R.id.resistanceColor3);
        resistanceColor4 = (ImageButton)findViewById(R.id.resistanceColor4);
        resistanceColor5 = (ImageButton)findViewById(R.id.resistanceColor5);
        resistanceColor6 = (ImageButton)findViewById(R.id.resistanceColor6);


        resistanceColor1.setBackgroundColor(parseColor(listeCouleur[listeCompteur[0]][0]));
        resistanceColor2.setBackgroundColor(parseColor(listeCouleur[listeCompteur[1]][0]));
        resistanceColor3.setBackgroundColor(parseColor(listeCouleur[listeCompteur[2]][0]));
        resistanceColor4.setBackgroundColor(parseColor(listeCouleur[listeCompteur[3]][0]));
        resistanceColor5.setBackgroundColor(parseColor(listeCouleur[listeCompteur[4]][0]));
        resistanceColor6.setBackgroundColor(parseColor(listeCouleur[listeCompteur[5]][0]));


        // Initialisation de l'interface ----------------------------------------------------------------------------

        bande3.setVisibility(View.INVISIBLE);
        bande6.setVisibility(View.INVISIBLE);
        textView_TCR.setVisibility(View.INVISIBLE);
        resistanceColor1.setScaleX((float) 1.5);
        resistanceColor2.setScaleX((float) 1.5);
        resistanceColor4.setScaleX((float) 1.5);
        resistanceColor5.setScaleX((float) 1.5);
        bande1.setTranslationX(40);
        bande2.setTranslationX(80);
        bande4.setTranslationX(-20);
        bande5.setTranslationX(20);
        calcul_4();


        // ========================================================================================================================
        //                              Reliage des boutons à une fonction
        // ========================================================================================================================

        buttonCroix.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){ clicButtonCroix(); }});
        boutonInfo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){ clicBoutonInfo(); }});


        resistanceColor1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){ clicResistanceColor1(); }});
        resistanceColor2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){ clicResistanceColor2(); }});
        resistanceColor3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){ clicResistanceColor3(); }});
        resistanceColor4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){ clicResistanceColor4(); }});
        resistanceColor5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){ clicResistanceColor5(); }});
        resistanceColor6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){ clicResistanceColor6(); }});


        button_flecheH1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){ clicButtonFlecheH1(); }});
        button_flecheB1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){ clicButtonFlecheB1(); }});
        button_flecheH2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){ clicButtonFlecheH2(); }});
        button_flecheB2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){ clicButtonFlecheB2(); }});
        button_flecheH3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){ clicButtonFlecheH3(); }});
        button_flecheB3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){ clicButtonFlecheB3(); }});
        button_flecheH4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){ clicButtonFlecheH4(); }});
        button_flecheB4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){ clicButtonFlecheB4(); }});
        button_flecheH5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){ clicButtonFlecheH5(); }});
        button_flecheB5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){ clicButtonFlecheB5(); }});
        button_flecheH6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){ clicButtonFlecheH6(); }});
        button_flecheB6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){ clicButtonFlecheB6(); }});


        // ========================================================================================================================
        //                              Gestion du nombre d'anneaux (TabLayout)
        // ========================================================================================================================

        TabLayout tt = (TabLayout) findViewById(R.id.tt);

        TabLayout.Tab tab = tt.getTabAt(NbBandeSelect-4);
        tab.select();
        if(NbBandeSelect == 4){TabSelect_4anneaux();}
        if(NbBandeSelect == 5){TabSelect_5anneaux();}
        if(NbBandeSelect == 6){TabSelect_6anneaux();}

        tt.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                switch (position){
                    case 0: TabSelect_4anneaux(); return;
                    case 1: TabSelect_5anneaux(); return;
                    case 2: TabSelect_6anneaux(); return;
                    default: return; }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }
            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });
    }

    public void TabSelect_4anneaux(){
        calcul_4();
        enleveTousLesCadres();
        NbBandeSelect =4;
        bande3.setVisibility(View.INVISIBLE);
        bande6.setVisibility(View.INVISIBLE);
        textView_TCR.setVisibility(View.INVISIBLE);
        layoutPaletteCouleur.setVisibility(View.INVISIBLE);

        resistanceColor1.setScaleX((float) 1.5);
        resistanceColor2.setScaleX((float) 1.5);
        resistanceColor4.setScaleX((float) 1.5);
        resistanceColor5.setScaleX((float) 1.5);

        bande1.setTranslationX(40);
        bande2.setTranslationX(80);
        bande4.setTranslationX(-20);
        bande5.setTranslationX(20);
    }
    public void TabSelect_5anneaux() {
        calcul_5();
        enleveTousLesCadres();
        NbBandeSelect = 5;
        bande3.setVisibility(View.VISIBLE);
        bande6.setVisibility(View.INVISIBLE);
        textView_TCR.setVisibility(View.INVISIBLE);
        layoutPaletteCouleur.setVisibility(View.INVISIBLE);

        resistanceColor1.setScaleX((float) 1.3);
        resistanceColor2.setScaleX((float) 1.3);
        resistanceColor3.setScaleX((float) 1.3);
        resistanceColor4.setScaleX((float) 1.3);
        resistanceColor5.setScaleX((float) 1.3);

        bande1.setTranslationX(20);
        bande2.setTranslationX(40);
        bande3.setTranslationX(60);
        bande4.setTranslationX(80);
        bande5.setTranslationX(100);
    }
    public void TabSelect_6anneaux() {
        calcul_6();
        enleveTousLesCadres();
        NbBandeSelect =6;
        bande3.setVisibility(View.VISIBLE);
        bande6.setVisibility(View.VISIBLE);
        textView_TCR.setVisibility(View.VISIBLE);
        layoutPaletteCouleur.setVisibility(View.INVISIBLE);

        resistanceColor1.setScaleX((float) 1.1);
        resistanceColor2.setScaleX((float) 1.1);
        resistanceColor3.setScaleX((float) 1.1);
        resistanceColor4.setScaleX((float) 1.1);
        resistanceColor5.setScaleX((float) 1.1);
        resistanceColor6.setScaleX((float) 1.1);

        bande1.setTranslationX(0);
        bande2.setTranslationX(-10);
        bande3.setTranslationX(-20);
        bande4.setTranslationX(-30);
        bande5.setTranslationX(-40);
        bande6.setTranslationX(-50);
    }

    // ========================================================================================================================
    //                              Cadre d'information
    // ========================================================================================================================

    public void clicBoutonInfo() { layoutPopup.setVisibility(View.VISIBLE);   }
    public void clicButtonCroix(){ layoutPopup.setVisibility(View.INVISIBLE); }


    // ========================================================================================================================
    //                              Calcul des valeurs de la resistance
    // ========================================================================================================================

    public void calcul_4(){
        a = listeCouleur[listeCompteur[0]][1];
        b = listeCouleur[listeCompteur[1]][1];
        c = listeCouleur[listeCompteur[2]][2];
        d = listeCouleur[listeCompteur[3]][3];
        float ab = Float.parseFloat(a + b);
        ab = (float) (ab * Math.pow(10,Float.parseFloat(c)));

        if(1000 <= ab && ab < 1000000){
            ab = ab/1000;
            ab = (float) Math.round(ab * 100) / 100; // arrondi à 2 chiffres apres la virgules
            // + "  A"+a+"   B"+b+"     C"+c
            textView.setText(String.valueOf(ab) + " KΩ  ± " + String.valueOf(d) +" %");
        }
        else if(ab >= 1000000){
            ab = ab/1000000;
            ab = (float) Math.round(ab * 100) / 100;
            textView.setText(String.valueOf(ab) + " MΩ  ± " + String.valueOf(d) +" %");
        }
        else {
            ab = (float) Math.round(ab * 100) / 100;
            textView.setText(String.valueOf(ab) + " Ω  ± " + String.valueOf(d) + " %");
        }
    }

    public void calcul_5(){
        a = listeCouleur[listeCompteur[0]][1];
        b = listeCouleur[listeCompteur[1]][1];
        c = listeCouleur[listeCompteur[2]][1];
        d = listeCouleur[listeCompteur[3]][2];
        e = listeCouleur[listeCompteur[4]][3];
        float abc = Float.parseFloat(a + b + c);
        abc = (float) (abc * Math.pow(10,Float.parseFloat(d)));
        if(1000 <= abc && abc < 1000000){
            abc = abc/1000;
            abc = (float) Math.round(abc * 100) / 100;
            textView.setText(String.valueOf(abc) + " KΩ  ± " + String.valueOf(e) +" %");
        }
        else if(abc >= 1000000){
            abc = abc/1000000;
            abc = (float) Math.round(abc * 100) / 100;
            textView.setText(String.valueOf(abc) + " MΩ  ± " + String.valueOf(e) +" %");
        }
        else {
            abc = (float) Math.round(abc * 100) / 100;
            textView.setText(String.valueOf(abc) + " Ω  ± " + String.valueOf(e) + " %");
        }
    }

    public void calcul_6(){
        calcul_5();
        a = listeCouleur[listeCompteur[5]][4];
        textView_TCR.setText("TCR : " + String.valueOf(a) + " pppm/KΩ");
    }


    // ========================================================================================================================
    //                              Gestion de l'appui sur les flèches
    // ========================================================================================================================

    public void clicFlecheHaut(int numBande, int numRangerListeCouleur, ImageView r){
        layoutPaletteCouleur.setVisibility(View.INVISIBLE);
        enleveTousLesCadres();

        listeCompteur[numBande] += 1;
        if (listeCompteur[numBande] > indiceListe){ listeCompteur[numBande] = 0; }
        l =listeCouleur[listeCompteur[numBande]][numRangerListeCouleur];
        while (l.equals("1000")){
            listeCompteur[numBande] += 1;
            if (listeCompteur[numBande] > indiceListe){ listeCompteur[numBande] = 0;}
            l =listeCouleur[listeCompteur[numBande]][numRangerListeCouleur];
        }
        r.setBackgroundColor(parseColor(listeCouleur[listeCompteur[numBande]][0]));
        if (NbBandeSelect == 4){ calcul_4(); }
        if (NbBandeSelect == 5){ calcul_5(); }
        if (NbBandeSelect == 6){ calcul_6(); }

    }
    public void clicFlecheBas(int numBande, int numRangerListeCouleur, ImageView r){
        layoutPaletteCouleur.setVisibility(View.INVISIBLE);
        enleveTousLesCadres();

        listeCompteur[numBande] -=1;
        if (listeCompteur[numBande] < 0){ listeCompteur[numBande] = indiceListe; }
        l =listeCouleur[listeCompteur[numBande]][numRangerListeCouleur];
        while (l.equals("1000")){
            listeCompteur[numBande] -= 1;
            if (listeCompteur[numBande] < 0){ listeCompteur[numBande] = indiceListe; }
            l =listeCouleur[listeCompteur[numBande]][numRangerListeCouleur];
        }
        r.setBackgroundColor(parseColor(listeCouleur[listeCompteur[numBande]][0]));
        if (NbBandeSelect == 4){ calcul_4(); }
        if (NbBandeSelect == 5){ calcul_5(); }
        if (NbBandeSelect == 6){ calcul_6(); }
    }
    public void clicButtonFlecheH1(){
        if (NbBandeSelect == 4 || NbBandeSelect == 5 || NbBandeSelect == 6){clicFlecheHaut(0, 1, resistanceColor1); }
    }
    public void clicButtonFlecheB1(){
        if (NbBandeSelect == 4 || NbBandeSelect == 5 || NbBandeSelect == 6){clicFlecheBas(0, 1, resistanceColor1); }
    }
    public void clicButtonFlecheH2(){
        if (NbBandeSelect == 4 || NbBandeSelect == 5 || NbBandeSelect == 6){clicFlecheHaut(1, 1, resistanceColor2); }
    }
    public void clicButtonFlecheB2(){
        if (NbBandeSelect == 4 || NbBandeSelect == 5 || NbBandeSelect == 6){clicFlecheBas(1, 1, resistanceColor2); }
    }
    public void clicButtonFlecheH3(){
        if (NbBandeSelect == 5 || NbBandeSelect == 6){ clicFlecheHaut(2, 1, resistanceColor3); }
    }
    public void clicButtonFlecheB3(){
        if (NbBandeSelect == 5 || NbBandeSelect == 6){ clicFlecheBas(2, 1, resistanceColor3); }
    }
    public void clicButtonFlecheH4(){
        if (NbBandeSelect == 4){clicFlecheHaut(2, 2, resistanceColor4); }
        if (NbBandeSelect == 5 || NbBandeSelect == 6){clicFlecheHaut(3, 2, resistanceColor4); }
    }
    public void clicButtonFlecheB4(){
        if (NbBandeSelect == 4){clicFlecheBas(2, 2, resistanceColor4); }
        if (NbBandeSelect == 5 || NbBandeSelect == 6){clicFlecheBas(3, 2, resistanceColor4); }
    }
    public void clicButtonFlecheH5(){
        if (NbBandeSelect == 4){clicFlecheHaut(3, 3, resistanceColor5); }
        if (NbBandeSelect == 5 || NbBandeSelect == 6){clicFlecheHaut(4, 3, resistanceColor5); }
    }
    public void clicButtonFlecheB5(){
        if (NbBandeSelect == 4){clicFlecheBas(3, 3, resistanceColor5); }
        if (NbBandeSelect == 5 || NbBandeSelect == 6){clicFlecheBas(4, 3, resistanceColor5); }
    }
    public void clicButtonFlecheH6(){
        if (NbBandeSelect == 6){clicFlecheHaut(5, 4, resistanceColor6); }
    }
    public void clicButtonFlecheB6(){
        if (NbBandeSelect == 6){clicFlecheBas(5, 4, resistanceColor6); }
    }



    // ========================================================================================================================
    //                              Gestion de la palette de couleur
    // ========================================================================================================================


    public void enleveTousLesCadres(){
        bande1.setBackgroundResource(R.drawable.outils_vide);
        bande2.setBackgroundResource(R.drawable.outils_vide);
        bande3.setBackgroundResource(R.drawable.outils_vide);
        bande4.setBackgroundResource(R.drawable.outils_vide);
        bande5.setBackgroundResource(R.drawable.outils_vide);
        bande6.setBackgroundResource(R.drawable.outils_vide);
    }

    public void apparitionDelaPalette(int numBande, int numRangerListeCouleur, ImageView r){
        layoutPaletteCouleur.setVisibility(View.VISIBLE);
        for (int i=0; i<listeCouleur.length; i+=1){
            liste_CarreCouleur[i].setVisibility(View.VISIBLE);
        }
        for (int i=0; i<listeCouleur.length; i+=1){
            l =listeCouleur[i][numRangerListeCouleur];
            if (l.equals("1000")){
                liste_CarreCouleur[i].setVisibility(View.INVISIBLE);
            }
        }
    }

    public void clicResistanceColor1(){
        enleveTousLesCadres();
        if (NbBandeSelect == 4 || NbBandeSelect == 5 || NbBandeSelect == 6){apparitionDelaPalette(0, 1, resistanceColor1); }
        NbBandeclick=0;
        bande1.setBackgroundResource(R.drawable.cadre);
        nomBande.setText("1er chiffre significatif");
    }
    public void clicResistanceColor2(){
        enleveTousLesCadres();
        if (NbBandeSelect == 4 || NbBandeSelect == 5 || NbBandeSelect == 6){apparitionDelaPalette(1, 1, resistanceColor2); }
        NbBandeclick=1;
        bande2.setBackgroundResource(R.drawable.cadre);
        nomBande.setText("2eme chiffre significatif");
    }
    public void clicResistanceColor3(){
        enleveTousLesCadres();
        if (NbBandeSelect == 5 || NbBandeSelect == 6){ apparitionDelaPalette(2, 1, resistanceColor3); }
        NbBandeclick=2;
        bande3.setBackgroundResource(R.drawable.cadre);
        nomBande.setText("3eme chiffre significatif");
    }
    public void clicResistanceColor4(){
        enleveTousLesCadres();
        if (NbBandeSelect == 4){apparitionDelaPalette(2, 2, resistanceColor4); }
        if (NbBandeSelect == 5 || NbBandeSelect == 6){apparitionDelaPalette(3, 2, resistanceColor4); }
        NbBandeclick=3;
        bande4.setBackgroundResource(R.drawable.cadre);
        nomBande.setText("multiplicateur");
    }
    public void clicResistanceColor5(){
        enleveTousLesCadres();
        if (NbBandeSelect == 4){apparitionDelaPalette(3, 3, resistanceColor5); }
        if (NbBandeSelect == 5 || NbBandeSelect == 6){apparitionDelaPalette(4, 3, resistanceColor5); }
        NbBandeclick=4;
        bande5.setBackgroundResource(R.drawable.cadre);
        nomBande.setText("tolérance");
    }
    public void clicResistanceColor6(){
        enleveTousLesCadres();
        if (NbBandeSelect == 6){apparitionDelaPalette(5, 4, resistanceColor6); }
        NbBandeclick=5;
        bande6.setBackgroundResource(R.drawable.cadre);
        nomBande.setText("TCR");
    }


    public void onClick_carreCourleurGeneral(String couleur, int positionCouleur){
        if(NbBandeclick ==0){resistanceColor1.setBackgroundColor(parseColor(couleur));}
        if(NbBandeclick ==1){resistanceColor2.setBackgroundColor(parseColor(couleur));}
        if(NbBandeclick ==2){resistanceColor3.setBackgroundColor(parseColor(couleur));}
        if(NbBandeclick ==3){resistanceColor4.setBackgroundColor(parseColor(couleur));}
        if(NbBandeclick ==4){resistanceColor5.setBackgroundColor(parseColor(couleur));}
        if(NbBandeclick ==5){resistanceColor6.setBackgroundColor(parseColor(couleur));}

        layoutPaletteCouleur.setVisibility(View.INVISIBLE);

        listeCompteur[NbBandeclick] = positionCouleur;

        if (NbBandeSelect == 4){ calcul_4(); }
        if (NbBandeSelect == 5){ calcul_5(); }
        if (NbBandeSelect == 6){ calcul_6(); }

    }

    public void onClick(View v) {
        if(v == findViewById(R.id.carreCouleur_1)){ onClick_carreCourleurGeneral(listeCouleur[0][0], 0); } //noir
        else if(v == findViewById(R.id.carreCouleur_2)){ onClick_carreCourleurGeneral(listeCouleur[1][0], 1); } //marron
        else if(v == findViewById(R.id.carreCouleur_3)){ onClick_carreCourleurGeneral(listeCouleur[2][0], 2); } //rouge
        else if(v == findViewById(R.id.carreCouleur_4)){ onClick_carreCourleurGeneral(listeCouleur[3][0], 3); } //orange
        else if(v == findViewById(R.id.carreCouleur_5)){ onClick_carreCourleurGeneral(listeCouleur[4][0], 4); } //jaune
        else if(v == findViewById(R.id.carreCouleur_6)){ onClick_carreCourleurGeneral(listeCouleur[5][0], 5); } //vert
        else if(v == findViewById(R.id.carreCouleur_7)){ onClick_carreCourleurGeneral(listeCouleur[6][0], 6); } //bleu
        else if(v == findViewById(R.id.carreCouleur_8)){ onClick_carreCourleurGeneral(listeCouleur[7][0], 7); } //violet
        else if(v == findViewById(R.id.carreCouleur_9)){ onClick_carreCourleurGeneral(listeCouleur[8][0], 8); } //gris
        else if(v == findViewById(R.id.carreCouleur_10)){ onClick_carreCourleurGeneral(listeCouleur[9][0], 9); } //blanc
        else if(v == findViewById(R.id.carreCouleur_11)){ onClick_carreCourleurGeneral(listeCouleur[10][0], 10); } //argent
        else if(v == findViewById(R.id.carreCouleur_12)){ onClick_carreCourleurGeneral(listeCouleur[11][0], 11);  } //or
    }


    // ========================================================================================================================
    //                              Favoris
    // ========================================================================================================================


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.coeur, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.coeur_vide:

                Resources res = getResources();

                if (stateHeart == 1) {
                    Drawable drawable = ResourcesCompat.getDrawable(res, R.drawable.coeur_plein, null);
                    item.setIcon(drawable);
                    stateHeart -= 1;
                }
                if(stateHeart == 2){
                    Drawable drawable = ResourcesCompat.getDrawable(res, R.drawable.coeur_vide, null);
                    item.setIcon(drawable);
                    stateHeart -= 1;
                }
                if (stateHeart == 0) { stateHeart = 2; }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


}


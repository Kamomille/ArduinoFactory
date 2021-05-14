package com.ArduinoFactory.androidstudio.CORBEILLE;

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

import com.ArduinoFactory.androidstudio.R;
import com.google.android.material.tabs.TabLayout;

import static android.graphics.Color.parseColor;


public class outils_resistance_test extends AppCompatActivity implements View.OnClickListener {

    // ========================================================================================================================
    //                              Déclaration et initialisation
    // ========================================================================================================================

    private Button liste_CarreCouleur[] = new Button[12];

    private ImageButton liste_bande_4_anneaux[] = new ImageButton[4];
    private ImageButton liste_bande_5_anneaux[] = new ImageButton[5];
    private ImageButton liste_bande_6_anneaux[] = new ImageButton[6];

    private ImageButton liste_fleche_haut_4[] = new ImageButton[4];
    private ImageButton liste_fleche_bas_4[] = new ImageButton[4];
    private ImageButton liste_fleche_haut_5[] = new ImageButton[5];
    private ImageButton liste_fleche_bas_5[] = new ImageButton[5];
    private ImageButton liste_fleche_haut_6[] = new ImageButton[6];
    private ImageButton liste_fleche_bas_6[] = new ImageButton[6];

    private LinearLayout layout_4anneaux, layout_5anneaux, layout_6anneaux;

    private ImageButton buttonCroix, boutonInfo;
    private LinearLayout layoutPopup, layoutPaletteCouleur;
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

        layout_4anneaux = (LinearLayout) findViewById(R.id.layout_4anneaux);
        layout_5anneaux = (LinearLayout) findViewById(R.id.layout_5anneaux);
        layout_6anneaux = (LinearLayout) findViewById(R.id.layout_6anneaux);


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
        for(int i=0; i<liste_CarreCouleur.length; i++){ liste_CarreCouleur[i].setOnClickListener(this); }





        liste_bande_4_anneaux[0] = (ImageButton) findViewById(R.id.resistanceColor1_4);
        liste_bande_4_anneaux[1] = (ImageButton) findViewById(R.id.resistanceColor2_4);
        liste_bande_4_anneaux[2] = (ImageButton) findViewById(R.id.resistanceColor3_4);
        liste_bande_4_anneaux[3] = (ImageButton) findViewById(R.id.resistanceColor4_4);
        for(int i=0; i<liste_bande_4_anneaux.length; i++){ liste_bande_4_anneaux[i].setOnClickListener(this); }

        liste_bande_5_anneaux[0] = (ImageButton) findViewById(R.id.resistanceColor1_5);
        liste_bande_5_anneaux[1] = (ImageButton) findViewById(R.id.resistanceColor2_5);
        liste_bande_5_anneaux[2] = (ImageButton) findViewById(R.id.resistanceColor3_5);
        liste_bande_5_anneaux[3] = (ImageButton) findViewById(R.id.resistanceColor4_5);
        liste_bande_5_anneaux[4] = (ImageButton) findViewById(R.id.resistanceColor5_5);
        for(int i=0; i<liste_bande_5_anneaux.length; i++){ liste_bande_5_anneaux[i].setOnClickListener(this); }

        liste_bande_6_anneaux[0] = (ImageButton) findViewById(R.id.resistanceColor1_6);
        liste_bande_6_anneaux[1] = (ImageButton) findViewById(R.id.resistanceColor2_6);
        liste_bande_6_anneaux[2] = (ImageButton) findViewById(R.id.resistanceColor3_6);
        liste_bande_6_anneaux[3] = (ImageButton) findViewById(R.id.resistanceColor4_6);
        liste_bande_6_anneaux[4] = (ImageButton) findViewById(R.id.resistanceColor5_6);
        liste_bande_6_anneaux[5] = (ImageButton) findViewById(R.id.resistanceColor6_6);
        for(int i=0; i<liste_bande_6_anneaux.length; i++){ liste_bande_6_anneaux[i].setOnClickListener(this); }


        liste_fleche_haut_4[0] = (ImageButton) findViewById(R.id.flecheH1_4);
        liste_fleche_haut_4[1] = (ImageButton) findViewById(R.id.flecheH2_4);
        liste_fleche_haut_4[2] = (ImageButton) findViewById(R.id.flecheH3_4);
        liste_fleche_haut_4[3] = (ImageButton) findViewById(R.id.flecheH4_4);
        for(int i=0; i<liste_fleche_haut_4.length; i++){ liste_fleche_haut_4[i].setOnClickListener(this); }
        liste_fleche_bas_4[0] = (ImageButton) findViewById(R.id.flecheB1_4);
        liste_fleche_bas_4[1] = (ImageButton) findViewById(R.id.flecheB2_4);
        liste_fleche_bas_4[2] = (ImageButton) findViewById(R.id.flecheB3_4);
        liste_fleche_bas_4[3] = (ImageButton) findViewById(R.id.flecheB4_4);
        for(int i=0; i<liste_fleche_bas_4.length; i++){ liste_fleche_bas_4[i].setOnClickListener(this); }

        liste_fleche_haut_5[0] = (ImageButton) findViewById(R.id.flecheH1_5);
        liste_fleche_haut_5[1] = (ImageButton) findViewById(R.id.flecheH2_5);
        liste_fleche_haut_5[2] = (ImageButton) findViewById(R.id.flecheH3_5);
        liste_fleche_haut_5[3] = (ImageButton) findViewById(R.id.flecheH4_5);
        liste_fleche_haut_5[4] = (ImageButton) findViewById(R.id.flecheH5_5);
        for(int i=0; i<liste_fleche_haut_5.length; i++){ liste_fleche_haut_5[i].setOnClickListener(this); }
        liste_fleche_bas_5[0] = (ImageButton) findViewById(R.id.flecheB1_5);
        liste_fleche_bas_5[1] = (ImageButton) findViewById(R.id.flecheB2_5);
        liste_fleche_bas_5[2] = (ImageButton) findViewById(R.id.flecheB3_5);
        liste_fleche_bas_5[3] = (ImageButton) findViewById(R.id.flecheB4_5);
        liste_fleche_bas_5[4] = (ImageButton) findViewById(R.id.flecheB5_5);
        for(int i=0; i<liste_fleche_bas_5.length; i++){ liste_fleche_bas_5[i].setOnClickListener(this); }

        liste_fleche_haut_6[0] = (ImageButton) findViewById(R.id.flecheH1_6);
        liste_fleche_haut_6[1] = (ImageButton) findViewById(R.id.flecheH2_6);
        liste_fleche_haut_6[2] = (ImageButton) findViewById(R.id.flecheH3_6);
        liste_fleche_haut_6[3] = (ImageButton) findViewById(R.id.flecheH4_6);
        liste_fleche_haut_6[4] = (ImageButton) findViewById(R.id.flecheH5_6);
        liste_fleche_haut_6[5] = (ImageButton) findViewById(R.id.flecheH6_6);
        for(int i=0; i<liste_fleche_haut_6.length; i++){ liste_fleche_haut_6[i].setOnClickListener(this); }
        liste_fleche_bas_6[0] = (ImageButton) findViewById(R.id.flecheB1_6);
        liste_fleche_bas_6[1] = (ImageButton) findViewById(R.id.flecheB2_6);
        liste_fleche_bas_6[2] = (ImageButton) findViewById(R.id.flecheB3_6);
        liste_fleche_bas_6[3] = (ImageButton) findViewById(R.id.flecheB4_6);
        liste_fleche_bas_6[4] = (ImageButton) findViewById(R.id.flecheB5_6);
        liste_fleche_bas_6[5] = (ImageButton) findViewById(R.id.flecheB6_6);
        for(int i=0; i<liste_fleche_bas_6.length; i++){ liste_fleche_bas_6[i].setOnClickListener(this); }



        for(int i=0; i<liste_bande_4_anneaux.length; i++){ liste_bande_4_anneaux[i].setBackgroundColor(parseColor(listeCouleur[listeCompteur[i]][0])); }
        for(int i=0; i<liste_bande_5_anneaux.length; i++){ liste_bande_5_anneaux[i].setBackgroundColor(parseColor(listeCouleur[listeCompteur[i]][0])); }
        for(int i=0; i<liste_bande_6_anneaux.length; i++){ liste_bande_6_anneaux[i].setBackgroundColor(parseColor(listeCouleur[listeCompteur[i]][0])); }



        // Initialisation de l'interface ----------------------------------------------------------------------------


        if (NbBandeSelect == 4){
            calcul_4();
            textView_TCR.setVisibility(View.INVISIBLE);
            layout_4anneaux.setVisibility(View.VISIBLE);
            layout_5anneaux.setVisibility(View.INVISIBLE);
            layout_6anneaux.setVisibility(View.INVISIBLE);
        }
        if (NbBandeSelect == 5){
            calcul_5();
            textView_TCR.setVisibility(View.INVISIBLE);
            layout_4anneaux.setVisibility(View.INVISIBLE);
            layout_5anneaux.setVisibility(View.VISIBLE);
            layout_6anneaux.setVisibility(View.INVISIBLE);
        }
        if (NbBandeSelect == 6){
            calcul_6();
            textView_TCR.setVisibility(View.VISIBLE);
            layout_4anneaux.setVisibility(View.INVISIBLE);
            layout_5anneaux.setVisibility(View.INVISIBLE);
            layout_6anneaux.setVisibility(View.VISIBLE);
        }




        // ========================================================================================================================
        //                              Reliage des boutons à une fonction
        // ========================================================================================================================

        buttonCroix.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){ clicButtonCroix(); }});
        boutonInfo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){ clicBoutonInfo(); }});



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
        layout_4anneaux.setVisibility(View.VISIBLE);
        layout_5anneaux.setVisibility(View.INVISIBLE);
        layout_6anneaux.setVisibility(View.INVISIBLE);
        textView_TCR.setVisibility(View.INVISIBLE);
        layoutPaletteCouleur.setVisibility(View.INVISIBLE);

    }
    public void TabSelect_5anneaux() {
        calcul_5();
        enleveTousLesCadres();
        NbBandeSelect = 5;
        layout_4anneaux.setVisibility(View.INVISIBLE);
        layout_5anneaux.setVisibility(View.VISIBLE);
        layout_6anneaux.setVisibility(View.INVISIBLE);
        textView_TCR.setVisibility(View.INVISIBLE);
        layoutPaletteCouleur.setVisibility(View.INVISIBLE);
    }
    public void TabSelect_6anneaux() {
        calcul_6();
        enleveTousLesCadres();
        NbBandeSelect =6;
        layout_4anneaux.setVisibility(View.INVISIBLE);
        layout_5anneaux.setVisibility(View.INVISIBLE);
        layout_6anneaux.setVisibility(View.VISIBLE);
        textView_TCR.setVisibility(View.VISIBLE);
        layoutPaletteCouleur.setVisibility(View.INVISIBLE);
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
        if (NbBandeSelect == 4){
            calcul_4();
            liste_bande_5_anneaux[numBande-1].setBackgroundColor(parseColor(listeCouleur[listeCompteur[numBande]][0]));
            liste_bande_6_anneaux[numBande-1].setBackgroundColor(parseColor(listeCouleur[listeCompteur[numBande]][0]));
        }
        if (NbBandeSelect == 5){
            calcul_5();
            liste_bande_4_anneaux[numBande-1].setBackgroundColor(parseColor(listeCouleur[listeCompteur[numBande]][0]));
            liste_bande_6_anneaux[numBande-1].setBackgroundColor(parseColor(listeCouleur[listeCompteur[numBande]][0]));
        }
        if (NbBandeSelect == 6){
            calcul_6();
            liste_bande_4_anneaux[numBande-1].setBackgroundColor(parseColor(listeCouleur[listeCompteur[numBande]][0]));
            liste_bande_5_anneaux[numBande-1].setBackgroundColor(parseColor(listeCouleur[listeCompteur[numBande]][0]));
        }
    }

    // ========================================================================================================================
    //                              Gestion click sur les boutons
    // ========================================================================================================================


    public void onClick(View v) {

        switch (v.getId()) {
            /*case R.id.carreCouleur_1:  onClick_carreCourleurGeneral(listeCouleur[0][0], 0); break;
            case R.id.carreCouleur_2:  onClick_carreCourleurGeneral(listeCouleur[1][0], 1); break;
            case R.id.carreCouleur_3:  onClick_carreCourleurGeneral(listeCouleur[2][0], 2); break;
            case R.id.carreCouleur_4:  onClick_carreCourleurGeneral(listeCouleur[3][0], 3); break;
            case R.id.carreCouleur_5:  onClick_carreCourleurGeneral(listeCouleur[4][0], 4); break;
            case R.id.carreCouleur_6:  onClick_carreCourleurGeneral(listeCouleur[5][0], 5);  break;
            case R.id.carreCouleur_7:  onClick_carreCourleurGeneral(listeCouleur[6][0], 6);  break;
            case R.id.carreCouleur_8:  onClick_carreCourleurGeneral(listeCouleur[7][0], 7); break;
            case R.id.carreCouleur_9:  onClick_carreCourleurGeneral(listeCouleur[8][0], 8);  break;
            case R.id.carreCouleur_10: onClick_carreCourleurGeneral(listeCouleur[9][0], 9); break;
            case R.id.carreCouleur_11: onClick_carreCourleurGeneral(listeCouleur[10][0], 10); break;
            case R.id.carreCouleur_12: onClick_carreCourleurGeneral(listeCouleur[11][0], 11); break;*/



            case R.id.flecheH1_4: clicFlecheHaut(1,  1, liste_bande_4_anneaux[0]); break;
            case R.id.flecheH2_4: clicFlecheHaut(2,  1, liste_bande_4_anneaux[1]); break;
            case R.id.flecheH3_4: clicFlecheHaut(3,  2, liste_bande_4_anneaux[2]); break;
            case R.id.flecheH4_4: clicFlecheHaut(4,  3, liste_bande_4_anneaux[3]); break;
            case R.id.flecheH1_5: clicFlecheHaut(1,  1, liste_bande_5_anneaux[0]); break;
            case R.id.flecheH2_5: clicFlecheHaut(2,  1, liste_bande_5_anneaux[1]); break;
            case R.id.flecheH3_5: clicFlecheHaut(3,  1, liste_bande_5_anneaux[2]); break;
            case R.id.flecheH4_5: clicFlecheHaut(4,  2, liste_bande_5_anneaux[3]); break;
            case R.id.flecheH5_5: clicFlecheHaut(5,  3, liste_bande_5_anneaux[4]); break;
            case R.id.flecheH1_6: clicFlecheHaut(1,  1, liste_bande_6_anneaux[0]); break;
            case R.id.flecheH2_6: clicFlecheHaut(2,  1, liste_bande_6_anneaux[1]); break;
            case R.id.flecheH3_6: clicFlecheHaut(3,  1, liste_bande_6_anneaux[2]); break;
            case R.id.flecheH4_6: clicFlecheHaut(4,  2, liste_bande_6_anneaux[3]); break;
            case R.id.flecheH5_6: clicFlecheHaut(5,  3, liste_bande_6_anneaux[4]); break;
            case R.id.flecheH6_6: clicFlecheHaut(6,  4, liste_bande_6_anneaux[5]); break;



            case R.id.flecheB1_4: clicFlecheBas(1,  1, liste_bande_4_anneaux[0]); break;
            case R.id.flecheB2_4: clicFlecheBas(2,  1, liste_bande_4_anneaux[1]); break;
            case R.id.flecheB3_4: clicFlecheBas(3,  2, liste_bande_4_anneaux[2]); break;
            case R.id.flecheB4_4: clicFlecheBas(4,  3, liste_bande_4_anneaux[3]); break;
            case R.id.flecheB1_5: clicFlecheBas(1,  1, liste_bande_5_anneaux[0]); break;
            case R.id.flecheB2_5: clicFlecheBas(2,  1, liste_bande_5_anneaux[1]); break;
            case R.id.flecheB3_5: clicFlecheBas(3,  1, liste_bande_5_anneaux[2]); break;
            case R.id.flecheB4_5: clicFlecheBas(4,  2, liste_bande_5_anneaux[3]); break;
            case R.id.flecheB5_5: clicFlecheBas(5,  3, liste_bande_5_anneaux[4]); break;
            case R.id.flecheB1_6: clicFlecheBas(1,  1, liste_bande_6_anneaux[0]); break;
            case R.id.flecheB2_6: clicFlecheBas(2,  1, liste_bande_6_anneaux[1]); break;
            case R.id.flecheB3_6: clicFlecheBas(3,  1, liste_bande_6_anneaux[2]); break;
            case R.id.flecheB4_6: clicFlecheBas(4,  2, liste_bande_6_anneaux[3]); break;
            case R.id.flecheB5_6: clicFlecheBas(5,  3, liste_bande_6_anneaux[4]); break;
            case R.id.flecheB6_6: clicFlecheBas(6,  4, liste_bande_6_anneaux[5]); break;


            case R.id.resistanceColor1_4: ; break;
            case R.id.resistanceColor2_4: ; break;
            case R.id.resistanceColor3_4: ; break;
            case R.id.resistanceColor4_4: ; break;
            case R.id.resistanceColor1_5: ; break;
            case R.id.resistanceColor2_5: ; break;
            case R.id.resistanceColor3_5: ; break;
            case R.id.resistanceColor4_5: ; break;
            case R.id.resistanceColor5_5: ; break;
            case R.id.resistanceColor1_6: ; break;
            case R.id.resistanceColor2_6: ; break;
            case R.id.resistanceColor3_6: ; break;
            case R.id.resistanceColor4_6: ; break;
            case R.id.resistanceColor5_6: ; break;
            case R.id.resistanceColor6_6: ; break;


        }

    }

    // ========================================================================================================================
    //                              Gestion de la palette de couleur
    // ========================================================================================================================


    public void enleveTousLesCadres(){

        //for (int i=0; i<liste_bande_4_anneaux.length; i+=1){ liste_bande_4_anneaux[i].setBackgroundResource(R.drawable.outils_vide); }
        //for (int i=0; i<liste_bande_5_anneaux.length; i+=1){ liste_bande_5_anneaux[i].setBackgroundResource(R.drawable.outils_vide); }
        //for (int i=0; i<liste_bande_6_anneaux.length; i+=1){ liste_bande_6_anneaux[i].setBackgroundResource(R.drawable.outils_vide); }

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






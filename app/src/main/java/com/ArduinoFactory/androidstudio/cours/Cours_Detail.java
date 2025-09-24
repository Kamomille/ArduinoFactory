package com.ArduinoFactory.androidstudio.cours;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ArduinoFactory.androidstudio.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class Cours_Detail extends AppCompatActivity {

    TabLayout.Tab initTab;

    TextView TextDef;
    TextView TextDes;
    ImageView imageDef;
    ImageView imageDes;
    ImageView imageCablage;
    Intent intent;
    Resources resources;
    LinearLayout layoutDef;
    LinearLayout layoutDes;
    LinearLayout layoutSchema;
    FirebaseFirestore db;
    String extraText;
    int id;
    int position;

    @SuppressLint({"CutPasteId", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cours_detail);

        MobileAds.initialize(this, initializationStatus -> {});

        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        AdView mAdView2 = findViewById(R.id.adView2);
        AdRequest adRequest2 = new AdRequest.Builder().build();
        mAdView2.loadAd(adRequest2);

        AdView mAdView3 = findViewById(R.id.adView3);
        AdRequest adRequest3 = new AdRequest.Builder().build();
        mAdView3.loadAd(adRequest3);

        // bouton retour
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        intent = getIntent();

        TextDef = findViewById(R.id.TextDef);
        TextDes = findViewById(R.id.TextDes);
        layoutDef = findViewById(R.id.layout_def);
        layoutDes = findViewById(R.id.layout_des);
        layoutSchema = findViewById(R.id.layout_schema);
        imageDef = findViewById(R.id.imageDef);
        imageDes = findViewById(R.id.imageDes);
        imageCablage = findViewById(R.id.imageCablage);
        extraText = intent.getStringExtra("page");
        position = intent.getIntExtra("typePage", 0);

        db = FirebaseFirestore.getInstance();

        LinearLayout layout_schema = findViewById(R.id.layout_schema);
        LinearLayout layout_des = findViewById(R.id.layout_des);
        LinearLayout layout_def = findViewById(R.id.layout_def);

        layout_def.setVisibility(View.VISIBLE);
        layout_des.setVisibility(View.VISIBLE);
        layout_schema.setVisibility(View.VISIBLE);



        db.collection("Cours").document(extraText).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @SuppressLint("DiscouragedApi")
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                assert value != null;
                TextDef.setText(value.getString("Définition"));
                TextDes.setText(value.getString("Description"));
                resources = getResources();

                // ✅ Image Déf
                id = resources.getIdentifier(value.getString("imageDef"), "drawable", getPackageName());
                Drawable drawable = ContextCompat.getDrawable(Cours_Detail.this, id);
                imageDef.setImageDrawable(drawable);

                // ✅ Image Des
                id = resources.getIdentifier(value.getString("imageDes"), "drawable", getPackageName());
                drawable = ContextCompat.getDrawable(Cours_Detail.this, id);
                imageDes.setImageDrawable(drawable);

                // ✅ Image Câblage
                id = resources.getIdentifier(value.getString("imageCablage"), "drawable", getPackageName());
                drawable = ContextCompat.getDrawable(Cours_Detail.this, id);
                imageCablage.setImageDrawable(drawable);
            }
        });
    }
}

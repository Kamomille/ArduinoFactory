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
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class Cours extends AppCompatActivity {

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

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page__cours);

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

        TabLayout tabLayout = findViewById(R.id.tabLayout);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tabLayout) {
                position = tabLayout.getPosition();

                switch (position) {
                    case 0:
                        layout_def.setVisibility(View.VISIBLE);
                        layout_des.setVisibility(View.INVISIBLE);
                        layout_schema.setVisibility(View.INVISIBLE);
                        return;
                    case 1:
                        layout_def.setVisibility(View.INVISIBLE);
                        layout_des.setVisibility(View.VISIBLE);
                        layout_schema.setVisibility(View.INVISIBLE);
                        return;
                    case 2:
                        layout_def.setVisibility(View.INVISIBLE);
                        layout_des.setVisibility(View.INVISIBLE);
                        layout_schema.setVisibility(View.VISIBLE);
                        return;
                    default:
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tabLayout) { }

            @Override
            public void onTabReselected(TabLayout.Tab tabLayout) { }
        });

        initTab = tabLayout.getTabAt(position);
        assert initTab != null;
        initTab.select();

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
                Drawable drawable = ContextCompat.getDrawable(Cours.this, id);
                imageDef.setImageDrawable(drawable);

                // ✅ Image Des
                id = resources.getIdentifier(value.getString("imageDes"), "drawable", getPackageName());
                drawable = ContextCompat.getDrawable(Cours.this, id);
                imageDes.setImageDrawable(drawable);

                // ✅ Image Câblage
                id = resources.getIdentifier(value.getString("imageCablage"), "drawable", getPackageName());
                drawable = ContextCompat.getDrawable(Cours.this, id);
                imageCablage.setImageDrawable(drawable);
            }
        });
    }
}

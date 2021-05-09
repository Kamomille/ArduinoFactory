package com.example.androidstudio.pages;


import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.androidstudio.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firestore.v1.GetDocumentRequest;

import java.util.List;

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
    boolean init=true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page__cours);

        // bouton retour
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        intent=getIntent();

        TextDef = findViewById(R.id.TextDef);
        TextDes = findViewById(R.id.TextDes);
        layoutDef = findViewById(R.id.layout_def);
        layoutDes = findViewById(R.id.layout_des);
        layoutSchema = findViewById(R.id.layout_schema);
        imageDef = findViewById(R.id.imageDef);
        imageDes = findViewById(R.id.imageDes);
        imageCablage = findViewById(R.id.imageCablage);
        extraText=(String) intent.getStringExtra("page");
        position=intent.getIntExtra("typePage",0);

        db = FirebaseFirestore.getInstance();

        LinearLayout layout_schema = (LinearLayout) findViewById(R.id.layout_schema);
        LinearLayout layout_des = (LinearLayout) findViewById(R.id.layout_des);
        LinearLayout layout_def = (LinearLayout) findViewById(R.id.layout_def);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tabLayout) {
                    position = tabLayout.getPosition();

                switch (position){
                    case 0:
                        // l'utilisateur clique sur definition
                        layout_def.setVisibility(View.VISIBLE);
                        layout_des.setVisibility(View.INVISIBLE);
                        layout_schema.setVisibility(View.INVISIBLE);
                        return;
                    case 1:
                        // l'utilisateur clique sur definition
                        layout_def.setVisibility(View.INVISIBLE);
                        layout_des.setVisibility(View.VISIBLE);
                        layout_schema.setVisibility(View.INVISIBLE);
                        return;
                    case 2:
                        // l'utilisateur clique sur definition
                        layout_def.setVisibility(View.INVISIBLE);
                        layout_des.setVisibility(View.INVISIBLE);
                        layout_schema.setVisibility(View.VISIBLE);
                        return;
                    default: return; }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tabLayout) { }
            @Override
            public void onTabReselected(TabLayout.Tab tabLayout) { }

        });
        initTab = tabLayout.getTabAt(position);
        initTab.select();
        db.collection("Cours").document(extraText).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                TextDef.setText(value.getString("Définition"));
                TextDes.setText(value.getString("Description"));
                resources= getResources();

                id = getResources().getIdentifier(value.getString("imageDef"),"drawable", getPackageName());
                Drawable drawable= getResources().getDrawable(id,null);
                drawable=getResources().getDrawable( id,null);
                imageDef.setImageDrawable(drawable);

                id = getResources().getIdentifier(value.getString("imageDes"),"drawable", getPackageName());
                drawable=getResources().getDrawable( id,null);
                imageDes.setImageDrawable(drawable);

                id = getResources().getIdentifier(value.getString("imageCablage"),"drawable", getPackageName());
                drawable=getResources().getDrawable( id,null);
                imageCablage.setImageDrawable(drawable);

            }
        });
    }


}


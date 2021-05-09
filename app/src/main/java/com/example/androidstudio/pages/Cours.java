package com.example.androidstudio.pages;


import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

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
        extraText=(String) intent.getStringExtra("page");
        db = FirebaseFirestore.getInstance();

        db.collection("Cours").document(extraText).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                TextDef.setText(value.getString("Définition"));
                TextDes.setText(value.getString("Description"));
                resources= getResources();
                id = getResources().getIdentifier("outil","drawable", getPackageName());
                System.out.println(id);
                imageDef.setImageResource(id);

            }
        });
    }
}


package com.example.androidstudio.pages;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.androidstudio.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class Cours extends AppCompatActivity {

    TextView TextDef;
    TextView TextDes;
    LinearLayout layoutDef;
    LinearLayout layoutDes;
    LinearLayout layoutSchema;
    FirebaseFirestore db ;
    DocumentReference document;
    String field;
    View stock;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page__menu_cours);

        // bouton retour
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        TextDef = findViewById(R.id.textViewDef);
        TextDes = findViewById(R.id.textViewDes);
        layoutDef = findViewById(R.id.Def);
        layoutDes = findViewById(R.id.Des);
        layoutSchema = findViewById(R.id.Schema);

        db = FirebaseFirestore.getInstance();

        document=db.collection("Cours").document("coffret");
        db.collection("Cours").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        if (!queryDocumentSnapshots.isEmpty()) {

                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();

                            for (DocumentSnapshot d : list) {
                                if (d.getId()=="Bouton poussoir"){
                                    TextDef.setText(d.getString("Définition"));
                                    TextDef.setText(d.getString("Description"));
                                }


                            }
                        }
                    }
                } );

    }

}


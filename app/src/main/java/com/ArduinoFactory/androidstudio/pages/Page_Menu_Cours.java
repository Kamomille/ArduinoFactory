package com.ArduinoFactory.androidstudio.pages;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ArduinoFactory.androidstudio.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class Page_Menu_Cours extends AppCompatActivity {

    TextView TextDef;
    TextView TextDes;

    Button newbutton;
    LinearLayout layout;
    LinearLayout layout2;
    FirebaseFirestore db ;
    DocumentReference document;
    View stock;
    Button stockbutton;
    int typePage;
    int index;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page__menu_cours);

        // bouton retour
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        layout = findViewById(R.id.layout);
        layout2 = findViewById(R.id.layout_cours_choix);
        TextDef = findViewById(R.id.TextDef);
        TextDes = findViewById(R.id.TextDes);

        db = FirebaseFirestore.getInstance();
        document=db.collection("Cours").document("coffret");
        db.collection("Cours").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        if (!queryDocumentSnapshots.isEmpty()) {

                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();

                            for (DocumentSnapshot d : list) {
                                addButton(d.getId());

                            }
                        }
                    }
                } );

    }

    public void onClick(View view){

        View layout2= getLayoutInflater().inflate(R.layout.activity_page__menu_cours_row_add,null, false);
        index = layout.indexOfChild(view);

        if(stock==null){
            layout.addView(layout2,index+1);
            stock=view;
            stockbutton= (Button) view;
        }
        else if (stock==view) {
            layout.removeViewAt(index + 1);
            stock = null;
        }
        else {
            layout.addView(layout2,index+1);
            int indexstock = layout.indexOfChild(stock);
            layout.removeViewAt(indexstock+1);
            stock=view;
            stockbutton= (Button) view;
        }

    }

    public void onClick_cours (View view){
        openActivtity_cours(view);
    }

    public void openActivtity_cours(View view){
        document=db.collection("cours").document("Bouton poussoir");
        document=db.collection("Cours").document("coffret");

        layout2=(LinearLayout) view.getParent();
        typePage=layout2.indexOfChild(view);

        Intent intent = new Intent(this, Cours.class);
        intent.putExtra("page",stockbutton.getText().toString());
        intent.putExtra("typePage",typePage);
        startActivity(intent);
    }


    public void addButton(String Text){
        LinearLayout layout = findViewById(R.id.layout);
        newbutton = new Button(this);
        newbutton.setText(Text);
        newbutton.setOnClickListener(this::onClick);
        layout.addView(newbutton,0);

    }

}
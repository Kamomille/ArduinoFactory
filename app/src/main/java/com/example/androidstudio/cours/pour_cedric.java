package com.example.androidstudio.cours;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.androidstudio.R;
import com.google.android.material.tabs.TabLayout;

public class pour_cedric extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page__cours);

        LinearLayout layout_schema = (LinearLayout) findViewById(R.id.layout_schema);
        LinearLayout layout_des = (LinearLayout) findViewById(R.id.layout_des);
        LinearLayout layout_def = (LinearLayout) findViewById(R.id.layout_def);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        layout_def.setVisibility(View.VISIBLE);
        layout_des.setVisibility(View.INVISIBLE);
        layout_schema.setVisibility(View.INVISIBLE);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tabLayout) {
                int position = tabLayout.getPosition();
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
    }

}

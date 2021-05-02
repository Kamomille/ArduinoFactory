package com.example.androidstudio.cours;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.androidstudio.R;
import com.google.android.material.tabs.TabLayout;

public class pour_cedric extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pour_cedric);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tabLayout) {
                int position = tabLayout.getPosition();
                switch (position){
                    case 0:
                        // l'utilisateur clique sur definition
                        return;
                    case 1:
                        // l'utilisateur clique sur definition
                        return;
                    case 2:
                        // l'utilisateur clique sur definition
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

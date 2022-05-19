package com.example.bilisimgelisimapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DersActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavDersler;
    private Fragment tempFragment;

    public static Integer dersID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ders);

        bottomNavDersler = findViewById(R.id.bottomNavDersler);

        Intent intent = getIntent();
        dersID = Integer.parseInt(intent.getStringExtra("ders_id"));

        getSupportFragmentManager().beginTransaction().add(R.id.fragmentTutucuDersler,new FragmentDerslerDers()).commit();

        bottomNavDersler.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.action_ders_ozet:
                        tempFragment = new FragmentDerslerDers();
                        break;
                    case R.id.action_konular:
                        tempFragment = new FragmentDerslerKonular();
                        break;
                    case R.id.action_ipuclari:
                        tempFragment = new FragmentDerslerIpuclari();
                        break;
                    default:
                        tempFragment = new FragmentDerslerDers();
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentTutucuDersler,tempFragment).commit();
                return true;
            }
        });

    }


}
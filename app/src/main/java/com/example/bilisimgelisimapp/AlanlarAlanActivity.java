package com.example.bilisimgelisimapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AlanlarAlanActivity extends AppCompatActivity {

    private BottomNavigationView botNavAlan;
    private Fragment tempFragment;
    public static Integer gelenAlanID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alanlar_alan);

        botNavAlan = findViewById(R.id.botNavAlan);

        Intent intent = getIntent();
        gelenAlanID = Integer.parseInt(intent.getStringExtra("alan_id"));

        getSupportFragmentManager().beginTransaction().add(R.id.frameLayoutAlan,new FragmentAlanGiris()).commit();

        botNavAlan.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.action_alan:
                        tempFragment = new FragmentAlanGiris();
                        break;
                    case R.id.action_oneriler:
                        tempFragment = new FragmentAlanOneriler();
                        break;
                    case R.id.action_bilgi:
                        tempFragment = new FragmentAlanBilgiBankasi();
                        break;
                    default:
                        tempFragment = new FragmentAlanGiris();
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutAlan,tempFragment).commit();

                return true;
            }
        });


    }
}
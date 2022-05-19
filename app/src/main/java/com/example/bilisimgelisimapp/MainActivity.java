package com.example.bilisimgelisimapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


public class MainActivity extends AppCompatActivity {


    private Button buttonLogin1,buttonSignUp1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonLogin1 = findViewById(R.id.buttonLogin1);
        buttonSignUp1 = findViewById(R.id.buttonSignUp1);

        buttonLogin1.getBackground().setAlpha(140);
        buttonSignUp1.getBackground().setAlpha(140);

        buttonLogin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this,GirisActivity.class));

            }
        });

        buttonSignUp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this,KayitActivity.class));

            }
        });
        

    }




}
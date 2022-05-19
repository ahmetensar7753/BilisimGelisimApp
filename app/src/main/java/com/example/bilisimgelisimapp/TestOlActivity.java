package com.example.bilisimgelisimapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TestOlActivity extends AppCompatActivity {

    private TextView textViewBaslamakIcinTikla;
    private Integer kullaniciID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_ol);

        textViewBaslamakIcinTikla = findViewById(R.id.textViewBaslamakIcinTikla);
        Intent intent = getIntent();
        kullaniciID = Integer.parseInt(intent.getStringExtra("kullanici_id"));

        textViewBaslamakIcinTikla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestOlActivity.this,TestOlTestActivity.class);
                intent.putExtra("kullanici_id",String.valueOf(kullaniciID));
                startActivity(intent);
                overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
            }
        });

    }
}
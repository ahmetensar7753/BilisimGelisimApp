package com.example.bilisimgelisimapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SistemBilgiOnerActivity extends AppCompatActivity {

    private Button buttonSBOQuizSorusu,buttonSBOAlanIpuclari,buttonSBODersIcerigi,buttonSBOGenelOneri;
    private Integer kullaniciID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sistem_bilgi_oner);

        buttonSBOQuizSorusu = findViewById(R.id.buttonSBOQuizSorusu);
        buttonSBOAlanIpuclari = findViewById(R.id.buttonSBOAlanIpuclari);
        buttonSBODersIcerigi = findViewById(R.id.buttonSBODersIcerigi);
        buttonSBOGenelOneri = findViewById(R.id.buttonSBOGenelOneri);
        kullaniciID = Integer.parseInt(getIntent().getStringExtra("kullanici_id"));


        buttonSBOQuizSorusu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SistemBilgiOnerActivity.this,SBOQuizActivity.class);
                intent.putExtra("kullanici_id",String.valueOf(kullaniciID));
                startActivity(intent);
            }
        });
        buttonSBOAlanIpuclari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(SistemBilgiOnerActivity.this,SBOAlanIpuclariActivity.class);
                intent1.putExtra("kullanici_id",String.valueOf(kullaniciID));
                startActivity(intent1);
            }
        });
        buttonSBODersIcerigi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(SistemBilgiOnerActivity.this,SBODersActivity.class);
                intent2.putExtra("kullanici_id",String.valueOf(kullaniciID));
                startActivity(intent2);
            }
        });
        buttonSBOGenelOneri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(SistemBilgiOnerActivity.this,SBOGenelActivity.class);
                intent3.putExtra("kullanici_id",String.valueOf(kullaniciID));
                startActivity(intent3);
            }
        });


    }
}
package com.example.bilisimgelisimapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bilisimgelisimapp.siniflar.OneriGonder;

public class SBOGenelActivity extends AppCompatActivity {

    private Integer kullaniciID;
    private EditText editTextSBOGenelOneri;
    private Button buttonSBOGenelGonder;

    private OneriGonder gonderici = new OneriGonder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sbogenel);

        kullaniciID = Integer.parseInt(getIntent().getStringExtra("kullanici_id"));

        editTextSBOGenelOneri = findViewById(R.id.editTextSBOGenelOneri);
        buttonSBOGenelGonder = findViewById(R.id.buttonSBOGenelGonder);

        buttonSBOGenelGonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!editTextSBOGenelOneri.getText().toString().trim().equals("")){
                    String oneri = editTextSBOGenelOneri.getText().toString().trim();
                    String tur = "GENEL";
                    gonderici.oneriGonder(tur,oneri,"",SBOGenelActivity.this,kullaniciID);
                    onBackPressed();
                }else {
                    Toast.makeText(SBOGenelActivity.this,"Boş Alan Bırakma!",Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}
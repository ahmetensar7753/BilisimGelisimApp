package com.example.bilisimgelisimapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bilisimgelisimapp.siniflar.OneriGonder;

public class SBOQuizActivity extends AppCompatActivity {

    private Integer kullaniciID;

    private EditText editTextSBOQuizSoru,editTextSBOQuizA,editTextSBOQuizB,editTextSBOQuizC,editTextSBOQuizD,editTextSBOQuizKaynak;
    private Button buttonSBOQuizGonder;

    private OneriGonder gonderici = new OneriGonder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sboquiz);

        kullaniciID = Integer.parseInt(getIntent().getStringExtra("kullanici_id"));

        editTextSBOQuizSoru = findViewById(R.id.editTextSBOQuizSoru);
        editTextSBOQuizA = findViewById(R.id.editTextSBOQuizA);
        editTextSBOQuizB = findViewById(R.id.editTextSBOQuizB);
        editTextSBOQuizC = findViewById(R.id.editTextSBOQuizC);
        editTextSBOQuizD = findViewById(R.id.editTextSBOQuizD);
        editTextSBOQuizKaynak = findViewById(R.id.editTextSBOQuizKaynak);
        buttonSBOQuizGonder = findViewById(R.id.buttonSBOQuizGonder);



        buttonSBOQuizGonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!editTextSBOQuizSoru.getText().toString().trim().equals("") &&
                        !editTextSBOQuizA.getText().toString().trim().equals("") &&
                        !editTextSBOQuizB.getText().toString().trim().equals("") &&
                        !editTextSBOQuizC.getText().toString().trim().equals("") &&
                        !editTextSBOQuizD.getText().toString().trim().equals("") &&
                        !editTextSBOQuizKaynak.getText().toString().trim().equals("")){

                    String icerik = "Soru:"+editTextSBOQuizSoru.getText().toString()+
                            " ŞıkA:"+editTextSBOQuizA.getText().toString()+
                            " ŞıkB:"+editTextSBOQuizB.getText().toString()+
                            " ŞıkC:"+editTextSBOQuizC.getText().toString()+
                            " ŞıkD:"+editTextSBOQuizD.getText().toString();
                    String kaynak = editTextSBOQuizKaynak.getText().toString();
                    String tur = "QUIZ";

                    gonderici.oneriGonder(tur,icerik,kaynak,SBOQuizActivity.this,kullaniciID);
                    onBackPressed();

                }else {
                    Toast.makeText(SBOQuizActivity.this,"Boş Alan Bırakma!",Toast.LENGTH_SHORT).show();
                }




            }
        });

    }
}
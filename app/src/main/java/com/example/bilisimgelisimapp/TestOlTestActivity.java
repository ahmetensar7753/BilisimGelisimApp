package com.example.bilisimgelisimapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bilisimgelisimapp.siniflar.QuizSorular;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class TestOlTestActivity extends AppCompatActivity {
    private Button buttonA,buttonB,buttonC,buttonD;
    private TextView textViewTestOlSure,textViewTestOlSoruNo;
    private TextView textViewQuizSoru;
    private Integer kullaniciID;

    private ArrayList<QuizSorular> soruListesi;
    private ArrayList<String> yanlisSecenekler;
    private QuizSorular dogruCevap;

    private CountDownTimer countDownTimer;

    private int soruSayac = 0;
    private int dogruSayac = 0;
    private int yanlisSayac = 0;

    private ArrayList<String> secenekler = new ArrayList<>();
    private HashSet<String> seceneklerKaristirma = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_ol_test);

        buttonA = findViewById(R.id.buttonA);
        buttonB = findViewById(R.id.buttonB);
        buttonC = findViewById(R.id.buttonC);
        buttonD = findViewById(R.id.buttonD);
        textViewTestOlSure = findViewById(R.id.textViewTestOlSure);
        textViewTestOlSoruNo = findViewById(R.id.textViewTestOlSoruNo);
        textViewQuizSoru = findViewById(R.id.textViewQuizSoru);

        Intent intent = getIntent();
        kullaniciID = Integer.parseInt(intent.getStringExtra("kullanici_id"));

        sorularCek();

        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dogruKontrol(buttonA);
                soruSayacKontrol(true);
            }
        });
        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dogruKontrol(buttonB);
                soruSayacKontrol(true);
            }
        });
        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dogruKontrol(buttonC);
                soruSayacKontrol(true);
            }
        });
        buttonD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dogruKontrol(buttonD);
                soruSayacKontrol(true);
            }
        });



    }

    public void sorularCek(){
        String url = "https://kristalekmek.com/bilisim_tez/quiz/rasgele_on_soru.php";
        StringRequest istek = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                soruListesi = new ArrayList<>();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray sorular = jsonObject.getJSONArray("quiz_table");

                    for (int i = 0; i<sorular.length();i++){
                        JSONObject j = sorular.getJSONObject(i);

                        String soru = j.getString("soru");
                        String dogruCevap = j.getString("dogru_cevap");
                        String cevap2 = j.getString("cevap2");
                        String cevap3 = j.getString("cevap3");
                        String cevap4 = j.getString("cevap4");

                        QuizSorular sor = new QuizSorular(soru,dogruCevap,cevap2,cevap3,cevap4);
                        soruListesi.add(sor);
                    }

                    soruYukle(false);



                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(TestOlTestActivity.this).add(istek);
    }

    public void soruYukle(Boolean tiklandi){

        if (tiklandi){
            countDownTimer.cancel();
        }

        countDownTimer = new CountDownTimer(11000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                textViewTestOlSure.setText(String.valueOf(millisUntilFinished/1000));
            }

            @Override
            public void onFinish() {
                yanlisSayac++;
                soruSayacKontrol(false);
            }
        }.start();



        textViewTestOlSoruNo.setText(String.valueOf(soruSayac+1));
        dogruCevap = soruListesi.get(soruSayac);

        yanlisSecenekler = new ArrayList<>();
        yanlisSecenekler.add(soruListesi.get(soruSayac).getCevap2());
        yanlisSecenekler.add(soruListesi.get(soruSayac).getCevap3());
        yanlisSecenekler.add(soruListesi.get(soruSayac).getCevap4());

        textViewQuizSoru.setText(soruListesi.get(soruSayac).getSoru());

        seceneklerKaristirma.clear();
        seceneklerKaristirma.add(dogruCevap.getDogru_cevap());
        seceneklerKaristirma.add(yanlisSecenekler.get(0));
        seceneklerKaristirma.add(yanlisSecenekler.get(1));
        seceneklerKaristirma.add(yanlisSecenekler.get(2));

        secenekler.clear();

        for (String s : seceneklerKaristirma){
            secenekler.add(s);
        }

        buttonA.setText(secenekler.get(0));
        buttonB.setText(secenekler.get(1));
        buttonC.setText(secenekler.get(2));
        buttonD.setText(secenekler.get(3));

    }

    public void dogruKontrol(Button button){
        String buttonYazi = button.getText().toString();
        String cevap = dogruCevap.getDogru_cevap();

        if (buttonYazi.equals(cevap)){
            dogruSayac++;
        }else {
            yanlisSayac++;
        }

    }

    public void soruSayacKontrol(boolean tiklandi){
        soruSayac++;
            if (soruSayac != 10){
                soruYukle(tiklandi);
            }else {
                Intent intent = new Intent(TestOlTestActivity.this,TestOlSonucActivity.class);
                intent.putExtra("kullanici_id",String.valueOf(kullaniciID));
                intent.putExtra("dogru_say",String.valueOf(dogruSayac));
                intent.putExtra("yanlis_say",String.valueOf(yanlisSayac));
                startActivity(intent);
                overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
                countDownTimer.cancel();
                finish();
            }
    }




}
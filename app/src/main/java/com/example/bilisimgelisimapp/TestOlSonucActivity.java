package com.example.bilisimgelisimapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class TestOlSonucActivity extends AppCompatActivity {

    private TextView textViewTestOlSonucDogru,textViewTestOlSonucYanlis,textViewTestOlSonucPuan;
    private Button buttonTestOlSonucTekrar,buttonTestOlSonucBitir;

    private Integer dogruSay,yanlisSay;
    private Integer kullaniciID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_ol_sonuc);

        textViewTestOlSonucDogru = findViewById(R.id.textViewTestOlSonucDogru);
        textViewTestOlSonucYanlis = findViewById(R.id.textViewTestOlSonucYanlis);
        textViewTestOlSonucPuan = findViewById(R.id.textViewTestOlSonucPuan);
        buttonTestOlSonucTekrar = findViewById(R.id.buttonTestOlSonucTekrar);
        buttonTestOlSonucBitir = findViewById(R.id.buttonTestOlSonucBitir);

        Intent gelenIntent = getIntent();
        dogruSay = Integer.parseInt(gelenIntent.getStringExtra("dogru_say"));
        yanlisSay = Integer.parseInt(gelenIntent.getStringExtra("yanlis_say"));
        kullaniciID = Integer.parseInt(gelenIntent.getStringExtra("kullanici_id"));
        Integer puan = (dogruSay * 10) + (yanlisSay * (-5));

        skorCek(kullaniciID);

        textViewTestOlSonucDogru.setText(String.valueOf(dogruSay));
        textViewTestOlSonucYanlis.setText(String.valueOf(yanlisSay));
        textViewTestOlSonucPuan.setText(String.valueOf(puan));

        buttonTestOlSonucTekrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1 = new Intent(TestOlSonucActivity.this,TestOlActivity.class);
                intent1.putExtra("kullanici_id",String.valueOf(kullaniciID));
                startActivity(intent1);
            }
        });

        buttonTestOlSonucBitir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent2 = new Intent(TestOlSonucActivity.this,AnaSayfaActivity.class);
                intent2.putExtra("kullanici_id",String.valueOf(kullaniciID));
                startActivity(intent2);

            }
        });
    }

    public void skorCek(int id){
        String url = "https://kristalekmek.com/bilisim_tez/quiz/skor_cek.php";
        StringRequest istek = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Integer profilPuan = 0;
                Integer testSayisi = 0;
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray list = jsonObject.getJSONArray("kullanici_table");

                    for (int i = 0; i<list.length();i++){
                        JSONObject j = list.getJSONObject(i);
                         profilPuan = j.getInt("profil_puani");
                         testSayisi = j.getInt("cozulen_test_sayisi");
                    }

                    testSayisi++;
                    profilPuan = profilPuan + Integer.parseInt(textViewTestOlSonucPuan.getText().toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                skorKaydet(id,profilPuan,testSayisi);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("kullanici_id",String.valueOf(id));
                return params;
            }
        };
        Volley.newRequestQueue(TestOlSonucActivity.this).add(istek);
    }

    public void skorKaydet(int id,int puan,int testSay){
        String url = "https://kristalekmek.com/bilisim_tez/quiz/update_skor.php";
        StringRequest istek = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("quizSonucRspns",response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("kullanici_id",String.valueOf(id));
                params.put("profil_puani",String.valueOf(puan));
                params.put("cozulen_test_sayisi",String.valueOf(testSay));
                return params;
            }
        };
        Volley.newRequestQueue(TestOlSonucActivity.this).add(istek);
    }

}
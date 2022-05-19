package com.example.bilisimgelisimapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bilisimgelisimapp.siniflar.Konular;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DersKonuDetayActivity extends AppCompatActivity {

    private Integer konuID;
    private TextView textViewDerslerKonuDersAdi,textViewDerslerKonuAdi,textViewDerslerKonuIcerik;

    private Konular konu;
    private String dersAdi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ders_konu_detay);

        Intent intent = getIntent();
        konuID = Integer.parseInt(intent.getStringExtra("konu_id"));

        textViewDerslerKonuDersAdi = findViewById(R.id.textViewDerslerKonuDersAdi);
        textViewDerslerKonuAdi = findViewById(R.id.textViewDerslerKonuAdi);
        textViewDerslerKonuIcerik = findViewById(R.id.textViewDerslerKonuIcerik);

        konuIcerikCek(konuID);


    }

    public void konuIcerikCek(int konu_id){
        String url = "https://kristalekmek.com/bilisim_tez/dersler/konu_icerik_cek.php";
        StringRequest istek = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                konu = new Konular();

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray list = jsonObject.getJSONArray("konular_table");

                    for (int i = 0 ;i<list.length();i++){
                        JSONObject j = list.getJSONObject(i);

                        String dersAd = j.getString("ders_ad");
                        String konuAd = j.getString("konu_adi");
                        String konuIcerik = j.getString("konu_icerik");

                        konu.setKonu_ad(konuAd);
                        konu.setKonu_icerik(konuIcerik);
                        dersAdi = dersAd;
                    }
                    textViewDerslerKonuDersAdi.setText(dersAdi);
                    textViewDerslerKonuAdi.setText(konu.getKonu_ad());
                    textViewDerslerKonuIcerik.setText(konu.getKonu_icerik());

                } catch (JSONException e) {
                    Log.e("respns",String.valueOf(e));
                    e.printStackTrace();
                }

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
                params.put("konu_id",String.valueOf(konu_id));
                return params;
            }
        };
        Volley.newRequestQueue(DersKonuDetayActivity.this).add(istek);
    }

}
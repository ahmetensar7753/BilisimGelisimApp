package com.example.bilisimgelisimapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bilisimgelisimapp.adapters.AlanlarAdapter;
import com.example.bilisimgelisimapp.siniflar.Alanlar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AlanlarActivity extends AppCompatActivity {

    private RecyclerView rvAlanlar;
    private ArrayList<Alanlar> vtdenCekilenAlanlar;
    private AlanlarAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alanlar);

        rvAlanlar = findViewById(R.id.rvAlanlar);

        alanlarCek();

    }


    public void alanlarCek(){
        String url = "https://kristalekmek.com/bilisim_tez/alanlar/alanlar_cek.php";
        StringRequest istek = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                vtdenCekilenAlanlar = new ArrayList<>();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray alanListe = jsonObject.getJSONArray("alanlar_table");

                    for (int i = 0; i < alanListe.length();i++){
                        JSONObject j = alanListe.getJSONObject(i);

                        int id = j.getInt("alan_id");
                        String ad = j.getString("alan_ad");

                        Alanlar alan = new Alanlar(ad);
                        alan.setAlan_id(id);

                        vtdenCekilenAlanlar.add(alan);
                    }

                    adapter = new AlanlarAdapter(AlanlarActivity.this,vtdenCekilenAlanlar);
                    rvAlanlar.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
                    rvAlanlar.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(AlanlarActivity.this).add(istek);
    }

}
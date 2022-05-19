package com.example.bilisimgelisimapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bilisimgelisimapp.adapters.SiralamaAdapter;
import com.example.bilisimgelisimapp.siniflar.Kullanicilar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SiralamaActivity extends AppCompatActivity {

    private TextView textViewSiralamaSira,textViewSiralamaAd,textViewSiralamaSira2,textViewSiralamaPuan;
    private RecyclerView rvSiralama;

    private ArrayList<Kullanicilar> kullaniciListe;
    private SiralamaAdapter adapter;

    private Integer gelenID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siralama);

        textViewSiralamaAd = findViewById(R.id.textViewSiralamaAd);
        textViewSiralamaSira2 = findViewById(R.id.textViewSiralamaSira2);
        textViewSiralamaSira = findViewById(R.id.textViewSiralamaSira);
        textViewSiralamaPuan = findViewById(R.id.textViewSiralamaPuan);
        rvSiralama = findViewById(R.id.rvSiralama);

        Intent intent = getIntent();
        String gelenKullaniciID = intent.getStringExtra("kullanici_id");
        gelenID = Integer.parseInt(gelenKullaniciID);
        
        siralamaCek(gelenID);
        
    }


    public void siralamaCek(int id){
        String url = "https://kristalekmek.com/bilisim_tez/siralama/siralama_cek.php";
        StringRequest istek = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                kullaniciListe = new ArrayList<>();

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray liste = jsonObject.getJSONArray("kullanici_table");

                    for (int i = 0; i<liste.length();i++){
                        JSONObject j = liste.getJSONObject(i);

                        Integer id = j.getInt("kullanici_id");
                        String ad = j.getString("kullanici_ad");
                        String soyad = j.getString("kullanici_soyad");
                        Integer puan = j.getInt("profil_puani");

                        Kullanicilar k = new Kullanicilar();
                        k.setKullanici_id(id);
                        k.setKullanici_ad(ad);
                        k.setKullanici_soyad(soyad);
                        k.setProfil_puani(puan);

                        kullaniciListe.add(k);
                    }

                    adapter = new SiralamaAdapter(SiralamaActivity.this,kullaniciListe);
                    rvSiralama.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
                    rvSiralama.setAdapter(adapter);

                    for (int i = 0;i<kullaniciListe.size();i++){
                        if (kullaniciListe.get(i).getKullanici_id() == id){
                            textViewSiralamaSira.setText(String.valueOf(i+1));
                            textViewSiralamaSira2.setText(String.valueOf(i+1));
                            textViewSiralamaAd.setText(kullaniciListe.get(i).getKullanici_ad()+" "+kullaniciListe.get(i).getKullanici_soyad());
                            textViewSiralamaPuan.setText(String.valueOf(kullaniciListe.get(i).getProfil_puani()));
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(SiralamaActivity.this).add(istek);
    }

}
package com.example.bilisimgelisimapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bilisimgelisimapp.adapters.DuyuruYorumlarAdapter;
import com.example.bilisimgelisimapp.siniflar.DuyuruVeHaber;
import com.example.bilisimgelisimapp.siniflar.Yorumlar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DuyuruHaberDetayActivity extends AppCompatActivity {

    private FloatingActionButton fabDuyuruHaberYorum,fabDuyuruHaberLike;
    private Animation fab_ileri,fab_geri,arti1;
    private TextView textViewHaberDetayBaslik,textViewHaberDetayIcerik,textViewArti1;
    private ImageView imageViewHaberDetayResim;
    private RecyclerView rvDuyuruHaberYorumlar;

    private DuyuruVeHaber duyuruHaber;
    private Integer gelenID;
    private Integer kullanici_id;
    private ArrayList<Yorumlar> yorumListesi;
    private DuyuruYorumlarAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duyuru_haber_detay);

        fabDuyuruHaberYorum = findViewById(R.id.fabDuyuruHaberYorum);
        fabDuyuruHaberLike = findViewById(R.id.fabDuyuruHaberLike);
        textViewHaberDetayBaslik = findViewById(R.id.textViewHaberDetayBaslik);
        textViewHaberDetayIcerik = findViewById(R.id.textViewHaberDetayIcerik);
        textViewArti1 = findViewById(R.id.textViewArti1);
        imageViewHaberDetayResim = findViewById(R.id.imageViewHaberDetayResim);
        rvDuyuruHaberYorumlar = findViewById(R.id.rvDuyuruHaberYorumlar);

        Intent intent = getIntent();
        String gelenHaberID = intent.getStringExtra("haber_id");
        String gelenKullaniciID = intent.getStringExtra("kullanici_id");
        Log.e("haber_id",gelenHaberID);
        Log.e("kullanici_id",gelenKullaniciID);
        gelenID = Integer.parseInt(gelenHaberID);
        kullanici_id = Integer.parseInt(gelenKullaniciID);

        fab_ileri = AnimationUtils.loadAnimation(DuyuruHaberDetayActivity.this,R.anim.anim_fab_like);
        fab_geri = AnimationUtils.loadAnimation(DuyuruHaberDetayActivity.this,R.anim.anim_fab_unlike);
        arti1 = AnimationUtils.loadAnimation(DuyuruHaberDetayActivity.this,R.anim.arti_1);



        duyHabCek(gelenHaberID,textViewHaberDetayBaslik,textViewHaberDetayIcerik,imageViewHaberDetayResim);
        yorumlariCek(gelenID);
        begeniSorgula(kullanici_id,gelenID);





        fabDuyuruHaberLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fabDuyuruHaberLike.startAnimation(fab_ileri);
                fabDuyuruHaberLike.startAnimation(fab_geri);
                textViewArti1.setText("+ 1");
                textViewArti1.startAnimation(arti1);


                //begeni artacak ve begeni table insert olacak
                int begeniSayisi = duyuruHaber.getDuy_hab_begeni_say();
                begeniSayisi++;
                begeniArtir(duyuruHaber.getDuy_hab_id(),begeniSayisi);

                //buraya genei tablosuna id yazdır
                insertBegeni(kullanici_id,gelenID);
            }
        });



        fabDuyuruHaberYorum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View view = getLayoutInflater().inflate(R.layout.alert_tasarim_yorum_yap,null);
                EditText editTextYorumYap = view.findViewById(R.id.editTextYorumYap);
                EditText editTextYorumBaslik = view.findViewById(R.id.editTextYorumBaslik);
                AlertDialog.Builder bd = new AlertDialog.Builder(DuyuruHaberDetayActivity.this);

                bd.setTitle("YORUM");
                bd.setView(view);
                bd.setIcon(R.drawable.yorum_resim);

                bd.setPositiveButton("Gönder", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Yorumlar yorum = new Yorumlar(editTextYorumBaslik.getText().toString(),editTextYorumYap.getText().toString());
                        yorum.setBagli_duy_hab_id(gelenID);
                        yorum.setBagli_kullanici_id(kullanici_id);

                        // yorum gönderiliyor.
                        yorumYap(yorum.getYorum_baslik(),yorum.getYorum_icerik(),yorum.getBagli_duy_hab_id(),yorum.getBagli_kullanici_id());
                        Toast.makeText(DuyuruHaberDetayActivity.this,"Yorum yapıldı",Toast.LENGTH_SHORT).show();
                    }
                });

                bd.setNegativeButton("İptal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //boş
                    }
                });
                bd.create().show();

            }
        });

    }

    public void duyHabCek(String id,TextView textViewHaberDetayBaslik,TextView textViewHaberDetayIcerik,ImageView imageViewHaberDetayResim){
        String url = "https://kristalekmek.com/bilisim_tez/duyuru_haberler/haber_detay_cek.php";
        StringRequest istek = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray haber = jsonObject.getJSONArray("duyuru_haber_table");

                    for (int i = 0 ;i<haber.length();i++){
                        JSONObject j = haber.getJSONObject(i);
                        String baslik = j.getString("baslik");
                        String icerik = j.getString("duy_hab_icerik");
                        String resim_ad = j.getString("duy_hab_resim_ad");
                        Integer begeni_say = j.getInt("duy_hab_begeni_say");

                        duyuruHaber = new DuyuruVeHaber();
                        duyuruHaber.setBaslik(baslik);
                        duyuruHaber.setDuy_hab_resim_ad(resim_ad);
                        duyuruHaber.setDuy_hab_icerik(icerik);
                        duyuruHaber.setDuy_hab_begeni_say(begeni_say);
                        duyuruHaber.setDuy_hab_id(gelenID);

                    }
                    //içerikler set ediliyor
                    textViewHaberDetayBaslik.setText(duyuruHaber.getBaslik());
                    textViewHaberDetayIcerik.setText(duyuruHaber.getDuy_hab_icerik());

                    //resim yükleniyor
                    String urlResim = "https://kristalekmek.com/bilisim_tez/duyuru_haberler/duyuru_haber_resimler/"+duyuruHaber.getDuy_hab_resim_ad()+".jpg";
                    Picasso.get().load(urlResim).into(imageViewHaberDetayResim);


                } catch (JSONException e) {
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
                params.put("duy_hab_id",id);
                return params;
            }
        };
        Volley.newRequestQueue(DuyuruHaberDetayActivity.this).add(istek);
    }

    public void yorumYap(String yorumBaslik,String yorumIcerik,int haberID,int kullaniciID){
        String url = "https://kristalekmek.com/bilisim_tez/duyuru_haberler/yorum_yap.php";
        StringRequest istek = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("ResponseYorumYap",response);
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

                params.put("yorum_baslik",yorumBaslik);
                params.put("yorum_icerik",yorumIcerik);
                params.put("duy_hab_id",String.valueOf(haberID));
                params.put("kullanici_id",String.valueOf(kullaniciID));

                return params;
            }
        };
        Volley.newRequestQueue(DuyuruHaberDetayActivity.this).add(istek);
    }

    public void yorumlariCek(int haberId){
        String url = "https://kristalekmek.com/bilisim_tez/duyuru_haberler/yorumlari_cek.php";
        StringRequest istek = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                yorumListesi = new ArrayList<>();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray yorumList = jsonObject.getJSONArray("yorumlar_table");

                    for (int i = 0;i<yorumList.length();i++){
                        JSONObject j = yorumList.getJSONObject(i);

                        String yorumSahibiAd = j.getString("kullanici_ad");
                        String yorumSahibiSoyad = j.getString("kullanici_soyad");
                        String baslik = j.getString("yorum_baslik");
                        String icerik = j.getString("yorum_icerik");

                        Yorumlar yorum = new Yorumlar(baslik,icerik);
                        yorum.setYorumSahibiAd(yorumSahibiAd+" "+yorumSahibiSoyad);

                        yorumListesi.add(yorum);
                    }

                    adapter = new DuyuruYorumlarAdapter(DuyuruHaberDetayActivity.this,yorumListesi);
                    rvDuyuruHaberYorumlar.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
                    rvDuyuruHaberYorumlar.setAdapter(adapter);


                } catch (JSONException e) {
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
                params.put("duy_hab_id",String.valueOf(haberId));
                return params;
            }
        };
        Volley.newRequestQueue(DuyuruHaberDetayActivity.this).add(istek);
    }


    public void begeniSorgula(int kisi_id,int duyuru_id){
        String url = "https://kristalekmek.com/bilisim_tez/duyuru_haberler/begeni_sorgula.php";
        StringRequest istek = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("duyuru_begeni_table");

                    for (int i = 0 ;i<jsonArray.length();i++){
                        JSONObject j = jsonArray.getJSONObject(i);
                        int gelenKisiID = j.getInt("begenen_kisi_id");

                        if (gelenKisiID == kisi_id){
                            fabDuyuruHaberLike.setVisibility(View.INVISIBLE);
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
        }){
            @Nullable
            @Override
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("begenen_kisi_id",String.valueOf(kisi_id));
                params.put("duyuru_id",String.valueOf(duyuru_id));
                return params;
            }
        };
        Volley.newRequestQueue(DuyuruHaberDetayActivity.this).add(istek);
    }

    public void begeniArtir(int duyID,int begeniSay){
        String url = "https://kristalekmek.com/bilisim_tez/duyuru_haberler/begeni_artir.php";
        StringRequest istek = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("begenıArtırRspns",response);
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
                params.put("duy_hab_id",String.valueOf(duyID));
                params.put("duy_hab_begeni_say",String.valueOf(begeniSay));
                return params;
            }
        };
        Volley.newRequestQueue(DuyuruHaberDetayActivity.this).add(istek);
    }

    public void insertBegeni(int kisiID,int duyuruID){
        String url = "https://kristalekmek.com/bilisim_tez/duyuru_haberler/insert_begeni.php";
        StringRequest istek = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("insertBegeni",response);
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
                params.put("begenen_kisi_id",String.valueOf(kisiID));
                params.put("duyuru_id",String.valueOf(duyuruID));
                return params;
            }
        };
        Volley.newRequestQueue(DuyuruHaberDetayActivity.this).add(istek);
    }

}
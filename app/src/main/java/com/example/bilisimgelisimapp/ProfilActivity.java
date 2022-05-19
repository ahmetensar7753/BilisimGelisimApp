package com.example.bilisimgelisimapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import com.example.bilisimgelisimapp.siniflar.Kullanicilar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ProfilActivity extends AppCompatActivity {

    private TextView textViewProfilPuan,textViewProfilSoru,textViewProfilAdSoyad,textViewProfilEmail,textViewProfilParola,textViewProfilBolum;
    private Button buttonProfilKaydet;
    private ImageView imageViewProfilDuzenle;
    private EditText editTextProfilDuzenleAd,editTextProfilDuzenleMail,editTextProfilDuzenleSifre,editTextProfilDuzenleBolum;
    private TextView textViewTempAd,textViewTempMail,textViewTempSifre,textViewTempBolum;

    private Integer kullaniciID;
    private Kullanicilar kullanici;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        textViewProfilPuan = findViewById(R.id.textViewProfilPuan);
        textViewProfilSoru = findViewById(R.id.textViewProfilSoru);
        textViewProfilAdSoyad = findViewById(R.id.textViewProfilAdSoyad);
        textViewProfilEmail = findViewById(R.id.textViewProfilEmail);
        textViewProfilParola = findViewById(R.id.textViewProfilParola);
        textViewProfilBolum = findViewById(R.id.textViewProfilBolum);

        textViewTempAd = findViewById(R.id.textViewTempAd);
        textViewTempMail = findViewById(R.id.textViewTempMail);
        textViewTempSifre = findViewById(R.id.textViewTempSifre);
        textViewTempBolum = findViewById(R.id.textViewTempBolum);

        buttonProfilKaydet = findViewById(R.id.buttonProfilKaydet);
        imageViewProfilDuzenle = findViewById(R.id.imageViewProfilDuzenle);
        editTextProfilDuzenleAd = findViewById(R.id.editTextProfilDuzenleAd);
        editTextProfilDuzenleMail = findViewById(R.id.editTextProfilDuzenleMail);
        editTextProfilDuzenleSifre = findViewById(R.id.editTextProfilDuzenleSifre);
        editTextProfilDuzenleBolum = findViewById(R.id.editTextProfilDuzenleBolum);

        Intent intent = getIntent();
        String gelenKullaniciID = intent.getStringExtra("kullanici_id");
        kullaniciID = Integer.parseInt(gelenKullaniciID);

        kullaniciCek(kullaniciID);

        imageViewProfilDuzenle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textViewProfilAdSoyad.setVisibility(View.INVISIBLE);
                textViewProfilEmail.setVisibility(View.INVISIBLE);
                textViewProfilParola.setVisibility(View.INVISIBLE);
                textViewProfilBolum.setVisibility(View.INVISIBLE);

                textViewTempAd.setVisibility(View.INVISIBLE);
                textViewTempMail.setVisibility(View.INVISIBLE);
                textViewTempSifre.setVisibility(View.INVISIBLE);
                textViewTempBolum.setVisibility(View.INVISIBLE);

                editTextProfilDuzenleAd.setText(textViewProfilAdSoyad.getText().toString());
                editTextProfilDuzenleMail.setText(textViewProfilEmail.getText().toString());
                editTextProfilDuzenleSifre.setText(textViewProfilParola.getText().toString());
                editTextProfilDuzenleBolum.setText(textViewProfilBolum.getText().toString());

                buttonProfilKaydet.setVisibility(View.VISIBLE);
                editTextProfilDuzenleAd.setVisibility(View.VISIBLE);
                editTextProfilDuzenleMail.setVisibility(View.VISIBLE);
                editTextProfilDuzenleSifre.setVisibility(View.VISIBLE);
                editTextProfilDuzenleBolum.setVisibility(View.VISIBLE);

            }
        });

        buttonProfilKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ad ;
                String soyad ;

                String str = editTextProfilDuzenleAd.getText().toString().trim();
                String [] dizi = str.split("\\s");

                Log.e("dizi boyutu",String.valueOf(dizi.length));

                if (dizi.length == 2){
                    ad = dizi[0];
                    soyad = dizi[1];
                }else {
                    ad = dizi[0]+" "+dizi[1];
                    soyad = dizi [2];
                }

                String mail = editTextProfilDuzenleMail.getText().toString();
                String sifre = editTextProfilDuzenleSifre.getText().toString();
                String bolum = editTextProfilDuzenleBolum.getText().toString();

                kullaniciUpdate(kullaniciID,ad,soyad,mail,sifre,bolum);


            }
        });

    }

    public void kullaniciCek(int id){
        String url = "https://kristalekmek.com/bilisim_tez/profil/kullanici_cek.php";
        StringRequest istek = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray liste = jsonObject.getJSONArray("kullanici_table");

                    for (int i =0; i<liste.length();i++){
                        JSONObject j = liste.getJSONObject(i);

                        String ad = j.getString("kullanici_ad");
                        String soyad = j.getString("kullanici_soyad");
                        String mail = j.getString("e_mail");
                        String sifre = j.getString("kullanici_sifre");
                        String bolum = j.getString("kullanici_bolum");
                        Integer puan = j.getInt("profil_puani");
                        Integer testSay = j.getInt("cozulen_test_sayisi");

                        kullanici = new Kullanicilar(ad,soyad,mail,sifre,bolum);
                        kullanici.setProfil_puani(puan);
                        kullanici.setCozulen_test_sayisi(testSay);
                    }

                    textViewProfilPuan.setText(String.valueOf(kullanici.getProfil_puani()));
                    textViewProfilSoru.setText(String.valueOf(kullanici.getCozulen_test_sayisi()));
                    textViewProfilAdSoyad.setText(kullanici.getKullanici_ad()+" "+kullanici.getKullanici_soyad());
                    textViewProfilEmail.setText(kullanici.getE_mail());
                    textViewProfilParola.setText(kullanici.getKullanici_sifre());
                    textViewProfilBolum.setText(kullanici.getKullanici_bolum());

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
                params.put("kullanici_id",String.valueOf(id));
                return params;
            }
        };
        Volley.newRequestQueue(ProfilActivity.this).add(istek);
    }

    public void kullaniciUpdate(int id,String ad,String soyad,String mail,String sifre,String bolum){
        String url = "https://kristalekmek.com/bilisim_tez/profil/update_kullanici.php";
        StringRequest istek = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("kullnciUPDTrspns",response);
                Toast.makeText(ProfilActivity.this,"Bilgiler Güncellendi!",Toast.LENGTH_SHORT).show();
                onBackPressed();
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
                params.put("kullanici_ad",ad);
                params.put("kullanici_soyad",soyad);
                params.put("e_mail",mail);
                params.put("kullanici_sifre",sifre);
                params.put("kullanici_bolum",bolum);

                return params;
            }
        };
        Volley.newRequestQueue(ProfilActivity.this).add(istek);
    }



}
















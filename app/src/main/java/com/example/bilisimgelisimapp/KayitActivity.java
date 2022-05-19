package com.example.bilisimgelisimapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class KayitActivity extends AppCompatActivity {

    private EditText editTextKayitAd,editTextKayitSoyad,editTextKayitEposta,editTextKayitParola,editTextKayitBolum;
    private Button buttonSignUp2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit);

        editTextKayitAd = findViewById(R.id.editTextKayitAd);
        editTextKayitSoyad = findViewById(R.id.editTextKayitSoyad);
        editTextKayitEposta = findViewById(R.id.editTextKayitEposta);
        editTextKayitParola = findViewById(R.id.editTextKayitParola);
        editTextKayitBolum = findViewById(R.id.editTextKayitBolum);
        buttonSignUp2 = findViewById(R.id.buttonSignUp2);

        buttonSignUp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!editTextKayitAd.getText().toString().trim().isEmpty()&&
                        !editTextKayitSoyad.getText().toString().trim().isEmpty()&&
                        !editTextKayitEposta.getText().toString().trim().isEmpty()&&
                        !editTextKayitParola.getText().toString().trim().isEmpty()&&
                        !editTextKayitBolum.getText().toString().trim().isEmpty()){

                    Kullanicilar yniKullanici = new Kullanicilar(editTextKayitAd.getText().toString(),
                            editTextKayitSoyad.getText().toString(),
                            editTextKayitEposta.getText().toString(),
                            editTextKayitParola.getText().toString(),
                            editTextKayitBolum.getText().toString());

                    eMailVarMi(editTextKayitEposta.getText().toString().trim(),yniKullanici);

                }else {
                    Toast.makeText(KayitActivity.this,"Boş alan bırakmayınız!",Toast.LENGTH_SHORT).show();
                }





            }
        });


    }

    public void eMailVarMi(String eMail,Kullanicilar yeniKullanici){

        String url = "https://kristalekmek.com/bilisim_tez/kayit_ol/email_varmi.php";
        StringRequest istek = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray liste = jsonObject.getJSONArray("kullanici_table");

                    for (int i = 0 ;i<liste.length();i++){
                        JSONObject k = liste.getJSONObject(i);
                        String gelenMail = k.getString("e_mail");
                        if (gelenMail.equals(eMail)){
                            Toast.makeText(KayitActivity.this,"Mail adresi sistemde kayıtlı!",Toast.LENGTH_SHORT).show();
                        }
                    }


                } catch (JSONException e) {

                    // kayıt işlemi burada gerçekleşecek.
                    kullaniciKaydet(yeniKullanici.getKullanici_ad(),
                            yeniKullanici.getKullanici_soyad(),
                            yeniKullanici.getE_mail(),
                            yeniKullanici.getKullanici_sifre(),
                            yeniKullanici.getKullanici_bolum());
                    startActivity(new Intent(KayitActivity.this,GirisActivity.class));
                    finish();
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
                params.put("e_mail",eMail);

                return params;
            }
        };
        Volley.newRequestQueue(KayitActivity.this).add(istek);
    }

    public void kullaniciKaydet(String ad,String soyad,String mail,String sifre,String bolum){
        String url = "https://kristalekmek.com/bilisim_tez/kayit_ol/insert_kullanici.php";
        StringRequest istek = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(KayitActivity.this,"Kayıt Gerçekleşti",Toast.LENGTH_SHORT).show();
                Log.e("kayitRespns",response);
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
                params.put("kullanici_ad",ad);
                params.put("kullanici_soyad",soyad);
                params.put("e_mail",mail);
                params.put("kullanici_sifre",sifre);
                params.put("kullanici_bolum",bolum);
                return params;
            }
        };
        Volley.newRequestQueue(KayitActivity.this).add(istek);
    }


}
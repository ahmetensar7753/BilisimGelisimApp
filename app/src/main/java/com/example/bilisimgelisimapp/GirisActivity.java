package com.example.bilisimgelisimapp;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
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
import com.example.bilisimgelisimapp.siniflar.MailGonderici;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GirisActivity extends AppCompatActivity {

    private EditText editTextUserName,editTextPassword;
    private Button buttonLogin2;
    private TextView textViewParolamiUnuttum;
    private String glnSifre = "";
    private ArrayList<Kullanicilar> vtdenGelenKullanicilar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris);

        editTextUserName = findViewById(R.id.editTextUserName);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin2 = findViewById(R.id.buttonLogin2);
        textViewParolamiUnuttum = findViewById(R.id.textViewParolamiUnuttum);

        kullanicilariCek();


        buttonLogin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String girilenMail = editTextUserName.getText().toString();
                String girilenSifre = editTextPassword.getText().toString();
                Integer gelenKullanici_id = 0;
                int index = 0;
                Kullanicilar tempKullanici = new Kullanicilar(gelenKullanici_id,girilenMail,girilenSifre);

                for (Kullanicilar k:vtdenGelenKullanicilar){
                    if (k.getE_mail().equals(girilenMail) && k.getKullanici_sifre().equals(girilenSifre)){
                        gelenKullanici_id = k.getKullanici_id();

                        tempKullanici.setE_mail(girilenMail);
                        tempKullanici.setKullanici_sifre(girilenSifre);
                        tempKullanici.setKullanici_id(gelenKullanici_id);

                        index = vtdenGelenKullanicilar.indexOf(k);

                        Intent intent = new Intent(GirisActivity.this,AnaSayfaActivity.class);
                        intent.putExtra("kullanici_id",String.valueOf(gelenKullanici_id));
                        startActivity(intent);

                        break;
                    }
                }
                //burada doğru giriş olduğu zaman uyarı verilmemesi sağlanıyor.
                //doğru giriş olmadığı zaman her türlü bu koşula giriliyor.
                if (vtdenGelenKullanicilar.get(index).getKullanici_id() != tempKullanici.getKullanici_id()){
                    Toast.makeText(GirisActivity.this,"Mail yada Şifre yanlış!",Toast.LENGTH_SHORT).show();
                }



            }
        });

        textViewParolamiUnuttum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View view = getLayoutInflater().inflate(R.layout.alert_tasarim_parolami_unuttum,null);
                EditText editTextParolamiUnuttumEposta = view.findViewById(R.id.editTextParolamiUnuttumEposta);
                AlertDialog.Builder bd = new AlertDialog.Builder(GirisActivity.this);

                bd.setTitle("Parolamı Unuttum");
                bd.setView(view);
                bd.setIcon(R.drawable.soru_isareti);

                bd.setPositiveButton("Gönder", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        parolamiUnuttum(editTextParolamiUnuttumEposta.getText().toString());

                        MailGonderici mg = new MailGonderici(GirisActivity.this,editTextParolamiUnuttumEposta.getText().toString(),
                                "Parola Hatırlatma!","Kullanici adınız : "+editTextParolamiUnuttumEposta.getText().toString()+" Kullanıcı Şifreniz : "+ glnSifre);
                        mg.execute();
                    }
                }).create().show();

            }
        });

    }

    public void parolamiUnuttum(String mail){
        String url = "https://kristalekmek.com/bilisim_tez/parolami_unuttum/parolami_unuttum.php";
        StringRequest istek = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray parola = jsonObject.getJSONArray("kullanici_table");

                    for (int i = 0 ;i<parola.length();i++){
                        JSONObject k = parola.getJSONObject(i);
                        String gelenSifre = k.getString("kullanici_sifre");
                        glnSifre = gelenSifre;
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
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("e_mail",mail);
                return params;
            }
        };
        Volley.newRequestQueue(GirisActivity.this).add(istek);
    }

    public void kullanicilariCek(){
        String url = "https://kristalekmek.com/bilisim_tez/login/kullanicilari_cek.php";
        StringRequest istek = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                vtdenGelenKullanicilar = new ArrayList<>();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray kullaniciList = jsonObject.getJSONArray("kullanici_table");

                    for (int i = 0 ;i<kullaniciList.length();i++){
                        JSONObject k = kullaniciList.getJSONObject(i);

                        String kullanici_id = k.getString("kullanici_id");
                        String e_mail = k.getString("e_mail");
                        String kullanici_sifre = k.getString("kullanici_sifre");

                        Kullanicilar kullanici = new Kullanicilar(Integer.parseInt(kullanici_id),e_mail,kullanici_sifre);
                        vtdenGelenKullanicilar.add(kullanici);

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
        Volley.newRequestQueue(GirisActivity.this).add(istek);
    }

}
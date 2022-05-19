package com.example.bilisimgelisimapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bilisimgelisimapp.siniflar.OneriGonder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SBOAlanIpuclariActivity extends AppCompatActivity {

    private Integer kullaniciID;

    private Spinner spinnerSBOAlanIpucu;
    private EditText editTextSBOAlanIpucu,editTextSBOAlanIpucuKaynak;
    private Button buttonSBOAlanGonder;

    private OneriGonder gonderici = new OneriGonder();

    private ArrayList<String> alanlar;
    private ArrayAdapter<String> veriAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sboalan_ipuclari);

        kullaniciID = Integer.parseInt(getIntent().getStringExtra("kullanici_id"));

        spinnerSBOAlanIpucu = findViewById(R.id.spinnerSBOAlanIpucu);
        editTextSBOAlanIpucu = findViewById(R.id.editTextSBOAlanIpucu);
        editTextSBOAlanIpucuKaynak = findViewById(R.id.editTextSBOAlanIpucuKaynak);
        buttonSBOAlanGonder = findViewById(R.id.buttonSBOAlanGonder);

        alanlarCek();

        buttonSBOAlanGonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!editTextSBOAlanIpucu.getText().toString().trim().equals("") && !editTextSBOAlanIpucuKaynak.getText().toString().trim().equals("")){
                    String tur = "Alan:"+alanlar.get(spinnerSBOAlanIpucu.getSelectedItemPosition());
                    String icerik = editTextSBOAlanIpucu.getText().toString().trim();
                    String kaynak = editTextSBOAlanIpucuKaynak.getText().toString().trim();
                    gonderici.oneriGonder(tur,icerik,kaynak,SBOAlanIpuclariActivity.this,kullaniciID);
                    onBackPressed();
                }else {
                    Toast.makeText(SBOAlanIpuclariActivity.this,"Boş Alan Bırakma!",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public void alanlarCek(){
        String url = "https://kristalekmek.com/bilisim_tez/SBO/alanlar_cek.php";
        StringRequest istek = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                alanlar = new ArrayList<>();
                try {
                    JSONObject js = new JSONObject(response);
                    JSONArray gelenAlanlar = js.getJSONArray("alanlar_table");

                    for (int i = 0; i<gelenAlanlar.length();i++){
                        JSONObject j = gelenAlanlar.getJSONObject(i);
                        String alanAd = j.getString("alan_ad");
                        alanlar.add(alanAd);
                    }

                    veriAdapter = new ArrayAdapter<String>(SBOAlanIpuclariActivity.this,android.R.layout.simple_list_item_1,android.R.id.text1,alanlar);
                    spinnerSBOAlanIpucu.setAdapter(veriAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(SBOAlanIpuclariActivity.this).add(istek);
    }

}


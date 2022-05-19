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

public class SBODersActivity extends AppCompatActivity {

    private Integer kullaniciID;

    private Spinner spinnerSBODersler;
    private EditText editTextSBODersOneri,editTextSBODersKaynak;
    private Button buttonSBODersGonder;

    private OneriGonder gonderici = new OneriGonder();

    private ArrayList<String> dersler;
    private ArrayAdapter<String> veriAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sboders);

        kullaniciID = Integer.parseInt(getIntent().getStringExtra("kullanici_id"));

        spinnerSBODersler = findViewById(R.id.spinnerSBODersler);
        editTextSBODersOneri = findViewById(R.id.editTextSBODersOneri);
        editTextSBODersKaynak = findViewById(R.id.editTextSBODersKaynak);
        buttonSBODersGonder = findViewById(R.id.buttonSBODersGonder);

        derslerCek();

        buttonSBODersGonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!editTextSBODersOneri.getText().toString().trim().equals("") && !editTextSBODersKaynak.getText().toString().trim().equals("")){
                    String tur = "Ders:"+dersler.get(spinnerSBODersler.getSelectedItemPosition());
                    String icerik = editTextSBODersOneri.getText().toString().trim();
                    String kaynak = editTextSBODersKaynak.getText().toString().trim();
                    gonderici.oneriGonder(tur,icerik,kaynak,SBODersActivity.this,kullaniciID);
                    onBackPressed();
                }else {
                    Toast.makeText(SBODersActivity.this,"Boş Alan Bırakma!",Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    public void derslerCek(){
        String url = "https://kristalekmek.com/bilisim_tez/SBO/dersler_cek.php";
        StringRequest istek = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                dersler = new ArrayList<>();
                try {
                    JSONObject js = new JSONObject(response);
                    JSONArray gelenDersler = js.getJSONArray("dersler_table");

                    for (int i = 0; i<gelenDersler.length();i++){
                        JSONObject j = gelenDersler.getJSONObject(i);
                        String dersAd = j.getString("ders_ad");
                        dersler.add(dersAd);
                    }

                    veriAdapter = new ArrayAdapter<String>(SBODersActivity.this,android.R.layout.simple_list_item_1,android.R.id.text1,dersler);
                    spinnerSBODersler.setAdapter(veriAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(SBODersActivity.this).add(istek);
    }

}
package com.example.bilisimgelisimapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bilisimgelisimapp.siniflar.Dersler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FragmentDerslerIpuclari extends Fragment {


    private Integer gelenDersID;
    private Dersler ders;

    private TextView textViewDersIpucuKullanimAlanlari,textViewDersIpucuZorluk,textViewDersIpucuProgDil,textViewDersIpucuSaat,textViewDersIpucuTerimler;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dersler_ipuclari,container,false);

        textViewDersIpucuKullanimAlanlari = rootView.findViewById(R.id.textViewDersIpucuKullanimAlanlari);
        textViewDersIpucuZorluk = rootView.findViewById(R.id.textViewDersIpucuZorluk);
        textViewDersIpucuProgDil = rootView.findViewById(R.id.textViewDersIpucuProgDil);
        textViewDersIpucuSaat = rootView.findViewById(R.id.textViewDersIpucuSaat);
        textViewDersIpucuTerimler = rootView.findViewById(R.id.textViewDersIpucuTerimler);

        gelenDersID = DersActivity.dersID;
        Log.e("gelenDersID",String.valueOf(gelenDersID));

        dersIpucuCek(gelenDersID);

        return rootView;
    }

    public void dersIpucuCek(int id){
        String url = "https://kristalekmek.com/bilisim_tez/dersler/ders_ipucu_cek.php";
        StringRequest istek = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                ders = new Dersler();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray list = jsonObject.getJSONArray("dersler_table");

                    for (int i = 0 ;i<list.length();i++){
                        JSONObject j = list.getJSONObject(i);

                        Double zorluk = j.getDouble("zorluk_derecesi");
                        String kullanimAlanlari = j.getString("kullanim_alanlari");
                        String kullanilanDiller = j.getString("kullanilan_diller");
                        Integer calismaSuresi = j.getInt("calisma_suresi");
                        String terimler = j.getString("terimler");

                        ders.setZorluk_derecesi(zorluk);
                        ders.setKullanim_alanlari(kullanimAlanlari);
                        ders.setKullanim_alanlari(kullanimAlanlari);
                        ders.setKullanilan_diller(kullanilanDiller);
                        ders.setCalisma_suresi(calismaSuresi);
                        ders.setTerimler(terimler);
                    }

                    textViewDersIpucuKullanimAlanlari.setText(ders.getKullanim_alanlari());
                    textViewDersIpucuZorluk.setText(String.valueOf(ders.getZorluk_derecesi()));
                    textViewDersIpucuProgDil.setText(ders.getKullanilan_diller());
                    textViewDersIpucuSaat.setText(String.valueOf(ders.getCalisma_suresi()));
                    textViewDersIpucuTerimler.setText(ders.getTerimler());



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
                params.put("ders_id",String.valueOf(id));
                return params;
            }
        };
        Volley.newRequestQueue(getActivity()).add(istek);
    }


}











package com.example.bilisimgelisimapp;

import android.os.Bundle;
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
import com.example.bilisimgelisimapp.siniflar.Alanlar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FragmentAlanGiris extends Fragment {

    private TextView textViewAlanOnBilgilendirme,textViewAlanAlanAdi;
    private Integer alanID;
    private Alanlar alan;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_alan_giris,container,false);

        textViewAlanAlanAdi = rootView.findViewById(R.id.textViewAlanAlanAdi);
        textViewAlanOnBilgilendirme = rootView.findViewById(R.id.textViewAlanOnBilgilendirme);
        alanID = AlanlarAlanActivity.gelenAlanID;

        alanGirisCek(alanID);

        return rootView;
    }

    public void alanGirisCek(int id){
        String url = "https://kristalekmek.com/bilisim_tez/alanlar/alan_giris_cek.php";
        StringRequest istek = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                alan = new Alanlar();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray list = jsonObject.getJSONArray("alanlar_table");

                    for (int i = 0;i<list.length();i++){
                        JSONObject j = list.getJSONObject(i);

                        String alanAd = j.getString("alan_ad");
                        String alanOzet = j.getString("alan_ozet");

                        alan.setAlan_id(id);
                        alan.setAlan_ad(alanAd);
                        alan.setAlan_ozet(alanOzet);
                    }
                    textViewAlanAlanAdi.setText(alan.getAlan_ad());
                    textViewAlanOnBilgilendirme.setText(alan.getAlan_ozet());


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
                params.put("alan_id",String.valueOf(id));
                return params;
            }
        };
        Volley.newRequestQueue(getActivity()).add(istek);
    }

}

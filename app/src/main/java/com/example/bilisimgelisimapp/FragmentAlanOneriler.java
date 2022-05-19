package com.example.bilisimgelisimapp;

import android.graphics.Paint;
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

public class FragmentAlanOneriler extends Fragment {

    private TextView textViewOneriProjeBaslangic,textViewOneriProjeOrta,textViewOneriProjeIleri,
            textViewAlanYaziliKaynakOneri,textViewAlanVideoKaynakOneri,textViewAlanCalismaSuresi;
    private Integer alanID;
    private Alanlar alan;

    private TextView textViewBaslngc,textViewOrtSevye,textViewIlerSevy;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_alan_oneriler,container,false);

        textViewOneriProjeBaslangic = rootView.findViewById(R.id.textViewOneriProjeBaslangic);
        textViewOneriProjeOrta = rootView.findViewById(R.id.textViewOneriProjeOrta);
        textViewOneriProjeIleri = rootView.findViewById(R.id.textViewOneriProjeIleri);
        textViewAlanYaziliKaynakOneri = rootView.findViewById(R.id.textViewAlanYaziliKaynakOneri);
        textViewAlanVideoKaynakOneri = rootView.findViewById(R.id.textViewAlanVideoKaynakOneri);
        textViewAlanCalismaSuresi = rootView.findViewById(R.id.textViewAlanCalismaSuresi);

        textViewBaslngc = rootView.findViewById(R.id.textViewBaslngc);
        textViewOrtSevye = rootView.findViewById(R.id.textViewOrtSevye);
        textViewIlerSevy = rootView.findViewById(R.id.textViewIlerSevy);

        textViewBaslngc.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        textViewOrtSevye.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        textViewIlerSevy.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);


        alanID = AlanlarAlanActivity.gelenAlanID;

        alanOneriCek(alanID);


        return rootView;
    }

    public void alanOneriCek(int id){
        String url = "https://kristalekmek.com/bilisim_tez/alanlar/alan_oneri_cek.php";
        StringRequest istek = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                alan = new Alanlar();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray liste = jsonObject.getJSONArray("alanlar_table");

                    for (int i = 0;i<liste.length();i++){
                        JSONObject j = liste.getJSONObject(i);

                        String projeBas = j.getString("alan_proje_baslangic");
                        String projeOrt = j.getString("alan_proje_orta");
                        String projeIlr = j.getString("alan_proje_ileri");
                        String yaziliKaynak = j.getString("alan_yazili_kaynak");
                        String videoKaynak = j.getString("alan_video_kaynak");
                        Integer alanCalsmaSure = j.getInt("alan_calisma_suresi");

                        alan.setAlan_proje_baslangic(projeBas);
                        alan.setAlan_proje_orta(projeOrt);
                        alan.setAlan_proje_ileri(projeIlr);
                        alan.setAlan_yazili_kaynak(yaziliKaynak);
                        alan.setAlan_video_kaynak(videoKaynak);
                        alan.setAlan_calisma_suresi(alanCalsmaSure);
                    }
                    textViewOneriProjeBaslangic.setText(alan.getAlan_proje_baslangic());
                    textViewOneriProjeOrta.setText(alan.getAlan_proje_orta());
                    textViewOneriProjeIleri.setText(alan.getAlan_proje_ileri());
                    textViewAlanYaziliKaynakOneri.setText(alan.getAlan_yazili_kaynak());
                    textViewAlanVideoKaynakOneri.setText(alan.getAlan_video_kaynak());
                    textViewAlanCalismaSuresi.setText(String.valueOf(alan.getAlan_calisma_suresi())+" saat");


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

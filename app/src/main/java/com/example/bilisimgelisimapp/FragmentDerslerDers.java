package com.example.bilisimgelisimapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bilisimgelisimapp.siniflar.Dersler;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FragmentDerslerDers extends Fragment {

    private FloatingActionButton fabDerslerOyVer;
    private TextView textViewDerslerDersAdi,textViewDerslerOzet,textViewDersPuani;

    private Integer gelenDersID;
    private Dersler ders;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dersler_ders,container,false);

        fabDerslerOyVer = rootView.findViewById(R.id.fabDerslerOyVer);
        textViewDerslerDersAdi = rootView.findViewById(R.id.textViewDerslerDersAdi);
        textViewDerslerOzet = rootView.findViewById(R.id.textViewDerslerOzet);
        textViewDersPuani = rootView.findViewById(R.id.textViewDersPuani);

        gelenDersID = DersActivity.dersID;
        Log.e("gelenDersID",String.valueOf(gelenDersID));

        dersGirisCek(gelenDersID);

        fabDerslerOyVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View alertTasarim = getLayoutInflater().inflate(R.layout.alert_tasarim_rating_bar,null);
                RatingBar ratingBarDersPuanla = alertTasarim.findViewById(R.id.ratingBarDersPuanla);
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                builder.setTitle("DERS İÇERİK OYLAMA");
                builder.setIcon(R.drawable.yarim_yildiz);
                builder.setView(alertTasarim);

                builder.setPositiveButton("GÖNDER", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        double toplam = ders.getPuan_toplam()+ratingBarDersPuanla.getRating();
                        ders.setPuan_toplam(toplam);
                        ders.setVerilen_oy_adet((ders.getVerilen_oy_adet()+1));

                        double puan = ders.getPuan_toplam() / ders.getVerilen_oy_adet();
                        ders.setDers_puan(puan);

                        oyVer(gelenDersID,ders.getDers_puan(),ders.getPuan_toplam(),ders.getVerilen_oy_adet());

                        Toast.makeText(getActivity(),"Oy Verildi",Toast.LENGTH_SHORT).show();

                        dersGirisCek(gelenDersID);
                    }
                });

                builder.setNegativeButton("İPTAL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //boş

                    }
                });
                builder.create().show();


            }
        });

        return rootView;
    }

    public void dersGirisCek(int id){
        String url = "https://kristalekmek.com/bilisim_tez/dersler/ders_ozet_cek.php";
        StringRequest istek = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray list = jsonObject.getJSONArray("dersler_table");

                    for (int i = 0 ; i<list.length();i++){
                        JSONObject j = list.getJSONObject(i);

                        String dersAd = j.getString("ders_ad");
                        String ozet = j.getString("ders_ozet");
                        Double puan = j.getDouble("ders_puan");
                        Double puanToplami = j.getDouble("puan_toplami");
                        Integer oyAdet = j.getInt("oy_adet");

                        ders = new Dersler(dersAd);
                        ders.setDers_ozet(ozet);
                        ders.setDers_puan(puan);
                        ders.setPuan_toplam(puanToplami);
                        ders.setVerilen_oy_adet(oyAdet);
                    }

                    textViewDerslerDersAdi.setText(ders.getDers_ad());
                    textViewDerslerOzet.setText(ders.getDers_ozet());
                    textViewDersPuani.setText(String.valueOf(ders.getDers_puan())+" / 5");


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

    public void oyVer(int id,double puan,double topPuan,int adet){
        String url = "https://kristalekmek.com/bilisim_tez/dersler/oy_ver.php";
        StringRequest istek = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

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
                params.put("ders_puan",String.valueOf(puan));
                params.put("puan_toplami",String.valueOf(topPuan));
                params.put("oy_adet",String.valueOf(adet));

                return params;
            }
        };
        Volley.newRequestQueue(getActivity()).add(istek);
    }


}










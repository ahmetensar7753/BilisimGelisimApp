package com.example.bilisimgelisimapp.siniflar;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bilisimgelisimapp.AnaSayfaActivity;
import com.example.bilisimgelisimapp.SistemBilgiOnerActivity;
import com.example.bilisimgelisimapp.TestOlSonucActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class OneriGonder {

    private Integer profilPuan = 0;

    public OneriGonder() {
    }

    public Integer getProfilPuan() {
        return profilPuan;
    }

    public void setProfilPuan(Integer profilPuan) {
        this.profilPuan = profilPuan;
    }

    public void oneriGonder(String tur, String icerik, String kaynak, Context context, int kisi_id){
        String url = "https://kristalekmek.com/bilisim_tez/SBO/oneri_gonder.php";
        StringRequest istek = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("onerRspns",response);
                Toast.makeText(context,"Öneri Gönderildi.Teşekkürler",Toast.LENGTH_SHORT).show();
                kisiPuanCek(context,kisi_id);
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
                params.put("oneri_turu",tur);
                params.put("oneri_icerik",icerik);
                params.put("oneri_kaynak",kaynak);
                params.put("oneri_kisi_id",String.valueOf(kisi_id));
                return params;
            }
        };
        Volley.newRequestQueue(context).add(istek);
    }

    public void kisiPuanCek(Context context,int id){
        String url = "https://kristalekmek.com/bilisim_tez/quiz/skor_cek.php";
        StringRequest istek = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray list = jsonObject.getJSONArray("kullanici_table");

                    for (int i = 0; i<list.length();i++){
                        JSONObject j = list.getJSONObject(i);
                        profilPuan = j.getInt("profil_puani");
                    }
                    profilPuan = profilPuan+50;
                    updateSkor(id,context,profilPuan);

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
        Volley.newRequestQueue(context).add(istek);
    }

    public void updateSkor(int id,Context context,int puan){
        String url = "https://kristalekmek.com/bilisim_tez/SBO/update_oneri_skor.php";
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
                params.put("kullanici_id",String.valueOf(id));
                params.put("profil_puani",String.valueOf(puan));
                return params;
            }
        };
        Volley.newRequestQueue(context).add(istek);
    }

}

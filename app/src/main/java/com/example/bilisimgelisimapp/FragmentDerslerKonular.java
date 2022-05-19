package com.example.bilisimgelisimapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bilisimgelisimapp.adapters.KonularAdapter;
import com.example.bilisimgelisimapp.siniflar.Konular;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FragmentDerslerKonular extends Fragment {

    private Integer dersID;

    private RecyclerView rvDerslerKonular;
    private ArrayList<Konular> konularArrayList;
    private KonularAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dersler_konular,container,false);

        rvDerslerKonular = rootView.findViewById(R.id.rvDerslerKonular);

        dersID = DersActivity.dersID;
        Log.e("gelenDersID",String.valueOf(dersID));

        konulariCek(dersID);

        return rootView;
    }

    public void konulariCek(int ders_id){
        String url = "https://kristalekmek.com/bilisim_tez/dersler/konular_cek.php";
        StringRequest istek = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                konularArrayList = new ArrayList<>();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray konuListe = jsonObject.getJSONArray("konular_table");

                    for (int i = 0;i<konuListe.length();i++){
                        JSONObject j = konuListe.getJSONObject(i);

                        Integer konu_id = j.getInt("konu_id");
                        String konu_adi = j.getString("konu_adi");

                        Konular konu = new Konular(konu_adi);
                        konu.setKonu_id(konu_id);

                        konularArrayList.add(konu);
                    }

                    //adapter işlemleri
                    adapter = new KonularAdapter(getActivity(),konularArrayList);
                    rvDerslerKonular.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
                    rvDerslerKonular.setAdapter(adapter);


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
                params.put("ders_id",String.valueOf(ders_id));
                return params;
            }
        };
        Volley.newRequestQueue(getActivity()).add(istek);
    }


}

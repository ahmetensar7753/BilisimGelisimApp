package com.example.bilisimgelisimapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
import com.example.bilisimgelisimapp.adapters.PratikAlanBilgileriAdapter;
import com.example.bilisimgelisimapp.siniflar.AlanPratikBilgiler;
import com.example.bilisimgelisimapp.siniflar.Alanlar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FragmentAlanBilgiBankasi extends Fragment {

    private TextView textViewAlanDiller,textViewAlanZorlukDerecesi,textViewAlanTerimler,textViewAlanOlmazsaOlmaz;
    private RecyclerView rvAlanPratikBilgiler;

    private Alanlar alan;
    private ArrayList<AlanPratikBilgiler> vtDenGelenBilgiList;
    private Integer alanID;
    private PratikAlanBilgileriAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_alan_bilgi_bankasi,container,false);

        textViewAlanDiller = rootView.findViewById(R.id.textViewAlanDiller);
        textViewAlanZorlukDerecesi = rootView.findViewById(R.id.textViewAlanZorlukDerecesi);
        textViewAlanTerimler = rootView.findViewById(R.id.textViewAlanTerimler);
        textViewAlanOlmazsaOlmaz = rootView.findViewById(R.id.textViewAlanOlmazsaOlmaz);
        rvAlanPratikBilgiler = rootView.findViewById(R.id.rvAlanPratikBilgiler);

        alanID = AlanlarAlanActivity.gelenAlanID;

        bilgiBankasiCek(alanID);

        return rootView;
    }

    public void bilgiBankasiCek(int alanID){
        String url = "https://kristalekmek.com/bilisim_tez/alanlar/alan_pratik_cek.php";
        StringRequest istek = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                vtDenGelenBilgiList = new ArrayList<>();
                alan = new Alanlar();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray liste = jsonObject.getJSONArray("alanlar_table");

                    for (int i = 0;i<liste.length();i++){
                        JSONObject j = liste.getJSONObject(i);

                        String diller = j.getString("alan_diller");
                        Double zorluk = j.getDouble("alan_zorluk");
                        String alanTerimler = j.getString("alan_terimler");
                        String olmazasOlmaz = j.getString("alan_olmazsa_olmaz");
                        String bilgiIcerik = j.getString("bilgi_icerik");
                        String bilgiBaslik = j.getString("bilgi_baslik");

                        alan.setAlan_diller(diller);
                        alan.setAlan_zorluk(zorluk);
                        alan.setAlan_terimler(alanTerimler);
                        alan.setAlan_olmazsa_olmazlar(olmazasOlmaz);

                        AlanPratikBilgiler bilgi = new AlanPratikBilgiler(bilgiBaslik);
                        bilgi.setBilgi_icerik(bilgiIcerik);
                        vtDenGelenBilgiList.add(bilgi);
                    }
                    textViewAlanDiller.setText(alan.getAlan_diller());
                    textViewAlanZorlukDerecesi.setText(String.valueOf(alan.getAlan_zorluk()));
                    textViewAlanOlmazsaOlmaz.setText(alan.getAlan_olmazsa_olmazlar());
                    textViewAlanTerimler.setText(alan.getAlan_terimler());

                    adapter = new PratikAlanBilgileriAdapter(getActivity(),vtDenGelenBilgiList);
                    rvAlanPratikBilgiler.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
                    rvAlanPratikBilgiler.setAdapter(adapter);


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
                params.put("alan_id",String.valueOf(alanID));
                return params;
            }
        };
        Volley.newRequestQueue(getActivity()).add(istek);
    }
}

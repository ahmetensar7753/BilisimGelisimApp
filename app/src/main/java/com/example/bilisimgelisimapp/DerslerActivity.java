package com.example.bilisimgelisimapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bilisimgelisimapp.adapters.DerslerAdapter;
import com.example.bilisimgelisimapp.siniflar.Dersler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DerslerActivity extends AppCompatActivity {

    private Toolbar toolbarDersler;
    private RecyclerView rvDersler;

    private ArrayList<Dersler> vtdenGelenDersler;
    private DerslerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dersler);

        toolbarDersler = findViewById(R.id.toolbarDersler);
        rvDersler = findViewById(R.id.rvDersler);

        toolbarDersler.setTitle("");
        setSupportActionBar(toolbarDersler);

        derslerCek();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dersler_menu,menu);
        MenuItem item = menu.findItem(R.id.action_ara);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("newText1",query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("newText",newText);

                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    public void derslerCek(){
        String url = "https://kristalekmek.com/bilisim_tez/dersler/dersler_cek.php";
        StringRequest istek = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                vtdenGelenDersler = new ArrayList<>();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray dersArray = jsonObject.getJSONArray("dersler_table");

                    for (int i = 0;i<dersArray.length();i++){
                        JSONObject j = dersArray.getJSONObject(i);

                        Integer id = j.getInt("ders_id");
                        String ad = j.getString("ders_ad");

                        Dersler d = new Dersler(ad);
                        d.setDers_id(id);

                        vtdenGelenDersler.add(d);
                    }

                    adapter = new DerslerAdapter(DerslerActivity.this,vtdenGelenDersler);
                    rvDersler.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
                    rvDersler.setAdapter(adapter);

                } catch (JSONException e) {
                    Log.e("excDersler",String.valueOf(e));
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(DerslerActivity.this).add(istek);
    }





}
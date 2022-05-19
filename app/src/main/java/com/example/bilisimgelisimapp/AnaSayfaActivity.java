package com.example.bilisimgelisimapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bilisimgelisimapp.adapters.DuyuruHaberAdapter;
import com.example.bilisimgelisimapp.siniflar.DuyuruVeHaber;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AnaSayfaActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private NavigationView navigationView;
    private DrawerLayout drawer;
    private Toolbar toolbarAnaSayfa;
    private RecyclerView recyclerViewAnaSayfa;

    private ArrayList<DuyuruVeHaber> vtdenGelenDuyHabList;
    private DuyuruHaberAdapter adapter;
    private Integer kullaniciID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ana_sayfa);

        navigationView = findViewById(R.id.navigationView);
        drawer = findViewById(R.id.drawer);
        toolbarAnaSayfa = findViewById(R.id.toolbarAnaSayfa);
        recyclerViewAnaSayfa = findViewById(R.id.recyclerViewAnaSayfa);


        //giriş yapan kullanici_id alınıyor.
        String gelenKullaniciID = getIntent().getStringExtra("kullanici_id");
        kullaniciID = Integer.parseInt(gelenKullaniciID);
        Log.e("gelenKullaniciID ",gelenKullaniciID);

        duyHabCek(Integer.parseInt(gelenKullaniciID));

        toolbarAnaSayfa.setTitle("");
        setSupportActionBar(toolbarAnaSayfa);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbarAnaSayfa
                ,0,0);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);




    }

    // toolbar üzerindeki profil action tanımlanıyor-bağlanıyor.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.ana_sayfa_menu2,menu);
        return true;
    }

    // toolbar üzerinde tıklanan item alınıyor. Profil activiy'e ve siralama acctivity geçişi sağlanıyor.
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_profil){
            Intent intent = new Intent(AnaSayfaActivity.this,ProfilActivity.class);
            intent.putExtra("kullanici_id",String.valueOf(kullaniciID));
            startActivity(intent);
        }
        if (item.getItemId() == R.id.action_siralama){
            Intent intent = new Intent(AnaSayfaActivity.this,SiralamaActivity.class);
            intent.putExtra("kullanici_id",String.valueOf(kullaniciID));
            startActivity(intent);
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_item_dersler:
                startActivity(new Intent(AnaSayfaActivity.this,DerslerActivity.class));
                break;
            case R.id.nav_item_alanlar:
                startActivity(new Intent(AnaSayfaActivity.this,AlanlarActivity.class));
                break;
            case R.id.nav_item_test_ol:
                Intent intent = new Intent(AnaSayfaActivity.this,TestOlActivity.class);
                intent.putExtra("kullanici_id",String.valueOf(kullaniciID));
                startActivity(intent);
                break;
            case R.id.nav_item_sistem_bilgi_oner:
                Intent intent2 = new Intent(AnaSayfaActivity.this,SistemBilgiOnerActivity.class);
                intent2.putExtra("kullanici_id",String.valueOf(kullaniciID));
                startActivity(intent2);
                break;
            default:
                return true;
        }

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            startActivity(new Intent(AnaSayfaActivity.this,MainActivity.class));
        }
    }

    public void duyHabCek(int kullaniciID){
        String url = "https://kristalekmek.com/bilisim_tez/duyuru_haberler/duy_hab_cek.php";
        StringRequest istek = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                vtdenGelenDuyHabList = new ArrayList<>();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray duyHabList = jsonObject.getJSONArray("duyuru_haber_table");
                    // bu döngü içerisinde vtden tek tek veriler çekilip her çekilişte nesne ayağa kaldırılıp globelde tanımlanan arrayListe ekleniyor.
                    // ardından adapter ayağa kadlırılıp cons. fonksiyona liste set ediliyor ve oluşan adapter rv'ye set ediliyor (try bloğu sonunda).
                    for (int i = 0;i<duyHabList.length();i++){
                        JSONObject k = duyHabList.getJSONObject(i);

                        int id = k.getInt("duy_hab_id");
                        String baslik = k.getString("baslik");
                        String ozet = k.getString("duy_hab_ozet");
                        String resim_ad = k.getString("duy_hab_resim_ad");
                        int begeni_say = k.getInt("duy_hab_begeni_say");

                        DuyuruVeHaber duyHab = new DuyuruVeHaber(baslik,ozet,resim_ad);
                        duyHab.setDuy_hab_id(id);
                        duyHab.setDuy_hab_begeni_say(begeni_say);

                        vtdenGelenDuyHabList.add(duyHab);
                    }
                    adapter = new DuyuruHaberAdapter(AnaSayfaActivity.this,vtdenGelenDuyHabList,kullaniciID);
                    recyclerViewAnaSayfa.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
                    recyclerViewAnaSayfa.setAdapter(adapter);

                } catch (JSONException e) {
                    Log.e("exepcion",String.valueOf(e));
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(AnaSayfaActivity.this).add(istek);
    }



}
package com.example.bilisimgelisimapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bilisimgelisimapp.R;
import com.example.bilisimgelisimapp.siniflar.Yorumlar;

import java.util.ArrayList;

public class DuyuruYorumlarAdapter extends RecyclerView.Adapter<DuyuruYorumlarAdapter.CardViewNesneTutucuYorum>{

    private Context mContext;
    private ArrayList<Yorumlar> yorumListesi = new ArrayList<>();


    public DuyuruYorumlarAdapter(Context mContext, ArrayList<Yorumlar> yorumListesi) {
        this.mContext = mContext;
        this.yorumListesi = yorumListesi;
    }

    public class CardViewNesneTutucuYorum extends RecyclerView.ViewHolder{
        TextView textViewCardKullaniciYorumAd,textViewCardYorumBaslik,textViewCardKullaniciYorumu;
        public CardViewNesneTutucuYorum(@NonNull View itemView) {
            super(itemView);
            textViewCardKullaniciYorumAd = itemView.findViewById(R.id.textViewCardKullaniciYorumAd);
            textViewCardYorumBaslik = itemView.findViewById(R.id.textViewCardYorumBaslik);
            textViewCardKullaniciYorumu = itemView.findViewById(R.id.textViewCardKullaniciYorumu);
        }
    }

    @NonNull
    @Override
    public CardViewNesneTutucuYorum onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_tasarim_duyuru_haber_yorumlari,parent,false);
        return new CardViewNesneTutucuYorum(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewNesneTutucuYorum holder, int position) {
        Yorumlar yorum = yorumListesi.get(position);

        holder.textViewCardKullaniciYorumAd.setText(yorum.getYorumSahibiAd());
        holder.textViewCardYorumBaslik.setText(yorum.getYorum_baslik());
        holder.textViewCardKullaniciYorumu.setText(yorum.getYorum_icerik());

    }

    @Override
    public int getItemCount() {
        return yorumListesi.size();
    }




}

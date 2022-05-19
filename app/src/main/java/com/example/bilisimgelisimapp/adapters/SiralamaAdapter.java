package com.example.bilisimgelisimapp.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bilisimgelisimapp.R;
import com.example.bilisimgelisimapp.siniflar.Kullanicilar;

import java.util.ArrayList;

public class SiralamaAdapter extends RecyclerView.Adapter<SiralamaAdapter.CardViewNesneTutucuSiralama>{

    private Context mContext;
    private ArrayList<Kullanicilar> disaridanGelenListe;

    public SiralamaAdapter(Context mContext, ArrayList<Kullanicilar> disaridanGelenListe) {
        this.mContext = mContext;
        this.disaridanGelenListe = disaridanGelenListe;
    }

    public class CardViewNesneTutucuSiralama extends RecyclerView.ViewHolder{
        private TextView textViewSiralamaCardAd,textViewCardSiralamaSira,textViewSiralamaCardPuan;
        public CardViewNesneTutucuSiralama(@NonNull View itemView) {
            super(itemView);
            textViewSiralamaCardAd = itemView.findViewById(R.id.textViewSiralamaCardAd);
            textViewCardSiralamaSira = itemView.findViewById(R.id.textViewCardSiralamaSira);
            textViewSiralamaCardPuan = itemView.findViewById(R.id.textViewSiralamaCardPuan);
        }
    }

    @NonNull
    @Override
    public CardViewNesneTutucuSiralama onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_tasarim_siralama,parent,false);
        return new CardViewNesneTutucuSiralama(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewNesneTutucuSiralama holder, int position) {
        Kullanicilar kullanici = disaridanGelenListe.get(position);

        holder.textViewCardSiralamaSira.setText(String.valueOf(position+1+"."));
        holder.textViewSiralamaCardAd.setText(kullanici.getKullanici_ad()+" "+kullanici.getKullanici_soyad());
        holder.textViewSiralamaCardPuan.setText(String.valueOf(kullanici.getProfil_puani()));

    }

    @Override
    public int getItemCount() {
        return disaridanGelenListe.size();
    }



}

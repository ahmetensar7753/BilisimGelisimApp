package com.example.bilisimgelisimapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bilisimgelisimapp.DuyuruHaberDetayActivity;
import com.example.bilisimgelisimapp.R;
import com.example.bilisimgelisimapp.siniflar.DuyuruVeHaber;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DuyuruHaberAdapter extends RecyclerView.Adapter<DuyuruHaberAdapter.CardViewNesneTutucuHaber>{

    private Context mContext;
    private ArrayList<DuyuruVeHaber> duyuruHaberList = new ArrayList<>();
    private int kullaniciID;

    public DuyuruHaberAdapter(Context mContext, ArrayList<DuyuruVeHaber> duyuruHaberList,int kullaniciID) {
        this.mContext = mContext;
        this.duyuruHaberList = duyuruHaberList;
        this.kullaniciID = kullaniciID;
    }

    public class CardViewNesneTutucuHaber extends RecyclerView.ViewHolder{
        CardView cardViewDuyuruHaber;
        ImageView imageViewCardDuyuruHaber;
        TextView textViewCardDuyuruHaberBaslik,textViewCardDuyuruHaberOzet,textViewCardBegeniSayisi;
        public CardViewNesneTutucuHaber(@NonNull View itemView) {
            super(itemView);

            cardViewDuyuruHaber = itemView.findViewById(R.id.cardViewDuyuruHaber);
            imageViewCardDuyuruHaber = itemView.findViewById(R.id.imageViewCardDuyuruHaber);
            textViewCardDuyuruHaberBaslik = itemView.findViewById(R.id.textViewCardDuyuruHaberBaslik);
            textViewCardDuyuruHaberOzet = itemView.findViewById(R.id.textViewCardDuyuruHaberOzet);
            textViewCardBegeniSayisi = itemView.findViewById(R.id.textViewCardBegeniSayisi);
        }
    }

    @NonNull
    @Override
    public CardViewNesneTutucuHaber onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_tasarim_ana_sayfa,parent,false);
        return new CardViewNesneTutucuHaber(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewNesneTutucuHaber holder, int position) {
        final DuyuruVeHaber duyHab = duyuruHaberList.get(position);

        String url = "https://kristalekmek.com/bilisim_tez/duyuru_haberler/duyuru_haber_resimler/"+duyHab.getDuy_hab_resim_ad()+".jpg";
        Picasso.get().load(url).into(holder.imageViewCardDuyuruHaber);


        holder.textViewCardDuyuruHaberBaslik.setText(duyHab.getBaslik());
        holder.textViewCardDuyuruHaberOzet.setText(duyHab.getDuy_hab_ozet());
        holder.textViewCardBegeniSayisi.setText(String.valueOf(duyHab.getDuy_hab_begeni_say()));

        holder.cardViewDuyuruHaber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DuyuruHaberDetayActivity.class);
                intent.putExtra("haber_id",String.valueOf(duyHab.getDuy_hab_id()));
                intent.putExtra("kullanici_id",String.valueOf(kullaniciID));
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return duyuruHaberList.size();
    }





}

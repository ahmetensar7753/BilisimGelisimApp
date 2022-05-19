package com.example.bilisimgelisimapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bilisimgelisimapp.DersKonuDetayActivity;
import com.example.bilisimgelisimapp.R;
import com.example.bilisimgelisimapp.siniflar.Konular;

import java.util.ArrayList;

public class KonularAdapter extends RecyclerView.Adapter<KonularAdapter.CardViewNesneTutucuKonu>{

    private Context mContext;
    private ArrayList<Konular> disaridanGelenListe = new ArrayList<>();


    public KonularAdapter(Context mContext, ArrayList<Konular> disaridanGelenListe) {
        this.mContext = mContext;
        this.disaridanGelenListe = disaridanGelenListe;

    }

    public class CardViewNesneTutucuKonu extends RecyclerView.ViewHolder{
        CardView cardViewDerslerKonu;
        TextView textVievCardDerslerKonu;
        public CardViewNesneTutucuKonu(@NonNull View itemView) {
            super(itemView);
            cardViewDerslerKonu = itemView.findViewById(R.id.cardViewDerslerKonu);
            textVievCardDerslerKonu = itemView.findViewById(R.id.textVievCardDerslerKonu);
        }
    }

    @NonNull
    @Override
    public CardViewNesneTutucuKonu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_tasarim_dersler_konular_konu,parent,false);
        return new CardViewNesneTutucuKonu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewNesneTutucuKonu holder, int position) {
        Konular konu = disaridanGelenListe.get(position);

        holder.textVievCardDerslerKonu.setText(konu.getKonu_ad());
        holder.cardViewDerslerKonu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, DersKonuDetayActivity.class);
                intent.putExtra("konu_id",String.valueOf(konu.getKonu_id()));
                mContext.startActivity(intent);

            }
        });

        holder.textVievCardDerslerKonu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.cardViewDerslerKonu.callOnClick();
            }
        });

    }

    @Override
    public int getItemCount() {
        return disaridanGelenListe.size();
    }




}

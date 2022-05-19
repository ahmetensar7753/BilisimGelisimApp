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

import com.example.bilisimgelisimapp.AlanlarAlanActivity;
import com.example.bilisimgelisimapp.R;
import com.example.bilisimgelisimapp.siniflar.Alanlar;

import java.util.ArrayList;

public class AlanlarAdapter extends RecyclerView.Adapter<AlanlarAdapter.CardViewNesneTutucuAlan>{

    private Context mContext;
    private ArrayList<Alanlar> disaridanGelenAlanList = new ArrayList<>();

    public AlanlarAdapter(Context mContext, ArrayList<Alanlar> disaridanGelenAlanList) {
        this.mContext = mContext;
        this.disaridanGelenAlanList = disaridanGelenAlanList;
    }

    public class CardViewNesneTutucuAlan extends RecyclerView.ViewHolder{
        CardView cardViewAlan;
        TextView textViewCardAlanAdi;
        public CardViewNesneTutucuAlan(@NonNull View itemView) {
            super(itemView);
            cardViewAlan = itemView.findViewById(R.id.cardViewAlan);
            textViewCardAlanAdi = itemView.findViewById(R.id.textViewCardAlanAdi);
        }
    }

    @NonNull
    @Override
    public CardViewNesneTutucuAlan onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_tasarim_alan,parent,false);
        return new CardViewNesneTutucuAlan(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewNesneTutucuAlan holder, int position) {
        Alanlar alan = disaridanGelenAlanList.get(position);

        holder.textViewCardAlanAdi.setText(alan.getAlan_ad());
        holder.cardViewAlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, AlanlarAlanActivity.class);
                intent.putExtra("alan_id",String.valueOf(alan.getAlan_id()));
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return disaridanGelenAlanList.size();
    }



}

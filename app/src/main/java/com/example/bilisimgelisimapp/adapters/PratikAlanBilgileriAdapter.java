package com.example.bilisimgelisimapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bilisimgelisimapp.R;
import com.example.bilisimgelisimapp.siniflar.AlanPratikBilgiler;

import java.util.ArrayList;

public class PratikAlanBilgileriAdapter extends RecyclerView.Adapter<PratikAlanBilgileriAdapter.CardViewNesneTutucuPratikBilgi>{

    private Context mContext;
    private ArrayList<AlanPratikBilgiler> disaridanGelenList = new ArrayList<>();

    public PratikAlanBilgileriAdapter(Context mContext, ArrayList<AlanPratikBilgiler> disaridanGelenList) {
        this.mContext = mContext;
        this.disaridanGelenList = disaridanGelenList;
    }

    public class CardViewNesneTutucuPratikBilgi extends RecyclerView.ViewHolder{
        TextView textViewAlanPratikBilgiBaslik,textViewAlanPratikBilgiIcerik;
        public CardViewNesneTutucuPratikBilgi(@NonNull View itemView) {
            super(itemView);
            textViewAlanPratikBilgiBaslik = itemView.findViewById(R.id.textViewAlanPratikBilgiBaslik);
            textViewAlanPratikBilgiIcerik = itemView.findViewById(R.id.textViewAlanPratikBilgiIcerik);
        }
    }

    @NonNull
    @Override
    public CardViewNesneTutucuPratikBilgi onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_tasarim_alan_pratik_bilgiler,parent,false);
        return new CardViewNesneTutucuPratikBilgi(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewNesneTutucuPratikBilgi holder, int position) {
        AlanPratikBilgiler bilgi = disaridanGelenList.get(position);

        holder.textViewAlanPratikBilgiBaslik.setText(bilgi.getBilgi_baslik());
        holder.textViewAlanPratikBilgiIcerik.setText(bilgi.getBilgi_icerik());

    }

    @Override
    public int getItemCount() {
        return disaridanGelenList.size();
    }



}

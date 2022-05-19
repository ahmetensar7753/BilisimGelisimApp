package com.example.bilisimgelisimapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bilisimgelisimapp.DersActivity;
import com.example.bilisimgelisimapp.R;
import com.example.bilisimgelisimapp.siniflar.Dersler;

import java.util.ArrayList;
import java.util.Locale;

public class DerslerAdapter extends RecyclerView.Adapter<DerslerAdapter.CardViewNesneTutucuDersler>{

    private Context mContext;
    private ArrayList<Dersler> disaridanGelenDersler = new ArrayList<>();
    private ArrayList<Dersler> fullList;

    public DerslerAdapter(Context mContext, ArrayList<Dersler> disaridanGelenDersler) {
        this.mContext = mContext;
        this.disaridanGelenDersler = disaridanGelenDersler;
        fullList = new ArrayList<>(disaridanGelenDersler);
    }

    public class CardViewNesneTutucuDersler extends RecyclerView.ViewHolder{
        CardView cardViewDersler;
        TextView textViewCardDersAdi;
        public CardViewNesneTutucuDersler(@NonNull View itemView) {
            super(itemView);
            cardViewDersler = itemView.findViewById(R.id.cardViewDersler);
            textViewCardDersAdi = itemView.findViewById(R.id.textViewCardDersAdi);
        }
    }

    @NonNull
    @Override
    public CardViewNesneTutucuDersler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_tasarim_dersler,parent,false);
        return new CardViewNesneTutucuDersler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewNesneTutucuDersler holder, int position) {
        final Dersler ders = disaridanGelenDersler.get(position);

        holder.textViewCardDersAdi.setText(ders.getDers_ad());
        holder.cardViewDersler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, DersActivity.class);
                intent.putExtra("ders_id",String.valueOf(ders.getDers_id()));
                mContext.startActivity(intent);

            }
        });

        holder.textViewCardDersAdi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.cardViewDersler.callOnClick();
            }
        });

    }

    @Override
    public int getItemCount() {
        return disaridanGelenDersler.size();
    }

    public Filter getFilter(){return Searched_Filter;}

    private Filter Searched_Filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Dersler> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0){
                filteredList.addAll(fullList);
            }else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Dersler d:fullList){
                    if (d.getDers_ad().toLowerCase().contains(filterPattern)){
                        filteredList.add(d);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            disaridanGelenDersler.clear();
            disaridanGelenDersler.addAll((ArrayList)results.values);
            notifyDataSetChanged();
        }
    };



}


















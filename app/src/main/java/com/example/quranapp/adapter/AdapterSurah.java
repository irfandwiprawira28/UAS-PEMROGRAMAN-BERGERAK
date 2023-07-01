package com.example.quranapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quranapp.R;
import com.example.quranapp.activity.AudioActivity;
import com.example.quranapp.activity.DetailActivity;
import com.example.quranapp.model.ChaptersItem;

import java.util.ArrayList;

public class AdapterSurah extends RecyclerView.Adapter<AdapterSurah.ViewHolder>{

    private ArrayList<ChaptersItem> list;
    private Context context;


    public AdapterSurah(ArrayList<ChaptersItem> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_surah_audio, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChaptersItem item = list.get(position);
        holder.tvItem.setText(item.getNameArabic()+" "+item.getNameSimple());
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("nomor",String.valueOf(item.getId()));
            intent.putExtra("surah",String.valueOf(item.getNameSimple()));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });
        holder.btnPlay.setOnClickListener(view ->{
            Intent intent = new Intent(context, AudioActivity.class);
            intent.putExtra("surah",item.getNameSimple());
            intent.putExtra("url",item.getId());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvItem;
        private final Button btnPlay;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(R.id.tvItemArab);
            btnPlay = itemView.findViewById(R.id.idBtnPlay);
        }
    }

}

package com.example.quranapp.adapter;

import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quranapp.R;
import com.example.quranapp.model.TranslationsItem;
import com.example.quranapp.model.VersesItem;

import java.util.ArrayList;

public class AdapterAyat extends RecyclerView.Adapter<AdapterAyat.ViewHolder>{

    private ArrayList<VersesItem> list;
    private ArrayList<TranslationsItem> listTranslate;
    private Context context;

    public AdapterAyat(ArrayList<VersesItem> list, ArrayList<TranslationsItem> listTranslate, Context context) {
        this.list = list;
        this.listTranslate = listTranslate;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_ayat, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        VersesItem item = list.get(position);
        TranslationsItem itemTranslate = listTranslate.get(position);
        holder.tvArabic.setText(item.getTextUthmani());
        holder.tvNo.setText(String.valueOf(item.getId()));
        holder.tvIndo.setText(Html.fromHtml(itemTranslate.getText(), Html.FROM_HTML_MODE_COMPACT));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvArabic,tvIndo,tvArti,tvNo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNo = itemView.findViewById(R.id.tvNomor);
            tvArabic = itemView.findViewById(R.id.tvArabic);
            tvIndo = itemView.findViewById(R.id.tvIndo);
            tvArti = itemView.findViewById(R.id.tvArti);
        }
    }
}

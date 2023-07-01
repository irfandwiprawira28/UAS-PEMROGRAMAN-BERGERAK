package com.example.quranapp.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quranapp.adapter.AdapterAyat;
import com.example.quranapp.api.ApiConfig;
import com.example.quranapp.databinding.ActivityDetailBinding;
import com.example.quranapp.model.ResponseAyat;
import com.example.quranapp.model.ResponseAyatTranslate;
import com.example.quranapp.model.TranslationsItem;
import com.example.quranapp.model.VersesItem;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    private ActivityDetailBinding binding;

    private ArrayList<VersesItem> listAyat;
    private ArrayList<TranslationsItem> listAyatTranslate;
    private AdapterAyat adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setTitle(getIntent().getStringExtra("surah"));
        listAyat = new ArrayList<>();
        listAyatTranslate = new ArrayList<>();
        getTranslate();
    }

    private void getDetail() {
        Call<ResponseAyat> client = ApiConfig.getApiService().getAllAyahBySurah(getIntent().getStringExtra("nomor"));
        client.enqueue(new Callback<ResponseAyat>() {
            @Override
            public void onResponse(Call<ResponseAyat> call, Response<ResponseAyat> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        listAyat.addAll(response.body().getVerses());
                        adapter = new AdapterAyat(listAyat,listAyatTranslate,getApplicationContext());
                        binding.rvAyat.setAdapter(adapter);
                    }
                }else {
                    if (response.body() != null){
                        Log.i("cek", "onFailure: "+response.message());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseAyat> call, Throwable t) {
                Log.i("cek", "onFailure: "+t.getMessage());
            }
        });
    }

    private void getTranslate() {
        Call<ResponseAyatTranslate> client =
                ApiConfig.getApiService().getAllTranslateBySurah(getIntent().getStringExtra("nomor"));
        client.enqueue(new Callback<ResponseAyatTranslate>() {
            @Override
            public void onResponse(Call<ResponseAyatTranslate> call, Response<ResponseAyatTranslate> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        listAyatTranslate.addAll(response.body().getTranslations());
                        getDetail();
                    }
                }else {
                    if (response.body() != null){
                        Log.i("cek", "onFailure: "+response.message());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseAyatTranslate> call, Throwable t) {
                Log.i("cek", "onFailure: "+t.getMessage());
            }
        });
    }
}
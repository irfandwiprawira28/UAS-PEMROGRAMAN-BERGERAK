package com.example.quranapp.api;

import com.example.quranapp.model.ResponseAudio;
import com.example.quranapp.model.ResponseAyat;
import com.example.quranapp.model.ResponseAyatTranslate;
import com.example.quranapp.model.ResponseSurah;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("chapters?language=id")
    Call<ResponseSurah> getAllSurah();

    @GET("quran/verses/uthmani")
    Call<ResponseAyat> getAllAyahBySurah(@Query("chapter_number") String nomor);

    @GET("quran/translations/33")
    Call<ResponseAyatTranslate> getAllTranslateBySurah(@Query("chapter_number") String nomor);

    @GET("chapter_recitations/33")
    Call<ResponseAudio> getAudioBySurah(@Query("chapter_number") String nomor);

}

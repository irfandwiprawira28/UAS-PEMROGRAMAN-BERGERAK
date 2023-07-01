package com.example.quranapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.example.quranapp.R;
import com.example.quranapp.adapter.AdapterAyat;
import com.example.quranapp.api.ApiConfig;
import com.example.quranapp.databinding.ActivityAudioBinding;
import com.example.quranapp.model.ResponseAudio;
import com.example.quranapp.model.ResponseAyat;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AudioActivity extends AppCompatActivity {

    private ActivityAudioBinding binding;
    MediaPlayer mediaPlayer;
    private Boolean isPlaying = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAudioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setTitle(getIntent().getStringExtra("surah"));
        Log.i("TAG", "onCreate: "+String.valueOf(getIntent().getIntExtra("url",0)));
        cekStatus(binding.btnPlay,binding.btnPause,isPlaying);
        binding.btnPlay.setOnClickListener(view ->{
            isPlaying = true;
            getUrl(String.valueOf(getIntent().getIntExtra("url",0)));
            cekStatus(binding.btnPlay,binding.btnPause,isPlaying);
        });
        binding.btnPause.setOnClickListener(view ->{
            isPlaying = false;
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
                mediaPlayer.reset();
                mediaPlayer.release();
                Toast.makeText(AudioActivity.this, "Pause", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(AudioActivity.this, "Not Play", Toast.LENGTH_SHORT).show();
            }
            cekStatus(binding.btnPlay,binding.btnPause,isPlaying);
        });

    }

    private void cekStatus(Button play,Button pause, boolean isPlaying) {
        if (isPlaying) {
            pause.setClickable(true);
            play.setClickable(false);
        } else {
            pause.setClickable(false);
            play.setClickable(true);
        }
    }

    private void playAudio(String url, String name) {

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioAttributes(new AudioAttributes.Builder()
                .setFlags(AudioAttributes.FLAG_AUDIBILITY_ENFORCED)
                .setLegacyStreamType(AudioManager.STREAM_MUSIC)
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build());
        try {
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepare();
            mediaPlayer.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
        Toast.makeText(this, "Audio started playing "+name, Toast.LENGTH_SHORT).show();
    }

    private void getUrl(String  nomor){
        Call<ResponseAudio> client =
                ApiConfig.getApiService()
                        .getAudioBySurah(nomor);
        client.enqueue(new Callback<ResponseAudio>() {
            @Override
            public void onResponse(Call<ResponseAudio> call, Response<ResponseAudio> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        playAudio(
                                response.body().getAudioFiles().get(0).getAudioUrl(),
                                getIntent().getStringExtra("surah")
                        );
                    }
                }else {
                    if (response.body() != null){
                        Log.i("cek", "onFailure: "+response.message());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseAudio> call, Throwable t) {
                Log.i("cek", "onFailure: "+t.getMessage());
            }
        });
    }
}
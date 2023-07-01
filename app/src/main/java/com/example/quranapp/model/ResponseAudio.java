package com.example.quranapp.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseAudio{

	@SerializedName("audio_files")
	private List<AudioFilesItem> audioFiles;

	public List<AudioFilesItem> getAudioFiles(){
		return audioFiles;
	}
}
package com.example.quranapp.model;

import com.google.gson.annotations.SerializedName;

public class VersesItem{

	@SerializedName("verse_key")
	private String verseKey;

	@SerializedName("text_uthmani")
	private String textUthmani;

	@SerializedName("id")
	private int id;

	public String getVerseKey(){
		return verseKey;
	}

	public String getTextUthmani(){
		return textUthmani;
	}

	public int getId(){
		return id;
	}
}
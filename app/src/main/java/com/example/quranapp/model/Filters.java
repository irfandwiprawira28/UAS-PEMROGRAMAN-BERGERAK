package com.example.quranapp.model;

import com.google.gson.annotations.SerializedName;

public class Filters{

	@SerializedName("chapter_number")
	private String chapterNumber;

	@SerializedName("resource_id")
	private int resourceId;

	public String getChapterNumber(){
		return chapterNumber;
	}

	public int getResourceId(){
		return resourceId;
	}
}
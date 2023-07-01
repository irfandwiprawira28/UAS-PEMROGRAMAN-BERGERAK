package com.example.quranapp.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseSurah{

	@SerializedName("chapters")
	private List<ChaptersItem> chapters;

	public List<ChaptersItem> getChapters(){
		return chapters;
	}
}
package com.example.quranapp.model;

import com.google.gson.annotations.SerializedName;

public class Meta{

	@SerializedName("author_name")
	private String authorName;

	@SerializedName("filters")
	private Filters filters;

	@SerializedName("translation_name")
	private String translationName;

	public String getAuthorName(){
		return authorName;
	}

	public Filters getFilters(){
		return filters;
	}

	public String getTranslationName(){
		return translationName;
	}
}
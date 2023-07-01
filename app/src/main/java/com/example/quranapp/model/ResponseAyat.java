package com.example.quranapp.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseAyat{

	@SerializedName("meta")
	private Meta meta;

	@SerializedName("verses")
	private List<VersesItem> verses;

	public Meta getMeta(){
		return meta;
	}

	public List<VersesItem> getVerses(){
		return verses;
	}
}
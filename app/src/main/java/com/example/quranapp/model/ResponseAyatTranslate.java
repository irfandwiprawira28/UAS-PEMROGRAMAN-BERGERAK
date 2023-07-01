package com.example.quranapp.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseAyatTranslate{

	@SerializedName("translations")
	private List<TranslationsItem> translations;

	@SerializedName("meta")
	private Meta meta;

	public List<TranslationsItem> getTranslations(){
		return translations;
	}

	public Meta getMeta(){
		return meta;
	}
}
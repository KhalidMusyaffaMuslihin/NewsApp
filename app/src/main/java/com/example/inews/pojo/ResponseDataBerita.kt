package com.example.inews.pojo

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

//@Generated("com.robohorse.robopojogenerator")
@Parcelize
data class ResponseDataBerita(

	@field:SerializedName("totalResults")
	val totalResults: Int? = null,

	@field:SerializedName("articles")
	val articles: List<ArticlesItem>? = null,

	@field:SerializedName("status")
	val status: String? = null
):Parcelable
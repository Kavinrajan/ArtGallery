package com.kavin.artgallery.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PhotoUrlsModel(
    @Expose @SerializedName("raw") var raw: String? = null,
    @Expose @SerializedName("full") var full: String? = null,
    @Expose @SerializedName("regular") var regular: String? = null,
    @Expose @SerializedName("small") var small: String? = null,
    @Expose @SerializedName("thumb") var thumb: String? = null,
    @Expose @SerializedName("small_s3") var smallS3: String? = null
) : Parcelable

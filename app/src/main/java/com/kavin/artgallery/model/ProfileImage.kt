package com.kavin.artgallery.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProfileImage(
    @Expose @SerializedName("small") var small: String? = null,
    @Expose @SerializedName("medium") var medium: String? = null,
    @Expose @SerializedName("large") var large: String? = null
) : Parcelable

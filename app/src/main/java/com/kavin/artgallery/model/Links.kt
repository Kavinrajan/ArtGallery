package com.kavin.artgallery.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Links(
    @Expose @SerializedName("self") var self: String? = null,
    @Expose @SerializedName("html") var html: String? = null,
    @Expose @SerializedName("download") var download: String? = null,
    @Expose @SerializedName("download_location") var downloadLocation: String? = null
) : Parcelable

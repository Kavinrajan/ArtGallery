package com.kavin.artgallery.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Sponsorship(
    @Expose @SerializedName("impression_urls") var impressionUrls: ArrayList<String>? = arrayListOf(),
    @Expose @SerializedName("tagline") var tagline: String? = null,
    @Expose @SerializedName("tagline_url") var taglineUrl: String? = null,
    @Expose @SerializedName("sponsor") var sponsor: Sponsor? = Sponsor()
) : Parcelable

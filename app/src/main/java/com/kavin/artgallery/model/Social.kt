package com.kavin.artgallery.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Social(
    @Expose @SerializedName("instagram_username") var instagramUsername: String? = null,
    @Expose @SerializedName("portfolio_url") var portfolioUrl: String? = null,
    @Expose @SerializedName("twitter_username") var twitterUsername: String? = null,
    @Expose @SerializedName("paypal_email") var paypalEmail: String? = null
) : Parcelable

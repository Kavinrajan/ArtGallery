package com.kavin.artgallery.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Social (
    @Expose var instagramUsername : String? = null,
    @Expose var portfolioUrl      : String? = null,
    @Expose var twitterUsername   : String? = null,
    @Expose var paypalEmail       : String? = null
): Parcelable

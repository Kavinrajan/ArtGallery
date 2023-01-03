package com.kavin.artgallery.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Sponsorship(
    @Expose var impressionUrls : ArrayList<String> = arrayListOf(),
    @Expose var tagline        : String?           = null,
    @Expose var taglineUrl     : String?           = null,
    @Expose var sponsor        : Sponsor?          = Sponsor()
): Parcelable

package com.kavin.artgallery.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Links(
    @Expose var self             : String? = null,
    @Expose var html             : String? = null,
    @Expose var download         : String? = null,
    @Expose var downloadLocation : String? = null
) : Parcelable

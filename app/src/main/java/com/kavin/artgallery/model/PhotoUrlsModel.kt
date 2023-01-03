package com.kavin.artgallery.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PhotoUrlsModel (
    @Expose var raw     : String? = null,
    @Expose var full    : String? = null,
    @Expose var regular : String? = null,
    @Expose var small   : String? = null,
    @Expose var thumb   : String? = null,
    @Expose var smallS3 : String? = null
): Parcelable

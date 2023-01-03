package com.kavin.artgallery.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProfileImage(
    @Expose var small  : String? = null,
    @Expose var medium : String? = null,
    @Expose var large  : String? = null
): Parcelable

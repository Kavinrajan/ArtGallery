package com.kavin.artgallery.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TagModel(
    val tagName: String,
    val imageUrl: String
): Parcelable

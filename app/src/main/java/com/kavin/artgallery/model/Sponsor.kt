package com.kavin.artgallery.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Sponsor(
    @Expose var id                : String?       = null,
    @Expose var updatedAt         : String?       = null,
    @Expose var username          : String?       = null,
    @Expose var name              : String?       = null,
    @Expose var firstName         : String?       = null,
    @Expose var lastName          : String?       = null,
    @Expose var twitterUsername   : String?       = null,
    @Expose var portfolioUrl      : String?       = null,
    @Expose var bio               : String?       = null,
    @Expose var location          : String?       = null,
    @Expose var links             : Links?        = Links(),
    @Expose var profileImage      : ProfileImage? = ProfileImage(),
    @Expose var instagramUsername : String?       = null,
    @Expose var totalCollections  : Int?          = null,
    @Expose var totalLikes        : Int?          = null,
    @Expose var totalPhotos       : Int?          = null,
    @Expose var acceptedTos       : Boolean?      = null,
    @Expose var forHire           : Boolean?      = null,
    @Expose var social            : Social?       = Social()
): Parcelable

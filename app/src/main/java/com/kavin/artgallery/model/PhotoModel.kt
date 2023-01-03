package com.kavin.artgallery.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PhotoModel (
    @Expose val id                     : String,
    @Expose var created_at              : String,
    @Expose var updatedAt              : String?           = null,
    @Expose var promotedAt             : String?           = null,
    @Expose var width                  : Int?              = null,
    @Expose var height                 : Int?              = null,
    @Expose var color                  : String?           = null,
    @Expose var blurHash               : String?           = null,
    @Expose var description            : String?           = null,
    @Expose var altDescription         : String?           = null,
    @Expose var urls                   : PhotoUrlsModel?   = PhotoUrlsModel(),
    @Expose var links                  : Links?            = Links(),
    @Expose var likes                  : Int?              = null,
    @Expose var likedByUser            : Boolean?          = null,
    @Expose var currentUserCollections : ArrayList<String> = arrayListOf(),
    @Expose var sponsorship            : Sponsorship?      = Sponsorship(),
//    @Expose var topicSubmissions       : TopicSubmissions? = TopicSubmissions(),
    @Expose var user                   : UserModel?             = UserModel(),
    @Expose var tags                   : ArrayList<String> = arrayListOf()
): Parcelable
package com.kavin.artgallery.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class PhotoModel(
    @Expose @SerializedName("id") val id: String? = null,
    @Expose @SerializedName("created_at") var created_at: String? = null,
    @Expose @SerializedName("updated_at") var updatedAt: String? = null,
    @Expose @SerializedName("promoted_at") var promotedAt: String? = null,
    @Expose @SerializedName("width") var width: Int? = null,
    @Expose @SerializedName("height") var height: Int? = null,
    @Expose @SerializedName("color") var color: String? = null,
    @Expose @SerializedName("blur_hash") var blurHash: String? = null,
    @Expose @SerializedName("description") var description: String? = null,
    @Expose @SerializedName("alt_description") var altDescription: String? = null,
    @Expose @SerializedName("urls") var urls: PhotoUrlsModel? = PhotoUrlsModel(),
    @Expose @SerializedName("links") var links: Links? = Links(),
    @Expose @SerializedName("likes") var likes: Int? = null,
    @Expose @SerializedName("liked_by_user") var likedByUser: Boolean? = null,
    @Expose @SerializedName("current_user_collections") var currentUserCollections: ArrayList<@RawValue Any>? = arrayListOf(),
    @Expose @SerializedName("sponsorship") var sponsorship: Sponsorship? = Sponsorship(),
    @Expose @SerializedName("user") var user: UserModel? = UserModel(),
    @Expose @SerializedName("tags") var tags: ArrayList<@RawValue Any>? = arrayListOf(),
) : Parcelable

package com.kavin.artgallery.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel(
    @Expose @SerializedName("id") var id: String? = null,
    @Expose @SerializedName("updated_at") var updatedAt: String? = null,
    @Expose @SerializedName("username") var username: String? = null,
    @Expose @SerializedName("name") var name: String? = null,
    @Expose @SerializedName("first_name") var firstName: String? = null,
    @Expose @SerializedName("last_name") var lastName: String? = null,
    @Expose @SerializedName("twitter_username") var twitterUsername: String? = null,
    @Expose @SerializedName("portfolio_url") var portfolioUrl: String? = null,
    @Expose @SerializedName("bio") var bio: String? = null,
    @Expose @SerializedName("location") var location: String? = null,
    @Expose @SerializedName("links") var links: Links? = Links(),
    @Expose @SerializedName("profile_image") var profileImage: ProfileImage? = ProfileImage(),
    @Expose @SerializedName("instagram_username") var instagramUsername: String? = null,
    @Expose @SerializedName("total_collections") var totalCollections: Int? = null,
    @Expose @SerializedName("total_likes") var totalLikes: Int? = null,
    @Expose @SerializedName("total_photos") var totalPhotos: Int? = null,
    @Expose @SerializedName("accepted_tos") var acceptedTos: Boolean? = null,
    @Expose @SerializedName("for_hire") var forHire: Boolean? = null,
    @Expose @SerializedName("social") var social: Social? = Social()
) : Parcelable

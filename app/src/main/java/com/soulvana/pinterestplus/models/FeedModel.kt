package com.soulvana.pinterestplus.models

import com.google.gson.annotations.SerializedName

data class FeedModel(
    @SerializedName("id") var id: String,
    @SerializedName("created_at") var createdAt: String,
    @SerializedName("width") var width: Int,
    @SerializedName("height") var height: Int,
    @SerializedName("color") var color: String,
    @SerializedName("likes") var likes: Int,
    @SerializedName("liked_by_user") var likedByUser: Boolean,
    @SerializedName("user") var user: User,
    @SerializedName("current_user_collections") var currentUserCollections: List<CurrentUserCollections>,
    @SerializedName("urls") var urls: Urls,
    @SerializedName("categories") var categories: List<CategoryModel>,
    @SerializedName("links") var links: FeedLinks
)
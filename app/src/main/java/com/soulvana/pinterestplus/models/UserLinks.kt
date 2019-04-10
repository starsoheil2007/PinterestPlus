package com.soulvana.pinterestplus.models

import com.google.gson.annotations.SerializedName

data class UserLinks(
    @SerializedName("self") var self: String,
    @SerializedName("html") var html: String,
    @SerializedName("photos") var photos: String,
    @SerializedName("likes") var likes: String
)
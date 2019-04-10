package com.soulvana.pinterestplus.models

import com.google.gson.annotations.SerializedName

data class CategoryLinks(
    @SerializedName("self") var self: String,
    @SerializedName("photos") var photos: String
)
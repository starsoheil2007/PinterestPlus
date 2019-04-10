package com.soulvana.pinterestplus.models

import com.google.gson.annotations.SerializedName

data class FeedLinks(
    @SerializedName("self") var self: String,
    @SerializedName("html") var html: String,
    @SerializedName("download") var download: String
)


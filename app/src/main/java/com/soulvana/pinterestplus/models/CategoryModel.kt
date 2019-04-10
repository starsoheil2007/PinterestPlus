package com.soulvana.pinterestplus.models

import com.google.gson.annotations.SerializedName
import com.soulvana.pinterestplus.models.CategoryLinks

data class CategoryModel(
    @SerializedName("id") var id: Int,
    @SerializedName("title") var title: String,
    @SerializedName("photo_count") var photoCount: Long,
    @SerializedName("links") var links: CategoryLinks
)
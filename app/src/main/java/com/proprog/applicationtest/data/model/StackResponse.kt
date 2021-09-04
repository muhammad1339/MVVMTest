package com.proprog.applicationtest.data.model


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class StackResponse(
    @SerializedName("has_more")
    @Expose
    val hasMore: Boolean,
    @SerializedName("items")
    @Expose
    val items: List<Item>,
    @SerializedName("quota_max")
    @Expose
    val quotaMax: Int,
    @SerializedName("quota_remaining")
    @Expose
    val quotaRemaining: Int
)
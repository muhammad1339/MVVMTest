package com.proprog.applicationtest.data.model


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class Owner(
    @SerializedName("accept_rate")
    @Expose
    val acceptRate: Int,
    @SerializedName("display_name")
    @Expose
    val displayName: String,
    @SerializedName("link")
    @Expose
    val link: String,
    @SerializedName("profile_image")
    @Expose
    val profileImage: String,
    @SerializedName("reputation")
    @Expose
    val reputation: Int,
    @SerializedName("user_id")
    @Expose
    val userId: Int,
    @SerializedName("user_type")
    @Expose
    val userType: String
)
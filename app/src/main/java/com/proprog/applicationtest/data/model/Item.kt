package com.proprog.applicationtest.data.model


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose
import com.idanatz.oneadapter.external.interfaces.Diffable

data class Item(
    @SerializedName("answer_id")
    @Expose
    val answerId: Long,
    @SerializedName("content_license")
    @Expose
    val contentLicense: String,
    @SerializedName("creation_date")
    @Expose
    val creationDate: Int,
    @SerializedName("is_accepted")
    @Expose
    val isAccepted: Boolean,
    @SerializedName("last_activity_date")
    @Expose
    val lastActivityDate: Int,
    @SerializedName("owner")
    @Expose
    val owner: Owner,
    @SerializedName("question_id")
    @Expose
    val questionId: Int,
    @SerializedName("score")
    @Expose
    val score: Int
) :Diffable {
    override val uniqueIdentifier: Long = answerId
    override fun areContentTheSame(other: Any): Boolean = other is Item && answerId == other.answerId
}

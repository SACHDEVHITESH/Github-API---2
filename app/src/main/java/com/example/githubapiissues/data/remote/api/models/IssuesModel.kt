package com.example.githubapiissues.data.remote.api.models

import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

@Parcelize
data class IssuesModel(
    @SerializedName("user") var user: GithubUserModel,
    @SerializedName("updated_at") var updatedAt: Date,
    @SerializedName("title") var title: String,
    @SerializedName("body") var body: String
): Serializable,
    Parcelable {

    @SuppressLint("SimpleDateFormat")
    fun getUpdatedDateAsString(): String? {
        val formatter = SimpleDateFormat("dd MMMM yyyy, hh:mm:ss")
        return formatter.format(updatedAt)
    }
}
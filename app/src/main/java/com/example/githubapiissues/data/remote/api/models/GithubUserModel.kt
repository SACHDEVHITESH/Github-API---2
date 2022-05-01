package com.example.githubapiissues.data.remote.api.models

import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

@Parcelize
data class GithubUserModel(
    @SerializedName("login") var login: String,
    @SerializedName("avatar_url") var avatarUrl: String,
) : Serializable, Parcelable
package com.example.githubapiissues.data.remote.api.base

import com.example.githubapiissues.data.remote.api.models.IssuesModel
import retrofit2.Call
import retrofit2.http.GET

interface GithubApi {

    @GET("repos/square/okhttp/issues")
    fun getIssuesInfo(
    ): Call<List<IssuesModel>>

}
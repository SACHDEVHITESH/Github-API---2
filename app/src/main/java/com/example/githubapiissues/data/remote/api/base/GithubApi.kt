package com.example.githubapiissues.data.remote.api.base

import com.example.githubapiissues.data.remote.api.models.CommentsModel
import com.example.githubapiissues.data.remote.api.models.IssuesModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {

    @GET("repos/square/okhttp/issues")
    fun getIssuesInfo(
    ): Call<List<IssuesModel>>

    @GET("https://api.github.com/repos/square/okhttp/issues/{number}/comments")
    fun getCommentssInfo(
        @Path("number") number: String
    ): Call<List<CommentsModel>>

}
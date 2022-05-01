package com.example.githubapiissues.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubapiissues.data.remote.api.base.ApiClient
import com.example.githubapiissues.data.remote.api.models.CommentsModel
import com.example.githubapiissues.data.remote.api.models.GithubUserModel
import com.example.githubapiissues.data.remote.api.models.IssuesModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CommentsViewModel : ViewModel() {

    private val githubComments = MutableLiveData<List<CommentsModel>?>()

    fun getCommentsInfo(number : String) : MutableLiveData<List<CommentsModel>?> {
            val response = ApiClient.getInstance().apis.getCommentssInfo(number)
                response.enqueue(object : Callback<List<CommentsModel>>{
                    override fun onResponse(call: Call<List<CommentsModel>>, response: Response<List<CommentsModel>>) {
                        if (response.code() == 200) {
                            githubComments.value = response.body()
                        } else {
                            githubComments.value = null
                        }
                    }
                    override fun onFailure(call: Call<List<CommentsModel>>, t: Throwable) {
                        githubComments.value = null
                    }

                })

        return githubComments
    }
}

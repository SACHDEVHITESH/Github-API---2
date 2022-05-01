package com.example.githubapiissues.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubapiissues.data.remote.api.base.ApiClient
import com.example.githubapiissues.data.remote.api.models.GithubUserModel
import com.example.githubapiissues.data.remote.api.models.IssuesModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class IssuesViewModel : ViewModel() {

    private val githubIssues = MutableLiveData<List<IssuesModel>?>()

    fun getIssuesInfo() : MutableLiveData<List<IssuesModel>?> {
            val response = ApiClient.getInstance().apis.getIssuesInfo()
                response.enqueue(object : Callback<List<IssuesModel>>{
                    override fun onResponse(call: Call<List<IssuesModel>>, response: Response<List<IssuesModel>>) {
                        if (response.code() == 200) {
                            githubIssues.value = response.body()
                        } else {
                            githubIssues.value = null
                        }
                    }
                    override fun onFailure(call: Call<List<IssuesModel>>, t: Throwable) {
                        githubIssues.value = null
                    }

                })

        return githubIssues
    }
}

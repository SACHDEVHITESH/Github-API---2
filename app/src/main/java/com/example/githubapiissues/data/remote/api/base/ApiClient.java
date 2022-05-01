package com.example.githubapiissues.data.remote.api.base;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static ApiClient instance;
    private final GithubApi githubApis;

    private ApiClient() {
        synchronized (Retrofit.class) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(100, TimeUnit.SECONDS);


            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ApiConstants.Companion.getBaseUrl())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(builder.build())
                    .build();

            githubApis = retrofit.create(GithubApi.class);
        }
    }

    public static ApiClient getInstance() {
        if (instance == null) {
            instance = new ApiClient();
        }
        return instance;
    }

    public GithubApi getApis() {
        return instance.githubApis;
    }
}

package com.example.halodocdemo.data.remote

import com.example.halodocdemo.data.model.api.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

public interface ApiHelper{


    @GET("search")
    fun fetchQuery(@Query("query") searchString: String?): Call<SearchResponse>
}
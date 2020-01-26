package com.example.halodocdemo.data.remote

import android.content.Context
import com.example.halodocdemo.AppUtil.AppConstant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit

class ApiManager(val context: Context){


        fun getSearchApi():ApiHelper{
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level= HttpLoggingInterceptor.Level.BASIC
            val client = OkHttpClient().newBuilder()
                .addInterceptor(loggingInterceptor)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(AppConstant.base_url)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create()).build()
            return retrofit.create(ApiHelper::class.java)



    }
}
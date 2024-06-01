package com.example.people

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitPhone {
    private const val BASE_URL1 = "http://192.168.56.1:3000/api/v1/"

    private val retrofitPhone by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL1)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService1: ApiService by lazy {
        retrofitPhone.create(ApiService::class.java)
    }
}

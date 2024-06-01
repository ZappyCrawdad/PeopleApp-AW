package com.example.people

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitEmail {

    private const val BASE_URL2 = "http://192.168.56.1:3000/api/v1/"

    private val retrofitEmail by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL2)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService2: ApiService by lazy {
        retrofitEmail.create(ApiService::class.java)
    }
}
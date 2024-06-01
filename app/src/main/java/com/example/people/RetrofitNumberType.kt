package com.example.people

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitNumberType {

    private const val BASE_URL3 = "http://192.168.56.1:3000/api/v1/"

    private val retrofitNumberType by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL3)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService3: ApiService by lazy {
        retrofitNumberType.create(ApiService::class.java)
    }
}
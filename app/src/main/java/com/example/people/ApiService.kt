package com.example.people

// File: ApiService.kt
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @GET("{id}")
    fun getPerson(@Path("id") id: String): Call<Person>

    @POST("person")
    fun createPerson(@Body person: Person): Call<Person>

    @PUT("person/{id}")
    fun updatePerson(@Path("id") id: String, @Body product: Person):Call<Person>

    //Phone

    @GET("person.person-phone/{id}")
    fun getPhone(@Path("id") id: String): Call<Phone>

    @POST("person-phone")
    fun createPhone(@Body phone: Phone): Call<Phone>

    @PUT("person.person-phone/")
    fun updatePhone(@Body phone: Phone): Call<Phone>

    //Email

    @GET("person.email-address/{id}")
    fun getEmail(@Path("id") id: String): Call<Email>

    @POST("email-address")
    fun createEmail(@Body email: Email): Call<Email>

    @PUT("person.email-address/")
    fun updateEmail(@Body email: Email): Call<Email>

    //Number Type

    @GET("person.phone-number-type/{id}")
    fun getNumberType(@Path("id") id: String): Call<NumberType>

    @POST("phone-number-type")
    fun createNumberType(@Body numberType: NumberType): Call<NumberType>

    @PUT("person.phone-number-type/")
    fun updateNumberType(@Body numberType: NumberType): Call<NumberType>
}


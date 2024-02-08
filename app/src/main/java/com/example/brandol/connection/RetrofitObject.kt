package com.example.brandol.connection

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitObject {

    private const val URL = "https://dev.brandol.site/swagger-ui/index.html/"

    private val getRetrofit by lazy {

        Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val getRetrofitService: RetrofitAPI by lazy {  getRetrofit.create(RetrofitAPI::class.java) }

}
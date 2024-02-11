package com.example.brandol.connection

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface RetrofitAPI {
    @POST("/auth/login/kakao")
    fun login(@Body request: RetrofitClient2.RequestLogin): Call<RetrofitClient2.ResponseLogin>
    @POST("/auth/signup")
    fun signup(@Body request: RetrofitClient2.RequestSignup): Call<RetrofitClient2.ResponseSignup>

    @GET("/avatar/myitems")
    fun getMyitem()

    @GET("/search/main")
    fun getSearchMain(@Header("Authorization")token:String): Call<RetrofitClient2.ResponseSearchMain>


}
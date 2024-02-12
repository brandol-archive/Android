package com.example.brandol.connection

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface RetrofitAPI {
    @POST("/auth/login/kakao")
    fun login(@Body request: RetrofitClient2.RequestLogin): Call<RetrofitClient2.ResponseLogin>
    @POST("/auth/signup")
    fun signup(@Body request: RetrofitClient2.RequestSignup): Call<RetrofitClient2.ResponseSignup>

    @GET("/avatar/myitems")
    fun getMyitem()

    @GET("/brands/{brandId}/header")
    fun getBrandHeader(@Header("Authorization")token:String, @Path("brandId") brandId: Long): Call<RetrofitClient2.GetBrandHeader>

    @GET("/users/main")
    fun getHomeFragment(@Header("Authorization")token:String): Call<RetrofitClient2.GetHomeFragment>

    @GET("/search/detail/brands")
    fun searchDetailBrands(@Header("Authorization")token:String): Call<RetrofitClient2.SearchDetailBrands>

    @GET("/search/detail/avatar-store/header")
    fun getUserAvatarAndPoints(@Header("Authorization")token:String): Call<RetrofitClient2.GetUserAvatarAndPoints>

    @POST("/users/my-board-list/unsubscribe/{brandId}")
    fun unsubscribeBrand(@Header("Authorization")token:String, @Path("brandId") brandId: Long): Call<RetrofitClient2.UnsubscribeBrand>
}
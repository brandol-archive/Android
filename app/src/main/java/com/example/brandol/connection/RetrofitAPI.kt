package com.example.brandol.connection

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitAPI {
    @POST("/auth/login/kakao")
    fun login(@Body request: RetrofitClient2.RequestLogin): Call<RetrofitClient2.ResponseLogin>
    @POST("/auth/signup")
    fun signup(@Body request: RetrofitClient2.RequestSignup): Call<RetrofitClient2.ResponseSignup>

    @GET("/avatar/myitems")
    fun getMyItemData(@Header("Authorization")token:String):Call<RetrofitClient2.ResponseItem>
    @GET("/avatar/{memberId}/items")
    fun getOtherItemData(@Header("Authorization")token: String, @Path("memberId") memberId :Long):Call<RetrofitClient2.ResponseItem>

    @GET("/avatar/{memberId}/community")
    fun getOtherCommunityData(@Header("Authorization")token: String,@Path("memberId") memberId :Long,@Query("page") page: Int):Call<RetrofitClient2.ResponseCommunity>
    @GET("/avatar/{memberId}/brandList")
    fun getOtherBrand(@Header("Authorization")token: String,@Path("memberId") memberId :Long):Call<RetrofitClient2.ResponseBrand>

    @PATCH("/avatar/myitems/wear")
    fun wearMyItem(@Header("Authorization")token:String,@Body request: RetrofitClient2.ReequestWear):Call<RetrofitClient2.ResponseWear>
}

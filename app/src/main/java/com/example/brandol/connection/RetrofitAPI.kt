package com.example.brandol.connection

import retrofit2.Call
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

    //페이지 조회 API : 검색 메인 페이지 조회
    @GET("/search/main")
    fun getSearchMain(@Header("Authorization")token:String): Call<RetrofitClient2.ResponseSearchMain>

    //미션 관련 API : 포인트 미션 도전
    @POST("/users/missions/{missionId}")
    fun completeMission(missionId1: String, @Path("missionId") missionId: Long): Call<RetrofitClient2.ResponseMissionCompletion>

    //미션 관련 API : 포인트 미션 성공
    /*
    @POST("/users/missions/{missionId}/success")
    fun completeMissionSuccess(@Path("missionId") missionId: Long): Call<RetrofitClient2.ResponseMissionSuccess>
*/
    //미션 관련 API : 포인트 미션 목록
    @GET("/users/missions")
    fun getMissionList(@Header("Authorization") token: String): Call<RetrofitClient2.ResponseMissionList>

}
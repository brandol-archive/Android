package com.example.brandol.connection

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitAPI {
    @POST("/auth/login/kakao")
    fun login(@Body request: RetrofitClient2.RequestLogin): Call<RetrofitClient2.ResponseLogin>
    @POST("/auth/signup")
    fun signup(@Body request: RetrofitClient2.RequestSignup): Call<RetrofitClient2.ResponseSignup>

    @GET("/avatar/myitems")
    fun getMyitem()

    //브랜드 관련 API (피그마 기준 페이지 2의 브랜드 상단 부분 조회, 하단 부분의 팬덤, 컨텐츠, 커뮤니티 조회)
    @POST("/brands/{brandId}/community/new")
    fun addBoard(@Body request: RetrofitClient2.AddBoardRequest): Call<RetrofitClient2.AddBoardResponse>
    @POST("/brands/new")
    fun addBrand(@Body request: RetrofitClient2.AddBrandRequest): Call<RetrofitClient2.AddBrandResponse>
    @GET("/brands/{brandId}/my-written/comments")
    fun myComments(
        @Path("brandId") brandId: Long,
        @Query("memberId") memberId: Long
    ): Call<RetrofitClient2.MyWrittenCommentsResponse>
    @GET("/brands/{brandId}/my-written/articles")
    fun myArticles(
        @Path("brandId") brandId: Long,
        @Query("memberId") memberId: Long
    ): Call<RetrofitClient2.MyWrittenArticlesResponse>
    @GET("/brands/{brandId}/header")
    fun brandheader(@Path("brandId") brandId: Long): Call<RetrofitClient2.BrandHeader>
    @GET("/brands/{brandId}/fandom")
    fun fandomLatest(@Path("brandId") brandId: Long): Call<RetrofitClient2.FandomResponse>
    @GET("/brands/{brandId}/contents")
    fun contentsLatest(@Path("brandId") brandId: Long): Call<RetrofitClient2.ContentsResponse>
    @GET("/brands/{brandId}/community")
    fun communityLatest(@Path("brandId") brandId: Long): Call<RetrofitClient2.CommunityResponse>


    //댓글 관련 API
    @POST("/fandom/{fandom_id}/comments/{commentId}")
    fun fandomWriteComcomment(
        @Path("fandom_id") fandomId: Long,
        @Query("memberId") memberId: Long,
        @Query("commentId") commentId: Long,
        @Body request: RetrofitClient2.FandomComcommentRequest
    ): Call<RetrofitClient2.FandomComcommentResponse>
    @POST("/fandom/{fandom_id}/comments/new")
    fun fandomWriteComment(
        @Path("fandom_id") fandomId: Long,
        @Query("memberId") memberId: Long,
        @Body request: RetrofitClient2.FandomCommentRequest
    ): Call<RetrofitClient2.FandomCommentResponse>
    @POST("/contents/{contents_id}/comments/{commentId}")
    fun contentsWriteComcomment(
        @Path("contents_id") contentsId: Long,
        @Query("memberId") memberId: Long,
        @Query("commentId") commentId: Long,
        @Body request: RetrofitClient2.ContentsComcommentRequest
    ): Call<RetrofitClient2.ContentsComcommentResponse>
    @POST("/contents/{contents_id}/comments/new")
    fun contentsWriteComment(
        @Path("contents_id") contentsId: Long,
        @Query("memberId") memberId: Long,
        @Body request: RetrofitClient2.ContentsCommentRequest
    ): Call<RetrofitClient2.ContentsCommentResponse>
    @POST("/communities/{community_id}/comments/{commentId}")
    fun communityWriteComcomment(
        @Path("community_id") communityId: Long,
        @Query("memberId") memberId: Long,
        @Query("commentId") commentId: Long,
        @Body request: RetrofitClient2.CommunityComcommentRequest
    ): Call<RetrofitClient2.CommunityComcommentResponse>
    @POST("/communities/{community_id}/comments/new")
    fun communityWriteComment(
        @Path("community_id") communityId: Long,
        @Query("memberId") memberId: Long,
        @Body request: RetrofitClient2.CommunityCommentRequest
    ): Call<RetrofitClient2.CommunityCommentResponse>
    @GET("/fandoms/{fandomId}/comments/all")
    fun fandomArticleViewComment(@Path("fandomId") fandomId: Long): Call<RetrofitClient2.FandomArticleResponse>
    @GET("/contents/{contentsId}/comments/all")
    fun contentsArticleViewComment(@Path("contentsId") contentsId: Long): Call<RetrofitClient2.ContentsArticleResponse>
    @GET("/communities/{communityId}/comments/all")
    fun communityArticleViewComment(@Path("communityId") communityId:Long): Call<RetrofitClient2.CommunityArticleResponse>

    //팬덤 게시판 관련 API
    @GET("/brands/{brandId}/fandom-cultures")
    fun fandomCultureBoardView(
        @Path("brandId") brandId: Long,
        @Query("page") page: Int
    ): Call<RetrofitClient2.FandomCultureBoardResponse>
    @GET("/brands/{brandId}/fandom-announcements")
    fun fandomAnnouncementBoardView(
        @Path("brandId") brandId: Long,
        @Query("page") page: Int
    ): Call<RetrofitClient2.FandomAnnouncementBoardResponse>
    @GET("/brands/fandoms/{fandomId}")
    fun fandomBoardDetailView(@Path("fandomId") fandomId:Long): Call<RetrofitClient2.FandomBoardDetailResponse>

    //콘텐츠 게시판 관련 API
    @GET("/brands/{brandId}/contents-videos")
    fun contentsVideoView(
        @Path("brandId") brandId: Long,
        @Query("page") page: Int
    ): Call<RetrofitClient2.ContentsVideoResponse>
    @GET("/brands/{brandId}/contents-events")
    fun contentsEventView(
        @Path("brandId") brandId: Long,
        @Query("page") page: Int
    ): Call<RetrofitClient2.ContentsEventResponse>
    @GET("/brands/{brandId}/contents-cardnews")
    fun contentsCardnewsView(
        @Path("brandId") brandId: Long,
        @Query("page") page:Int
    ): Call<RetrofitClient2.ContentsCardnewsResponse>
    @GET("/brands/contents/{contentsId}")
    fun contentsBoardDetailView(@Path("contentsId") contentsId: Long): Call<RetrofitClient2.ContentsBoardDetail>

    //커뮤니티 게시판 관련 API
    @GET("/brands/{brandId}/community-free")
    fun communityFreeView(
        @Path("brandId") brandId: Long,
        @Query("page") page: Int
    ): Call<RetrofitClient2.CommunityFreeResponse>
    @GET("/brands/{brandId}/community-feedback")
    fun communityFeedbackView(
        @Path("brandId") brandId: Long,
        @Query("page") page: Int
    ): Call<RetrofitClient2.CommunityFeedbackResponse>
    @GET("/brands/communities/{communityId}")
    fun communityBoardDetailView(@Path("communityId") communityId: Long): Call<RetrofitClient2.CommunityBoardDetailResponse>
}
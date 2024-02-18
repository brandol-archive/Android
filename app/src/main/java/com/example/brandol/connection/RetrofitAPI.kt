package com.example.brandol.connection

import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query


interface RetrofitAPI {
    @POST("/auth/login/kakao")
    fun login(@Body request: RetrofitClient2.RequestLogin): Call<RetrofitClient2.ResponseLogin>
    @POST("/auth/signup")
    fun signup(@Body request: RetrofitClient2.RequestSignup): Call<RetrofitClient2.ResponseSignup>

    //구매아이템 조회
    @GET("/avatar/myitems")
    fun getMyItemData(@Header("Authorization")token:String):Call<RetrofitClient2.ResponseItem>
    //타유저 아이템 조회
    @GET("/avatar/{memberId}/items")
    fun getOtherItemData(@Header("Authorization")token: String, @Path("memberId") memberId :Long):Call<RetrofitClient2.ResponseItem>
    //타유저 게시물 조회
    @GET("/avatar/{memberId}/community")
    fun getOtherCommunityData(@Header("Authorization")token: String,@Path("memberId") memberId :Long,@Query("page") page: Int):Call<RetrofitClient2.ResponseCommunity>
   //타유저 브랜드도회
    @GET("/avatar/{memberId}/brandsList")
    fun getOtherBrand(@Header("Authorization")token: String, @Path("memberId") memberId :Long):Call<RetrofitClient2.ResponseBrand>
    //타유저 아바타 조회
    @GET("/users/{memberId}/avatar")
    fun getOtherAvatar(@Header("Authorization")token: String,@Path("memberId") memberId :Long): Call<RetrofitClient2.ResponseOtherAvatar>
    // 아바타 서버에 업로드
    @Multipart
    @PATCH("/avatar/myitems/wear")
    fun updateAvatar(@Header("Authorization")token:String, @Part("wearingItemIdList") wearingItemIdList : List<Long>, @Part avatarImage: MultipartBody.Part):Call<RetrofitClient2.ResponseWear>


    //마이페이지 정보 조회
    @GET("/auth/info")
    fun getMypageData(@Header("Authorization")token:String) : Call<RetrofitClient2.ResponseMyInfo>

    //회원탈퇴
    @PATCH("/auth/status")
    fun deleteAccount(@Header("Authorization")token:String) : Call<RetrofitClient2.ResponseStatus>
    //닉네임 수정
    @PATCH("/auth/nickname")
    fun changeNickname(@Header("Authorization")token:String,@Body request : RetrofitClient2.RequestNickname) : Call<RetrofitClient2.ResponseNickname>

    //서현 시작
    //페이지 조회 API : 검색 메인 페이지 조회
    @GET("/search/main")
    fun getSearchMain(@Header("Authorization")token:String): Call<RetrofitClient2.ResponseSearchMain>

    //브랜드 추가 미션 도전
    @POST("/missions/{missionId}/add")
    fun challengeAddMission(@Path("missionId") missionId: Long): Call<RetrofitClient2.ResponseChallengeAddMission>

    //브랜드 추가 미션 성공
    @PATCH("/missions/{missionId}/add/success")
    fun completeAddMissionSuccess(@Path("missionId") missionId: Long): Call<RetrofitClient2.ResponseMissionSuccess>

    //미션 관련 API : 포인트 미션 목록
    @GET("/users/missions")
    fun getMissionList(@Header("Authorization") token: String): Call<RetrofitClient2.ResponseMissionList>
    //서현 끝

    @GET("/brands/{brandId}/header")
    fun getBrandHeader(@Header("Authorization")token:String, @Path("brandId") brandId: Long): Call<RetrofitClient2.GetBrandHeader>

    @GET("/users/main")
    fun getHomeFragment(@Header("Authorization")token:String): Call<RetrofitClient2.GetHomeFragment>


    @POST("/users/my-board-list/unsubscribe/{brandId}")
    fun unsubscribeBrand(@Header("Authorization") token:String, @Path("brandId") brandId: Int): Call<RetrofitClient2.UnsubscribeBrand>

    @POST("/avatar/items/{itemId}")
    fun purchaseItem(@Header("Authorization") token:String, @Path("itemId") brandId: Int): Call<RetrofitClient2.PurchaseItem>

    @GET("/search/detail/brands")
    fun searchDetailBrands(@Header("Authorization") token:String): Call<RetrofitClient2.SearchDetailBrands>

    @GET("/search/detail/users")
    fun searchDetailUser(@Header("Authorization") token:String): Call<RetrofitClient2.SearchDetailUser>

    @GET("/search/detail/contents")
    fun searchDetailContents(@Header("Authorization") token:String): Call<RetrofitClient2.SearchDetailContents>

    @GET("/search/detail/avatar-store/header")
    fun searchDetailUserAvatarAndPoints(@Header("Authorization") token:String): Call<RetrofitClient2.SearchDetailUserAvatarAndPoints>

    @GET("/search/detail/avatar-store/body")
    fun searchDetailAvatarStoreBody(@Header("Authorization") token:String, @Query("itemPart") itemPart: String): Call<RetrofitClient2.SearchDetailAvatarStoreBody>

    //브랜드 관련 API (피그마 기준 페이지 2의 브랜드 상단 부분 조회, 하단 부분의 팬덤, 컨텐츠, 커뮤니티 조회)
    @POST("/brands/{brandId}/community/new")
    fun addBoard(
        @Header("Authorization") token:String,
        @Body request: RetrofitClient2.AddBoardRequest
    ): Call<RetrofitClient2.AddBoardResponse>
    @POST("/brands/new")
    fun addBrand(
        @Header("Authorization") token:String,
        @Body request: RetrofitClient2.AddBrandRequest
    ): Call<RetrofitClient2.AddBrandResponse>
    @GET("/brands/{brandId}/my-written/comments")
    fun myComments(
        @Header("Authorization") token:String,
        @Path("brandId") brandId: Long?,
        @Query("memberId") memberId: Long?
    ): Call<RetrofitClient2.MyWrittenCommentsResponse>
    @GET("/brands/{brandId}/my-written/articles")
    fun myArticles(
        @Header("Authorization") token:String,
        @Path("brandId") brandId: Long?,
        @Query("memberId") memberId: Long?
    ): Call<RetrofitClient2.MyWrittenArticlesResponse>


    @GET("/brands/{brandId}/header")
    fun brandheader(
        @Header("Authorization") token:String,
        @Path("brandId") brandId: Long
    ): Call<RetrofitClient2.BrandHeader>

    @GET("/brands/{brandId}/fandom")
    fun fandomLatest(
        @Header("Authorization") token:String,
        @Path("brandId") brandId: Long
    ): Call<RetrofitClient2.FandomResponse>
    @GET("/brands/{brandId}/contents")
    fun contentsLatest(
        @Header("Authorization") token:String,
        @Path("brandId") brandId: Long
    ): Call<RetrofitClient2.ContentsResponse>
    @GET("/brands/{brandId}/community")
    fun communityLatest(
        @Header("Authorization") token:String,
        @Path("brandId") brandId: Long
    ): Call<RetrofitClient2.CommunityResponse>


    //댓글 관련 API
    @POST("/fandom/{fandom_id}/comments/{commentId}")
    fun fandomWriteComcomment(
        @Header("Authorization") token:String,
        @Path("fandom_id") fandomId: Long,
        @Query("memberId") memberId: Long,
        @Query("commentId") commentId: Long,
        @Body request: RetrofitClient2.FandomComcommentRequest
    ): Call<RetrofitClient2.FandomComcommentResponse>
    @POST("/fandom/{fandom_id}/comments/new")
    fun fandomWriteComment(
        @Header("Authorization") token:String,
        @Path("fandom_id") fandomId: Long,
        @Query("memberId") memberId: Long,
        @Body request: RetrofitClient2.FandomCommentRequest
    ): Call<RetrofitClient2.FandomCommentResponse>
    @POST("/contents/{contents_id}/comments/{commentId}")
    fun contentsWriteComcomment(
        @Header("Authorization") token:String,
        @Path("contents_id") contentsId: Long,
        @Query("memberId") memberId: Long,
        @Query("commentId") commentId: Long,
        @Body request: RetrofitClient2.ContentsComcommentRequest
    ): Call<RetrofitClient2.ContentsComcommentResponse>
    @POST("/contents/{contents_id}/comments/new")
    fun contentsWriteComment(
        @Header("Authorization") token:String,
        @Path("contents_id") contentsId: Long,
        @Query("memberId") memberId: Long,
        @Body request: RetrofitClient2.ContentsCommentRequest
    ): Call<RetrofitClient2.ContentsCommentResponse>
    @POST("/communities/{community_id}/comments/{commentId}")
    fun communityWriteComcomment(
        @Header("Authorization") token:String,
        @Path("community_id") communityId: Long,
        @Query("memberId") memberId: Long,
        @Query("commentId") commentId: Long,
        @Body request: RetrofitClient2.CommunityComcommentRequest
    ): Call<RetrofitClient2.CommunityComcommentResponse>
    @POST("/communities/{community_id}/comments/new")
    fun communityWriteComment(
        @Header("Authorization") token:String,
        @Path("community_id") communityId: Long,
        @Query("memberId") memberId: Long,
        @Body request: RetrofitClient2.CommunityCommentRequest
    ): Call<RetrofitClient2.CommunityCommentResponse>
    @GET("/fandoms/{fandomId}/comments/all")
    fun fandomArticleViewComment(
        @Header("Authorization") token:String,
        @Path("fandomId") fandomId: Long
    ): Call<RetrofitClient2.FandomArticleResponse>
    @GET("/contents/{contentsId}/comments/all")
    fun contentsArticleViewComment(
        @Header("Authorization") token:String,
        @Path("contentsId") contentsId: Long
    ): Call<RetrofitClient2.ContentsArticleResponse>
    @GET("/communities/{communityId}/comments/all")
    fun communityArticleViewComment(
        @Header("Authorization") token:String,
        @Path("communityId") communityId:Long
    ): Call<RetrofitClient2.CommunityArticleResponse>

    //팬덤 게시판 관련 API
    @GET("/brands/{brandId}/fandom-cultures")
    fun fandomCultureBoardView(
        @Header("Authorization") token:String,
        @Path("brandId") brandId: Long,
        @Query("page") page: Int
    ): Call<RetrofitClient2.FandomCultureBoardResponse>
    @GET("/brands/{brandId}/fandom-announcements")
    fun fandomAnnouncementBoardView(
        @Header("Authorization") token:String,
        @Path("brandId") brandId: Long,
        @Query("page") page: Int
    ): Call<RetrofitClient2.FandomAnnouncementBoardResponse>
    @GET("/brands/fandoms/{fandomId}")
    fun fandomBoardDetailView(
        @Header("Authorization") token:String,
        @Path("fandomId") fandomId:Long
    ): Call<RetrofitClient2.FandomBoardDetailResponse>

    //콘텐츠 게시판 관련 API
    @GET("/brands/{brandId}/contents-videos")
    fun contentsVideoView(
        @Header("Authorization") token:String,
        @Path("brandId") brandId: Long,
        @Query("page") page: Int
    ): Call<RetrofitClient2.ContentsVideoResponse>
    @GET("/brands/{brandId}/contents-events")
    fun contentsEventView(
        @Header("Authorization") token:String,
        @Path("brandId") brandId: Long,
        @Query("page") page: Int
    ): Call<RetrofitClient2.ContentsEventResponse>
    @GET("/brands/{brandId}/contents-cardnews")
    fun contentsCardnewsView(
        @Header("Authorization") token:String,
        @Path("brandId") brandId: Long,
        @Query("page") page:Int
    ): Call<RetrofitClient2.ContentsCardnewsResponse>
    @GET("/brands/contents/{contentsId}")
    fun contentsBoardDetailView(
        @Header("Authorization") token:String,
        @Path("contentsId") contentsId: Long
    ): Call<RetrofitClient2.ContentsBoardDetail>

    //커뮤니티 게시판 관련 API
    @GET("/brands/{brandId}/community-free")
    fun communityFreeView(
        @Header("Authorization") token:String,
        @Path("brandId") brandId: Long,
        @Query("page") page: Int
    ): Call<RetrofitClient2.CommunityFreeResponse>
    @GET("/brands/{brandId}/community-feedback")
    fun communityFeedbackView(
        @Header("Authorization") token:String,
        @Path("brandId") brandId: Long,
        @Query("page") page: Int
    ): Call<RetrofitClient2.CommunityFeedbackResponse>
    @GET("/brands/communities/{communityId}")
    fun communityBoardDetailView(
        @Header("Authorization") token:String,
        @Path("communityId") communityId: Long
    ): Call<RetrofitClient2.CommunityBoardDetailResponse>

}
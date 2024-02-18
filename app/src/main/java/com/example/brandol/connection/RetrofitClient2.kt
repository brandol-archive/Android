package com.example.brandol.connection

import com.google.gson.annotations.SerializedName
import java.util.Date

class RetrofitClient2 {
    data class RequestLogin(
        @SerializedName("email")
        val email: String,
        @SerializedName("name")
        val name: String
    )


    data class ResponseLogin(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: LoginResult
    )

    data class LoginResult(
        @SerializedName("accessToken")
        val accessToken: String,
        @SerializedName("refreshToken")
        val refreshToken: String
    )

    data class RequestSignup(
        @SerializedName("email")
        val email: String,
        @SerializedName("termsIdList")
        val termsIdList: List<Long>,
        @SerializedName("nickname")
        val nickname: String,
        @SerializedName("gender")
        val gender: String,
        @SerializedName("age")
        val age: Int
    )

    data class ResponseSignup(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: SignupResult
    )

    data class SignupResult(
        @SerializedName("memberId")
        val memberId: Long,
        @SerializedName("signUp")
        val signUp: Boolean
    )

    data class ResponseItem(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: List<Item>
    )

    data class Item(
        @SerializedName("myItemId")
        val myItemId: Long,
        @SerializedName("itemId")
        val itemId: Long,
        @SerializedName("brandId")
        val brandId: Long,
        @SerializedName("brandName")
        val brandName : String,
        @SerializedName("itemName")
        val itemName : String,
        @SerializedName("part")
        val part : String,
        @SerializedName("description")
        val description : String,
        @SerializedName("image")
        val image : String,
        @SerializedName("wearingImage")
        val wearingImage : String,
        @SerializedName("price")
        val price : Int,
        @SerializedName("createdAt")
        val createdAt : String,
        @SerializedName("wearing")
        val wearing : Boolean,
    )

    //2페이지 브랜드 관련 api
    //브랜드 콘텐츠에 종속된 브랜드 자유게시판, 피드백 게시판에 게시글 생성
    data class AddBoardRequest(
        @SerializedName("title")
        val title: String,
        @SerializedName("content")
        val content: String,
        @SerializedName("images")
        val images: List<String>,
        @SerializedName("communityType")
        val communityType: String
    )

    data class AddBoardResponse(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: String
    )

    //브랜돌 서비스에 브랜드를 신규 등록
    data class AddBrandRequest(
        @SerializedName("name")
        val name: String,
        @SerializedName("description")
        val description: String,
        @SerializedName("profileImage")
        val profileImage: ByteArray,
        @SerializedName("backgroundImage")
        val backgroundImage: ByteArray
    )

    data class AddBrandResponse(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: AddBrandResult
    )

    data class AddBrandResult(
        @SerializedName("createdAt")
        val createdAt: Date,
        @SerializedName("updatedAt")
        val updatedAt: Date,
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("description")
        val description: String,
        @SerializedName("profileImage")
        val profileImage: String,
        @SerializedName("backgroundImage")
        val backgroundImage: String
    )

    //멤버 작성 댓글 조회 (MyFragment)
    data class MyWrittenCommentsResponse(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: MyWrittenCommentsResult
    )

    data class MyWrittenCommentsResult(
        @SerializedName("totalArticleCount")
        val totalArticleCount: Int,
        @SerializedName("memberWrittenDtoList")
        val memberWrittenDtoList: List<MyWrittenCommentsMemberWrittenDtoList>
    )

    data class MyWrittenCommentsMemberWrittenDtoList(
        @SerializedName("writerId")
        val writerId: Int,
        @SerializedName("writerName")
        val writerName: String,
        @SerializedName("writerProfile")
        val writerProfile: String,
        @SerializedName("articleInfo")
        val articleInfo: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("content")
        val content: String,
        @SerializedName("images")
        val images: List<String>,
        @SerializedName("likeCount")
        val likeCount: Int,
        @SerializedName("commentCount")
        val commentCount: Int,
        @SerializedName("writtenDate")
        val writtenDate: String
    )

    //멤버 작성 글 조회 (MyFragment)
    data class MyWrittenArticlesResponse(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: MyWrittenArticlesResult
    )

    data class MyWrittenArticlesResult(
        @SerializedName("totalArticleCount")
        val totalArticleCount: Int,
        @SerializedName("memberWrittenDtoList")
        val memberWrittenDtoList: List<MyWrittenArticlesMemberWrittenDtoList>
    )

    data class MyWrittenArticlesMemberWrittenDtoList(
        @SerializedName("writerId")
        val writerId: Int,
        @SerializedName("writerName")
        val writerName: String,
        @SerializedName("writerProfile")
        val writerProfile: String,
        @SerializedName("articleInfo")
        val articleInfo: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("content")
        val content: String,
        @SerializedName("images")
        val images: List<String>,
        @SerializedName("likeCount")
        val likeCount: Int,
        @SerializedName("commentCount")
        val commentCount: Int,
        @SerializedName("writtenDate")
        val writtenDate: String
    )

    //브랜드 프로필,배경이미지, 구독자 수등 브랜드 상세정보 헤더를 조회
    data class BrandHeader(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: BrandResult
    )

    data class BrandResult(
        @SerializedName("brandPreviewDto")
        val brandPreviewDto: BrandPreviewDto,
        @SerializedName("brandUserStatus")
        val brandUserStatus: BrandUserStatus
    )
    data class BrandPreviewDto(
        @SerializedName("brand_id")
        val brandId: Int,
        @SerializedName("brand_name")
        val brandName: String,
        @SerializedName("brand_description")
        val brandDescription: String,
        @SerializedName("brand_fan")
        val brandFan: Int,
        @SerializedName("brand_profile")
        val brandProfile: String,
        @SerializedName("brand_background")
        val brandBackground: String
    )

    data class BrandUserStatus(
        @SerializedName("isFan")
        val isFan: Boolean,
        @SerializedName("join_date")
        val joinDate: Date,
        @SerializedName("fan_sequence")
        val fanSequence: Int
    )

    //브랜드 팬덤에 종속된 브랜드 컬처, 브랜드 공지사항 최신 2건을 조회
    data class FandomResponse(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: FandomResult
    )

    data class FandomResult(
        @SerializedName("brandFandomCultureDtoList")
        val brandFandomCultureDtoList: List<BrandFandomCultureDtoList>,
        @SerializedName("brandFandomAnnouncementDtoList")
        val brandFandomAnnouncementDtoList: List<BrandFandomAnnouncementDtoList>
    )

    data class BrandFandomCultureDtoList(
        @SerializedName("writerId")
        val writerId: Int,
        @SerializedName("writerName")
        val writerName: String,
        @SerializedName("writerProfile")
        val writerProfile: String,
        @SerializedName("fandomId")
        val fandomId: Int,
        @SerializedName("title")
        val title: String,
        @SerializedName("content")
        val content: String,
        @SerializedName("images")
        val images: List<String>,
        @SerializedName("likeCount")
        val likeCount: Int,
        @SerializedName("commentCount")
        val commentCount: Int,
        @SerializedName("writtenDate")
        val writtenDate: String
    )

    data class BrandFandomAnnouncementDtoList(
        @SerializedName("writerId")
        val writerId: Int,
        @SerializedName("writerName")
        val writerName: String,
        @SerializedName("writerProfile")
        val writerProfile: String,
        @SerializedName("fandomId")
        val fandomId: Int,
        @SerializedName("title")
        val title: String,
        @SerializedName("content")
        val content: String,
        @SerializedName("images")
        val images: List<String>,
        @SerializedName("likeCount")
        val likeCount: Int,
        @SerializedName("commentCount")
        val commentCount: Int,
        @SerializedName("writtenDate")
        val writtenDate: String
    )

    //브랜드 콘텐츠에 종속된 브랜드 이벤트, 브랜드 카드뉴스, 브랜드 비디오 최신 2건을 조회
    data class ContentsResponse(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: ContentsResult
    )

    data class ContentsResult(
        @SerializedName("brandContentsEventDtoList")
        val brandContentsEventDtoList: List<BrandContentsEventDtoList>,
        @SerializedName("brandContentsCardNewsDtoList")
        val brandContentsCardNewsDtoList: List<BrandContentsCardNewsDtoList>,
        @SerializedName("brandContentsVideoDtoList")
        val brandContentsVideoDtoList: List<BrandContentsVideoDtoList>
    )

    data class BrandContentsEventDtoList(
        @SerializedName("writerId")
        val writerId: Int,
        @SerializedName("writerName")
        val writerName: String,
        @SerializedName("writerProfile")
        val writerProfile: String,
        @SerializedName("contentsId")
        val contentsId: Int,
        @SerializedName("title")
        val title: String,
        @SerializedName("content")
        val content: String,
        @SerializedName("images")
        val images: List<String>,
        @SerializedName("likeCount")
        val likeCount: Int,
        @SerializedName("commentCount")
        val commentCount: Int,
        @SerializedName("writtenDate")
        val writtenDate: String
    )

    data class BrandContentsCardNewsDtoList(
        @SerializedName("writerId")
        val writerId: Int,
        @SerializedName("writerName")
        val writerName: String,
        @SerializedName("writerProfile")
        val writerProfile: String,
        @SerializedName("contentsId")
        val contentsId: Int,
        @SerializedName("title")
        val title: String,
        @SerializedName("content")
        val content: String,
        @SerializedName("images")
        val images: List<String>,
        @SerializedName("likeCount")
        val likeCount: Int,
        @SerializedName("commentCount")
        val commentCount: Int,
        @SerializedName("writtenDate")
        val writtenDate: String
    )

    data class BrandContentsVideoDtoList(
        @SerializedName("writerId")
        val writerId: Int,
        @SerializedName("writerName")
        val writerName: String,
        @SerializedName("writerProfile")
        val writerProfile: String,
        @SerializedName("contentsId")
        val contentsId: Int,
        @SerializedName("title")
        val title: String,
        @SerializedName("content")
        val content: String,
        @SerializedName("images")
        val images: List<String>,
        @SerializedName("video")
        val video: String,
        @SerializedName("likeCount")
        val likeCount: Int,
        @SerializedName("commentCount")
        val commentCount: Int,
        @SerializedName("writtenDate")
        val writtenDate: String
    )

    //브랜드 콘텐츠에 종속된 브랜드 자유게시판, 피드백 게시판 최신 2건을 조회
    data class CommunityResponse(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: CommunityResult
    )

    data class CommunityResult(
        @SerializedName("brandCommunityBoardDtoList")
        val brandCommunityBoardDtoList: List<BrandCommunityBoardDtoList>,
        @SerializedName("brandCommunityFeedBackBoardDtoList")
        val brandCommunityFeedBackBoardDtoList: List<BrandCommunityFeedBackBoardDtoList>
    )

    data class BrandCommunityBoardDtoList(
        @SerializedName("writerId")
        val writerId: Int,
        @SerializedName("writerName")
        val writerName: String,
        @SerializedName("writerProfile")
        val writerProfile: String,
        @SerializedName("communityId")
        val communityId: Int,
        @SerializedName("title")
        val title: String,
        @SerializedName("content")
        val content: String,
        @SerializedName("images")
        val images: List<String>,
        @SerializedName("likeCount")
        val likeCount: Int,
        @SerializedName("commentCount")
        val commentCount: Int,
        @SerializedName("writtenDate")
        val writtenDate: String
    )

    data class BrandCommunityFeedBackBoardDtoList(
        @SerializedName("writerId")
        val writerId: Int,
        @SerializedName("writerName")
        val writerName: String,
        @SerializedName("writerProfile")
        val writerProfile: String,
        @SerializedName("communityId")
        val communityId: Int,
        @SerializedName("title")
        val title: String,
        @SerializedName("content")
        val content: String,
        @SerializedName("images")
        val images: List<String>,
        @SerializedName("likeCount")
        val likeCount: Int,
        @SerializedName("commentCount")
        val commentCount: Int,
        @SerializedName("writtenDate")
        val writtenDate: String
    )

    //댓글 및 대댓글 작성
    //멤버가 팬덤 게시판 게시글에 대댓글을 생성
    data class FandomComcommentRequest(
        @SerializedName("content")
        val content: String
    )

    data class FandomComcommentResponse(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: String
    )

    //멤버가 팬덤 게시판 게시글에 댓글을 생성
    data class FandomCommentRequest(
        @SerializedName("content")
        val content: String
    )

    data class FandomCommentResponse(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: String
    )

    //멤버가 콘텐츠 게시판 게시글에 대댓글을 생성
    data class ContentsComcommentRequest(
        @SerializedName("content")
        val content: String
    )

    data class ContentsComcommentResponse(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: String
    )

    //멤버가 콘텐츠 게시판 게시글에 댓글을 생성
    data class ContentsCommentRequest(
        @SerializedName("content")
        val content: String
    )

    data class ContentsCommentResponse(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: String
    )

    //멤버가 커뮤니티 게시판 게시글에 대댓글을 생성
    data class CommunityComcommentRequest(
        @SerializedName("content")
        val content: String
    )

    data class CommunityComcommentResponse(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: String
    )

    //멤버가 커뮤니티 게시판 게시글에 댓글을 생성
    data class CommunityCommentRequest(
        @SerializedName("content")
        val content: String
    )

    data class CommunityCommentResponse(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: String
    )

    //해당 팬덤 게시글의 댓글 전체 조회
    data class FandomArticleResponse(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: List<FandomArticleResult>
    )

    data class FandomArticleResult(
        @SerializedName("parentDto")
        val parentDto: List<FandomArticleParentDto>,
        @SerializedName("childDtoList")
        val childDtoList: List<FandomArticleChildDtoList>
    )

    data class FandomArticleParentDto(
        @SerializedName("commentId")
        val commentId: Int,
        @SerializedName("parentId")
        val parentId: Int,
        @SerializedName("depth")
        val depth: Int,
        @SerializedName("writerId")
        val writerId: Int,
        @SerializedName("writerName")
        val writerName: String,
        @SerializedName("writerProfile")
        val writerProfile: String,
        @SerializedName("content")
        val content: String,
        @SerializedName("likeCount")
        val likeCount: Int,
        @SerializedName("writtenDate")
        val writtenDate: String
    )

    data class FandomArticleChildDtoList(
        @SerializedName("commentId")
        val commentId: Int,
        @SerializedName("parentId")
        val parentId: Int,
        @SerializedName("depth")
        val depth: Int,
        @SerializedName("writerId")
        val writerId: Int,
        @SerializedName("writerName")
        val writerName: String,
        @SerializedName("writerProfile")
        val writerProfile: String,
        @SerializedName("content")
        val content: String,
        @SerializedName("likeCount")
        val likeCount: Int,
        @SerializedName("writtenDate")
        val writtenDate: String
    )

    //해당 콘텐츠 게시글의 댓글 전체 조회
    data class ContentsArticleResponse(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: List<ContentsArticleResult>
    )

    data class ContentsArticleResult(
        @SerializedName("parentDto")
        val parentDto: List<ContentsArticleParentDto>,
        @SerializedName("childDtoList")
        val childDtoList: List<ContentsArticleChildDtoList>
    )

    data class ContentsArticleParentDto(
        @SerializedName("commentId")
        val commentId: Int,
        @SerializedName("parentId")
        val parentId: Int,
        @SerializedName("depth")
        val depth: Int,
        @SerializedName("writerId")
        val writerId: Int,
        @SerializedName("writerName")
        val writerName: String,
        @SerializedName("writerProfile")
        val writerProfile: String,
        @SerializedName("content")
        val content: String,
        @SerializedName("likeCount")
        val likeCount: Int,
        @SerializedName("writtenDate")
        val writtenDate: String
    )

    data class ContentsArticleChildDtoList(
        @SerializedName("commentId")
        val commentId: Int,
        @SerializedName("parentId")
        val parentId: Int,
        @SerializedName("depth")
        val depth: Int,
        @SerializedName("writerId")
        val writerId: Int,
        @SerializedName("writerName")
        val writerName: String,
        @SerializedName("writerProfile")
        val writerProfile: String,
        @SerializedName("content")
        val content: String,
        @SerializedName("likeCount")
        val likeCount: Int,
        @SerializedName("writtenDate")
        val writtenDate: String
    )

    //해당 커뮤니티 게시글의 댓글 전체 조회
    data class CommunityArticleResponse(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: List<CommunityArticleResult>
    )

    data class CommunityArticleResult(
        @SerializedName("parentDto")
        val parentDto: List<CommunityArticleParentDto>,
        @SerializedName("childDtoList")
        val childDtoList: List<CommunityArticleChildDtoList>
    )

    data class CommunityArticleParentDto(
        @SerializedName("commentId")
        val commentId: Int,
        @SerializedName("parentId")
        val parentId: Int,
        @SerializedName("depth")
        val depth: Int,
        @SerializedName("writerId")
        val writerId: Int,
        @SerializedName("writerName")
        val writerName: String,
        @SerializedName("writerProfile")
        val writerProfile: String,
        @SerializedName("content")
        val content: String,
        @SerializedName("likeCount")
        val likeCount: Int,
        @SerializedName("writtenDate")
        val writtenDate: String
    )

    data class CommunityArticleChildDtoList(
        @SerializedName("commentId")
        val commentId: Int,
        @SerializedName("parentId")
        val parentId: Int,
        @SerializedName("depth")
        val depth: Int,
        @SerializedName("writerId")
        val writerId: Int,
        @SerializedName("writerName")
        val writerName: String,
        @SerializedName("writerProfile")
        val writerProfile: String,
        @SerializedName("content")
        val content: String,
        @SerializedName("likeCount")
        val likeCount: Int,
        @SerializedName("writtenDate")
        val writtenDate: String
    )

    //팬덤 컬처 게시물 전체 조회(페이징: 0페이지 부터 시작)
    data class FandomCultureBoardResponse(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: List<FandomCultureBoardResult>
    )

    data class FandomCultureBoardResult(
        @SerializedName("writerId")
        val writerId: Int,
        @SerializedName("writerName")
        val writerName: String,
        @SerializedName("writerProfile")
        val writerProfile: String,
        @SerializedName("fandomId")
        val fandomId: Int,
        @SerializedName("title")
        val title: String,
        @SerializedName("content")
        val content: String,
        @SerializedName("images")
        val images: List<String>,
        @SerializedName("likeCount")
        val likeCount: Int,
        @SerializedName("commentCount")
        val commentCount: Int,
        @SerializedName("writtenDate")
        val writtenDate: String
    )

    //팬덤 아나운스먼트 게시물 전체 조회(페이징: 0페이지 부터 시작)
    data class FandomAnnouncementBoardResponse(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: FandomAnnouncementBoardResult
    )

    data class FandomAnnouncementBoardResult(
        @SerializedName("writerId")
        val writerId: Int,
        @SerializedName("writerName")
        val writerName: String,
        @SerializedName("writerProfile")
        val writerProfile: String,
        @SerializedName("fandomId")
        val fandomId: Int,
        @SerializedName("title")
        val title: String,
        @SerializedName("content")
        val content: String,
        @SerializedName("images")
        val images: List<String>,
        @SerializedName("likeCount")
        val likeCount: Int,
        @SerializedName("commentCount")
        val commentCount: Int,
        @SerializedName("writtenDate")
        val writtenDate: String
    )

    //팬덤 게시글 상세조회
    data class FandomBoardDetailResponse(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: List<FandomBoardDetailResult>
    )

    data class FandomBoardDetailResult(
        @SerializedName("writerId")
        val writerId: Int,
        @SerializedName("writerName")
        val writerName: String,
        @SerializedName("writerProfile")
        val writerProfile: String,
        @SerializedName("fandomId")
        val fandomId: Int,
        @SerializedName("title")
        val title: String,
        @SerializedName("content")
        val content: String,
        @SerializedName("images")
        val images: List<String>,
        @SerializedName("likeCount")
        val likeCount: Int,
        @SerializedName("commentCount")
        val commentCount: Int,
        @SerializedName("writtenDate")
        val writtenDate: String
    )

    //콘텐츠 비디오 게시판 전체 조회(페이징: 0페이지 부터 시작)
    data class ContentsVideoResponse(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: List<ContentsVideoResult>
    )

    data class ContentsVideoResult(
        @SerializedName("writerId")
        val writerId: Int,
        @SerializedName("writerName")
        val writerName: String,
        @SerializedName("writerProfile")
        val writerProfile: String,
        @SerializedName("contentsId")
        val contentsId: Int,
        @SerializedName("title")
        val title: String,
        @SerializedName("content")
        val content: String,
        @SerializedName("images")
        val images: List<String>,
        @SerializedName("file")
        val file: String,
        @SerializedName("likeCount")
        val likeCount: Int,
        @SerializedName("commentCount")
        val commentCount: Int,
        @SerializedName("writtenDate")
        val writtenDate: String
    )

    //콘텐츠 이벤트 게사판 전체 조회(페이징: 0페이지 부터 시작)
    data class ContentsEventResponse(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: List<ContentsEventResult>
    )

    data class ContentsEventResult(
        @SerializedName("writerId")
        val writerId: Int,
        @SerializedName("writerName")
        val writerName: String,
        @SerializedName("writerProfile")
        val writerProfile: String,
        @SerializedName("contentsId")
        val contentsId: Int,
        @SerializedName("title")
        val title: String,
        @SerializedName("content")
        val content: String,
        @SerializedName("images")
        val images: List<String>,
        @SerializedName("file")
        val file: String,
        @SerializedName("likeCount")
        val likeCount: Int,
        @SerializedName("commentCount")
        val commentCount: Int,
        @SerializedName("writtenDate")
        val writtenDate: String
    )

    //콘텐츠 카드뉴스 게시판 전체 조회(페이징: 0페이지 부터 시작)
    data class ContentsCardnewsResponse(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: List<ContentsCardnewsResult>
    )

    data class ContentsCardnewsResult(
        @SerializedName("writerId")
        val writerId: Int,
        @SerializedName("writerName")
        val writerName: String,
        @SerializedName("writerProfile")
        val writerProfile: String,
        @SerializedName("contentsId")
        val contentsId: Int,
        @SerializedName("title")
        val title: String,
        @SerializedName("content")
        val content: String,
        @SerializedName("images")
        val images: List<String>,
        @SerializedName("file")
        val file: String,
        @SerializedName("likeCount")
        val likeCount: Int,
        @SerializedName("commentCount")
        val commentCount: Int,
        @SerializedName("writtenDate")
        val writtenDate: String
    )

    //콘텐츠 게시글 상세조회
    data class ContentsBoardDetail(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: List<ContentsBoardDetailResult>
    )

    data class ContentsBoardDetailResult(
        @SerializedName("writerId")
        val writerId: Int,
        @SerializedName("writerName")
        val writerName: String,
        @SerializedName("writerProfile")
        val writerProfile: String,
        @SerializedName("contentsId")
        val contentsId: Int,
        @SerializedName("title")
        val title: String,
        @SerializedName("content")
        val content: String,
        @SerializedName("images")
        val images: List<String>,
        @SerializedName("file")
        val file: String,
        @SerializedName("likeCount")
        val likeCount: Int,
        @SerializedName("commentCount")
        val commentCount: Int,
        @SerializedName("writtenDate")
        val writtenDate: String
    )

    data class CommunityFreeResponse(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: List<CommunityFreeResult>
    )

    data class CommunityFreeResult(
        @SerializedName("writerId")
        val writerId: Int,
        @SerializedName("writerName")
        val writerName: String,
        @SerializedName("writerProfile")
        val writerProfile: String,
        @SerializedName("communityId")
        val communityId: Int,
        @SerializedName("title")
        val title: String,
        @SerializedName("content")
        val content: String,
        @SerializedName("images")
        val images: List<String>,
        @SerializedName("likeCount")
        val likeCount: Int,
        @SerializedName("commentCount")
        val commentCount: Int,
        @SerializedName("writtenDate")
        val writtenDate: String
    )

    //커뮤니티 피드게시판 전체 조회(페이징: 0페이지 부터 시작)
    data class CommunityFeedbackResponse(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: List<CommunityFeedbackResult>
    )

    data class CommunityFeedbackResult(
        @SerializedName("writerId")
        val writerId: Int,
        @SerializedName("writerName")
        val writerName: String,
        @SerializedName("writerProfile")
        val writerProfile: String,
        @SerializedName("communityId")
        val communityId: Int,
        @SerializedName("title")
        val title: String,
        @SerializedName("content")
        val content: String,
        @SerializedName("images")
        val images: List<String>,
        @SerializedName("likeCount")
        val likeCount: Int,
        @SerializedName("commentCount")
        val commentCount: Int,
        @SerializedName("writtenDate")
        val writtenDate: String
    )

    //커뮤니티 게시글 상세조회
    data class CommunityBoardDetailResponse(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: List<CommunityBoardDetailResult>
    )

    data class CommunityBoardDetailResult(
        @SerializedName("writerId")
        val writerId: Int,
        @SerializedName("writerName")
        val writerName: String,
        @SerializedName("writerProfile")
        val writerProfile: String,
        @SerializedName("communityId")
        val communityId: Int,
        @SerializedName("title")
        val title: String,
        @SerializedName("content")
        val content: String,
        @SerializedName("images")
        val images: List<String>,
        @SerializedName("likeCount")
        val likeCount: Int,
        @SerializedName("commentCount")
        val commentCount: Int,
        @SerializedName("writtenDate")
        val writtenDate: String
    )

    data class ResponseCommunity(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: List<Community>
    )

    data class Community(
        @SerializedName("writerId") val writerId: Long,
        @SerializedName("writerName") val writerName: String,
        @SerializedName("writerProfile") val writerProfile: String,
        @SerializedName("articleType") val articleType: String,
        @SerializedName("id") val id: Long,
        @SerializedName("title") val title: String,
        @SerializedName("content") val content: String,
        @SerializedName("images") val images: List<String>,
        @SerializedName("likeCount") val likeCount: Int,
        @SerializedName("commentCount") val commentCount: Int,
        @SerializedName("writtenDate") val writtenDate: String
    )

    data class ResponseBrand(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: List<Brand>
    )

    data class Brand(
        @SerializedName("brandId")
        val brandId: Int,
        @SerializedName("brandName")
        val brandName: String,
        @SerializedName("description")
        val description: String,
        @SerializedName("profileImage")
        val profileImage: String,
        @SerializedName("sequence")
        val sequence: Int
    )

    data class ResponseOtherAvatar(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result") val result: OtherAvatar
    )

    data class OtherAvatar(
        @SerializedName("memberId") val memberId: Long,
        @SerializedName("avatar") val avatar: String,
        @SerializedName("nickname") val nickname: String
    )


    data class ResponseWear(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: String
    )

    data class ResponseStatus(
        val isSuccess: Boolean,
        val code: String,
        val message: String,
        val result: String
    )
    data class ResponseMyInfo(
        val isSuccess: Boolean,
        val code: String,
        val message: String,
        val result: MyInfo
    )

    data class MyInfo(
        val memberId: Long,
        val nickname: String,
        val avatar: String,
        val point: Int
    )

    data class RequestNickname(
        val nickname : String
    )

    data class ResponseNickname(
        val isSuccess: Boolean,
        val code: String,
        val message: String,
        val result: Nickname
    )

    data class Nickname(
        val memberId: Long,
        val nickname: String
    )
    data class GetBrandHeader(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: GetBrandHeaderResult
    )

    data class GetBrandHeaderResult(
        @SerializedName("brandPreviewDto")
        val brandPreviewDto: brandPreviewDtoResult,
        @SerializedName("brandUserStatus")
        val brandUserStatus: brandUserStatusResult
    )

    data class brandPreviewDtoResult(
        @SerializedName("brand_id")
        val brand_id: Int,
        @SerializedName("brand_name")
        val brand_name: String,
        @SerializedName("brand_description")
        val brand_description: String,
        @SerializedName("brand_fan")
        val brand_fan: Int,
        @SerializedName("brand_profile")
        val brand_profile: String,
        @SerializedName("brand_background")
        val brand_background: String
    )

    data class brandUserStatusResult(
        @SerializedName("isFan")
        val isFan: Boolean,
        @SerializedName("join_date")
        val join_date: String,
        @SerializedName("fan_sequence")
        val fan_sequence: Int
    )

    data class GetHomeFragment(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: ResultGetHomeFragment
    )

    data class ResultGetHomeFragment(
        @SerializedName("mainBannersDtoList")
        val mainBannersDtoList: List<mainBannersDtoList>,
        @SerializedName("subBannersDtoList")
        val subBannersDtoList: List<subBannersDtoList>,
        @SerializedName("memberBrandListDtoList")
        val memberBrandListDtoList: List<memberBrandListDtoList>
    )

    data class mainBannersDtoList(
        @SerializedName("brandId")
        val brandId: Int,
        @SerializedName("brandBackgroundImage")
        val brandBackgroundImage: String
    )

    data class subBannersDtoList(
        @SerializedName("contentId")
        val contentId: Int,
        @SerializedName("images")
        val images: List<String>
    )

    data class memberBrandListDtoList(
        @SerializedName("brandId")
        val brandId: Int,
        @SerializedName("brandName")
        val brandName: String,
        @SerializedName("profileImage")
        val profileImage: String,
        @SerializedName("brandDescription")
        val brandDescription: String,
        @SerializedName("sequence")
        val sequence: Int
    )

    data class UnsubscribeBrand(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: String
    )

    data class SearchDetailBrands(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: ResultSearchDetailBrands
    )
    data class ResultSearchDetailBrands(
        @SerializedName("searchDetailBrandDto")
        val searchDetailBrandDto: List<searchDetailBrandDto>
    )
    data class searchDetailBrandDto(
        @SerializedName("brandId")
        val brandId: Int,
        @SerializedName("brandName")
        val brandName: String,
        @SerializedName("brandProfileImage")
        val brandProfileImage: String,
        @SerializedName("brandBackgroundImage")
        val brandBackgroundImage: String,
        @SerializedName("brandDescription")
        val brandDescription: String,
        @SerializedName("brandFans")
        val brandFans: Int
    )


    data class SearchDetailUser(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: SearchDetailUserResult
    )

    data class SearchDetailUserResult(
        @SerializedName("searchDetailUserDto")
        val searchDetailUserDto: List<SearchDetailUserDto>
    )

    data class SearchDetailUserDto(
        @SerializedName("userId")
        val userId: Int,
        @SerializedName("userName")
        val userName: String,
        @SerializedName("userAvatar")
        val userAvatar: String
    )

    data class SearchDetailContents(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: SearchDetailContentsResult
    )

    data class SearchDetailContentsResult(
        @SerializedName("searchDetailContentsDto")
        val searchDetailContentsDto: List<SearchDetailContentsDto>
    )

    data class SearchDetailContentsDto(
        @SerializedName("contentsId")
        val contentsId: Int,
        @SerializedName("contentsTitle")
        val contentsTitle: String,
        @SerializedName("content")
        val content: String,
        @SerializedName("images")
        val images: List<String>,
        @SerializedName("likeCount")
        val likeCount: Int,
        @SerializedName("commentCount")
        val commentCount: Int,
        @SerializedName("writerId")
        val writerId: Int,
        @SerializedName("writerName")
        val writerName: String,
        @SerializedName("writerProfile")
        val writerProfile: String,
        @SerializedName("createdDate")
        val createdDate: String
    )

    data class SearchDetailUserAvatarAndPoints(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: SearchDetailUserAvatarAndPointsResult
    )

    data class SearchDetailUserAvatarAndPointsResult(
        @SerializedName("memberId")
        val memberId: Int,
        @SerializedName("memberAvatar")
        val memberAvatar: String,
        @SerializedName("memberPoints")
        val memberPoints: Int
    )

    data class SearchDetailAvatarStoreBody(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: SearchDetailAvatarStoreBodyResult
    )

    data class SearchDetailAvatarStoreBodyResult(
        @SerializedName("searchDetailAvatarStoreBodyDto")
        val searchDetailAvatarStoreBodyDto: List<SearchDetailAvatarStoreBodyDto>
    )

    data class SearchDetailAvatarStoreBodyDto(
        @SerializedName("itemId")
        val itemId: Int,
        @SerializedName("itemsName")
        val itemsName: String,
        @SerializedName("itemPart")
        val itemPart: String,
        @SerializedName("brandName")
        val brandName: String,
        @SerializedName("itemImage")
        val itemImage: String,
        @SerializedName("itemDescription")
        val itemDescription: String,
        @SerializedName("itemPrice")
        val itemPrice: Int
    )

    //서현 시작
    //page4
    // 검색메인
    data class ResponseSearchMain(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: SearchMainResult
    )

    data class SearchMainResult(
        @SerializedName("searchMainBrandDto")
        val searchMainBrandDto: List<BrandDto>,
        @SerializedName("searchMainUserDto")
        val searchMainUserDto: List<UserDto>,
        @SerializedName("searchMainContentsDto")
        val searchMainContentsDto: List<ContentsDto>,
        @SerializedName("searchMainAvatarStoreDto")
        val searchMainAvatarStoreDto: List<AvatarStoreDto>
    )

    // 검색 메인 페이지에서 사용될 데이터 클래스들을 정의합니다.
    data class BrandDto(
        @SerializedName("brandId") val brandId: Long,
        @SerializedName("brandName") val brandName: String,
        @SerializedName("brandProfileImage") val brandProfileImage: String,
        @SerializedName("brandDescription") val brandDescription: String
    )

    data class UserDto(
        @SerializedName("userId") val userId: Long,
        @SerializedName("userName") val userName: String,
        @SerializedName("userAvatar") val userAvatar: String
    )

    data class ContentsDto(
        @SerializedName("contentsId") val contentsId: Long,
        @SerializedName("contentsTitle") val contentsTitle: String,
        @SerializedName("content") val content: String,
        @SerializedName("images") val images: List<String>,
        @SerializedName("likeCount") val likeCount: Long,
        @SerializedName("commentCount") val commentCount: Long,
        @SerializedName("writerId") val writerId: Long,
        @SerializedName("writerName") val writerName: String,
        @SerializedName("writerProfile") val writerProfile: String,
        @SerializedName("createdDate") val createdDate: String
    )

    data class AvatarStoreDto(
        @SerializedName("itemId") val itemId: Long,
        @SerializedName("itemsName") val itemsName: String,
        @SerializedName("itemPart") val itemPart: String,
        @SerializedName("brandName") val brandName: String,
        @SerializedName("itemImage") val itemImage: String,
        @SerializedName("itemDescription") val itemDescription: String,
        @SerializedName("itemPrice") val itemPrice: Long
    )


    //미션 관련 API : 브랜드 추가 미션 도전
    data class ResponseChallengeAddMission(
        @SerializedName("isSuccess") val isSuccess: Boolean,
        @SerializedName("code") val code: String,
        @SerializedName("message") val message: String,
        @SerializedName("result") val result: ChallengeAddMissionResult?
    )

    data class ChallengeAddMissionResult(
        @SerializedName("missionSuccess") val missionSuccess: Boolean,
        @SerializedName("id") val id: Long
    )

    //미션 관련 API : 브랜드 추가 미션 성공
    data class ResponseMissionSuccess(
        @SerializedName("isSuccess") val isSuccess: Boolean,
        @SerializedName("code") val code: String,
        @SerializedName("message") val message: String,
        @SerializedName("result") val result: MissionAddSuccessResult?
    )

    data class MissionAddSuccessResult(
        @SerializedName("missionSuccess") val missionSuccess: Boolean,
        @SerializedName("id") val id: Long
    )

    //미션 관련 API : 포인트 미션 리스트
    data class ResponseMissionList(
        @SerializedName("isSuccess") val isSuccess: Boolean,
        @SerializedName("code") val code: String,
        @SerializedName("message") val message: String,
        @SerializedName("result") val result: MissionListResult
    )

    data class MissionListResult(
        @SerializedName("memberMissionList") val memberMissionList: List<Mission>,
        @SerializedName("missionList") val missionList: List<Mission>
    )

    data class Mission(
        @SerializedName("missionId") val missionId: Long,
        @SerializedName("missionName") val missionName: String,
        @SerializedName("missionPoints") val missionPoints: Long,
        @SerializedName("missionType") val missionType: String,
        @SerializedName("missionStatus") val missionStatus: String,
        @SerializedName("missionImage") val missionImage: String,
        @SerializedName("brandId") val brandId: Long

    )
    //서현 끝


    data class TryBrandAdditionMission(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: TryBrandAdditionMissionResult
    )

    data class TryBrandAdditionMissionResult(
        @SerializedName("missionSuccess")
        val missionSuccess: Boolean,
        @SerializedName("id")
        val id: Int
    )

    data class TrySurveyMission(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: TrySurveyMissionResult
    )

    data class TrySurveyMissionResult(
        @SerializedName("missionId")
        val missionId: Int,
        @SerializedName("survey")
        val survey: List<Survey>
    )

    data class Survey(
        @SerializedName("surveyQuestionId")
        val surveyQuestionId: Int,
        @SerializedName("surveyQuestion")
        val surveyQuestion: String,
        @SerializedName("surveyQuestionType")
        val surveyQuestionType: String,
        @SerializedName("surveyExamples")
        val surveyExamples: List<SurveyExamples>
    )

    data class SurveyExamples(
        @SerializedName("surveyExampleId")
        val surveyExampleId: Int,
        @SerializedName("surveyExample")
        val surveyExample: String
    )


    data class CompleteSurveyMissionSuccess(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: List<SurveyMissionEmptyResult>
    )
    data class SurveyMissionEmptyResult (
        val surveyResult: String
    )

    data class RequestSurveyMission (
        val requestSurveyMission: List<QuestionResponse>
    )
    data class QuestionResponse(
        val surveyQuestionID: Int,
        val surveyQuestionType: String,
        val response: String
    )

    data class PointHistory(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: PointHistoryResult
    )

    data class PointHistoryResult(
        @SerializedName("result")
        val result: List<UserPointHistory>
    )

    data class UserPointHistory(
        @SerializedName("history")
        val history: String,
        @SerializedName("points")
        val points: Int,
        @SerializedName("date")
        val date: String
    )



    data class PurchaseItem(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: String
    )

    data class MakeChatRoomResponse(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: String
    )

    data class GetMessagesResponse(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: GetMessagesResponseResult
    )

    data class GetMessagesResponseResult(
        @SerializedName("messageDtoList")
        val messageDtoList: List<MessageDto>,
        @SerializedName("lastIndex")
        val lastIndex: Int
    )

    data class MessageDto(
        @SerializedName("memberId")
        val memberId: Int,
        @SerializedName("memberName")
        val memberName: String,
        @SerializedName("memberAvatar")
        val memberAvatar: String,
        @SerializedName("content")
        val content: String,
        @SerializedName("time")
        val time: String
    )

    data class SendMessagesRequest(
        val content: String
    )

    data class SendMessagesResponse(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: String
    )

}
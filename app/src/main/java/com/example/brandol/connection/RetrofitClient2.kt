package com.example.brandol.connection

import com.google.gson.annotations.SerializedName

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

    data class ResponseMyItem(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: ItemResult
    )

    data class ItemResult(
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
        @SerializedName("price")
        val price : Int,
        @SerializedName("createdAt")
        val createdAt : String,
        @SerializedName("wearing")
        val wearing : Boolean,
    )

    //page4
    // 검색메인
    data class ResponseSearchMain(
        @SerializedName("isSuccess") val isSuccess: Boolean,
        @SerializedName("code") val code: String,
        @SerializedName("message") val message: String,
        @SerializedName("result") val result: SearchMainResult
    )

    data class SearchMainResult(
        @SerializedName("searchMainBrandDto") val searchMainBrandDto: List<BrandDto>,
        @SerializedName("searchMainUserDto") val searchMainUserDto: List<UserDto>,
        @SerializedName("searchMainContentsDto") val searchMainContentsDto: List<ContentsDto>,
        @SerializedName("searchMainAvatarStoreDto") val searchMainAvatarStoreDto: List<AvatarStoreDto>
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

}
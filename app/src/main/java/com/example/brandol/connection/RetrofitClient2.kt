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
        val brandName: String,
        @SerializedName("itemName")
        val itemName: String,
        @SerializedName("part")
        val part: String,
        @SerializedName("description")
        val description: String,
        @SerializedName("image")
        val image: String,
        @SerializedName("price")
        val price: Int,
        @SerializedName("createdAt")
        val createdAt: String,
        @SerializedName("wearing")
        val wearing: Boolean,
    )

    data class ResponseCommunity(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: Community
    )

    data class Community(
        val writerId: Long,
        val writerName: String,
        val writerProfile: String,
        val articleType: String,
        val id: Long,
        val title: String,
        val content: String,
        val images: List<String>,
        val likeCount: Int,
        val commentCount: Int,
        val writtenDate: String
    )

    data class ResponseBrand(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: Brand
    )

    data class Brand(
        @SerializedName("brandId") val brandId: Int,
        @SerializedName("brandName") val brandName: String,
        @SerializedName("description") val description: String,
        @SerializedName("profileImage") val profileImage: String,
        @SerializedName("sequence") val sequence: Int
    )

    data class ReequestWear(
        @SerializedName("wearingItemIdList")
        val wearingItemIdList: List<Long>,
        @SerializedName("avatarImage")
        val avatarImage: String
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


}
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
        @SerializedName("sequence")
        val sequence: Int
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


    data class GetUserAvatarAndPoints(
        @SerializedName("isSuccess")
        val isSuccess: Boolean,
        @SerializedName("code")
        val code: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("result")
        val result: GetUserAvatarAndPointsResult
    )

    data class GetUserAvatarAndPointsResult(
        @SerializedName("memberId")
        val memberId: Int,
        @SerializedName("memberAvatar")
        val memberAvatar: String,
        @SerializedName("memberPoints")
        val memberPoints: Int
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

}
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
        @SerializedName("termsIdlist")
        val termsIdList: List<Long>,
        @SerializedName("nickname")
        val nickname : String,
        @SerializedName("gender")
        val gender : String,
        @SerializedName("age")
        val age: Short
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
        val memberId: Int,
        @SerializedName("signUp")
        val signUp: Boolean
    )
}
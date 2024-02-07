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
        val isSuccess: Boolean,
        val code: String,
        val message: String,
        val result: LoginResult
    )

    data class LoginResult(
        val accessToken: String,
        val refreshToken: String
    )
}
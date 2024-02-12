package com.example.brandol.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.brandol.MainActivity
import com.example.brandol.R
import com.example.brandol.connection.RetrofitObject
import com.example.brandol.connection.RetrofitClient2
import com.example.brandol.databinding.ActivityLoginStartBinding
import com.kakao.sdk.auth.LoginClient
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.AuthErrorCause
import com.kakao.sdk.user.UserApiClient
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response

class LoginStartActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginStartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 애니메이션을 적용하지 않도록 설정합니다.
        overridePendingTransition(0, 0)

//        binding.startKakaotalkB.setOnClickListener {
//            val intent = Intent(this, TermsActivity::class.java)
//            startActivity(intent)
//        }
        // 로그인 정보 확인
//        checkLoginInfo()

        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                when {
                    error.toString() == AuthErrorCause.AccessDenied.toString() -> {
                        Toast.makeText(this, "접근이 거부 됨(동의 취소)", Toast.LENGTH_SHORT).show()
                    }

                    error.toString() == AuthErrorCause.InvalidClient.toString() -> {
                        Toast.makeText(this, "유효하지 않은 앱", Toast.LENGTH_SHORT).show()
                    }

                    error.toString() == AuthErrorCause.InvalidGrant.toString() -> {
                        Toast.makeText(this, "인증 수단이 유효하지 않아 인증할 수 없는 상태", Toast.LENGTH_SHORT)
                            .show()
                    }

                    error.toString() == AuthErrorCause.InvalidRequest.toString() -> {
                        Toast.makeText(this, "요청 파라미터 오류", Toast.LENGTH_SHORT).show()
                    }

                    error.toString() == AuthErrorCause.InvalidScope.toString() -> {
                        Toast.makeText(this, "유효하지 않은 scope ID", Toast.LENGTH_SHORT).show()
                    }

                    error.toString() == AuthErrorCause.Misconfigured.toString() -> {
                        Toast.makeText(this, "설정이 올바르지 않음(android key hash)", Toast.LENGTH_SHORT)
                            .show()
                    }

                    error.toString() == AuthErrorCause.ServerError.toString() -> {
                        Toast.makeText(this, "서버 내부 에러", Toast.LENGTH_SHORT).show()
                    }

                    error.toString() == AuthErrorCause.Unauthorized.toString() -> {
                        Toast.makeText(this, "앱이 요청 권한이 없음", Toast.LENGTH_SHORT).show()
                    }

                    else -> { // Unknown
                        Toast.makeText(this, "기타 에러", Toast.LENGTH_SHORT).show()
                    }
                }
            } else if (token != null) {
                Toast.makeText(this, "로그인에 성공하였습니다.", Toast.LENGTH_SHORT).show()
                //로그인 했으니 여기서 이메일 이름을 받아옴
                UserApiClient.instance.me { user, error ->
                    if (error != null) {
                        Log.e("LHJ", "사용자 정보 요청 실패", error)
                    } else if (user != null) {
                        Log.d("LHJ", "사용자 정보 요청 성공")
//                        val email = user.kakaoAccount?.email
//                        val name = user.kakaoAccount?.profile?.nickname 진짜 코드
                        val email = "test"
                        val name = "호지니"
                        //이메일 이름 보내서 서버와 연결
                        Log.d("email", email.toString())
                        loginServer(email!!, name!!)

                    }
                }
//                val intent = Intent(this, TermsActivity::class.java)
//                startActivity(intent)
            }

        }


        val kakao_login_button = findViewById<Button>(R.id.start_kakaotalk_b) // 로그인 버튼

        kakao_login_button.setOnClickListener {
            if (LoginClient.instance.isKakaoTalkLoginAvailable(this)) {
                LoginClient.instance.loginWithKakaoTalk(this, callback = callback)
            } else {
                LoginClient.instance.loginWithKakaoAccount(this, callback = callback)
            }

//            binding.startKakaotalkB.setOnClickListener {
//                val intent = Intent(this, TermsActivity::class.java)
//                startActivity(intent)
//            }
        }
    }

    private fun loginServer(email: String,name: String): Boolean {
        val call = RetrofitObject.getRetrofitService.login(RetrofitClient2.RequestLogin(email, name))
        call.enqueue(object : Callback<RetrofitClient2.ResponseLogin> {
            override fun onResponse(
                call: Call<RetrofitClient2.ResponseLogin>,
                response: Response<RetrofitClient2.ResponseLogin>
            ) {
                Log.d("LHJ", response.toString())
                if (response.isSuccessful) {
                    val response = response.body()
                    Log.d("LHJ", response.toString())
                    if (response != null) {
                        if (response.isSuccess) {
                            val accessToken = response.result.accessToken
                            val refreshToken = response.result.refreshToken
                            saveTokenInfo(this@LoginStartActivity,accessToken,refreshToken)
                            val intent = Intent(this@LoginStartActivity, MainActivity::class.java)
                            startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                            finish()
                        } else {
                            val intent = Intent(this@LoginStartActivity, TermsActivity::class.java)
                            startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                            finish()
                        }
                    }

                }
            }

            override fun onFailure(
                call:
                Call<RetrofitClient2.ResponseLogin>, t: Throwable
            ) {
                val errorMessage = "Call Failed: ${t.message}"
                Log.d("LHJ", errorMessage)
            }
        })
        return false
    }

//    private fun checkLoginInfo() {
//        UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
//            if (error != null) {
//                Toast.makeText(this, "토큰 정보 보기 실패", Toast.LENGTH_SHORT).show()
//            } else if (tokenInfo != null) {
//                Toast.makeText(this, "토큰 정보 보기 성공", Toast.LENGTH_SHORT).show()
////                val intent = Intent(this, MainActivity::class.java)
////                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
////                finish()
//            }
//        }
//    }
    private fun saveTokenInfo(context: Context, accessToken: String?,refreshtoken:String?)
    {
        val sharedPref = context.getSharedPreferences("Brandol", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            accessToken?.let { putString("accessToken", it) }
            Log.d("LHJTOKEN",accessToken.toString())
            refreshtoken?.let { putString("refreshtoken", it) }
            apply()
        }
    }
}


package com.example.brandol

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.kakao.sdk.common.util.Utility

class SplashActivity : AppCompatActivity() {

    private val SPLASH_DELAY: Long = 1000 // 2초 후에 메인 액티비티로 이동

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            // 스플래시 화면이 끝난 후 실행할 코드
            val mainIntent = Intent(this@SplashActivity, LoginStartActivity::class.java)
            startActivity(mainIntent)
            finish() // 현재 액티비티 종료
        }, SPLASH_DELAY)
    }
}

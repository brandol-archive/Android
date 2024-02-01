package com.example.brandol

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class LoginStartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_start)

        // 애니메이션을 적용하지 않도록 설정합니다.
        overridePendingTransition(0, 0)

//        start_kakaotalk_b.setOnClickListener {
//            // start_kakaotalk_b 버튼이 클릭되면 LoginStartActivity로 이동합니다.
//            val intent = Intent(this, LoginStartActivity::class.java)
//            startActivity(intent)
//        }
    }
}
package com.example.brandol

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.brandol.databinding.ActivityChattingBinding
import com.example.brandol.databinding.ActivityLoginStartBinding

class LoginStartActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 애니메이션을 적용하지 않도록 설정합니다.
        overridePendingTransition(0, 0)

        binding.startKakaotalkB.setOnClickListener {
            val intent = Intent(this, TermsActivity::class.java)
            startActivity(intent)
        }
    }
}
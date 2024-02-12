package com.example.brandol

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.brandol.Home.HomeFragment
import com.example.brandol.avatar.AvatarFragment
import com.example.brandol.databinding.ActivityMainBinding
import com.example.brandol.mypage.MypageFragment
import com.kakao.sdk.user.UserApiClient

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //바텀 네비게이션
        initBottomnavigation()

    }

    private fun loadUserInfo() {
        UserApiClient.instance.me { user, error ->
            if (error != null) {
                Log.e("LHJ", "사용자 정보 요청 실패", error)
            } else if (user != null) {
                Log.i(
                    "LHJ", "사용자 정보 요청 성공" +
                            "\n회원번호: ${user.id}" +
                            "\n이메일: ${user.kakaoAccount?.email}" +
                            "\n닉네임: ${user.kakaoAccount?.profile?.nickname}" +
                            "\n프로필사진: ${user.kakaoAccount?.profile?.thumbnailImageUrl}"
                )
            }
        }
    }


    private fun initBottomnavigation() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, HomeFragment())
            .commitAllowingStateLoss()

        binding.mainBnv.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.page_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, HomeFragment())
                        //.replace(R.id.main_frm, BrandManagementHome())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }

                R.id.page_search -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, SearchFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }

                R.id.page_point -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, PointFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }

                R.id.page_avatar -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, AvatarFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }

                R.id.page_mypage -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, MypageFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }
}
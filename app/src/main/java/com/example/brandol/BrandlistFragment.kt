package com.example.brandol

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.brandol.databinding.FragmentBrandlistBinding
import com.example.brandol.databinding.FragmentLinktreeBinding
import com.google.android.material.tabs.TabLayoutMediator

class BrandlistFragment: AppCompatActivity() {
    lateinit var binding: FragmentBrandlistBinding
    private lateinit var callback : OnBackPressedCallback
    lateinit var moveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentBrandlistBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewPager()

        binding.brandlistAddlistBtn.setOnClickListener {
            //list 추가 날짜 및 팬 번호 텍스트 변경
            binding.brandlistAddlistTv.text = "2024년 01월 06일  |  6번째 팬"
        }

        binding.brandlistLinkBtn.setOnClickListener {
            // LinktreeFragment로 전환
            val linktreeFragment = LinktreeFragment()
            supportFragmentManager.beginTransaction()
                .replace(android.R.id.content, linktreeFragment)  // android.R.id.content는 전체 화면을 나타냅니다.
                .addToBackStack(null)
                .commit()
        }

        //LinktreeFragment가 열린 상태에서 뒤로 가기 버튼을 누르면 이전 화면(BrandlistFragment)으로 돌아오기
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // 프래그먼트 스택이 비어있지 않다면 뒤로 가기 동작 수행
                if (supportFragmentManager.backStackEntryCount > 0) {
                    supportFragmentManager.popBackStack()
                } else {
                    // 프래그먼트 스택이 비어있다면 앱 종료
                    finish()
                }
            }
        }
        onBackPressedDispatcher.addCallback(this, callback)

    }

    private fun initViewPager() {
        //ViewPager2 Adapter 셋팅
        var categoryAdapter = CategoryAdapter(this)
        categoryAdapter.addFragment(FandomFragment())
        categoryAdapter.addFragment(ContentsFragment())
        categoryAdapter.addFragment(CommuFragment())
        categoryAdapter.addFragment(MyFragment())

        //Adapter 연결
        binding.brandlistCategoryVp.apply {
            adapter = categoryAdapter

            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                }
            })
        }

        //ViewPager, TabLayout 연결
        TabLayoutMediator(binding.brandlistCategorynavTl, binding.brandlistCategoryVp) { tab, position ->
            Log.e("YMC", "ViewPager position: ${position}")
            when (position) {
                0 -> tab.text = "FANDOM"
                1 -> tab.text = "컨텐츠"
                2 -> tab.text = "커뮤니티"
                3 -> tab.text = "MY"
            }
        }.attach()
    }
}
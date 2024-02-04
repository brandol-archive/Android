package com.example.brandol

import android.os.Bundle
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.brandol.databinding.FragmentBrandinfoBinding
import com.google.android.material.tabs.TabLayoutMediator

class BrandinfoFragment: AppCompatActivity() {
    lateinit var binding: FragmentBrandinfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentBrandinfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewPager()

        //브랜드 배경 사진 및 로고 설정
        binding.brandinfoBrandinfoLl.setImageResource(R.drawable.iv_brandinfo_ex)
        binding.brandinfoBrandlogoIv.setImageResource(R.drawable.iv_brandlogo_ex)

        //브랜드 정보 관련 텍스트 설정
        binding.brandinfoFancntnumTv.text = "10"  //추후 수정 필요
        binding.brandinfoBrandintroTv.text = "BRANDOL"  //추후 수정 필요
        binding.brandinfoBrandnameTv.text = "브랜드 팬덤 커뮤니티"  //추후 수정 필요

        binding.brandinfoAddlistBtn.setOnClickListener {
            //list 추가 날짜 및 팬 번호 텍스트 변경 -> 추후에 날짜와 팬 순서 숫자 받아서 다시 해야 함
            binding.brandinfoAddlistTv.text = "2024년 01월 06일  |  6번째 팬"
            binding.brandinfoAddlistBtn.setBackgroundColor(getColor(R.color.white))
        }

        binding.brandinfoLinkBtn.setOnClickListener {
            // LinktreeFragment로 전환
            val linktreeFragment = LinktreeFragment()
            supportFragmentManager.beginTransaction()
                .replace(android.R.id.content, linktreeFragment)  // android.R.id.content는 전체 화면을 나타냄
                .addToBackStack(null)
                .commit()
        }

//        //LinktreeFragment가 열린 상태에서 뒤로 가기 버튼을 누르면 이전 화면(BrandinfoFragment)으로 돌아오기
//        callback = object : OnBackPressedCallback(true) {
//            override fun handleOnBackPressed() {
//                // 프래그먼트 스택이 비어있지 않다면 뒤로 가기 동작 수행
//                if (supportFragmentManager.backStackEntryCount > 0) {
//                    supportFragmentManager.popBackStack()
//                } else {
//                    // 프래그먼트 스택이 비어있다면 앱 종료
//                    finish()
//                }
//            }
//        }
//        onBackPressedDispatcher.addCallback(this, callback)

    }

    //브랜드 카테고리 생성
    private fun initViewPager() {
        //ViewPager2 Adapter setting
        var categoryAdapter = CategoryAdapter(this)
        categoryAdapter.addFragment(FandomFragment())
        categoryAdapter.addFragment(ContentsFragment())
        categoryAdapter.addFragment(CommuFragment())
        categoryAdapter.addFragment(MyFragment())

        //Adapter 연결
        binding.brandinfoCategoryVp.apply {
            adapter = categoryAdapter

            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                }
            })
        }

        //ViewPager, TabLayout 연결
        TabLayoutMediator(binding.brandinfoCategorynavTl, binding.brandinfoCategoryVp) { tab, position ->
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
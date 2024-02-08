package com.example.brandol

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.brandol.adaptor.SearchPagerAdapter
import com.example.brandol.databinding.FragmentSearchBarBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class SearchBarFragment : Fragment() {

    private lateinit var binding: FragmentSearchBarBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSearchBarBinding.inflate(inflater, container, false)

        // TabLayout 초기화
        val tabLayout: TabLayout = binding.searchBarTb

        // 탭의 제목 리스트
        val tabTitles = arrayListOf("브랜드", "유저", "콘텐츠", "아바타 스토어")

        // ViewPager2 초기화
        val viewPager: ViewPager2 = binding.searchBarVp
        val adapter = SearchPagerAdapter(childFragmentManager, lifecycle)
        viewPager.adapter = adapter

        // TabLayout에 ViewPager2 연결
        TabLayoutMediator(binding.searchBarTb, binding.searchBarVp) {
            tab, position ->
            tab.text = tabTitles[position]
        }.attach()

        // Tab이 선택되었을 때의 색상 변경
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                // 선택된 탭의 색상 변경
                tab?.text?.let {
                    when (it) {
                        "브랜드" -> tab.view.setBackgroundColor(Color.BLUE)
                        "유저" -> tab.view.setBackgroundColor(Color.BLUE)
                        "콘텐츠" -> tab.view.setBackgroundColor(Color.BLUE)
                        "아바타 스토어" -> tab.view.setBackgroundColor(Color.BLUE)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // 선택 해제된 탭의 색상 초기화
                tab?.view?.setBackgroundColor(Color.TRANSPARENT)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // 재선택된 탭의 색상 변경 (여기서는 동일하게 처리)
                onTabSelected(tab)
            }
        })

        return binding.root
    }
}


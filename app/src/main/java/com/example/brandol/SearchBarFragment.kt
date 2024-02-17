package com.example.brandol

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.brandol.adaptor.SearchPagerAdapter
import com.example.brandol.connection.RetrofitClient2
import com.example.brandol.connection.RetrofitClient2.*
import com.example.brandol.connection.RetrofitObject
import com.example.brandol.databinding.FragmentSearchBarBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

        // 탭의 선택된 상태에 대한 스타일 정의
        binding.searchBarTb.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.apply {
                    // 선택된 탭의 텍스트 색상 변경
                    view?.findViewById<TextView>(com.google.android.material.R.id.title)?.setTextColor(
                        ContextCompat.getColor(requireContext(), R.color.puple)
                    )
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // 선택되지 않은 탭의 텍스트 색상 변경 (원래의 색 또는 다른 색)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // 탭이 이미 선택된 상태에서 다시 선택될 때의 동작
            }
        })


        return binding.root

    }


}


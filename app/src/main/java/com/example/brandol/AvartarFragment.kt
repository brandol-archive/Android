package com.example.brandol

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.brandol.databinding.FragmentAvartarBinding
import com.google.android.material.tabs.TabLayoutMediator


class AvartarFragment : Fragment() {
    lateinit var binding: FragmentAvartarBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAvartarBinding.inflate(inflater, container, false)
        val profile = arguments?.getInt("profile")

        //아이템리스트 보여주기
        showtablayout()
        binding.avartarChattingBtn.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.main_frm,MessageFragment())
                .addToBackStack(null)
                .commit()
        }
        if (profile != null) {
            binding.avartarRealAvartar.setImageResource(profile)
        }
        //채팅 온 갯수 앞으로 보내기
        binding.avartarChattingQuantity.bringToFront()
        return binding.root
    }

    private fun showtablayout() {
        var tabElement = arrayOf("전체", "헤어", "피부", "한벌", "싱의", "하의", "신발")

        //ViewPager2에 TabFragments 전달(Adapter pattern)
        val fragments: List<Fragment> = ArrayList()
        fragments.apply {
            AvartarTabFragment.newInstance(tabElement[0])
            AvartarTabFragment.newInstance(tabElement[1])
            AvartarTabFragment.newInstance(tabElement[2])
            AvartarTabFragment.newInstance(tabElement[3])
            AvartarTabFragment.newInstance(tabElement[4])
            AvartarTabFragment.newInstance(tabElement[5])
            AvartarTabFragment.newInstance(tabElement[6])
        }
        //뷰페이저 어댑터 연결
        val pagerAdapter = AvartarVPAdapter(childFragmentManager, lifecycle)
        binding.avartarItemlistVp.adapter = pagerAdapter

        //탭 레이아웃과 뷰페이저 연동
        TabLayoutMediator(binding.avartarCategoryTl, binding.avartarItemlistVp) { tab, position ->
            // 탭의 텍스트 설정
            tab.text = tabElement[position]
        }.attach()
    }


}
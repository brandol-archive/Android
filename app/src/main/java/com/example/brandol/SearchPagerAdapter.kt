package com.example.brandol

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class SearchPagerAdapter(fm: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fm, lifecycle) {

    override fun getItemCount(): Int {
        // ViewPager에 표시할 Fragment 개수 반환
        return 1
    }

    override fun createFragment(position: Int): Fragment {
        // position에 따라 다른 Fragment 반환
        return CatagoryFragment()
    }
}
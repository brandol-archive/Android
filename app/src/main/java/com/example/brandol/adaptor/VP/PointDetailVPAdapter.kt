package com.example.brandol.adaptor.VP

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.brandol.PointUseGetFragment
import com.example.brandol.PointUseTotalFragment
import com.example.brandol.PointUseUseFragment

class PointDetailVPAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3 // 탭의 개수

    override fun createFragment(position: Int): Fragment {
        // 각 탭에 맞는 Fragment를 반환
        return when (position) {
            0 -> PointUseTotalFragment()
            1 -> PointUseGetFragment()
            2 -> PointUseUseFragment()
            else -> throw IllegalArgumentException("Invalid position: $position")
        }
    }
}

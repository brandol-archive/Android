package com.example.brandol.adaptor.VP

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.brandol.opavatar.OpBrandListFragment
import com.example.brandol.opavatar.OpItemListFragment
import com.example.brandol.opavatar.OpPostListFragment

class OpponentVPAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return 3  // 탭의 수에 맞게 조절
    }

    override fun createFragment(position: Int): Fragment {
        // 각 탭에 해당하는 프래그먼트를 반환
        return when (position) {
            0 -> OpBrandListFragment()
            1 -> OpItemListFragment()
            2 -> OpPostListFragment()
            else -> throw IllegalArgumentException("Invalid position")
        }
    }
}
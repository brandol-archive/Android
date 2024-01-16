package com.example.brandol

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class AvartarVPAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    //CategoryFragment 전환에 사용.
    override fun getItemCount(): Int {
        return 7  // 탭의 수에 맞게 조절
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> AvartarTabFragment()
            1 -> AvartarTabFragment()
            2 -> AvartarTabFragment()
            3 -> AvartarTabFragment()
            4 -> AvartarTabFragment()
            5 -> AvartarTabFragment()
            6 -> AvartarTabFragment()
            else -> throw IllegalArgumentException("Invalid position")
        }
    }
}


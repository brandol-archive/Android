package com.example.brandol.adaptor.VP

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.brandol.avatar.AvatarTabFragment

class AvatarVPAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    val tabElement = arrayOf("전체","피부","헤어", "상의", "하의", "신발")
    //CategoryFragment 전환에 사용.
    override fun getItemCount(): Int {
        return 6  // 탭의 수에 맞게 조절
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0-> AvatarTabFragment.newInstance(tabElement[0])
            1-> AvatarTabFragment.newInstance(tabElement[1])
            2-> AvatarTabFragment.newInstance(tabElement[2])
            3-> AvatarTabFragment.newInstance(tabElement[3])
            4-> AvatarTabFragment.newInstance(tabElement[4])
            5-> AvatarTabFragment.newInstance(tabElement[5])
            else -> throw IllegalArgumentException("Invalid position")
        }
    }
}




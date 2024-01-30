package com.example.brandol

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class AvartarVPAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    val tabElement = arrayOf("전체", "헤어", "피부", "한벌", "상의", "하의", "신발", "기타")
    //CategoryFragment 전환에 사용.
    override fun getItemCount(): Int {
        return 8  // 탭의 수에 맞게 조절
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0->AvartarTabFragment.newInstance(tabElement[0])
            1->AvartarTabFragment.newInstance(tabElement[1])
            2->AvartarTabFragment.newInstance(tabElement[2])
            3->AvartarTabFragment.newInstance(tabElement[3])
            4->AvartarTabFragment.newInstance(tabElement[4])
            5->AvartarTabFragment.newInstance(tabElement[5])
            6->AvartarTabFragment.newInstance(tabElement[6])
            7->AvartarTabFragment.newInstance(tabElement[7])
            else -> throw IllegalArgumentException("Invalid position")
        }
    }
}




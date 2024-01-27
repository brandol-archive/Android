package com.example.brandol

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class SearchPagerAdapter(fragment: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragment, lifecycle) {

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> SearchBrandFragment()
            1 -> SearchUserFragment()
            2 -> SearchContentFragment()
            3 -> SearchAvartarFragment()
            else -> throw IllegalArgumentException("Invalid position: $position")
        }
    }
}
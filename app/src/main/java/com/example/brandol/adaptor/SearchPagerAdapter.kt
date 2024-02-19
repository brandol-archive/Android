package com.example.brandol.adaptor

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.brandol.SearchAvartarFragment
import com.example.brandol.SearchBrandFragment
import com.example.brandol.SearchContentFragment
import com.example.brandol.SearchUserFragment
import com.example.brandol.adaptor.VP.SearchBrandAdapter
import com.example.brandol.adaptor.VP.SearchContentsAdapter
import com.example.brandol.adaptor.VP.SearchUserAdapter

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


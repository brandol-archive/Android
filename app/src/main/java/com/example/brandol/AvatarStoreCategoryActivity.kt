package com.example.brandol

import AvatarStoreAdapter
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class AvatarStoreCategoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_avatarstore_category)


        val viewPager: ViewPager2 = findViewById(R.id.avatar_store_vp)
        val tabLayout: TabLayout = findViewById(R.id.avatar_store_tabs)

        val adapter = AvatarStoreAdapter(this)
        adapter.addFragment(OverallFragment(), "전체")
        adapter.addFragment(HairFragment(), "헤어")
        adapter.addFragment(SkinFragment(), "피부")
        adapter.addFragment(OutfitFragment(), "한 벌")
        adapter.addFragment(TopFragment(), "상의")

        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = adapter.titleList[position]
        }.attach()


    }
}

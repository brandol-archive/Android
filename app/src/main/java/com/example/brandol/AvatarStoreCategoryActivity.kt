package com.example.brandol

import AvatarStoreAdapter
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.brandol.databinding.ActivityAvatarstoreCategoryBinding
import com.example.brandol.databinding.ActivityBrandCategoryBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class AvatarStoreCategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAvatarstoreCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAvatarstoreCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)


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

        binding.btnBackAvatarstoreCategory.setOnClickListener {
            // 원하는 동작을 여기에 추가
            finish()
        }


    }
}

package com.example.brandol

import BrandCategoryAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.brandol.databinding.ActivityBrandCategoryBinding
import com.example.brandol.databinding.ActivityUserCategoryBinding

class BrandCategoryActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: BrandCategoryAdapter
    private lateinit var binding: ActivityBrandCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_brand_category)
        binding = ActivityBrandCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // RecyclerView 초기화
        recyclerView = findViewById(R.id.brand_categoty_rv)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Adapter 초기화
        adapter = BrandCategoryAdapter()
        recyclerView.adapter = adapter

        // RecyclerView에 더미 데이터 추가
        for (i in 1..10) {
            adapter.addItem("Brand $i") // BrandCategoryAdapter에 addItem 메서드가 있다고 가정
        }

        // move_iv 클릭 동작 설정
        binding.btnBackBrandCategory.setOnClickListener {
            // 원하는 동작을 여기에 추가
            finish()
        }


    }
}

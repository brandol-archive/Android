package com.example.brandol

import BrandListAdapter
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.brandol.databinding.ActivityBrandManagementBinding

class BrandManagementActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBrandManagementBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var brandListAdapter: BrandListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBrandManagementBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 리사이클러뷰 초기화 및 레이아웃 매니저 설정
        recyclerView = binding.rvBrandList
        recyclerView.layoutManager = LinearLayoutManager(this)

        // 더미 데이터 생성
        val brandList = listOf("Brand 1", "Brand 2", "Brand 3", "Brand 4", "Brand 5")

        // 어댑터 초기화 및 리사이클러뷰에 설정
        brandListAdapter = BrandListAdapter(brandList.toMutableList())
        recyclerView.adapter = brandListAdapter


        // move_iv 클릭 동작 설정
        binding.btnBackBrandManagement.setOnClickListener {
            // 원하는 동작을 여기에 추가
            finish()
        }

        // ItemTouchHelper를 RecyclerView에 연결
        brandListAdapter.attachItemTouchHelper(recyclerView)

    }
}

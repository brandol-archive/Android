package com.example.brandol

import BrandListAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.brandol.databinding.ActivityBrandManagementBinding

data class BrandData(
    val brandName: String,
    val brandInfo: String,
    val brandImageResourceId: Int
)
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
        val brandDataList = listOf(
            BrandData("BRANDOL", "브랜드 팬덤 커뮤니티", R.drawable.img_brandol),
            BrandData("빙그레", "행복한 맛!", R.drawable.img_binggrae),
            BrandData("배달의민족", "대한민국 1등 배달앱", R.drawable.img_baemin),
        )

        // 어댑터 초기화 및 리사이클러뷰에 설정
        brandListAdapter = BrandListAdapter(mutableListOf())
        recyclerView.adapter = brandListAdapter

        // move_iv 클릭 동작 설정
        binding.btnBackBrandManagement.setOnClickListener {
            // 원하는 동작을 여기에 추가
            finish()
        }

        // ItemTouchHelper를 RecyclerView에 연결
        brandListAdapter.attachItemTouchHelper(recyclerView)

        // 어댑터에 더미 데이터 설정
        brandListAdapter.setBrandList(brandDataList)
    }
}

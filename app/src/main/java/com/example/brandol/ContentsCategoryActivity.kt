package com.example.brandol

import ContentCategoryAdapter
import ContentModel
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.brandol.databinding.ActivityContentsCategoryBinding

class ContentsCategoryActivity : AppCompatActivity() {

    private lateinit var contentAdapter: ContentCategoryAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: ActivityContentsCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.acticvity_contents_category)
        binding = ActivityContentsCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 더미 데이터 생성
        val dummyDataList = generateDummyData()

        // RecyclerView 초기화
        contentAdapter = ContentCategoryAdapter(dummyDataList)
        recyclerView = findViewById(R.id.contents_categoty_rv) // Use findViewById to find views in the activity's layout
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = contentAdapter

        // move_iv 클릭 동작 설정
        binding.btnBackContentsCategory.setOnClickListener {
            // 원하는 동작을 여기에 추가
            finish()
        }
    }

    private fun generateDummyData(): List<ContentModel> {
        return listOf(
            ContentModel("BRANDOL", "게시물 제목", "브랜드 추구 방향성, 문화 \n" + "최근 게시글이 들어감글글글글글", "2023.12.10"),
            ContentModel("Brand1", "Post Title 1", "Post Content 1", "0000.00.00"),
            ContentModel("Brand2", "Post Title 2", "Post Content 2", "0000.00.00"),
            // Add more dummy data as needed
        )
    }

}

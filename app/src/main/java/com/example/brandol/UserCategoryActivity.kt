package com.example.brandol

import UserCategoryAdapter
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.brandol.R
import com.example.brandol.databinding.ActivityUserCategoryBinding

class UserCategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserCategoryBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var userAdapter: UserCategoryAdapter

    //@SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_user_category)
        binding = ActivityUserCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // RecyclerView 초기화
        recyclerView = findViewById(R.id.user_categoty_rv)
        //recyclerView.layoutManager = LinearLayoutManager(this)

        // RecyclerView 설정
        userAdapter = UserCategoryAdapter()
        recyclerView.adapter = userAdapter
        recyclerView.layoutManager = GridLayoutManager(this, 2) // 2개의 열로 구성
        recyclerView.setHasFixedSize(true) // 옵션: 아이템 크기 고정

        // 데이터 추가 예시 (실제 데이터 추가 방법에 맞게 수정)
        userAdapter.addItem("User1")
        userAdapter.addItem("User2")
        userAdapter.addItem("User3")

        // 뒤로가기 버튼
        binding.btnBackUserCategory.setOnClickListener {
            // 원하는 동작을 여기에 추가
            finish()
        }
    }
}

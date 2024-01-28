package com.example.brandol

import UserCategoryAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.brandol.databinding.ActivityUserCategoryBinding

data class UserData(
    val userName: String,
    val userImageResourceId: Int
)

class UserCategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserCategoryBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var userAdapter: UserCategoryAdapter

    //@SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // RecyclerView 초기화
        recyclerView = findViewById(R.id.user_categoty_rv)

        // RecyclerView 설정
        userAdapter = UserCategoryAdapter(mutableListOf())
        recyclerView.adapter = userAdapter
        recyclerView.layoutManager = GridLayoutManager(this, 2) // 2개의 열로 구성
        recyclerView.setHasFixedSize(true) // 옵션: 아이템 크기 고정

        // 더미 데이터 생성
        val userDataList = listOf(
            UserData("주뇽이", R.drawable.img_user_character_1),
            UserData("지혀니", R.drawable.img_user_character_2),
            UserData("얌얌이", R.drawable.img_user_character_3),
            UserData("빙그레우스", R.drawable.img_user_character_4),
            UserData("A", R.drawable.img_user_character_5),
            UserData("B", R.drawable.img_user_character_6),
            UserData("C", R.drawable.img_user_character_5),
            UserData("D", R.drawable.img_user_character_6),
        )

        // 어댑터에 더미 데이터 설정
        userAdapter.setUserList(userDataList)

        // 뒤로가기 버튼
        binding.btnBackUserCategory.setOnClickListener {
            // 원하는 동작을 여기에 추가
            finish()
        }
    }
}

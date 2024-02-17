package com.example.brandol.board

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.brandol.connection.RetrofitAPI
import com.example.brandol.connection.RetrofitClient2
import com.example.brandol.connection.RetrofitObject
import com.example.brandol.databinding.ActivityBoardBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BoardActivity : AppCompatActivity() {
    lateinit var binding: ActivityBoardBinding
    private lateinit var retrofitAPI: RetrofitAPI // Declare RetrofitAPI instance

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        binding = ActivityBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize RetrofitAPI
        retrofitAPI = RetrofitObject.getRetrofitService

        // Call function to fetch brand header
        fetchBrandHeader()

        //게시판 제목 및 게시판 위치 글씨 변경
        binding.boardBrandnameTv.text = "BRANDOL"  //추후 수정 필요
        binding.boardNowcateTv.text = "커뮤니티"  //추후 수정 필요
        binding.boardNowboardTv.text = "자유게시판"  //추후 수정 필요

        //뒤로가기
        goBack()
        //검색 화면 전환
        search()
        //글쓰기 화면 전환
        writePost()

        //리사이클러뷰 관련 코드
    }

    private fun goBack() {
        //뒤로가기 버튼 클릭
        binding.boardBackBtn.setOnClickListener {
            finish()
        }
    }

    private fun search() {
        //검색 버튼 클릭 -> 실행 되는 거 보고 확인
        binding.boardSearchBtn.setOnClickListener {
            val intent = Intent(this, BoardSearchActivity::class.java)
            startActivity(intent)
        }
    }

    private fun writePost() {
        //글쓰기 버튼 클릭 -> 실행 되는 거 보고 확인
        binding.boardWriteboardBtn.setOnClickListener {
            val intent = Intent(this, WriteBoardActivity::class.java)
            startActivity(intent)
        }
    }

    private fun fetchBrandHeader() {
        // BoardActivity에서 결과 설정 및 액티비티 종료
        val brandId = intent.getIntExtra("brandId", 1001)

        // Make network call to fetch brand header
        val call: Call<RetrofitClient2.BrandHeader> = retrofitAPI.brandheader(brandId.toLong())  //추후 여기에 적절한 브랜드 아이디(brandId)를 전달해야 함
        call.enqueue(object : Callback<RetrofitClient2.BrandHeader> {
            override fun onResponse(call: Call<RetrofitClient2.BrandHeader>, response: Response<RetrofitClient2.BrandHeader>) {
                if (response.isSuccessful) {
                    val brandHeader: RetrofitClient2.BrandHeader? = response.body()
                    updateUI(brandHeader)
                }
            }

            override fun onFailure(call: Call<RetrofitClient2.BrandHeader>, t: Throwable) {
                // Handle network call failure
                Log.d("BoardActivity", "브랜드 커뮤니티 불러오기 실패")
            }
        })
    }

    private fun updateUI(brandHeader: RetrofitClient2.BrandHeader?) {
        // Update UI with brand header data
        brandHeader?.let {
            val brandName = it.result.brandPreviewDto.brandName
            val brandPosition = "커뮤니티" // Assuming you get this information from the backend as well

            binding.boardBrandnameTv.text = brandName
            binding.boardNowcateTv.text = brandPosition
            // Update other UI elements if needed
        }
    }
}
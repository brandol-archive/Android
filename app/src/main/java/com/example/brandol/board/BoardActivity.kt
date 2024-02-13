package com.example.brandol.board

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.brandol.databinding.ActivityBoardBinding

class BoardActivity : AppCompatActivity() {
    lateinit var binding: ActivityBoardBinding
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        binding = ActivityBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
}
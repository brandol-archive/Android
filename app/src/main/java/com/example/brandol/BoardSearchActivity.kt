package com.example.brandol

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.brandol.databinding.ActivityBoardSearchBinding
import com.example.brandol.databinding.ItemBoardBinding

class BoardSearchActivity : AppCompatActivity() {
    lateinit var binding: ActivityBoardSearchBinding
    lateinit var _binding: ItemBoardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //게시글 더미데이터 생성
        _binding.itemUsernickTv.text = "호지니"
        _binding.itemPosttitleTv.text = "나 좀 닮은듯ㅎ"
        _binding.itemPostcontentTv.text = "인정하면 좋아요"
        _binding.itemLikecntTv.text = "99"
        _binding.itemCommentcntTv.text = "99"
        _binding.itemPosttimeTv.text = "2023.12.25"
        _binding.itemImageIv.setImageResource(R.drawable.iv_image_ex)

        //뒤로가기 버튼 클릭
        goBack()
        //검색 버튼 클릭
        search()
        //리사이클러뷰 관련 코드 작성
    }

    private fun goBack() {
        binding.searchBackBtn.setOnClickListener {
            finish()
        }
    }

    private fun search() {
        binding.searchSearchBtn.setOnClickListener {
            //코드 작성
        }
    }
}
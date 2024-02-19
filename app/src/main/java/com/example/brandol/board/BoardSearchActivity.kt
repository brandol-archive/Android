package com.example.brandol.board

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.brandol.databinding.ActivityBoardSearchBinding
import com.example.brandol.databinding.ItemBoardBinding

class BoardSearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBoardSearchBinding
    private lateinit var itemBinding: ItemBoardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBoardSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // ItemBoardBinding을 초기화할 때 사용하는 뷰 요소들만 초기화합니다.
        itemBinding = ItemBoardBinding.bind(binding.root)

        // 게시글 더미데이터 생성
        itemBinding.itemUsernickTv.text = "호지니"
        itemBinding.itemPosttitleTv.text = "나 좀 닮은듯ㅎ"
        itemBinding.itemPostcontentTv.text = "인정하면 좋아요"
        itemBinding.itemLikecntTv.text = "99"
        itemBinding.itemCommentcntTv.text = "99"
        itemBinding.itemPosttimeTv.text = "2023.12.25"
        // itemBinding.itemImageIv.setImageResource(R.drawable.iv_image_ex)

        // 뒤로가기 버튼 클릭
        goBack()
        // 검색 버튼 클릭
        search()
        // 리사이클러뷰 관련 코드 작성
    }

    private fun goBack() {
        binding.searchBackBtn.setOnClickListener {
            finish()
        }
    }

    private fun search() {
        binding.searchSearchBtn.setOnClickListener {
            // 코드 작성
        }
    }


}

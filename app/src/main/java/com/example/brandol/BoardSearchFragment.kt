package com.example.brandol

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.brandol.databinding.FragmentBoardSearchBinding

class BoardSearchFragment : Fragment() {
    lateinit var binding: FragmentBoardSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //게시글 더미데이터 생성
        binding.searchUsernick1Tv.text = "호지니"
        binding.searchPosttitle1Tv.text = "나 좀 닮은듯ㅎ"
        binding.searchPostcontent1Tv.text = "인정하면 좋아요"
        binding.searchLikecnt1Tv.text = "99"
        binding.searchCommentcnt1Tv.text = "99"
        binding.searchPosttime1Tv.text = "2023.12.25"
        binding.searchImageIv.setImageResource(R.drawable.iv_image_ex)
    }
}
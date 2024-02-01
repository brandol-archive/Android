package com.example.brandol

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.brandol.databinding.FragmentBoardViewimageBinding
import com.example.brandol.databinding.FragmentHomeBinding

lateinit var binding: FragmentBoardViewimageBinding

private lateinit var imageViewPager: ViewPager2

class ViewimageFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBoardViewimageBinding.inflate(inflater, container, false)
        val view = binding.root

//        setContentView(R.layout.fragment_board_viewimage)
//
//        viewimage_image_vp.adapter = ImageSliderAdapter(getImageList()) // 어댑터 생성
//        viewimage_image_vp.orientation = ViewPager2.ORIENTATION_HORIZONTAL // 방향을 가로로

        return view
    }

    // 뷰 페이저에 들어갈 아이템
    private fun getImageList(): ArrayList<Int> {
        return arrayListOf<Int>(R.drawable.iv_image_ex, R.drawable.iv_image_ex, R.drawable.iv_image_ex)
    }
}
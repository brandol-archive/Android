package com.example.brandol

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.brandol.databinding.FragmentPointDetailBinding
import com.example.brandol.databinding.FragmentPointMission1Binding

class PointMission1Fragment: Fragment() {

    private lateinit var binding: FragmentPointMission1Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPointMission1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 뒤로가기 버튼 클릭 시 Fragment를 닫습니다.
        binding.pointMissionBackBtnIv.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

    }
}
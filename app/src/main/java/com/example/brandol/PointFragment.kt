package com.example.brandol

import PointDetailFragment
import PointMissionSurveyFragment
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.brandol.databinding.FragmentPointBinding

class PointFragment : Fragment() {
    private lateinit var binding: FragmentPointBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPointBinding.inflate(inflater, container, false)
        binding.pointUseIv.setOnClickListener {
            // 화면 전환
            parentFragmentManager.beginTransaction()
                .replace(R.id.main_frm, PointDetailFragment())
                .addToBackStack(null)
                .commit()
        }

        binding.missionPointBox2Iv.setOnClickListener {
            // 테스트용 포인트미션 설문조사형 프래그먼트로 전환
            parentFragmentManager.beginTransaction()
                .replace(R.id.main_frm, PointMissionSurveyFragment())
                .addToBackStack(null)
                .commit()
        }

        return binding.root
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        /**
//        binding.pointUseIv.setOnClickListener {
//        startActivity(Intent(activity,PointDetailFragment::class.java))
//        }**/
//
//        // "Go to PointDetailFragment" 버튼 클릭 시
//        binding.pointUseIv.setOnClickListener {
//            // 화면 전환
//            findNavController().navigate(R.id.action_pointFragment_to_pointDetailFragment3)
//        }
//    }

}
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


        binding.missionPointBox2Iv.setOnClickListener {
            // 테스트용 포인트미션 설문조사형 프래그먼트로 전환
            parentFragmentManager.beginTransaction()
                .replace(R.id.main_frm, PointMissionSurveyFragment())
                .addToBackStack(null)
                .commit()
        }
        binding.pointUseIv.setOnClickListener {
            // 포인트 디테일 프레그먼트로 화면 전환
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.main_frm, PointDetailFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }
        binding.missionPointBox1Iv.setOnClickListener {
            // 포인트 미션1 프레그먼트로 화면 전환
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.main_frm,PointMission1Fragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**
        binding.pointUseIv.setOnClickListener {
        startActivity(Intent(activity,PointDetailFragment::class.java))
        }**/

    }

//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment PointFragment.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            PointFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
}
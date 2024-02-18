package com.example.brandol

import PointDetailFragment
import PointMissionSurveyFragment
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.brandol.connection.RetrofitClient2
import com.example.brandol.connection.RetrofitObject
import com.example.brandol.databinding.FragmentPointBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

        getMissionList()

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

    private fun getCurrentToken(context: Context): String?{
        val sharedPref = context.getSharedPreferences("Brandol", AppCompatActivity.MODE_PRIVATE)
        return sharedPref.getString("accessToken", null)
    }

    private fun getMissionList() {
        val token = getCurrentToken(requireContext())
        val call = RetrofitObject.getRetrofitService.getMissionList("Bearer $token")
        Log.d("mission_list", "good_1")
        call.enqueue(object : Callback<RetrofitClient2.ResponseMissionList> {
            override fun onResponse(
                call: Call<RetrofitClient2.ResponseMissionList>,
                response: Response<RetrofitClient2.ResponseMissionList?>
            ) {
                Log.d("mission_list", "good_2")
                Log.d("mission_list", response.toString())
                if (response.isSuccessful) {
                    val responseData = response.body()
                    val missionListResult = responseData?.result
                    Log.d("mission_list", responseData.toString())
                    if (responseData != null && missionListResult != null) {
                        if (responseData.isSuccess) {
                            Log.d("mission_list", missionListResult.toString())

                            // 예시: 첫 번째 미션 데이터를 가져와서 UI 업데이트
                            if (missionListResult.memberMissionList.isNotEmpty()) {
                                val firstMission = missionListResult.memberMissionList[0]

                                // 미션 데이터를 UI에 업데이트하는 함수 호출
                                //updateMissionData(firstMission)
                                        //updateMissionData(missionListResult.memberMissionList)
                            }
                        }
                    }
                }
            }

            override fun onFailure(call: Call<RetrofitClient2.ResponseMissionList>, t: Throwable) {
                val errorMessage = "Call Failed: ${t.message}"
                Log.e("sseohyeonn", errorMessage)
            }
        })
    }

    // 미션 데이터를 UI에 업데이트하는 함수
    private fun updateMissionData(missions: List<RetrofitClient2.Mission>) {
        // UI 업데이트 코드 작성
        for ((index, mission) in missions.withIndex()) {
            // 예시: 두 개의 미션만 처리하도록 설정
            if (index == 0) {
                updateTextView(mission.missionName, binding.myPointTv)
                updateTextView(mission.missionPoints.toString() + "p", binding.myPointPTv)
                updateImage(mission.missionImage, binding.pointMision1Iv)
            } else if (index == 1) {
                // 두 번째 미션 데이터를 활용하여 다른 UI 업데이트 작업 수행
                // updateTextView(mission.missionName, binding.anotherTextView)
                // updateImage(mission.missionImage, binding.anotherImageView)
            }
            // 더 많은 미션 데이터에 대한 처리 추가 가능
        }
    }


    // 이미지뷰를 업데이트하는 함수
    private fun updateImage(brandProfileImage: String, imageView: ImageView) {
        Glide.with(requireContext()).load(brandProfileImage).into(imageView)
    }


    // 텍스트뷰를 업데이트하는 함수
    private fun updateTextView(text: String?, textView: TextView) {
        if (!text.isNullOrBlank()) {
            textView.text = text
        } else {
            // 텍스트가 비어있는 경우 기본 텍스트 또는 다른 처리를 수행할 수 있습니다.
            // 예를 들어, 기본 텍스트를 설정하거나 숨김 처리를 할 수 있습니다.
            textView.visibility = View.GONE
        }
    }

}
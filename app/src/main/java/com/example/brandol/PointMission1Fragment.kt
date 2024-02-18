package com.example.brandol

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.brandol.connection.RetrofitClient2
import com.example.brandol.connection.RetrofitObject
import com.example.brandol.databinding.FragmentPointMission1Binding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PointMission1Fragment: Fragment() {

    private lateinit var binding: FragmentPointMission1Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPointMission1Binding.inflate(inflater, container, false)

        //val missionId = 2L
        //completeMission(missionId)

        tryBrandAdditionMission(1)

        return binding.root
    }

    private fun getCurrentToken(context: Context): String?{
        val sharedPref = context.getSharedPreferences("Brandol", AppCompatActivity.MODE_PRIVATE)
        return sharedPref.getString("accessToken", null)
    }

    private fun tryBrandAdditionMission(missionId: Int) {
        val token = getCurrentToken(requireContext())
        val call = RetrofitObject.getRetrofitService.tryBrandAdditionMission("Bearer $token", missionId)
        Log.d("complete_mission", "good_1")
        call.enqueue(object : Callback<RetrofitClient2.TryBrandAdditionMission> {
            override fun onResponse(
                call: Call<RetrofitClient2.TryBrandAdditionMission>,
                response: Response<RetrofitClient2.TryBrandAdditionMission?>
            ) {
                Log.d("complete_mission", "good_2")
                Log.d("complete_mission", response.toString())
                if (response.isSuccessful) {
                    val response = response.body()
                    Log.d("complete_mission", response.toString())
                    if (response != null) {
                        if (response.isSuccess) {
                            Log.d("complete_mission", response.result.toString())
                        }
                    }

                }
            }

            override fun onFailure(
                call: Call<RetrofitClient2.TryBrandAdditionMission>,
                t: Throwable
            ) {
                val errorMessage = "Call Failed: ${t.message}"
                Log.e("sseohyeonn", errorMessage)
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 뒤로가기 버튼 클릭 시 Fragment를 닫습니다.
        binding.pointMissionBackBtnIv.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

    }

}
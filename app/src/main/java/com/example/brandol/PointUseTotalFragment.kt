package com.example.brandol

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.brandol.connection.RetrofitClient2
import com.example.brandol.connection.RetrofitObject
import com.example.brandol.databinding.FragmentPointUseTotalBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PointUseTotalFragment :Fragment() {

    lateinit var binding : FragmentPointUseTotalBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPointUseTotalBinding.inflate(inflater,container,false)

        val token = getCurrentToken(requireContext())
        val call = RetrofitObject.getRetrofitService.pointHistory("Bearer $token")
        call.enqueue(object : Callback<RetrofitClient2.PointHistory> {
            override fun onResponse(
                call: Call<RetrofitClient2.PointHistory>,
                response: Response<RetrofitClient2.PointHistory>
            ) {
                Log.d("ikj", response.toString())
                if (response.isSuccessful) {
                    val response = response.body()
                    Log.d("ikj", response.toString())
                    if (response != null) {
                        if (response.isSuccess) {

                            //pointAdapter.setData(pointHistories)

                        }
                    }
                }
            }

            override fun onFailure(call: Call<RetrofitClient2.PointHistory>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

        return binding.root
    }

    private fun getCurrentToken(context: Context): String? {
        val sharedPref = context.getSharedPreferences("Brandol", Context.MODE_PRIVATE)
        return sharedPref.getString("accessToken", null)
    }
}
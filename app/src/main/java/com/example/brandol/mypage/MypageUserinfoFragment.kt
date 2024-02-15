package com.example.brandol.mypage

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.brandol.R
import com.example.brandol.connection.RetrofitClient2
import com.example.brandol.connection.RetrofitObject
import com.example.brandol.databinding.FragmentMypageUserinfoBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MypageUserinfoFragment : Fragment() {
    lateinit var binding: FragmentMypageUserinfoBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMypageUserinfoBinding.inflate(inflater,container,false)
        backbtn()

        val newNickname = binding.nicknameChangeEt.text.toString()
        binding.changeTv.setOnClickListener {
            val token = getCurrentToken(requireContext())
            val call = RetrofitObject.getRetrofitService.changeNickname(
                "Bearer $token",
                RetrofitClient2.RequestNickname(newNickname)
            )
            call.enqueue(object : Callback<RetrofitClient2.ResponseNickname> {
                override fun onResponse(
                    call: Call<RetrofitClient2.ResponseNickname>,
                    response: Response<RetrofitClient2.ResponseNickname>
                ) {
                    Log.d("LHJ", response.toString())
                    if (response.isSuccessful) {
                        val response = response.body()
                        Log.d("LHJ", response.toString())
                        if (response != null) {
                            if (response.isSuccess) {
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<RetrofitClient2.ResponseNickname>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        }
        return binding.root
    }

    private fun backbtn() {
        binding.mypageUserinfoBackBtn.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.main_frm, MypageFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    private fun getCurrentToken(context: Context): String?{
        val sharedPref = context.getSharedPreferences("Brandol", AppCompatActivity.MODE_PRIVATE)
        return sharedPref.getString("accessToken", null)
    }
}
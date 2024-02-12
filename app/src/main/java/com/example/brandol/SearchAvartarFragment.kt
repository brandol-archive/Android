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
import com.example.brandol.databinding.FragmentSearchAvartarBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchAvartarFragment : Fragment() {
    lateinit var binding : FragmentSearchAvartarBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchAvartarBinding.inflate(inflater,container,false)

        searchMain()

        return binding.root
    }

    private fun getCurrentToken(context: Context): String?{
        val sharedPref = context.getSharedPreferences("Brandol", AppCompatActivity.MODE_PRIVATE)
        return sharedPref.getString("accessToken", null)
    }


    private fun searchMain() {
        val token = getCurrentToken(requireContext())

        val call = RetrofitObject.getRetrofitService.getSearchMain("Bearer $token")
        Log.d("search_avartar", "good_1")
        call.enqueue(object : Callback<RetrofitClient2.ResponseSearchMain> {
            override fun onResponse(
                call: Call<RetrofitClient2.ResponseSearchMain>,
                response: Response<RetrofitClient2.ResponseSearchMain?>
            ) {
                Log.d("search_avartar", "good_2")
                Log.d("search_avartar", response.toString())
                if (response.isSuccessful) {
                    val response = response.body()
                    Log.d("search_avartar", response.toString())
                    if (response != null) {
                        if (response.isSuccess) {
                            Log.d("search_avartar", response.result.toString())

                        }
                    }
                }
            }

            override fun onFailure(
                call: Call<RetrofitClient2.ResponseSearchMain>,
                t: Throwable
            ) {
                val errorMessage = "Call Failed: ${t.message}"
                Log.e("sseohyeonn", errorMessage)
            }
        })
    }
}
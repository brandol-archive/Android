package com.example.brandol.searchCategory

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.brandol.adaptor.BrandCategoryAdapter
import com.example.brandol.connection.RetrofitClient2
import com.example.brandol.connection.RetrofitObject
import com.example.brandol.databinding.FragmentBrandCategoryBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BrandCategoryFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: BrandCategoryAdapter
    private lateinit var binding: FragmentBrandCategoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBrandCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun getCurrentToken(context: Context): String? {
        val sharedPref = context.getSharedPreferences("Brandol", Context.MODE_PRIVATE)
        return sharedPref.getString("accessToken", null)
    }

    private fun getBrandCategoryData() {
        val token = getCurrentToken(requireContext())
        val call = RetrofitObject.getRetrofitService.searchDetailBrands("Bearer $token")
        Log.d("ikj", "good__1")
        call.enqueue(object : Callback<RetrofitClient2.SearchDetailBrands> {
            override fun onResponse(
                call: Call<RetrofitClient2.SearchDetailBrands>,
                response: Response<RetrofitClient2.SearchDetailBrands>
            ) {
                Log.d("ikj", "good__2")
                Log.d("ikj", response.toString())
                if (response.isSuccessful) {
                    val response = response.body()
                    Log.d("ikj", response.toString())
                    if (response != null) {
                        if (response.isSuccess) {
                            Log.d("ikj",response.result.toString())


                        }
                    }

                }
            }
            override fun onFailure(call: Call<RetrofitClient2.SearchDetailBrands>, t: Throwable) {
                val errorMessage = "Call Failed: ${t.message}"
                Log.d("ikj", errorMessage)
            }

        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // RecyclerView 초기화
        recyclerView = binding.brandCategotyRv
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Adapter 초기화
        adapter = BrandCategoryAdapter()
        recyclerView.adapter = adapter

        // 서버에서 브랜드 데이터 가져오기
        //getBrandCategoryData()


        // RecyclerView에 더미 데이터 추가
        for (i in 1..10) {
            adapter.addItem("Brand $i") // BrandCategoryAdapter에 addItem 메서드가 있다고 가정
        }

        // move_iv 클릭 동작 설정
        binding.btnBackBrandCategory.setOnClickListener {
            // 이전 화면으로 돌아가기
            requireActivity().supportFragmentManager.popBackStack()
        }
    }
}

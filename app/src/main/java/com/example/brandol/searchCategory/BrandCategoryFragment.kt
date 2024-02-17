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
import com.example.brandol.Home.BrandManagementFragment
import com.example.brandol.R
import com.example.brandol.SearchBarFragment
import com.example.brandol.SearchFragment
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


        binding.btnSearchBarIb.setOnClickListener {
            val searchBarFragment = SearchBarFragment()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.main_frm, searchBarFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
        binding.btnSearchIc.setOnClickListener {
            val searchBarFragment = SearchBarFragment()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.main_frm, searchBarFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }



        return binding.root
    }

    private fun getCurrentToken(context: Context): String? {
        val sharedPref = context.getSharedPreferences("Brandol", Context.MODE_PRIVATE)
        return sharedPref.getString("accessToken", null)
    }

    private fun getBrandCategoryData() {
        val token = getCurrentToken(requireContext())
        val call = RetrofitObject.getRetrofitService.searchDetailBrands("Bearer $token")

        call.enqueue(object : Callback<RetrofitClient2.SearchDetailBrands> {
            override fun onResponse(
                call: Call<RetrofitClient2.SearchDetailBrands>,
                response: Response<RetrofitClient2.SearchDetailBrands>
            ) {
                if (response.isSuccessful) {
                    val responseData = response.body()
                    if (responseData != null && responseData.isSuccess) {
                        val brandDataList = responseData.result.searchDetailBrandDto
                        adapter.addItems(brandDataList)
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
        getBrandCategoryData()


        // RecyclerView에 더미 데이터 추가
//        for (i in 1..10) {
//            adapter.addItem("Brand $i") // BrandCategoryAdapter에 addItem 메서드가 있다고 가정
//        }

        // move_iv 클릭 동작 설정
        binding.btnBackBrandCategory.setOnClickListener {
            // 이전 화면으로 돌아가기
            requireActivity().supportFragmentManager.popBackStack()
        }
    }
}

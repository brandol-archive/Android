package com.example.brandol.Home

import android.content.Context
import com.example.brandol.adaptor.BrandListAdapter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.brandol.R
import com.example.brandol.collection.BrandData
import com.example.brandol.connection.RetrofitClient2
import com.example.brandol.connection.RetrofitObject
import com.example.brandol.databinding.FragmentBrandManagementBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


// BrandManagementFragment.kt


class BrandManagementFragment : Fragment() {
    private lateinit var binding: FragmentBrandManagementBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var brandListAdapter: BrandListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBrandManagementBinding.inflate(inflater, container, false)


        //unsubscribeBrandData()
        getBrandListData()

        return binding.root
    }

    private fun getCurrentToken(context: Context): String? {
        val sharedPref = context.getSharedPreferences("Brandol", Context.MODE_PRIVATE)
        return sharedPref.getString("accessToken", null)
    }

    private fun getBrandListData() {
        val token = getCurrentToken(requireContext())
        val call = RetrofitObject.getRetrofitService.getHomeFragment("Bearer $token")
        Log.d("ikj", "good_1")
        call.enqueue(object : Callback<RetrofitClient2.GetHomeFragment> {
            override fun onResponse(
                call: Call<RetrofitClient2.GetHomeFragment>,
                response: Response<RetrofitClient2.GetHomeFragment>
            ) {
                Log.d("ikj", "good_2")
                Log.d("ikj", response.toString())
                if (response.isSuccessful) {
                    val response = response.body()
                    Log.d("ikj", response.toString())
                    if (response != null) {
                        if (response.isSuccess) {
                            Log.d("ikj", response.result.toString())
                            val responseData = response.result

                            // Check if brand list is empty
                            if (responseData?.memberBrandListDtoList.isNullOrEmpty()) {
                                brandListAdapter.setEmptyBrandList()
                            } else {
                                // Update brand button data
                                responseData?.memberBrandListDtoList?.let { brandListAdapter.setBrandList(it) }
                            }
                        }
                    }
                }
            }

            override fun onFailure(call: Call<RetrofitClient2.GetHomeFragment>, t: Throwable) {
                val errorMessage = "Call Failed: ${t.message}"
                Log.d("ikj", errorMessage)
            }
        })
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 리사이클러뷰 초기화 및 레이아웃 매니저 설정
        recyclerView = binding.rvBrandList
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // 더미 데이터 생성
//        val brandDataList = listOf(
//            BrandData("BRANDOL", "브랜드 팬덤 커뮤니티", R.drawable.img_brandol),
//            BrandData("빙그레", "행복한 맛!", R.drawable.img_binggrae),
//            BrandData("배달의민족", "대한민국 1등 배달앱", R.drawable.img_baemin),
//        )

        // 어댑터 초기화 및 리사이클러뷰에 설정
        brandListAdapter = BrandListAdapter(mutableListOf())
        recyclerView.adapter = brandListAdapter


        // move_iv 클릭 동작 설정
        binding.btnBackBrandManagement.setOnClickListener {
            // 이전 화면으로 돌아가기
            requireActivity().supportFragmentManager.popBackStack()
        }

        // ItemTouchHelper를 RecyclerView에 연결
        brandListAdapter.attachItemTouchHelper(recyclerView)

        // 어댑터에 더미 데이터 설정
        //brandListAdapter.setBrandList(brandDataList)
    }
}


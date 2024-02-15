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
import com.example.brandol.R
import com.example.brandol.SearchBarFragment
import com.example.brandol.adaptor.ContentCategoryAdapter
import com.example.brandol.adaptor.ContentModel
import com.example.brandol.connection.RetrofitClient2
import com.example.brandol.connection.RetrofitObject
import com.example.brandol.databinding.FragmentContentsCategoryBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ContentsCategoryFragment : Fragment() {

    private lateinit var contentAdapter: ContentCategoryAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: FragmentContentsCategoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContentsCategoryBinding.inflate(inflater, container, false)


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

    private fun getContentsCategoryData() {
        val token = getCurrentToken(requireContext())
        val call = RetrofitObject.getRetrofitService.searchDetailContents("Bearer $token")
        call.enqueue(object : Callback<RetrofitClient2.SearchDetailContents> {
            override fun onResponse(
                call: Call<RetrofitClient2.SearchDetailContents>,
                response: Response<RetrofitClient2.SearchDetailContents>
            ) {
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
            override fun onFailure(call: Call<RetrofitClient2.SearchDetailContents>, t: Throwable) {
                val errorMessage = "Call Failed: ${t.message}"
                Log.d("ikj", errorMessage)
            }

        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 더미 데이터 생성
        val dummyDataList = generateDummyData()

        // RecyclerView 초기화
        contentAdapter = ContentCategoryAdapter(dummyDataList)
        recyclerView = binding.contentsCategotyRv
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = contentAdapter

        binding.btnBackContentsCategory.setOnClickListener {
            // 이전 화면으로 돌아가기
            requireActivity().supportFragmentManager.popBackStack()
        }

        getContentsCategoryData()
    }


    private fun generateDummyData(): List<ContentModel> {
        return listOf(
            ContentModel("BRANDOL", "게시물 제목", "브랜드 추구 방향성, 문화 \n" + "최근 게시글이 들어감글글글글글", "2023.12.10"),
            ContentModel("Brand1", "Post Title 1", "Post Content 1", "0000.00.00"),
            ContentModel("Brand2", "Post Title 2", "Post Content 2", "0000.00.00"),
            // Add more dummy data as needed
        )
    }
}

package com.example.brandol

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.brandol.adaptor.SearchPagerAdapter
import com.example.brandol.connection.RetrofitClient2
import com.example.brandol.connection.RetrofitObject
import com.example.brandol.databinding.FragmentSearchBarBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.Response


class SearchBarFragment : Fragment() {

    private lateinit var binding: FragmentSearchBarBinding
    //private lateinit var retrofitApi: RetrofitApi

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSearchBarBinding.inflate(inflater, container, false)
        //initRetrofit()

        // TabLayout 초기화
        val tabLayout: TabLayout = binding.searchBarTb

        // 탭의 제목 리스트
        val tabTitles = arrayListOf("브랜드", "유저", "콘텐츠", "아바타 스토어")

        // ViewPager2 초기화
        val viewPager: ViewPager2 = binding.searchBarVp
        val adapter = SearchPagerAdapter(childFragmentManager, lifecycle)
        viewPager.adapter = adapter

        // TabLayout에 ViewPager2 연결
        TabLayoutMediator(binding.searchBarTb, binding.searchBarVp) {
            tab, position ->
            tab.text = tabTitles[position]
        }.attach()

        // 탭의 선택된 상태에 대한 스타일 정의
        binding.searchBarTb.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.apply {
                    // 선택된 탭의 텍스트 색상 변경
                    view?.findViewById<TextView>(com.google.android.material.R.id.title)?.setTextColor(
                        ContextCompat.getColor(requireContext(), R.color.puple)
                    )
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // 선택되지 않은 탭의 텍스트 색상 변경 (원래의 색 또는 다른 색)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // 탭이 이미 선택된 상태에서 다시 선택될 때의 동작
            }
        })

        //searchContents("d")

        binding.btnSearchGlasses2Ib.setOnClickListener {
            val query = binding.btnSearchBar2Ib.text.toString()
            searchContents(query)

            closeKeyboard()
        }


        return binding.root

    }
    private fun closeKeyboard() {
        val view = requireActivity().currentFocus
        if (view != null) {
            val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun getCurrentToken(context: Context): String?{
        val sharedPref = context.getSharedPreferences("Brandol", AppCompatActivity.MODE_PRIVATE)
        return sharedPref.getString("accessToken", null)
    }

    private fun searchContents(query: String) {
        val token = getCurrentToken(requireContext())
        val call = RetrofitObject.getRetrofitService.getSearchMain("Bearer $token")
        Log.d("search_bar", "good_1")
        call.enqueue(object : Callback<RetrofitClient2.SearchResponse> {
            override fun onResponse(
                call: Call<RetrofitClient2.SearchResponse>,
                response: Response<RetrofitClient2.SearchResponse>
            ) {
                Log.d("search_bar", "good_2")
                if (response.isSuccessful) {
                    val searchResponse = response.body()

                    // searchResponse를 활용하여 UI 업데이트 또는 다른 처리 수행
                    updateUI(searchResponse)
                    Log.d("search_bar", "성고옹ㅇㅇㅇ?")
                } else {
                    // 오류 처리
                    // response.errorBody() 등을 활용하여 오류 정보를 처리
                    Log.d("search_bar", "실패")
                }
            }

            override fun onFailure(call: Call<RetrofitClient2.SearchResponse>, t: Throwable) {
                // 네트워크 오류 처리
                Log.d("search_bar", "네트워크 오류: ${t.message}")
            }
        })
    }

    private fun updateUI(searchResponse: RetrofitClient2.SearchResponse?) {
        // UI 업데이트를 위한 코드
        // searchResponse 내의 데이터 활용
    }


}

private fun <T> Call<T>.enqueue(callback: Callback<RetrofitClient2.SearchResponse>) {

}


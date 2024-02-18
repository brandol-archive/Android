package com.example.brandol.searchCategory

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.brandol.R
import com.example.brandol.SearchBarFragment
import com.example.brandol.adaptor.ItemClickListener
import com.example.brandol.adaptor.UserCategoryAdapter
import com.example.brandol.connection.RetrofitClient2
import com.example.brandol.connection.RetrofitObject
import com.example.brandol.databinding.FragmentUserCategoryBinding
import com.example.brandol.opavatar.OpponentAvatarActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//data class UserData(
//    val userName: String,
//    val userImageResourceId: Int
//)

class UserCategoryFragment : Fragment() {

    private lateinit var binding: FragmentUserCategoryBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var userAdapter: UserCategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserCategoryBinding.inflate(inflater, container, false)

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

        getUserCategoryData()

        return binding.root
    }

    private fun getCurrentToken(context: Context): String? {
        val sharedPref = context.getSharedPreferences("Brandol", Context.MODE_PRIVATE)
        return sharedPref.getString("accessToken", null)
    }

    private fun getUserCategoryData() {
        val token = getCurrentToken(requireContext())
        val call = RetrofitObject.getRetrofitService.searchDetailUser("Bearer $token")

        call.enqueue(object : Callback<RetrofitClient2.SearchDetailUser> {
            override fun onResponse(
                call: Call<RetrofitClient2.SearchDetailUser>,
                response: Response<RetrofitClient2.SearchDetailUser>
            ) {
                if (response.isSuccessful) {
                    val responseData = response.body()
                    if (responseData != null && responseData.isSuccess) {
                        val userDataList = responseData.result.searchDetailUserDto
                        userAdapter.addItem(userDataList)
                    }
                }
            }

            override fun onFailure(call: Call<RetrofitClient2.SearchDetailUser>, t: Throwable) {
                val errorMessage = "Call Failed: ${t.message}"
                Log.d("ikj", errorMessage)
            }
        })
    }

//    private fun navigateToOpponentAvatar(memberId: String) {
//        val intent = Intent(requireContext(), OpponentAvatarActivity::class.java)
//        intent.putExtra("from", "UserCategory")
//        intent.putExtra("memberId", memberId)
//        startActivity(intent)
//    }

    private fun navigateToOpponentAvatar(userId: Int) {
        val intent = Intent(requireContext(), OpponentAvatarActivity::class.java)
        intent.putExtra("userId", userId)
        startActivity(intent)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // RecyclerView 초기화
        recyclerView = binding.userCategotyRv

        // RecyclerView 설정
        userAdapter = UserCategoryAdapter()
        recyclerView.adapter = userAdapter
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2) // 2개의 열로 구성
        recyclerView.setHasFixedSize(true) // 옵션: 아이템 크기 고정

        // 서버에서 유저 데이터 가져오기
        getUserCategoryData()

        // 아이템 클릭 시 브랜드 아이디 전달
        userAdapter.itemClickListener = object : ItemClickListener,
            com.example.brandol.ItemClickListener {
            override fun onItemClick(position: Int, userId: Int) {
                navigateToOpponentAvatar(userId)
            }

            override fun onItemClick(userId: Long) {
                TODO("Not yet implemented")
            }
        }

        // 뒤로가기 버튼
        binding.btnBackUserCategory.setOnClickListener {
            // 이전 화면으로 돌아가기
            requireActivity().supportFragmentManager.popBackStack()
        }
    }
}

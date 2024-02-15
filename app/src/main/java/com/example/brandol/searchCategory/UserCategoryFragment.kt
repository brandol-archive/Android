package com.example.brandol.searchCategory

import android.content.Context
import com.example.brandol.adaptor.UserCategoryAdapter
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.brandol.ItemClickListener
import com.example.brandol.R
import com.example.brandol.SearchBarFragment
import com.example.brandol.connection.RetrofitClient2
import com.example.brandol.connection.RetrofitObject
import com.example.brandol.databinding.FragmentUserCategoryBinding
import com.example.brandol.opavatar.OpponentAvatarActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

data class UserData(
    val userName: String,
    val userImageResourceId: Int
)


class UserCategoryFragment : Fragment() {

    private lateinit var binding: FragmentUserCategoryBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var userAdapter: UserCategoryAdapter
    private var userDataList = listOf<UserData>()

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
            override fun onFailure(call: Call<RetrofitClient2.SearchDetailUser>, t: Throwable) {
                val errorMessage = "Call Failed: ${t.message}"
                Log.d("ikj", errorMessage)
            }

        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // RecyclerView 초기화
        recyclerView = binding.userCategotyRv

        // RecyclerView 설정
        userAdapter = UserCategoryAdapter(mutableListOf())
        recyclerView.adapter = userAdapter
        //호진이 만든 user 클릭리스너 구현
        val intent = Intent(activity, OpponentAvatarActivity::class.java)
        userAdapter.itemClickListener = object : ItemClickListener {
            override fun onItemClick(position: Int) {
                val userData = userDataList[position]
                intent.putExtra("from","UserCategory")
                intent.putExtra("userNameKey", userData.userName)
                startActivity(intent)
            }
        }
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2) // 2개의 열로 구성
        recyclerView.setHasFixedSize(true) // 옵션: 아이템 크기 고정

        // 더미 데이터 생성
        userDataList = listOf(
            UserData("주뇽이", R.drawable.img_user_character_1),
            UserData("지혀니", R.drawable.img_user_character_2),
            UserData("얌얌이", R.drawable.img_user_character_3),
            UserData("빙그레우스", R.drawable.img_user_character_4),
            UserData("A", R.drawable.img_user_character_5),
            UserData("B", R.drawable.img_user_character_6),
            UserData("C", R.drawable.img_user_character_5),
            UserData("D", R.drawable.img_user_character_6),
        )

        getUserCategoryData()

        // 어댑터에 더미 데이터 설정
        userAdapter.setUserList(userDataList)

        // 뒤로가기 버튼
        binding.btnBackUserCategory.setOnClickListener {
            // 이전 화면으로 돌아가기
            requireActivity().supportFragmentManager.popBackStack()
        }
    }
}

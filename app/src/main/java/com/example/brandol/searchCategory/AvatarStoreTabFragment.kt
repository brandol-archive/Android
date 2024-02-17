package com.example.brandol.searchCategory

import android.annotation.SuppressLint
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
import com.example.brandol.adaptor.AvatarStoreItemAdapter
import com.example.brandol.collection.ItemModel
import com.example.brandol.connection.RetrofitClient2
import com.example.brandol.connection.RetrofitObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AvatarStoreTabFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView

    companion object {
        fun newInstance(category: String): AvatarStoreTabFragment {
            val fragment = AvatarStoreTabFragment()
            val args = Bundle()
            args.putString("category", category)
            fragment.arguments = args
            return fragment
        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_avatarstore_rv, container, false)

        // 리사이클러뷰 초기화
        recyclerView = rootView.findViewById(R.id.avatarstore_rv)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // 어댑터 설정
        val adapter = AvatarStoreItemAdapter()  // YourAdapter는 실제 어댑터 클래스로 대체해야 합니다.
        recyclerView.adapter = adapter

        // 초기에 빈 목록을 생성
        val itemList = listOf<ItemModel>()

        // 어댑터에 빈 목록을 설정
        adapter.submitList(itemList)

        // 각 탭에 대한 데이터 가져오기
        //getAvatarStoreCategoryData("상의")
        //getAvatarStoreCategoryData("하의")
        // 필요한 만큼 더 탭을 추가하세요

        getAvatarStoreCategoryData()

        return rootView
    }

    private fun getCurrentToken(context: Context): String? {
        val sharedPref = context.getSharedPreferences("Brandol", Context.MODE_PRIVATE)
        return sharedPref.getString("accessToken", null)
    }

//
    private fun getAvatarStoreCategoryData() {
        val token = getCurrentToken(requireContext())
        val category = arguments?.getString("category") ?: ""

        val call = RetrofitObject.getRetrofitService.searchDetailAvatarStoreBody("Bearer $token", category)
        call.enqueue(object : Callback<RetrofitClient2.SearchDetailAvatarStoreBody> {
            override fun onResponse(
                call: Call<RetrofitClient2.SearchDetailAvatarStoreBody>,
                response: Response<RetrofitClient2.SearchDetailAvatarStoreBody>
            ) {
                Log.d("ikj", response.toString())
                if (response.isSuccessful) {
                    val responseData = response.body()
                    Log.d("ikj", responseData.toString())
                    if (responseData != null && responseData.isSuccess) {
                        // 서버에서 받아온 데이터를 처리
                        Log.d("ikj", responseData.result.toString())
                        // 어댑터를 업데이트하는 메서드 호출
                        updateAdapter(responseData.result.searchDetailAvatarStoreBodyDto)
                    }
                }
            }

            override fun onFailure(call: Call<RetrofitClient2.SearchDetailAvatarStoreBody>, t: Throwable) {
                val errorMessage = "Call Failed: ${t.message}"
                Log.d("ikj", errorMessage)
            }
        })
    }

    private fun updateAdapter(itemList: List<RetrofitClient2.SearchDetailAvatarStoreBodyDto>) {
        val adapter = recyclerView.adapter as? AvatarStoreItemAdapter
        adapter?.submitList(itemList.map { dto ->
            ItemModel(
                dto.itemImage,
                dto.itemsName,
                dto.itemPart,  // ItemModel에 itemPart 추가
                dto.brandName,
                dto.itemDescription,
                dto.itemPrice.toString() + "p"
            )
        })
    }



//    private fun generateItemData(): List<ItemModel> {
//        // 아이템 데이터 생성 및 반환
//        // 여기에서는 더미 데이터로 예시를 작성하였습니다.
//        return listOf(
//            ItemModel(R.drawable.img_avatar_item, "실리카겔", "피부", "김춘추의 창백한 피부", "김춘추가 촬영 오기 전에 급히 김밥을 \n" + "먹다가 체하는 바람에 창백해 졌다.\n" + "그의 피부에서 착안했다.", "50p"),
//            ItemModel(R.drawable.img_avatar_item, "실리카겔", "피부", "김춘추의 창백한 피부", "김춘추가 촬영 오기 전에 급히 김밥을 \n" + "먹다가 체하는 바람에 창백해 졌다.\n" + "그의 피부에서 착안했다.", "150p"),
//            ItemModel(R.drawable.img_avatar_item, "실리카겔", "피부", "김춘추의 창백한 피부", "김춘추가 촬영 오기 전에 급히 김밥을 \n" + "먹다가 체하는 바람에 창백해 졌다.\n" + "그의 피부에서 착안했다.", "150p"),
//            ItemModel(R.drawable.img_avatar_item, "실리카겔", "피부", "김춘추의 창백한 피부", "김춘추가 촬영 오기 전에 급히 김밥을 \n" + "먹다가 체하는 바람에 창백해 졌다.\n" + "그의 피부에서 착안했다.", "200p"),
//            ItemModel(R.drawable.img_avatar_item, "실리카겔", "피부", "김춘추의 창백한 피부", "김춘추가 촬영 오기 전에 급히 김밥을 \n" + "먹다가 체하는 바람에 창백해 졌다.\n" + "그의 피부에서 착안했다.", "250p"),
//            ItemModel(R.drawable.img_avatar_item, "실리카겔", "피부", "김춘추의 창백한 피부", "김춘추가 촬영 오기 전에 급히 김밥을 \n" + "먹다가 체하는 바람에 창백해 졌다.\n" + "그의 피부에서 착안했다.", "270p"),
//            ItemModel(R.drawable.img_avatar_item, "실리카겔", "피부", "김춘추의 창백한 피부", "김춘추가 촬영 오기 전에 급히 김밥을 \n" + "먹다가 체하는 바람에 창백해 졌다.\n" + "그의 피부에서 착안했다.", "250p"),
//            ItemModel(R.drawable.img_avatar_item, "실리카겔", "피부", "김춘추의 창백한 피부", "김춘추가 촬영 오기 전에 급히 김밥을 \n" + "먹다가 체하는 바람에 창백해 졌다.\n" + "그의 피부에서 착안했다.", "270p"),
//            // 추가 아이템 데이터 작성
//        )
//    }
}
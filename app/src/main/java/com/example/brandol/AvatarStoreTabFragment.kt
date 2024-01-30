package com.example.brandol

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AvatarStoreTabFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView

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

        // 아이템 데이터 생성 및 추가
        val itemList = generateItemData()
        adapter.submitList(itemList)

        return rootView
    }

    private fun generateItemData(): List<ItemModel> {
        // 아이템 데이터 생성 및 반환
        // 여기에서는 더미 데이터로 예시를 작성하였습니다.
        return listOf(
            ItemModel(R.drawable.img_avatar_item, "실리카겔", "피부", "김춘추의 창백한 피부", "김춘추가 촬영 오기 전에 급히 김밥을 \n" + "먹다가 체하는 바람에 창백해 졌다.\n" + "그의 피부에서 착안했다.", "50p"),
            ItemModel(R.drawable.img_avatar_item, "실리카겔", "피부", "김춘추의 창백한 피부", "김춘추가 촬영 오기 전에 급히 김밥을 \n" + "먹다가 체하는 바람에 창백해 졌다.\n" + "그의 피부에서 착안했다.", "150p"),
            ItemModel(R.drawable.img_avatar_item, "실리카겔", "피부", "김춘추의 창백한 피부", "김춘추가 촬영 오기 전에 급히 김밥을 \n" + "먹다가 체하는 바람에 창백해 졌다.\n" + "그의 피부에서 착안했다.", "150p"),
            ItemModel(R.drawable.img_avatar_item, "실리카겔", "피부", "김춘추의 창백한 피부", "김춘추가 촬영 오기 전에 급히 김밥을 \n" + "먹다가 체하는 바람에 창백해 졌다.\n" + "그의 피부에서 착안했다.", "200p"),
            ItemModel(R.drawable.img_avatar_item, "실리카겔", "피부", "김춘추의 창백한 피부", "김춘추가 촬영 오기 전에 급히 김밥을 \n" + "먹다가 체하는 바람에 창백해 졌다.\n" + "그의 피부에서 착안했다.", "250p"),
            ItemModel(R.drawable.img_avatar_item, "실리카겔", "피부", "김춘추의 창백한 피부", "김춘추가 촬영 오기 전에 급히 김밥을 \n" + "먹다가 체하는 바람에 창백해 졌다.\n" + "그의 피부에서 착안했다.", "270p"),
            ItemModel(R.drawable.img_avatar_item, "실리카겔", "피부", "김춘추의 창백한 피부", "김춘추가 촬영 오기 전에 급히 김밥을 \n" + "먹다가 체하는 바람에 창백해 졌다.\n" + "그의 피부에서 착안했다.", "250p"),
            ItemModel(R.drawable.img_avatar_item, "실리카겔", "피부", "김춘추의 창백한 피부", "김춘추가 촬영 오기 전에 급히 김밥을 \n" + "먹다가 체하는 바람에 창백해 졌다.\n" + "그의 피부에서 착안했다.", "270p"),
            // 추가 아이템 데이터 작성
        )
    }
}
package com.example.brandol

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.brandol.adaptor.AvatarStoreItemAdapter
import com.example.brandol.collection.ItemModel2

class OutfitFragment : Fragment()
{
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_outfit_avatarstore, container, false)

        // 리사이클러뷰 초기화
        recyclerView = rootView.findViewById(R.id.outfit_rv)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // 어댑터 설정
        val adapter = AvatarStoreItemAdapter()  // YourAdapter는 실제 어댑터 클래스로 대체해야 합니다.
        recyclerView.adapter = adapter

        // 아이템 데이터 생성 및 추가
        val itemList = generateItemData()
        adapter.submitList(itemList)

        return rootView
    }

    private fun generateItemData(): List<ItemModel2> {
        // 아이템 데이터 생성 및 반환
        // 여기에서는 더미 데이터로 예시를 작성하였습니다.
        return listOf(
            ItemModel2(R.drawable.ic_launcher_foreground, "Brand1", "|", "Item1", "item1_info", "100p"),
            // 추가 아이템 데이터 작성
        )
    }
}
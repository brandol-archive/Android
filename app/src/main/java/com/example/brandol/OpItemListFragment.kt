package com.example.brandol

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.brandol.databinding.FragmentOpItemListBinding

class OpItemListFragment : Fragment() {
    lateinit var binding: FragmentOpItemListBinding
    private var opItemList = ArrayList<OpItem>()
    private var itemAdapter = OpItemRVAdapter(opItemList)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOpItemListBinding.inflate(inflater,container,false)
        opItemList.apply {
            add(OpItem("실리카겔","김춘추의 창백한 피부","김춘추가 촬영 오기 전에 급히 김밥을 \n" +
                    "먹다가 체하는 바람에 창백해 졌다.\n" +
                    "그의 피부에서 착안했다.","아이템 구매 포인트",R.drawable.demo_skin))
            add(OpItem("BRANDOL","아이템 명","아이템 설명","아이템 구매 포인트",R.drawable.demo_hair))
            add(OpItem("BRANDOL","아이템 명","아이템 설명","아이템 구매 포인트",R.drawable.demo_pants))
            add(OpItem("BRANDOL","아이템 명","아이템 설명","아이템 구매 포인트",R.drawable.demo_shoes))
        }
        binding.itemListRv.layoutManager = LinearLayoutManager(activity)
        binding.itemListRv.adapter = itemAdapter
        return binding.root
    }

}
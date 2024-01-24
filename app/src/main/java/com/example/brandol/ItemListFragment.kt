package com.example.brandol

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.brandol.databinding.FragmentItemListBinding

class ItemListFragment : Fragment() {
    lateinit var binding: FragmentItemListBinding
    private var opItemList = ArrayList<OpItem>()
    private var itemAdapter = ItemRVAdapter(opItemList)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentItemListBinding.inflate(inflater,container,false)
        opItemList.apply {
            add(OpItem("BRANDOL","아이템 명","아이템 설명","아이템 구매 포인트",R.drawable.justexp))
            add(OpItem("BRANDOL","아이템 명","아이템 설명","아이템 구매 포인트",R.drawable.justexp))
            add(OpItem("BRANDOL","아이템 명","아이템 설명","아이템 구매 포인트",R.drawable.justexp))
            add(OpItem("BRANDOL","아이템 명","아이템 설명","아이템 구매 포인트",R.drawable.justexp))
        }
        binding.itemListRv.layoutManager = LinearLayoutManager(activity)
        binding.itemListRv.adapter = itemAdapter
        return binding.root
    }

}
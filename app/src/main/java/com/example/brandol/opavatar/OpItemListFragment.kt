package com.example.brandol.opavatar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.brandol.R
import com.example.brandol.adaptor.RV.OpItemRVAdapter
import com.example.brandol.collection.ItemModel
import com.example.brandol.databinding.FragmentOpItemListBinding

class OpItemListFragment : Fragment() {
    lateinit var binding: FragmentOpItemListBinding
    private var opItemList = ArrayList<ItemModel>()
    private var itemAdapter = OpItemRVAdapter(opItemList)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOpItemListBinding.inflate(layoutInflater,container,false)
        opItemList.apply {
            add(ItemModel(R.drawable.demo_skin, "Brand1", "피부", "Item1", "item1_info", "100p"))
            add(ItemModel(R.drawable.demo_skin, "Brand1", "|", "Item1", "item1_info", "100p"))
            add(ItemModel(R.drawable.demo_skin, "Brand1", "|", "Item1", "item1_info", "100p"))
            add(ItemModel(R.drawable.demo_skin, "Brand1", "|", "Item1", "item1_info", "100p"))
        }
        binding.itemListRv.layoutManager = LinearLayoutManager(activity)
        binding.itemListRv.adapter = itemAdapter
        return binding.root
    }

}
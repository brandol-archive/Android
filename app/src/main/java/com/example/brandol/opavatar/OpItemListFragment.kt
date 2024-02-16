package com.example.brandol.opavatar

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.brandol.ItemClickListener
import com.example.brandol.R
import com.example.brandol.adaptor.RV.OpItemRVAdapter
import com.example.brandol.collection.ItemModel2
import com.example.brandol.connection.RetrofitClient2
import com.example.brandol.connection.RetrofitObject
import com.example.brandol.databinding.FragmentOpItemListBinding
import com.example.brandol.searchCategory.AvatarStoreCategoryFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OpItemListFragment : Fragment(),ItemClickListener{
    lateinit var binding: FragmentOpItemListBinding
    private var opItemList = ArrayList<ItemModel2>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOpItemListBinding.inflate(layoutInflater, container, false)
        val token = getCurrentToken(requireContext())
        val call = RetrofitObject.getRetrofitService.getOtherItemData("Bearer $token", 1)
        call.enqueue(object : Callback<RetrofitClient2.ResponseItem> {
            override fun onResponse(
                call: Call<RetrofitClient2.ResponseItem>,
                response: Response<RetrofitClient2.ResponseItem>
            ) {
                if (response.isSuccessful) {
                    val response = response.body()
                    Log.d("LHJ", response.toString())
                    if (response != null) {
                        if (response.isSuccess) {
                            for (i in 0..response.result.size - 1) {
                                val myItemId = response.result[i].myItemId
                                val itemId: Long = response.result[i].itemId
                                val brandId: Long = response.result[i].itemId
                                val brandName: String = response.result[i].brandName
                                val itemName: String = response.result[i].itemName
                                val part: String = response.result[i].part
                                val description: String = response.result[i].description
                                val image: String = response.result[i].image
                                val price: Int = response.result[i].price
                                val createdAt: String = response.result[i].createdAt
                                val wearing: Boolean = response.result[i].wearing
                                opItemList.add(
                                    ItemModel2(
                                        myItemId,
                                        itemId,
                                        brandId,
                                        brandName,
                                        itemName,
                                        part,
                                        description,
                                        image,
                                        "hh",
                                        price,
                                        createdAt,
                                        wearing,
                                        false
                                    )
                                )
                            }
                            binding.itemListRv.layoutManager = LinearLayoutManager(activity)
                            binding.itemListRv.adapter =
                                OpItemRVAdapter(opItemList,this@OpItemListFragment)
                        }
                    }
                }
            }


            override fun onFailure(call: Call<RetrofitClient2.ResponseItem>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
        binding.itemListRv.adapter = OpItemRVAdapter(opItemList,this)
        return binding.root
    }

    override fun onItemClick(position: Int) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.main_frm,AvatarStoreCategoryFragment())
            .addToBackStack(null)
            .commit()
    }
    private fun getCurrentToken(context: Context): String? {
        val sharedPref =
            context.getSharedPreferences("Brandol", AppCompatActivity.MODE_PRIVATE)
        return sharedPref.getString("accessToken", null)
    }

}
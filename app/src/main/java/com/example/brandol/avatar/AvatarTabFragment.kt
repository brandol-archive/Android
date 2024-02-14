package com.example.brandol.avatar


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.brandol.ItemClickListener
import com.example.brandol.adaptor.RV.AvatarRVAdapter
import com.example.brandol.collection.ItemModel2
import com.example.brandol.collection.ItemModel
import com.example.brandol.connection.RetrofitClient2
import com.example.brandol.connection.RetrofitObject
import com.example.brandol.databinding.FragmentAvatarTabBinding
import com.example.brandol.dialog.CustomDetailInfoDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AvatarTabFragment : Fragment(), ItemClickListener {


    lateinit var binding: FragmentAvatarTabBinding
    private var category: String? = ""
    var itemList = ArrayList<ItemModel2>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAvatarTabBinding.inflate(inflater, container, false)
        //카테고리에 따라 탭레이아웃 분배
        //리사이클러뷰 연결
        splitByCategory()
        binding.avatartabItemlistRv.layoutManager = GridLayoutManager(context, 4)

        return binding.root
    }

    private fun splitByCategory() {
        category = arguments?.getString("category")
        when (category) {
            "전체" -> setAllItemTab()
            "피부" -> setTabByCategory("SKIN")
            "헤어" -> setTabByCategory("HAIR")
            "상의" -> setTabByCategory("TOP")
            "하의" -> setTabByCategory("BOTTOM")
            "신발" -> setTabByCategory("SHOES")
        }
    }

    //아바타 옷입히는 로직 구현


    private fun setAllItemTab() {
        itemList.clear()
        val token = getCurrentToken(requireContext())
        val call = RetrofitObject.getRetrofitService.getMyItemData("Bearer $token")
        call.enqueue(object : Callback<RetrofitClient2.ResponseItem> {
            override fun onResponse(
                call: Call<RetrofitClient2.ResponseItem>,
                response: Response<RetrofitClient2.ResponseItem>
            ) {
                Log.d("LHJ", response.toString())
                if (response.isSuccessful) {
                    val response = response.body()
                    //Log.d("LHJ", response.toString())
                    if (response != null) {
                        if (response.isSuccess) {
                            for(i in 0..response.result.size-1) {
                                val myItemId = response.result[i].myItemId
                                val itemId: Long = response.result[i].itemId
                                val brandId: Long = response.result[i].itemId
                                val brandName: String = response.result[i].brandName
                                val itemName: String = response.result[i].itemName
                                val part: String = response.result[i].part
                                val description: String = response.result[i].description
                                val image: String = response.result[i].image
                                val wearingImage : String = response.result[i].wearingImage
                                val price: Int = response.result[i].price
                                val createdAt: String = response.result[i].createdAt
                                val wearing: Boolean = response.result[i].wearing
                                var ischeck = false
                                itemList.add(
                                    ItemModel2(
                                        myItemId,
                                        itemId,
                                        brandId,
                                        brandName,
                                        itemName,
                                        part,
                                        description,
                                        image,
                                        //wearingImage,
                                        price,
                                        createdAt,
                                        wearing,
                                    )
                                )
                            }
                            val avatarAdapter = AvatarRVAdapter(itemList, this@AvatarTabFragment)
                            binding.avatartabItemlistRv.adapter =avatarAdapter
                            avatarAdapter.notifyDataSetChanged()
                        }
                    }

                }
            }

            override fun onFailure(call: Call<RetrofitClient2.ResponseItem>, t: Throwable) {
                val errorMessage = "Call Failed: ${t.message}"
                Log.d("LHJ", errorMessage)
            }

        })

        var avatarAdapter = AvatarRVAdapter(itemList, this)
        binding.avatartabItemlistRv.adapter =avatarAdapter
        avatarAdapter.notifyDataSetChanged()

    }

    private fun setTabByCategory(category: String) {
        itemList.clear()
        val token = getCurrentToken(requireContext())
        val call = RetrofitObject.getRetrofitService.getMyItemData("Bearer $token")
        call.enqueue(object : Callback<RetrofitClient2.ResponseItem> {
            override fun onResponse(
                call: Call<RetrofitClient2.ResponseItem>,
                response: Response<RetrofitClient2.ResponseItem>
            ) {
                Log.d("LHJ", response.toString())
                if (response.isSuccessful) {
                    val response = response.body()
                    //Log.d("LHJ", response.toString())
                    if (response != null) {
                        if (response.isSuccess) {
                            Log.d("LHJ", response.result.size.toString())
                            for(i in 0..response.result.size-1) {
                                if (response.result[i].part == category) {
                                    val myItemId = response.result[i].myItemId
                                    val itemId: Long = response.result[i].itemId
                                    val brandId: Long = response.result[i].itemId
                                    val brandName: String = response.result[i].brandName
                                    val itemName: String = response.result[i].itemName
                                    val part: String = response.result[i].part
                                    val description: String = response.result[i].description
                                    val image: String = response.result[i].image
                                    val wearingImage : String = response.result[i].wearingImage
                                    val price: Int = response.result[i].price
                                    val createdAt: String = response.result[i].createdAt
                                    val wearing: Boolean = response.result[i].wearing
                                    var ischeck = false
                                    itemList.add(
                                        ItemModel2(
                                            myItemId,
                                            itemId,
                                            brandId,
                                            brandName,
                                            itemName,
                                            part,
                                            description,
                                            image,
                                            //wearingImage,
                                            price,
                                            createdAt,
                                            wearing,
                                        )
                                    )
                                }
                            }
                            val avatarAdapter = AvatarRVAdapter(itemList, this@AvatarTabFragment)
                            binding.avatartabItemlistRv.adapter =avatarAdapter
                            avatarAdapter.notifyDataSetChanged()
                        }
                    }

                }
            }

            override fun onFailure(call: Call<RetrofitClient2.ResponseItem>, t: Throwable) {
                val errorMessage = "Call Failed: ${t.message}"
                Log.d("LHJ", errorMessage)
            }

        })


    }


    private fun getCurrentToken(context: Context): String?{
        val sharedPref = context.getSharedPreferences("Brandol", AppCompatActivity.MODE_PRIVATE)
        return sharedPref.getString("accessToken", null)
    }

    companion object {
        fun newInstance(category: String): AvatarTabFragment {
            var fragment = AvatarTabFragment()
            var args = Bundle()
            args.putString("category", category)
            fragment.arguments = args

            return fragment
        }
    }

    override fun onItemClick(position: Int,check : Boolean) {
        //클릭시 이름을 보내서 이름을 매개로 정보를 받게
        val itemobject = itemList.get(position)
        val itemWearingImage = itemobject.image
        val itemPart = itemobject.part
        val itemId = itemobject.itemId
        // Use the Kotlin extension in the fragment-ktx artifact.
        var bundle = Bundle()
        bundle.putString("part",itemPart)
        bundle.putString("wearingImage",itemWearingImage)
        bundle.putLong("id",itemId)
        bundle.putBoolean("check",check)
        parentFragmentManager.setFragmentResult("requestKey", bundle)

    }

    override fun showCustomDialog(position: Int) {
        val itemobject = itemList.get(position)
        val context = context
        val dialog = CustomDetailInfoDialog(context!!, itemobject.itemName, itemobject.brandName,itemobject.image,itemobject.description,itemobject.price,itemobject.part)
        //다이얼로그 위치 조정
        val params = dialog.window?.attributes
        params?.gravity = Gravity.TOP or Gravity.LEFT// 원하는 위치로 변경합니다.
        params?.x = 100 // x 위치 조정
        params?.y = 100 // y 위치 조정
        dialog.window?.setAttributes(params);
        //배경 어두워지는 효과 제거
        dialog.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        dialog.show()
    }
}
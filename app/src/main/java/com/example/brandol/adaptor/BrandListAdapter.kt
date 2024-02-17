package com.example.brandol.adaptor// BrandListAdapter.kt
import android.content.Context
import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.brandol.dialog.CustomDeleteDialog
import com.example.brandol.R
import com.example.brandol.connection.RetrofitClient2
import com.example.brandol.connection.RetrofitObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Collections

class BrandListAdapter(private val brandList: MutableList<RetrofitClient2.memberBrandListDtoList>)
    : RecyclerView.Adapter<BrandListAdapter.BrandViewHolder>() {

    inner class BrandViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val brandImageView: ImageView = itemView.findViewById(R.id.brand_iv)
        private val brandNameTextView: TextView = itemView.findViewById(R.id.brand_name_tv)
        private val brandInfoTextView: TextView = itemView.findViewById(R.id.brand_info_tv)
        private val deleteTextView: TextView = itemView.findViewById(R.id.delete_tv)

//        fun bind(brandData: BrandData) {
//            // 브랜드 데이터를 뷰에 설정
//            brandImageView.setImageResource(brandData.brandImageResourceId)
//            brandNameTextView.text = brandData.brandName
//            brandInfoTextView.text = brandData.brandInfo
//
//            deleteTextView.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
//
//            // 삭제 텍스트를 클릭했을 때의 동작 설정
//            deleteTextView.setOnClickListener {
//                showDeleteDialog(brandData)
//            }
//        }

        private fun getCurrentToken(context: Context): String? {
            val sharedPref = context.getSharedPreferences("Brandol", Context.MODE_PRIVATE)
            return sharedPref.getString("accessToken", null)
        }


//        private fun unsubscribeBrandData() {
//            val token = getCurrentToken()
//            val call = RetrofitObject.getRetrofitService.unsubscribeBrand("Bearer $token", 1)
//            call.enqueue(object : Callback<RetrofitClient2.UnsubscribeBrand> {
//                override fun onResponse(
//                    call: Call<RetrofitClient2.UnsubscribeBrand>,
//                    response: Response<RetrofitClient2.UnsubscribeBrand>
//                ) {
//                    Log.d("ikj_brand_unsubscribe", response.toString())
//                    if (response.isSuccessful) {
//                        val responseData = response.body()
//                        Log.d("ikj_brand_unsubscribe", responseData.toString())
//                        if (responseData != null && responseData.isSuccess) {
//
//                        }
//                    }
//                }
//
//                override fun onFailure(call: Call<RetrofitClient2.UnsubscribeBrand>, t: Throwable) {
//                    val errorMessage = "Call Failed: ${t.message}"
//                    Log.d("ikj_brand_unsubscribe", errorMessage)
//                }
//            })
//        }

        fun bind(brandData: RetrofitClient2.memberBrandListDtoList) {
            // Update the view with server data
            Glide.with(itemView.context).load(brandData.profileImage).into(brandImageView) // Replace with actual image loading
            brandNameTextView.text = brandData.brandName
            brandInfoTextView.text = "Brand Info"  // You need to get brand info from the server

            deleteTextView.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);

            // 삭제 텍스트를 클릭했을 때의 동작 설정
            deleteTextView.setOnClickListener {
                showDeleteDialog(brandData)
            }
        }


//        private fun showDeleteDialog(brandData: RetrofitClient2.memberBrandListDtoList) {
//            val context = itemView.context
//            val confirmButtonText = "확인"  // 수정: "확인" 버튼 텍스트 설정
//            val dialog = CustomDeleteDialog(context, "정말로 브랜드 리스트에서\n삭제하시겠습니까?", confirmButtonText, {
//                // 삭제 로직
//                //removeItem(brandData)
//            }, {
//
//            })
//
//            dialog.show()
//        }

        private fun showDeleteDialog(brandData: RetrofitClient2.memberBrandListDtoList) {
            val context = itemView.context
            val confirmButtonText = "확인"
            val dialog = CustomDeleteDialog(context, "정말로 브랜드 리스트에서\n삭제하시겠습니까?", confirmButtonText, {
                // Call the API to delete the brand
                val token = getCurrentToken(context)
                val brandId = brandData.brandId // Replace with the actual method to get brandId

                if (token != null && brandId != null) {
                    val call = RetrofitObject.getRetrofitService.unsubscribeBrand("Bearer $token", brandId)
                    call.enqueue(object : Callback<RetrofitClient2.UnsubscribeBrand> {
                        override fun onResponse(
                            call: Call<RetrofitClient2.UnsubscribeBrand>,
                            response: Response<RetrofitClient2.UnsubscribeBrand>
                        ) {
                            Log.d("ikj_brand_unsubscribe", response.toString())
                            if (response.isSuccessful) {
                                val responseData = response.body()
                                Log.d("ikj_brand_unsubscribe", responseData.toString())
                                if (responseData != null && responseData.isSuccess) {
                                    // Brand deleted successfully, you might want to update UI
                                    removeItem(brandData)
                                }
                            }
                        }

                        override fun onFailure(call: Call<RetrofitClient2.UnsubscribeBrand>, t: Throwable) {
                            val errorMessage = "Call Failed: ${t.message}"
                            Log.d("ikj_brand_unsubscribe", errorMessage)
                        }
                    })
                }
            }, {

            })

            dialog.show()
        }

    }

        private fun removeItem(brandData: RetrofitClient2.memberBrandListDtoList) {
        val position = brandList.indexOf(brandData)
        if (position != -1) {
            brandList.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun setBrandList(newBrandList: List<RetrofitClient2.memberBrandListDtoList>) {
        brandList.clear()
        brandList.addAll(newBrandList)
        notifyDataSetChanged()
    }

    fun setEmptyBrandList() {
        brandList.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_brand_management, parent, false)
        return BrandViewHolder(view)
    }

    override fun onBindViewHolder(holder: BrandViewHolder, position: Int) {
        val brandData = brandList[position]
        holder.bind(brandData)
    }

    override fun getItemCount(): Int {
        return brandList.size
    }

    // 외부에서 더미 데이터를 설정하는 메서드
//    fun setBrandList(newBrandList: List<BrandData>) {
//        brandList.clear()
//        brandList.addAll(newBrandList)
//        notifyDataSetChanged()
//    }

//    private fun removeItem(brandData: RetrofitClient2.memberBrandListDtoList) {
//        val position = brandList.indexOf(brandData)
//        if (position != -1) {
//            brandList.removeAt(position)
//            notifyItemRemoved(position)
//        }
//    }

    fun attachItemTouchHelper(recyclerView: RecyclerView) {
        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                // 드래그 액션 처리
                val from = viewHolder.adapterPosition
                val to = target.adapterPosition
                Collections.swap(brandList, from, to)
                notifyItemMoved(from, to)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                // 스와이프 액션 처리
                val position = viewHolder.adapterPosition
                //removeItem(brandList[position])
            }
        })

        itemTouchHelper.attachToRecyclerView(recyclerView)
    }
}
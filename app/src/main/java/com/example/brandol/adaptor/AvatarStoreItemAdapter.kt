package com.example.brandol.adaptor

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.brandol.R
import com.example.brandol.collection.ItemModel
import com.example.brandol.connection.RetrofitClient2
import com.example.brandol.connection.RetrofitObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AvatarStoreItemAdapter : ListAdapter<ItemModel, AvatarStoreItemAdapter.ViewHolder>(
    ItemModelDiffCallback()
) {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val brandLogo: ImageView = itemView.findViewById(R.id.brand_logo_store_iv)
        val brandName: TextView = itemView.findViewById(R.id.brand_name_store_tv)
        val tabCategory: TextView = itemView.findViewById(R.id.tab_category_tv)
        val itemName: TextView = itemView.findViewById(R.id.item_name_store_tv)
        val itemInfo: TextView = itemView.findViewById(R.id.item_info_store_tv)
        val itemPoint: TextView = itemView.findViewById(R.id.item_purchase_button)
        val purchaseButton: Button = itemView.findViewById(R.id.item_purchase_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_avatar_store, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = getItem(position)

        // 아이템 데이터를 뷰에 설정
        Glide.with(holder.itemView.context)
            .load(currentItem.brandLogoRes) // brandLogo가 이미지 URL이라고 가정
            .into(holder.brandLogo)
        holder.brandName.text = currentItem.brandName
        holder.tabCategory.text = currentItem.tabCategory
        holder.itemName.text = currentItem.itemName
        holder.itemInfo.text = currentItem.itemInfo
        holder.itemPoint.text = currentItem.itemPoint

        // 버튼 클릭 이벤트 처리
        holder.purchaseButton.setOnClickListener {
            // 현재 아이템에 접근하여 showDeleteDialog 메서드 호출
            showDialog(currentItem, holder.itemView.context)
        }

    }

    private fun showDialog(currentItem: ItemModel, context: Context) {
        //val context = itemView.context

        // 다이얼로그를 직접 생성하고 커스텀 레이아웃 설정
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_delete_brand, null)
        val dialog = AlertDialog.Builder(context)
            .setView(dialogView)
            .create()

        // 커스텀 다이얼로그 레이아웃에서 각 뷰 가져오기
        val deleteTextView_purchase: AppCompatTextView = dialogView.findViewById(R.id.dialog_delete_tv)
        val noButton: AppCompatButton = dialogView.findViewById(R.id.dialog_no_btn)
        val yesButton: AppCompatButton = dialogView.findViewById(R.id.dialog_yes_btn)

        // 브랜드 데이터 정보를 다이얼로그에 설정
        //deleteTextView.text = "정말로 ${brandData.brandName}을(를) 삭제하시겠습니까?"
        deleteTextView_purchase.text = "\n\n\n\n\n해당 아이템을 구매하시겠습니까?\n\n\n\n\n"


        // 확인 버튼 클릭 시 동작
        yesButton.setOnClickListener {
            dialog.dismiss()
            showPurchaseDialog(currentItem, context)
        }

        // 취소 버튼 클릭 시 동작
        noButton.setOnClickListener {
            dialog.dismiss()
        }

        // 다이얼로그 표시
        dialog.show()
    }

    private fun showPurchaseDialog(currentItem: ItemModel, context: Context) {
        //val context = itemView.context

        // 다이얼로그를 직접 생성하고 커스텀 레이아웃 설정
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_delete_brand, null)
        val dialog = AlertDialog.Builder(context)
            .setView(dialogView)
            .create()

        // 커스텀 다이얼로그 레이아웃에서 각 뷰 가져오기
        val purchaseTextView: AppCompatTextView = dialogView.findViewById(R.id.dialog_delete_tv)
        val okButton: AppCompatButton = dialogView.findViewById(R.id.dialog_yes_btn)

        // 구매 정보를 다이얼로그에 설정
        purchaseTextView.text = "\n\n\n아이템을 성공적으로 구매했습니다.\n아이템은 아바타에서 확인할 수 있습니다.\n\n\n"

        okButton.text = "확인"

        // 확인 버튼 클릭 시 동작
        okButton.setOnClickListener {
            dialog.dismiss()
            purchaseItem(context, currentItem.itemId)
        }

        // 취소 버튼 제거
        val cancelButton: AppCompatButton = dialogView.findViewById(R.id.dialog_no_btn)
        cancelButton.visibility = View.GONE

        // 다이얼로그 표시
        dialog.show()
    }

    private fun getCurrentToken(context: Context): String? {
        val sharedPref = context.getSharedPreferences("Brandol", Context.MODE_PRIVATE)
        return sharedPref.getString("accessToken", null)
    }

    private fun purchaseItem(context: Context, itemId: Int) {
        val token = getCurrentToken(context)
        val call = RetrofitObject.getRetrofitService.purchaseItem("Bearer $token", itemId.toInt())
        call.enqueue(object : Callback<RetrofitClient2.PurchaseItem> {
            override fun onResponse(
                call: Call<RetrofitClient2.PurchaseItem>,
                response: Response<RetrofitClient2.PurchaseItem>
            ) {
                Log.d("ikj", response.toString())
                if (response.isSuccessful) {
                    val response = response.body()
                    Log.d("ikj", response.toString())
                    if (response != null && response.isSuccess) {
                        //Log.d("ikj", response.result)
                        Log.d("ikj", "성공")
                        // 아이템 구매 성공 시 처리 로직을 추가하세요
                    }
                }
            }

            override fun onFailure(call: Call<RetrofitClient2.PurchaseItem>, t: Throwable) {
                val errorMessage = "Call Failed: ${t.message}"
                Log.d("ikj", errorMessage)
            }
        })
    }

}

class ItemModelDiffCallback : DiffUtil.ItemCallback<ItemModel>() {
    override fun areItemsTheSame(oldItem: ItemModel, newItem: ItemModel): Boolean {
        return oldItem.itemName == newItem.itemName
    }

    override fun areContentsTheSame(oldItem: ItemModel, newItem: ItemModel): Boolean {
        return oldItem == newItem
    }
}
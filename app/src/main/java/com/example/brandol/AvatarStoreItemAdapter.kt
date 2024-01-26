package com.example.brandol

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

data class ItemModel(
    val brandLogoRes: Int,
    val brandName: String,
    val tabCategory: String,
    val itemName: String,
    val itemInfo: String,
    val itemPoint: String
)

class AvatarStoreItemAdapter : ListAdapter<ItemModel, AvatarStoreItemAdapter.ViewHolder>(ItemModelDiffCallback()) {

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
        holder.brandLogo.setImageResource(currentItem.brandLogoRes)
        holder.brandName.text = currentItem.brandName
        holder.tabCategory.text = currentItem.tabCategory
        holder.itemName.text = currentItem.itemName
        holder.itemInfo.text = currentItem.itemInfo
        holder.itemPoint.text = currentItem.itemPoint

        // 버튼 클릭 이벤트 처리
        holder.purchaseButton.setOnClickListener {
            // 구매 버튼 클릭 시 동작 구현
            // 예: Toast 메시지 출력
            // Toast.makeText(holder.itemView.context, "구매 버튼 클릭 - ${currentItem.itemName}", Toast.LENGTH_SHORT).show()
        }
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

package com.example.brandol.adaptor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.brandol.connection.RetrofitClient2
import com.example.brandol.databinding.ItemSearchAvatarStoreBinding

class SearchAvarAdapter(private val itemList: List<RetrofitClient2.AvatarStoreItem>) :
    RecyclerView.Adapter<SearchAvarAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemSearchAvatarStoreBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: RetrofitClient2.AvatarStoreItem) {
            // 아이템의 데이터를 바인딩
            binding.avartarStoreItemIv.setImageResource(item.itemImageResId)
            binding.avartarStoreItemNameTv.text = item.itemName
            binding.avartarStoreItemTypeTv.text = item.itemType
            binding.avartarStoreItemContentTv.text = item.itemContent
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSearchAvatarStoreBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size
}

package com.example.brandol.adaptor.VP

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.brandol.connection.RetrofitClient2
import com.example.brandol.databinding.ItemSearchUserBinding

class SearchUserAdapter (private val itemList: List<RetrofitClient2.UserItem>) :
    RecyclerView.Adapter<SearchUserAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemSearchUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: RetrofitClient2.UserItem) {
            // 아이템의 데이터를 바인딩
            binding.userBoxIv.setImageResource(item.boxImageResId)
            binding.userCharIv.setImageResource(item.charImageResId)
            binding.userCharTv.text = item.charName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSearchUserBinding.inflate(
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

package com.example.brandol.adaptor.VP

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.brandol.connection.RetrofitClient2
import com.example.brandol.databinding.ItemSearchContentsBinding

class SearchContentsAdapter (private val itemList: List<RetrofitClient2.ContentItem>) :
    RecyclerView.Adapter<SearchContentsAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemSearchContentsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: RetrofitClient2.ContentItem) {
            // 아이템의 데이터를 바인딩
            binding.itemBoxIv.setImageResource(item.boxImageResId)
            binding.contentImageIv.setImageResource(item.contentImageResId)
            binding.miniCharIv.setImageResource(item.miniCharImageResId)
            binding.content1NameTv.text = item.contentName
            binding.content1TitleTv.text = item.contentTitle
            binding.content1ContentTv.text = item.contentContent
            binding.content1RecentTextTv.text = item.recentText
            binding.content1LikeCountTv.text = item.likeCount.toString()
            binding.content1ChatCountTv.text = item.chatCount.toString()
            binding.content1DateTv.text = item.date
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSearchContentsBinding.inflate(
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
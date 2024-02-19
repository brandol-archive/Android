package com.example.brandol.adaptor.VP

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.brandol.connection.RetrofitClient2
import com.example.brandol.databinding.ItemSearchBrandBinding

class SearchBrandAdapter(private val itemList: List<RetrofitClient2.SearchBrandItem>) :
    RecyclerView.Adapter<SearchBrandAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemSearchBrandBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: RetrofitClient2.SearchBrandItem) {
            // 아이템의 데이터를 바인딩
            binding.searchBBrandBoxIv.setImageResource(item.boxImageResId)
            binding.searchBDrandolLogoIv.setImageResource(item.logoImageResId)
            binding.searchBBrandNameTv.text = item.brandName
            //binding.searchBBrandDetailTv.text = item.brandDetail
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSearchBrandBinding.inflate(
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

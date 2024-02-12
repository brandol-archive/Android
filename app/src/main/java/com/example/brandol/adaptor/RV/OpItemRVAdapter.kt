package com.example.brandol.adaptor.RV

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.brandol.ItemClickListener
import com.example.brandol.collection.ItemModel2
import com.example.brandol.R

class OpItemRVAdapter(private val opItemList: List<ItemModel2>) :
    RecyclerView.Adapter<OpItemRVAdapter.ItemViewHolder>() {
    var itemClickListener: ItemClickListener? = null
    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemName: TextView = itemView.findViewById(R.id.item_name_store_tv)
        val brandName: TextView = itemView.findViewById(R.id.brand_name_store_tv)
        val itemImage: ImageView = itemView.findViewById(R.id.brand_logo_store_iv)
        val itemInfo: TextView = itemView.findViewById(R.id.item_info_store_tv)
        val storeButton: Button = itemView.findViewById(R.id.item_purchase_button)
        val tabCategory: TextView = itemView.findViewById(R.id.tab_category_tv)

        init {
            storeButton.setOnClickListener{
                itemClickListener?.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_avatar_store, parent, false)
        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.itemName.text = opItemList.get(position).itemName
        holder.brandName.text = opItemList.get(position).brandName
        holder.itemInfo.text = opItemList.get(position).itemInfo
        holder.itemImage.setImageResource(opItemList.get(position).brandLogoRes)
        holder.tabCategory.text = opItemList.get(position).tabCategory
    }

    override fun getItemCount(): Int {
        return opItemList.size
    }
}
package com.example.brandol

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class OpBrandRVAdpater(private val brandlist:List<BrandData>): RecyclerView.Adapter<OpBrandRVAdpater.BrandViewHolder>() {

    inner class BrandViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val name : TextView = itemView.findViewById(R.id.brand_name_tv)
        val explain : TextView = itemView.findViewById(R.id.brand_info_tv)
        val image : ImageView = itemView.findViewById(R.id.brand_iv)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BrandViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_brand_home, parent, false)
        return BrandViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BrandViewHolder, position: Int) {
        holder.name.text = brandlist.get(position).brandName
        holder.explain.text = brandlist.get(position).brandInfo
        holder.image.setImageResource(brandlist.get(position).brandImageResourceId)
    }

    override fun getItemCount(): Int {
        return brandlist.size
    }
}
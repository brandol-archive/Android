package com.example.brandol

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BrandRVAdpater(private val brandlist:List<OpBrand>): RecyclerView.Adapter<BrandRVAdpater.BrandViewHolder>() {

    inner class BrandViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val name : TextView = itemView.findViewById(R.id.item_brand_name_tv)
        val explain : TextView = itemView.findViewById(R.id.item_brand_explain_tv)
        val image : ImageView = itemView.findViewById(R.id.item_brand_img_iv)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BrandViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_brand, parent, false)
        return BrandViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BrandViewHolder, position: Int) {
        holder.name.text = brandlist.get(position).name
        holder.explain.text = brandlist.get(position).explain
        holder.image.setImageResource(brandlist.get(position).image)
    }

    override fun getItemCount(): Int {
        return brandlist.size
    }
}
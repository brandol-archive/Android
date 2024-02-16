package com.example.brandol.adaptor.RV

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.brandol.collection.BrandData
import com.example.brandol.R
import com.example.brandol.collection.OpBrand

class OpBrandRVAdapter(private val brandlist:List<OpBrand>): RecyclerView.Adapter<OpBrandRVAdapter.BrandViewHolder>() {

    inner class BrandViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val name : TextView = itemView.findViewById(R.id.brand_name_tv)
        val explain : TextView = itemView.findViewById(R.id.brand_info_tv)
        val sequence : TextView =itemView.findViewById(R.id.fan_count)
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
        holder.explain.text = brandlist.get(position).description
        holder.sequence.text = brandlist.get(position).sequence.toString()
        Glide.with(holder.itemView.context).load(brandlist.get(position).profileImage).into(holder.image)
    }
    override fun getItemCount(): Int {
        return brandlist.size
    }
}
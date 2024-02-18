package com.example.brandol.adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.brandol.ItemClickListener
import com.example.brandol.R
import com.example.brandol.connection.RetrofitClient2


class CategoryBrandAdapter : RecyclerView.Adapter<CategoryBrandAdapter.CategoryBrandViewHolder>() {
    private val brandItems = mutableListOf<RetrofitClient2.BrandDto>()
    var itemClickListener: ItemClickListener? = null


    // Data setting and updating
    fun setCategoryBrandData(brandList: List<RetrofitClient2.BrandDto>) {
        brandItems.clear()
        brandItems.addAll(brandItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryBrandViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_brand_home, parent, false)
        return CategoryBrandViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryBrandViewHolder, position: Int) {
        holder.bind(brandItems[position])
    }

    override fun getItemCount(): Int {
        return brandItems.size
    }
    /*
    fun setEmptyBrandList() {
        brandItems.clear()
        notifyDataSetChanged()
    }*/


    inner class CategoryBrandViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val brandImageView: ImageView = itemView.findViewById(R.id.drandol_logo1_iv)
        private val brandNameTextView: TextView = itemView.findViewById(R.id.brand_name1_tv)
        private val brandInfoTextView: TextView = itemView.findViewById(R.id.brand_name1_detail_tv)

        init {
            itemView.setOnClickListener {
                itemClickListener?.onItemClick(adapterPosition)
            }
        }

        // ViewHolder data binding
        fun bind(brand: RetrofitClient2.BrandDto) {
            // Set brand profile image (use an image loading library)
            Glide.with(itemView.context).load(brand.brandProfileImage).into(brandImageView)

            // Set brand name
            brandNameTextView.text = brand.brandName

            //brandInfoTextView.text = brand.
            brandInfoTextView.text = brand.brandDescription

            // Set a brief description of the brand
            //brandInfoTextView.text = brand.description ?: ""

        }
    }


}
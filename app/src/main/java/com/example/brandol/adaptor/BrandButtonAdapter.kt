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
class BrandButtonAdapter : RecyclerView.Adapter<BrandButtonAdapter.ButtonViewHolder>() {

    private val buttonItems = mutableListOf<RetrofitClient2.memberBrandListDtoList>()
    var itemClickListener: ItemClickListener? = null

    // Data setting and updating
    fun setBrandButtonData(brandList: List<RetrofitClient2.memberBrandListDtoList>) {
        buttonItems.clear()
        buttonItems.addAll(brandList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ButtonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_brand_home, parent, false)
        return ButtonViewHolder(view)
    }

    override fun onBindViewHolder(holder: ButtonViewHolder, position: Int) {
        holder.bind(buttonItems[position])
    }

    override fun getItemCount(): Int {
        return buttonItems.size
    }

    fun setEmptyBrandList() {
        buttonItems.clear()
        notifyDataSetChanged()
    }


    inner class ButtonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val brandImageView: ImageView = itemView.findViewById(R.id.brand_iv)
        private val brandNameTextView: TextView = itemView.findViewById(R.id.brand_name_tv)
        private val brandInfoTextView: TextView = itemView.findViewById(R.id.brand_info_tv)
        private val fanCountTextView: TextView = itemView.findViewById(R.id.fan_count)

        init {
            itemView.setOnClickListener {
                itemClickListener?.onItemClick(adapterPosition)
            }
        }

        // ViewHolder data binding
        fun bind(brand: RetrofitClient2.memberBrandListDtoList) {
            // Set brand profile image (use an image loading library)
            Glide.with(itemView.context).load(brand.profileImage).into(brandImageView)

            // Set brand name
            brandNameTextView.text = brand.brandName

            brandInfoTextView.text = brand.brandDescription


            // Set a brief description of the brand
            //brandInfoTextView.text = brand.description ?: ""

            // Set the fan count (replace '0' with the actual fan count)
            fanCountTextView.text = brand.sequence.toString()
        }
    }
}

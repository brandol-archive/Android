package com.example.brandol.adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.brandol.Home.HomeFragment
import com.example.brandol.ItemClickListener
import com.example.brandol.R

class BrandButtonAdapter : RecyclerView.Adapter<BrandButtonAdapter.ButtonViewHolder>() {

    private val buttonItems = mutableListOf<HomeFragment.DummyData>()
    var itemClickListener: ItemClickListener? = null


    companion object {
        private const val VIEW_TYPE_ITEM = 1
        private const val VIEW_TYPE_PLUS_BUTTON = 2
    }


    fun addButton(buttonData: HomeFragment.DummyData) {
        buttonItems.add(buttonData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ButtonViewHolder {
        return when (viewType) {
            VIEW_TYPE_ITEM -> {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_brand_home, parent, false)
                ButtonViewHolder(view)
            }
            VIEW_TYPE_PLUS_BUTTON -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_brand_home, parent, false)
                ButtonViewHolder(view, isPlusButton = true)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: ButtonViewHolder, position: Int) {
        if (position < buttonItems.size) {
            val buttonData = buttonItems[position]
            holder.bind(buttonData)
        } else {
            holder.bindPlusButton()
        }
    }

    override fun getItemCount(): Int {
        return buttonItems.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < buttonItems.size) {
            VIEW_TYPE_ITEM
        } else {
            VIEW_TYPE_PLUS_BUTTON
        }
    }

    inner class ButtonViewHolder(itemView: View, isPlusButton: Boolean = false) :
        RecyclerView.ViewHolder(itemView) {
        private val brandImageView: ImageView = itemView.findViewById(R.id.brand_iv)
        private val brandNameTextView: TextView = itemView.findViewById(R.id.brand_name_tv)
        private val brandInfoTextView: TextView = itemView.findViewById(R.id.brand_info_tv)
        private val fanCountTextView: TextView = itemView.findViewById(R.id.fan_count_tv)
        private val plusButtonImageView: Button = itemView.findViewById(R.id.plus_button)

        init {
            if (!isPlusButton) {
                itemView.setOnClickListener {
                    itemClickListener?.onItemClick(adapterPosition)
                }
            } else {
                // 플러스 버튼인 경우 클릭 이벤트 리스너 설정
                plusButtonImageView.setOnClickListener{
                    itemClickListener?.onItemClick(adapterPosition)
                }
            }
        }

        fun bind(buttonData: HomeFragment.DummyData) {
            brandImageView.visibility = View.VISIBLE
            brandImageView.setImageResource(buttonData.brandImageResourceId)
            brandNameTextView.text = buttonData.brandName
            brandInfoTextView.text = buttonData.brandInfo
            fanCountTextView.text = buttonData.fanCount
        }

        fun bindPlusButton() {
            brandImageView.visibility = View.GONE
            brandNameTextView.text = ""
            brandInfoTextView.text = ""
            fanCountTextView.text = ""
            plusButtonImageView.visibility = View.VISIBLE
        }
    }
}



//package com.example.brandol.adaptor
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//import com.example.brandol.ItemClickListener
//import com.example.brandol.R
//import com.example.brandol.connection.RetrofitClient2
//
//
//class BrandButtonAdapter : RecyclerView.Adapter<BrandButtonAdapter.ButtonViewHolder>() {
//
//    private val buttonItems = mutableListOf<RetrofitClient2.GetBrandHeader>()
//    var itemClickListener: ItemClickListener? = null
//
//    // 데이터 설정 및 갱신
//    fun setData(brandList: List<RetrofitClient2.GetBrandHeader>) {
//        buttonItems.clear()
//        buttonItems.addAll(brandList)
//        notifyDataSetChanged()
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ButtonViewHolder {
//        val view =
//            LayoutInflater.from(parent.context).inflate(R.layout.item_brand_home, parent, false)
//        return ButtonViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: ButtonViewHolder, position: Int) {
//        holder.bind(buttonItems[position])
//    }
//
//    override fun getItemCount(): Int {
//        return buttonItems.size
//    }
//
//    inner class ButtonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        private val brandImageView: ImageView = itemView.findViewById(R.id.brand_iv)
//        private val brandNameTextView: TextView = itemView.findViewById(R.id.brand_name_tv)
//        private val brandInfoTextView: TextView = itemView.findViewById(R.id.brand_info_tv)
//        private val fanCountTextView: TextView = itemView.findViewById(R.id.fan_count_tv)
//
//        init {
//            itemView.setOnClickListener {
//                itemClickListener?.onItemClick(adapterPosition)
//            }
//        }
//
//        // 뷰홀더에 데이터 바인딩
//        fun bind(brandHeader: RetrofitClient2.GetBrandHeader) {
//            // 브랜드 프로필 이미지 설정 (이미지 로딩 라이브러리를 사용하면 좋습니다)
//            Glide.with(itemView.context).load(brandHeader.result.brandPreviewDto.brand_profile).into(brandImageView)
//
//            // 브랜드 이름 설정
//            brandNameTextView.text = brandHeader.result.brandPreviewDto.brand_name
//
//            // 브랜드에 대한 간단한 설명 설정
//            brandInfoTextView.text = brandHeader.result.brandPreviewDto.brand_description
//
//            // 팬 수 설정
//            fanCountTextView.text = brandHeader.result.brandPreviewDto.brand_fan.toString()
//        }
//    }
//}



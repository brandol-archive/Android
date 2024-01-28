package com.example.brandol

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class AvartarRVAdapter(private val avartarList: List<Avartar>) : RecyclerView.Adapter<AvartarRVAdapter.MyViewHolder>() {

    var itemClickListener: ItemClickListener? = null
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // ViewHolder에서 사용할 뷰들을 정의
        val image: ImageView = itemView.findViewById(R.id.item_stuff_image_iv)

        init {
            itemView.setOnClickListener{
                itemClickListener?.onItemClick(adapterPosition)
            }
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_avartar, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AvartarRVAdapter.MyViewHolder, position: Int) {
        holder.image.setImageResource(avartarList.get(position).image!!)
        holder.itemView.setOnClickListener{
            holder.image.setBackgroundResource(R.drawable.selector_item)
        }
    }

    override fun getItemCount(): Int {
       return avartarList.size
    }


}
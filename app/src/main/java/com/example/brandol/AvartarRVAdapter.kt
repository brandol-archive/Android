package com.example.brandol

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class AvartarRVAdapter(private val stuffList: List<Stuff>) : RecyclerView.Adapter<AvartarRVAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // ViewHolder에서 사용할 뷰들을 정의
        val image: ImageView = itemView.findViewById(R.id.item_stuff_image)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_stuff, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AvartarRVAdapter.MyViewHolder, position: Int) {
        holder.image.setImageResource(stuffList.get(position).image!!)
    }

    override fun getItemCount(): Int {
       return stuffList.size
    }


}
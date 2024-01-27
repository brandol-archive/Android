package com.example.brandol

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MessageRVAdapter(private val messagelist: List<Message>): RecyclerView.Adapter<MessageRVAdapter.MessageViewHolder>() {


    var itemClickListener: ItemClickListener? = null

    inner class MessageViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        // ViewHolder에서 사용할 뷰들을 정의
        val profile: ImageView = itemView.findViewById(R.id.item_message_profile_iv)
        val name : TextView = itemView.findViewById(R.id.item_message_name_tv)
        val contents : TextView = itemView.findViewById(R.id.item_message_contents_tv)
        val time: TextView = itemView.findViewById(R.id.item_message_time_tv)
        val quantity : TextView = itemView.findViewById(R.id.item_message_quantity_tv)

        init {
            itemView.setOnClickListener{
                itemClickListener?.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MessageViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_message, parent, false)
        return MessageViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.profile.setImageResource(messagelist.get(position).profile!!)
        holder.name.text = messagelist.get(position).name
        holder.contents.text = messagelist.get(position).contents
        holder.time.text = messagelist.get(position).time
        holder.quantity.text = messagelist.get(position).quantity.toString()
    }


    override fun getItemCount(): Int {
        return messagelist.size
    }
}

package com.example.brandol

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MessageRVAdapter(private val messagelist: List<Message>,private var listener: ItemClickListener) :
    RecyclerView.Adapter<MessageRVAdapter.MessageViewHolder>() {
    private var handler = Handler(Looper.getMainLooper())

    inner class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // ViewHolder에서 사용할 뷰들을 정의
        val profile: ImageView = itemView.findViewById(R.id.item_message_profile_iv)
        val name: TextView = itemView.findViewById(R.id.item_message_name_tv)
        val contents: TextView = itemView.findViewById(R.id.item_message_contents_tv)
        val time: TextView = itemView.findViewById(R.id.item_message_time_tv)
        val quantity: TextView = itemView.findViewById(R.id.item_message_quantity_tv)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }

            itemView.setOnTouchListener { v, event ->
                when (event.actionMasked) {
                    MotionEvent.ACTION_DOWN -> {
                        val startTime = System.currentTimeMillis()
                        v.tag = startTime // 시작 시간을 View의 tag에 저장
                        handler.postDelayed({
                            listener.showCustomDialog(adapterPosition)
                        }, 1000)
                    }

                    MotionEvent.ACTION_UP -> {
                        handler.removeCallbacksAndMessages(null)
                        val endTime = System.currentTimeMillis()
                        val startTime = v.tag as? Long ?: 0
                        val duration = endTime - startTime
                        if (duration < 1000) { // 터치 시간이 1초보다 작으면 클릭으로 처리
                            v.performClick() // 클릭 이벤트 발생
                        }
                    }
                }
                true
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MessageViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_message, parent, false)
        return MessageViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.profile.setImageResource(messagelist.get(position).profile!!)
        holder.name.text = messagelist.get(position).name
        holder.contents.text = messagelist.get(position).contents
        holder.time.text = messagelist.get(position).time
        holder.quantity.text = messagelist.get(position).quantity.toString()
    }


    fun removeItem(){

    }
    override fun getItemCount(): Int {
        return messagelist.size
    }
}

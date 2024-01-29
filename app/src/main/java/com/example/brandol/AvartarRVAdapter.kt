package com.example.brandol


import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class AvartarRVAdapter(private val avartarList: List<Avartar>,private val listener: ItemClickListener) : RecyclerView.Adapter<AvartarRVAdapter.MyViewHolder>() {
    private var handler = Handler(Looper.getMainLooper())
    private var ischeck : Boolean = false

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // ViewHolder에서 사용할 뷰들을 정의
        val image: ImageView = itemView.findViewById(R.id.item_stuff_image_iv)
        val backgorund : ImageView = itemView.findViewById(R.id.item_stuff_image_iv)
        init {
            itemView.setOnClickListener {
                if (ischeck == false) {
                    backgorund.setBackgroundResource(R.drawable.selector_click_item)
                    ischeck = true
                } else {
                    backgorund.setBackgroundResource(R.drawable.object_default_background)
                    ischeck = false
                }
                listener.onItemClick(adapterPosition)
            }


            itemView.setOnTouchListener { v, event ->
                when(event.actionMasked){
                    MotionEvent.ACTION_DOWN -> {
                        val startTime = System.currentTimeMillis()
                        v.tag = startTime // 시작 시간을 View의 tag에 저장
                        handler.postDelayed({
                            backgorund.setBackgroundResource(R.drawable.selector_touch_item)
                        },50)
                        handler.postDelayed({
                            listener.showCustomDialog(position)
                        }, 1000)
                    }
                    MotionEvent.ACTION_UP -> {
                        handler.removeCallbacksAndMessages(null)
                        backgorund.setBackgroundResource(R.drawable.object_default_background)
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
    ): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_avartar, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AvartarRVAdapter.MyViewHolder, position: Int) {
        holder.image.setImageResource(avartarList.get(position).image!!)
    }

    override fun getItemCount(): Int {
       return avartarList.size
    }


}
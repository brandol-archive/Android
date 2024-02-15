package com.example.brandol.adaptor.RV


import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.brandol.ItemClickListener
import com.example.brandol.R
import com.example.brandol.collection.ItemModel2

class AvatarRVAdapter(private val itemList: List<ItemModel2>, private val listener: ItemClickListener) : RecyclerView.Adapter<AvatarRVAdapter.MyViewHolder>() {

    private var handler = Handler(Looper.getMainLooper())
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // ViewHolder에서 사용할 뷰들을 정의
        val image: ImageView = itemView.findViewById(R.id.item_stuff_image_iv)


        init {
            itemView.setOnClickListener {
                if(duplicateCheck(adapterPosition) != -1){
                    val dupPosition = duplicateCheck(adapterPosition)
                    itemList.get(dupPosition).ischeck = false
                    itemList.get(adapterPosition).ischeck = true
                    notifyItemChanged(dupPosition)
                    notifyItemChanged(adapterPosition)
                    listener?.onItemClick(adapterPosition,itemList.get(adapterPosition).ischeck,itemList.get(dupPosition).itemId)
                }else if (itemList.get(adapterPosition).ischeck == false) {
                    itemList.get(adapterPosition).ischeck = true
                    notifyItemChanged(adapterPosition)
                    listener?.onItemClick(adapterPosition,itemList.get(adapterPosition).ischeck,999)
                } else {
                    itemList.get(adapterPosition).ischeck = false
                    notifyItemChanged(adapterPosition)
                    listener?.onItemClick(adapterPosition,itemList.get(adapterPosition).ischeck,999)
                }

            }
            //터치 이벤트 구현
            itemView.setOnTouchListener { v, event ->
                when(event.actionMasked){
                    MotionEvent.ACTION_DOWN -> {
                        val startTime = System.currentTimeMillis()
                        v.tag = startTime // 시작 시간을 View의 tag에 저장
                        handler.postDelayed({
                            image.setBackgroundResource(R.drawable.selector_touch_item)
                        },1)
                        handler.postDelayed({
                            listener.showCustomDialog(adapterPosition)
                        }, 1000)
                    }
                    MotionEvent.ACTION_UP -> {
                        handler.removeCallbacksAndMessages(null)
                        image.setBackgroundResource(R.drawable.object_default_background)
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
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_avatar, parent, false)
        return MyViewHolder(itemView)
    }


    fun duplicateCheck(position: Int): Int {
        for(i in 0..itemList.size-1){
            //같은 파트의 다른 아이템이 눌러져 있을때
            if(i != position && itemList.get(i).part == itemList.get(position).part && itemList.get(i).ischeck){
                return i
            }
        }
        return -1
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //입고있던 아바타는 체크
        if (itemList.get(position).ischeck == false) {
            holder.image.setBackgroundResource(R.drawable.object_default_background)
        }else{
            holder.image.setBackgroundResource(R.drawable.selector_click_item)
        }
        //이미지 설정
        Glide.with(holder.image.context).load(itemList.get(position).image).into(holder.image)

    }

    override fun getItemCount(): Int {
        return itemList.size
    }


}
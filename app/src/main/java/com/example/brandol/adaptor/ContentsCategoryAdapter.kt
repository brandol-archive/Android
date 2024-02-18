package com.example.brandol.adaptor

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.brandol.R
import com.example.brandol.connection.RetrofitClient2

class ContentCategoryAdapter(private var contentList: List<RetrofitClient2.SearchDetailContentsDto>) :
    RecyclerView.Adapter<ContentCategoryAdapter.ContentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_contents_category, parent, false)
        return ContentViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        val content = contentList[position]
        holder.bind(content)
    }

    override fun getItemCount(): Int {
        return contentList.size
    }

    fun setData(newData: List<RetrofitClient2.SearchDetailContentsDto>) {
        contentList = newData
        notifyDataSetChanged()
    }

    inner class ContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val brandNameTextView: TextView? = itemView.findViewById(R.id.content_brand_name_tv)
        private val postTitleTextView: TextView? = itemView.findViewById(R.id.post_title_tv)
        private val postContentTextView: TextView? = itemView.findViewById(R.id.post_content_tv)
        private val contentInfoTextView: TextView? = itemView.findViewById(R.id.content_info_tv)
        private val contentImagesView: ImageView? = itemView.findViewById(R.id.contents_iv)

        fun bind(content: RetrofitClient2.SearchDetailContentsDto) {
            // Null 체크를 추가하여 뷰가 null이 아닌 경우에만 작업을 수행합니다.
            brandNameTextView?.text = content.writerName
            postTitleTextView?.text = content.contentsTitle
            postContentTextView?.text = content.content
            contentInfoTextView?.text = content.createdDate

            if (content.images.isNotEmpty()) {
                Log.d("content", "content.images.isNotEmpty()")
                contentImagesView?.let { Glide.with(itemView.context).load(content.images[0]).into(it) }
            } else {
                //contentImagesView?.visibility = View.GONE
            }
        }
    }

}

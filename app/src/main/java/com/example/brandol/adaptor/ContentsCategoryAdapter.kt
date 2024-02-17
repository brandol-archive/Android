package com.example.brandol.adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
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
        private val brandNameTextView: TextView = itemView.findViewById(R.id.content_brand_name_tv)
        private val postTitleTextView: TextView = itemView.findViewById(R.id.post_title_tv)
        private val postContentTextView: TextView = itemView.findViewById(R.id.post_content_tv)
        private val contentInfoTextView: TextView = itemView.findViewById(R.id.content_info_tv)

        fun bind(content: RetrofitClient2.SearchDetailContentsDto) {
            brandNameTextView.text = content.writerName // 작성자 이름으로 설정
            postTitleTextView.text = content.contentsTitle
            postContentTextView.text = content.content
            contentInfoTextView.text = content.createdDate
        }
    }
}

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.brandol.R

class ContentCategoryAdapter(private val contentList: List<ContentModel>) : RecyclerView.Adapter<ContentCategoryAdapter.ContentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contents_category, parent, false)
        return ContentViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        val content = contentList[position]
        holder.bind(content)
    }

    override fun getItemCount(): Int {
        return contentList.size
    }

    inner class ContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val brandNameTextView: TextView = itemView.findViewById(R.id.content_brand_name_tv)
        private val postTitleTextView: TextView = itemView.findViewById(R.id.post_title_tv)
        private val postContentTextView: TextView = itemView.findViewById(R.id.post_content_tv)
        private val contentInfoTextView: TextView = itemView.findViewById(R.id.content_info_tv)

        fun bind(content: ContentModel) {
            // 데이터 모델에 따라 각 TextView에 값을 설정
            brandNameTextView.text = content.brandName
            postTitleTextView.text = content.postTitle
            postContentTextView.text = content.postContent
            contentInfoTextView.text = content.contentInfo
        }
    }
}

data class ContentModel(
    val brandName: String,
    val postTitle: String,
    val postContent: String,
    val contentInfo: String
)

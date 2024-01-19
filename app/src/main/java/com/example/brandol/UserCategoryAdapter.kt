import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.brandol.R

class UserCategoryAdapter : RecyclerView.Adapter<UserCategoryAdapter.UserViewHolder>() {

    private val items = mutableListOf<String>()

    // 데이터 추가 메서드
    fun addItem(item: String) {
        items.add(item)
        notifyDataSetChanged()
    }

    // 뷰홀더 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user_category, parent, false)
        return UserViewHolder(view)
    }

    // 뷰홀더에 데이터 바인딩
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(items[position])
    }

    // 아이템 개수 반환
    override fun getItemCount(): Int {
        return items.size
    }

    // 뷰홀더 클래스 정의
    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val fanTextView: TextView = itemView.findViewById(R.id.user_name_tv)

        // 데이터 바인딩 메서드
        fun bind(userName: String) {
            fanTextView.text = userName
            // 필요한 경우 여기에 다른 위젯들에 대한 데이터 바인딩을 추가할 수 있습니다.
        }
    }
}

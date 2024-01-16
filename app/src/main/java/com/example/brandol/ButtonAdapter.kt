import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.brandol.R

class ButtonAdapter : RecyclerView.Adapter<ButtonAdapter.ButtonViewHolder>() {

    private val buttonItems = mutableListOf<String>()

    companion object {
        private const val VIEW_TYPE_ITEM = 1
        private const val VIEW_TYPE_BUTTON = 2
    }

    fun addButton(buttonText: String) {
        buttonItems.add(buttonText)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ButtonViewHolder {
        return when (viewType) {
            VIEW_TYPE_ITEM -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.brand_item_home, parent, false)
                ButtonViewHolder(view)
            }
            VIEW_TYPE_BUTTON -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.brand_item_add, parent, false)
                ButtonViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: ButtonViewHolder, position: Int) {
        // position이 버튼을 추가하는 위치인지 확인
        if (position < buttonItems.size) {
            // 기존 버튼 아이템에 대한 처리
            val buttonText = buttonItems[position]
            holder.bind(buttonText)
        } else {
            // 플러스 버튼에 대한 처리
            // TODO: 플러스 버튼의 디자인 등을 설정
        }
    }

    override fun getItemCount(): Int {
        // 플러스 버튼을 추가한 크기를 반환
        return buttonItems.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < buttonItems.size) {
            VIEW_TYPE_ITEM
        } else {
            VIEW_TYPE_BUTTON
        }
    }


    inner class ButtonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val button: Button = itemView.findViewById(R.id.button)

        fun bind(buttonText: String) {
            button.text = buttonText
            // 플러스 버튼 클릭 리스너 설정
            if (buttonText == "+") {
                button.setOnClickListener {
                    // +버튼 클릭 시 동작
                    // 예: 새로운 항목 추가 등
                }
            }
        }
    }
}

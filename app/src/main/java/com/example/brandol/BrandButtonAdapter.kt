import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.brandol.HomeFragment
import com.example.brandol.R

class BrandButtonAdapter : RecyclerView.Adapter<BrandButtonAdapter.ButtonViewHolder>() {

    private val buttonItems = mutableListOf<HomeFragment.DummyData>()

    companion object {
        private const val VIEW_TYPE_ITEM = 1
//        private const val VIEW_TYPE_BUTTON = 2
    }

    fun addButton(buttonData: HomeFragment.DummyData) {
        buttonItems.add(buttonData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ButtonViewHolder {
        return when (viewType) {
            VIEW_TYPE_ITEM -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_brand_home, parent, false)
                ButtonViewHolder(view)
            }
//            VIEW_TYPE_BUTTON -> {
//                val view = LayoutInflater.from(parent.context).inflate(R.layout.brand_item_add, parent, false)
//                ButtonViewHolder(view)
//            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: ButtonViewHolder, position: Int) {
        // position이 버튼을 추가하는 위치인지 확인
        if (position < buttonItems.size) {
            // 기존 버튼 아이템에 대한 처리
            val buttonData = buttonItems[position]
            holder.bind(buttonData)
        } else {
            // 플러스 버튼에 대한 처리
            holder.bindPlusButton()
        }
    }

    override fun getItemCount(): Int {
        // 플러스 버튼을 추가한 크기를 반환
        return buttonItems.size + 1
    }

//    override fun getItemViewType(position: Int): Int {
//        return if (position < buttonItems.size) {
//            VIEW_TYPE_ITEM
//        } else {
////            VIEW_TYPE_BUTTON
//            // 플러스 버튼을 사용하지 않을 경우 더 이상 VIEW_TYPE_BUTTON이 필요하지 않습니다.
//            throw IllegalArgumentException("Invalid view type")
//        }
//    }

    override fun getItemViewType(position: Int): Int {
        return VIEW_TYPE_ITEM
    }

    inner class ButtonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val brandImageView: ImageView = itemView.findViewById(R.id.brand_iv)
        private val classificationView: View = itemView.findViewById(R.id.classification_v)
        private val brandNameTextView: TextView = itemView.findViewById(R.id.brand_name_tv)
        private val brandInfoTextView: TextView = itemView.findViewById(R.id.brand_info_tv)
        private val fanCountTextView: TextView = itemView.findViewById(R.id.fan_count_tv)
        private val plusButtonImageView: Button = itemView.findViewById(R.id.plus_button)


        init {
            // classificationView 등이 null이 아닌지 확인
            //checkNotNull(classificationView) { "classificationView must not be null" }
            checkNotNull(brandNameTextView) { "brandNameTextView must not be null" }
            checkNotNull(brandInfoTextView) { "brandInfoTextView must not be null" }
            checkNotNull(fanCountTextView) { "fanCountTextView must not be null" }
            //checkNotNull(plusButtonImageView) { "button must not be null" }
        }

        fun bind(buttonData: HomeFragment.DummyData) {
            // 버튼이 아닌 경우에 대한 레이아웃 처리
            brandImageView.visibility = View.VISIBLE
            brandImageView.setImageResource(buttonData.brandImageResourceId) // 브랜드 이미지 설정
            //classificationView.visibility = View.VISIBLE
            brandNameTextView.text = buttonData.brandName
            brandInfoTextView.text = buttonData.brandInfo
            fanCountTextView.text = buttonData.fanCount

            // 플러스 버튼 클릭 리스너 설정
//            if (buttonData.brandName == "+") {
//                button.visibility = View.GONE // + 버튼은 숨김 처리
//                button.setOnClickListener {
//                    // +버튼 클릭 시 동작
//                    // 예: 새로운 항목 추가 등
//                }
//            } else {
//                // 기존 버튼일 경우 버튼 텍스트 설정
//                button.text = buttonData.brandName
//            }
        }

        // 플러스 버튼에 대한 디자인 및 클릭 리스너 설정
        fun bindPlusButton() {
            brandImageView.visibility = View.GONE
            classificationView.visibility = View.GONE
            brandNameTextView.text = ""
            brandInfoTextView.text = ""
            fanCountTextView.text = ""
            plusButtonImageView.visibility = View.VISIBLE

//            button.text = "+" // 버튼 텍스트 설정
//            button.visibility = View.VISIBLE // 버튼을 보이도록 설정
//            button.setOnClickListener {
            // +버튼 클릭 시 동작
            // 예: 새로운 항목 추가 등
//            }
        }
    }
}

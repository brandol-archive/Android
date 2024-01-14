import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.brandol.R

class BrandListAdapter(private val brandList: List<String>) :
    RecyclerView.Adapter<BrandListAdapter.BrandViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.brand_item_management, parent, false)
        return BrandViewHolder(view)
    }

    override fun onBindViewHolder(holder: BrandViewHolder, position: Int) {
        val brandName = brandList[position]
        holder.bind(brandName)
    }

    override fun getItemCount(): Int {
        return brandList.size
    }

    inner class BrandViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val brandNameTextView: TextView = itemView.findViewById(R.id.brand_name_tv)

        fun bind(brandName: String) {
            brandNameTextView.text = brandName
            // TODO: 필요한 작업 추가 (예: 아이콘 설정, 클릭 이벤트 등)
        }
    }
}

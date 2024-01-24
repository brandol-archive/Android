// BrandListAdapter.kt
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.brandol.BrandData
import com.example.brandol.R
import java.util.Collections

class BrandListAdapter(private val brandList: MutableList<BrandData>) :
    RecyclerView.Adapter<BrandListAdapter.BrandViewHolder>() {

    inner class BrandViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val brandImageView: ImageView = itemView.findViewById(R.id.brand_iv)
        private val brandNameTextView: TextView = itemView.findViewById(R.id.brand_name_tv)
        private val brandInfoTextView: TextView = itemView.findViewById(R.id.brand_info_tv)
        private val deleteTextView: TextView = itemView.findViewById(R.id.delete_tv)

        fun bind(brandData: BrandData) {
            // 브랜드 데이터를 뷰에 설정
            brandImageView.setImageResource(brandData.brandImageResourceId)
            brandNameTextView.text = brandData.brandName
            brandInfoTextView.text = brandData.brandInfo

            // 삭제 텍스트를 클릭했을 때의 동작 설정
            deleteTextView.setOnClickListener {
                showDeleteDialog(brandData)
            }
        }

        private fun showDeleteDialog(brandData: BrandData) {
            val context = itemView.context

            // 다이얼로그를 직접 생성하고 커스텀 레이아웃 설정
            val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_delete_brand, null)
            val dialog = AlertDialog.Builder(context)
                .setView(dialogView)
                .create()

            // 커스텀 다이얼로그 레이아웃에서 각 뷰 가져오기
            val deleteTextView: AppCompatTextView = dialogView.findViewById(R.id.dialog_delete_tv)
            val noButton: AppCompatButton = dialogView.findViewById(R.id.dialog_no_btn)
            val yesButton: AppCompatButton = dialogView.findViewById(R.id.dialog_yes_btn)

            // 브랜드 데이터 정보를 다이얼로그에 설정
            //deleteTextView.text = "정말로 ${brandData.brandName}을(를) 삭제하시겠습니까?"
            deleteTextView.text = "정말로 브랜드 리스트에서\n 삭제하시겠습니까?"


            // 확인 버튼 클릭 시 동작
            noButton.setOnClickListener {
                removeItem(brandData)
                dialog.dismiss()
            }

            // 취소 버튼 클릭 시 동작
            yesButton.setOnClickListener {
                dialog.dismiss()
            }

            // 다이얼로그 표시
            dialog.show()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_brand_management, parent, false)
        return BrandViewHolder(view)
    }

    override fun onBindViewHolder(holder: BrandViewHolder, position: Int) {
        val brandData = brandList[position]
        holder.bind(brandData)
    }

    override fun getItemCount(): Int {
        return brandList.size
    }

    // 외부에서 더미 데이터를 설정하는 메서드
    fun setBrandList(newBrandList: List<BrandData>) {
        brandList.clear()
        brandList.addAll(newBrandList)
        notifyDataSetChanged()
    }

    private fun removeItem(brandData: BrandData) {
        val position = brandList.indexOf(brandData)
        if (position != -1) {
            brandList.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun attachItemTouchHelper(recyclerView: RecyclerView) {
        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                // 드래그 액션 처리
                val from = viewHolder.adapterPosition
                val to = target.adapterPosition
                Collections.swap(brandList, from, to)
                notifyItemMoved(from, to)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                // 스와이프 액션 처리
                val position = viewHolder.adapterPosition
                removeItem(brandList[position])
            }
        })

        itemTouchHelper.attachToRecyclerView(recyclerView)
    }
}
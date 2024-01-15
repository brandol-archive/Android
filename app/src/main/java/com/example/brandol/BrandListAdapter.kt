// BrandListAdapter.kt
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.brandol.R
import com.example.brandol.databinding.BrandItemManagementBinding
import androidx.recyclerview.widget.ItemTouchHelper
import java.util.Collections

class BrandListAdapter(private val brandList: MutableList<String>) :
    RecyclerView.Adapter<BrandListAdapter.BrandViewHolder>(),
    ItemTouchHelperCallback.OnItemMoveListener {

    private val itemTouchHelper: ItemTouchHelper

    init {
        val itemTouchHelperCallback = ItemTouchHelperCallback(this)
        itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandViewHolder {
        val binding = BrandItemManagementBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BrandViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BrandViewHolder, position: Int) {
        val brandName = brandList[position]
        holder.bind(brandName)
    }

    override fun getItemCount(): Int {
        return brandList.size
    }

    inner class BrandViewHolder(private val binding: BrandItemManagementBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val brandNameTextView: TextView = binding.brandNameTv
        private val deleteTextView: TextView = binding.deleteTv

        fun bind(brandName: String) {
            brandNameTextView.text = brandName

            // 삭제 텍스트 클릭 이벤트 처리
            deleteTextView.setOnClickListener {
                // 삭제 동작을 수행하도록 수정
                showDeleteDialog(adapterPosition)
            }
        }

        private fun showDeleteDialog(position: Int) {
            val context = brandNameTextView.context
            val dialog = CustomDialog(
                context,
                "정말로 브랜드 리스트에서\n 삭제하시겠습니까?",
                {
                    // 확인 버튼 클릭 시 동작
                    removeItem(position)
                },
                {
                    // 취소 버튼 클릭 시 동작
                }
            )
            dialog.show()
        }
    }

    fun removeItem(position: Int) {
        brandList.removeAt(position)
        notifyItemRemoved(position)
    }
    fun attachItemTouchHelper(recyclerView: RecyclerView) {
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    // ItemTouchHelperCallback.OnItemMoveListener 구현
    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        // 아이템 이동 처리
        Collections.swap(brandList, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
        return true
    }

}


class ItemTouchHelperCallback(private val listener: OnItemMoveListener) : ItemTouchHelper.Callback() {

    interface OnItemMoveListener {
        fun onItemMove(fromPosition: Int, toPosition: Int): Boolean
    }

    override fun isLongPressDragEnabled(): Boolean {
        return true
    }

    override fun isItemViewSwipeEnabled(): Boolean {
        return false
    }

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        return makeMovementFlags(dragFlags, 0)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return listener.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        // Swipe 이벤트 처리 (여기서는 사용하지 않음)
    }
}


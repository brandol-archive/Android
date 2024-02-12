package com.example.brandol.adaptor// BrandListAdapter.kt
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.brandol.collection.BrandData
import com.example.brandol.dialog.CustomDeleteDialog
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

            deleteTextView.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);

            // 삭제 텍스트를 클릭했을 때의 동작 설정
            deleteTextView.setOnClickListener {
                showDeleteDialog(brandData)
            }
        }

        private fun showDeleteDialog(brandData: BrandData) {
            val context = itemView.context
            // 다이얼로그를 직접 생성하고 커스텀 레이아웃 설정
            val dialog = CustomDeleteDialog(context,"정말로 브랜드 리스트에서\n" +
                    "삭제하시겠습니까?",{
                removeItem(brandData)
            },{

            })

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
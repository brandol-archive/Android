package com.example.brandol.adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.brandol.R
import com.example.brandol.connection.RetrofitClient2

class PointUseAdapter(private val pointList: List<RetrofitClient2.UserPointHistory>) :
    RecyclerView.Adapter<PointUseAdapter.PointViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PointViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_point_history, parent, false)
        return PointViewHolder(view)
    }

    override fun onBindViewHolder(holder: PointViewHolder, position: Int) {
        val pointItem = pointList[position]
        holder.bind(pointItem)
    }

    override fun getItemCount(): Int {
        return pointList.size
    }

    class PointViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val bonusTextView: TextView = itemView.findViewById(R.id.total_action_bonus_tv)
        private val dateTextView: TextView = itemView.findViewById(R.id.total_action_bonus_date_tv)
        private val getTextView: TextView = itemView.findViewById(R.id.total_action_bonus_get_tv)
        private val getPTextView: TextView = itemView.findViewById(R.id.total_action_bonus_get_p_tv)

        fun bind(pointItem: RetrofitClient2.UserPointHistory) {
            bonusTextView.text = pointItem.history
            dateTextView.text = pointItem.date
            getTextView.text = "획득"
            getPTextView.text = pointItem.points.toString() + "P"
        }
    }
}

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.brandol.R

class ButtonAdapter : RecyclerView.Adapter<ButtonAdapter.ButtonViewHolder>() {

    private val buttonItems = mutableListOf<String>()

    fun addButton(buttonText: String) {
        buttonItems.add(buttonText)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ButtonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.brand_item, parent, false)
        return ButtonViewHolder(view)
    }

    override fun onBindViewHolder(holder: ButtonViewHolder, position: Int) {
        val buttonText = buttonItems[position]
        holder.bind(buttonText)
    }

    override fun getItemCount(): Int {
        return buttonItems.size
    }

    inner class ButtonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val button: Button = itemView.findViewById(R.id.button)

        fun bind(buttonText: String) {
            button.text = buttonText
        }
    }
}

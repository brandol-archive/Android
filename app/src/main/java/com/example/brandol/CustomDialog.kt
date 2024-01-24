
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import com.example.brandol.R


class CustomDialog(
    context: Context,
    private val message: String,
    private val onConfirm: () -> Unit,
    private val onCancel: () -> Unit
) : Dialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_delete_brand)


        val messageTextView: AppCompatTextView = findViewById(R.id.dialog_delete_tv)
        val confirmButton: AppCompatButton = findViewById(R.id.dialog_no_btn)
        val cancelButton: AppCompatButton = findViewById(R.id.dialog_yes_btn)

        messageTextView.text = message

        confirmButton.setOnClickListener {
            onConfirm.invoke()
            dismiss()
        }

        cancelButton.setOnClickListener {
            onCancel.invoke()
            dismiss()
        }
    }

}

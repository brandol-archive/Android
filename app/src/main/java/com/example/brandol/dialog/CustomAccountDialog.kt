package com.example.brandol.dialog
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.brandol.R

class CustomAccountDialog(
    context: Context,
    private val message: String,
    private val onConfirm: () -> Unit,
    private val onCancel: () -> Unit
) : Dialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_delete_account)
        //다이얼로그 둥글게 만들기
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val acTextView:TextView = findViewById(R.id.dialog_delete_account_tv)

        val acyesButton: Button = findViewById(R.id.dialog_account_yes_btn)
        val acnoButton: Button = findViewById(R.id.dialog_account_no_btn)


        val editText : EditText = findViewById(R.id.dialog_account_et)


        acTextView.text = message


        acyesButton.setOnClickListener {

            if(editText.text.trim().isEmpty()) {
                Toast.makeText(context,"닉네임을 적어 주세요",Toast.LENGTH_SHORT).show()
            }else {
                onConfirm.invoke()
                dismiss()
            }

        }

        acnoButton.setOnClickListener {
            onCancel.invoke()
            dismiss()
        }

    }

}
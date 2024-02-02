package com.example.brandol

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class CustomDeleteDialog(
    context: Context,
    private val message: String,
    private val onConfirm: () -> Unit,
    private val onCancel: () -> Unit
) : Dialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_delete_brand)

        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val yesButton: Button = findViewById(R.id.dialog_yes_btn)
        val noButton: Button = findViewById(R.id.dialog_no_btn)
        var textView: TextView = findViewById(R.id.dialog_delete_tv)

        textView.text = message
        yesButton.setOnClickListener {
            onConfirm.invoke()
            dismiss()
        }

        noButton.setOnClickListener {
            onCancel.invoke()
            dismiss()
        }

    }

}
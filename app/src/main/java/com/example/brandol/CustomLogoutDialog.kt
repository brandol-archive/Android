package com.example.brandol

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class CustomLogoutDialog(
    context: Context,
    private val message: String,
    private val onConfirm: () -> Unit,
    private val onCancel: () -> Unit
) : Dialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //다이얼로그 둥글게 만들기
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val logyesButton: Button = findViewById(R.id.dialog_logout_yes_btn)
        val lognoButton: Button = findViewById(R.id.dialog_logout_no_btn)

        val logTextView: TextView = findViewById(R.id.dialog_logout_tv)
        logTextView.text = message

        logyesButton.setOnClickListener {
            onConfirm.invoke()
            dismiss()
        }

        lognoButton.setOnClickListener {
            onCancel.invoke()
            dismiss()
        }
    }
}
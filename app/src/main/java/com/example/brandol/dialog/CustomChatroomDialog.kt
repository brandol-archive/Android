package com.example.brandol.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.brandol.R

class CustomChatroomDialog(
    context: Context,
    private val message: String,
    private val upBtn: () -> Unit,
    private val downbtn: () -> Unit
): Dialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_chatroom)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val exitButton : Button = findViewById(R.id.dialog_chatting_exit_btn)
        val banButton : Button = findViewById(R.id.dialog_chatting_ban_btn)
        val userName : TextView = findViewById(R.id.dialog_chatting_user_name_tv)

        userName.text = message
        exitButton.setOnClickListener {
            upBtn.invoke()
            dismiss()
        }
        banButton.setOnClickListener {
            downbtn.invoke()
            dismiss()
        }
    }
}
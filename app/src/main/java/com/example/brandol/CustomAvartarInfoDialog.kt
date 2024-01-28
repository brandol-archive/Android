package com.example.brandol

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import org.w3c.dom.Text

class CustomAvartarInfoDialog(
    context: Context,
) : Dialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //다이얼로그 둥글게 만들기
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))



        val closetext : TextView = findViewById(R.id.dialog_announce_info_close_tv)
        closetext.setOnClickListener {
            dismiss()
        }
    }

}
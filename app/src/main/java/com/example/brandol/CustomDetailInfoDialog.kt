package com.example.brandol

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class CustomDetailInfoDialog(
    context: Context,
    private var itemName: String,
    private var itemImage: Int
) : Dialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //다이얼로그 둥글게 만들기
        setContentView(R.layout.dialog_detail_info)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val textView : TextView = findViewById(R.id.dialog_item_name_tv)
        val imageView: ImageView = findViewById(R.id.dialog_item_image_iv)
        textView.text = itemName
        imageView.setImageResource(itemImage)
    }
}
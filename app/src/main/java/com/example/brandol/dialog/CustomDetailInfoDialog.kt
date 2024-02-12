package com.example.brandol.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.brandol.R
import com.squareup.picasso.Picasso

class CustomDetailInfoDialog(
    context: Context,
    private var itemName: String,
    private var brandName: String,
    private var itemImage: String,
    private var description: String,
    private var price : Int,
    private var part : String


) : Dialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //다이얼로그 둥글게 만들기
        setContentView(R.layout.dialog_detail_info)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val nameText : TextView = findViewById(R.id.dialog_item_name_tv)
        val desText: TextView = findViewById(R.id.dialog_item_info_tv)
        val bnameText: TextView = findViewById(R.id.dialog_item_brandName_tv)
        val priceText : TextView = findViewById(R.id.dialog_item_price_tv)
        val partText: TextView = findViewById(R.id.dialog_item_part_tv)
        val imageView: ImageView = findViewById(R.id.dialog_item_image_iv)
        nameText.text = itemName
        desText.text = description
        bnameText.text = brandName
        priceText.text = price.toString()
        partText.text = part
        Glide.with(imageView.context).load(itemImage).into(imageView)
    }
}
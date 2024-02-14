package com.example.brandol.collection

data class ItemModel2(
    val myItemId: Long,
    val itemId: Long,
    val brandId: Long,
    val brandName : String,
    val itemName : String,
    val part : String,
    val description : String,
    val image : String,
    val wearingImage : String,
    val price : Int,
    val createdAt : String,
    val wearing : Boolean,
    var ischeck : Boolean
)

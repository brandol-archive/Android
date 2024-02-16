package com.example.brandol

interface ItemClickListener {
    fun onItemClick(position: Int) {}
    fun onItemClick(position: Int,check: Boolean,dup : Long){}
    fun onItemClick(position: Int,check: Boolean){}
    fun showCustomDialog(position: Int){}
}
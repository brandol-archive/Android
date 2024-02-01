package com.example.brandol

interface ItemClickListener {
    fun onItemClick(position: Int) {}
    fun showCustomDialog(position: Int){}
}
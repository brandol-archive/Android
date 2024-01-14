package com.example.brandol

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.brandol.databinding.ActivityMainBinding

class BrandManagementActivity : AppCompatActivity()  {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}
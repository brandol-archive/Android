package com.example.brandol

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.brandol.databinding.ActivityOpponentAvatarBinding
import com.google.android.material.tabs.TabLayoutMediator

class OpponentAvatarActivity : AppCompatActivity() {

    lateinit var binding : ActivityOpponentAvatarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOpponentAvatarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val pagerAdapter = OpponentVPAdapter(supportFragmentManager,lifecycle)
        binding.opponentAvatarVp.adapter = pagerAdapter
        binding.opponentAvatarNameTv.text = intent.getStringExtra("userNameKey")
        TabLayoutMediator(binding.opponentAvatarCategoryTl,binding.opponentAvatarVp){ tab, position ->
            when(position){
                0->tab.text = "브랜드 리스트"
                1->tab.text = "착용 아바타"
                2->tab.text = "작성한 글"
            }
        }.attach()

    }
}
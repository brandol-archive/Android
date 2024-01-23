package com.example.brandol

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.brandol.databinding.ActivityOpponentAvartarBinding
import com.google.android.material.tabs.TabLayoutMediator

class OpponentAvartarActivity : AppCompatActivity() {

    lateinit var binding : ActivityOpponentAvartarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOpponentAvartarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val pagerAdapter = OpponentVPAdapter(supportFragmentManager,lifecycle)
        binding.opponentAvartarVp.adapter = pagerAdapter

        TabLayoutMediator(binding.opponentAvartarCategoryTl,binding.opponentAvartarVp){ tab, position ->
            when(position){
                0->tab.text = "브랜드 리스트"
                1->tab.text = "착용 아바타"
                2->tab.text = "작성한 글"
            }
        }.attach()

    }
}
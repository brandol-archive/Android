package com.example.brandol.opavatar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.brandol.adaptor.VP.OpponentVPAdapter
import com.example.brandol.databinding.ActivityOpponentAvatarBinding
import com.google.android.material.tabs.TabLayoutMediator

class OpponentAvatarActivity : AppCompatActivity() {

    lateinit var binding : ActivityOpponentAvatarBinding
    val pagerAdapter = OpponentVPAdapter(supportFragmentManager,lifecycle)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOpponentAvatarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.opponentAvatarVp.adapter = pagerAdapter
        val intent = intent
        val from = intent.getStringExtra("from")
        var username : String = ""

        if (from == "Chatting"){
            username = intent.getStringExtra("chatkey").toString()
            binding.opponentAvatarNameTv.text = username
        }else if(from == "UserCategory"){
            username=  intent.getStringExtra("userNameKey").toString()
            binding.opponentAvatarNameTv.text = username
        }
        TabLayoutMediator(binding.opponentAvatarCategoryTl,binding.opponentAvatarVp){ tab, position ->
            when(position){
                0->tab.text = "브랜드 리스트"
                1->tab.text = "착용 아바타"
                2->tab.text = "작성한 글"
            }
        }.attach()

    }
}
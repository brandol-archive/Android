package com.example.brandol.opavatar

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.example.brandol.adaptor.VP.OpponentVPAdapter
import com.example.brandol.chatting.ChattingActivity
import com.example.brandol.connection.RetrofitClient2
import com.example.brandol.connection.RetrofitObject
import com.example.brandol.databinding.ActivityOpponentAvatarBinding
import com.google.android.material.tabs.TabLayoutMediator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OpponentAvatarActivity : AppCompatActivity() {

    lateinit var binding : ActivityOpponentAvatarBinding
    val pagerAdapter = OpponentVPAdapter(supportFragmentManager,lifecycle)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOpponentAvatarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //백버튼
        binding.opponentAvatarBackArrowBtn.setOnClickListener {
            finish()
        }
        //뷰페이저 어댑터 연결
        binding.opponentAvatarVp.adapter = pagerAdapter
        val intent = intent
        val from = intent.getStringExtra("from")
        var username : String = ""

        // 여기서 userId를 받아옴
        val memberId = intent.getIntExtra("userId", -1)


        //여기에서 인텐트로 멤버 아이디 받음
        if (from == "Chatting"){
            username = intent.getStringExtra("chatkey").toString()
            binding.opponentAvatarNameTv.text = username
        }else if(from == "UserCategory"){
            username=  intent.getStringExtra("userNameKey").toString()
            binding.opponentAvatarNameTv.text = username
        }

        viewPageConnect()

        //멤버아이디 받아서
        val token = getCurrentToken(this)
        val call = RetrofitObject.getRetrofitService.getOtherAvatar("Bearer $token",memberId.toLong())
        call.enqueue(object : Callback<RetrofitClient2.ResponseOtherAvatar> {
            override fun onResponse(
                call: Call<RetrofitClient2.ResponseOtherAvatar>,
                response: Response<RetrofitClient2.ResponseOtherAvatar>
            ) {
                if (response.isSuccessful) {
                    val response = response.body()
                    Log.d("LHJ", response.toString())
                    if (response != null) {
                        if (response.isSuccess) {
                            //아바타 이미지와 닉네임 설정
                            val avatarImage = response.result.avatar
                            val nickname = response.result.nickname
                            val memberId = response.result.memberId
                            Glide.with(this@OpponentAvatarActivity).load(avatarImage).into(binding.opponentAvatarRealAvatar)
                            binding.opponentAvatarNameTv.text = nickname
                            binding.opponentAvatarChattingBtn.setOnClickListener {
                               val intent = Intent(this@OpponentAvatarActivity,ChattingActivity::class.java)
                                intent.putExtra("memberId",memberId)
                                startActivity(intent)
                            }
                        }
                    }
                }
            }

            override fun onFailure(call: Call<RetrofitClient2.ResponseOtherAvatar>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

    }




    private fun getCurrentToken(context: Context): String?{
        val sharedPref = context.getSharedPreferences("Brandol", AppCompatActivity.MODE_PRIVATE)
        return sharedPref.getString("accessToken", null)
    }
    private fun viewPageConnect() {
        TabLayoutMediator(
            binding.opponentAvatarCategoryTl,
            binding.opponentAvatarVp
        ) { tab, position ->
            when (position) {
                0 -> tab.text = "브랜드 리스트"
                1 -> tab.text = "착용 아이템"
                2 -> tab.text = "작성한 글"
            }
        }.attach()
    }
}
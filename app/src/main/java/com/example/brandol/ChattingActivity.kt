package com.example.brandol

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.brandol.databinding.ActivityChattingBinding
import java.text.SimpleDateFormat
import java.util.Calendar

class ChattingActivity : AppCompatActivity() {

    lateinit var binding: ActivityChattingBinding
    val email : String = "ww"
    private var chatList = ArrayList<Chat>()
    private val adapterForChat = ChattingRVAdapter(chatList,email)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChattingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.chattingNameTv.text = intent.getStringExtra("messagekey")

        binding.chattingContentsRv.layoutManager = LinearLayoutManager(this)
        binding.chattingContentsRv.adapter = adapterForChat

        chatList.apply {
            add(Chat("w","ㅋㅋㅋㅋㅋ",R.drawable.demo_avatar3,"용기리우스"))
        }
        binding.chattingSendBtn.setOnClickListener {
            var chattext = binding.chattingEdittextEt.text.toString()
            chatList.add(Chat(email,chattext,R.drawable.demo_avatar3,"호진"))
            // 메시지 보내고 받는 시간 받기
            val calendar: Calendar = Calendar.getInstance() // 캘린더 객체 인스턴스 calendar
            val dateFormat = SimpleDateFormat("yyyy-dd-MMM HH:mm:ss") // SimpleDataFormat 이라는 날짜와 시간을 출력하는 객체 생성, hh을 HH로 변경했더니 24시각제로 바뀜
            val datetime: String = dateFormat.format(calendar.time)
        }

        binding.button.setOnClickListener {
            val intent = Intent(this,OpponentAvatarActivity::class.java)
            startActivity(intent)
        }
        backbtn()
    }

    private fun backbtn() {
        binding.chattingBackBtn.setOnClickListener {
            finish()
        }
    }
}
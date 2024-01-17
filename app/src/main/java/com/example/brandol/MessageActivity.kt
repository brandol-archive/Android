package com.example.brandol

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.brandol.databinding.ActivityMessageBinding

class MessageActivity : AppCompatActivity() {

    lateinit var binding: ActivityMessageBinding
    private var messageList = ArrayList<Message>()
    private val messageAdapter = MessageRVAdapter(messageList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMessageBinding.inflate(layoutInflater)
        setContentView(binding.root)


        backbtn()
        initMessageList()
        clickMessageEvent()

        binding.messageChattinglistRv.layoutManager = LinearLayoutManager(this)
        binding.messageChattinglistRv.adapter = messageAdapter
    }

    // 메세지 클릭 이벤트 기능 구현
    private fun clickMessageEvent() {
        val intent = Intent(this, ChattingActivity::class.java)
        messageAdapter.itemClickListener = object : ItemClickListener {
            override fun onItemClick(position: Int) {
                val message = messageList[position]
                intent.putExtra("messagekey", message.name)
                startActivity(intent)
            }
        }
    }

    //메세지 리스트 초기화
    private fun initMessageList() {
        messageList.apply {
            add(
                Message(
                    R.drawable.justexp,
                    "이호진",
                    "ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ",
                    "4:58",
                    5
                )
            )
            add(Message(R.drawable.justexp, "김용기", "나 좀 잘생기지 않았냐ㅋㅋ", "4:58", 4))
            add(Message(R.drawable.justexp, "박준용", "안녕하세요~", "4:58", 16))
            add(Message(R.drawable.justexp, "임경진", " ", "4:58", 15))
        }
    }

    //백 버튼 기능 구현
    private fun backbtn() {
        binding.messageBackBtn.setOnClickListener {
            finish()
        }
    }
}
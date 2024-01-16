package com.example.brandol

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.brandol.databinding.ActivityMessageBinding

class MessageActivity : AppCompatActivity() {

    lateinit var binding: ActivityMessageBinding
    private  var messages = ArrayList<Message>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMessageBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.messageBackBtn.setOnClickListener {
            finish()
        }
        messages.apply {
            add(Message(R.drawable.justexp,"이호진","ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ","4:58",5))
            add(Message(R.drawable.justexp,"김용기","나 좀 잘생기지 않았냐ㅋㅋ","4:58",4))
            add(Message(R.drawable.justexp,"박준용","안녕하세요~","4:58",16))
            add(Message(R.drawable.justexp,"임경진"," ","4:58",15))
        }
        binding.messageChattinglistRv.layoutManager = LinearLayoutManager(this)
        binding.messageChattinglistRv.adapter = MessageRVAdapter(messages)
    }
}
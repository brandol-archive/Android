package com.example.brandol

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.brandol.databinding.FragmentMessageBinding

class MessageFragment : Fragment() {
    lateinit var binding: FragmentMessageBinding
    private var messageList = ArrayList<Message>()
    private val messageAdapter = MessageRVAdapter(messageList)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMessageBinding.inflate(inflater, container, false)
        backbtn()
        initMessageList()
        clickMessageEvent()
        binding.messageChattinglistRv.layoutManager = LinearLayoutManager(activity)
        binding.messageChattinglistRv.adapter = messageAdapter
        return binding.root
    }

    private fun clickMessageEvent() {
        val intent = Intent(activity, ChattingActivity::class.java)
        messageAdapter.itemClickListener = object : ItemClickListener {
            override fun onItemClick(position: Int) {
                val message = messageList[position]
                intent.putExtra("messagekey", message.name)
                startActivity(intent)
            }
        }
    }

    private fun initMessageList() {
        messageList.apply {
            add(
                Message(
                    R.drawable.demo_avartar,
                    "이호진",
                    "ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ",
                    "4:58",
                    5
                )
            )
            add(Message(R.drawable.demo_avartar2, "김용기", "나 좀 잘생기지 않았냐ㅋㅋ", "4:58", 4))
            add(Message(R.drawable.demo_avartar3, "박준용", "안녕하세요~", "4:58", 16))
            add(Message(R.drawable.demo_avartar4, "임경진", " ", "4:58", 15))
        }
    }

    private fun backbtn() {
        binding.messageBackBtn.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.main_frm, AvartarFragment())
                .commit()
        }
    }
}
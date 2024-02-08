package com.example.brandol.chatting

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.brandol.ItemClickListener
import com.example.brandol.R
import com.example.brandol.adaptor.RV.MessageRVAdapter
import com.example.brandol.avatar.AvatarFragment
import com.example.brandol.collection.Message
import com.example.brandol.databinding.FragmentMessageBinding
import com.example.brandol.dialog.CustomChatroomDialog

class MessageFragment : Fragment(), ItemClickListener {
    lateinit var binding: FragmentMessageBinding
    private var messageList = ArrayList<Message>()
    private val messageAdapter = MessageRVAdapter(messageList, this)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMessageBinding.inflate(inflater, container, false)
        backbtn()
        initMessageList()
        binding.messageChattinglistRv.layoutManager = LinearLayoutManager(activity)
        binding.messageChattinglistRv.adapter = messageAdapter
        return binding.root
    }

    override fun showCustomDialog(position: Int) {
        val context = context
        val dialog = CustomChatroomDialog(context!!, messageList.get(position).name, {
            messageList.removeAt(position)
            messageAdapter.notifyItemRemoved(position)
        }, {
            //차단기능
        })
        dialog.show()
    }
    override fun onItemClick(position: Int) {
        val intent = Intent(activity, ChattingActivity::class.java)
        val message = messageList[position]
        intent.putExtra("messagekey", message.name)
        startActivity(intent)
    }


    private fun initMessageList() {
        messageList.apply {
            add(
                Message(
                    R.drawable.demo_avatar,
                    "이호진",
                    "ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ",
                    "4:58",
                    5
                )
            )
            add(Message(R.drawable.demo_avatar2, "김용기", "나 좀 잘생기지 않았냐ㅋㅋ", "4:58", 4))
            add(Message(R.drawable.demo_avatar3, "박준용", "안녕하세요~", "4:58", 16))
            add(Message(R.drawable.demo_avatar4, "임경진", " ", "4:58", 15))
        }
    }

    private fun backbtn() {
        binding.messageBackBtn.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.main_frm, AvatarFragment())
                .commit()
        }
    }
}
package com.example.brandol.dialog

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.brandol.chatting.ChattingActivity
import com.example.brandol.R
import com.example.brandol.databinding.DialogNowriterOptionBinding

class CustomNowriterOptionDialog (
    context: Context,
) : Dialog(context) {

    lateinit var binding: DialogNowriterOptionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_nowriter_option)

        //아바타 채팅 화면으로 전환
        goChatting()
        //신고 버튼 클릭 이벤트 처리
        report()
    }

    private fun goChatting() {
        binding.dialogSendmessageBtn.setOnClickListener {
            val intent = Intent(context, ChattingActivity::class.java)
            context.startActivity(intent)
        }
    }

    private fun report() {
        binding.dialogReportBtn.setOnClickListener {
            Toast.makeText(context, "신고가 접수되었습니다.", Toast.LENGTH_SHORT).show()
        }
    }
}
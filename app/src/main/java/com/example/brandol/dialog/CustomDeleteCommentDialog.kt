package com.example.brandol.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.example.brandol.R

class CustomDeleteCommentDialog (
    context: Context,
) : Dialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_delete_comment)

        //댓글 삭제 관련 코드
    }
}
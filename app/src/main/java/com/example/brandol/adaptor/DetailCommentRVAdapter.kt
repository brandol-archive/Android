package com.example.brandol.adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.brandol.R
import com.example.brandol.collection.Usercomment

class DetailCommentRVAdapter(private var userComment: List<Usercomment>, private val myemail: String) :
    RecyclerView.Adapter<DetailCommentRVAdapter.CommentViewHodler>() {

    private var iscoomcombtnChecked: Boolean = false  //대댓글 다이얼로그 버튼 체크 여부 확인 및 false로 초기화

    inner class CommentViewHodler(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userprofile: ImageView = itemView.findViewById(R.id.avatar_iv)
        val username: TextView = itemView.findViewById(R.id.name_tv)
        val commenttext: TextView = itemView.findViewById(R.id.chat_tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHodler {
        var itemView : View
        // 댓글일 경우
        if (viewType == 1) {
            itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_comment, parent, false)
        } else{  // 대댓글일 경우
            itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_comcomment, parent, false)
        }

        return CommentViewHodler(itemView)
    }

    override fun onBindViewHolder(holder: CommentViewHodler, position: Int) {
        holder.userprofile.setImageResource(userComment.get(position).userProfile)
        holder.username.text = userComment.get(position).userName
        holder.commenttext.text = userComment.get(position).commentText
    }

    //글 작성자인지 판별
    override fun getItemViewType(position: Int): Int {
        if (userComment.get(position).userEmail.equals(myemail)) {
            return 3
        } else {
            return 4  //글 작성자가 아니다
        }
    }

    override fun getItemCount(): Int {
        return userComment.size
    }

    fun isComment(position: Int): Int {
        val currentUserEmail = userComment[position].userEmail
        // 게시물에 대한 댓글
        if(currentUserEmail == myemail) {  //3: 글 작성자인 경우
            return 3
        } else if(iscoomcombtnChecked == false) {  //2: 글 작성자가 아니고 댓글인 경우
            return 2
        } else {  //글 작성자가 아니고 대댓글인 경우
            return 1
        }
    }

}
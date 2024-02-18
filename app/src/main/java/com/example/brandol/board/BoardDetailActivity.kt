package com.example.brandol.board

import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.brandol.R
import com.example.brandol.adaptor.DetailCommentRVAdapter
import com.example.brandol.collection.Usercomment
import com.example.brandol.databinding.ActivityBoardDetailBinding
import com.example.brandol.databinding.ItemComcommentBinding
import com.example.brandol.databinding.ItemCommentBinding
import java.text.SimpleDateFormat
import java.util.Calendar


class BoardDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityBoardDetailBinding
    lateinit var _binding: ItemCommentBinding
    lateinit var __binding: ItemComcommentBinding
    val myemail : String = "ww"
    private var userComment = ArrayList<Usercomment>()
    private val adapterForComment = DetailCommentRVAdapter(userComment, myemail)
    private var iscomcomment: Int = 1  //1은 댓글, 2는 대댓글

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBoardDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val profile = intent.getStringExtra("Profile")
        val usernick = intent.getStringExtra("Usernick")
        val posttitle = intent.getStringExtra("Posttitle")
        val postcontent = intent.getStringExtra("Postcontent")
        val likecnt = intent.getIntExtra("Likecnt", 0)
        val commentcnt = intent.getIntExtra("Commentcnt", 0)
        val posttime = intent.getStringExtra("Posttime")

        binding.detailProfileIv.setImageURI(Uri.parse(profile))
        binding.detailUsernickTv.text = usernick
        binding.detailPosttimeTv.text = posttime
        binding.detailPosttitleTv.text = posttitle
        binding.detailPostcontentTv.text = postcontent

        binding.detailLikecntTv.text = likecnt.toString()
        binding.detailCommentcntTv.text = commentcnt.toString()

        binding.detailUsernickTv.text = intent.getStringExtra("commentkey")

        binding.detailCommentsRv.layoutManager = LinearLayoutManager(this)
        binding.detailCommentsRv.adapter = adapterForComment

        userComment.apply{
            add(Usercomment(R.drawable.ic_avatar_ex, "홍길동", "아아아", "dd", "2023.12.10", 1))
            add(Usercomment(R.drawable.ic_avatar_ex, "B2", "인정합니다..", "ss", "2023.12.10", 2))
            add(Usercomment(R.drawable.ic_avatar_ex, "호지니", "인정합니다..", "ss", "2023.12.10", 2))
        }

        binding.detailSendBtn.setOnClickListener {
            var commenttext = binding.detailEdittextEt.text.toString()

            val calendar: Calendar = Calendar.getInstance()
            val dateFormat = SimpleDateFormat("yyyy.dd.MM")
            val datetime: String = dateFormat.format(calendar.time)

            userComment.add(Usercomment(R.drawable.ic_avatar_ex, "호진", commenttext, myemail, datetime, iscomcomment))
        }

        goBack()
        moreOption()
        commentMoreOPtion()
        isWriter()
        showPostImage()

    }

    private fun goBack() {
        binding.detailBackBtn.setOnClickListener {
            finish()
        }
    }

    private fun moreOption() {
        binding.detailMoreBtn.setOnClickListener {
            val isWriter = userComment.any { it.userEmail == myemail }

            val inflater = LayoutInflater.from(this)
            val dialogView = if (isWriter) {
                inflater.inflate(R.layout.dialog_writer_option, null)
            } else {
                inflater.inflate(R.layout.dialog_nowriter_option, null)
            }

            val builder = AlertDialog.Builder(this)
            builder.setView(dialogView)
            val dialog = builder.create()

            dialog.show()
        }
    }

    private fun isWriter() {
        for (comment in userComment) {
            if (myemail == comment.userEmail) {
                binding.detailUsernickTv.setTextColor(ContextCompat.getColor(this, R.color.puple))
            }
        }
    }

    private fun showPostImage() {
        val imageCount = intent.getIntExtra("imageCount", 0)

        if (imageCount == 1) {
            binding.detailOneimageIv.visibility = View.VISIBLE
            binding.detailOneimageIv.setImageResource(R.drawable.iv_image_ex)
        } else if (imageCount >= 2) {
            binding.detailTwomoreimageRv.visibility = View.VISIBLE

            for (i in 0 until imageCount) {
                val inflater = LayoutInflater.from(this)
                val itemView = inflater.inflate(R.layout.item_detail_image, binding.detailTwomoreimageRv, false)
                val imageView: ImageView = itemView.findViewById(R.id.item_image_iv)
                imageView.setImageResource(R.drawable.iv_image_ex)
                binding.detailTwomoreimageRv.addView(itemView)
            }
        }
    }

    private fun commentMoreOPtion() {
        _binding = ItemCommentBinding.inflate(layoutInflater)

        _binding.itemMoreBtn.setOnClickListener {
            val isCommentWriter = userComment.any { it.userEmail == myemail }
            val isComcommentWriter = userComment.any { it.userEmail == myemail }

            val inflater = LayoutInflater.from(this)
            val dialogView = if (isCommentWriter) {
                inflater.inflate(R.layout.dialog_delete_comment, null)
            } else {
                inflater.inflate(R.layout.dialog_nowriter_option, null)
            }

            val builder = AlertDialog.Builder(this)
            builder.setView(dialogView)
            val dialog = builder.create()

            dialog.show()
        }

        __binding = ItemComcommentBinding.inflate(layoutInflater)

        __binding.itemComcomentMoreBtn.setOnClickListener {
            val isComcommentWriter = userComment.any { it.userEmail == myemail }

            val inflater = LayoutInflater.from(this)
            val dialogView = if (isComcommentWriter) {
                inflater.inflate(R.layout.dialog_delete_comment, null)
            } else {
                inflater.inflate(R.layout.dialog_nowriter_option, null)
            }

            val builder = AlertDialog.Builder(this)
            builder.setView(dialogView)
            val dialog = builder.create()

            dialog.show()
        }
    }
}

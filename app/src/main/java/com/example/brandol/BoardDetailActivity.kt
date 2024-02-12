package com.example.brandol

import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
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

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        //현재 카테고리 및 게시판 위치 설정
        binding.detailBrandnameTv.text = "BRANDOL"  //텍스트 추후 수정
        binding.boardNowcateTv.text = "FANDOM"  //텍스트 추후 수정
        binding.boardNowboardTv.text = "FANDOM CULTURE"  //텍스트 추후 수정

        //게시물 관련 텍스트 및 사진 설정
        binding.detailProfileIv.setImageResource(R.drawable.iv_image_ex)  //이미지 추후 수정
        binding.detailUsernickTv.text = "호지니"  //텍스트 추후 수정
        binding.detailPosttimeTv.text = "2024.02.04"  //텍스트 추후 수정
        binding.detailPosttitleTv.text = "나 좀 닮은듯ㅎ"  //텍스트 추후 수정
        binding.detailPostcontentTv.text = "인정하면 좋아요"  //텍스트 추후 수정

        //좋아요 및 댓글 수 설정
        binding.detailLikecntTv.text = "99"  //텍스트 추후 수정
        binding.detailCommentcntTv.text = "99"  //텍스트 추후 수정

        //인텐트 텍스트 전달
        binding.detailUsernickTv.text = intent.getStringExtra("commentkey")

        //adapter 설정
        binding.detailCommentsRv.layoutManager = LinearLayoutManager(this)
        binding.detailCommentsRv.adapter = adapterForComment

        //댓글 더미데이터 생성
        userComment.apply{
            add(Usercomment(R.drawable.ic_avatar_ex, "홍길동", "아아아", "dd", "2023.12.10", 1))
            add(Usercomment(R.drawable.ic_avatar_ex, "B2", "인정합니다..", "ss", "2023.12.10", 2))
            add(Usercomment(R.drawable.ic_avatar_ex, "호지니", "인정합니다..", "ss", "2023.12.10", 2))
        }

        //댓글 리사이클러뷰 코드
        binding.detailSendBtn.setOnClickListener {
            var commenttext = binding.detailEdittextEt.text.toString()

            // 캘린더 객체 인스턴스 calendar
            val calendar: Calendar = Calendar.getInstance()
            // SimpleDataFormat 이라는 날짜를 출력하는 객체 생성
            val dateFormat = SimpleDateFormat("yyyy.dd.MM")
            val datetime: String = dateFormat.format(calendar.time)

            userComment.add(Usercomment(R.drawable.ic_avatar_ex, "호진", commenttext, myemail, datetime, iscomcomment))
        }

        //뒤로가기
        goBack()
        //더보기
        moreOption()
        commentMoreOPtion()
        //글 작성자 아이디 색상 변경
        isWriter()
        //사진이 있을 경우 보여주기
        showPostImage()

    }

    private fun goBack() {
        //뒤로 가기 버튼 클릭 시
        binding.detailBackBtn.setOnClickListener {
            finish()
        }
    }

    private fun moreOption() {
        // 더보기 버튼 클릭 시 글 작성자 여부에 따라
        binding.detailMoreBtn.setOnClickListener {
            // 글 작성자인지 여부 확인
            val isWriter = userComment.any { it.userEmail == myemail }

            // 다이얼로그 인플레이션
            val inflater = LayoutInflater.from(this)
            val dialogView = if (isWriter) {
                // 글 작성자인 경우
                inflater.inflate(R.layout.dialog_writer_option, null)
            } else {
                // 글 작성자가 아닌 경우
                inflater.inflate(R.layout.dialog_nowriter_option, null)
            }

            // AlertDialog.Builder를 사용하여 다이얼로그 생성
            val builder = AlertDialog.Builder(this)
            builder.setView(dialogView)
            val dialog = builder.create()

            // 다이얼로그 보이기
            dialog.show()
        }
    }


    private fun isWriter() {
        // 게시물에 달린 모든 댓글의 작성자의 이메일을 확인하여 작성자인 경우 닉네임의 색상을 변경
        for (comment in userComment) {
            if (myemail == comment.userEmail) {
                binding.detailUsernickTv.setTextColor(ContextCompat.getColor(this, R.color.puple))
            }
        }
    }

    private fun showPostImage() {
        val imageCount = intent.getIntExtra("imageCount", 0)

        if (imageCount == 1) {
            // 이미지의 개수가 1개인 경우
            binding.detailOneimageIv.visibility = View.VISIBLE
            binding.detailOneimageIv.setImageResource(R.drawable.iv_image_ex)  //이미지 추후 수정
        } else if (imageCount >= 2) {
            // 이미지의 개수가 2개 이상인 경우
            binding.detailTwomoreimageRv.visibility = View.VISIBLE

            // item_detail_image.xml의 itemlist를 사용하여 이미지를 보여줍니다.
            for (i in 0 until imageCount) {
                // item_detail_image.xml을 인플레이션하여 item을 생성합니다.
                val inflater = LayoutInflater.from(this)
                val itemView = inflater.inflate(R.layout.item_detail_image, binding.detailTwomoreimageRv, false)
                // 이미지 뷰에 이미지를 설정합니다.
                val imageView: ImageView = itemView.findViewById(R.id.item_image_iv)
                imageView.setImageResource(R.drawable.iv_image_ex)
                // 생성한 item을 슬라이딩 레이아웃에 추가합니다.
                binding.detailTwomoreimageRv.addView(itemView)
            }
        }
    }

    private fun commentMoreOPtion() {
        //댓글 더보기 클릭 시 커스텀 dialog 띄우기
        val isCommentWriter = userComment.any { it.userEmail == myemail }  //추후 댓글 작성자 이메일과 비교로 변경
        val isComcommentWriter = userComment.any { it.userEmail == myemail }  //추후 대댓글 작성자 이메일과 비교로 변경

        _binding.itemMoreBtn.setOnClickListener {
            // 다이얼로그 인플레이션
            val inflater = LayoutInflater.from(this)
            val dialogView = if (isCommentWriter) {
                // 댓글 작성자인 경우
                inflater.inflate(R.layout.dialog_delete_comment, null)
            } else {
                // 댓글 작성자가 아닌 경우
                inflater.inflate(R.layout.dialog_nowriter_option, null)
            }

            // AlertDialog.Builder를 사용하여 다이얼로그 생성
            val builder = AlertDialog.Builder(this)
            builder.setView(dialogView)
            val dialog = builder.create()

            // 다이얼로그 보이기
            dialog.show()
        }
        //대댓글 더보기 버튼 클릭 시
        __binding.itemComcomentMoreBtn.setOnClickListener {
            val inflater = LayoutInflater.from(this)
            val dialogView = if (isComcommentWriter) {
                // 댓글 작성자인 경우
                inflater.inflate(R.layout.dialog_delete_comment, null)
            } else {
                // 댓글 작성자가 아닌 경우
                inflater.inflate(R.layout.dialog_nowriter_option, null)  //dialog 수정해야 함
            }

            // AlertDialog.Builder를 사용하여 다이얼로그 생성
            val builder = AlertDialog.Builder(this)
            builder.setView(dialogView)
            val dialog = builder.create()

            // 다이얼로그 보이기
            dialog.show()
        }
    }
}
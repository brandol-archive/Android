package com.example.brandol.board

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.brandol.adaptor.RV.BoardRVAdapter
import com.example.brandol.adaptor.RV.OpBrandRVAdapter
import com.example.brandol.collection.OpBrand
import com.example.brandol.connection.RetrofitAPI
import com.example.brandol.connection.RetrofitClient2
import com.example.brandol.connection.RetrofitObject
import com.example.brandol.databinding.ActivityBoardBinding
import com.example.brandol.databinding.ItemBoardBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BoardActivity : AppCompatActivity() {
    lateinit var binding: ActivityBoardBinding
    lateinit var _binding: ItemBoardBinding
    private lateinit var retrofitAPI: RetrofitAPI // Declare RetrofitAPI instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("BoardActivity", "게시판 열림")

        // Initialize RetrofitAPI
        retrofitAPI = RetrofitObject.getRetrofitService

        // Call function to fetch brand header
        fetchBoardData()

        // Get data from intent
        fetchBoardInfo()

        //게시판 제목 및 게시판 위치 글씨 변경
//        binding.boardBrandnameTv.text =
//        binding.boardNowcateTv.text = "커뮤니티"  //추후 수정 필요
//        binding.boardNowboardTv.text = "자유게시판"  //추후 수정 필요

        //뒤로가기
        goBack()
        //검색 화면 전환
        search()
        //글쓰기 화면 전환
        writePost()

        //리사이클러뷰 관련 코드
    }

    private fun goBack() {
        //뒤로가기 버튼 클릭
        binding.boardBackBtn.setOnClickListener {
            finish()
        }
    }

    private fun search() {
        //검색 버튼 클릭 -> 실행 되는 거 보고 확인
        binding.boardSearchBtn.setOnClickListener {
            val intent = Intent(this, BoardSearchActivity::class.java)
            startActivity(intent)
        }
    }

    private fun writePost() {
        //글쓰기 버튼 클릭 -> 실행 되는 거 보고 확인
        binding.boardWriteboardBtn.setOnClickListener {
            val intent = Intent(this, WriteBoardActivity::class.java)
            startActivity(intent)
        }
    }

    private fun fetchBoardInfo() {
        val category = intent.getStringExtra("category")
        val boardText = intent.getStringExtra("boardText")

        Log.d("fetchBoardInfo", "$category - $boardText")

        binding.boardNowcateTv.text = category
        binding.boardNowboardTv.text = boardText
    }

    private fun fetchBoardData() {
        val requestCode = intent.getIntExtra("requestCode", -1)
        val brandId = intent.getIntExtra("brandId", -1)
        val token = getCurrentToken(this)

        Log.d("fetchBoardData", "$requestCode - $brandId")

        when (requestCode) {
            101 -> {
                // 팬덤 게시판 관련 API 호출
                fetchFandomCultureBoard(brandId, token)
            }
            102 -> {
                // 팬덤 게시판 관련 API 호출
                fetchFandomAnnouncementBoard(brandId, token)
            }
            201 -> {
                // 콘텐츠 게시판 관련 API 호출
                fetchContentsVideo(brandId, token)
            }
            202 -> {
                // 콘텐츠 게시판 관련 API 호출
                fetchContentsEvent(brandId, token)
            }
            203 -> {
                // 콘텐츠 게시판 관련 API 호출
                fetchContentsCardnews(brandId, token)
            }
            301 -> {
                // 커뮤니티 게시판 관련 API 호출
                fetchCommunityFree(brandId, token)
            }
            302 -> {
                // 커뮤니티 게시판 관련 API 호출
                fetchCommunityFeedback(brandId, token)
            }
            401 -> {
                // 마이 게시판 관련 API 호출

            }
            402 -> {
                // 마이 게시판 관련 API 호출

            }
            else -> {
                // 예외 처리
                Log.e("BoardActivity", "Invalid requestCode: $requestCode")
            }
        }
    }

    private fun fetchFandomCultureBoard(brandId: Int, token: String?) {
        // Fandom - Fandom Culture 게시판 관련 API 호출
        val call: Call<RetrofitClient2.FandomCultureBoardResponse> = retrofitAPI.fandomCultureBoardView("Bearer $token", brandId.toLong(), 1)
        call.enqueue(object : Callback<RetrofitClient2.FandomCultureBoardResponse> {
            override fun onResponse(call: Call<RetrofitClient2.FandomCultureBoardResponse>, response: Response<RetrofitClient2.FandomCultureBoardResponse>) {
                // 성공적으로 응답을 받았을 때 처리
                if (response.isSuccessful) {
                    // 데이터 처리
                    val response = response.body()
                    Log.d("fetchFandomCultureBoard", response.toString())
                    if (response != null) {
                        if (response.isSuccess) {
                            val boardList = mutableListOf<BoardItem>() // 게시물 리스트를 담을 리스트

                            // 각 게시물의 정보를 추출하여 리스트에 추가
                            for (i in 0 until response.result.size) {
                                val boardItem = response.result[i]
                                val writerId = boardItem.writerId
                                val writerProfile = boardItem.writerProfile
                                val writerName = boardItem.writerName
                                val title = boardItem.title
                                val content = boardItem.content
                                val images = boardItem.images
                                val likeCount = boardItem.likeCount
                                val commentCount = boardItem.commentCount
                                val writtenDate = boardItem.writtenDate

                                //val board = BoardItem(writerId, writerProfile, writerName, title, content, images, likeCount, commentCount, writtenDate)
                                //boardList.add(board)
                            }

                            // RecyclerView에 어댑터 설정
                            //val boardAdapter = BoardRVAdapter(boardList)
                            //binding.boardListRv.adapter = boardAdapter
                            //binding.boardListRv.layoutManager = LinearLayoutManager(activity)
                        }
                    }
                } else {
                    // 오류 처리
                }
            }

            override fun onFailure(call: Call<RetrofitClient2.FandomCultureBoardResponse>, t: Throwable) {
                // 네트워크 오류 처리
                TODO("Not yet implemented")
            }
        })
    }

    private fun fetchFandomAnnouncementBoard(brandId: Int, token: String?) {
        // Fandom - Notice 게시판 관련 API 호출
        val call: Call<RetrofitClient2.FandomAnnouncementBoardResponse> = retrofitAPI.fandomAnnouncementBoardView("Bearer $token", brandId.toLong(), 1)
        call.enqueue(object : Callback<RetrofitClient2.FandomAnnouncementBoardResponse> {
            override fun onResponse(call: Call<RetrofitClient2.FandomAnnouncementBoardResponse>, response: Response<RetrofitClient2.FandomAnnouncementBoardResponse>) {
                // 성공적으로 응답을 받았을 때 처리
                if (response.isSuccessful) {
                    // 데이터 처리
                } else {
                    // 오류 처리
                }
            }

            override fun onFailure(call: Call<RetrofitClient2.FandomAnnouncementBoardResponse>, t: Throwable) {
                // 네트워크 오류 처리
            }
        })
    }

    private fun fetchContentsEvent(brandId: Int, token: String?) {
        // Contents - Event 게시판 관련 API 호출
        val call: Call<RetrofitClient2.ContentsEventResponse> = retrofitAPI.contentsEventView("Bearer $token", brandId.toLong(), 1)
        call.enqueue(object : Callback<RetrofitClient2.ContentsEventResponse> {
            override fun onResponse(call: Call<RetrofitClient2.ContentsEventResponse>, response: Response<RetrofitClient2.ContentsEventResponse>) {
                // 성공적으로 응답을 받았을 때 처리
                if (response.isSuccessful) {
                    // 데이터 처리
                } else {
                    // 오류 처리
                }
            }

            override fun onFailure(call: Call<RetrofitClient2.ContentsEventResponse>, t: Throwable) {
                // 네트워크 오류 처리
            }
        })
    }

    private fun fetchContentsCardnews(brandId: Int, token: String?) {
        // Content - Cardnews 게시판 관련 API 호출
        val call: Call<RetrofitClient2.ContentsCardnewsResponse> = retrofitAPI.contentsCardnewsView("Bearer $token", brandId.toLong(), 1)
        call.enqueue(object : Callback<RetrofitClient2.ContentsCardnewsResponse> {
            override fun onResponse(call: Call<RetrofitClient2.ContentsCardnewsResponse>, response: Response<RetrofitClient2.ContentsCardnewsResponse>) {
                // 성공적으로 응답을 받았을 때 처리
                if (response.isSuccessful) {
                    // 데이터 처리
                } else {
                    // 오류 처리
                }
            }

            override fun onFailure(call: Call<RetrofitClient2.ContentsCardnewsResponse>, t: Throwable) {
                // 네트워크 오류 처리
            }
        })
    }

    private fun fetchContentsVideo(brandId: Int, token: String?) {
        // Contents - Video 게시판 관련 API 호출
        val call: Call<RetrofitClient2.ContentsVideoResponse> = retrofitAPI.contentsVideoView("Bearer $token", brandId.toLong(), 1)
        call.enqueue(object : Callback<RetrofitClient2.ContentsVideoResponse> {
            override fun onResponse(call: Call<RetrofitClient2.ContentsVideoResponse>, response: Response<RetrofitClient2.ContentsVideoResponse>) {
                // 성공적으로 응답을 받았을 때 처리
                if (response.isSuccessful) {
                    // 데이터 처리
                } else {
                    // 오류 처리
                }
            }

            override fun onFailure(call: Call<RetrofitClient2.ContentsVideoResponse>, t: Throwable) {
                // 네트워크 오류 처리
            }
        })
    }

    private fun fetchCommunityFree(brandId: Int, token: String?) {
        // Community - Free 게시판 관련 API 호출
        val call: Call<RetrofitClient2.CommunityFreeResponse> = retrofitAPI.communityFreeView("Bearer $token", brandId.toLong(), 1)
        call.enqueue(object : Callback<RetrofitClient2.CommunityFreeResponse> {
            override fun onResponse(call: Call<RetrofitClient2.CommunityFreeResponse>, response: Response<RetrofitClient2.CommunityFreeResponse>) {
                // 성공적으로 응답을 받았을 때 처리
                if (response.isSuccessful) {
                    // 데이터 처리
                } else {
                    // 오류 처리
                }
            }

            override fun onFailure(call: Call<RetrofitClient2.CommunityFreeResponse>, t: Throwable) {
                // 네트워크 오류 처리
            }
        })
    }

    private fun fetchCommunityFeedback(brandId: Int, token: String?) {
        // Community - Feedback 게시판 관련 API 호출
        val call: Call<RetrofitClient2.CommunityFeedbackResponse> = retrofitAPI.communityFeedbackView("Bearer $token", brandId.toLong(), 1)
        call.enqueue(object : Callback<RetrofitClient2.CommunityFeedbackResponse> {
            override fun onResponse(call: Call<RetrofitClient2.CommunityFeedbackResponse>, response: Response<RetrofitClient2.CommunityFeedbackResponse>) {
                // 성공적으로 응답을 받았을 때 처리
                if (response.isSuccessful) {
                    // 데이터 처리
                } else {
                    // 오류 처리
                }
            }

            override fun onFailure(call: Call<RetrofitClient2.CommunityFeedbackResponse>, t: Throwable) {
                // 네트워크 오류 처리
            }
        })
    }

    private fun getCurrentToken(context: Context): String? {
        val sharedPref = context.getSharedPreferences("Brandol", Context.MODE_PRIVATE)
        return sharedPref.getString("accessToken", null)
    }
}
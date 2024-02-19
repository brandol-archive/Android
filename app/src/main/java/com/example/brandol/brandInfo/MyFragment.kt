package com.example.brandol.brandInfo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.brandol.R
import com.example.brandol.board.BoardActivity
import com.example.brandol.board.BoardDetailActivity
import com.example.brandol.connection.RetrofitAPI
import com.example.brandol.connection.RetrofitClient2
import com.example.brandol.connection.RetrofitObject
import com.example.brandol.databinding.FragmentCommuBinding
import com.example.brandol.databinding.FragmentMyBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Query

class MyFragment : Fragment() {

    private var _binding: FragmentMyBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View? {
        _binding = FragmentMyBinding.inflate(inflater, container, false)

        // MyFragment가 생성될 때 브랜드 ID와 회원 ID를 가져옴
        val brandId: Long? = arguments?.getLong("brandId")
        var memberId: Long? = 123  //추후 수정

        // loadMyCategory() 함수에 브랜드 ID와 회원 ID를 전달하여 호출
        loadMyCategory(2, memberId)

        binding.myPlus1Btn.setOnClickListener {
            navigateToBoardActivity("MY", "내가 작성한 글", 401)
        }

        binding.myPlus2Btn.setOnClickListener {
            navigateToBoardActivity("MY", "내가 작성한 댓글", 402)
        }

        return binding.root
    }

    private fun navigateToBoardActivity(category: String, boardText: String, requestCode: Int) {
        val intent = Intent(requireContext(), BoardActivity::class.java)
        intent.putExtra("category", category)
        intent.putExtra("boardText", boardText)
        startActivityForResult(intent, requestCode)
    }

    private fun navigateToBoardDetailActivity(Profile: String, Usernick: String, Posttitle: String, Postcontent: String, Likecnt: Int, Commentcnt: Int, Posttime: String) {
        val intent = Intent(requireContext(), BoardDetailActivity::class.java)
        intent.putExtra("Profile", Profile)
        intent.putExtra("Usernick", Usernick)
        intent.putExtra("Posttitle", Posttitle)
        intent.putExtra("Postcontent", Postcontent)
        intent.putExtra("Likecnt", Likecnt)
        intent.putExtra("Commentcnt", Commentcnt)
        intent.putExtra("Posttime", Posttime)
        startActivity(intent)
    }


    private fun loadMyCategory(brandId: Long?, memberId: Long?) {
        // Retrofit을 사용하여 API 호출
        val token = getCurrentToken(requireContext())
        val callArticle: Call<RetrofitClient2.MyWrittenArticlesResponse> = RetrofitObject.getRetrofitService.myArticles("Bearer $token", brandId, memberId)
        callArticle.enqueue(object : Callback<RetrofitClient2.MyWrittenArticlesResponse> {
            override fun onResponse(call: Call<RetrofitClient2.MyWrittenArticlesResponse>, response: Response<RetrofitClient2.MyWrittenArticlesResponse>) {
                if (response.isSuccessful) {
                    val myWrittenArticlesResponse = response.body()
                    myWrittenArticlesResponse?.let {
                        updateArticle(it)
                    }
                } else {
                    // API 호출은 성공했지만 응답이 실패한 경우에 대한 처리
                    Log.e("Myfragment", "Failed to load my article: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<RetrofitClient2.MyWrittenArticlesResponse>, t: Throwable) {
                // 네트워크 호출 실패에 대한 처리
                Log.e("Myfragment", "Failed to load my article", t)
            }
        })

        val callComment: Call<RetrofitClient2.MyWrittenCommentsResponse> = RetrofitObject.getRetrofitService.myComments("Bearer $token", brandId, memberId)
        callComment.enqueue(object : Callback<RetrofitClient2.MyWrittenCommentsResponse> {
            override fun onResponse(call: Call<RetrofitClient2.MyWrittenCommentsResponse>, response: Response<RetrofitClient2.MyWrittenCommentsResponse>) {
                if (response.isSuccessful) {
                    val myWrittenArticlesResponse = response.body()
                    myWrittenArticlesResponse?.let {
                        updateComment(it)
                    }
                } else {
                    // API 호출은 성공했지만 응답이 실패한 경우에 대한 처리
                    Log.e("Myfragment", "Failed to load my comment: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<RetrofitClient2.MyWrittenCommentsResponse>, t: Throwable) {
                // 네트워크 호출 실패에 대한 처리
                Log.e("Myfragment", "Failed to load my comment", t)
            }
        })

    }

    private fun updateArticle(response: RetrofitClient2.MyWrittenArticlesResponse) {
        binding.myContentcnt1Tv.text = response.result.totalArticleCount.toString()
        //첫 번째 게시글
        val firstArticle = response.result.memberWrittenDtoList.firstOrNull()
        firstArticle?.let {
            Glide.with(requireContext())
                .load(it.writerProfile)
                .into(binding.myProfile1Iv)
            binding.myUsernick1Tv.text = it.writerName
            binding.myPosttitle1Tv.text = it.title
            binding.myPostcontent1Tv.text = it.content
//            Glide.with(requireContext())
//                .load(it.images)
//                .into(binding.myImage1Iv)
            binding.myLikecnt1Tv.text = it.likeCount.toString()
            binding.myCommentcnt1Tv.text = it.commentCount.toString()
            binding.myPosttime1Tv.text = it.writtenDate.toString()
        }

        //두 번째 게시글
        val secondArticle = response.result.memberWrittenDtoList.getOrNull(1)
        secondArticle?.let {
            Glide.with(requireContext())
                .load(it.writerProfile)
                .into(binding.myProfile2Iv)
            binding.myUsernick2Tv.text = it.writerName
            binding.myPosttitle2Tv.text = it.title
            binding.myPostcontent2Tv.text = it.content
//            Glide.with(requireContext())
//                .load(it.images)
//                .into(binding.myImage2Iv)
            binding.myLikecnt2Tv.text = it.likeCount.toString()
            binding.myCommentcnt2Tv.text = it.commentCount.toString()
            binding.myPosttime2Tv.text = it.writtenDate.toString()
        }

        //첫 번째 게시글 클릭 이벤트
        binding.myPost1Cl.setOnClickListener {
            firstArticle?.let {
                navigateToBoardDetailActivity(it.writerProfile, it.writerName, it.title, it.content, it.likeCount, it.commentCount, it.writtenDate)
            }
        }

        //두 번째 게시글 클릭 이벤트
        binding.myPost2Cl.setOnClickListener {
            secondArticle?.let {
                navigateToBoardDetailActivity(it.writerProfile, it.writerName, it.title, it.content, it.likeCount, it.commentCount, it.writtenDate)
            }
        }
    }

    private fun updateComment(response: RetrofitClient2.MyWrittenCommentsResponse) {
        binding.myContentcnt2Tv.text = response.result.totalArticleCount.toString()
        //첫 번째 게시글
        val firstComment = response.result.memberWrittenDtoList.firstOrNull()
        firstComment?.let {
            Glide.with(requireContext())
                .load(it.writerProfile)
                .into(binding.myProfile3Iv)
            binding.myUsernick3Tv.text = it.writerName
            binding.myPosttitle3Tv.text = it.title
            binding.myPostcontent3Tv.text = it.content
//            Glide.with(requireContext())
//                .load(it.images)
//                .into(binding.myImage3Iv)
            binding.myLikecnt3Tv.text = it.likeCount.toString()
            binding.myCommentcnt3Tv.text = it.commentCount.toString()
            binding.myPosttime3Tv.text = it.writtenDate.toString()
        }

        //두 번째 게시글
        val secondComment = response.result.memberWrittenDtoList.getOrNull(1)
        secondComment?.let {
            Glide.with(requireContext())
                .load(it.writerProfile)
                .into(binding.myProfile4Iv)
            binding.myUsernick4Tv.text = it.writerName
            binding.myPosttitle4Tv.text = it.title
            binding.myPostcontent4Tv.text = it.content
//            Glide.with(requireContext())
//                .load(it.images)
//                .into(binding.myImage4Iv)
            binding.myLikecnt4Tv.text = it.likeCount.toString()
            binding.myCommentcnt4Tv.text = it.commentCount.toString()
            binding.myPosttime4Tv.text = it.writtenDate.toString()
        }

        //첫 번째 댓글 클릭 이벤트
        binding.myPost3Cl.setOnClickListener {
            firstComment?.let {
                navigateToBoardDetailActivity(it.writerProfile, it.writerName, it.title, it.content, it.likeCount, it.commentCount, it.writtenDate)
            }
        }

        //두 번째 댓글 클릭 이벤트
        binding.myPost4Cl.setOnClickListener {
            secondComment?.let {
                navigateToBoardDetailActivity(it.writerProfile, it.writerName, it.title, it.content, it.likeCount, it.commentCount, it.writtenDate)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getCurrentToken(context: Context): String? {
        val sharedPref = context.getSharedPreferences("Brandol", Context.MODE_PRIVATE)
        return sharedPref.getString("accessToken", null)
    }
}

//package com.example.brandol.brandInfo

//import android.content.Context
//import android.content.Intent
//import android.os.Bundle
//import android.util.Log
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import com.bumptech.glide.Glide
//import com.example.brandol.R
//import com.example.brandol.board.BoardActivity
//import com.example.brandol.connection.RetrofitAPI
//import com.example.brandol.connection.RetrofitClient2
//import com.example.brandol.connection.RetrofitObject
//import com.example.brandol.databinding.FragmentCommuBinding
//import com.example.brandol.databinding.FragmentMyBinding
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//import retrofit2.http.Query
//
//class MyFragment : Fragment() {
//
//    private var _binding: FragmentMyBinding? = null
//    private val binding get() = _binding!!
//    private lateinit var retrofitAPI: RetrofitAPI // Declare RetrofitAPI instance
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View? {
//        _binding = FragmentMyBinding.inflate(inflater, container, false)
//
//        // RetrofitAPI 초기화
//        retrofitAPI = RetrofitObject.getRetrofitService
//
//        // MyFragment가 생성될 때 브랜드 ID와 회원 ID를 가져옴
//        val brandId: Long? = arguments?.getLong("brandId")
//        var memberId: Long? = 123  //추후 수정
//
//        // loadMyCategory() 함수에 브랜드 ID와 회원 ID를 전달하여 호출
//        loadMyCategory(brandId, memberId)
//
//        binding.myPlus1Btn.setOnClickListener {
//            navigateToBoardActivity("MY", "내가 작성한 글", 401)
//        }
//
//        binding.myPlus2Btn.setOnClickListener {
//            navigateToBoardActivity("MY", "내가 작성한 댓글", 402)
//        }
//
//        return binding.root
//    }
//
//    private fun navigateToBoardActivity(category: String, boardText: String, requestCode: Int) {
//        val intent = Intent(requireContext(), BoardActivity::class.java)
//        intent.putExtra("boardNowcateText", category)
//        intent.putExtra("boardNowboardText", boardText)
//        startActivityForResult(intent, requestCode)
//    }
//
//    private fun loadMyCategory(brandId: Long?, memberId: Long?) {
//        val token = getCurrentToken(requireContext())
//        // Retrofit을 사용하여 API 호출
//        val callArticle: Call<RetrofitClient2.MyWrittenArticlesResponse> = RetrofitObject.getRetrofitService.myArticles("Bearer $token", brandId, memberId)
//        callArticle.enqueue(object : Callback<RetrofitClient2.MyWrittenArticlesResponse> {
//            override fun onResponse(call: Call<RetrofitClient2.MyWrittenArticlesResponse>, response: Response<RetrofitClient2.MyWrittenArticlesResponse>) {
//                if (response.isSuccessful) {
//                    val myWrittenArticlesResponse = response.body()
//                    myWrittenArticlesResponse?.let {
//                        updateArticle(it)
//                    }
//                } else {
//                    // API 호출은 성공했지만 응답이 실패한 경우에 대한 처리
//                    Log.e("Myfragment", "Failed to load my article: ${response.code()}")
//                }
//            }
//
//            override fun onFailure(call: Call<RetrofitClient2.MyWrittenArticlesResponse>, t: Throwable) {
//                // 네트워크 호출 실패에 대한 처리
//                Log.e("Myfragment", "Failed to load my article", t)
//            }
//        })
//
//        val callComment: Call<RetrofitClient2.MyWrittenCommentsResponse> = RetrofitObject.getRetrofitService.myComments("Bearer $token", brandId, memberId)
//        callComment.enqueue(object : Callback<RetrofitClient2.MyWrittenCommentsResponse> {
//            override fun onResponse(call: Call<RetrofitClient2.MyWrittenCommentsResponse>, response: Response<RetrofitClient2.MyWrittenCommentsResponse>) {
//                if (response.isSuccessful) {
//                    val myWrittenArticlesResponse = response.body()
//                    myWrittenArticlesResponse?.let {
//                        updateComment(it)
//                    }
//                } else {
//                    // API 호출은 성공했지만 응답이 실패한 경우에 대한 처리
//                    Log.e("Myfragment", "Failed to load my comment: ${response.code()}")
//                }
//            }
//
//            override fun onFailure(call: Call<RetrofitClient2.MyWrittenCommentsResponse>, t: Throwable) {
//                // 네트워크 호출 실패에 대한 처리
//                Log.e("Myfragment", "Failed to load my comment", t)
//            }
//        })
//
//    }
//
//    private fun updateArticle(response: RetrofitClient2.MyWrittenArticlesResponse) {
//        binding.myContentcnt1Tv.text = response.result.totalArticleCount.toString()
//        //첫 번째 게시글
//        val firstArticle = response.result.memberWrittenDtoList.firstOrNull()
//        firstArticle?.let {
//            Glide.with(requireContext())
//                .load(it.writerProfile)
//                .into(binding.myProfile1Iv)
//            binding.myUsernick1Tv.text = it.writerName
//            binding.myPosttitle1Tv.text = it.title
//            binding.myPostcontent1Tv.text = it.content
//            Glide.with(requireContext())
//                .load(it.images)
//                .into(binding.myImage1Iv)
//            binding.myLikecnt1Tv.text = it.likeCount.toString()
//            binding.myCommentcnt1Tv.text = it.commentCount.toString()
//            binding.myPosttime1Tv.text = it.writtenDate.toString()
//        }
//
//        //두 번째 게시글
//        val secondArticle = response.result.memberWrittenDtoList.getOrNull(1)
//        secondArticle?.let {
//            Glide.with(requireContext())
//                .load(it.writerProfile)
//                .into(binding.myProfile2Iv)
//            binding.myUsernick2Tv.text = it.writerName
//            binding.myPosttitle2Tv.text = it.title
//            binding.myPostcontent2Tv.text = it.content
//            Glide.with(requireContext())
//                .load(it.images)
//                .into(binding.myImage2Iv)
//            binding.myLikecnt2Tv.text = it.likeCount.toString()
//            binding.myCommentcnt2Tv.text = it.commentCount.toString()
//            binding.myPosttime2Tv.text = it.writtenDate.toString()
//        }
//    }
//
//    private fun updateComment(response: RetrofitClient2.MyWrittenCommentsResponse) {
//        binding.myContentcnt2Tv.text = response.result.totalArticleCount.toString()
//        //첫 번째 게시글
//        val firstComment = response.result.memberWrittenDtoList.firstOrNull()
//        firstComment?.let {
//            Glide.with(requireContext())
//                .load(it.writerProfile)
//                .into(binding.myProfile3Iv)
//            binding.myUsernick3Tv.text = it.writerName
//            binding.myPosttitle3Tv.text = it.title
//            binding.myPostcontent3Tv.text = it.content
//            Glide.with(requireContext())
//                .load(it.images)
//                .into(binding.myImage3Iv)
//            binding.myLikecnt3Tv.text = it.likeCount.toString()
//            binding.myCommentcnt3Tv.text = it.commentCount.toString()
//            binding.myPosttime3Tv.text = it.writtenDate.toString()
//        }
//
//        //두 번째 게시글
//        val secondComment = response.result.memberWrittenDtoList.getOrNull(1)
//        secondComment?.let {
//            Glide.with(requireContext())
//                .load(it.writerProfile)
//                .into(binding.myProfile4Iv)
//            binding.myUsernick4Tv.text = it.writerName
//            binding.myPosttitle4Tv.text = it.title
//            binding.myPostcontent4Tv.text = it.content
//            Glide.with(requireContext())
//                .load(it.images)
//                .into(binding.myImage4Iv)
//            binding.myLikecnt4Tv.text = it.likeCount.toString()
//            binding.myCommentcnt4Tv.text = it.commentCount.toString()
//            binding.myPosttime4Tv.text = it.writtenDate.toString()
//        }
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//
//    private fun getCurrentToken(context: Context): String? {
//        val sharedPref = context.getSharedPreferences("Brandol", Context.MODE_PRIVATE)
//        return sharedPref.getString("accessToken", null)
//    }
//}
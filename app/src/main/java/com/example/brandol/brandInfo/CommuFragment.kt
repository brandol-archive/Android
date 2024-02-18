//package com.example.brandol.brandInfo
//
//import android.content.Context
//import android.content.Intent
//import android.os.Bundle
//import android.util.Log
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import com.bumptech.glide.Glide
//import com.example.brandol.board.BoardActivity
//import com.example.brandol.connection.RetrofitAPI
//import com.example.brandol.connection.RetrofitClient2
//import com.example.brandol.connection.RetrofitObject
//import com.example.brandol.databinding.FragmentCommuBinding
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//class CommuFragment : Fragment() {
//    private var _binding: FragmentCommuBinding? = null
//    private val binding get() = _binding!!
//    private lateinit var retrofitAPI: RetrofitAPI // Declare RetrofitAPI instance
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View? {
//        _binding = FragmentCommuBinding.inflate(inflater, container, false)
//
//        // RetrofitAPI 초기화
//        retrofitAPI = RetrofitObject.getRetrofitService
//
//        // CommunityFragment 생성될 때 브랜드 ID와 회원 ID를 가져옴
//        val brandId: Long? = arguments?.getLong("brandId")
//
//        // loadCommunityCategory() 함수에 브랜드 ID와 회원 ID를 전달하여 호출
//        loadCommunityCategory(brandId)
//
//        binding.commuPlus1Btn.setOnClickListener {
//            navigateToBoardActivity("커뮤니티", "팬덤 문화", 301)
//        }
//
//        binding.commuPlus2Btn.setOnClickListener {
//            navigateToBoardActivity("커뮤니티", "공지사항", 302)
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
//    private fun loadCommunityCategory(brandId: Long?) {
//        val token = getCurrentToken(requireContext())
//        // Retrofit을 사용하여 API 호출
//        val callCommu: Call<RetrofitClient2.CommunityResponse> = if (brandId != null) {
//            RetrofitObject.getRetrofitService.communityLatest("Bearer $token", brandId)
//        } else {
//            // brandId가 null이면 기본값으로 0을 사용하도록 하였습니다.
//            RetrofitObject.getRetrofitService.communityLatest("Bearer $token", 0)
//        }
//        callCommu.enqueue(object : Callback<RetrofitClient2.CommunityResponse> {
//            override fun onResponse(call: Call<RetrofitClient2.CommunityResponse>, response: Response<RetrofitClient2.CommunityResponse>) {
//                if (response.isSuccessful) {
//                    val commuResponse = response.body()
//                    commuResponse?.let {
//                        updateFreeBoard(it)
//                        updateFeedbackBoard(it)
//                    }
//                } else {
//                    // API 호출은 성공했지만 응답이 실패한 경우에 대한 처리
//                    Log.e("Communityfragment", "Failed to load Communityfragment: ${response.code()}")
//                }
//            }
//
//            override fun onFailure(call: Call<RetrofitClient2.CommunityResponse>, t: Throwable) {
//                // 네트워크 호출 실패에 대한 처리
//                Log.e("Communityfragment", "Failed to load Communityfragment", t)
//            }
//        })
//    }
//
//    private fun updateFreeBoard(response: RetrofitClient2.CommunityResponse) {
//        //첫 번째 게시글
//        val firstBoard = response.result.brandCommunityBoardDtoList.firstOrNull()
//        firstBoard?.let {
//            Glide.with(requireContext())
//                .load(it.writerProfile)
//                .into(binding.commuProfile1Iv)
//            binding.commuUsernick1Tv.text = it.writerName
//            binding.commuPosttitle1Tv.text = it.title
//            binding.commuPostcontent1Tv.text = it.content
//            Glide.with(requireContext())
//                .load(it.images)
//                .into(binding.commuImage1Iv)
//            binding.commuLikecnt1Tv.text = it.likeCount.toString()
//            binding.commuCommentcnt1Tv.text = it.commentCount.toString()
//            binding.commuPosttime1Tv.text = it.writtenDate.toString()
//        }
//
//        //두 번째 게시글
//        val secondBoard = response.result.brandCommunityBoardDtoList.getOrNull(1)
//        secondBoard?.let {
//            Glide.with(requireContext())
//                .load(it.writerProfile)
//                .into(binding.commuProfile2Iv)
//            binding.commuUsernick2Tv.text = it.writerName
//            binding.commuPosttitle2Tv.text = it.title
//            binding.commuPostcontent2Tv.text = it.content
//            Glide.with(requireContext())
//                .load(it.images)
//                .into(binding.commuImage2Iv)
//            binding.commuLikecnt2Tv.text = it.likeCount.toString()
//            binding.commuCommentcnt2Tv.text = it.commentCount.toString()
//            binding.commuPosttime2Tv.text = it.writtenDate.toString()
//        }
//    }
//
//    private fun updateFeedbackBoard(response: RetrofitClient2.CommunityResponse) {
//        //첫 번째 게시글
//        val firstNotice = response.result.brandCommunityFeedBackBoardDtoList.firstOrNull()
//        firstNotice?.let {
//            Glide.with(requireContext())
//                .load(it.writerProfile)
//                .into(binding.commuProfile3Iv)
//            binding.commuUsernick3Tv.text = it.writerName
//            binding.commuPosttitle3Tv.text = it.title
//            binding.commuPostcontent3Tv.text = it.content
//            Glide.with(requireContext())
//                .load(it.images)
//                .into(binding.commuImage3Iv)
//            binding.commuLikecnt3Tv.text = it.likeCount.toString()
//            binding.commuCommentcnt3Tv.text = it.commentCount.toString()
//            binding.commuPosttime3Tv.text = it.writtenDate.toString()
//        }
//
//        //두 번째 게시글
//        val secondNotice = response.result.brandCommunityFeedBackBoardDtoList.getOrNull(1)
//        secondNotice?.let {
//            Glide.with(requireContext())
//                .load(it.writerProfile)
//                .into(binding.commuProfile4Iv)
//            binding.commuUsernick4Tv.text = it.writerName
//            binding.commuPosttitle4Tv.text = it.title
//            binding.commuPostcontent4Tv.text = it.content
//            Glide.with(requireContext())
//                .load(it.images)
//                .into(binding.commuImage4Iv)
//            binding.commuLikecnt4Tv.text = it.likeCount.toString()
//            binding.commuCommentcnt4Tv.text = it.commentCount.toString()
//            binding.commuPosttime4Tv.text = it.writtenDate.toString()
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
import com.example.brandol.board.BoardActivity
import com.example.brandol.connection.RetrofitAPI
import com.example.brandol.connection.RetrofitClient2
import com.example.brandol.connection.RetrofitObject
import com.example.brandol.databinding.FragmentCommuBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommuFragment : Fragment() {
    private var _binding: FragmentCommuBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View? {
        _binding = FragmentCommuBinding.inflate(inflater, container, false)

        // CommunityFragment 생성될 때 브랜드 ID와 회원 ID를 가져옴
        val brandId: Long? = arguments?.getLong("brandId")

        // loadCommunityCategory() 함수에 브랜드 ID와 회원 ID를 전달하여 호출
        loadCommunityCategory(2)

        binding.commuPlus1Btn.setOnClickListener {
            navigateToBoardActivity("커뮤니티", "팬덤 문화", 301)
        }

        binding.commuPlus2Btn.setOnClickListener {
            navigateToBoardActivity("커뮤니티", "공지사항", 302)
        }

        return binding.root
    }

    private fun navigateToBoardActivity(category: String, boardText: String, requestCode: Int) {
        val intent = Intent(requireContext(), BoardActivity::class.java)
        intent.putExtra("category", category)
        intent.putExtra("boardText", boardText)
        startActivityForResult(intent, requestCode)
    }

    private fun loadCommunityCategory(brandId: Long?) {
        val token = getCurrentToken(requireContext())
        // Retrofit을 사용하여 API 호출
        val callCommu: Call<RetrofitClient2.CommunityResponse> = if (brandId != null) {
            RetrofitObject.getRetrofitService.communityLatest("Bearer $token", brandId)
        } else {
            // brandId가 null이면 기본값으로 0을 사용하도록 하였습니다.
            RetrofitObject.getRetrofitService.communityLatest("Bearer $token", 0)
        }
        callCommu.enqueue(object : Callback<RetrofitClient2.CommunityResponse> {
            override fun onResponse(call: Call<RetrofitClient2.CommunityResponse>, response: Response<RetrofitClient2.CommunityResponse>) {
                if (response.isSuccessful) {
                    val commuResponse = response.body()
                    commuResponse?.let {
                        Log.d("Communityfragment", "")
                        updateFreeBoard(it)
                        updateFeedbackBoard(it)
                    }
                } else {
                    // API 호출은 성공했지만 응답이 실패한 경우에 대한 처리
                    Log.e("Communityfragment", "Failed to load Communityfragment: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<RetrofitClient2.CommunityResponse>, t: Throwable) {
                // 네트워크 호출 실패에 대한 처리
                Log.e("Communityfragment", "Failed to load Communityfragment", t)
            }
        })
    }

    private fun updateFreeBoard(response: RetrofitClient2.CommunityResponse) {
        val context = context ?: return // context가 null인 경우에는 함수를 종료합니다.

        //첫 번째 게시글
        val FreeBoard = response.result.brandCommunityBoardDtoList.firstOrNull()
        FreeBoard?.let {
            Glide.with(requireContext())
                .load(it.writerProfile)
                .into(binding.commuProfile1Iv)
            binding.commuUsernick1Tv.text = it.writerName
            binding.commuPosttitle1Tv.text = it.title
            binding.commuPostcontent1Tv.text = it.content
//            Glide.with(requireContext())
//                .load(it.images)
//                .into(binding.commuImage1Iv)
            binding.commuLikecnt1Tv.text = it.likeCount.toString()
            binding.commuCommentcnt1Tv.text = it.commentCount.toString()
            binding.commuPosttime1Tv.text = it.writtenDate.toString()
        }

        //두 번째 게시글
        FreeBoard?.let {
            Glide.with(requireContext())
                .load(it.writerProfile)
                .into(binding.commuProfile2Iv)
            binding.commuUsernick2Tv.text = it.writerName
            binding.commuPosttitle2Tv.text = it.title
            binding.commuPostcontent2Tv.text = it.content
//            Glide.with(requireContext())
//                .load(it.images)
//                .into(binding.commuImage2Iv)
            binding.commuLikecnt2Tv.text = it.likeCount.toString()
            binding.commuCommentcnt2Tv.text = it.commentCount.toString()
            binding.commuPosttime2Tv.text = it.writtenDate.toString()
        }
    }

    private fun updateFeedbackBoard(response: RetrofitClient2.CommunityResponse) {
        val context = context ?: return // context가 null인 경우에는 함수를 종료합니다.

        //첫 번째 게시글
        val FeedbackBoard = response.result.brandCommunityFeedBackBoardDtoList.firstOrNull()
        FeedbackBoard?.let {
            Glide.with(requireContext())
                .load(it.writerProfile)
                .into(binding.commuProfile3Iv)
            binding.commuUsernick3Tv.text = it.writerName
            binding.commuPosttitle3Tv.text = it.title
            binding.commuPostcontent3Tv.text = it.content
//            Glide.with(requireContext())
//                .load(it.images)
//                .into(binding.commuImage3Iv)
            binding.commuLikecnt3Tv.text = it.likeCount.toString()
            binding.commuCommentcnt3Tv.text = it.commentCount.toString()
            binding.commuPosttime3Tv.text = it.writtenDate.toString()
        }

        //두 번째 게시글
        FeedbackBoard?.let {
            Glide.with(requireContext())
                .load(it.writerProfile)
                .into(binding.commuProfile4Iv)
            binding.commuUsernick4Tv.text = it.writerName
            binding.commuPosttitle4Tv.text = it.title
            binding.commuPostcontent4Tv.text = it.content
//            Glide.with(requireContext())
//                .load(it.images)
//                .into(binding.commuImage4Iv)
            binding.commuLikecnt4Tv.text = it.likeCount.toString()
            binding.commuCommentcnt4Tv.text = it.commentCount.toString()
            binding.commuPosttime4Tv.text = it.writtenDate.toString()
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
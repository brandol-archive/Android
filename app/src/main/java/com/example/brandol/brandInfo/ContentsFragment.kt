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
import com.example.brandol.databinding.FragmentContentsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ContentsFragment : Fragment() {
    private var _binding: FragmentContentsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View? {
        _binding = FragmentContentsBinding.inflate(inflater, container, false)

        // CommunityFragment 생성될 때 브랜드 ID와 회원 ID를 가져옴
        val brandId: Long? = arguments?.getLong("brandId")

        // loadCommunityCategory() 함수에 브랜드 ID와 회원 ID를 전달하여 호출
        loadContentsCategory(2)

        binding.contentsPlus1Btn.setOnClickListener {
            navigateToBoardActivity("콘텐츠", "이벤트", 201)
        }

        binding.contentsPlus2Btn.setOnClickListener {
            navigateToBoardActivity("콘텐츠", "카드뉴스", 202)
        }

        binding.contentsPlus3Btn.setOnClickListener {
            navigateToBoardActivity("콘텐츠", "영상", 203)
        }

        return binding.root
    }

    private fun navigateToBoardActivity(category: String, boardText: String, requestCode: Int) {
        val intent = Intent(requireContext(), BoardActivity::class.java)
        intent.putExtra("boardNowcateText", category)
        intent.putExtra("boardNowboardText", boardText)
        startActivityForResult(intent, requestCode)
    }

        private fun loadContentsCategory(brandId: Long?) {
        val token = getCurrentToken(requireContext())
        // Retrofit을 사용하여 API 호출
        val callFandom: Call<RetrofitClient2.ContentsResponse> = if (brandId != null) {
            RetrofitObject.getRetrofitService.contentsLatest("Bearer $token", brandId)
        } else {
            // brandId가 null이면 기본값으로 0을 사용하도록 하였습니다.
            RetrofitObject.getRetrofitService.contentsLatest("Bearer $token", 0)
        }
        callFandom.enqueue(object : Callback<RetrofitClient2.ContentsResponse> {
            override fun onResponse(call: Call<RetrofitClient2.ContentsResponse>, response: Response<RetrofitClient2.ContentsResponse>) {
                if (response.isSuccessful) {
                    val contentsResponse = response.body()
                    contentsResponse?.let {
                        updateEvent(it)
                        updateCardnews(it)
                        updateVideo(it)
                    }
                } else {
                    // API 호출은 성공했지만 응답이 실패한 경우에 대한 처리
                    Log.e("Contentsfragment", "Failed to load Contentsfragment: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<RetrofitClient2.ContentsResponse>, t: Throwable) {
                // 네트워크 호출 실패에 대한 처리
                Log.e("Contentsfragment", "Failed to load Contentsfragment", t)
            }
        })
    }


    private fun updateEvent(response: RetrofitClient2.ContentsResponse) {
        val context = context ?: return // context가 null인 경우에는 함수를 종료합니다.

        //첫 번째 이벤트 게시글
        val Event = response.result.brandContentsEventDtoList.firstOrNull()
        Event?.let {
            Glide.with(requireContext())
                .load(Event.writerProfile)
                .into(binding.contentsProfile1Iv)
            binding.contentsUsernick1Tv.text = Event.writerName
            binding.contentsPosttitle1Tv.text = Event.title
            binding.contentsPostcontent1Tv.text = Event.content
//            Glide.with(requireContext())
//                .load(Event.images)
//                .into(binding.contentsImage1Iv)
            binding.contentsLikecnt1Tv.text = Event.likeCount.toString()
            binding.contentsCommentcnt1Tv.text = Event.commentCount.toString()
            binding.contentsPosttime1Tv.text = Event.writtenDate.toString()
        }

        //두 번째 이벤트 게시글
        Event?.let {
            Glide.with(requireContext())
                .load(Event.writerProfile)
                .into(binding.contentsProfile2Iv)
            binding.contentsUsernick2Tv.text = Event.writerName
            binding.contentsPosttitle2Tv.text = Event.title
            binding.contentsPostcontent2Tv.text = Event.content
//            Glide.with(requireContext())
//                .load(Event.images)
//                .into(binding.contentsImage2Iv)
            binding.contentsLikecnt2Tv.text = Event.likeCount.toString()
            binding.contentsCommentcnt2Tv.text = Event.commentCount.toString()
            binding.contentsPosttime2Tv.text = Event.writtenDate.toString()
        }
    }

    private fun updateCardnews(response: RetrofitClient2.ContentsResponse) {
        val context = context ?: return // context가 null인 경우에는 함수를 종료합니다.

        //첫 번째 카드뉴스 게시글
        val CardNews = response.result.brandContentsCardNewsDtoList.firstOrNull()
        CardNews?.let {
            Glide.with(requireContext())
                .load(CardNews.writerProfile)
                .into(binding.contentsProfile3Iv)
            binding.contentsUsernick3Tv.text = CardNews.writerName
            binding.contentsPosttitle3Tv.text = CardNews.title
            binding.contentsPostcontent3Tv.text = CardNews.content
//            Glide.with(requireContext())
//                .load(CardNews.images)
//                .into(binding.contentsImage3Iv)
            binding.contentsLikecnt3Tv.text = CardNews.likeCount.toString()
            binding.contentsCommentcnt3Tv.text = CardNews.commentCount.toString()
            binding.contentsPosttime3Tv.text = CardNews.writtenDate.toString()
        }

        //두 번째 카드뉴스 게시글
        CardNews?.let {
            Glide.with(requireContext())
                .load(CardNews.writerProfile)
                .into(binding.contentsProfile4Iv)
            binding.contentsUsernick4Tv.text = CardNews.writerName
            binding.contentsPosttitle4Tv.text = CardNews.title
            binding.contentsPostcontent4Tv.text = CardNews.content
//            Glide.with(requireContext())
//                .load(CardNews.images)
//                .into(binding.contentsImage4Iv)
            binding.contentsLikecnt4Tv.text = CardNews.likeCount.toString()
            binding.contentsCommentcnt4Tv.text = CardNews.commentCount.toString()
            binding.contentsPosttime4Tv.text = CardNews.writtenDate.toString()
        }
    }

    private fun updateVideo(response: RetrofitClient2.ContentsResponse) {
        val context = context ?: return // context가 null인 경우에는 함수를 종료합니다.

        //첫 번째 영상 게시글
        val Video = response.result.brandContentsVideoDtoList.firstOrNull()
        Video?.let {
            Glide.with(requireContext())
                .load(Video.writerProfile)
                .into(binding.contentsProfile5Iv)
            binding.contentsUsernick5Tv.text = Video.writerName
            binding.contentsPosttitle5Tv.text = Video.title
            binding.contentsPostcontent5Tv.text = Video.content
//            Glide.with(requireContext())
//                .load(Video.images)
//                .into(binding.contentsImage5Iv)
            binding.contentsLikecnt5Tv.text = Video.likeCount.toString()
            binding.contentsCommentcnt5Tv.text = Video.commentCount.toString()
            binding.contentsPosttime5Tv.text = Video.writtenDate.toString()
        }

        //두 번째 영상 게시글
        Video?.let {
            Glide.with(requireContext())
                .load(Video.writerProfile)
                .into(binding.contentsProfile6Iv)
            binding.contentsUsernick6Tv.text = Video.writerName
            binding.contentsPosttitle6Tv.text = Video.title
            binding.contentsPostcontent6Tv.text = Video.content
//            Glide.with(requireContext())
//                .load(Video.images)
//                .into(binding.contentsImage6Iv)
            binding.contentsLikecnt6Tv.text = Video.likeCount.toString()
            binding.contentsCommentcnt6Tv.text = Video.commentCount.toString()
            binding.contentsPosttime6Tv.text = Video.writtenDate.toString()
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
//import com.example.brandol.databinding.FragmentContentsBinding
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//class ContentsFragment : Fragment() {
//
//    private var _binding: FragmentContentsBinding? = null
//    private val binding get() = _binding!!
//    private lateinit var retrofitAPI: RetrofitAPI // Declare RetrofitAPI instance
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        _binding = FragmentContentsBinding.inflate(inflater, container, false)
//
//        // RetrofitAPI 초기화
//        retrofitAPI = RetrofitObject.getRetrofitService
//
//        // ContentsFragment 생성될 때 브랜드 ID와 회원 ID를 가져옴
//        val brandId: Long? = arguments?.getLong("brandId")
//
//        // loadContentsCategory() 함수에 브랜드 ID와 회원 ID를 전달하여 호출
//        loadContentsCategory(brandId)
//
//        binding.contentsPlus1Btn.setOnClickListener {
//            navigateToBoardActivity("콘텐츠", "이벤트", 201)
//        }
//
//        binding.contentsPlus2Btn.setOnClickListener {
//            navigateToBoardActivity("콘텐츠", "카드뉴스", 202)
//        }
//
//        binding.contentsPlus3Btn.setOnClickListener {
//            navigateToBoardActivity("콘텐츠", "영상", 203)
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
//    private fun loadContentsCategory(brandId: Long?) {
//        val token = getCurrentToken(requireContext())
//        // Retrofit을 사용하여 API 호출
//        val callFandom: Call<RetrofitClient2.ContentsResponse> = if (brandId != null) {
//            RetrofitObject.getRetrofitService.contentsLatest("Bearer $token", brandId)
//        } else {
//            // brandId가 null이면 기본값으로 0을 사용하도록 하였습니다.
//            RetrofitObject.getRetrofitService.contentsLatest("Bearer $token", 0)
//        }
//        callFandom.enqueue(object : Callback<RetrofitClient2.ContentsResponse> {
//            override fun onResponse(call: Call<RetrofitClient2.ContentsResponse>, response: Response<RetrofitClient2.ContentsResponse>) {
//                if (response.isSuccessful) {
//                    val contentsResponse = response.body()
//                    contentsResponse?.let {
//                        updateEvent(it)
//                        updateCardnews(it)
//                        updateVideo(it)
//                    }
//                } else {
//                    // API 호출은 성공했지만 응답이 실패한 경우에 대한 처리
//                    Log.e("Contentsfragment", "Failed to load Contentsfragment: ${response.code()}")
//                }
//            }
//
//            override fun onFailure(call: Call<RetrofitClient2.ContentsResponse>, t: Throwable) {
//                // 네트워크 호출 실패에 대한 처리
//                Log.e("Contentsfragment", "Failed to load Contentsfragment", t)
//            }
//        })
//    }
//
//    private fun updateEvent(response: RetrofitClient2.ContentsResponse) {
//        //첫 번째 이벤트 게시글
//        val firstEvent = response.result.brandContentsEventDtoList.firstOrNull()
//        if (firstEvent != null && isAdded) { // Fragment가 연결된 상태인지 확인
//            Glide.with(requireContext())
//                .load(firstEvent.writerProfile)
//                .into(binding.contentsProfile1Iv)
//            binding.contentsUsernick1Tv.text = firstEvent.writerName
//            binding.contentsPosttitle1Tv.text = firstEvent.title
//            binding.contentsPostcontent1Tv.text = firstEvent.content
//            Glide.with(requireContext())
//                .load(firstEvent.images)
//                .into(binding.contentsImage1Iv)
//            binding.contentsLikecnt1Tv.text = firstEvent.likeCount.toString()
//            binding.contentsCommentcnt1Tv.text = firstEvent.commentCount.toString()
//            binding.contentsPosttime1Tv.text = firstEvent.writtenDate.toString()
//        }
//
//        //두 번째 이벤트 게시글
//        val secondEvent = response.result.brandContentsEventDtoList.getOrNull(1)
//        if (secondEvent != null && isAdded) { // Fragment가 연결된 상태인지 확인
//            Glide.with(requireContext())
//                .load(secondEvent.writerProfile)
//                .into(binding.contentsProfile2Iv)
//            binding.contentsUsernick2Tv.text = secondEvent.writerName
//            binding.contentsPosttitle2Tv.text = secondEvent.title
//            binding.contentsPostcontent2Tv.text = secondEvent.content
//            Glide.with(requireContext())
//                .load(secondEvent.images)
//                .into(binding.contentsImage2Iv)
//            binding.contentsLikecnt2Tv.text = secondEvent.likeCount.toString()
//            binding.contentsCommentcnt2Tv.text = secondEvent.commentCount.toString()
//            binding.contentsPosttime2Tv.text = secondEvent.writtenDate.toString()
//        }
//    }
//
//    private fun updateCardnews(response: RetrofitClient2.ContentsResponse) {
//        //첫 번째 카드뉴스 게시글
//        val firstCardNews = response.result.brandContentsCardNewsDtoList.firstOrNull()
//        if (firstCardNews != null && isAdded) { // Fragment가 연결된 상태인지 확인
//            Glide.with(requireContext())
//                .load(firstCardNews.writerProfile)
//                .into(binding.contentsProfile3Iv)
//            binding.contentsUsernick3Tv.text = firstCardNews.writerName
//            binding.contentsPosttitle3Tv.text = firstCardNews.title
//            binding.contentsPostcontent3Tv.text = firstCardNews.content
//            Glide.with(requireContext())
//                .load(firstCardNews.images)
//                .into(binding.contentsImage3Iv)
//            binding.contentsLikecnt3Tv.text = firstCardNews.likeCount.toString()
//            binding.contentsCommentcnt3Tv.text = firstCardNews.commentCount.toString()
//            binding.contentsPosttime3Tv.text = firstCardNews.writtenDate.toString()
//        }
//
//        //두 번째 카드뉴스 게시글
//        val secondCardNews = response.result.brandContentsCardNewsDtoList.getOrNull(1)
//        if (secondCardNews != null && isAdded) { // Fragment가 연결된 상태인지 확인
//            Glide.with(requireContext())
//                .load(secondCardNews.writerProfile)
//                .into(binding.contentsProfile4Iv)
//            binding.contentsUsernick4Tv.text = secondCardNews.writerName
//            binding.contentsPosttitle4Tv.text = secondCardNews.title
//            binding.contentsPostcontent4Tv.text = secondCardNews.content
//            Glide.with(requireContext())
//                .load(secondCardNews.images)
//                .into(binding.contentsImage4Iv)
//            binding.contentsLikecnt4Tv.text = secondCardNews.likeCount.toString()
//            binding.contentsCommentcnt4Tv.text = secondCardNews.commentCount.toString()
//            binding.contentsPosttime4Tv.text = secondCardNews.writtenDate.toString()
//        }
//    }
//
//    private fun updateVideo(response: RetrofitClient2.ContentsResponse) {
//        //첫 번째 영상 게시글
//        val firstVideo = response.result.brandContentsVideoDtoList.firstOrNull()
//        if (firstVideo != null && isAdded) { // Fragment가 연결된 상태인지 확인
//            Glide.with(requireContext())
//                .load(firstVideo.writerProfile)
//                .into(binding.contentsProfile5Iv)
//            binding.contentsUsernick5Tv.text = firstVideo.writerName
//            binding.contentsPosttitle5Tv.text = firstVideo.title
//            binding.contentsPostcontent5Tv.text = firstVideo.content
//            Glide.with(requireContext())
//                .load(firstVideo.images)
//                .into(binding.contentsImage5Iv)
//            binding.contentsLikecnt5Tv.text = firstVideo.likeCount.toString()
//            binding.contentsCommentcnt5Tv.text = firstVideo.commentCount.toString()
//            binding.contentsPosttime5Tv.text = firstVideo.writtenDate.toString()
//        }
//
//        //두 번째 영상 게시글
//        val secondVideo = response.result.brandContentsVideoDtoList.getOrNull(1)
//        if (secondVideo != null && isAdded) { // Fragment가 연결된 상태인지 확인
//            Glide.with(requireContext())
//                .load(secondVideo.writerProfile)
//                .into(binding.contentsProfile6Iv)
//            binding.contentsUsernick6Tv.text = secondVideo.writerName
//            binding.contentsPosttitle6Tv.text = secondVideo.title
//            binding.contentsPostcontent6Tv.text = secondVideo.content
//            Glide.with(requireContext())
//                .load(secondVideo.images)
//                .into(binding.contentsImage6Iv)
//            binding.contentsLikecnt6Tv.text = secondVideo.likeCount.toString()
//            binding.contentsCommentcnt6Tv.text = secondVideo.commentCount.toString()
//            binding.contentsPosttime6Tv.text = secondVideo.writtenDate.toString()
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

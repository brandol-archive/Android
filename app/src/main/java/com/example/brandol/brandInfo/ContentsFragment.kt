package com.example.brandol.brandInfo

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
import com.example.brandol.databinding.FragmentContentsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ContentsFragment : Fragment() {

    private var _binding: FragmentContentsBinding? = null
    private val binding get() = _binding!!
    private lateinit var retrofitAPI: RetrofitAPI // Declare RetrofitAPI instance

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContentsBinding.inflate(inflater, container, false)

        // ContentsFragment 생성될 때 브랜드 ID와 회원 ID를 가져옴
        val brandId: Long? = arguments?.getLong("brandId")

        // loadContentsCategory() 함수에 브랜드 ID와 회원 ID를 전달하여 호출
        loadContentsCategory(brandId)

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
        // Retrofit을 사용하여 API 호출
        val callFandom: Call<RetrofitClient2.ContentsResponse> = if (brandId != null) {
            retrofitAPI.contentsLatest(brandId)
        } else {
            // brandId가 null이면 기본값으로 0을 사용하도록 하였습니다.
            retrofitAPI.contentsLatest(0)
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
        //첫 번째 게시글
        Glide.with(requireContext())
            .load(response.result.brandContentsEventDtoList.writerProfile)
            .into(binding.contentsProfile1Iv)
        binding.contentsUsernick1Tv.text = response.result.brandContentsEventDtoList.writerName
        binding.contentsPosttitle1Tv.text = response.result.brandContentsEventDtoList.title
        binding.contentsPostcontent1Tv.text = response.result.brandContentsEventDtoList.content
        Glide.with(requireContext())
            .load(response.result.brandContentsEventDtoList.images)
            .into(binding.contentsImage1Iv)
        binding.contentsLikecnt1Tv.text = response.result.brandContentsEventDtoList.likeCount.toString()
        binding.contentsCommentcnt1Tv.text = response.result.brandContentsEventDtoList.commentCount.toString()
        binding.contentsPosttime1Tv.text = response.result.brandContentsEventDtoList.writtenDate.toString()

        //두 번째 게시글
        Glide.with(requireContext())
            .load(response.result.brandContentsEventDtoList.writerProfile)
            .into(binding.contentsProfile2Iv)
        binding.contentsUsernick2Tv.text = response.result.brandContentsEventDtoList.writerName
        binding.contentsPosttitle2Tv.text = response.result.brandContentsEventDtoList.title
        binding.contentsPostcontent2Tv.text = response.result.brandContentsEventDtoList.content
        Glide.with(requireContext())
            .load(response.result.brandContentsEventDtoList.images)
            .into(binding.contentsImage2Iv)
        binding.contentsLikecnt2Tv.text = response.result.brandContentsEventDtoList.likeCount.toString()
        binding.contentsCommentcnt2Tv.text = response.result.brandContentsEventDtoList.commentCount.toString()
        binding.contentsPosttime2Tv.text = response.result.brandContentsEventDtoList.writtenDate.toString()
    }

    private fun updateCardnews(response: RetrofitClient2.ContentsResponse) {
        //첫 번째 게시글
        Glide.with(requireContext())
            .load(response.result.brandContentsCardNewsDtoList.writerProfile)
            .into(binding.contentsProfile3Iv)
        binding.contentsUsernick3Tv.text = response.result.brandContentsCardNewsDtoList.writerName
        binding.contentsPosttitle3Tv.text = response.result.brandContentsCardNewsDtoList.title
        binding.contentsPostcontent3Tv.text = response.result.brandContentsCardNewsDtoList.content
        Glide.with(requireContext())
            .load(response.result.brandContentsCardNewsDtoList.images)
            .into(binding.contentsImage3Iv)
        binding.contentsLikecnt3Tv.text = response.result.brandContentsCardNewsDtoList.likeCount.toString()
        binding.contentsCommentcnt3Tv.text = response.result.brandContentsCardNewsDtoList.commentCount.toString()
        binding.contentsPosttime3Tv.text = response.result.brandContentsCardNewsDtoList.writtenDate.toString()

        //두 번째 게시글
        Glide.with(requireContext())
            .load(response.result.brandContentsCardNewsDtoList.writerProfile)
            .into(binding.contentsProfile4Iv)
        binding.contentsUsernick4Tv.text = response.result.brandContentsCardNewsDtoList.writerName
        binding.contentsPosttitle4Tv.text = response.result.brandContentsCardNewsDtoList.title
        binding.contentsPostcontent4Tv.text = response.result.brandContentsCardNewsDtoList.content
        Glide.with(requireContext())
            .load(response.result.brandContentsCardNewsDtoList.images)
            .into(binding.contentsImage4Iv)
        binding.contentsLikecnt4Tv.text = response.result.brandContentsCardNewsDtoList.likeCount.toString()
        binding.contentsCommentcnt4Tv.text = response.result.brandContentsCardNewsDtoList.commentCount.toString()
        binding.contentsPosttime4Tv.text = response.result.brandContentsCardNewsDtoList.writtenDate.toString()
    }

    private fun updateVideo(response: RetrofitClient2.ContentsResponse) {
        //다섯 번째 게시글
        Glide.with(requireContext())
            .load(response.result.brandContentsVideoDtoList.writerProfile)
            .into(binding.contentsProfile5Iv)
        binding.contentsUsernick5Tv.text = response.result.brandContentsVideoDtoList.writerName
        binding.contentsPosttitle5Tv.text = response.result.brandContentsVideoDtoList.title
        binding.contentsPostcontent5Tv.text = response.result.brandContentsVideoDtoList.content
        Glide.with(requireContext())
            .load(response.result.brandContentsVideoDtoList.images)
            .into(binding.contentsImage5Iv)
        binding.contentsLikecnt5Tv.text = response.result.brandContentsVideoDtoList.likeCount.toString()
        binding.contentsCommentcnt5Tv.text = response.result.brandContentsVideoDtoList.commentCount.toString()
        binding.contentsPosttime5Tv.text = response.result.brandContentsVideoDtoList.writtenDate.toString()

        //여섯 번째 게시글
        Glide.with(requireContext())
            .load(response.result.brandContentsVideoDtoList.writerProfile)
            .into(binding.contentsProfile6Iv)
        binding.contentsUsernick6Tv.text = response.result.brandContentsVideoDtoList.writerName
        binding.contentsPosttitle6Tv.text = response.result.brandContentsVideoDtoList.title
        binding.contentsPostcontent6Tv.text = response.result.brandContentsVideoDtoList.content
        Glide.with(requireContext())
            .load(response.result.brandContentsVideoDtoList.images)
            .into(binding.contentsImage6Iv)
        binding.contentsLikecnt6Tv.text = response.result.brandContentsVideoDtoList.likeCount.toString()
        binding.contentsCommentcnt6Tv.text = response.result.brandContentsVideoDtoList.commentCount.toString()
        binding.contentsPosttime6Tv.text = response.result.brandContentsVideoDtoList.writtenDate.toString()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
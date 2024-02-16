package com.example.brandol.brandInfo

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
import com.example.brandol.connection.RetrofitAPI
import com.example.brandol.connection.RetrofitClient2
import com.example.brandol.databinding.FragmentFandomBinding
import com.example.brandol.databinding.FragmentMyBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FandomFragment : Fragment() {

    private var _binding: FragmentFandomBinding? = null
    private val binding get() = _binding!!
    private lateinit var retrofitAPI: RetrofitAPI // Declare RetrofitAPI instance

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFandomBinding.inflate(inflater, container, false)

        // FandomFragment 생성될 때 브랜드 ID와 회원 ID를 가져옴
        val brandId: Long? = arguments?.getLong("brandId")

        // loadFandomCategory() 함수에 브랜드 ID와 회원 ID를 전달하여 호출
        loadFandomCategory(brandId)

        binding.fandomPlus1Btn.setOnClickListener {
            navigateToBoardActivity("FANDOM", "팬덤 문화", 101)
        }

        binding.fandomPlus2Btn.setOnClickListener {
            navigateToBoardActivity("FANDOM", "공지사항", 102)
        }

        return binding.root
    }

    private fun navigateToBoardActivity(category: String, boardText: String, requestCode: Int) {
        val intent = Intent(requireContext(), BoardActivity::class.java)
        intent.putExtra("boardNowcateText", category)
        intent.putExtra("boardNowboardText", boardText)
        startActivityForResult(intent, requestCode)
    }

    private fun loadFandomCategory(brandId: Long?) {
        // Retrofit을 사용하여 API 호출
        val callFandom: Call<RetrofitClient2.FandomResponse> = if (brandId != null) {
            retrofitAPI.fandomLatest(brandId)
        } else {
            // brandId가 null이면 기본값으로 0을 사용하도록 하였습니다.
            retrofitAPI.fandomLatest(0)
        }
        callFandom.enqueue(object : Callback<RetrofitClient2.FandomResponse> {
            override fun onResponse(call: Call<RetrofitClient2.FandomResponse>, response: Response<RetrofitClient2.FandomResponse>) {
                if (response.isSuccessful) {
                    val fandomResponse = response.body()
                    fandomResponse?.let {
                        updateFandomCulture(it)
                        updateNotice(it)
                    }
                } else {
                    // API 호출은 성공했지만 응답이 실패한 경우에 대한 처리
                    Log.e("Fandomfragment", "Failed to load Fandomfragment: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<RetrofitClient2.FandomResponse>, t: Throwable) {
                // 네트워크 호출 실패에 대한 처리
                Log.e("Fandomfragment", "Failed to load Fandomfragment", t)
            }
        })
    }

    private fun updateFandomCulture(response: RetrofitClient2.FandomResponse) {
        //첫 번째 게시글
        Glide.with(requireContext())
            .load(response.result.brandFandomCultureDtoList.writerProfile)
            .into(binding.fandomProfile1Iv)
        binding.fandomUsernick1Tv.text = response.result.brandFandomCultureDtoList.writerName
        binding.fandomPosttitle1Tv.text = response.result.brandFandomCultureDtoList.title
        binding.fandomPostcontent1Tv.text = response.result.brandFandomCultureDtoList.content
        Glide.with(requireContext())
            .load(response.result.brandFandomCultureDtoList.images)
            .into(binding.fandomImage1Iv)
        binding.fandomLikecnt1Tv.text = response.result.brandFandomCultureDtoList.likeCount.toString()
        binding.fandomCommentcnt1Tv.text = response.result.brandFandomCultureDtoList.commentCount.toString()
        binding.fandomPosttime1Tv.text = response.result.brandFandomCultureDtoList.writtenDate.toString()

        //두 번째 게시글
        Glide.with(requireContext())
            .load(response.result.brandFandomCultureDtoList.writerProfile)
            .into(binding.fandomProfile2Iv)
        binding.fandomUsernick2Tv.text = response.result.brandFandomCultureDtoList.writerName
        binding.fandomPosttitle2Tv.text = response.result.brandFandomCultureDtoList.title
        binding.fandomPostcontent2Tv.text = response.result.brandFandomCultureDtoList.content
        Glide.with(requireContext())
            .load(response.result.brandFandomCultureDtoList.images)
            .into(binding.fandomImage2Iv)
        binding.fandomLikecnt2Tv.text = response.result.brandFandomCultureDtoList.likeCount.toString()
        binding.fandomCommentcnt2Tv.text = response.result.brandFandomCultureDtoList.commentCount.toString()
        binding.fandomPosttime2Tv.text = response.result.brandFandomCultureDtoList.writtenDate.toString()
    }

    private fun updateNotice(response: RetrofitClient2.FandomResponse) {
        //첫 번째 게시글
        Glide.with(requireContext())
            .load(response.result.brandFandomAnnouncementDtoList.writerProfile)
            .into(binding.fandomProfile3Iv)
        binding.fandomUsernick3Tv.text = response.result.brandFandomAnnouncementDtoList.writerName
        binding.fandomPosttitle3Tv.text = response.result.brandFandomAnnouncementDtoList.title
        binding.fandomPostcontent3Tv.text = response.result.brandFandomAnnouncementDtoList.content
        Glide.with(requireContext())
            .load(response.result.brandFandomAnnouncementDtoList.images)
            .into(binding.fandomImage3Iv)
        binding.fandomLikecnt3Tv.text = response.result.brandFandomAnnouncementDtoList.likeCount.toString()
        binding.fandomCommentcnt3Tv.text = response.result.brandFandomAnnouncementDtoList.commentCount.toString()
        binding.fandomPosttime3Tv.text = response.result.brandFandomAnnouncementDtoList.writtenDate.toString()

        //두 번째 게시글
        Glide.with(requireContext())
            .load(response.result.brandFandomAnnouncementDtoList.writerProfile)
            .into(binding.fandomProfile4Iv)
        binding.fandomUsernick4Tv.text = response.result.brandFandomAnnouncementDtoList.writerName
        binding.fandomPosttitle4Tv.text = response.result.brandFandomAnnouncementDtoList.title
        binding.fandomPostcontent4Tv.text = response.result.brandFandomAnnouncementDtoList.content
        Glide.with(requireContext())
            .load(response.result.brandFandomAnnouncementDtoList.images)
            .into(binding.fandomImage4Iv)
        binding.fandomLikecnt4Tv.text = response.result.brandFandomAnnouncementDtoList.likeCount.toString()
        binding.fandomCommentcnt4Tv.text = response.result.brandFandomAnnouncementDtoList.commentCount.toString()
        binding.fandomPosttime4Tv.text = response.result.brandFandomAnnouncementDtoList.writtenDate.toString()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
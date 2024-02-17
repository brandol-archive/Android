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
import com.example.brandol.connection.RetrofitAPI
import com.example.brandol.connection.RetrofitClient2
import com.example.brandol.connection.RetrofitObject
import com.example.brandol.databinding.FragmentFandomBinding
import com.example.brandol.databinding.FragmentMyBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FandomFragment : Fragment() {

    private var _binding: FragmentFandomBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFandomBinding.inflate(inflater, container, false)

        // FandomFragment 생성될 때 브랜드 ID와 회원 ID를 가져옴
        val brandId: Long? = arguments?.getLong("brandId")

        // loadFandomCategory() 함수에 브랜드 ID와 회원 ID를 전달하여 호출
        loadFandomCategory(2)

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
        val token = getCurrentToken(requireContext())
        Log.d("brandId", "brandId1: $brandId")
        // Retrofit을 사용하여 API 호출
        val callFandom: Call<RetrofitClient2.FandomResponse> = if (brandId != null) {
            RetrofitObject.getRetrofitService.fandomLatest("Bearer $token", brandId)
        } else {
            // brandId가 null이면 기본값으로 0을 사용하도록 하였습니다.
            RetrofitObject.getRetrofitService.fandomLatest("Bearer $token", 0)
        }
        callFandom.enqueue(object : Callback<RetrofitClient2.FandomResponse> {
            override fun onResponse(call: Call<RetrofitClient2.FandomResponse>, response: Response<RetrofitClient2.FandomResponse>) {
                Log.d("fandomLatest1", "${response.toString()}")
                if (response.isSuccessful) {
                    val fandomResponse = response.body()
                    Log.d("fandomLatest2", "${fandomResponse.toString()}")
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
        val context = context ?: return // context가 null인 경우에는 함수를 종료합니다.

        //첫 번째 게시글
        val Culture = response.result.brandFandomCultureDtoList.firstOrNull()
        Culture?.let {
            Glide.with(this)
                .load(it.writerProfile)
                .into(binding.fandomProfile1Iv)
            binding.fandomUsernick1Tv.text = it.writerName
            binding.fandomPosttitle1Tv.text = it.title
            binding.fandomPostcontent1Tv.text = it.content
//            Glide.with(this)
//                .load(it.images)
//                .into(binding.fandomImage1Iv)
            binding.fandomLikecnt1Tv.text = it.likeCount.toString()
            binding.fandomCommentcnt1Tv.text = it.commentCount.toString()
            binding.fandomPosttime1Tv.text = it.writtenDate.toString()
        }

        //두 번째 게시글
        Culture?.let {
            Glide.with(this)
                .load(it.writerProfile)
                .into(binding.fandomProfile2Iv)
            binding.fandomUsernick2Tv.text = it.writerName
            binding.fandomPosttitle2Tv.text = it.title
            binding.fandomPostcontent2Tv.text = it.content
//            Glide.with(this)
//                .load(it.images)
//                .into(binding.fandomImage2Iv)
            binding.fandomLikecnt2Tv.text = it.likeCount.toString()
            binding.fandomCommentcnt2Tv.text = it.commentCount.toString()
            binding.fandomPosttime2Tv.text = it.writtenDate.toString()
        }
    }

    private fun updateNotice(response: RetrofitClient2.FandomResponse) {
        val context = context ?: return // context가 null인 경우에는 함수를 종료합니다.

        //첫 번째 게시글
        val Notice = response.result.brandFandomAnnouncementDtoList.firstOrNull()
        Notice?.let {
            Glide.with(this)
                .load(it.writerProfile)
                .into(binding.fandomProfile3Iv)
            binding.fandomUsernick3Tv.text = it.writerName
            binding.fandomPosttitle3Tv.text = it.title
            binding.fandomPostcontent3Tv.text = it.content
//            Glide.with(this)
//                .load(it.images)
//                .into(binding.fandomImage3Iv)
            binding.fandomLikecnt3Tv.text = it.likeCount.toString()
            binding.fandomCommentcnt3Tv.text = it.commentCount.toString()
            //binding.fandomPosttime3Tv.text = it.writtenDate.toString()
        }

        //두 번째 게시글
        Notice?.let {
            Glide.with(this)
                .load(it.writerProfile)
                .into(binding.fandomProfile4Iv)
            binding.fandomUsernick4Tv.text = it.writerName
            binding.fandomPosttitle4Tv.text = it.title
            binding.fandomPostcontent4Tv.text = it.content
//            Glide.with(this)
//                .load(it.images)
//                .into(binding.fandomImage4Iv)
            binding.fandomLikecnt4Tv.text = it.likeCount.toString()
            binding.fandomCommentcnt4Tv.text = it.commentCount.toString()
            //binding.fandomPosttime4Tv.text = it.writtenDate.toString()
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
//import com.example.brandol.R
//import com.example.brandol.board.BoardActivity
//import com.example.brandol.connection.RetrofitAPI
//import com.example.brandol.connection.RetrofitClient2
//import com.example.brandol.connection.RetrofitObject
//import com.example.brandol.databinding.FragmentFandomBinding
//import com.example.brandol.databinding.FragmentMyBinding
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//
//class FandomFragment : Fragment() {
//
//    private var _binding: FragmentFandomBinding? = null
//    private val binding get() = _binding!!
//    private lateinit var retrofitAPI: RetrofitAPI // Declare RetrofitAPI instance
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        _binding = FragmentFandomBinding.inflate(inflater, container, false)
//
//        // RetrofitAPI 초기화
//        retrofitAPI = RetrofitObject.getRetrofitService
//
//        // FandomFragment 생성될 때 브랜드 ID를 가져옴
//        //val brandId: Long? = arguments?.getLong("brandId")
//
//        // loadFandomCategory() 함수에 브랜드 ID와 회원 ID를 전달하여 호출
//        //loadFandomCategory(1)
//
//        binding.fandomPlus1Btn.setOnClickListener {
//            navigateToBoardActivity("FANDOM", "팬덤 문화", 101)
//        }
//
//        binding.fandomPlus2Btn.setOnClickListener {
//            navigateToBoardActivity("FANDOM", "공지사항", 102)
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
//    private fun loadFandomCategory(brandId: Long?) {
//        val token = getCurrentToken(requireContext())
//        Log.d("brandId", "brandId1: $brandId")
//        // Retrofit을 사용하여 API 호출
//        val callFandom: Call<RetrofitClient2.FandomResponse> = if (brandId != null) {
//            RetrofitObject.getRetrofitService.fandomLatest("Bearer $token", brandId)
//        } else {
//            // brandId가 null이면 기본값으로 0을 사용하도록 하였습니다.
//            RetrofitObject.getRetrofitService.fandomLatest("Bearer $token", 0)
//        }
//        callFandom.enqueue(object : Callback<RetrofitClient2.FandomResponse> {
//            override fun onResponse(call: Call<RetrofitClient2.FandomResponse>, response: Response<RetrofitClient2.FandomResponse>) {
//                Log.d("brandId", "brandId2: ${response.toString()}")
//                if (response.isSuccessful) {
//                    val fandomResponse = response.body()
//                    Log.d("brandId", "brandId2: ${fandomResponse.toString()}")
//                    fandomResponse?.let {
//                        updateFandomCulture(it)
//                        updateNotice(it)
//                    }
//                } else {
//                    // API 호출은 성공했지만 응답이 실패한 경우에 대한 처리
//                    Log.e("Fandomfragment", "Failed to load Fandomfragment: ${response.code()}")
//                }
//            }
//
//            override fun onFailure(call: Call<RetrofitClient2.FandomResponse>, t: Throwable) {
//                // 네트워크 호출 실패에 대한 처리
//                Log.e("Fandomfragment", "Failed to load Fandomfragment", t)
//            }
//        })
//    }
//
//    private fun updateFandomCulture(response: RetrofitClient2.FandomResponse) {
//        val context = context ?: return // context가 null인 경우에는 함수를 종료합니다.
//
//        //첫 번째 게시글
//        val firstCulture = response.result.brandFandomCultureDtoList.firstOrNull()
//        firstCulture?.let {
//            Glide.with(this)
//                .load(it.writerProfile)
//                .into(binding.fandomProfile1Iv)
//            binding.fandomUsernick1Tv.text = it.writerName
//            binding.fandomPosttitle1Tv.text = it.title
//            binding.fandomPostcontent1Tv.text = it.content
//            Glide.with(this)
//                .load(it.images)
//                .into(binding.fandomImage1Iv)
//            binding.fandomLikecnt1Tv.text = it.likeCount.toString()
//            binding.fandomCommentcnt1Tv.text = it.commentCount.toString()
//            //binding.fandomPosttime1Tv.text = it.writtenDate.toString()
//        }
//
//        //두 번째 게시글
//        val secondCulture = response.result.brandFandomCultureDtoList.getOrNull(1)
//        secondCulture?.let {
//            Glide.with(this)
//                .load(it.writerProfile)
//                .into(binding.fandomProfile2Iv)
//            binding.fandomUsernick2Tv.text = it.writerName
//            binding.fandomPosttitle2Tv.text = it.title
//            binding.fandomPostcontent2Tv.text = it.content
//            Glide.with(this)
//                .load(it.images)
//                .into(binding.fandomImage2Iv)
//            binding.fandomLikecnt2Tv.text = it.likeCount.toString()
//            binding.fandomCommentcnt2Tv.text = it.commentCount.toString()
//            //binding.fandomPosttime2Tv.text = it.writtenDate.toString()
//        }
//    }
//
//
//    private fun updateNotice(response: RetrofitClient2.FandomResponse) {
//        val context = context ?: return // context가 null인 경우에는 함수를 종료합니다.
//
//        //첫 번째 게시글
//        val firstNotice = response.result.brandFandomAnnouncementDtoList.firstOrNull()
//        firstNotice?.let {
//            Glide.with(this)
//                .load(it.writerProfile)
//                .into(binding.fandomProfile3Iv)
//            binding.fandomUsernick3Tv.text = it.writerName
//            binding.fandomPosttitle3Tv.text = it.title
//            binding.fandomPostcontent3Tv.text = it.content
//            Glide.with(this)
//                .load(it.images)
//                .into(binding.fandomImage3Iv)
//            binding.fandomLikecnt3Tv.text = it.likeCount.toString()
//            binding.fandomCommentcnt3Tv.text = it.commentCount.toString()
//            //binding.fandomPosttime3Tv.text = it.writtenDate.toString()
//        }
//
//        //두 번째 게시글
//        val secondNotice = response.result.brandFandomAnnouncementDtoList.getOrNull(1)
//        secondNotice?.let {
//            Glide.with(this)
//                .load(it.writerProfile)
//                .into(binding.fandomProfile4Iv)
//            binding.fandomUsernick4Tv.text = it.writerName
//            binding.fandomPosttitle4Tv.text = it.title
//            binding.fandomPostcontent4Tv.text = it.content
//            Glide.with(this)
//                .load(it.images)
//                .into(binding.fandomImage4Iv)
//            binding.fandomLikecnt4Tv.text = it.likeCount.toString()
//            binding.fandomCommentcnt4Tv.text = it.commentCount.toString()
//            //binding.fandomPosttime4Tv.text = it.writtenDate.toString()
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
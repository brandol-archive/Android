package com.example.brandol.searchCategory

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.brandol.R
import com.example.brandol.adaptor.CategoryAdapter
import com.example.brandol.connection.RetrofitClient2
import com.example.brandol.connection.RetrofitObject
import com.example.brandol.databinding.FragmentCatagoryBinding
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CatagoryFragment : Fragment() {

    lateinit var binding: FragmentCatagoryBinding
    private lateinit var adapter: CategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCatagoryBinding.inflate(inflater, container, false)
        val view = binding.root


        // RecyclerView 대신 LinearLayout 사용
        val linearLayout: LinearLayout = binding.linear
        //adapter = CategoryAdapter()


        binding.brandPlusIv.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.main_frm, BrandCategoryFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }

        binding.userPlusIv.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.main_frm, UserCategoryFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }

        binding.contentPlusIv.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.main_frm, ContentsCategoryFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }

        binding.avartarPlusIv.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.main_frm, AvatarStoreCategoryFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }

        lifecycleScope.launch {
            searchMain()
        }


        //return inflater.inflate(R.layout.fragment_catagory, container, false)
        return view

    }

    private fun getCurrentToken(context: Context): String?{
        val sharedPref = context.getSharedPreferences("Brandol", AppCompatActivity.MODE_PRIVATE)
        return sharedPref.getString("accessToken", null)
    }

    private fun searchMain() {
        val token = getCurrentToken(requireContext())

        val call = RetrofitObject.getRetrofitService.getSearchMain("Bearer $token")
        Log.d("search_brand", "good_1")
        call.enqueue(object : Callback<RetrofitClient2.ResponseSearchMain> {
            override fun onResponse(
                call: Call<RetrofitClient2.ResponseSearchMain>,
                response: Response<RetrofitClient2.ResponseSearchMain?>
            ) {
                Log.d("search_brand", "good_2")
                Log.d("search_brand", response.toString())
                if (response.isSuccessful) {
                    val searchData = response.body()?.result


                    // 브랜드
                    updateImage(searchData!!.searchMainBrandDto[0].brandProfileImage, binding.drandolLogo1Iv)
                    updateImage(searchData.searchMainBrandDto[1].brandProfileImage, binding.drandolLogo2Iv)
                    updateImage(searchData.searchMainBrandDto[2].brandProfileImage, binding.drandolLogo3Iv)

                    updateTextView(searchData.searchMainBrandDto[0].brandName, binding.brandName1Tv)
                    updateTextView(searchData.searchMainBrandDto[1].brandName, binding.brandName2Tv)
                    updateTextView(searchData.searchMainBrandDto[2].brandName, binding.brandName3Tv)

                    updateTextView(searchData.searchMainBrandDto[0].brandDescription, binding.brandName1DetailTv)
                    updateTextView(searchData.searchMainBrandDto[1].brandDescription, binding.brandName2DetailTv)
                    updateTextView(searchData.searchMainBrandDto[2].brandDescription, binding.brandName3DetailTv)

                    // 유저
                    updateImage(searchData.searchMainUserDto[0].userAvatar, binding.userChar1Iv)
                    updateImage(searchData.searchMainUserDto[1].userAvatar, binding.userChar2Iv)
                    updateImage(searchData.searchMainUserDto[2].userAvatar, binding.userChar3Iv)

                    updateTextView(searchData.searchMainUserDto[0].userName, binding.userChar1Tv)
                    updateTextView(searchData.searchMainUserDto[1].userName, binding.userChar2Tv)
                    updateTextView(searchData.searchMainUserDto[2].userName, binding.userChar3Tv)

                    // 콘텐츠
                    updateContentImage(searchData.searchMainContentsDto[0], binding.contentImageIv)
                    updateContentImage(searchData.searchMainContentsDto[1], binding.contentImage2Iv)
                    updateContentImage(searchData.searchMainContentsDto[2], binding.contentImage3Iv)

                    updateTextView(searchData.searchMainContentsDto[0].content, binding.content1ContentTv)
                    updateTextView(searchData.searchMainContentsDto[1].content, binding.content2ContentTv)
                    updateTextView(searchData.searchMainContentsDto[2].content, binding.content3ContentTv)

                    updateTextView(searchData.searchMainContentsDto[0].createdDate, binding.content1DateTv)
                    updateTextView(searchData.searchMainContentsDto[1].createdDate, binding.content2DateTv)
                    updateTextView(searchData.searchMainContentsDto[2].createdDate, binding.content3DateTv)

                    updateTextView(searchData.searchMainContentsDto[0].writerName, binding.content1NameTv)
                    updateTextView(searchData.searchMainContentsDto[1].writerName, binding.content2NameTv)
                    updateTextView(searchData.searchMainContentsDto[2].writerName, binding.content3NameTv)

                    updateTextViewLong(searchData.searchMainContentsDto[0].likeCount, binding.content1LikeCountTv)
                    updateTextViewLong(searchData.searchMainContentsDto[1].likeCount, binding.content2LikeCountTv)
                    updateTextViewLong(searchData.searchMainContentsDto[2].likeCount, binding.content3LikeCountTv)

                    updateTextViewLong(searchData.searchMainContentsDto[0].commentCount, binding.content1ChatCountTv)
                    updateTextViewLong(searchData.searchMainContentsDto[1].commentCount, binding.content2ChatCountTv)
                    updateTextViewLong(searchData.searchMainContentsDto[2].commentCount, binding.content3ChatCountTv)

                    updateImage(searchData.searchMainContentsDto[0].writerProfile, binding.miniCharIv)
                    updateImage(searchData.searchMainContentsDto[1].writerProfile, binding.miniChar2Iv)
                    updateImage(searchData.searchMainContentsDto[2].writerProfile, binding.miniChar3Iv)

                    updateTextView(searchData.searchMainContentsDto[0].createdDate, binding.content1DateTv)
                    updateTextView(searchData.searchMainContentsDto[1].createdDate, binding.content2DateTv)
                    updateTextView(searchData.searchMainContentsDto[2].createdDate, binding.content3DateTv)

                    updateTextView(searchData.searchMainContentsDto[0].contentsTitle, binding.content1TitleTv)
                    updateTextView(searchData.searchMainContentsDto[1].contentsTitle, binding.content2TitleTv)
                    updateTextView(searchData.searchMainContentsDto[2].contentsTitle, binding.content3TitleTv)

                    // 아바타스토어
                    updateImage(searchData.searchMainAvatarStoreDto[0].itemImage, binding.avartarStoreItem1Iv)
                    updateImage(searchData.searchMainAvatarStoreDto[1].itemImage, binding.avartarStoreItem2Iv)
                    updateImage(searchData.searchMainAvatarStoreDto[2].itemImage, binding.avartarStoreItem3Iv)

                    updateTextView(searchData.searchMainAvatarStoreDto[0].itemsName, binding.avartarStoreItem1NameTv)
                    updateTextView(searchData.searchMainAvatarStoreDto[1].itemsName, binding.avartarStoreItem2NameTv)
                    updateTextView(searchData.searchMainAvatarStoreDto[2].itemsName, binding.avartarStoreItem3NameTv)

                    updateTextView(searchData.searchMainAvatarStoreDto[0].itemPart, binding.avartarStoreItem1TypeTv)
                    updateTextView(searchData.searchMainAvatarStoreDto[1].itemPart, binding.avartarStoreItem2TypeTv)
                    updateTextView(searchData.searchMainAvatarStoreDto[2].itemPart, binding.avartarStoreItem3TypeTv)

                    //추후에 UI 수정 후 브랜드네임 추가 예정
                    //updateTextView(searchData.searchMainAvatarStoreDto[0].brandName, binding.avartarStoreItem1TypeTv)

                    updateTextView(searchData.searchMainAvatarStoreDto[0].itemDescription, binding.avartarStoreItem1ContentTv)
                    updateTextView(searchData.searchMainAvatarStoreDto[1].itemDescription, binding.avartarStoreItem2ContentTv)
                    updateTextView(searchData.searchMainAvatarStoreDto[2].itemDescription, binding.avartarStoreItem3ContentTv)

                }
            }

            override fun onFailure(
                call: Call<RetrofitClient2.ResponseSearchMain>,
                t: Throwable
            ) {
                val errorMessage = "Call Failed: ${t.message}"
                Log.e("sseohyeonn", errorMessage)
            }
        })
    }



    // 브랜드 로고 이미지뷰를 업데이트하는 함수
    private fun updateImage(brandProfileImage: String, imageView: ImageView) {
        Glide.with(requireContext()).load(brandProfileImage).into(imageView)
    }

    // 콘텐츠 이미지뷰를 업데이트하는 함수 (이미지가 있는 경우에만 업데이트)
    private fun updateContentImage(contentDto: RetrofitClient2.ContentsDto, imageView: ImageView) {
        if (contentDto.images.isNotEmpty()) {
            Glide.with(requireContext()).load(contentDto.images[0]).into(imageView)
        } else {
            // 이미지가 없을 경우 기본 이미지 또는 다른 처리를 수행할 수 있습니다.
            // 예를 들어, 기본 이미지를 설정하거나 숨김 처리를 할 수 있습니다.
            imageView.visibility = View.GONE
        }
    }

    // 텍스트뷰를 업데이트하는 함수
    private fun updateTextView(text: String?, textView: TextView) {
        if (!text.isNullOrBlank()) {
            textView.text = text
        } else {
            // 텍스트가 비어있는 경우 기본 텍스트 또는 다른 처리를 수행할 수 있습니다.
            // 예를 들어, 기본 텍스트를 설정하거나 숨김 처리를 할 수 있습니다.
            textView.visibility = View.GONE
        }
    }

    private fun updateTextViewLong(text: Long?, textView: TextView) {
            textView.text = text.toString()
    }



}


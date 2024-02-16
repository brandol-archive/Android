package com.example.brandol.brandInfo

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.brandol.board.BoardActivity
import com.example.brandol.R
import com.example.brandol.adaptor.CategoryAdapter
import com.example.brandol.connection.RetrofitAPI
import com.example.brandol.connection.RetrofitClient2
import com.example.brandol.databinding.FragmentBrandinfoBinding
import com.google.android.material.tabs.TabLayoutMediator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BrandInfoFragment: Fragment() {
    lateinit var binding: FragmentBrandinfoBinding
    private lateinit var retrofitAPI: RetrofitAPI // Declare RetrofitAPI instance

    @SuppressLint("ResourceAsColor")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBrandinfoBinding.inflate(inflater,container,false)

        initViewPager()

        // 브랜드 정보 불러오기
        loadBrandInfo(1) //추후 여기에 적절한 브랜드 아이디(brandId)를 전달해야 함

        binding.brandinfoLinkBtn.setOnClickListener {
            // LinktreeFragment로 전환
            val linktreeFragment = LinktreeFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.main_frm, linktreeFragment)  // android.R.id.content는 전체 화면을 나타냄
                .addToBackStack(null)
                .commit()
        }

        binding.brandinfoBrandlogoIv.setOnClickListener {
            val intent = Intent(context, BoardActivity::class.java)
            startActivity(intent)
        }

        //LinktreeFragment가 열린 상태에서 뒤로 가기 버튼을 누르면 이전 화면(BrandinfoFragment)으로 돌아오기
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // 프래그먼트 스택이 비어있지 않다면 뒤로 가기 동작 수행
                if (parentFragmentManager.backStackEntryCount > 0) {
                    parentFragmentManager.popBackStack()
                } else {
                    // 프래그먼트 스택이 비어있다면 앱 종료
                    requireActivity().finish()
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

        return binding.root
    }

    //브랜드 카테고리 생성
    private fun initViewPager() {
        //ViewPager2 Adapter setting
        var categoryAdapter = CategoryAdapter(requireActivity())
        categoryAdapter.addFragment(FandomFragment())
        categoryAdapter.addFragment(ContentsFragment())
        categoryAdapter.addFragment(CommuFragment())
        categoryAdapter.addFragment(MyFragment())

        //Adapter 연결
        binding.brandinfoCategoryVp.apply {
            adapter = categoryAdapter

            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                }
            })
        }

        //ViewPager, TabLayout 연결
        TabLayoutMediator(binding.brandinfoCategorynavTl, binding.brandinfoCategoryVp) { tab, position ->
            Log.e("YMC", "ViewPager position: ${position}")
            when (position) {
                0 -> tab.text = "FANDOM"
                1 -> tab.text = "컨텐츠"
                2 -> tab.text = "커뮤니티"
                3 -> tab.text = "MY"
            }
        }.attach()
    }

    // 브랜드 정보를 불러오는 Retrofit API 호출하는 함수 정의
    private fun loadBrandInfo(brandId: Long) {
        val call = retrofitAPI.brandheader(brandId)

        val intent = Intent(requireContext(), BoardActivity::class.java)
        intent.putExtra("brandId", brandId) // 데이터 넣기
        startActivityForResult(intent, 1001)

        // FandomFragment 생성 및 전달
        var fandomFragment = MyFragment()
        var bundleFandom = Bundle()
        bundleFandom.putLong("brandId", brandId) // Long 타입의 데이터를 넣음
        fandomFragment.arguments = bundleFandom
        activity?.supportFragmentManager!!.beginTransaction()
            .replace(R.id.fragment_container, fandomFragment)
            .commit()

        // ContentsFragment 생성 및 전달
        var contentsFragment = MyFragment()
        var bundleContents = Bundle()
        bundleContents.putLong("brandId", brandId) // Long 타입의 데이터를 넣음
        contentsFragment.arguments = bundleContents
        activity?.supportFragmentManager!!.beginTransaction()
            .replace(R.id.fragment_container, contentsFragment)
            .commit()

        // CommunityFragment 생성 및 전달
        var commuFragment = MyFragment()
        var bundleCommu = Bundle()
        bundleCommu.putLong("brandId", brandId) // Long 타입의 데이터를 넣음
        commuFragment.arguments = bundleCommu
        activity?.supportFragmentManager!!.beginTransaction()
            .replace(R.id.fragment_container, commuFragment)
            .commit()

        // MyFragment 생성 및 전달
        var myFragment = MyFragment()
        var bundleMy = Bundle()
        bundleMy.putLong("brandId", brandId) // Long 타입의 데이터를 넣음
        myFragment.arguments = bundleMy
        activity?.supportFragmentManager!!.beginTransaction()
            .replace(R.id.fragment_container, myFragment)
            .commit()

        call.enqueue(object : Callback<RetrofitClient2.BrandHeader> {
            override fun onResponse(
                call: Call<RetrofitClient2.BrandHeader>,
                response: Response<RetrofitClient2.BrandHeader>
            ) {
                if (response.isSuccessful) {
                    val brandHeader = response.body()
                    brandHeader?.let {
                        //백그라운드 사진 및 브랜드 로고 업데이트
                        Glide.with(requireContext())
                            .load(it.result.brandPreviewDto.brandBackground)
                            .centerCrop() // 이미지를 이미지뷰에 맞게 자르기
                            .into(binding.brandinfoBrandinfoIv)
                        Glide.with(requireContext())
                            .load(it.result.brandPreviewDto.brandProfile)
                            .centerCrop() // 이미지를 이미지뷰에 맞게 자르기
                            .into(binding.brandinfoBrandlogoIv)

                        //API에서 불러온 데이터로 화면 업데이트
                        binding.brandinfoFancntnumTv.text = it.result.brandPreviewDto.brandFan.toString()
                        binding.brandinfoBrandintroTv.text = it.result.brandPreviewDto.brandDescription
                        binding.brandinfoBrandnameTv.text = it.result.brandPreviewDto.brandName
                        //브랜드 추가하기 버튼 클릭 이벤트
                        binding.brandinfoAddlistBtn.setOnClickListener {
                            //list 추가 날짜 및 팬 번호 텍스트 변경 -> 추후에 날짜와 팬 순서 숫자 받아서 다시 해야 함
                            binding.brandinfoAddlistTv.text = "${brandHeader.result.brandUserStatus.joinDate}  |  ${brandHeader.result.brandUserStatus.fanSequence}번째 팬"
                            binding.brandinfoAddlistTv.setTextColor(requireContext().getColor(R.color.black))
                            binding.brandinfoAddlistBtn.setBackgroundResource(R.drawable.border_1dp)
                        }
                    }
                } else {
                    Log.e("BrandInfoFragment", "Failed to load brand info: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<RetrofitClient2.BrandHeader>, t: Throwable) {
                Log.e("BrandInfoFragment", "Failed to load brand info", t)
            }
        })
    }
}
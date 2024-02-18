//package com.example.brandol.Home
//
//import com.example.brandol.searchCategory.BrandCategoryFragment
//import com.example.brandol.adaptor.BrandButtonAdapter
//import android.graphics.Paint
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import androidx.recyclerview.widget.GridLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import androidx.viewpager2.widget.ViewPager2
//import com.example.brandol.ItemClickListener
//import com.example.brandol.R
//import com.example.brandol.brandInfo.BrandInfoFragment
//import com.example.brandol.databinding.FragmentHomeBinding
//
//
//class HomeFragment : Fragment() {
//
//    lateinit var binding: FragmentHomeBinding
//
//    private lateinit var mainBannerViewPager: ViewPager2
//    private lateinit var subBannerViewPager: ViewPager2
//    private lateinit var mainBannerAdapter: BannerAdapter
//    private lateinit var subBannerAdapter: BannerAdapter
//
//    private lateinit var recyclerView: RecyclerView
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = FragmentHomeBinding.inflate(inflater, container, false)
//        val view = binding.root
//
//
//        // 메인 배너 뷰페이저 초기화
//        mainBannerViewPager = view.findViewById(R.id.viewPager_main)
//        mainBannerAdapter = BannerAdapter()
//        mainBannerViewPager.adapter = mainBannerAdapter
//
//        // 메인 배너 아이템 추가
//        mainBannerAdapter.addBannerItem(R.drawable.img_main_banner)
//        mainBannerAdapter.addBannerItem(R.drawable.img_main_banner)
//
//        // 서브 배너 뷰페이저 초기화
//        subBannerViewPager = view.findViewById(R.id.viewPager_sub)
//        subBannerAdapter = BannerAdapter()
//        subBannerViewPager.adapter = subBannerAdapter
//
//        // 서브 배너 아이템 추가
//        subBannerAdapter.addBannerItem(R.drawable.img_sub_banner)
//        subBannerAdapter.addBannerItem(R.drawable.img_sub_banner)
//
//        recyclerView = view.findViewById(R.id.recyclerView)
//
//        // 리사이클러뷰 초기화 및 어댑터 설정
//        val adapter = BrandButtonAdapter()
//        recyclerView.adapter = adapter
//        recyclerView.layoutManager = GridLayoutManager(requireContext(), 1) // 1개의 열로 구성
//        recyclerView.setHasFixedSize(true) // 옵션: 아이템 크기 고정
//
//        // 더미 데이터로 뷰 홀더의 항목들을 설정
//        val dummyDataList = listOf(
//            DummyData("BRANDOL", "브랜드 팬덤 커뮤니티", "6번째 팬", R.drawable.img_brandol),
//            DummyData("빙그레", "행복한 맛!", "2번째 팬", R.drawable.img_binggrae),
//            DummyData("배달의민족", "대한민국 1등 배달앱", "168번째 팬", R.drawable.img_baemin)
//        )
//
//
//        //브랜드 클릭해서 brandInfoFragment로 화면 전환
////        adapter.itemClickListener = object : ItemClickListener {
////            override fun onItemClick(position: Int) {
////                //클릭한 아이템 정보를 가져와서 넘겨줌
////                val brand = dummyDataList[position]
////                parentFragmentManager.beginTransaction()
////                    .replace(R.id.main_frm, BrandInfoFragment())
////                    .addToBackStack(null)
////                    .commit()
////            }
////        }
//        // 브랜드 클릭해서 brandInfoFragment로 화면 전환
//        adapter.itemClickListener = object : ItemClickListener {
//            override fun onItemClick(position: Int) {
//                if (position in 0 until dummyDataList.size) {
//                    // 유효한 인덱스 범위 내에서 클릭한 아이템 정보를 가져와서 넘겨줌
//                    val brand = dummyDataList[position]
//                    parentFragmentManager.beginTransaction()
//                        .replace(R.id.main_frm, BrandInfoFragment())
//                        .addToBackStack(null)
//                        .commit()
//                } else {
//                    // 인덱스가 유효하지 않을 때 다른 프래그먼트로 이동
//                    val brandCategoryFragment = BrandCategoryFragment()
//                    parentFragmentManager.beginTransaction()
//                        .replace(R.id.main_frm, brandCategoryFragment)
//                        .addToBackStack(null)
//                        .commit()
//                }
//            }
//        }
//
//
//
//        dummyDataList.forEach { dummyData ->
//            adapter.addButton(dummyData)
//        }
//
//
//
//        binding.manageTv.setOnClickListener {
//            // 브랜드 관리 프래그먼트로 전환
//            val brandManagementFragment = BrandManagementFragment()
//            val transaction = requireActivity().supportFragmentManager.beginTransaction()
//            transaction.replace(R.id.main_frm, brandManagementFragment)
//            transaction.addToBackStack(null)
//            transaction.commit()
//        }
//
//
//
//        binding.manageTv.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
//
//        return view
//    }
//
//    data class DummyData(
//        val brandName: String,
//        val brandInfo: String,
//        val fanCount: String,
//        val brandImageResourceId: Int // 브랜드 이미지 리소스 ID
//    )
//
//    // ViewPager2에 사용될 어댑터
//    inner class BannerAdapter : RecyclerView.Adapter<BannerAdapter.BannerViewHolder>() {
//
//        private val bannerItems = mutableListOf<Int>()
//
//        fun addBannerItem(bannerItem: Int) {
//            bannerItems.add(bannerItem)
//            notifyDataSetChanged()
//        }
//
//        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
//            val view = LayoutInflater.from(parent.context)
//                .inflate(R.layout.fragment_home, parent, false)
//            return BannerViewHolder(view)
//        }
//
//        override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
//            val bannerItem = bannerItems[position]
//            holder.bind(bannerItem)
//        }
//
//        override fun getItemCount(): Int {
//            return bannerItems.size
//        }
//
//        inner class BannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//            fun bind(bannerItem: Int) {
//                // 원하는 작업 수행 (예: 이미지 로딩 등)
//                itemView.setBackgroundResource(bannerItem)
//            }
//        }
//    }
//
//}






package com.example.brandol.Home

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.example.brandol.adaptor.BrandButtonAdapter
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.brandol.ItemClickListener
import com.example.brandol.R
import com.example.brandol.brandInfo.BrandInfoFragment
import com.example.brandol.connection.RetrofitClient2
import com.example.brandol.connection.RetrofitObject
import com.example.brandol.databinding.FragmentHomeBinding
import com.example.brandol.searchCategory.BrandCategoryFragment
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding

    private lateinit var mainBannerViewPager: ViewPager2
    private lateinit var subBannerViewPager: ViewPager2
    private lateinit var mainBannerAdapter: BannerAdapter
    private lateinit var subBannerAdapter: BannerAdapter

    private lateinit var recyclerView: RecyclerView

    private lateinit var brandButtonAdapter: BrandButtonAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        // 메인 배너 뷰페이저 초기화
        mainBannerViewPager = view.findViewById(R.id.viewPager_main)
        mainBannerAdapter = BannerAdapter()
        mainBannerViewPager.adapter = mainBannerAdapter

        // 메인 배너 아이템 추가
        //mainBannerAdapter.addBannerItem(R.drawable.img_main_banner)
        //mainBannerAdapter.addBannerItem(R.drawable.img_main_banner)

        // 서브 배너 뷰페이저 초기화
        subBannerViewPager = view.findViewById(R.id.viewPager_sub)
        subBannerAdapter = BannerAdapter()
        subBannerViewPager.adapter = subBannerAdapter

        // 서브 배너 아이템 추가
        //subBannerAdapter.addBannerItem(R.drawable.img_sub_banner)
        //subBannerAdapter.addBannerItem(R.drawable.img_sub_banner)

        recyclerView = view.findViewById(R.id.recyclerView)

        // 리사이클러뷰 초기화 및 어댑터 설정
        brandButtonAdapter = BrandButtonAdapter()
        recyclerView.adapter = brandButtonAdapter
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 1)
        recyclerView.setHasFixedSize(true)



        //브랜드 클릭해서 brandInfoFragment로 화면 전환
        brandButtonAdapter.itemClickListener = object : ItemClickListener {
            override fun onItemClick(position: Int) {
                //클릭한 아이템 정보를 가져와서 넘겨줌
                //val brand = dummyDataList[position]
                parentFragmentManager.beginTransaction()
                    .replace(R.id.main_frm, BrandInfoFragment())
                    .addToBackStack(null)
                    .commit()
            }
        }


        binding.brandPlusButton.setOnClickListener {
            // 브랜드 관리 프래그먼트로 전환
            val brandCategoryFragment = BrandCategoryFragment()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.main_frm, brandCategoryFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }


        binding.manageTv.setOnClickListener {
            // 브랜드 관리 프래그먼트로 전환
            val brandManagementFragment = BrandManagementFragment()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.main_frm, brandManagementFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        // 데이터 가져오기 및 화면 갱신
        lifecycleScope.launch {
            getHomeFragmentData()
        }

        binding.manageTv.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);

        return view
    }

    private fun getCurrentToken(context: Context): String? {
        val sharedPref = context.getSharedPreferences("Brandol", MODE_PRIVATE)
        return sharedPref.getString("accessToken", null)
    }

    private fun getHomeFragmentData() {
        val token = getCurrentToken(requireContext())
        val call = RetrofitObject.getRetrofitService.getHomeFragment("Bearer $token")
        Log.d("ikj", "good_1")
        call.enqueue(object : Callback<RetrofitClient2.GetHomeFragment> {
            override fun onResponse(
                call: Call<RetrofitClient2.GetHomeFragment>,
                response: Response<RetrofitClient2.GetHomeFragment>
            ) {
                Log.d("ikj", "good_2")
                Log.d("ikj", response.toString())
                if (response.isSuccessful) {
                    val response = response.body()
                    Log.d("ikj", response.toString())
                    if (response != null) {
                        if (response.isSuccess) {
                            Log.d("ikj", response.result.toString())
                            val responseData = response.result

                            // Update main banner data
                            responseData?.mainBannersDtoList?.let { mainBannerAdapter.setMainBannerData(it.map { it.brandBackgroundImage }) }

                            // Update sub banner data
                            responseData?.subBannersDtoList?.let { subBannerAdapter.setSubBannerData(it.flatMap { it.images }) }


                            // Check if brand list is empty
                            if (responseData?.memberBrandListDtoList.isNullOrEmpty()) {
                                brandButtonAdapter.setEmptyBrandList()
                            } else {
                                // Update brand button data
                                responseData?.memberBrandListDtoList?.let { brandButtonAdapter.setBrandButtonData(it) }
                            }
                        }
                    }
                }
            }

            override fun onFailure(call: Call<RetrofitClient2.GetHomeFragment>, t: Throwable) {
                val errorMessage = "Call Failed: ${t.message}"
                Log.d("ikj", errorMessage)
            }
        })
    }


    // ViewPager2에 사용될 어댑터
    // ViewPager2에 사용될 어댑터
    inner class BannerAdapter : RecyclerView.Adapter<BannerAdapter.BannerViewHolder>() {

        private val bannerItems = mutableListOf<String>()

        // 메인 배너 데이터를 설정하는 함수
        fun setMainBannerData(images: List<String>) {
            bannerItems.clear()
            bannerItems.addAll(images)
            notifyDataSetChanged()
        }


        fun setSubBannerData(images: List<String>) {
            bannerItems.clear()
            bannerItems.addAll(images)
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_banner, parent, false)
            return BannerViewHolder(view)
        }

        override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
            val bannerItem = bannerItems[position]
            holder.bind(bannerItem)
        }

        override fun getItemCount(): Int {
            return bannerItems.size
        }

        inner class BannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val bannerImageView: ImageView = itemView.findViewById(R.id.bannerImageView)

            fun bind(bannerItem: String) {
                Glide.with(itemView.context).load(bannerItem).into(bannerImageView)
            }
        }
    }



}




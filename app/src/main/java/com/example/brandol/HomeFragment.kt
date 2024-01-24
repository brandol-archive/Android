package com.example.brandol

import BrandButtonAdapter
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.brandol.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding

    private lateinit var mainBannerViewPager: ViewPager2
    private lateinit var subBannerViewPager: ViewPager2
    private lateinit var mainBannerAdapter: BannerAdapter
    private lateinit var subBannerAdapter: BannerAdapter

    private lateinit var recyclerView: RecyclerView

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
        mainBannerAdapter.addBannerItem(R.drawable.img_main_banner)
        mainBannerAdapter.addBannerItem(R.drawable.img_main_banner)

        // 서브 배너 뷰페이저 초기화
        subBannerViewPager = view.findViewById(R.id.viewPager_sub)
        subBannerAdapter = BannerAdapter()
        subBannerViewPager.adapter = subBannerAdapter

        // 서브 배너 아이템 추가
        subBannerAdapter.addBannerItem(R.drawable.img_sub_banner)
        subBannerAdapter.addBannerItem(R.drawable.img_sub_banner)

        recyclerView = view.findViewById(R.id.recyclerView)

        // 리사이클러뷰 초기화 및 어댑터 설정
        val adapter = BrandButtonAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 1) // 1개의 열로 구성
        recyclerView.setHasFixedSize(true) // 옵션: 아이템 크기 고정

        // 더미 데이터로 뷰 홀더의 항목들을 설정
        val dummyDataList = listOf(
            DummyData("BRANDOL", "브랜드 팬덤 커뮤니티", "6번째 팬", R.drawable.img_brandol),
            DummyData("빙그레", "행복한 맛!", "2번째 팬", R.drawable.img_binggrae),
            DummyData("배달의민족", "대한민국 1등 배달앱", "168번째 팬", R.drawable.img_baemin)
        )

        dummyDataList.forEach { dummyData ->
            adapter.addButton(dummyData)
        }


        binding.manageTv.setOnClickListener {
            startActivity(Intent(activity, BrandManagementActivity::class.java))
        }

        binding.brandListTv.setOnClickListener {
            startActivity(Intent(activity, BrandCategoryActivity::class.java))
        }

        return view
    }

    data class DummyData(
        val brandName: String,
        val brandInfo: String,
        val fanCount: String,
        val brandImageResourceId: Int // 브랜드 이미지 리소스 ID
    )

    // ViewPager2에 사용될 어댑터
    inner class BannerAdapter : RecyclerView.Adapter<BannerAdapter.BannerViewHolder>() {

        private val bannerItems = mutableListOf<Int>()

        fun addBannerItem(bannerItem: Int) {
            bannerItems.add(bannerItem)
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_home, parent, false)
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
            fun bind(bannerItem: Int) {
                // 원하는 작업 수행 (예: 이미지 로딩 등)
                itemView.setBackgroundResource(bannerItem)
            }
        }
    }

}




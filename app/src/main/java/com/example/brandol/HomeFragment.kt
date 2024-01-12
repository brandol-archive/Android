package com.example.brandol

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

class HomeFragment : Fragment() {

    private lateinit var mainBannerViewPager: ViewPager2
    private lateinit var subBannerViewPager: ViewPager2
    private lateinit var mainBannerAdapter: BannerAdapter
    private lateinit var subBannerAdapter: BannerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // 메인 배너 뷰페이저 초기화
        mainBannerViewPager = view.findViewById(R.id.viewPager_main)
        mainBannerAdapter = BannerAdapter()
        mainBannerViewPager.adapter = mainBannerAdapter

        // 메인 배너 아이템 추가
        mainBannerAdapter.addBannerItem(R.drawable.ic_launcher_foreground)
        mainBannerAdapter.addBannerItem(R.drawable.ic_launcher_background)

        // 서브 배너 뷰페이저 초기화
        subBannerViewPager = view.findViewById(R.id.viewPager_sub)
        subBannerAdapter = BannerAdapter()
        subBannerViewPager.adapter = subBannerAdapter

        // 서브 배너 아이템 추가
        subBannerAdapter.addBannerItem(R.drawable.ic_launcher_foreground)
        subBannerAdapter.addBannerItem(R.drawable.ic_launcher_background)

        return view
    }

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

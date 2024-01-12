package com.example.brandol

import ButtonAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

class HomeFragment : Fragment() {

    private lateinit var mainBannerViewPager: ViewPager2
    private lateinit var subBannerViewPager: ViewPager2
    private lateinit var mainBannerAdapter: BannerAdapter
    private lateinit var subBannerAdapter: BannerAdapter

    private lateinit var brandListTextView: TextView
    private lateinit var manageButton: Button
    private lateinit var addButton: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var gridLayoutButtons: GridLayout

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


        brandListTextView = view.findViewById(R.id.brandList_tv)
        manageButton = view.findViewById(R.id.manage_btn)
        //addButton = view.findViewById(R.id.btnAdd)
        recyclerView = view.findViewById(R.id.recyclerView)
        //gridLayoutButtons = view.findViewById(R.id.gridLayoutButtons)

        // 리사이클러뷰 초기화 및 어댑터 설정
        val adapter = ButtonAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 3) // 3개의 열로 구성
        recyclerView.setHasFixedSize(true) // 옵션: 아이템 크기 고정

        // 버튼 아이템 추가
        adapter.addButton("Brand 1")
        adapter.addButton("Brand 2")
        adapter.addButton("Brand 3")
        adapter.addButton("Brand 4")
        adapter.addButton("Brand 5")
        adapter.addButton("Brand 6")
        adapter.addButton("Brand 7")
        adapter.addButton("Brand 8")


//        val manageButton = view.findViewById<Button>(R.id.btnManage)
//        manageButton.setOnClickListener {
//            findNavController().navigate(R.id.action_homeFragment_to_anotherFragment)
//        }


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

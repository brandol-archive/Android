package com.example.brandol

import com.example.brandol.adaptor.BrandListAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.brandol.collection.BrandData
import com.example.brandol.databinding.FragmentBrandManagementBinding


// BrandManagementFragment.kt


class BrandManagementFragment : Fragment() {
    private lateinit var binding: FragmentBrandManagementBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var brandListAdapter: BrandListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBrandManagementBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 리사이클러뷰 초기화 및 레이아웃 매니저 설정
        recyclerView = binding.rvBrandList
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // 더미 데이터 생성
        val brandDataList = listOf(
            BrandData("BRANDOL", "브랜드 팬덤 커뮤니티", R.drawable.img_brandol),
            BrandData("빙그레", "행복한 맛!", R.drawable.img_binggrae),
            BrandData("배달의민족", "대한민국 1등 배달앱", R.drawable.img_baemin),
        )

        // 어댑터 초기화 및 리사이클러뷰에 설정
        brandListAdapter = BrandListAdapter(mutableListOf())
        recyclerView.adapter = brandListAdapter


        // move_iv 클릭 동작 설정
        binding.btnBackBrandManagement.setOnClickListener {
            // 이전 화면으로 돌아가기
            requireActivity().supportFragmentManager.popBackStack()
        }

        // ItemTouchHelper를 RecyclerView에 연결
        brandListAdapter.attachItemTouchHelper(recyclerView)

        // 어댑터에 더미 데이터 설정
        brandListAdapter.setBrandList(brandDataList)
    }
}


package com.example.brandol

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.brandol.databinding.FragmentOpBrandListBinding


class OpBrandListFragment : Fragment() {

    lateinit var binding: FragmentOpBrandListBinding
    private var opBrandList = ArrayList<BrandData>()
    private var brandAdapter = OpBrandRVAdpater(opBrandList)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOpBrandListBinding.inflate(layoutInflater,container,false)
        opBrandList.apply {
            add(BrandData("브랜돌","브랜돌 팬덤 커뮤니티",R.drawable.brandol))
            add(BrandData("브랜돌","브랜돌 팬덤 커뮤니티",R.drawable.brandol))
            add(BrandData("브랜돌","브랜돌 팬덤 커뮤니티",R.drawable.brandol))
            add(BrandData("브랜돌","브랜돌 팬덤 커뮤니티",R.drawable.brandol))
            add(BrandData("브랜돌","브랜돌 팬덤 커뮤니티",R.drawable.brandol))
        }
        binding.brandListRv.adapter =  brandAdapter
        binding.brandListRv.layoutManager = LinearLayoutManager(activity)
        return binding.root
    }

}
package com.example.brandol

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.brandol.databinding.FragmentBrandListBinding


class BrandListFragment : Fragment() {

    lateinit var binding: FragmentBrandListBinding
    private var opBrandList = ArrayList<OpBrand>()
    private var brandAdapter = BrandRVAdpater(opBrandList)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBrandListBinding.inflate(layoutInflater,container,false)
        opBrandList.apply {
            add(OpBrand("브랜돌",R.drawable.brandol,"브랜돌 팬덤 커뮤니티"))
            add(OpBrand("브랜돌",R.drawable.brandol,"브랜돌 팬덤 커뮤니티"))
            add(OpBrand("브랜돌",R.drawable.brandol,"브랜돌 팬덤 커뮤니티"))
            add(OpBrand("브랜돌",R.drawable.brandol,"브랜돌 팬덤 커뮤니티"))
            add(OpBrand("브랜돌",R.drawable.brandol,"브랜돌 팬덤 커뮤니티"))
        }
        binding.brandListRv.adapter =  brandAdapter
        binding.brandListRv.layoutManager = LinearLayoutManager(activity)
        return binding.root
    }

}
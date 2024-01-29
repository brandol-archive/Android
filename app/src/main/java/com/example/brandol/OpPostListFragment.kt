package com.example.brandol

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.brandol.databinding.FragmentOpPostListBinding

class OpPostListFragment : Fragment() {

    lateinit var binding: FragmentOpPostListBinding
    private var opPostList = ArrayList<OpPost>()
    private var postAdapter = OpPostRVAdapter(opPostList)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOpPostListBinding.inflate(inflater,container,false)
        opPostList.apply {
            add(OpPost(R.drawable.demo_avartar,"작성자","게시물 이름","브랜드 추구 방향성, 문화 \n" +
                    "최근 게시글이 들어감글글글글글"))
            add(OpPost(R.drawable.demo_avartar2,"작성자","게시물 이름","브랜드 추구 방향성, 문화 \n" +
                    "최근 게시글이 들어감글글글글글"))
            add(OpPost(R.drawable.demo_avartar3,"작성자","게시물 이름","브랜드 추구 방향성, 문화 \n" +
                    "최근 게시글이 들어감글글글글글"))
            add(OpPost(R.drawable.demo_avartar4,"작성자","게시물 이름","브랜드 추구 방향성, 문화 \n" +
                    "최근 게시글이 들어감글글글글글"))
        }
        binding.postListRv.adapter = postAdapter
        binding.postListRv.layoutManager = LinearLayoutManager(activity)

        // Inflate the layout for this fragment
        return binding.root
    }

}
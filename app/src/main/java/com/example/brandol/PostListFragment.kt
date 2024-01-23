package com.example.brandol

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.brandol.databinding.FragmentPostListBinding

class PostListFragment : Fragment() {

    lateinit var binding: FragmentPostListBinding
    private var opPostList = ArrayList<OpPost>()
    private var postAdapter = PostRVAdapter(opPostList)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPostListBinding.inflate(inflater,container,false)
        opPostList.apply {
            add(OpPost("게시물 이름","게시물 내용","게시판 이름"))
            add(OpPost("게시물 이름","게시물 내용","게시판 이름"))
            add(OpPost("게시물 이름","게시물 내용","게시판 이름"))
            add(OpPost("게시물 이름","게시물 내용","게시판 이름"))
            add(OpPost("게시물 이름","게시물 내용","게시판 이름"))
            add(OpPost("게시물 이름","게시물 내용","게시판 이름"))
            add(OpPost("게시물 이름","게시물 내용","게시판 이름"))
        }
        binding.postListRv.adapter = postAdapter
        binding.postListRv.layoutManager = LinearLayoutManager(activity)

        // Inflate the layout for this fragment
        return binding.root
    }

}
package com.example.brandol.mypage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.brandol.R
import com.example.brandol.databinding.FragmentMypageBlacklistBinding

class MypageBlacklistFragment : Fragment() {
    lateinit var binding: FragmentMypageBlacklistBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMypageBlacklistBinding.inflate(inflater,container,false)
        binding.mypageBlacklistBackBtn.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.main_frm, MypageFragment())
                .addToBackStack(null)
                .commit()
        }
        return binding.root
    }

}
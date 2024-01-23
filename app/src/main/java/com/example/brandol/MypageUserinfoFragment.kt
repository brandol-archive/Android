package com.example.brandol

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.brandol.databinding.FragmentMypageUserinfoBinding

class MypageUserinfoFragment : Fragment() {
    lateinit var binding: FragmentMypageUserinfoBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMypageUserinfoBinding.inflate(inflater,container,false)
        binding.messageBackBtn.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.main_frm,MypageFragment())
                .addToBackStack(null)
                .commit()
        }
        return binding.root
    }

}
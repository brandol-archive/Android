package com.example.brandol

import CustomDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.brandol.databinding.FragmentMypageBinding

class MypageFragment : Fragment() {
    lateinit var binding: FragmentMypageBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMypageBinding.inflate(inflater,container,false)


        binding.mypageUserinfoTv.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.main_frm,MypageUserinfoFragment())
                .addToBackStack(null)
                .commit()
        }

        binding.mypagePushAlarmTv.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.main_frm,MypagePushalarmFragment())
                .addToBackStack(null)
                .commit()
        }

        binding.mypageBlacklistTv.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.main_frm,MypageBlacklistFragment())
                .addToBackStack(null)
                .commit()
        }

        binding.mypageTermuseTv.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.main_frm,MypageTermuseFragment())
                .addToBackStack(null)
                .commit()
        }
        binding.mypageLogoutTv.setOnClickListener {
            val dialog = CustomDialog(
                binding.mypageLogoutTv.context,
                "로그아웃 하시겠습니까?",
                {
                    // 확인 버튼 클릭 시 동작
                    activity?.finish()
                },
                {
                    // 취소 버튼 클릭 시 동작
                }
            )
            dialog.show()
        }
        return binding.root
    }

}
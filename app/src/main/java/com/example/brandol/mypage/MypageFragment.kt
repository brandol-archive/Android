package com.example.brandol.mypage

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.brandol.R
import com.kakao.sdk.user.UserApiClient
import com.example.brandol.databinding.FragmentMypageBinding
import com.example.brandol.dialog.CustomAccountDialog
import com.example.brandol.dialog.CustomLogoutDialog
import com.example.brandol.login.LoginStartActivity

class MypageFragment : Fragment() {
    lateinit var binding: FragmentMypageBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMypageBinding.inflate(inflater, container, false)

        setimage()

        binding.mypageUserinfoTv.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.main_frm, MypageUserinfoFragment())
                .addToBackStack(null)
                .commit()
        }

        binding.mypagePushAlarmTv.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.main_frm, MypagePushalarmFragment())
                .addToBackStack(null)
                .commit()
        }

        binding.mypageBlacklistTv.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.main_frm, MypageBlacklistFragment())
                .addToBackStack(null)
                .commit()
        }

        binding.mypageTermuseTv.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.main_frm, MypageTermuseFragment())
                .addToBackStack(null)
                .commit()
        }
        binding.mypageLogoutTv.setOnClickListener {
            val context = context
            val dialog = CustomLogoutDialog(
                context!!, "로그아웃 하시겠습니까?",
                {
                    UserApiClient.instance.logout { error ->
                        if (error != null) {
                            Log.d("카카오", "카카오 로그아웃 실패")
                        } else {
                            Log.d("카카오", "카카오 로그아웃 성공!")

                            // 로그아웃 성공 시 LoginActivity로 이동
                            val intent = Intent(context, LoginStartActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            startActivity(intent)
                            activity?.finish()
                        }
                    }
                    activity?.finish()
                },
                {
                    // 취소 버튼 클릭 시 동작

                }
            )
            dialog.show()
        }

        binding.mypageAccountDeleteTv.setOnClickListener {
            val context = context
            val dialog = CustomAccountDialog(
                context!!, "정말로 계정을 탈퇴하시겠습니까?\n" +
                        "탈퇴를 계속하기 원하신다면\n" +
                        "닉네임 입력 후 확인을 눌러주세요.",
                {
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

    private fun setimage() {
        parentFragmentManager.setFragmentResultListener("avatarImage",
            viewLifecycleOwner
        ) { key, bundle ->
            val uriString = bundle.getString("bundlekey")
            val uri = Uri.parse(uriString)
            binding.mypageProfileIv.setImageURI(uri)
        }
    }
}
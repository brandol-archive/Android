//package com.example.brandol.brandInfo
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import com.example.brandol.connection.RetrofitAPI
//import com.example.brandol.connection.RetrofitObject
//import com.example.brandol.databinding.FragmentLinktreeBinding
//
//class LinktreeFragment : Fragment() {


package com.example.brandol.brandInfo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.brandol.connection.RetrofitAPI
import com.example.brandol.connection.RetrofitObject
import com.example.brandol.databinding.FragmentLinktreeBinding

class LinktreeFragment : Fragment() {

    lateinit var binding: FragmentLinktreeBinding
    private lateinit var retrofitAPI: RetrofitAPI // Declare RetrofitAPI instance


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLinktreeBinding.inflate(inflater, container, false)

        // RetrofitAPI 초기화
        retrofitAPI = RetrofitObject.getRetrofitService

        //뒤로 가기
        binding.linktreeBackBtn.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        // brandId 가져오기
        val brandId = arguments?.getInt("brandId")

        // brandId에 따라 링크트리 설정
        when (brandId) {
            1 -> {
                // BRANDOL 링크트리
                // sns
                binding.linktreeSns1Btn.text = "https://www.instagram.com/brandol__official?utm_source=ig_web_button_share_sheet&igsh=ZDNlZDc0MzIxNw=="
                binding.linktreeSns1Btn.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(binding.linktreeSns1Btn.text.toString()))
                    startActivity(intent)
                }
            }
            2 -> {
                // 119REO 링크트리
                // 홈페이지
                binding.linktreeHomepageBtn.text = "https://www.119reo.com/"
                binding.linktreeHomepageBtn.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(binding.linktreeHomepageBtn.text.toString()))
                    startActivity(intent)
                }

                // sns
                binding.linktreeSns1Btn.text = "https://www.instagram.com/119reo?utm_source=ig_web_button_share_sheet&igsh=ZDNlZDc0MzIxNw=="
                binding.linktreeSns1Btn.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(binding.linktreeSns1Btn.text.toString()))
                    startActivity(intent)
                }

                // 스토어
                binding.linktreeStoreBtn.text = "https://www.119reo.com/25"
                binding.linktreeStoreBtn.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(binding.linktreeStoreBtn.text.toString()))
                    startActivity(intent)
                }
            }
            else -> {
                // 기본 동작은 여기에 추가
                // 119REO 링크트리
                // 홈페이지
                binding.linktreeHomepageBtn.text = "https://www.119reo.com/"
                binding.linktreeHomepageBtn.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(binding.linktreeHomepageBtn.text.toString()))
                    startActivity(intent)
                }

                // sns
                binding.linktreeSns1Btn.text = "https://www.instagram.com/119reo?utm_source=ig_web_button_share_sheet&igsh=ZDNlZDc0MzIxNw=="
                binding.linktreeSns1Btn.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(binding.linktreeSns1Btn.text.toString()))
                    startActivity(intent)
                }

                // 스토어
                binding.linktreeStoreBtn.text = "https://www.119reo.com/25"
                binding.linktreeStoreBtn.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(binding.linktreeStoreBtn.text.toString()))
                    startActivity(intent)
                }
            }
        }

        return binding.root
    }
}
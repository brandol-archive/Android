package com.example.brandol

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.example.brandol.databinding.FragmentAvatarBinding
import com.google.android.material.tabs.TabLayoutMediator

class AvatarFragment : Fragment() {
    lateinit var binding: FragmentAvatarBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAvatarBinding.inflate(inflater, container, false)
        //저장하기 버튼 로직
        saveButtonLogic()
        //짧은 다이얼로그 보여주기
        showShortDialog()
        //아이템리스트 보여주기
        showtablayout()
        //메시지 화면으로 전환
        goMessage()
        //채팅 온 갯수 앞으로 보내기
        bringFront()
        //아바타에 옷힙히기 로직
        wearitem()

        return binding.root
    }

    private fun wearitem() {
        childFragmentManager.setFragmentResultListener(
            "requestKey",
            viewLifecycleOwner
        ) { key, bundle ->
            val itemName = bundle.getString("bundleKey")
            when (itemName) {
                "상의" -> binding.avatarBaseAvatarShirts.setImageResource(R.drawable.no1_item_shirts)
                "하의" -> binding.avatarBaseAvatarPants.setImageResource(R.drawable.no1_item_pants)
                "신발" -> binding.avatarBaseAvatarShoes.setImageResource(R.drawable.no1_item_shoes)
                "헤어" -> binding.avatarBaseAvatarHair.setImageResource(R.drawable.no1_item_woman_hair)
                "피부1" -> binding.avatarBaseAvatarSkin.setImageResource(R.drawable.no1_item_skin)
                "피부2" -> binding.avatarBaseAvatarSkin.setImageResource(R.drawable.no2_item_skin)
                "피부3" -> binding.avatarBaseAvatarSkin.setImageResource(R.drawable.no3_item_skin)
                "피부4" -> binding.avatarBaseAvatarSkin.setImageResource(R.drawable.no4_item_skin)
                "피부5" -> binding.avatarBaseAvatarSkin.setImageResource(R.drawable.no5_item_skin)
            }
        }
    }

    private fun saveButtonLogic() {
        binding.avatarSaveBtn.setOnClickListener {
            val dialog = CustomSaveDialog(binding.avatarChattingQuantityTv.context)
            dialog.show()
        }
    }

    private fun bringFront() {
        binding.avatarChattingQuantity.bringToFront()
    }

    private fun goMessage() {
        binding.avatarChattingBtn.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.main_frm, MessageFragment())
                .addToBackStack(null)
                .setReorderingAllowed(true)
                .commit()
        }
    }

    private fun showShortDialog() {
        val dialog = CustomAnnonceInfoDialog(binding.avatarChattingQuantityTv.context)
        dialog.setContentView(R.layout.dialog_announce_info)

        val params = dialog.window?.attributes
        params?.gravity = Gravity.TOP or Gravity.LEFT // 원하는 위치로 변경합니다.
        params?.x = 100 // x 위치 조정
        params?.y = 450 // y 위치 조정
        dialog.window?.setAttributes(params);
        dialog.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        dialog.show()
    }


    private fun showtablayout() {
        val tabElement = arrayOf("전체", "피부", "헤어", "한벌", "상의", "하의", "신발", "기타")

        //뷰페이저 어댑터 연결
        val pagerAdapter = AvatarVPAdapter(childFragmentManager,lifecycle)
        binding.avatarItemlistVp.adapter = pagerAdapter

        //탭 레이아웃과 뷰페이저 연동
        TabLayoutMediator(binding.avatarCategoryTl, binding.avatarItemlistVp) { tab, position ->
            // 탭의 텍스트 설정
            tab.text = tabElement[position]
        }.attach()
    }


}
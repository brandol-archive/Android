package com.example.brandol

import android.app.AlertDialog
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.example.brandol.databinding.FragmentAvartarBinding
import com.google.android.material.tabs.TabLayoutMediator

class AvartarFragment : Fragment() {
    lateinit var binding: FragmentAvartarBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAvartarBinding.inflate(inflater, container, false)
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
                "상의" -> binding.avartarBaseAvartarShirts.setImageResource(R.drawable.no1_item_shirts)
                "하의" -> binding.avartarBaseAvartarPants.setImageResource(R.drawable.no1_item_pants)
                "신발" -> binding.avartarBaseAvartarShoes.setImageResource(R.drawable.no1_item_shoes)
                "헤어" -> binding.avartarBaseAvartarHair.setImageResource(R.drawable.no1_item_woman_hair)
                "피부1" -> binding.avartarBaseAvartarSkin.setImageResource(R.drawable.no1_item_skin)
                "피부2" -> binding.avartarBaseAvartarSkin.setImageResource(R.drawable.no2_item_skin)
                "피부3" -> binding.avartarBaseAvartarSkin.setImageResource(R.drawable.no3_item_skin)
                "피부4" -> binding.avartarBaseAvartarSkin.setImageResource(R.drawable.no4_item_skin)
                "피부5" -> binding.avartarBaseAvartarSkin.setImageResource(R.drawable.no5_item_skin)
            }
        }
    }

    private fun saveButtonLogic() {
        binding.avartarSaveBtn.setOnClickListener {
            val dialog = CustomSaveDialog(binding.avartarChattingQuantityTv.context)
            dialog.show()
        }
    }

    private fun bringFront() {
        binding.avartarChattingQuantity.bringToFront()
    }

    private fun goMessage() {
        binding.avartarChattingBtn.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.main_frm, MessageFragment())
                .addToBackStack(null)
                .setReorderingAllowed(true)
                .commit()
        }
    }

    private fun showShortDialog() {
        val dialog = CustomAvartarInfoDialog(binding.avartarChattingQuantityTv.context)
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
        val pagerAdapter = AvartarVPAdapter(childFragmentManager,lifecycle)
        binding.avartarItemlistVp.adapter = pagerAdapter

        //탭 레이아웃과 뷰페이저 연동
        TabLayoutMediator(binding.avartarCategoryTl, binding.avartarItemlistVp) { tab, position ->
            // 탭의 텍스트 설정
            tab.text = tabElement[position]
        }.attach()
    }


}
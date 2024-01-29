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
        return binding.root
    }

    private fun saveButtonLogic() {
        binding.avartarSaveBtn.setOnClickListener {
            val dialog = CustomDetailInfoDialog(binding.avartarChattingQuantityTv.context)
                dialog.setContentView(R.layout.dialog_save_avartar)
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
        var tabElement = arrayOf("전체", "헤어", "피부", "한벌", "싱의", "하의", "신발")

        //ViewPager2에 TabFragments 전달(Adapter pattern)
        val fragments: List<Fragment> = ArrayList()
        fragments.apply {
            AvartarTabFragment.newInstance(tabElement[0])
            AvartarTabFragment.newInstance(tabElement[1])
            AvartarTabFragment.newInstance(tabElement[2])
            AvartarTabFragment.newInstance(tabElement[3])
            AvartarTabFragment.newInstance(tabElement[4])
            AvartarTabFragment.newInstance(tabElement[5])
            AvartarTabFragment.newInstance(tabElement[6])
        }
        //뷰페이저 어댑터 연결
        val pagerAdapter = AvartarVPAdapter(childFragmentManager, lifecycle)
        binding.avartarItemlistVp.adapter = pagerAdapter

        //탭 레이아웃과 뷰페이저 연동
        TabLayoutMediator(binding.avartarCategoryTl, binding.avartarItemlistVp) { tab, position ->
            // 탭의 텍스트 설정
            tab.text = tabElement[position]
        }.attach()
    }


}
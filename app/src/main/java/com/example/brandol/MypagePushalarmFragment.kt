package com.example.brandol

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.brandol.databinding.FragmentMypagePushalarmBinding

class MypagePushalarmFragment : Fragment() {

    lateinit var binding: FragmentMypagePushalarmBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMypagePushalarmBinding.inflate(inflater,container,false)
        binding.mypagePushAlarmBackBtn.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.main_frm,MypageFragment())
                .commit()
        }
        return binding.root
    }

}
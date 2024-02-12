package com.example.brandol.brandInfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.brandol.databinding.FragmentLinktreeBinding

class LinktreeFragment : Fragment() {

    lateinit var binding: FragmentLinktreeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLinktreeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //뒤로 가기
        binding.linktreeBackBtn.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }
}
package com.example.brandol

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.brandol.databinding.FragmentPointUseTotalBinding
import com.example.brandol.databinding.FragmentPointUseUseBinding

class PointUseUseFragment : Fragment() {

    lateinit var binding : FragmentPointUseUseBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPointUseUseBinding.inflate(inflater,container,false)

        return binding.root
    }
}
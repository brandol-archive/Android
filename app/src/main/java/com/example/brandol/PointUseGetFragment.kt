package com.example.brandol

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.brandol.databinding.FragmentPointUseGetBinding
import com.example.brandol.databinding.FragmentPointUseTotalBinding

class PointUseGetFragment : Fragment() {

    lateinit var binding : FragmentPointUseGetBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPointUseGetBinding.inflate(inflater,container,false)

        return binding.root
    }
}
package com.example.brandol

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.brandol.databinding.FragmentAbataBinding
import com.google.android.material.tabs.TabLayoutMediator


class AvartarFragment : Fragment() {
    lateinit var binding: FragmentAbataBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAbataBinding.inflate(inflater,container,false)

        return binding.root
    }

}
package com.example.brandol

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.brandol.databinding.FragmentPointUseTotalBinding

class PointUseTotalFragment :Fragment() {

    lateinit var binding : FragmentPointUseTotalBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPointUseTotalBinding.inflate(inflater,container,false)

        return binding.root
    }
}
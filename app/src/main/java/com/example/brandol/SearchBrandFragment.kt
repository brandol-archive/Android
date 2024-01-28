package com.example.brandol

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.brandol.databinding.FragmentSearchBrandBinding

class SearchBrandFragment : Fragment() {

    lateinit var binding : FragmentSearchBrandBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBrandBinding.inflate(inflater,container,false)

        return binding.root
    }
}
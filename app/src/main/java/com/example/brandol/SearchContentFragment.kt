package com.example.brandol

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.brandol.databinding.FragmentSearchContentBinding

class SearchContentFragment : Fragment() {
    lateinit var binding : FragmentSearchContentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchContentBinding.inflate(inflater,container,false)

        return binding.root
    }
}
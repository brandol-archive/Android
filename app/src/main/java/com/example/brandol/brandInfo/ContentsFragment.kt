package com.example.brandol.brandInfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.brandol.databinding.FragmentContentsBinding

class ContentsFragment : Fragment() {
    private var _binding: FragmentContentsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View? {
        _binding = FragmentContentsBinding.inflate(inflater, container, false)
        return binding.root
    }
}
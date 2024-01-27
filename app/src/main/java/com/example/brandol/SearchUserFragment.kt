package com.example.brandol

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.brandol.databinding.FragmentSearchUserBinding

class SearchUserFragment : Fragment() {
    lateinit var binding : FragmentSearchUserBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchUserBinding.inflate(inflater,container,false)

        return binding.root
    }
}
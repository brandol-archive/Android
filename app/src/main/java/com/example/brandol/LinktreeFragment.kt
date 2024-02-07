package com.example.brandol

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
        val view = FragmentLinktreeBinding.inflate(inflater, container, false)

        return binding.root
    }
}
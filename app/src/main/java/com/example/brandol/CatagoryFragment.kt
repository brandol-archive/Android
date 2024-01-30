package com.example.brandol

import BrandCategoryFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.brandol.databinding.FragmentCatagoryBinding
import com.example.brandol.databinding.FragmentHomeBinding

class CatagoryFragment : Fragment() {

    lateinit var binding: FragmentCatagoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCatagoryBinding.inflate(inflater, container, false)
        val view = binding.root


        binding.brandPlusIv.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.main_frm, BrandCategoryFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }

        binding.userPlusIv.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.main_frm, UserCategoryFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }


        //return inflater.inflate(R.layout.fragment_catagory, container, false)
        return view

    }
}

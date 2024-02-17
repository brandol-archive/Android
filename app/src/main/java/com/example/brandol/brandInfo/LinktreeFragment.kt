//package com.example.brandol.brandInfo
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import com.example.brandol.connection.RetrofitAPI
//import com.example.brandol.connection.RetrofitObject
//import com.example.brandol.databinding.FragmentLinktreeBinding
//
//class LinktreeFragment : Fragment() {
//
//    lateinit var binding: FragmentLinktreeBinding
//    private lateinit var retrofitAPI: RetrofitAPI // Declare RetrofitAPI instance
//
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = FragmentLinktreeBinding.inflate(inflater, container, false)
//
//        // RetrofitAPI 초기화
//        retrofitAPI = RetrofitObject.getRetrofitService
//
//        //뒤로 가기
//        binding.linktreeBackBtn.setOnClickListener {
//            requireActivity().supportFragmentManager.popBackStack()
//        }
//
//        return binding.root
//    }
//}

package com.example.brandol.brandInfo

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
        binding = FragmentLinktreeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //뒤로 가기
        binding.linktreeBackBtn.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }
}
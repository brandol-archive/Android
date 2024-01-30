package com.example.brandol

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.brandol.databinding.FragmentSearchBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)  // 데이터 바인딩 초기화

        // ViewPager2 초기화
        binding.catagoryContentVp.adapter = CategoryPagerAdapter(requireActivity())

        // 검색 버튼 클릭 이벤트 처리
       /* binding.btnSearchBarIb.setOnClickListener {
            // SearchBarFragment로 직접 화면 전환
            val searchBarFragment = SearchBarFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.btn_search_bar_ib, searchBarFragment)
            //transaction.replace(R.id.btn_search_bar_Fl, searchBarFragment)
            //transaction.addToBackStack(null)
            transaction.commit()
        }*/

        return binding.root
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_search, container, false)
    }
    private inner class CategoryPagerAdapter(fragmentActivity: FragmentActivity) :
        FragmentStateAdapter(fragmentActivity) {

        override fun getItemCount(): Int {
            // Return the number of fragments/pages
            return 1
        }

        override fun createFragment(position: Int): Fragment {
            // Return the fragment associated with the specified position
            return CatagoryFragment() // Assuming CategoryFragment is the fragment_catagory.xml fragment
        }
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SearchFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


}
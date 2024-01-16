//package com.example.brandol
//
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//
//// TODO: Rename parameter arguments, choose names that match
//// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"
//
///**
// * A simple [Fragment] subclass.
// * Use the [SearchFragment.newInstance] factory method to
// * create an instance of this fragment.
// */
//class SearchFragment : Fragment() {
//    // TODO: Rename and change types of parameters
//    private var param1: String? = null
//    private var param2: String? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_search, container, false)
//    }
//
//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment SearchFragment.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            SearchFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
//}


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.brandol.R

//브랜드 카테고리
//class SearchFragment : Fragment() {
//
//    private lateinit var recyclerView: RecyclerView
//    private lateinit var adapter: BrandCategoryAdapter
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val view = inflater.inflate(R.layout.fragment_search, container, false)
//
//        // RecyclerView 초기화
//        recyclerView = view.findViewById(R.id.brand_categoty_rv)
//        recyclerView.layoutManager = LinearLayoutManager(context)
//
//        // Adapter 초기화
//        adapter = BrandCategoryAdapter()
//        recyclerView.adapter = adapter
//
//        // RecyclerView에 더미 데이터 추가
//        for (i in 1..10) {
//            adapter.addItem("Brand $i")
//        }
//
//        return view
//    }
//}



//유저 카테고리
//class SearchFragment : Fragment() {
//
//    private lateinit var recyclerView: RecyclerView
//    private lateinit var userAdapter: UserCategoryAdapter
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val view = inflater.inflate(R.layout.fragment_search, container, false)
//
//        // RecyclerView 초기화
//        recyclerView = view.findViewById(R.id.brand_categoty_rv)
//        recyclerView.layoutManager = LinearLayoutManager(context)
//
//        // RecyclerView 설정
//        userAdapter = UserCategoryAdapter()
//        recyclerView.adapter = userAdapter
//
//        // 데이터 추가 예시 (실제 데이터 추가 방법에 맞게 수정)
//        userAdapter.addItem("User1")
//        userAdapter.addItem("User2")
//        userAdapter.addItem("User3")
//
//        return view
//    }
//}


//유저 카테고리
class SearchFragment : Fragment() {

    private lateinit var contentAdapter: ContentCategoryAdapter
    private lateinit var recyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 더미 데이터 생성
        val dummyDataList = generateDummyData()

        // RecyclerView 초기화
        contentAdapter = ContentCategoryAdapter(dummyDataList)
        recyclerView = view.findViewById(R.id.brand_categoty_rv)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = contentAdapter

        // 검색 기능 구현 (예: EditText에 텍스트 입력 시 필터링)
//        searchEditText.setOnKeyListener { _, _, _ ->
//            filterContentList(searchEditText.text.toString())
//            false
//        }
    }

    private fun generateDummyData(): List<ContentModel> {
        return listOf(
            ContentModel("Brand1", "Post Title 1", "Post Content 1", "좋아요 / 댓글 / 시간 / 작성자"),
            ContentModel("Brand2", "Post Title 2", "Post Content 2", "좋아요 / 댓글 / 시간 / 작성자"),
            ContentModel("Brand3", "Post Title 3", "Post Content 3", "좋아요 / 댓글 / 시간 / 작성자"),
            // Add more dummy data as needed
        )
    }
}
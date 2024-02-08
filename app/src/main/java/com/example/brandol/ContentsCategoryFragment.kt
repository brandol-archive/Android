import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.brandol.adaptor.ContentCategoryAdapter
import com.example.brandol.adaptor.ContentModel
import com.example.brandol.databinding.FragmentContentsCategoryBinding

class ContentsCategoryFragment : Fragment() {

    private lateinit var contentAdapter: ContentCategoryAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: FragmentContentsCategoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContentsCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 더미 데이터 생성
        val dummyDataList = generateDummyData()

        // RecyclerView 초기화
        contentAdapter = ContentCategoryAdapter(dummyDataList)
        recyclerView = binding.contentsCategotyRv
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = contentAdapter

        binding.btnBackContentsCategory.setOnClickListener {
            // 이전 화면으로 돌아가기
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    private fun generateDummyData(): List<ContentModel> {
        return listOf(
            ContentModel("BRANDOL", "게시물 제목", "브랜드 추구 방향성, 문화 \n" + "최근 게시글이 들어감글글글글글", "2023.12.10"),
            ContentModel("Brand1", "Post Title 1", "Post Content 1", "0000.00.00"),
            ContentModel("Brand2", "Post Title 2", "Post Content 2", "0000.00.00"),
            // Add more dummy data as needed
        )
    }
}

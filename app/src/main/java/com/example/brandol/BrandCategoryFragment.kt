import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.brandol.adaptor.BrandCategoryAdapter
import com.example.brandol.databinding.FragmentBrandCategoryBinding

class BrandCategoryFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: BrandCategoryAdapter
    private lateinit var binding: FragmentBrandCategoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBrandCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // RecyclerView 초기화
        recyclerView = binding.brandCategotyRv
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Adapter 초기화
        adapter = BrandCategoryAdapter()
        recyclerView.adapter = adapter

        // RecyclerView에 더미 데이터 추가
        for (i in 1..10) {
            adapter.addItem("Brand $i") // BrandCategoryAdapter에 addItem 메서드가 있다고 가정
        }

        // move_iv 클릭 동작 설정
        binding.btnBackBrandCategory.setOnClickListener {
            // 이전 화면으로 돌아가기
            requireActivity().supportFragmentManager.popBackStack()
        }
    }
}

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.brandol.AvatarStoreTabFragment
import com.example.brandol.adaptor.AvatarStoreAdapter
import com.example.brandol.databinding.FragmentAvatarstoreCategoryBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class AvatarStoreCategoryFragment : Fragment() {

    private lateinit var binding: FragmentAvatarstoreCategoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAvatarstoreCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager: ViewPager2 = binding.avatarStoreVp
        val tabLayout: TabLayout = binding.avatarStoreTabs

        val adapter = AvatarStoreAdapter(requireActivity())

        // 탭 프래그먼트를 하나로 하고 서버에서 정보 받아올 때 다르게 할 예정
        adapter.addFragment(AvatarStoreTabFragment(), "전체")
        adapter.addFragment(AvatarStoreTabFragment(), "헤어")
        adapter.addFragment(AvatarStoreTabFragment(), "피부")
        adapter.addFragment(AvatarStoreTabFragment(), "한 벌")
        adapter.addFragment(AvatarStoreTabFragment(), "상의")
        adapter.addFragment(AvatarStoreTabFragment(), "하의")
        adapter.addFragment(AvatarStoreTabFragment(), "신발")
        adapter.addFragment(AvatarStoreTabFragment(), "배경")
//        adapter.addFragment(OverallFragment(), "전체")
//        adapter.addFragment(HairFragment(), "헤어")
//        adapter.addFragment(SkinFragment(), "피부")
//        adapter.addFragment(OutfitFragment(), "한 벌")
//        adapter.addFragment(TopFragment(), "상의")

        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = adapter.titleList[position]
        }.attach()

        binding.btnBackAvatarstoreCategory.setOnClickListener {
            // 이전 화면으로 돌아가기
            requireActivity().supportFragmentManager.popBackStack()
        }
    }
}

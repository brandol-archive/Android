import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.fragment.app.Fragment
import com.example.brandol.R // R 클래스의 위치에 따라 import 경로가 달라질 수 있습니다.
import com.example.brandol.databinding.FragmentPointMissionSurveyBinding

class PointMissionSurveyFragment : Fragment() {

    private lateinit var binding: FragmentPointMissionSurveyBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPointMissionSurveyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 뒤로가기 버튼 클릭 시 Fragment를 닫습니다.
        binding.pointMissionBackBtnIv.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        // 각 버튼에 대한 체크 상태 변경 리스너를 설정합니다.
        binding.q31stB.setOnCheckedChangeListener { group, isChecked ->
            if (isChecked) {
                binding.q31stB.setTextColor(resources.getColor(R.color.white))
                binding.q32ndB.setTextColor(resources.getColor(R.color.selectedpurple))
                binding.q33rdB.setTextColor(resources.getColor(R.color.selectedpurple))
            }
        }

        binding.q32ndB.setOnCheckedChangeListener { group, isChecked ->
            if (isChecked) {
                binding.q31stB.setTextColor(resources.getColor(R.color.selectedpurple))
                binding.q32ndB.setTextColor(resources.getColor(R.color.white))
                binding.q33rdB.setTextColor(resources.getColor(R.color.selectedpurple))
            }
        }

        binding.q33rdB.setOnCheckedChangeListener { group, isChecked ->
            if (isChecked) {
                binding.q31stB.setTextColor(resources.getColor(R.color.selectedpurple))
                binding.q32ndB.setTextColor(resources.getColor(R.color.selectedpurple))
                binding.q33rdB.setTextColor(resources.getColor(R.color.white))
            }
        }
    }
}

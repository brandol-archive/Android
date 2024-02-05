import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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

        // Q1 문제에 대한 체크 상태 변경 리스너
        binding.q1MissionO.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.q1MissionX.isChecked = false
            }
            updateCompletionMessageVisibility()
        }

        binding.q1MissionX.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.q1MissionO.isChecked = false
            }
            updateCompletionMessageVisibility()
        }

        // Q3 문제에 대한 체크 상태 변경 리스너
        binding.q31stB.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.q32ndB.isChecked = false
                binding.q33rdB.isChecked = false
            }
            updateCompletionMessageVisibility()
        }

        binding.q32ndB.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.q31stB.isChecked = false
                binding.q33rdB.isChecked = false
            }
            updateCompletionMessageVisibility()
        }

        binding.q33rdB.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.q32ndB.isChecked = false
                binding.q31stB.isChecked = false
            }
            updateCompletionMessageVisibility()
        }

        // 초기 상태에서 응답 완료 메시지의 가시성을 설정합니다.
        updateCompletionMessageVisibility()
    }

    private fun updateCompletionMessageVisibility() {
        if (areAllQuestionsAnswered()) {
            binding.pointMissionSurveyBeforeIb.visibility = View.VISIBLE
            binding.pointMissionSurveyBeforeTv.visibility = View.VISIBLE
            binding.pointMissionSurveyIngIb.visibility = View.GONE
            binding.pointMissionSurveyIngTv.visibility = View.GONE
        } else {
            binding.pointMissionSurveyBeforeIb.visibility = View.GONE
            binding.pointMissionSurveyBeforeTv.visibility = View.GONE
            binding.pointMissionSurveyIngIb.visibility = View.VISIBLE
            binding.pointMissionSurveyIngTv.visibility = View.VISIBLE
        }
    }

    private fun areAllQuestionsAnswered(): Boolean {
        val isQ1Answered = binding.q1MissionO.isChecked || binding.q1MissionX.isChecked
        val isQ2Answered = /* Add your check for Q2, for example: */ !binding.q2Edittext.text.isNullOrEmpty()
        val isQ3Answered = binding.q31stB.isChecked || binding.q32ndB.isChecked || binding.q33rdB.isChecked

        // Add similar checks for other questions...

        return isQ1Answered && isQ2Answered && isQ3Answered /* Add other question checks... */
    }

}

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import com.example.brandol.R
import com.example.brandol.collection.ItemModel
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

        // 미션 완료 성공시 뜨는 팝업
        binding.pointMissionSurveyCompleteIb.setOnClickListener {
            showDialog()
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

        // Q2 문제에 대한 체크 상태 변경 리스너
        binding.q2MissionO.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.q2MissionX.isChecked = false
            }
            updateCompletionMessageVisibility()
        }

        binding.q2MissionX.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.q2MissionO.isChecked = false
            }
            updateCompletionMessageVisibility()
        }



        // Q3 문제에 대한 체크 상태 변경 리스너
        binding.q31stB.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.q32ndB.isChecked = false
                binding.q33rdB.isChecked = false
                binding.q34thB.isChecked = false
            }
            updateCompletionMessageVisibility()
        }

        binding.q32ndB.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.q31stB.isChecked = false
                binding.q33rdB.isChecked = false
                binding.q34thB.isChecked = false
            }
            updateCompletionMessageVisibility()
        }

        binding.q33rdB.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.q32ndB.isChecked = false
                binding.q31stB.isChecked = false
                binding.q34thB.isChecked = false
            }
            updateCompletionMessageVisibility()
        }

        binding.q34thB.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.q32ndB.isChecked = false
                binding.q31stB.isChecked = false
                binding.q33rdB.isChecked = false
            }
            updateCompletionMessageVisibility()
        }


        // Q4 문제에 대한 체크 상태 변경 리스너
        binding.q41stB.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.q42ndB.isChecked = false
                binding.q43rdB.isChecked = false
                binding.q44thB.isChecked = false
            }
            updateCompletionMessageVisibility()
        }

        binding.q42ndB.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.q41stB.isChecked = false
                binding.q43rdB.isChecked = false
                binding.q44thB.isChecked = false
            }
            updateCompletionMessageVisibility()
        }

        binding.q43rdB.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.q42ndB.isChecked = false
                binding.q41stB.isChecked = false
                binding.q44thB.isChecked = false
            }
            updateCompletionMessageVisibility()
        }

        binding.q44thB.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.q42ndB.isChecked = false
                binding.q41stB.isChecked = false
                binding.q43rdB.isChecked = false
            }
            updateCompletionMessageVisibility()
        }



        // Q5 문제에 대한 체크 상태 변경 리스너
        binding.q51stB.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.q52ndB.isChecked = false
                binding.q53rdB.isChecked = false
                binding.q54thB.isChecked = false
                binding.q55thB.isChecked = false
                binding.q56thB.isChecked = false
                binding.q57thB.isChecked = false
                binding.q58thB.isChecked = false
            }
            updateCompletionMessageVisibility()
        }

        binding.q52ndB.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.q51stB.isChecked = false
                binding.q53rdB.isChecked = false
                binding.q54thB.isChecked = false
                binding.q55thB.isChecked = false
                binding.q56thB.isChecked = false
                binding.q57thB.isChecked = false
                binding.q58thB.isChecked = false
            }
            updateCompletionMessageVisibility()
        }

        binding.q53rdB.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.q51stB.isChecked = false
                binding.q52ndB.isChecked = false
                binding.q54thB.isChecked = false
                binding.q55thB.isChecked = false
                binding.q56thB.isChecked = false
                binding.q57thB.isChecked = false
                binding.q58thB.isChecked = false
            }
            updateCompletionMessageVisibility()
        }

        binding.q54thB.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.q51stB.isChecked = false
                binding.q52ndB.isChecked = false
                binding.q53rdB.isChecked = false
                binding.q55thB.isChecked = false
                binding.q56thB.isChecked = false
                binding.q57thB.isChecked = false
                binding.q58thB.isChecked = false
            }
            updateCompletionMessageVisibility()
        }

        binding.q55thB.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.q51stB.isChecked = false
                binding.q52ndB.isChecked = false
                binding.q53rdB.isChecked = false
                binding.q54thB.isChecked = false
                binding.q56thB.isChecked = false
                binding.q57thB.isChecked = false
                binding.q58thB.isChecked = false
            }
            updateCompletionMessageVisibility()
        }

        binding.q56thB.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.q51stB.isChecked = false
                binding.q52ndB.isChecked = false
                binding.q53rdB.isChecked = false
                binding.q54thB.isChecked = false
                binding.q55thB.isChecked = false
                binding.q57thB.isChecked = false
                binding.q58thB.isChecked = false
            }
            updateCompletionMessageVisibility()
        }

        binding.q57thB.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.q51stB.isChecked = false
                binding.q52ndB.isChecked = false
                binding.q53rdB.isChecked = false
                binding.q54thB.isChecked = false
                binding.q55thB.isChecked = false
                binding.q56thB.isChecked = false
                binding.q58thB.isChecked = false
            }
            updateCompletionMessageVisibility()
        }

        binding.q58thB.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.q51stB.isChecked = false
                binding.q52ndB.isChecked = false
                binding.q53rdB.isChecked = false
                binding.q54thB.isChecked = false
                binding.q55thB.isChecked = false
                binding.q56thB.isChecked = false
                binding.q57thB.isChecked = false
            }
            updateCompletionMessageVisibility()
        }

        // 초기 상태에서 응답 완료 메시지의 가시성을 설정합니다.
        updateCompletionMessageVisibility()
    }

    private fun updateCompletionMessageVisibility() {
        if (areAllQuestionsAnswered()) {
            binding.pointMissionSurveyCompleteIb.visibility = View.VISIBLE
            binding.pointMissionSurveyCompleteTv.visibility = View.VISIBLE
            binding.pointMissionSurveyIngIb.visibility = View.GONE
            binding.pointMissionSurveyIngTv.visibility = View.GONE
        } else {
            binding.pointMissionSurveyCompleteIb.visibility = View.GONE
            binding.pointMissionSurveyCompleteTv.visibility = View.GONE
            binding.pointMissionSurveyIngIb.visibility = View.VISIBLE
            binding.pointMissionSurveyIngTv.visibility = View.VISIBLE
        }
    }

    private fun areAllQuestionsAnswered(): Boolean {
        val isQ1Answered = binding.q1MissionO.isChecked || binding.q1MissionX.isChecked
        val isQ2Answered = binding.q2MissionO.isChecked || binding.q2MissionX.isChecked
        val isQ3Answered = binding.q31stB.isChecked || binding.q32ndB.isChecked || binding.q33rdB.isChecked || binding.q34thB.isChecked
        val isQ4Answered = binding.q41stB.isChecked || binding.q42ndB.isChecked || binding.q43rdB.isChecked || binding.q44thB.isChecked
        val isQ5Answered = binding.q51stB.isChecked || binding.q52ndB.isChecked || binding.q53rdB.isChecked || binding.q54thB.isChecked || binding.q55thB.isChecked || binding.q56thB.isChecked || binding.q57thB.isChecked || binding.q58thB.isChecked
        val isQ6Answered = !binding.q6Edittext.text.isNullOrEmpty()
        val isQ7Answered = !binding.q7Edittext.text.isNullOrEmpty()
        val isQ8Answered = !binding.q8Edittext.text.isNullOrEmpty()
        val isQ9Answered = !binding.q9Edittext.text.isNullOrEmpty()
        val isQ10Answered = !binding.q10Edittext.text.isNullOrEmpty()

        // Add similar checks for other questions...

        return isQ1Answered && isQ2Answered && isQ3Answered && isQ4Answered && isQ5Answered && isQ6Answered && isQ7Answered && isQ8Answered && isQ9Answered && isQ10Answered  /* Add other question checks... */
    }

    private fun showDialog() {
        val context = requireContext()

        val dialog = Dialog(context)
        dialog.setContentView(LayoutInflater.from(context).inflate(R.layout.dialog_mission_complete_popup, null))
        //dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        // Set custom dialog size
        val width = resources.displayMetrics.widthPixels * 0.7 // Adjust as needed
        val height = resources.displayMetrics.heightPixels * 0.5 // Adjust as needed
        dialog.window?.setLayout(width.toInt(), height.toInt())

        // Make dialog background transparent
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)


        dialog.show()

//        // 다이얼로그를 직접 생성하고 커스텀 레이아웃 설정
//        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_mission_complete_popup, null)
//        val dialog = AlertDialog.Builder(context)
//            .setView(dialogView)
//            .create()
//
//        // 다이얼로그 표시
//        dialog.show()
    }

}

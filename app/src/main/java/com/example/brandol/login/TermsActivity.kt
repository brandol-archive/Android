// TermsActivity.kt
package com.example.brandol.login

import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import com.example.brandol.R
import com.example.brandol.databinding.ActivityTermsBinding

class TermsActivity : AppCompatActivity() {

    lateinit var binding: ActivityTermsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTermsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 약관보기 텍스트 언더라인 처리
        binding.terms2ViewTv.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        binding.terms3ViewTv.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        binding.terms4ViewTv.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        binding.terms5ViewTv.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        binding.terms6ViewTv.paintFlags = Paint.UNDERLINE_TEXT_FLAG

        binding.termsBackBtn.setOnClickListener {
            finish()
        }


        binding.termsNextOkB.setOnClickListener {
            val intent = Intent(this, SignupNicknameActivity::class.java)
            startActivity(intent)
        }

        // 약관 전체 동의하기 체크박스
        binding.checkbox.setOnCheckedChangeListener { _, isChecked ->
            // 모든 하위 체크박스들의 상태를 변경
            binding.checkbox1.isChecked = isChecked
            binding.checkbox2.isChecked = isChecked
            binding.checkbox3.isChecked = isChecked
            binding.checkbox4.isChecked = isChecked
            binding.checkbox5.isChecked = isChecked
            binding.checkbox6.isChecked = isChecked
        }

        // 각 체크박스들의 체크 상태 변경 시
        val checkBoxChangeListener = CompoundButton.OnCheckedChangeListener { _, _ ->
            // 모든 체크박스들의 상태를 확인하여 다음 버튼을 표시하거나 숨김
            updateNextButtonVisibility()
        }

        // 각 체크박스들에 리스너 등록
        binding.checkbox1.setOnCheckedChangeListener(checkBoxChangeListener)
        binding.checkbox2.setOnCheckedChangeListener(checkBoxChangeListener)
        binding.checkbox3.setOnCheckedChangeListener(checkBoxChangeListener)
        binding.checkbox4.setOnCheckedChangeListener(checkBoxChangeListener)
        binding.checkbox5.setOnCheckedChangeListener(checkBoxChangeListener)
        binding.checkbox6.setOnCheckedChangeListener(checkBoxChangeListener)

        // 초기 상태에서는 다음 버튼은 숨겨져 있어야 함
        binding.termsNextOkB.visibility = android.view.View.GONE
        binding.termsNextNoB.visibility = android.view.View.VISIBLE


        // 각 약관보기 텍스트 클릭 시 해당 약관 내용을 보여주는 액티비티로 이동
        binding.terms2ViewTv.setOnClickListener {
            navigateToTermsContent(getString(R.string.terms_of_service))
        }
        binding.terms3ViewTv.setOnClickListener {
            navigateToTermsContent(getString(R.string.privacy_policy))
        }
        binding.terms4ViewTv.setOnClickListener {
            navigateToTermsContent(getString(R.string.third_party_consent))
        }
        binding.terms5ViewTv.setOnClickListener {
            navigateToTermsContent(getString(R.string.collect_and_use))
        }
        binding.terms6ViewTv.setOnClickListener {
            navigateToTermsContent(getString(R.string.push_notification))
        }

    }


    private fun navigateToTermsContent(title: String) {
        val intent = Intent(this, TermsContentActivity::class.java)
        // 약관 타이틀을 Intent에 추가
        intent.putExtra(TermsContentActivity.EXTRA_CONTENT_TITLE, title)
        startActivity(intent)
    }


    private fun updateNextButtonVisibility() {
        // 모든 하위 체크박스들의 상태 확인
        val isCheckbox1Checked = binding.checkbox1.isChecked
        val isCheckbox2Checked = binding.checkbox2.isChecked
        val isCheckbox3Checked = binding.checkbox3.isChecked
        val isCheckbox4Checked = binding.checkbox4.isChecked
        val isCheckbox5Checked = binding.checkbox5.isChecked
        val isCheckbox6Checked = binding.checkbox6.isChecked

        // 약관 전체 동의하기 체크박스의 상태
        val isAllChecked = binding.checkbox.isChecked

        // 모든 체크박스가 선택되었을 때
        if (isCheckbox1Checked && isCheckbox2Checked && isCheckbox3Checked &&
            isCheckbox4Checked && isCheckbox5Checked ) {
            // 다음 버튼 표시, 회색 버튼 숨김
            binding.termsNextOkB.visibility = android.view.View.VISIBLE
            binding.termsNextNoB.visibility = android.view.View.GONE
        } else {
            // 그 외의 경우 다음 버튼 숨김, 회색 버튼 표시
            binding.termsNextOkB.visibility = android.view.View.GONE
            binding.termsNextNoB.visibility = android.view.View.VISIBLE
        }
    }
}

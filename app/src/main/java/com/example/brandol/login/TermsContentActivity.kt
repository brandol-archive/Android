package com.example.brandol.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.brandol.R
import com.example.brandol.databinding.ActivityTermsContentBinding

class TermsContentActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_CONTENT_TITLE = "extra_content_title"
    }

    lateinit var binding: ActivityTermsContentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTermsContentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 전달된 타이틀을 가져옴
        val contentTitle = intent.getStringExtra(EXTRA_CONTENT_TITLE)
        contentTitle?.let {
            // 약관 내용을 직접 문자열로 가져옴
            val content = getTermsContent(it)
            // TextView를 사용하여 타이틀 동적으로 변경
            binding.termsTitleTv.text = it
            binding.contentTextView.text = content
        }

        // 뒤로 가기 버튼 클릭 시 현재 액티비티 종료
        binding.termsContentBackBtn.setOnClickListener {
            finish()
        }
    }

    private fun getTermsContent(contentTitle: String): String {
        return when (contentTitle) {
            getString(R.string.terms_of_service) -> getString(R.string.terms_of_service_content)
            getString(R.string.privacy_policy) -> getString(R.string.privacy_policy_content)
            getString(R.string.third_party_consent) -> getString(R.string.third_party_consent_content)
            getString(R.string.collect_and_use) -> getString(R.string.collect_and_use_content)
            getString(R.string.push_notification) -> getString(R.string.push_notification_content)
            else -> ""
        }
    }
}

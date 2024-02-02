// NicknameActivity.kt
package com.example.brandol

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.example.brandol.databinding.ActivitySignupNicknameBinding

class SignupNicknameActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignupNicknameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupNicknameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 에딧텍스트의 텍스트 변경을 감지하는 TextWatcher 등록
        binding.nicknameEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {
                // 에딧텍스트에 텍스트가 입력되면 호출
                val isNicknameNotEmpty = charSequence?.isNotEmpty() == true

                // 텍스트가 입력되었을 때 보라색 버튼 표시, 그렇지 않으면 회색 버튼 표시
                if (isNicknameNotEmpty) {
                    binding.signUpOkB.visibility = android.view.View.VISIBLE
                    binding.signUpNoB.visibility = android.view.View.GONE
                } else {
                    binding.signUpOkB.visibility = android.view.View.GONE
                    binding.signUpNoB.visibility = android.view.View.VISIBLE
                }
            }

            override fun afterTextChanged(editable: Editable?) {}
        })

        binding.nicknameBackBtn.setOnClickListener {
            finish()
        }

        binding.signUpOkB.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // 에딧텍스트 클릭 시 힌트 텍스트 삭제
        binding.nicknameEt.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.nicknameEt.text= null
                binding.nicknameEt.setTextColor(resources.getColor(R.color.black))
            } else {
                // 에딧텍스트가 포커스를 잃으면서 텍스트가 비어있을 경우 힌트 텍스트를 다시 설정
                if (binding.nicknameEt.text.isEmpty()) {
                    binding.nicknameEt.setTextColor(resources.getColor(R.color.gray))
                }
            }
        }

        // 초기 상태에서는 보라색 버튼은 숨겨져 있고, 회색 버튼이 표시되어 있어야 함
        binding.signUpOkB.visibility = android.view.View.GONE
        binding.signUpNoB.visibility = android.view.View.VISIBLE
    }
}

package com.example.codingnatorpoject

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.navigation.fragment.findNavController
import com.example.codingnatorpoject.databinding.FragmentLogInBinding
import com.google.android.material.snackbar.Snackbar

class LogInFragment : Fragment() {

    var email = "";
    var pw = "";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    var binding: FragmentLogInBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLogInBinding.inflate(inflater)

        // input field에 대한 Listener 추가
        with (binding) {
            this!!.inputEmail.addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    email = s.toString()
                }

                override fun afterTextChanged(s: Editable?) {}

            })
            this!!.inputPassword.addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    pw = s.toString()
                }

                override fun afterTextChanged(s: Editable?) {}

            })
        }

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.btnLogIn?.setOnClickListener {


            if ((activity as LogActivity).dbc.login(email,pw) != null) {
                findNavController().navigate(R.id.action_logInFragment_to_entryFragment)
            }
            else {
                // 알림이 보여야 하므로 키보드를 숨김
                (context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager)
                    .hideSoftInputFromWindow(view.windowToken, 0)
                
                // 이후 알림 출력
                Snackbar
                    .make(this.requireView(), "이메일 또는 비밀번호가 올바르지 않습니다.", Snackbar.LENGTH_SHORT)
                    .show()
            }

        }
    }
}
package com.example.codingnatorpoject

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.codingnatorpoject.DBConnection.Cognito
import com.example.codingnatorpoject.databinding.FragmentSignUpBinding
import com.google.android.material.snackbar.Snackbar

class SignUpFragment : Fragment() {

    var email = "";
    var pw = "";
    var pw_confirm = "";
    var nickname = "";
    var verification_code = "";

    lateinit var auth: Cognito

    lateinit var binding: FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        auth = Cognito(activity?.applicationContext)
        binding = FragmentSignUpBinding.inflate(inflater)

        // input field에 대한 Listener 추가
        with (binding) {
            inputEmail.addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    email = s.toString()
                }

                override fun afterTextChanged(s: Editable?) {}

            })

            inputPassword.addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    pw = s.toString()
                }

                override fun afterTextChanged(s: Editable?) {}

            })

            inputPasswordConfirm.addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    pw_confirm = s.toString()
                }

                override fun afterTextChanged(s: Editable?) {}

            })

            inputNickname.addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    nickname = s.toString()
                }

                override fun afterTextChanged(s: Editable?) {}

            })

            inputVerificationCode.addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    verification_code = s.toString()
                }

                override fun afterTextChanged(s: Editable?) {}

            })
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            btnVerificate.setOnClickListener {
                Log.i("Cognito", "Let's verificate...")
                if (pw == pw_confirm) {
                    auth.addAttribute("email", email);
                    auth.addAttribute("name", nickname);
                    auth.signUpInBackground(nickname, pw);
                } else {
                    Snackbar
                        .make(view, "확인 비밀번호 불일치", Snackbar.LENGTH_SHORT)
                        .show()
                }
            }

            btnSignUp.setOnClickListener {
                Log.i("Cognito", "Signing up...")
                auth.confirmUser(nickname, verification_code)
                findNavController().navigate(R.id.action_signUpFragment_to_logInFragment)
            }

        }
    }

}
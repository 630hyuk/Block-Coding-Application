package com.example.codingnatorpoject

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.codingnatorpoject.DBConnection.Cognito
import com.example.codingnatorpoject.DBConnection.PasswordManager
import com.example.codingnatorpoject.databinding.FragmentSignUpBinding
import com.google.android.material.snackbar.Snackbar
import java.lang.Exception

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
        with(binding) {
            inputEmail.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    email = s.toString()
                }

                override fun afterTextChanged(s: Editable?) {}

            })

            inputPassword.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    pw = s.toString()
                }

                override fun afterTextChanged(s: Editable?) {}

            })

            inputPasswordConfirm.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    pw_confirm = s.toString()
                }

                override fun afterTextChanged(s: Editable?) {}

            })

            inputNickname.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    nickname = s.toString()
                }

                override fun afterTextChanged(s: Editable?) {}

            })

            inputVerificationCode.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

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
                val enc: String
                try {
                    enc = PasswordManager.encrypt(pw)
                } catch (e: PasswordManager.InvalidPasswordException) {
                    Toast
                        .makeText(context, "올바르지 않은 비밀번호", Toast.LENGTH_SHORT)
                        .show()
                    return@setOnClickListener
                }

                if (!email.contains("@")) {
                    Toast
                        .makeText(context, "올바르지 않은 이메일", Toast.LENGTH_SHORT)
                        .show()
                    return@setOnClickListener
                }

                if (pw != pw_confirm) {
                    Toast
                        .makeText(context, "확인 비밀번호 불일치", Toast.LENGTH_SHORT)
                        .show()
                    return@setOnClickListener
                }

                Log.i("SignUpFragment", "Sending email...")

                auth.addAttribute("email", email)
                Cognito.email = email;
                auth.addAttribute("name", nickname)
                Cognito.nickname = nickname;
                auth.addAttribute("custom:pw", enc)
                Cognito.pw = enc;
                auth.signUpInBackground(nickname, pw)

            }

            btnSignUp.setOnClickListener {
                Log.i("SignUpFragment", "Signing up...")
                auth.confirmUser(nickname, verification_code)

                Thread.sleep(800)

                findNavController().navigate(R.id.action_signUpFragment_to_logInFragment)

            }

        }

    }

}

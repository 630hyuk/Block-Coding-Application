package com.example.codingnatorpoject

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.navigation.fragment.findNavController
import com.example.codingnatorpoject.DBConnection.Cognito
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

    lateinit var binding: FragmentLogInBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLogInBinding.inflate(inflater)

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
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mainIntent = Intent(requireActivity(), MainActivity::class.java) //메인 엑티비티로 화면 전환

        binding.txtSignUp.setOnClickListener {
            Log.i("LoginFragment", "Let's Sign up...")
            findNavController().navigate(R.id.action_logInFragment_to_signUpFragment)
        }

        binding.btnLogIn.setOnClickListener {
            Log.i("Cognito", "Trying login...")
            val authentication = Cognito(activity?.applicationContext)
            authentication.userLogin(email, pw)

            startActivity(mainIntent)
        }
    }
}
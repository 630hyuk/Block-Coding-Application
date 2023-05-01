package com.example.codingnatorpoject

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.codingnatorpoject.databinding.FragmentEntryBinding

class EntryFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    var binding: FragmentEntryBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEntryBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mainIntent = Intent(requireActivity(), MainActivity::class.java) //메인 엑티비티로 화면 전환

        binding?.btnGoToMain?.setOnClickListener {
            startActivity(mainIntent)
        }

        binding?.btnLogOut?.setOnClickListener {
            findNavController().navigate(R.id.action_entryFragment_to_logInFragment)
        }
    }
}
package com.example.codingnatorpoject

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.codingnatorpoject.databinding.FragmentStageOneBinding
import com.example.codingnatorpoject.databinding.FragmentStageTwoBinding

class StageTwoFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    var binding: FragmentStageTwoBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStageTwoBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = Bundle()  //몇 챕터를 선택할지 이 번들에 넣어서 알려줍니다.
        val mainIntent = Intent(requireActivity(), MainActivity::class.java)  //mainActivity로 넘어가기 위한 Intent입니다.

        binding?.btnGoTo1?.setOnClickListener {
            findNavController().navigate(R.id.action_stageTwoFragment_to_stageOneFragment)
        }
        binding?.btnGoTo3?.setOnClickListener {
            findNavController().navigate(R.id.action_stageTwoFragment_to_stageThreeFragment)
        }
        binding?.btnReturnToMain?.setOnClickListener {  //혹시 여기를 이렇게 만든 이유가 있나요? intent로 한 Activiy로 하는데 문제가 있었나요?
            findNavController().navigate(R.id.action_stageTwoFragment_to_selectQuizFragment2)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
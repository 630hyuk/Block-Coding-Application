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

        binding?.btnGoTo1?.setOnClickListener {
            findNavController().navigate(R.id.action_stageTwoFragment_to_stageOneFragment)
        }
        binding?.btnGoTo3?.setOnClickListener {
            findNavController().navigate(R.id.action_stageTwoFragment_to_stageThreeFragment)
        }
        binding?.btnReturnToSelect?.setOnClickListener {
            findNavController().navigate(R.id.action_stageTwoFragment_to_selectQuizFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
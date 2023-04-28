package com.example.codingnatorpoject

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.codingnatorpoject.databinding.FragmentStageOneBinding

class StageOneFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    var binding: FragmentStageOneBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStageOneBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = Bundle()  //몇 챕터를 선택할지 이 번들에 넣어서 알려줍니다.
        val mainIntent = Intent(requireActivity(), MainActivity::class.java)  //mainActivity로 넘어가기 위한 Intent입니다.

        binding?.btnChapter1?.setOnClickListener {
            bundle.putInt("chapterNumber", 1)  //챕터 1임을 알려줍니다.
            findNavController().navigate(R.id.action_stageOneFragment_to_introduceFragment, bundle)
        }

        binding?.btnChapter2?.setOnClickListener {
            bundle.putInt("chapterNumber", 2)  //챕터 2임을 알려줍니다.
            findNavController().navigate(R.id.action_stageOneFragment_to_introduceFragment, bundle)
        }

        binding?.btnChapter3?.setOnClickListener {
            bundle.putInt("chapterNumber", 3)  //챕터3임을 알려줍니다.
            findNavController().navigate(R.id.action_stageOneFragment_to_introduceFragment, bundle)
        }

        binding?.btnChapter4?.setOnClickListener {
            bundle.putInt("chapterNumber", 4)  //탭터4임을 알려줍니다다
            findNavController().navigate(R.id.action_stageOneFragment_to_introduceFragment, bundle)
        }

        binding?.btnReturnToMain?.setOnClickListener {
            findNavController().navigate(R.id.action_stageOneFragment_to_selectQuizFragment2, bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
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

        binding?.btnGoTo2?.setOnClickListener {
            findNavController().navigate(R.id.action_stageOneFragment_to_stageTwoFragment)
        }

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

        binding?.btnChapter5?.setOnClickListener {
            bundle.putInt("chapterNumber", 5)  //챕터5임을 알려줍니다
            findNavController().navigate(R.id.action_stageOneFragment_to_introduceFragment, bundle)
        }

        binding?.btnChapter6?.setOnClickListener {
            bundle.putInt("chapterNumber", 6)  //챕터6임을 알려줍니다
            findNavController().navigate(R.id.action_stageOneFragment_to_introduceFragment, bundle)
        }

        binding?.btnChapter7?.setOnClickListener {
            bundle.putInt("chapterNumber", 7)  //챕터7임을 알려줍니다
            findNavController().navigate(R.id.action_stageOneFragment_to_introduceFragment, bundle)
        }

        binding?.btnChapter8?.setOnClickListener {
            bundle.putInt("chapterNumber", 8)  //챕터8임을 알려줍니다
            findNavController().navigate(R.id.action_stageOneFragment_to_introduceFragment, bundle)
        }

        binding?.btnChapter9?.setOnClickListener {
            bundle.putInt("chapterNumber", 9)  //챕터9임을 알려줍니다
            findNavController().navigate(R.id.action_stageOneFragment_to_introduceFragment, bundle)
        }

        binding?.btnChapter10?.setOnClickListener {
            bundle.putInt("order", 1)  //챕터10의 1번부터 10번까지를 호출하기 위한 order값입니다
            findNavController().navigate(R.id.action_stageOneFragment_to_stageOneChapterTenQuizOXFragment, bundle)
        }

        binding?.btnReturnToMain?.setOnClickListener {  //혹시 여기를 이렇게 만든 이유가 있나요? intent로 한 Activiy로 하는데 문제가 있었나요?
            findNavController().navigate(R.id.action_stageOneFragment_to_selectQuizFragment2, bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
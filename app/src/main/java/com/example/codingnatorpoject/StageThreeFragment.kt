package com.example.codingnatorpoject

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.codingnatorpoject.databinding.FragmentStageOneBinding
import com.example.codingnatorpoject.databinding.FragmentStageThreeBinding
import com.example.codingnatorpoject.databinding.FragmentStageTwoBinding

class StageThreeFragment : Fragment() {
    private var restart : String? = null //LastResultFragment에서 재시작 신호를 받았을때

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            restart = it.getString("restart")
        }
    }

    var binding: FragmentStageThreeBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStageThreeBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = Bundle()  //몇 챕터를 선택할지 이 번들에 넣어서 알려줍니다.

        binding?.btnGoTo2?.setOnClickListener {
            findNavController().navigate(R.id.action_stageThreeFragment_to_stageTwoFragment)
        }

        binding?.btnReturnToSelect?.setOnClickListener {
            //findNavController().navigate(R.id.action_stageThreeFragment_to_selectQuizFragment)
            findNavController().popBackStack()  //해당 프래그먼트를 뒤로 보내는 역할을 해준다.
        }

        binding?.btnChapter31?.setOnClickListener {
            //binding?.imgStarOne11?.setImageResource(android.R.drawable.btn_star_big_off)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
            bundle.putInt("chapterNumber", 1)  //챕터 1임을 알려줍니다.
            findNavController().navigate(R.id.action_stageThreeFragment_to_introduceThreeFragment, bundle)
        }

        binding?.btnChapter32?.setOnClickListener {
            bundle.putInt("chapterNumber", 2)  //챕터 2임을 알려줍니다.
            findNavController().navigate(R.id.action_stageThreeFragment_to_introduceThreeFragment, bundle)
        }

        binding?.btnChapter33?.setOnClickListener {
            bundle.putInt("chapterNumber", 3)  //챕터 3임을 알려줍니다.
            findNavController().navigate(R.id.action_stageThreeFragment_to_introduceThreeFragment, bundle)
        }

        binding?.btnChapter34?.setOnClickListener {
            bundle.putInt("chapterNumber", 4)  //챕터 4임을 알려줍니다.
            findNavController().navigate(R.id.action_stageThreeFragment_to_introduceThreeFragment, bundle)
        }

        binding?.btnChapter35?.setOnClickListener {
            bundle.putInt("chapterNumber", 5)  //챕터 5임을 알려줍니다.
            findNavController().navigate(R.id.action_stageThreeFragment_to_introduceThreeFragment, bundle)
        }

        binding?.btnChapter36?.setOnClickListener {
            bundle.putInt("chapterNumber", 6)  //챕터 6임을 알려줍니다.
            findNavController().navigate(R.id.action_stageThreeFragment_to_introduceThreeFragment, bundle)
        }

        binding?.btnChapter37?.setOnClickListener {
            bundle.putInt("chapterNumber", 7)  //챕터 7임을 알려줍니다.
            findNavController().navigate(R.id.action_stageThreeFragment_to_introduceThreeFragment, bundle)
        }

        binding?.btnChapter38?.setOnClickListener {
            bundle.putInt("chapterNumber", 8)  //챕터 8임을 알려줍니다.
            findNavController().navigate(R.id.action_stageThreeFragment_to_introduceThreeFragment, bundle)
        }

        binding?.btnChapter39?.setOnClickListener {
            bundle.putInt("chapterNumber", 9)  //챕터 9임을 알려줍니다.
            findNavController().navigate(R.id.action_stageThreeFragment_to_introduceThreeFragment, bundle)
        }

        binding?.btnChapter310?.setOnClickListener {
            bundle.putInt("order", 1)  //챕터10의 1번부터 10번까지를 호출하기 위한 order값입니다
            bundle.putString("restart", restart)  // 만약 챕터10 재시작이 아닐 경우, 이 안에는 null값이 들어가지만, 재시작을 할 경우 "restart"가 들어간다.
            findNavController().navigate(R.id.action_stageThreeFragment_to_stageThreeChapterTenQuizOXFragment, bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
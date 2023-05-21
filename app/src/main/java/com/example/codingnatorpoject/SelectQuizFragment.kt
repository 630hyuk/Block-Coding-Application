package com.example.codingnatorpoject

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.codingnatorpoject.databinding.FragmentSelectQuizBinding

class SelectQuizFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    var binding: FragmentSelectQuizBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSelectQuizBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mainIntent = Intent(requireActivity(), MainActivity::class.java)  //챕터의 문제를 푸는 Activity로 넘어가기 위한 Intent입니다.

        binding?.btnOneStage?.setOnClickListener {  //1스테이지
            findNavController().navigate(R.id.action_selectQuizFragment_to_stageOneFragment)
        }

        binding?.btnTwoStage?.setOnClickListener{  //2스테이지
            findNavController().navigate(R.id.action_selectQuizFragment_to_stageTwoFragment)
        }

        binding?.btnThreeStage?.setOnClickListener {  //3스테이지
            findNavController().navigate(R.id.action_selectQuizFragment_to_stageThreeFragment)
        }

        binding?.btnReturnMain?.setOnClickListener {  //다시 메인 엑티비티로 돌아갑니다.
            //startActivity(mainIntent)
            activity?.finish()  //이 문법이 해당 엑티비티를 취소하고 뒤로 가는 것입니다
            /*
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.remove(this)
                ?.commit()
            //이 문법도 해당 엑티비티를 취소하고 뒤로 가는 것입니다. 다만, 아직 애매한게 있어서 이를 주석처리 했으니, 지우지는 말아주세요
             */
        }
    }
}
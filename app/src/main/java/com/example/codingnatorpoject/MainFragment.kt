package com.example.codingnatorpoject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.codingnatorpoject.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    var binding: FragmentMainBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.btnPlay?.setOnClickListener {  //학습하러 가기를 눌렀을때
            findNavController().navigate(R.id.action_mainFragment_to_selectQuizFragment)
        }

        binding?.btnSetting?.setOnClickListener {  //환경설정을 눌렀을 때
            findNavController().navigate(R.id.action_mainFragment_to_settingFragment)
        }

        binding?.btnRanking?.setOnClickListener {  //랭킹을 눌렀을 때
            findNavController().navigate(R.id.action_mainFragment_to_rankingFragment)
        }
    }
}
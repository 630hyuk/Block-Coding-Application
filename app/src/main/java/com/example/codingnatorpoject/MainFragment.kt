package com.example.codingnatorpoject

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.codingnatorpoject.databinding.FragmentMainBinding
import com.example.codingnatorpoject.DBConnection.UserManager

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
        val selectStageIntent = Intent(requireActivity(), EducationActivity::class.java)  //챕터의 문제를 푸는 Activity로 넘어가기 위한 Intent입니다.

        binding?.btnPlay?.setOnClickListener {  //학습하러 가기를 눌렀을때
            startActivity(selectStageIntent)
        }

        binding?.btnLogout?.setOnClickListener {  //로그아웃 버튼을 눌렀을 때
            UserManager.logout(true)
            activity?.finish()  // 아마 이 곳에서 aws와의 연동이 또 필요할 것으로 보임
                                // 연동 해드렸습니다 ^^
        }

        binding?.btnRanking?.setOnClickListener {  //랭킹을 눌렀을 때
            findNavController().navigate(R.id.action_mainFragment_to_rankingFragment)
        }
    }
}
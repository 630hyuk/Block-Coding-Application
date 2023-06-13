package com.example.codingnatorpoject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.codingnatorpoject.databinding.FragmentRankingBinding

class RankingFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    lateinit var adapter: UserAdapter
    private lateinit var userList: ArrayList<User>

    var binding: FragmentRankingBinding ?= null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRankingBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userList = ArrayList()
        adapter = UserAdapter(requireContext(), userList)  //adapter는 사람 한명한명을 보관하는 장소

        binding?.recUsers?.layoutManager = LinearLayoutManager(requireContext())
        binding?.recUsers?.adapter = adapter

        //아마 이곳에서 aws와 연결하는 작업이 또 필요할 것으로 보임

        binding?.btnBackToStart?.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
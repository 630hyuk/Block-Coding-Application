package com.example.codingnatorpoject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.codingnatorpoject.databinding.FragmentLastResultBinding
import com.example.codingnatorpoject.DBConnection.User

class LastResultFragment : Fragment() {
    private var totalCorrect: Int? = null  //번들로 받아온 전체 맞은개수를 세기위한 것
    private var chapterNumber: Int? = null  //번들에서 받아온 해당 챕터

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            totalCorrect = it.getInt("totalCorrect")
            chapterNumber = it.getInt("chapterNumber")
        }
    }

    var binding: FragmentLastResultBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLastResultBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        User.updateStarAt(1, chapterNumber!!, totalCorrect!!.toByte())

        binding?.btnToStage?.setOnClickListener {
            val restart = "restart"
            val bundle = Bundle().apply {
                putString("restart", restart)
                putInt("totalCorrect", totalCorrect!!)
                putInt("chapterNumber", chapterNumber!!)
            }  //이 변수는 챕터10을 다시 실행했을 경우를 대비한 예비책입니다. 이 변수를 이용해 챕터10을 다시 클릭했을때, totalCorrect를 0으로 초기화 할 수 있습니다.
            findNavController().navigate(R.id.action_lastResultFragment_to_stageOneFragment, bundle)
            //findNavController().popBackStack()
            //이 기능을 넣어줬으니, 이제 클라우드에서 보관되는 점수는 이 LastResultFragment에서 수정되애 함
        }

        binding?.txtNumCorrect?.text = "${totalCorrect}개 맞음"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
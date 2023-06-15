package com.example.codingnatorpoject

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.codingnatorpoject.DBConnection.QuestionRepository
import com.example.codingnatorpoject.databinding.FragmentIntroduceThreeBinding

class IntroduceThreeFragment : Fragment() {
    private var chapterNumber: Int? = null
    private var totalCorrect: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            chapterNumber = it.getInt("chapterNumber")
            totalCorrect = it.getInt("totalCorrect")
        }
    }

    var binding: FragmentIntroduceThreeBinding? = null
    private val repo = QuestionRepository(activity?.applicationContext)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIntroduceThreeBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showEducation(chapterNumber!!)

        binding?.btnBack?.setOnClickListener {  //다시 메인화면으로 보내줍니다
            findNavController().popBackStack()  //해당 프래그먼트를 뒤로 보내는 역할을 해준다.
        }

        binding?.btnQuiz?.setOnClickListener {
            val bundle = Bundle().apply {
                putInt("chapterNumber", chapterNumber!!)
                putInt("totalCorrect", totalCorrect!!)  //전체 개수를 세기위한
            }
            findNavController().navigate(R.id.action_introduceThreeFragment_to_OXQuizThreeFragment, bundle)
        }
    }

    fun showEducation(cn: Int) {
        binding?.imgIntro?.setImageBitmap(repo.getIntroduceImage(3,cn))
    }
}
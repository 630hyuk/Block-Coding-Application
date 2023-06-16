package com.example.codingnatorpoject

import android.graphics.Color
import android.media.SoundPool
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.codingnatorpoject.databinding.FragmentResultTwoBinding

class ResultTwoFragment : Fragment() {
    private var myAnswer: String? = null
    private var myquestion: String? = null
    private var myExample: String? = null
    //private var myReason: String? = null
    private var chapterNumber: Int? = null
    private var quizFourComplete: Int? = null   //4지선다 퀴즈의 두 번쨰 문제인지 확인해주는 변수(즉, ox퀴즈에서 막 넘어 왔으면 이 값은 null이다)
    private var totalCorrect: Int? = null  //번들로 받아온 전체 맞은개수를 세기위한 것

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            myAnswer = it.getString("answer")
            myquestion = it.getString("question")
            myExample = it.getString("example")
            //myReason = it.getString("reason")
            chapterNumber = it.getInt("chapterNumber")
            quizFourComplete = it.getInt("quizFourComplete")

            totalCorrect = it.getInt("totalCorrect")
        }
    }

    var binding: FragmentResultTwoBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResultTwoBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val color = Color.rgb(231, 137, 137)
        val bundle = Bundle()  //몇 챕터를 선택할지 이 번들에 넣어서 알려줍니다.
        val soundPool = SoundPool.Builder().build()
        val soundIds = mutableListOf<Int>()
        val isWrong: Boolean = (myExample != null)

        if (isWrong) {  // 오답
            binding?.correctTxt?.text = "오답!"
            binding?.correctTxt?.setBackgroundColor(color)  //이렇게 rgb를 이용해 background의 색을 바꿉니다
        }
        else {  // 정답
            binding?.correctTxt?.text = "정답!"
        }

        val str1 = (if (isWrong) "<$myExample> : 오답입니다.\n\n" else "") + "\"$myquestion\"은(는) "
        val str2 = "<$myAnswer>"
        val str3 = "입니다."
        val spannable = SpannableString("$str1$str2$str3")
        spannable.setSpan(
            ForegroundColorSpan(color),
            str1.length,
            str1.length + str2.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        binding?.explainTxt?.setText(spannable, TextView.BufferType.SPANNABLE)

        binding?.btnNext?.setOnClickListener {

            bundle.putInt("chapterNumber", chapterNumber!!)
            bundle.putInt("totalCorrect", totalCorrect!!)  //맞은 개수를 번들에 넣어서 보내준다.

            when (quizFourComplete) {
                200 -> {
                    findNavController().navigate(
                        R.id.action_resultTwoFragment_to_lastResultTwoFragment,
                        bundle
                    )
                }

                100 -> {
                    bundle.putInt("quizFourComplete", 100)  //2번째 4지선다인지 확인용
                    findNavController().navigate(
                        R.id.action_resultTwoFragment_to_fourChooseQuizTwoFragment,
                        bundle
                    )
                }

                else -> {
                    findNavController().navigate(
                        R.id.action_resultTwoFragment_to_fourChooseQuizTwoFragment,
                        bundle
                    )
                }
            }
        }   // end of btnNext.setOnClickListener

        soundPool.load(requireContext(), R.raw.correctanswer, 1)
        soundPool.load(requireContext(), R.raw.wronganswer, 1)
        soundPool.setOnLoadCompleteListener { soundPool, sampleId, status ->
            if (status == 0) {
                soundIds.add(sampleId)
                if (soundIds.size == 2)
                    soundPool.play(
                        soundIds[if (isWrong) 1 else 0],
                        1.0f, 1.0f, 0, 0, 1.0f
                    )
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}
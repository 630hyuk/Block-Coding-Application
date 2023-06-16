package com.example.codingnatorpoject

import android.graphics.Color
import android.media.SoundPool
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.method.ScrollingMovementMethod
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.codingnatorpoject.DBConnection.UserManager
import com.example.codingnatorpoject.databinding.FragmentStageOneChapterTenResultBinding

class StageOneChapterTenResultFragment : Fragment() {
    //private var myAnswer: String? = null
    //private var myquestion: String? = null
    private var myExample: String? = null
    private var reason: String? = null
    private var order: Int? = null
    private var totalCorrect: Int? = null  //번들로 받아온 전체 맞은개수를 세기위한 것

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            //myAnswer = it.getString("answer")
            //myquestion = it.getString("question")
            totalCorrect = it.getInt("totalCorrect")

            myExample = it.getString("example")
            reason = it.getString("reason")
            order = it.getInt("order")
        }
    }

    var binding: FragmentStageOneChapterTenResultBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStageOneChapterTenResultBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val color = Color.rgb(231, 137, 137)
        val bundle = Bundle()  //몇 챕터를 선택할지 이 번들에 넣어서 알려줍니다.
        val soundPool = SoundPool.Builder().build()
        val soundIds = mutableListOf<Int>()
        val isWrong: Boolean = (reason != null)

        if (isWrong) {  // 오답
            val str1 = "<$myExample> : 오답입니다.\n"
            val str2 = "$reason"
            val spannable = SpannableString("$str1$str2")
            spannable.setSpan(
                ForegroundColorSpan(color),
                str1.length,
                str1.length + str2.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            binding?.txtCorrectChapterTen?.setBackgroundColor(color)
            binding?.txtCorrectChapterTen?.text = "오답!"
            binding?.txtExplainChapterTen?.setText(spannable, TextView.BufferType.SPANNABLE)
            binding?.txtExplainChapterTen?.movementMethod = ScrollingMovementMethod.getInstance()
        }
        else {  // 정답
            binding?.txtCorrectChapterTen?.text = "정답!"
            binding?.txtExplainChapterTen?.text = "다음 문제로 이동하세요"
        }

        binding?.btnNextChapterTen?.setOnClickListener {
            order = order!! + 1  //즉, 여기서 order를 하나씩 올려준다.
            bundle.putInt("order", order!!)
            bundle.putInt("totalCorrect", totalCorrect!!)  //맞은 개수를 번들에 넣어서 보내준다.

            if (order == 2) {  //챕터10의 ox가 두 문제 뿐이니, 이렇게 만들어줬습니다.
                findNavController().navigate(
                    R.id.action_stageOneChapterTenResultFragment_to_stageOneChapterTenQuizOXFragment,
                    bundle
                )
            } else if (order == 11) {  //챕터10이 다 끝났다면...
                UserManager.updateStarAt(1, 10, totalCorrect!!.toByte())   // 유저 데이터 업데이트
                bundle.putInt(
                    "chapterNumber",
                    10
                )  // LastResultFragment의 원활한 데이터 처리를 위해 10챕터라는 데이터를 전송
                findNavController().navigate(
                    R.id.action_stageOneChapterTenResultFragment_to_lastResultFragment,
                    bundle
                )
            } else {
                findNavController().navigate(
                    R.id.action_stageOneChapterTenResultFragment_to_stageOneChapterTenQuizFourFragment,
                    bundle
                )
            }
        }

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
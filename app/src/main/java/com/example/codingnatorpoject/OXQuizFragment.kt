package com.example.codingnatorpoject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.codingnatorpoject.databinding.FragmentOXQuizBinding

class OXQuizFragment : Fragment() {
    private var chapterNumber: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            chapterNumber = it.getInt("chapterNumber")
        }
    }

    var binding: FragmentOXQuizBinding? = null
    var problems =
        arrayOf( //mapOf를 사용해서 문제를 추출합니다.... 배열의 형태로 만들어줬습니다. 물론, 현재는 무작위 추출이 아니고 이 배열의 순서대로 문제가 출력되는 형식으로 했습니다.
            mapOf(
                "question" to "해당 블록은 캐릭터가 바라보는 방향으로 30만큼 움직이나요?",
                "answer" to "O",
                "example1" to "O",
                "example2" to "X"
            ),
            mapOf(
                "question" to "해당 블록은 캐릭터가 바라보는 방향이 90도가 되게 만드나요?",
                "answer" to "X",
                "example1" to "O",
                "example2" to "X"
            ),
            mapOf(
                "question" to "해당 블록은 스페이스 키를 눌렀을 때 캐릭터가 10만큼 움직이도록 만드나요?",
                "answer" to "O",
                "example1" to "O",
                "example2" to "X"
            ),
            mapOf(
                "question" to "x좌표는 좌우를 담당한다고 했어요. x좌표가 +이면 오른쪽, -면 왼쪽으로 가요. 그러면 아래 블록은 캐릭터를 어느 쪽으로 이동시킬까요",
                "answer" to "오른쪽",
                "example1" to "왼쪽",
                "example2" to "오른쪽"
            )
        )
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOXQuizBinding.inflate(inflater)
        return binding?.root
    }

    var question = ""
    var answer = ""
    var example1 = ""
    var example2 = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(chapterNumber == 1){
            showProblem(chapterNumber!!)
            binding?.imgQuestionOX?.setImageResource(R.drawable.ox1)
        }

        if(chapterNumber == 2){
            showProblem(chapterNumber!!)
            binding?.imgQuestionOX?.setImageResource(R.drawable.ox2)
        }

        if(chapterNumber == 3){
            showProblem(chapterNumber!!)
            binding?.imgQuestionOX?.setImageResource(R.drawable.ox3)
        }

        if(chapterNumber == 4){
            showProblem(chapterNumber!!)
            binding?.imgQuestionOX?.setImageResource(R.drawable.ox4)
        }

        binding?.btnO?.setOnClickListener {
            selectExample(example1, question)
        }

        binding?.btnX?.setOnClickListener {
            selectExample(example2, question)
        }
    }

    fun showProblem(pn: Int) { //problemNUmber도 파라미터로 받기(객체지향으로 만들기)
        question = problems[pn - 1]["question"].toString()  //즉, question to 머시기를 String으로 바꿔 question에 넣어줍니다.
        answer = problems[pn - 1]["answer"].toString()
        example1 = problems[pn - 1]["example1"].toString()
        example2 = problems[pn - 1]["example2"].toString()

        binding?.txtQuestion?.text = question  //위에서 만들어준 녀석들을 binding을 통해 화면에 뿌려줍니다.
        binding?.btnO?.text = example1
        binding?.btnX?.text = example2
    }

    fun selectExample(example: String, question: String) {  //이 함수는 버튼을 클릭했을 때, 사용하는 함수입니다.
        val bundle = Bundle()
        if (answer == example) {  //즉, 사용자가 입력한 값이 정답일때
            bundle.putString("answer", answer)
            bundle.putString("question", question)
            bundle.putInt("chapterNumber", chapterNumber!!)
            findNavController().navigate(R.id.action_OXQuizFragment_to_resultFragment, bundle)
        } else {  //즉, 사용자가 입력한 값이 오답일때,
            bundle.putString("example", example)
            bundle.putString("answer", answer)
            bundle.putString("question", question)
            bundle.putInt("chapterNumber", chapterNumber!!)
            findNavController().navigate(R.id.action_OXQuizFragment_to_resultFragment, bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
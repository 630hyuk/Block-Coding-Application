package com.example.codingnatorpoject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.codingnatorpoject.databinding.FragmentStageOneChapterTenQuizFourBinding

class StageOneChapterTenQuizFourFragment : Fragment() {
    private var order: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            order = it.getInt("order")
        }
    }

    var binding: FragmentStageOneChapterTenQuizFourBinding? = null
    var problems =
        arrayOf( //mapOf를 사용해서 문제를 추출합니다.... 배열의 형태로 만들어줬습니다. 물론, 현재는 무작위 추출이 아니고 이 배열의 순서대로 문제가 출력되는 형식으로 했습니다.
            mapOf( //3번문제
                "question" to "스페이스 키를 눌렀을 때 해당 스프라이트는 우리가 볼 때 어느 쪽으로 움직일까요?",
                "answer" to "오른쪽",
                "example1" to "오른쪽",
                "example2" to "왼쪽",
                "example3" to "위쪽",
                "example4" to "아래쪽"
            ),
            mapOf(  //4번문제
                "question" to "초록 깃발을 클릭하고 10초 후에 출발을 말하게 하려면, 빈칸에 알맞은 숫자는 무엇인가요?",
                "answer" to "10",
                "example1" to "5",
                "example2" to "10",
                "example3" to "15",
                "example4" to "20"
            ),
            mapOf(  //5번문제
                "question" to "민수가 라면을 끓이려고 한다. 다음 코드의 실행 결과는 무엇인가?",
                "answer" to "물",
                "example1" to "라면",
                "example2" to "물",
                "example3" to "20",
                "example4" to "10"
            ),
            mapOf(  //6번문제
                "question" to "다음 코드의 안녕과 음... 의 각각의 출력횟수의 합은?",
                "answer" to "5",
                "example1" to "5",
                "example2" to "10",
                "example3" to "15",
                "example4" to "20"
            ),
            mapOf(  //7번문제
                "question" to "육각형을 그리려면 빈칸에 어떤 값이 들어가야 적절한가?",
                "answer" to "60",
                "example1" to "30",
                "example2" to "60",
                "example3" to "90",
                "example4" to "120"
            ),
            mapOf(  //8번문제
                "question" to "해당 코드를 실행했을 때 캐릭터는 어떤 값을 말하나요?",
                "answer" to "50",
                "example1" to "0",
                "example2" to "50",
                "example3" to "100",
                "example4" to "150"
            ),
            mapOf(  //9번문제
                "question" to "초록깃발을 눌러 다음 코드를 실행시키면 캐릭터는 어떤 값을 말하는가?",
                "answer" to "가",
                "example1" to "가",
                "example2" to "가위",
                "example3" to "나",
                "example4" to "나무"
            ),
            mapOf(  //10번문제
                "question" to "초록깃발을 한번 누르고 ‘1’을 3번 눌렀을 때, ‘안경’은 어떤 값을 가지고 있나요?",
                "answer" to "55",
                "example1" to "10",
                "example2" to "25",
                "example3" to "40",
                "example4" to "55"
            )
        )
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStageOneChapterTenQuizFourBinding.inflate(inflater)
        return binding?.root
    }

    var question = ""
    var answer = ""
    var example1 = ""
    var example2 = ""
    var example3 = ""
    var example4 = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(order == 3){
            showProblem(order!!)
            binding?.imgChapter10Four?.setImageResource(R.drawable.chapterten3)
        }

        if(order == 4){
            showProblem(order!!)
            binding?.imgChapter10Four?.setImageResource(R.drawable.chapterten4)
        }

        if(order == 5){
            showProblem(order!!)
            binding?.imgChapter10Four?.setImageResource(R.drawable.chapterten5)
        }

        if(order == 6){
            showProblem(order!!)
            binding?.imgChapter10Four?.setImageResource(R.drawable.chapterten6)
        }

        if(order == 7){
            showProblem(order!!)
            binding?.imgChapter10Four?.setImageResource(R.drawable.chapterten7)
        }

        if(order == 8){
            showProblem(order!!)
            binding?.imgChapter10Four?.setImageResource(R.drawable.chapterten8)
        }

        if(order == 9){
            showProblem(order!!)
            binding?.imgChapter10Four?.setImageResource(R.drawable.chapterten9)
        }

        if(order == 10){
            showProblem(order!!)
            binding?.imgChapter10Four?.setImageResource(R.drawable.chapterten10)
        }

        binding?.btnChapter10Ex1?.setOnClickListener {
            selectExample(example1, question)
        }

        binding?.btnChapter10Ex2?.setOnClickListener {
            selectExample(example2, question)
        }

        binding?.btnChapter10Ex3?.setOnClickListener {
            selectExample(example3, question)
        }

        binding?.btnChapter10Ex4?.setOnClickListener {
            selectExample(example4, question)
        }
    }

    fun showProblem(pn: Int) { //problemNUmber도 파라미터로 받기(객체지향으로 만들기)
        question = problems[pn - 3]["question"].toString()  //즉, question to 머시기를 String으로 바꿔 question에 넣어줍니다.
        answer = problems[pn - 3]["answer"].toString()
        example1 = problems[pn - 3]["example1"].toString()
        example2 = problems[pn - 3]["example2"].toString()
        example3 = problems[pn - 3]["example3"].toString()
        example4 = problems[pn - 3]["example4"].toString()

        binding?.txtChapter10FourQuestion?.text = question  //위에서 만들어준 녀석들을 binding을 통해 화면에 뿌려줍니다.
        binding?.btnChapter10Ex1?.text = example1
        binding?.btnChapter10Ex2?.text = example2
        binding?.btnChapter10Ex3?.text = example3
        binding?.btnChapter10Ex4?.text = example4
    }

    fun selectExample(example: String, question: String) {  //이 함수는 버튼을 클릭했을 때, 사용하는 함수입니다.
        val bundle = Bundle()
        if (answer == example) {  //즉, 사용자가 입력한 값이 정답일때
            bundle.putString("answer", answer)
            bundle.putString("question", question)
            bundle.putInt("order", order!!)
            findNavController().navigate(R.id.action_stageOneChapterTenQuizFourFragment_to_stageOneChapterTenResultFragment, bundle)
        } else {  //즉, 사용자가 입력한 값이 오답일때,
            bundle.putString("example", example)
            bundle.putString("answer", answer)
            bundle.putString("question", question)
            bundle.putInt("order", order!!)
            findNavController().navigate(R.id.action_stageOneChapterTenQuizFourFragment_to_stageOneChapterTenResultFragment, bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
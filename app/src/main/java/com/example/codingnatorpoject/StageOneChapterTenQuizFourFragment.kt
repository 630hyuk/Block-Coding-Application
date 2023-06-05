package com.example.codingnatorpoject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
//import com.davemorrissey.labs.subscaleview.ImageSource
import com.example.codingnatorpoject.DBConnection.DatabaseConnector
import com.example.codingnatorpoject.DBConnection.ImageAccessor
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
                "question" to "스페이스 키를 눌렀을 때 고양이는 우리가 볼 때 어느 쪽으로 움직일까요?",
                "answer" to "오른쪽",
                "example1" to "오른쪽",
                "example2" to "왼쪽",
                "example3" to "위쪽",
                "example4" to "아래쪽",
                "reason" to "정답은 \"오른쪽\", x좌표는 스프라이트의 좌우 위치를 결정하기 때문이다."
            ),
            mapOf(  //4번문제
                "question" to "기차를 타고 여행을 떠나려고 해요. 초록 깃발을 클릭하고 10초 후에 출발을 말하게 하려면, 빈칸에 알맞은 숫자는 무엇인가요?",
                "answer" to "10",
                "example1" to "5",
                "example2" to "10",
                "example3" to "15",
                "example4" to "20",
                "reason" to "정답은 \"10\", 10초 후에 말하기 블록이 작동하여야하므로, 1초 기다리기 블록이 10번 수행되는 것이 옳다."
            ),
            mapOf(  //5번문제
                "question" to "민수가 라면을 끓이려고 해요. 다음 블록들의 실행 결과는 무엇인가요?",
                "answer" to "물",
                "example1" to "라면",
                "example2" to "물",
                "example3" to "20",
                "example4" to "10",
                "reason" to "정답은 \"물\", 만약(if)에 영향을 주는 값은 x좌표이므로, x좌표만 고려하면 x:20으로 이동되었고, 10만큼 바꾸기를 통해 x:30으로 이동되었다. 그 상태에서 판별하므로 x>50 이 거짓임을 알 수 있다."
            ),
            mapOf(  //6번문제
                "question" to "영희가 자기소개를 하려고 고민 중이에요. 아래 블록의 ‘안녕!’과 ‘고민 중...’ 의 각각의 생각하고 말한 횟수를 더하면 얼마인가요?",
                "answer" to "15",
                "example1" to "5",
                "example2" to "10",
                "example3" to "15",
                "example4" to "20",
                "reason" to "정답은 \"15\", ‘안녕!’은 변수가 5를 초과할 때만 출력되고, 처음 변수가 0으로 초기화되었으며 ‘1만큼 바꾸기’가 판별 블록 앞에 있으므로 변수가 6,7,8,9,10 일 때 출력된다. ‘고민 중...’은 변수에 상관없이 출력되므로 10번 출력되어 총 15번 출력이 이루어진다."
            ),
            mapOf(  //7번문제
                "question" to "육각형을 그리려고 하고 있어요. 빈칸에 어떤 값이 들어가야 알맞게 그릴 수 있을까요?",
                "answer" to "60",
                "example1" to "30",
                "example2" to "60",
                "example3" to "90",
                "example4" to "120",
                "reason" to "정답은 \"60\", 육각형의 한 각의 크기는 120도 이고 해당 각을 만들어 내기 위해서는 직선인 180도에서 60도만큼 돌아주면 120도가 되기 때문이다."
            ),
            mapOf(  //8번문제
                "question" to "고양이가 마이크를 옮기고 있어요. 아래 블록을 실행했을 때 고양이는 어떤 숫자를 말하나요?",
                "answer" to "50",
                "example1" to "0",
                "example2" to "50",
                "example3" to "100",
                "example4" to "150",
                "reason" to "정답은 \"50\", x좌표는 10, 마이크는 30+10으로 40을 가지고 있기에 두 변수의 합은 50이다."
            ),
            mapOf(  //9번문제
                "question" to "고양이가 말하려고 해요. 초록 깃발을 눌러 아래 블록을 실행시키면 고양이는 어떤 값을 말하하나요?",
                "answer" to "사",
                "example1" to "사",
                "example2" to "과",
                "example3" to "나",
                "example4" to "무",
                "reason" to "정답은 \"사\", 결합한 ‘사과 나무’의 1번째 글자는 ‘사’이므로 조건문이 참으로 성립하여 ‘사’가 출력된다."
            ),
            mapOf(  //10번문제
                "question" to "안경 개수를 세는 중이에요. 초록 깃발을 한번 누르고 ‘1’키를 3번 눌렀을 때, ‘안경’을 몇 개 가지고 있나요?",
                "answer" to "55",
                "example1" to "10",
                "example2" to "25",
                "example3" to "40",
                "example4" to "55",
                "reason" to "정답은 \"55\", 초기값이 10, ‘1’을 한번 누를 때마다 15씩 증가하므로, 최종적으로 55를 가진다."
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
    var reason = ""

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
        reason = problems[pn - 3]["reason"].toString()  //틀린 이유를 알려줘야 하므로

        binding?.txtChapter10FourQuestion?.text = question  //위에서 만들어준 녀석들을 binding을 통해 화면에 뿌려줍니다.
        binding?.btnChapter10Ex1?.text = example1
        binding?.btnChapter10Ex2?.text = example2
        binding?.btnChapter10Ex3?.text = example3
        binding?.btnChapter10Ex4?.text = example4

        // set doUpload to true, for upload local question data
        val doUpload = true
        if (doUpload) {
            DatabaseConnector(context).uploadQuestion(
                "1-10-$order", question,
                problems[pn - 3]["hint"].toString(), false,
                arrayOf(example1, example2, example3, example4), answer, /*reason*/"Something to explain?", ImageAccessor(context).getFileUrl(1, 10, order!!))
        }
    }

    fun selectExample(example: String, question: String) {  //이 함수는 버튼을 클릭했을 때, 사용하는 함수입니다.
        val bundle = Bundle()
        if (answer == example) {  //즉, 사용자가 입력한 값이 정답일때
            //bundle.putString("answer", answer)
            //bundle.putString("question", question)
            bundle.putInt("order", order!!)
            findNavController().navigate(R.id.action_stageOneChapterTenQuizFourFragment_to_stageOneChapterTenResultFragment, bundle)
        } else {  //즉, 사용자가 입력한 값이 오답일때,
            bundle.putString("example", example)
            //bundle.putString("answer", answer)
            //bundle.putString("question", question)
            bundle.putString("reason", reason)  //틀린 이유 알리기
            bundle.putInt("order", order!!)
            findNavController().navigate(R.id.action_stageOneChapterTenQuizFourFragment_to_stageOneChapterTenResultFragment, bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
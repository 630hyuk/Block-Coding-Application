package com.example.codingnatorpoject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.codingnatorpoject.DBConnection.DatabaseConnector
import com.example.codingnatorpoject.DBConnection.ImageAccessor
import com.example.codingnatorpoject.DBConnection.QuestionRepository
import com.example.codingnatorpoject.databinding.FragmentStageThreeChapterTenQuizFourBinding

class StageThreeChapterTenQuizFourFragment : Fragment() {
    private var order: Int? = null
    private var totalCorrect: Int? = null  //번들로 받아온 전체 맞은개수를 세기위한 것

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            order = it.getInt("order")
            totalCorrect = it.getInt("totalCorrect")
        }
    }

    var binding: FragmentStageThreeChapterTenQuizFourBinding? = null
    private val repo = QuestionRepository(activity?.applicationContext)

    /*
    var problems =
        arrayOf( //mapOf를 사용해서 문제를 추출합니다.... 배열의 형태로 만들어줬습니다. 물론, 현재는 무작위 추출이 아니고 이 배열의 순서대로 문제가 출력되는 형식으로 했습니다.
            mapOf( //3번문제
                "question" to "버스를 타고 학교로 가려고 해요. 초록 깃발을 클릭하고 30초 후에 출발을 말하게 하려면, 빈칸에 알맞은 숫자는 무엇인가요?",
                "answer" to "30",
                "example1" to "5",
                "example2" to "15",
                "example3" to "25",
                "example4" to "30",
                "reason" to "정답 30, 30초 후에 말하기 블록이 작동하여야 하므로, 1초 기다리기 블록이 30번 수행되는 것이 옳다. "
            ),
            mapOf(  //4번문제
                "question" to "철수가 음료수에 넣을 얼음 개수를 세는 중이에요. ‘얼음’을 총 몇 개 넣어야 하나요?",
                "answer" to "25",
                "example1" to "10",
                "example2" to "25",
                "example3" to "40",
                "example4" to "55",
                "reason" to "정답 25, 음료수가 5개, 음료수 1개당 얼음이 5개가 들어가므로, 25개가 필요하다."
            ),
            mapOf(  //5번문제
                "question" to "우산을 1개씩 총 35개를 세어보고 있어요. 빈칸에는 어떤 숫자가 들어가야 마지막까지 우산을 세어볼 수 있을까요?",
                "answer" to "35",
                "example1" to "5",
                "example2" to "15",
                "example3" to "25",
                "example4" to "35",
                "reason" to "정답 35, 챕터10에서 5번문제에 정답의 이유가 안나와있음"
            ),
            mapOf(  //6번문제
                "question" to "사각형을 그리려고 하고 있어요. 빈칸에 어떤 값이 들어가야 알맞게 그릴 수 있을까요?",
                "answer" to "90",
                "example1" to "30",
                "example2" to "60",
                "example3" to "90",
                "example4" to "120",
                "reason" to "정답 90, 사각형의 한 각의 크기는 90도이고 해당 각을 만들어 내기 위해서는 직선인 180도에서 90도만큼 돌아주면 90도가 되기 때문이다.)"
            ),
            mapOf(  //7번문제
                "question" to "초록 깃발을 클릭하고 화살표 키를 최대한 적은 횟수로 눌러서 x좌표를 70, y좌표를 -30으로 만들고 싶어요. 화살표 키를 전부 몇 번 누르면 될까요?",
                "answer" to "10",
                "example1" to "2",
                "example2" to "5",
                "example3" to "7",
                "example4" to "10",
                "reason" to "정답 : ④ 10, 오른쪽 화살표 7번, 아래쪽 화살표 3번이 가장 적은 횟수로 목표 좌표로 이동가능하기 때문이에요."
            ),
            mapOf(  //8번문제
                "question" to "철수가 시험을 못 보고 슬퍼하고 있네요. 철수의 수학 성적은 몇 점일까요?",
                "answer" to "50",
                "example1" to "50",
                "example2" to "70",
                "example3" to "80",
                "example4" to "90",
                "reason" to "정답 : 50, 국어+수학+과학이 120을 넘지 않으려면 수학이 59점 이하이어야 하기 때문이에요."
            ),
            mapOf(  //9번문제
                "question" to "영수가 그림 장식으로 넣을 꽃잎 개수를 세는 중이에요. ‘꽃잎’은 총 몇 장 인가요?",
                "answer" to "50",
                "example1" to "10",
                "example2" to "25",
                "example3" to "40",
                "example4" to "50",
                "reason" to "정답 ④ 50, 꽃이 10송이, 꽃 1송이당 꽃잎이 5장 추가되므로, 총 50장이다."
            ),
            mapOf(  //10번문제
                "question" to "수아가 과일가게에 들려 장을 보고 있어요. 사과는 12개, 감은 20개, 딸기는 15개를 사려고 하는데, 과일은 전부 몇 개 샀나요?",
                "answer" to "47",
                "example1" to "10",
                "example2" to "25",
                "example3" to "40",
                "example4" to "47",
                "reason" to "정답 : ④ 47, 사과+감+딸기의 개수는 총 47개이기 때문이에요."
            )
        )
    */

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStageThreeChapterTenQuizFourBinding.inflate(inflater)
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

        showProblem(3, 10, order!!)

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

    fun showProblem(stage: Int, chapter: Int, pn: Int) { //problemNUmber도 파라미터로 받기(객체지향으로 만들기)
        val problem = repo.get(stage, chapter, pn)

        question = problem["content"].toString()  //즉, question to 머시기를 String으로 바꿔 question에 넣어줍니다.
        answer = problem["answer"].toString()
        example1 = problem["cand1"].toString()
        example2 = problem["cand2"].toString()
        example3 = problem["cand3"].toString()
        example4 = problem["cand4"].toString()
        reason = problem["explanation"].toString()  //틀린 이유를 알려줘야 하므로

        binding?.txtChapter10FourQuestion?.text = question  //위에서 만들어준 녀석들을 binding을 통해 화면에 뿌려줍니다.
        binding?.btnChapter10Ex1?.text = example1
        binding?.btnChapter10Ex2?.text = example2
        binding?.btnChapter10Ex3?.text = example3
        binding?.btnChapter10Ex4?.text = example4

        binding?.imgChapter10Four?.setImageBitmap(repo.getImage(stage, chapter, pn))
    }

    fun selectExample(example: String, question: String) {  //이 함수는 버튼을 클릭했을 때, 사용하는 함수입니다.
        val bundle = Bundle()
        if (answer == example) {  //즉, 사용자가 입력한 값이 정답일때
            totalCorrect = totalCorrect!! + 1  //맞은 개수를 올려준다.
            //bundle.putString("answer", answer)
            //bundle.putString("question", question)
            bundle.putInt("order", order!!)
            bundle.putInt("totalCorrect", totalCorrect!!)  //맞은 개수를 번들에 넣어서 보내준다.
            findNavController().navigate(R.id.action_stageThreeChapterTenQuizFourFragment_to_stageThreeChapterTenResultFragment, bundle)
        } else {  //즉, 사용자가 입력한 값이 오답일때,
            bundle.putString("example", example)
            //bundle.putString("answer", answer)
            //bundle.putString("question", question)
            bundle.putString("reason", reason)  //틀린 이유 알리기
            bundle.putInt("order", order!!)
            bundle.putInt("totalCorrect", totalCorrect!!)  //맞은 개수를 번들에 넣어서 보내준다.
            findNavController().navigate(R.id.action_stageThreeChapterTenQuizFourFragment_to_stageThreeChapterTenResultFragment, bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
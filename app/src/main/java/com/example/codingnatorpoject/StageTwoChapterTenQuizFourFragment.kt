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
import com.example.codingnatorpoject.databinding.FragmentStageTwoChapterTenQuizFourBinding

class StageTwoChapterTenQuizFourFragment : Fragment() {
    private var order: Int? = null
    private var totalCorrect: Int? = null  //번들로 받아온 전체 맞은개수를 세기위한 것

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            order = it.getInt("order")
            totalCorrect = it.getInt("totalCorrect")
        }
    }

    var binding: FragmentStageTwoChapterTenQuizFourBinding? = null
    private val repo = QuestionRepository(activity?.applicationContext)

    /*
    var problems =
        arrayOf( //mapOf를 사용해서 문제를 추출합니다.... 배열의 형태로 만들어줬습니다. 물론, 현재는 무작위 추출이 아니고 이 배열의 순서대로 문제가 출력되는 형식으로 했습니다.
            mapOf(  //5번문제
                "question" to "우산을 1개씩 총 15개를 세어보고 있어요. 빈칸에는 어떤 숫자가 들어가야 마지막까지 우산을 세어볼 수 있을까요?",
                "answer" to "15",
                "example1" to "5",
                "example2" to "10",
                "example3" to "15",
                "example4" to "20",
                "reason" to "정답 : 15, 우산을 1개씩 세는 과정을 총 15개를 세려면 15번 반복해야 하기 때문이에요. "
            ),
            mapOf(  //6번문제
                "question" to "캐릭터가 오른쪽으로 50만큼 이동하고, 아래쪽으로 30만큼 이동한 후, 왼쪽으로 20만큼 이동하는 코드를 작성하려면, 어떤 순서로 블록을 이어붙어야 할까요?",
                "answer" to "㉠-㉡-㉢",
                "example1" to "㉠-㉡-㉢",
                "example2" to "㉢-㉠-㉡",
                "example3" to "㉡-㉢-㉠",
                "example4" to "㉠-㉢-㉡",
                "reason" to "정답 : ㉠-㉡-㉢, 문제에서 이동하는 순서를 맞추면 그대로 ㉠-㉡-㉢이기 때문이에요."
            ),
            mapOf(  //7번문제
                "question" to "초록 깃발을 클릭하고 화살표 키를 최대한 적은 횟수로 눌러서 x좌표를 50, y좌표를 -20으로 만들고 싶어요. 화살표 키를 전부 몇 번 누르면 될까요?",
                "answer" to "7",
                "example1" to "2",
                "example2" to "5",
                "example3" to "7",
                "example4" to "10",
                "reason" to "정답 : 7, 오른쪽 화살표 5번, 아래쪽 화살표 2번이 가장 적은 횟수로 해당 좌표로 이동가능하기 때문이에요."
            ),
            mapOf(  //8번문제
                "question" to "민수가 시험을 잘 보고 기뻐하고 있네요. 민수의 국어 성적은 몇 점일까요?",
                "answer" to "97",
                "example1" to "58",
                "example2" to "72",
                "example3" to "89",
                "example4" to "97",
                "reason" to "정답 : 97, 국어+수학+과학이 270을 넘으려면 국어가 96점 이상이어야 하기 때문이에요."
            ),
            mapOf(  //9번문제
                "question" to "지원이가 과일가게에 들려 장을 보고 있어요. 사과는 4개, 감은 5개, 딸기는 10개를 사려고 하는데, 과일은 전부 몇 개 샀나요?",
                "answer" to "19",
                "example1" to "9",
                "example2" to "14",
                "example3" to "19",
                "example4" to "25",
                "reason" to "정답 : 19, 사과+감+딸기의 개수는 총 19개이기 때문이에요.)"
            ),
            mapOf(  //10번문제
                "question" to "소풍을 나와서 오늘 점심은 도시락이에요. 초록 깃발을 눌러서 배부르게 먹으면 배부름에 들어가는 숫자는 얼마일까요?",
                "answer" to "40",
                "example1" to "0",
                "example2" to "20",
                "example3" to "40",
                "example4" to "50",
                "reason" to "(정답 : 40, 도시락 신호를 보내고 배부름이 20이 더해지고, 김밥 신호를 보내고 배부름이 20이 더해지므로 40이에요."
            )
        )
     */

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStageTwoChapterTenQuizFourBinding.inflate(inflater)
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

        showProblem(2, 10, order!!)

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
        example1 = problem["example1"].toString()
        example2 = problem["example2"].toString()
        example3 = problem["example3"].toString()
        example4 = problem["example4"].toString()
        reason = problem["reason"].toString()  //틀린 이유를 알려줘야 하므로

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
            findNavController().navigate(R.id.action_stageTwoChapterTenQuizFourFragment_to_stageTwoChapterTenResultFragment, bundle)
        } else {  //즉, 사용자가 입력한 값이 오답일때,
            bundle.putString("example", example)
            //bundle.putString("answer", answer)
            //bundle.putString("question", question)
            bundle.putString("reason", reason)  //틀린 이유 알리기
            bundle.putInt("order", order!!)
            bundle.putInt("totalCorrect", totalCorrect!!)  //맞은 개수를 번들에 넣어서 보내준다.
            findNavController().navigate(R.id.action_stageTwoChapterTenQuizFourFragment_to_stageTwoChapterTenResultFragment, bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
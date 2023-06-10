package com.example.codingnatorpoject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.codingnatorpoject.DBConnection.DatabaseConnector
import com.example.codingnatorpoject.DBConnection.ImageAccessor
import com.example.codingnatorpoject.databinding.FragmentStageTwoChapterTenQuizOXBinding

var totalCorrect_two = 0  //전체 맞은 개수를 세기위한 전역변수입니다. 일단 OX가 다 끝나고 four로 이동시에 bundle에 넣어줍니다

class StageTwoChapterTenQuizOXFragment : Fragment() {
    private var order: Int? = null
    private var restart : String? = null //LastResultFragment에서 재시작 신호를 받았을때

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            order = it.getInt("order")
            restart = it.getString("restart")
        }
    }

    var binding: FragmentStageTwoChapterTenQuizOXBinding? = null
    var problems =
        arrayOf( //mapOf를 사용해서 문제를 추출합니다.... 배열의 형태로 만들어줬습니다. 물론, 현재는 무작위 추출이 아니고 이 배열의 순서대로 문제가 출력되는 형식으로 했습니다.
            mapOf(
                "question" to "다음 블록은 캐릭터를 무작위 위치로 이동시키나요?",
                "answer" to "O",
                "example1" to "O",
                "example2" to "X",
                "reason" to "정답 : O, ‘무작위 위치’로 이동하기 블록이므로 캐릭터를 무작위 위치로 이동시켜요."
            ),
            mapOf(
                "question" to "다음 블록은 캐릭터를 안 보이게 해주나요?",
                "answer" to "X",
                "example1" to "O",
                "example2" to "X",
                "reason" to "정답 : X, 캐릭터를 안 보이게 해주는 블록은 ‘숨기기’ 블록이에요."
            ),
            mapOf(
                "question" to "이 블록은 묻고 기다리기 블록하고 같이 쓰이나요? \n참고 블록이 묻고 기다리기 블록이에요.",
                "answer" to "O",
                "example1" to "O",
                "example2" to "X",
                "reason" to "정답 : O, 대답은 묻고 기다리기 블록이 작동할 때 나오는 칸에 입력한 값이에요."
            ),
            mapOf(
                "question" to "캐릭터가 ‘숨기기’ 상태일 때, 이 블록은 작동하나요?",
                "answer" to "X",
                "example1" to "O",
                "example2" to "X",
                "reason" to "정답 : X, 캐릭터가 보이지 않아서 원래 있던 자리를 클릭하여도 반응하지 않아요."
            )
        )
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStageTwoChapterTenQuizOXBinding.inflate(inflater)
        return binding?.root
    }

    var question = ""
    var answer = ""
    var example1 = ""
    var example2 = ""
    var reason = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(restart == "restart"){
            totalCorrect_two = 0 //LastResultFragment에서 재시작으로 넘어왔을 경우, totalCorrect 다시 0로 초기화 합니다.
        }

        if(order == 1){
            showProblem(order!!)
            //binding?.imgChapter10OX?.setImageResource(R.drawable.chapterten1)
        }

        if(order == 2){
            showProblem(order!!)
            //binding?.imgChapter10OX?.setImageResource(R.drawable.chapterten2)
        }

        if(order == 3){
            showProblem(order!!)
            //binding?.imgChapter10OX?.setImageResource(R.drawable.chapterten2)
        }

        if(order == 4){
            showProblem(order!!)
            //binding?.imgChapter10OX?.setImageResource(R.drawable.chapterten2)
        }

        binding?.btnChapter10O?.setOnClickListener {
            selectExample(example1, question)
        }

        binding?.btnChapter10X?.setOnClickListener {
            selectExample(example2, question)
        }
    }

    fun showProblem(pn: Int) { //problemNUmber도 파라미터로 받기(객체지향으로 만들기)
        question = problems[pn - 1]["question"].toString()  //즉, question to 머시기를 String으로 바꿔 question에 넣어줍니다.
        answer = problems[pn - 1]["answer"].toString()
        example1 = problems[pn - 1]["example1"].toString()
        example2 = problems[pn - 1]["example2"].toString()
        reason = problems[pn - 1]["reason"].toString()  //틀린 이유를 알려줘야 하므로

        binding?.txtChapter10OXQuestion?.text = question  //위에서 만들어준 녀석들을 binding을 통해 화면에 뿌려줍니다.
        binding?.btnChapter10O?.text = example1
        binding?.btnChapter10X?.text = example2

        // set doUpload to true, for upload local question data
        val doUpload = true
        if (doUpload) {
            DatabaseConnector(context).uploadQuestion(
                "1-10-$order", question,
                problems[pn - 1]["hint"].toString(), true,
                arrayOf(example1, example2), answer, reason, ImageAccessor(context).getFileUrl(1, 10, order!!))
        }
    }

    fun selectExample(example: String, question: String) {  //이 함수는 버튼을 클릭했을 때, 사용하는 함수입니다.
        val bundle = Bundle()
        if (answer == example) {  //즉, 사용자가 입력한 값이 정답일때
            totalCorrect_two += 1
            bundle.putInt("totalCorrect", totalCorrect_two)  //맞은 개수를 번들에 넣어서 보내준다.
            //bundle.putString("answer", answer)
            //bundle.putString("question", question)
            bundle.putInt("order", order!!)
            findNavController().navigate(R.id.action_stageTwoChapterTenQuizOXFragment_to_stageTwoChapterTenResultFragment, bundle)
        } else {  //즉, 사용자가 입력한 값이 오답일때,
            bundle.putInt("totalCorrect", totalCorrect_two)  //맞은 개수를 번들에 넣어서 보내준다.
            bundle.putString("example", example)
            //bundle.putString("answer", answer)
            //bundle.putString("question", question)
            bundle.putString("reason", reason)  //틀린 이유 알리기
            bundle.putInt("order", order!!)
            findNavController().navigate(R.id.action_stageTwoChapterTenQuizOXFragment_to_stageTwoChapterTenResultFragment, bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
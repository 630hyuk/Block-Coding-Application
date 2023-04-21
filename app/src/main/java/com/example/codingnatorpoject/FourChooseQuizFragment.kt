package com.example.codingnatorpoject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.codingnatorpoject.databinding.FragmentFourChooseQuizBinding

class FourChooseQuizFragment : Fragment() {
    private var chapterNumber: Int? = null
    private var quizFourComplete: Int? = null   //4지선다 퀴즈의 두 번쨰 문제인지 확인해주는 변수
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            chapterNumber = it.getInt("chapterNumber")
            quizFourComplete = it.getInt("quizFourComplete")
        }
    }

    var binding: FragmentFourChooseQuizBinding? = null
    var problems =
        arrayOf( //mapOf를 사용해서 문제를 추출합니다.... 배열의 형태로 만들어줬습니다. 물론, 현재는 무작위 추출이 아니고 이 배열의 순서대로 문제가 출력되는 형식으로 했습니다.
            mapOf(
                "question" to "해당 블록들은 캐릭터가 바라보는 방향으로 총 얼마만큼 움직이게 하나요?",
                "answer" to "40",
                "example1" to "10",
                "example2" to "20",
                "example3" to "30",
                "example4" to "40"
            ),
            mapOf(
                "question" to "해당 블록들이 한 번씩 실행되면 처음 위치에서 얼마나 이동하나요? (단, 90도, -90도는 각각 아래 그림의 화살표 방향이다.)",
                "answer" to "0",
                "example1" to "0",
                "example2" to "10",
                "example3" to "20",
                "example4" to "30"
            ),
            mapOf(
                "question" to "해당 블록은 캐릭터가 바라보는 방향이 몇 도를 바라보도록 만드나요?",
                "answer" to "90도",
                "example1" to "0도",
                "example2" to "90도",
                "example3" to "60도",
                "example4" to "180도"
            ),
            mapOf(
                "question" to "해당 블록은 캐릭터가 바라보는 방향이 몇 도를 바라보도록 만드나요?",
                "answer" to "150도",
                "example1" to "90도",
                "example2" to "120도",
                "example3" to "150도",
                "example4" to "0도"
            ),
            mapOf(
                "question" to "해당 블록은 어떤 키를 눌러야 캐릭터가 90도 방향을 보게 만드나요?",
                "answer" to "a",
                "example1" to "a",
                "example2" to "b",
                "example3" to "c",
                "example4" to "d"
            ),
            mapOf(
                "question" to "이 중 어떤 키를 눌러야 30 만큼 움직일까요? 아래 블록들을 보고 판단해보세요.",
                "answer" to "3",
                "example1" to "1",
                "example2" to "2",
                "example3" to "3",
                "example4" to "4"
            ),
            mapOf(
                "question" to "아래 블록을 실행시키면 캐릭터의 x좌표는 얼마가 될까요?",
                "answer" to "10",
                "example1" to "0",
                "example2" to "5",
                "example3" to "10",
                "example4" to "20"
            ),
            mapOf(
                "question" to "아래 블록을 실행시키고 캐릭터의 x좌표와 y좌표의 합은 얼마가 될까요?",
                "answer" to "0",
                "example1" to "0",
                "example2" to "5",
                "example3" to "10",
                "example4" to "20"
            )
        )
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFourChooseQuizBinding.inflate(inflater)
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

        if(chapterNumber == 1){
            if(quizFourComplete == 100){
                showProblem(chapterNumber!!*2)
                binding?.imgQuestionFour?.setImageResource(R.drawable.choosefour2)
            }
            else{
                showProblem((chapterNumber!!*2)-1)
                binding?.imgQuestionFour?.setImageResource(R.drawable.choosefour1)
            }
        }

        if(chapterNumber == 2){
            if(quizFourComplete == 100){
                showProblem(chapterNumber!!*2)
                binding?.imgQuestionFour?.setImageResource(R.drawable.choosefour4)
            }
            else{
                showProblem((chapterNumber!!*2)-1)
                binding?.imgQuestionFour?.setImageResource(R.drawable.choosefour3)
            }
        }

        if(chapterNumber == 3){
            if(quizFourComplete == 100){
                showProblem(chapterNumber!!*2)
                binding?.imgQuestionFour?.setImageResource(R.drawable.choosefour6)
            }
            else{
                showProblem((chapterNumber!!*2)-1)
                binding?.imgQuestionFour?.setImageResource(R.drawable.choosefour5)
            }
        }

        if(chapterNumber == 4){
            if(quizFourComplete == 100){
                showProblem(chapterNumber!!*2)
                binding?.imgQuestionFour?.setImageResource(R.drawable.choosefour8)
            }
            else{
                showProblem((chapterNumber!!*2)-1)
                binding?.imgQuestionFour?.setImageResource(R.drawable.choosefour7)
            }
        }

        binding?.btnEx1?.setOnClickListener {
            selectExample(example1, question)
        }

        binding?.btnEx2?.setOnClickListener {
            selectExample(example2, question)
        }

        binding?.btnEx3?.setOnClickListener {
            selectExample(example3, question)
        }

        binding?.btnEx4?.setOnClickListener {
            selectExample(example4, question)
        }
    }

    fun showProblem(pn: Int) { //problemNUmber도 파라미터로 받기(객체지향으로 만들기)
        question = problems[pn - 1]["question"].toString()  //즉, question to 머시기를 String으로 바꿔 question에 넣어줍니다.
        answer = problems[pn - 1]["answer"].toString()
        example1 = problems[pn - 1]["example1"].toString()
        example2 = problems[pn - 1]["example2"].toString()
        example3 = problems[pn - 1]["example3"].toString()
        example4 = problems[pn - 1]["example4"].toString()

        binding?.fourQuestionTextView?.text = question  //위에서 만들어준 녀석들을 binding을 통해 화면에 뿌려줍니다.
        binding?.btnEx1?.text = example1
        binding?.btnEx2?.text = example2
        binding?.btnEx3?.text = example3
        binding?.btnEx4?.text = example4
    }

    fun selectExample(example: String, question: String) {  //이 함수는 버튼을 클릭했을 때, 사용하는 함수입니다.
        val bundle = Bundle()
        if (answer == example) {  //즉, 사용자가 입력한 값이 정답일때
            bundle.putString("answer", answer)
            bundle.putString("question", question)
            bundle.putInt("chapterNumber", chapterNumber!!)
            if(quizFourComplete == 100){
                bundle.putInt("quizFourComplete", 200) //한 챕터가 다 끝남을 의미
            }
            else{
                bundle.putInt("quizFourComplete", 100) //4지선다는 2문제씩 제출하므로, 1문제를 출제하면 바로 이것을 bundle로 보내줍니다.
            }
            //bundle.putInt("quizFourComplete", 100) //4지선다는 2문제씩 제출하므로, 1문제를 출제하면 바로 이것을 bundle로 보내줍니다.
            findNavController().navigate(R.id.action_fourChooseQuizFragment_to_resultFragment, bundle)
        } else {  //즉, 사용자가 입력한 값이 오답일때,
            bundle.putString("example", example)
            bundle.putString("answer", answer)
            bundle.putString("question", question)
            bundle.putInt("chapterNumber", chapterNumber!!)
            if(quizFourComplete == 100){
                bundle.putInt("quizFourComplete", 200) //한 챕터가 다 끝남을 의미
            }
            else{
                bundle.putInt("quizFourComplete", 100) //4지선다는 2문제씩 제출하므로, 1문제를 출제하면 바로 이것을 bundle로 보내줍니다.
            }
            //bundle.putInt("quizFourComplete", 100) //4지선다는 2문제씩 제출하므로, 1문제를 출제하면 바로 이것을 bundle로 보내줍니다.
            findNavController().navigate(R.id.action_fourChooseQuizFragment_to_resultFragment, bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
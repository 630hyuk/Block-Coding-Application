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
            mapOf( //챕터1
                "question" to "해당 블록들은 캐릭터가 바라보는 방향으로 총 얼마만큼 움직이게 하나요?",
                "answer" to "40",
                "example1" to "10",
                "example2" to "20",
                "example3" to "30",
                "example4" to "40"
            ),
            mapOf( //챕터1
                "question" to "해당 블록들이 한 번씩 실행되면 처음 위치에서 얼마나 이동하나요?",
                "answer" to "0",
                "example1" to "0",
                "example2" to "10",
                "example3" to "20",
                "example4" to "30"
            ),
            mapOf( //챕터2
                "question" to "해당 블록은 캐릭터가 바라보는 방향이 몇 도를 바라보도록 만드나요?",
                "answer" to "90도",
                "example1" to "0도",
                "example2" to "90도",
                "example3" to "60도",
                "example4" to "180도"
            ),
            mapOf( //챕터2
                "question" to "해당 블록은 캐릭터가 바라보는 방향이 몇 도를 바라보도록 만드나요?",
                "answer" to "150도",
                "example1" to "90도",
                "example2" to "120도",
                "example3" to "150도",
                "example4" to "0도"
            ),
            mapOf( //챕터3
                "question" to "해당 블록은 어떤 키를 눌러야 캐릭터가 90도 방향을 보게 만드나요?",
                "answer" to "a",
                "example1" to "a",
                "example2" to "b",
                "example3" to "c",
                "example4" to "d"
            ),
            mapOf( //챕터3
                "question" to "이 중 어떤 키를 눌러야 30 만큼 움직일까요? 아래 블록들을 보고 판단해보세요.",
                "answer" to "3",
                "example1" to "1",
                "example2" to "2",
                "example3" to "3",
                "example4" to "4"
            ),
            mapOf(//챕터4
                "question" to "아래 블록을 실행시키면 캐릭터의 x좌표는 얼마가 될까요?",
                "answer" to "10",
                "example1" to "0",
                "example2" to "5",
                "example3" to "10",
                "example4" to "20"
            ),
            mapOf( //챕터4
                "question" to "아래 블록을 실행시키고 캐릭터의 x좌표와 y좌표의 합은 얼마가 될까요?",
                "answer" to "0",
                "example1" to "0",
                "example2" to "5",
                "example3" to "10",
                "example4" to "20"
            ),
            mapOf(  //문제의 챕터5
                "question" to "다음 중 배경 추가 버튼에 해당하는 것을 고르세요",
                "answer" to "2",
                "example1" to "1",
                "example2" to "2",
                "example3" to "3",
                "example4" to "4"
            ),
            mapOf( //문제의 챕터5
                "question" to "초록 깃발(시작) 버튼을 누르면 블록 아래의 코드가 실행되는데, 도중에 멈추고 싶으면 어느 버튼을 눌러야 할까요?",
                "answer" to "3",
                "example1" to "1",
                "example2" to "2",
                "example3" to "3",
                "example4" to "4"
            ),
            mapOf(  //문제의 챕터6
                "question" to "해당 코드가 전부 실행되고 나서 캐릭터는 어떤 말풍선을 출력하고 있나요?",
                "answer" to "50 생각하기",
                "example1" to "10 말하기",
                "example2" to "50 말하기",
                "example3" to "10 생각하기",
                "example4" to "50 생각하기"
            ),
            mapOf( //챕터6
                "question" to "해당 코드가 실행될 때, “점심”은 “맛있는” 이 사라지고 몇 초 뒤에 말하나요?",
                "answer" to "0",
                "example1" to "0",
                "example2" to "1",
                "example3" to "2",
                "example4" to "3"
            ),
            mapOf(  //챕터7
                "question" to "해당 블록이 전부 실행된 후 ‘라면’ 변수는 얼마로 설정되나요?",
                "answer" to "20",
                "example1" to "0",
                "example2" to "10",
                "example3" to "20",
                "example4" to "30"
            ),
            mapOf( //챕터7
                "question" to "해당 블록이 전부 실행된 후 ‘스마트폰’ 변수는 얼마로 설정되나요?",
                "answer" to "40",
                "example1" to "10",
                "example2" to "20",
                "example3" to "30",
                "example4" to "40"
            ),
            mapOf(  //챕터8
                "question" to "아래 블록은 어떤 값을 가지고 있을까요?",
                "answer" to "4",
                "example1" to "1",
                "example2" to "2",
                "example3" to "3",
                "example4" to "4"
            ),
            mapOf( //챕터8
                "question" to "초록깃발을 누른 후, 해당 조건을 만족하게 하려면 빈칸에 어떤 값을 써줘야 할까요?",
                "answer" to "40",
                "example1" to "10",
                "example2" to "20",
                "example3" to "30",
                "example4" to "40"
            ),
            mapOf(  //챕터9
                "question" to "초록 깃발을 클릭하고 실행이 완료되면, 캐릭터의 x좌표는 어디일까요?",
                "answer" to "100",
                "example1" to "10",
                "example2" to "50",
                "example3" to "100",
                "example4" to "200"
            ),
            mapOf( //챕터9
                "question" to "초록깃발을 클릭하면 캐릭터가 출력하는 값은 뭔가요?",
                "answer" to "30",
                "example1" to "0",
                "example2" to "10",
                "example3" to "20",
                "example4" to "30"
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

        if(chapterNumber == 5){
            if(quizFourComplete == 100){
                showProblem(chapterNumber!!*2)
                binding?.imgQuestionFour?.setImageResource(R.drawable.choosefour10)
            }
            else{
                showProblem((chapterNumber!!*2)-1)
                binding?.imgQuestionFour?.setImageResource(R.drawable.choosefour9)
            }
        }

        if(chapterNumber == 6){
            if(quizFourComplete == 100){
                showProblem(chapterNumber!!*2)
                binding?.imgQuestionFour?.setImageResource(R.drawable.choosefour12)
            }
            else{
                showProblem((chapterNumber!!*2)-1)
                binding?.imgQuestionFour?.setImageResource(R.drawable.choosefour11)
            }
        }

        if(chapterNumber == 7){
            if(quizFourComplete == 100){
                showProblem(chapterNumber!!*2)
                binding?.imgQuestionFour?.setImageResource(R.drawable.choosefour14)
            }
            else{
                showProblem((chapterNumber!!*2)-1)
                binding?.imgQuestionFour?.setImageResource(R.drawable.choosefour13)
            }
        }

        if(chapterNumber == 8){
            if(quizFourComplete == 100){
                showProblem(chapterNumber!!*2)
                binding?.imgQuestionFour?.setImageResource(R.drawable.choosefour16)
            }
            else{
                showProblem((chapterNumber!!*2)-1)
                binding?.imgQuestionFour?.setImageResource(R.drawable.choosefour15)
            }
        }

        if(chapterNumber == 9){
            if(quizFourComplete == 100){
                showProblem(chapterNumber!!*2)
                binding?.imgQuestionFour?.setImageResource(R.drawable.choosefour18)
            }
            else{
                showProblem((chapterNumber!!*2)-1)
                binding?.imgQuestionFour?.setImageResource(R.drawable.choosefour17)
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
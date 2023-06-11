package com.example.codingnatorpoject

import android.app.AlertDialog
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.codingnatorpoject.DBConnection.DatabaseConnector
import com.example.codingnatorpoject.DBConnection.ImageAccessor
import com.example.codingnatorpoject.databinding.FragmentFourChooseQuizThreeBinding

class FourChooseQuizThreeFragment : Fragment() {
    private var chapterNumber: Int? = null
    private var quizFourComplete: Int? = null   //4지선다 퀴즈의 두 번쨰 문제인지 확인해주는 변수
    private var totalCorrect: Int? = null  //번들로 받아온 전체 맞은개수를 세기위한 것

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            chapterNumber = it.getInt("chapterNumber")
            quizFourComplete = it.getInt("quizFourComplete")

            totalCorrect = it.getInt("totalCorrect")
        }
    }

    var binding: FragmentFourChooseQuizThreeBinding? = null

    var problems =
        arrayOf( //mapOf를 사용해서 문제를 추출합니다.... 배열의 형태로 만들어줬습니다. 물론, 현재는 무작위 추출이 아니고 이 배열의 순서대로 문제가 출력되는 형식으로 했습니다.
            mapOf( //챕터1
                "question" to "해당 블록이 전부 실행되고 캐릭터의 x와 y를 더하면 얼마일까요?",
                "answer" to "40",
                "example1" to "10",
                "example2" to "20",
                "example3" to "30",
                "example4" to "40",
                "hint" to "코드의 마지막 블록을 살펴보세요."
            ),
            mapOf( //챕터1
                "question" to "해당 블록이 전부 실행되고 캐릭터의 x와 y를 더하면 얼마일까요?",
                "answer" to "40",
                "example1" to "0",
                "example2" to "20",
                "example3" to "40",
                "example4" to "60",
                "hint" to "코드의 마지막 블록을 살펴보세요."
            ),
            mapOf( //챕터2
                "question" to "민수가 공중제비를 돌고 있어요. 총 몇 바퀴를 돌게 되나요?",
                "answer" to "2바퀴",
                "example1" to "1바퀴",
                "example2" to "2바퀴",
                "example3" to "3바퀴",
                "example4" to "4바퀴",
                "hint" to "360도가 1바퀴에요."
            ),
            mapOf( //챕터2
                "question" to "영희가 줄넘기를 하는 중이에요. 총 몇 번 뛰었나요?",
                "answer" to "25",
                "example1" to "10",
                "example2" to "20",
                "example3" to "25",
                "example4" to "30",
                "hint" to "점프를 몇 번 뛰었는지는 몇 번 반복했는지를 보면 알 수 있어요."
            ),
            mapOf( //챕터3
                "question" to "민수가 서울로 여행을 가고 싶어해요. 민수는 서울 신호를 받으면 어떤 표를 구하나요?",
                "answer" to "기차표",
                "example1" to "비행기표",
                "example2" to "기차표",
                "example3" to "고속버스표",
                "example4" to "배표",
                "hint" to "‘서울’ 신호와 연결된 블록을 찾아보세요."
            ),
            mapOf( //챕터3
                "question" to "민수가 지도를 보고 목적지로 향하고 있어요. 민수가 도착하고 x와 y를 더하면 얼마일까요?",
                "answer" to "60",
                "example1" to "0",
                "example2" to "20",
                "example3" to "40",
                "example4" to "60",
                "hint" to "‘이동하기’ 신호는 ‘이동하기’ 신호를 받는 모든 블록이 작동해요. "
            ),
            mapOf(//챕터4
                "question" to "피아노의 소리를 추가하고 싶어요. ‘A Piano’를 추가하려면 어떤 블록을 사용하여야 할까요?",
                "answer" to "③",
                "example1" to "①",
                "example2" to "②",
                "example3" to "③",
                "example4" to "④",
                "hint" to "재생 블록은 칸 안에 있는 소리를 재생해요."
            ),
            mapOf( //챕터4
                "question" to "스피커의 음량(소리) 크기를 변화시키고 싶어요. 해당 블록들이 실행되면 음량의 크기는 어떻게 될까요?",
                "answer" to "0",
                "example1" to "0",
                "example2" to "20",
                "example3" to "40",
                "example4" to "50",
                "hint" to "음량이 100% 라는건 음량이 100이라는 뜻이에요."
            ),
            mapOf(  //챕터5
                "question" to "민수가 목적지에 가고 싶어해요. 알맞은 길을 알려주세요.",
                "answer" to "x방향 반복 : 10, y방향 반복 : 10",
                "example1" to "x방향 반복 : 0, y방향 반복 : 0",
                "example2" to "x방향 반복 : 0, y방향 반복 : 10",
                "example3" to "x방향 반복 : 10, y방향 반복 : 0",
                "example4" to "x방향 반복 : 10, y방향 반복 : 10",
                "hint" to "조건을 만족시키는 반복 횟수를 구해보세요."
            ),
            mapOf( //챕터5
                "question" to "철수가 시험을 잘 봐서 신나하고 있네요. 철수의 점수가 얼마일지 예상해보세요.",
                "answer" to "국어 : 100, 수학 : 100",
                "example1" to "국어 : 100, 수학 : 100",
                "example2" to "국어 70, 수학 : 40",
                "example3" to "국어 40, 수학 : 70",
                "example4" to "국어 40, 수학 : 40 ",
                "hint" to "‘그리고’ 블록은 양쪽 조건을 모두 만족해야 해요."
            ),
            mapOf(  //챕터6
                "question" to "음료수를 사러 편의점에 가고 있어요. 2번, 5번, 7번 편의점에 음료수가 있을 때, 총 몇 개를 사서 가지고 오나요?",
                "answer" to "3",
                "example1" to "0",
                "example2" to "1",
                "example3" to "2",
                "example4" to "3",
                "hint" to "음료수를 사는 편의점의 개수를 세어보세요.",
            ),
            mapOf( //챕터6
                "question" to "새가 원숭이에게 이름을 물어보고 있어요! 대답을 ‘원숭이’라고 했으면 마지막 블록의 ‘대답의 길이’는 얼마일까요?",
                "answer" to "3",
                "example1" to "3",
                "example2" to "2",
                "example3" to "1",
                "example4" to "0",
                "hint" to "대답은 입력한 ‘원숭이’가 들어가 있어요.",
            ),
            mapOf( //챕터7
                "question" to "고양이가 뛰어가고 있어요. 초록 깃발을 클릭하고 실행이 완료되면, 고양이의 x좌표는 어디까요? ",
                "answer" to "200",
                "example1" to "10",
                "example2" to "50",
                "example3" to "100",
                "example4" to "200",
                "hint" to "조건을 보면, 반복 블록은 x좌표가 처음에 0이고 10만큼 x좌표가 200이 될 때 까지 더해줬어요.",
                //"reason" to "왜냐하면 첫 ‘스마트폰’ 만큼 바꾸기 블록은 +10만큼 바꿔주고, 두 번째 ‘스마트폰’ 만큼 바꾸기 블록은 +20만큼 바꿔주기 때문"
            ),
            mapOf(  //챕터7
                "question" to "철수가 숙제를 하는 중이에요. 숙제를 전부 다 해결하는 시간은 얼마나 걸릴까요?",
                "answer" to "150",
                "example1" to "10",
                "example2" to "50",
                "example3" to "100",
                "example4" to "150",
                "hint" to "반복이 몇 번 될지 생각해보세요."
            ),
            mapOf(  //챕터8
                "question" to "Abby가 춤을 추고 있네요. 어느 버튼을 눌러야 Abby-d 동작을 보여줄 수 있을까요?",
                "answer" to "d",
                "example1" to "a",
                "example2" to "b",
                "example3" to "c",
                "example4" to "d",
                "hint" to "바꾸기 블록의 안에 있는 모양을 잘 살펴보세요"
            ),
            mapOf( //챕터8
                "question" to "영희가 걸어가고 있어요. x 좌표가 400인 지점까지 걸어가고 싶어하네요. 두 빈칸에 들어갈 알맞은 숫자를 골라보세요. ",
                "answer" to "20, 20",
                "example1" to "10, 10",
                "example2" to "20, 20",
                "example3" to "30, 30",
                "example4" to "40, 40",
                "hint" to "□ 만큼 바꾸기와 □ 번 반복하기의 곱이 400이 되는 값이에요."
            ),
            mapOf(  //챕터9
                "question" to "음료수를 먹고 싶어서 장을 보러 나왔어요. 블록이 전부 실행된 후 ‘음료수’ 개수는 얼마로 정해지나요?",
                "answer" to "20",
                "example1" to "0",
                "example2" to "10",
                "example3" to "20",
                "example4" to "30",
                "hint" to "변수를 ~ 만큼 바꾸기는 해당하는 빈칸의 값만큼 더해주면 된답니다."
            ),
            mapOf( //챕터9
                "question" to "자동차가 몇 대가 있는지 세어보는 중이에요. 블록이 전부 실행된 후 ‘자동차’ 개수는 얼마나 있나요?",
                "answer" to "160",
                "example1" to "20",
                "example2" to "40",
                "example3" to "80",
                "example4" to "160",
                "hint" to "변수를 ~ 만큼 바꾸기는 해당하는 빈칸의 값만큼 더해주면 된답니다. 단, 변수를 변수만큼 바꾸기가 끝난 상태로 마지막 블록이 실행되므로 주의!\\n"
            )
        )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFourChooseQuizThreeBinding.inflate(inflater)
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
                //binding?.imgQuestionOX?.setImageResource(R.drawable.choosefour2)
            }
            else{
                showProblem((chapterNumber!!*2)-1)
                //binding?.imgQuestionOX?.setImageResource(R.drawable.choosefour1)
            }
        }

        if(chapterNumber == 2){
            if(quizFourComplete == 100){
                showProblem(chapterNumber!!*2)
                //binding?.imgQuestionOX?.setImageResource(R.drawable.choosefour2)
            }
            else{
                showProblem((chapterNumber!!*2)-1)
                //binding?.imgQuestionOX?.setImageResource(R.drawable.choosefour2)
            }
        }

        if(chapterNumber == 3){
            if(quizFourComplete == 100){
                showProblem(chapterNumber!!*2)
                //binding?.imgQuestionOX?.setImageResource(R.drawable.choosefour2)
            }
            else{
                showProblem((chapterNumber!!*2)-1)
                //binding?.imgQuestionOX?.setImageResource(R.drawable.choosefour2)
            }
        }

        if(chapterNumber == 4){
            if(quizFourComplete == 100){
                showProblem(chapterNumber!!*2)
                //binding?.imgQuestionOX?.setImageResource(R.drawable.choosefour2)
            }
            else{
                showProblem((chapterNumber!!*2)-1)
                //binding?.imgQuestionOX?.setImageResource(R.drawable.choosefour2)
            }
        }

        if(chapterNumber == 5){
            if(quizFourComplete == 100){
                showProblem(chapterNumber!!*2)
                //binding?.imgQuestionOX?.setImageResource(R.drawable.choosefour2)
            }
            else{
                showProblem((chapterNumber!!*2)-1)
                //binding?.imgQuestionOX?.setImageResource(R.drawable.choosefour2)
            }
        }

        if(chapterNumber == 6){
            if(quizFourComplete == 100){
                showProblem(chapterNumber!!*2)
                //binding?.imgQuestionOX?.setImageResource(R.drawable.choosefour2)
            }
            else{
                showProblem((chapterNumber!!*2)-1)
                //binding?.imgQuestionOX?.setImageResource(R.drawable.choosefour2)
            }
        }

        if(chapterNumber == 7){
            if(quizFourComplete == 100){
                showProblem(chapterNumber!!*2)
                //binding?.imgQuestionOX?.setImageResource(R.drawable.choosefour2)
            }
            else{
                showProblem((chapterNumber!!*2)-1)
                //binding?.imgQuestionOX?.setImageResource(R.drawable.choosefour2)
            }
        }

        if(chapterNumber == 8){
            if(quizFourComplete == 100){
                showProblem(chapterNumber!!*2)
                //binding?.imgQuestionOX?.setImageResource(R.drawable.choosefour2)
            }
            else{
                showProblem((chapterNumber!!*2)-1)
                //binding?.imgQuestionOX?.setImageResource(R.drawable.choosefour2)
            }
        }

        if(chapterNumber == 9){
            if(quizFourComplete == 100){
                showProblem(chapterNumber!!*2)
                //binding?.imgQuestionOX?.setImageResource(R.drawable.choosefour2)
            }
            else{
                showProblem((chapterNumber!!*2)-1)
                //binding?.imgQuestionOX?.setImageResource(R.drawable.choosefour2)
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

        binding?.btnHintFour?.setOnClickListener {
            if(quizFourComplete == 100){
                showHintBox(chapterNumber!!*2)
            }
            else{
                showHintBox((chapterNumber!!*2)-1)
            }
        }
    }

    fun showProblem(pn: Int) { //problemNUmber도 파라미터로 받기(객체지향으로 만들기)
        question = problems[pn - 1]["question"].toString()  //즉, question to 머시기를 String으로 바꿔 question에 넣어줍니다.
        answer = problems[pn - 1]["answer"].toString()
        example1 = problems[pn - 1]["example1"].toString()
        example2 = problems[pn - 1]["example2"].toString()
        example3 = problems[pn - 1]["example3"].toString()
        example4 = problems[pn - 1]["example4"].toString()
        //reason = problems[pn - 1]["reason"].toString()  //유저가 퀴즈를 틀렸을때, 그 틀린 이유를 알려주는 퀴즈문제들이 몇몇 있었습니다. 그들을 위한 변수입니다.

        binding?.fourQuestionTextView?.text = question  //위에서 만들어준 녀석들을 binding을 통해 화면에 뿌려줍니다.
        binding?.fourQuestionTextView?.movementMethod = ScrollingMovementMethod.getInstance() //이렇게 qustion텍스트도 스크롤이 가능해집니다.
        binding?.btnEx1?.text = example1
        binding?.btnEx2?.text = example2
        binding?.btnEx3?.text = example3
        binding?.btnEx4?.text = example4

    }

    fun selectExample(example: String, question: String) {  //이 함수는 버튼을 클릭했을 때, 사용하는 함수입니다.
        val bundle = Bundle()
        if (answer == example) {  //즉, 사용자가 입력한 값이 정답일때
            totalCorrect = totalCorrect!! + 1  //맞은 개수를 올려준다.
            bundle.putString("answer", answer)
            bundle.putString("question", question)
            bundle.putInt("chapterNumber", chapterNumber!!)

            bundle.putInt("totalCorrect", totalCorrect!!)  //맞은 개수를 번들에 넣어서 보내준다.

            if(quizFourComplete == 100){
                bundle.putInt("quizFourComplete", 200) //한 챕터가 다 끝남을 의미
            }
            else{
                bundle.putInt("quizFourComplete", 100) //4지선다는 2문제씩 제출하므로, 1문제를 출제하면 바로 이것을 bundle로 보내줍니다.
            }
            //bundle.putInt("quizFourComplete", 100) //4지선다는 2문제씩 제출하므로, 1문제를 출제하면 바로 이것을 bundle로 보내줍니다.
            findNavController().navigate(R.id.action_fourChooseQuizThreeFragment_to_resultThreeFragment, bundle)
        } else {  //즉, 사용자가 입력한 값이 오답일때,
            bundle.putString("example", example)
            bundle.putString("answer", answer)
            bundle.putString("question", question)
            //bundle.putString("reason", reason)  //틀린 이유가 따로 나와있는경우
            bundle.putInt("chapterNumber", chapterNumber!!)

            bundle.putInt("totalCorrect", totalCorrect!!)  //맞은 개수를 번들에 넣어서 보내준다.

            if(quizFourComplete == 100){
                bundle.putInt("quizFourComplete", 200) //한 챕터가 다 끝남을 의미
            }
            else{
                bundle.putInt("quizFourComplete", 100) //4지선다는 2문제씩 제출하므로, 1문제를 출제하면 바로 이것을 bundle로 보내줍니다.
            }
            //bundle.putInt("quizFourComplete", 100) //4지선다는 2문제씩 제출하므로, 1문제를 출제하면 바로 이것을 bundle로 보내줍니다.
            findNavController().navigate(R.id.action_fourChooseQuizThreeFragment_to_resultThreeFragment, bundle)
        }
    }

    fun showHintBox(pn: Int){  //힌트박스를 보여주기 위한 함수입니다.
        val alertDialog = AlertDialog.Builder(this.context)
            .setTitle("힌트")
            .setMessage(problems[pn - 1]["hint"].toString())
            .setNeutralButton("닫기", null)
            .create()
        alertDialog.show()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
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
import com.example.codingnatorpoject.DBConnection.QuestionRepository
import com.example.codingnatorpoject.databinding.FragmentFourChooseQuizTwoBinding

class FourChooseQuizTwoFragment : Fragment() {
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

    var binding: FragmentFourChooseQuizTwoBinding? = null
    private val repo = QuestionRepository(activity?.applicationContext)

    /*
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
                "answer" to "60",
                "example1" to "0",
                "example2" to "20",
                "example3" to "40",
                "example4" to "60",
                "hint" to "코드의 마지막 블록을 살펴보세요."
            ),
            mapOf( //챕터2
                "question" to "물고기가 어항 배경을 돌아다니는 중이에요. 다음 중 벽에 닿았을 때 물고기가 뒤집히지 않고 자연스럽게 방향을 바꾸는 알맞은 블록은 무엇인가요?",
                "answer" to "③",
                "example1" to "①",
                "example2" to "②",
                "example3" to "③",
                "example4" to "④",
                "hint" to "회전방식이 ‘회전하기’인 경우 캐릭터가 뒤집혀요."
            ),
            mapOf( //챕터2
                "question" to "해당 블록들은 캐릭터가 바라보는 방향이 몇 도를 바라보도록 만들고, 캐릭터 이미지는 돌아가나요?",
                "answer" to "모른다, 안 돌아간다.",
                "example1" to "90도, 안 돌아간다.",
                "example2" to "0도, 돌아간다.",
                "example3" to "90도, 돌아간다.",
                "example4" to "0도",
                "hint" to "실행할 때 마우스 포인터의 위치를 알려주지 않아서 처음 방향을 몰라요."
            ),
            mapOf( //챕터3
                "question" to "새한테 소리를 추가하고 싶어요. ‘Bird’를 추가하려면 어떤 블록을 사용하여야 할까요?",
                "answer" to "②",
                "example1" to "①",
                "example2" to "②",
                "example3" to "③",
                "example4" to "④",
                "hint" to "재생 블록은 칸 안에 있는 소리를 재생해요."
            ),
            mapOf( //챕터3
                "question" to "스피커의 음량(소리) 크기를 변화시키고 싶어요. 해당 블록들이 실행되면 음량의 크기는 어떻게 될까요?",
                "answer" to "70",
                "example1" to "100",
                "example2" to "90",
                "example3" to "80",
                "example4" to "70",
                "hint" to "음량이 100% 라는건 음량이 100이라는 뜻이에요."
            ),
            mapOf(//챕터4
                "question" to "Abby가 춤을 추고 있네요. 어느 버튼을 눌러야 Abby-b 동작을 보여줄 수 있을까요?",
                "answer" to "b",
                "example1" to "a",
                "example2" to "b",
                "example3" to "c",
                "example4" to "d",
                "hint" to "바꾸기 블록의 안에 있는 모양을 잘 살펴보세요."
            ),
            mapOf( //챕터4
                "question" to "철수가 걸어가고 있어요. x 좌표가 100인 지점까지 걸어가고 싶어하네요. 두 빈칸에 들어갈 알맞은 숫자를 골라보세요.",
                "answer" to "10, 10",
                "example1" to "10, 10",
                "example2" to "20, 20",
                "example3" to "30, 30",
                "example4" to "40, 40",
                "hint" to "□ 만큼 바꾸기와 □ 번 반복하기의 곱이 100이 되는 값이에요."
            ),
            mapOf(  //챕터5
                "question" to "철수가 음료수를 사러 가고 싶어하네요. 철수는 어떤 신호를 받아야 움직이나요?",
                "answer" to "음료수",
                "example1" to "서울",
                "example2" to "기차",
                "example3" to "장갑",
                "example4" to "음료수",
                "hint" to "움직이기 블록이 어떤 신호 블록이랑 연결되어있나요?"
            ),
            mapOf( //챕터5
                "question" to "민수가 지도를 보고 목적지로 향하고 있어요. 민수가 도착하고 x와 y를 더하면 얼마일까요?",
                "answer" to "50",
                "example1" to "0",
                "example2" to "20",
                "example3" to "30",
                "example4" to "50",
                "hint" to "‘이동하기’ 신호는 ‘이동하기’ 신호를 받는 모든 블록이 작동해요."
            ),
            mapOf(  //챕터6
                "question" to "영희가 목적지에 가고 싶어해요. 알맞은 길을 알려주세요.",
                "answer" to "x : 10, y : 10",
                "example1" to "x : 0, y : 0",
                "example2" to "x : 0, y : 10",
                "example3" to "x : 10, y : 0",
                "example4" to "x : 10, y : 10",
                "hint" to "아래 조건을 만족해야 목적지에 도착해요.",
                //"reason" to "왜냐하면 마지막에 출력된 것이 50 생각하기이므로"
            ),
            mapOf( //챕터6
                "question" to "철수가 시험을 망쳐서 시무룩해져 있네요. 철수의 점수가 얼마일지 예상해보세요.",
                "answer" to "국어 : 40, 수학 : 40",
                "example1" to "국어 : 100, 수학 : 100",
                "example2" to "국어 : 70, 수학 : 40",
                "example3" to "국어 : 40, 수학 : 70",
                "example4" to "국어 : 40, 수학 : 40",
                "hint" to "그리고 블록은 양쪽 조건을 모두 만족해야 해요.",
                //"reason" to "왜냐하면 “맛있는“이 나오기 시작하고 사라질 때 까지의 시간이 2초인 것이고, 사라지고 나서 중간에 ~초 기다리기 같은 블록이 없으므로 점심”은 “맛있는” 이 사라지고 0초 뒤에 말해요"
            ),
            mapOf(  //챕터7
                "question" to "아이스크림을 사러 가고 있어요. 초록 깃발을 클릭하고 스페이스 키를 두 번 누르면 아이스크림의 개수는 몇 개인가요?",
                "answer" to "3",
                "example1" to "0",
                "example2" to "1",
                "example3" to "2",
                "example4" to "3",
                "hint" to "~ 까지 기다리기 블록은 조건을 만족할 때까지 기다려요."
            ),
            mapOf( //챕터7
                "question" to "새가 고양이에게 이름을 물어보고 있어요! 대답을 ‘고양이’라고 했으면 마지막 블록의 ‘대답의 길이’는 얼마일까요?",
                "answer" to "3",
                "example1" to "3",
                "example2" to "2",
                "example3" to "1",
                "example4" to "0",
                "hint" to "대답은 입력한 ‘고양이’가 들어가 있어요.",
                //"reason" to "왜냐하면 첫 ‘스마트폰’ 만큼 바꾸기 블록은 +10만큼 바꿔주고, 두 번째 ‘스마트폰’ 만큼 바꾸기 블록은 +20만큼 바꿔주기 때문"
            ),
            mapOf(  //챕터8
                "question" to "고양이가 파티에 가서 화려한 조명을 받고 있어요. 고양이가 번쩍번쩍하게 색깔이 바뀌게 하려면 어떤 블록을 사용해야 하나요?",
                "answer" to "③",
                "example1" to "①",
                "example2" to "②",
                "example3" to "③",
                "example4" to "④",
                "hint" to "이 중에 색깔을 바꾸는 블록은 하나에요"   //이거 본래는 사진이었음
            ),
            mapOf( //챕터8
                "question" to "고양이가 약을 많이 먹어서 크기가 2배로 커져 버렸어요. 다시 줄어들게 하려면 어떤 숫자를 넣어야 할까요?",
                "answer" to "100",
                "example1" to "10",
                "example2" to "50",
                "example3" to "70",
                "example4" to "100",
                "hint" to "캐릭터의 원래 크기는 100%에요."
            ),
            mapOf(  //챕터9
                "question" to "고양이가 숨어서 이동하고 있다가 나뭇가지를 밟아 소리가 나서 들키고 말았어요. 고양이가 들킨 x좌표 지점은 어디일까요? ",
                "answer" to "12",
                "example1" to "0",
                "example2" to "12",
                "example3" to "24",
                "example4" to "48",
                "hint" to "얼마나 이동한 뒤에 ‘나뭇가지’ 소리가 재생되었는지 생각해보아요."
            ),
            mapOf( //챕터9
                "question" to "고양이가 다시 숨으려고 하네요. 고양이는 몇 초 뒤에 발견되나요?",
                "answer" to "30",
                "example1" to "0",
                "example2" to "6",
                "example3" to "30",
                "example4" to "60",
                "hint" to "5초 기다리기를 6번 반복해요!"
            )
        )
    */

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFourChooseQuizTwoBinding.inflate(inflater)
        return binding?.root
    }

    var question = ""
    var answer = ""
    var example1 = ""
    var example2 = ""
    var example3 = ""
    var example4 = ""
    var hint = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showProblem(2, chapterNumber!!, if(quizFourComplete == 100) 3 else 2)

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
            showHintBox(hint)
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
        hint = problem["hint"]?: "No hint."
        //reason = problems[pn - 1]["reason"].toString()  //유저가 퀴즈를 틀렸을때, 그 틀린 이유를 알려주는 퀴즈문제들이 몇몇 있었습니다. 그들을 위한 변수입니다.

        binding?.fourQuestionTextView?.text = question  //위에서 만들어준 녀석들을 binding을 통해 화면에 뿌려줍니다.
        binding?.fourQuestionTextView?.movementMethod = ScrollingMovementMethod.getInstance() //이렇게 qustion텍스트도 스크롤이 가능해집니다.
        binding?.btnEx1?.text = example1
        binding?.btnEx2?.text = example2
        binding?.btnEx3?.text = example3
        binding?.btnEx4?.text = example4

        binding?.imgQuestionFour?.setImageBitmap(repo.getImage(stage, chapter, pn))

        if(chapter == 6){
            binding?.btnEx1?.textSize = 17F
            binding?.btnEx2?.textSize = 17F
            binding?.btnEx3?.textSize = 17F
            binding?.btnEx4?.textSize = 17F
        }
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
            findNavController().navigate(R.id.action_fourChooseQuizTwoFragment_to_resultTwoFragment, bundle)
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
            findNavController().navigate(R.id.action_fourChooseQuizTwoFragment_to_resultTwoFragment, bundle)
        }
    }

    fun showHintBox(hint: String){  //힌트박스를 보여주기 위한 함수입니다.
        val alertDialog = AlertDialog.Builder(this.context)
            .setTitle("힌트")
            .setMessage(hint)
            .setNeutralButton("닫기", null)
            .create()
        alertDialog.show()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
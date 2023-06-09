package com.example.codingnatorpoject

import android.app.AlertDialog
import android.graphics.Bitmap
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.codingnatorpoject.DBConnection.QuestionRepository
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
    private val repo =
        QuestionRepository(activity?.applicationContext)
    /*
    var problems =

        arrayOf( //mapOf를 사용해서 문제를 추출합니다.... 배열의 형태로 만들어줬습니다. 물론, 현재는 무작위 추출이 아니고 이 배열의 순서대로 문제가 출력되는 형식으로 했습니다.
            mapOf( //챕터1
                "question" to "해당 블록들은 캐릭터가 바라보는 방향으로 총 얼마만큼 움직이게 하나요?",
                "answer" to "40",
                "example1" to "10",
                "example2" to "20",
                "example3" to "30",
                "example4" to "40",
                "hint" to "10만큼 움직이기가 몇 개 있는지 세어보세요"
            ),
            mapOf( //챕터1
                "question" to "고양이가 움직이고 싶어해요. 아래 블록들이 한 번씩 실행되면 처음 위치에서 얼마나 이동하나요?",
                "answer" to "0",
                "example1" to "0",
                "example2" to "10",
                "example3" to "20",
                "example4" to "30",
                "hint" to "90도, -90도는 각각 아래 그림의 화살표 방향이랍니다"
            ),
            mapOf( //챕터2
                "question" to "해당 블록은 캐릭터가 바라보는 방향이 몇 도를 바라보도록 만드나요?",
                "answer" to "90도",
                "example1" to "0도",
                "example2" to "90도",
                "example3" to "60도",
                "example4" to "180도",
                "hint" to "12시 방향(0도)에서 시계방향으로 90도만큼 돌아가요. 즉, 3시 방향으로 바뀐답니다"
            ),
            mapOf( //챕터2
                "question" to "고양이가 돌고 있어요. 아래 블록들은 고양이가 바라보는 방향이 몇 도를 바라보도록 만드나요?",
                "answer" to "150도",
                "example1" to "90도",
                "example2" to "120도",
                "example3" to "150도",
                "example4" to "0도",
                "hint" to "15도만큼 2번 돌았어요. 처음 120도에서 15도만큼 2번 더해보세요"
            ),
            mapOf( //챕터3
                "question" to "고양이가 돌고 싶어해요. 어떤 키를 눌러야 고양이가 90도 방향을 보게 만드나요?",
                "answer" to "a",
                "example1" to "a",
                "example2" to "b",
                "example3" to "c",
                "example4" to "d",
                "hint" to "□ 키를 눌렀을 때는 □ 키를 누르면 그 아래의 블록들이 작동한답니다"
            ),
            mapOf( //챕터3
                "question" to "고양이가 움직이고 싶어해요. 어떤 키를 눌러야 고양이가 30 만큼 움직일까요?",
                "answer" to "3",
                "example1" to "1",
                "example2" to "2",
                "example3" to "3",
                "example4" to "4",
                "hint" to "□ 키를 눌렀을 때는 □ 키를 누르면 그 아래의 블록들이 작동한답니다"
            ),
            mapOf(//챕터4
                "question" to "강아지가 움직이려고 해요. 아래 블록을 실행시키면 강아지의 x좌표는 얼마가 될까요?",
                "answer" to "10",
                "example1" to "0",
                "example2" to "5",
                "example3" to "10",
                "example4" to "20",
                "hint" to "좌표를 ~ 만큼 바꾸기는 해당하는 빈칸의 값만큼 더해주면 된답니다"
            ),
            mapOf( //챕터4
                "question" to "여우가 움직이려고 해요. 아래 블록을 실행시키고 여우의 x좌표와 y좌표의 합은 얼마가 될까요?",
                "answer" to "0",
                "example1" to "0",
                "example2" to "5",
                "example3" to "10",
                "example4" to "20",
                "hint" to "마지막에 실행되는 두 개의 블록을 잘 살펴보세요"
            ),
            mapOf(  //챕터5
                "question" to "다음 중 배경 추가 버튼에 해당하는 것을 고르세요",
                "answer" to "2",
                "example1" to "1",
                "example2" to "2",
                "example3" to "3",
                "example4" to "4",
                "hint" to "배경 추가버튼은 사진 모양을 하고 있어요"
            ),
            mapOf( //챕터5
                "question" to "초록 깃발(시작) 버튼을 누르면 블록 아래의 코드가 실행되는데, 도중에 멈추고 싶으면 어느 버튼을 눌러야 할까요?",
                "answer" to "3",
                "example1" to "1",
                "example2" to "2",
                "example3" to "3",
                "example4" to "4",
                "hint" to "초록깃발이 시작, 빨간 팔각형 모양이 정지 버튼이랍니다"
            ),
            mapOf(  //챕터6
                "question" to "고양이가 말하고 생각하는 중이에요. 아래 코드가 전부 실행되고 나서 고양이는 어떤 말풍선을 출력하고 있나요?",
                "answer" to "4",
                "example1" to "1",
                "example2" to "2",
                "example3" to "3",
                "example4" to "4",
                "hint" to "마지막에 실행되는 블록을 잘 살펴보세요. 말하기와 생각하기는 서로 같은 자리를 차지하기 때문에 마지막에 실행되는 것이 남는답니다",
                //"reason" to "왜냐하면 마지막에 출력된 것이 50 생각하기이므로"
            ),
            mapOf( //챕터6
                "question" to "점심시간에 “맛있는 점심”을 말하려고 해요. 아래 코드가 실행될 때, “점심”은 “맛있는” 이 사라지고 몇 초 뒤에 말하나요?",
                "answer" to "0",
                "example1" to "0",
                "example2" to "1",
                "example3" to "2",
                "example4" to "3",
                "hint" to "“맛있는” 이 나오기 시작하고 2초 뒤에 “점심” 이라고 말하죠. 하지만 “맛있는”이 사라진 직후 “점심”이 출력된답니다. ‘사라지고 몇 초’를 중심으로 생각해보아요",
                //"reason" to "왜냐하면 “맛있는“이 나오기 시작하고 사라질 때 까지의 시간이 2초인 것이고, 사라지고 나서 중간에 ~초 기다리기 같은 블록이 없으므로 점심”은 “맛있는” 이 사라지고 0초 뒤에 말해요"
            ),
            mapOf(  //챕터7
                "question" to "라면을 먹고 싶어서 장을 보러 나왔어요. 블록이 전부 실행된 후 ‘라면’ 개수는 얼마로 정해지나요?",
                "answer" to "20",
                "example1" to "0",
                "example2" to "10",
                "example3" to "20",
                "example4" to "30",
                "hint" to "변수를 ~ 만큼 바꾸기는 해당하는 빈칸의 값만큼 더해주면 된답니다"
            ),
            mapOf( //챕터7
                "question" to "스마트폰이 몇 대가 있는지 세어보는 중이에요. 블록이 전부 실행된 후 ‘스마트폰’ 개수는 얼마나 있나요?",
                "answer" to "40",
                "example1" to "10",
                "example2" to "20",
                "example3" to "30",
                "example4" to "40",
                "hint" to " 변수를 ~ 만큼 바꾸기는 해당하는 빈칸의 값만큼 더해주면 된답니다. 단, 변수를 변수만큼 바꾸기가 끝난 상태로 마지막 블록이 실행되므로 주의",
                //"reason" to "왜냐하면 첫 ‘스마트폰’ 만큼 바꾸기 블록은 +10만큼 바꿔주고, 두 번째 ‘스마트폰’ 만큼 바꾸기 블록은 +20만큼 바꿔주기 때문"
            ),
            mapOf(  //챕터8
                "question" to "글자들의 길이를 알아보아요. 아래 블록은 어떤 값을 가지고 있을까요?",
                "answer" to "4",
                "example1" to "1",
                "example2" to "2",
                "example3" to "3",
                "example4" to "4",
                "hint" to "가위는 길이가 2이에요"   //이거 본래는 사진이었음
            ),
            mapOf( //챕터8
                "question" to "철수가 음료수를 사려고 해요. 초록 깃발을 누른 후, 조건을 만족하게 하려면 빈칸에 어떤 숫자를 써줘야 할까요?",
                "answer" to "40",
                "example1" to "10",
                "example2" to "20",
                "example3" to "30",
                "example4" to "40",
                "hint" to "해당 조건은 음료수 변수가 50이면 만족해요. 처음에 변수를 10으로 정했고 ~ 만큼 바꾸기는 빈칸의 숫자만큼 변수에 더해준다고 알려줬어요"
            ),
            mapOf(  //챕터9
                "question" to "고양이가 뛰어가고 있어요. 초록 깃발을 클릭하고 실행이 완료되면, 고양이의 x좌표는 어디일까요? ",
                "answer" to "100",
                "example1" to "10",
                "example2" to "50",
                "example3" to "100",
                "example4" to "200",
                "hint" to "해당 블록은 x좌표가 처음에 0이고 10만큼 10번 더해줬어요"
            ),
            mapOf( //챕터9
                "question" to "물을 마시고 싶어요. 초록 깃발을 클릭하면 고양이가 출력하는 숫자는 뭔가요?",
                "answer" to "30",
                "example1" to "0",
                "example2" to "10",
                "example3" to "20",
                "example4" to "30",
                "hint" to "물 변수가 처음에 0이고, 만약 ~라면 중 해당하는 조건은 물 < 50 일 때 밖에 없어요"
            )
        )*/

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
    var hint = ""
    //var reason = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //val id = String.format(QuestionRepository.id_format, 1, chapterNumber, if(quizFourComplete == 100) 2 else 3)

        // now, showProblem also set the imageview
        showProblem(1/*TODO: get stage too*/, chapterNumber!!, if(quizFourComplete == 100) 3 else 2)

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
        val problem = repo.get(stage, chapter!!, pn)
        question = problem["content"].toString()  //즉, question to 머시기를 String으로 바꿔 question에 넣어줍니다.
        answer = problem["answer"].toString()
        example1 = problem["cand1"].toString()
        example2 = problem["cand2"].toString()
        example3 = problem["cand3"].toString()
        example4 = problem["cand4"].toString()
        //reason = problems[pn - 1]["reason"].toString()  //유저가 퀴즈를 틀렸을때, 그 틀린 이유를 알려주는 퀴즈문제들이 몇몇 있었습니다. 그들을 위한 변수입니다.
        //explanation = problem["explanation"].toString() //

        binding?.fourQuestionTextView?.text = question  //위에서 만들어준 녀석들을 binding을 통해 화면에 뿌려줍니다.
        binding?.fourQuestionTextView?.movementMethod = ScrollingMovementMethod.getInstance() //이렇게 qustion텍스트도 스크롤이 가능해집니다.
        binding?.btnEx1?.text = example1
        binding?.btnEx2?.text = example2
        binding?.btnEx3?.text = example3
        binding?.btnEx4?.text = example4

        /*
        val src = ImageSource.bitmap(repo.getImage(stage, chapter, pn))
        Log.i("here",src.toString())
        binding?.imgQuestionFour?.setImage(src)*/
        binding?.imgQuestionFour?.setImageBitmap(repo.getImage(stage, chapter, pn))

        /*
        val num = if (quizFourComplete == 100) 3 else 2
        DatabaseConnector(context).uploadQuestion(
            "1-$chapterNumber-$num", question,
            problems[pn - 1]["hint"].toString(), false,
            arrayOf(example1, example2, example3, example4), answer, /*reason*/"Something to explain?", ImageAccessor(context).getFileUrl(1, chapterNumber!!, num))
        */
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
            //bundle.putString("reason", reason)  //틀린 이유가 따로 나와있는경우
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
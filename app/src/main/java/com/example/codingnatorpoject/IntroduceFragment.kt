package com.example.codingnatorpoject

import android.content.Intent
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.codingnatorpoject.databinding.FragmentIntroduceBinding

class IntroduceFragment : Fragment() {
    private var chapterNumber: Int? = null
    private var totalCorrect: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            chapterNumber = it.getInt("chapterNumber")
            totalCorrect = it.getInt("totalCorrect")
        }
    }

    var binding: FragmentIntroduceBinding? = null
    var explanation = arrayOf( //mapOf를 사용해서 문제를 추출합니다.... 배열의 형태로 만들어줬습니다. 물론, 현재는 무작위 추출이 아니고 이 배열의 순서대로 문제가 출력되는 형식으로 했습니다.
        mapOf("question" to "이동 - 기초\n아래 두 블록은 캐릭터가 움직일 수 있도록 해주는 블록이에요.\n 캐릭터가 바라보는 방향으로 10만큼 움직이는 블록으로, 숫자를 바꿔 이동거리를 바꿀 수 있어요."),
        mapOf("question" to "방향 전환 - 기초\n아래 세 블록은 캐릭터가 바라보는 방향을 바꿀 수 있도록 해주는 블록이에요.\n 캐릭터가 바라보는 방향을 90도로 직접 바꿔주거나, 현재 바라보고 있는 방향에서 화살표 방향으로 15도큼 방향을 바꿔주는 블록으로, 숫자를 바꿔 이동방향을 자유롭게 바꿀 수 있어요."),
        mapOf("question" to "이벤트 - 기초\n아래 블록들은 캐릭터에 있는 블록들을 작동할 수 있도록 해주는 블록이에요. \n클릭하거나 버튼을 누르면 해당 블록에 연결되어있는 블록들이 차래대로 작동한답니다.\n* 스프라이트, 오브젝트는 해당 코드가 있는 캐릭터를 의미해요."),
        mapOf("question" to "이동 - 기본\n아래 블록들은 캐릭터가 움직일 수 있도록 해주는 블록이에요.\nx좌표는 좌우, y좌표는 상하의 움직임이고, 숫자를 바꿔 이동거리를 바꿀 수 있어요.\n* 이동하기와 정하기는 해당 좌표로 이동하는 블록이고, ~만큼 바꾸기는 현재 좌표에서 안의 값만큼 더해주는 블록이에요. 헷갈리지 않도록 주의!"),
        mapOf("question" to "화면 구성 - 기본\n아래 버튼들은 스크래치 사이트에서 볼 수 있는 다양한 아이콘이에요.\n각 아이콘의 기능을 알아봅시다.\n확장 기능에는 이것 말고도 다양한 기능들이 있어요."),
        mapOf("question" to "출력문 - 기초\n다음 블록들은 캐릭터가 말하거나, 생각하는 등의 동작을 말풍선으로 띄워주는 블록들이에요\n아래 메시지나 초를 바꿔 자유롭게 말하게 할 수 있어요.\n말하기와 생각하기의 차이점은 말풍선 종류가 달라지는 것이고, 말하기와 ~초 동안 말하기는 그냥 말하기는 말하고 다른 것을 생각하거나 말할 때 까지 그 말풍선이 유지되지만, ~초 동안 말하기는 해당 초가 지나가면 그 말풍선이 사라져요."),
        mapOf("question" to "변수 - 기초\n다음 블록들은 변수를 자유롭게 만들고 이용할 수 있는 블록들이에요."),
        mapOf("question" to "조건 - 기초\n아래 블록들은 다음 단원에 배울 반복문의 실행 조건을 설정하여 줄 수 있는 조건블록이에요.\n기본적인 부등호(>,<.=), 사칙연산(+(덧셈),-(뺄셈),*(곱셈),/(나눗셈)), 난수(랜덤 수), 글자에 관련 블록과 나머지까지, 수많은 조건 블록들이 존재하고, 이를 여러 조건을 만족시켜야 작동하게 만들거나, 하나만 만족시켜도 작동되게 만드는 등, 여러 방식으로 만들 수 있어요."),
        mapOf("question" to "반복문&판별문 - 기본\n다음 블록들은 저번 단원에서 배웠던 조건들을 바탕으로 판단하여 실행시키거나, 반복하게 만들어 주는 블록이에요.")
        )
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIntroduceBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //val mainIntent = Intent(requireActivity(), MainActivity::class.java)  //mainActivity로 넘어가기 위한 Intent입니다.

        if(chapterNumber == 1){
            showEducation(chapterNumber!!)
            binding?.imgIntro?.setImageResource(R.drawable.introduce1)
        }

        if(chapterNumber == 2){
            showEducation(chapterNumber!!)
            binding?.imgIntro?.setImageResource(R.drawable.introduce2)
        }

        if(chapterNumber == 3){
            showEducation(chapterNumber!!)
            binding?.imgIntro?.setImageResource(R.drawable.introduce3)
        }

        if(chapterNumber == 4){
            showEducation(chapterNumber!!)
            binding?.imgIntro?.setImageResource(R.drawable.introduce4)
        }

        if(chapterNumber == 5){
            showEducation(chapterNumber!!)
            binding?.imgIntro?.setImageResource(R.drawable.introduce5)
        }

        if(chapterNumber == 6){
            showEducation(chapterNumber!!)
            binding?.imgIntro?.setImageResource(R.drawable.introduce6)
        }

        if(chapterNumber == 7){
            showEducation(chapterNumber!!)
            binding?.imgIntro?.setImageResource(R.drawable.introduce7)
        }

        if(chapterNumber == 8){
            showEducation(chapterNumber!!)
            binding?.imgIntro?.setImageResource(R.drawable.introduce8)
        }

        if(chapterNumber == 9){
            showEducation(chapterNumber!!)
            binding?.imgIntro?.setImageResource(R.drawable.introduce9)
        }


        binding?.btnBack?.setOnClickListener {  //다시 메인화면으로 보내줍니다
            findNavController().popBackStack()  //해당 프래그먼트를 뒤로 보내는 역할을 해준다.
        }

        binding?.btnQuiz?.setOnClickListener {
            val bundle = Bundle().apply {
                putInt("chapterNumber", chapterNumber!!)
                putInt("totalCorrect", totalCorrect!!)  //전체 개수를 세기위한
            }
            findNavController().navigate(R.id.action_introduceFragment_to_OXQuizFragment, bundle)
        }
    }

    fun showEducation(pn: Int) {
        binding?.txtQuestion?.text = explanation[pn - 1]["question"].toString()
        binding?.txtQuestion?.movementMethod = ScrollingMovementMethod.getInstance()
    }
}
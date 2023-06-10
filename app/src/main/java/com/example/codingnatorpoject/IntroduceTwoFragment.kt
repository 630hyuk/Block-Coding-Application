package com.example.codingnatorpoject

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.codingnatorpoject.databinding.FragmentIntroduceTwoBinding

class IntroduceTwoFragment : Fragment() {
    private var chapterNumber: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            chapterNumber = it.getInt("chapterNumber")
        }
    }
    var binding: FragmentIntroduceTwoBinding? = null

    //잘 동작하는 지 알아보기 위해서 스테이지1과 동일한 방식으로 넣었습니다. aws가 잘 작동하면, 이곳을 주석처리해주세요!
    var explanation = arrayOf( //mapOf를 사용해서 문제를 추출합니다.... 배열의 형태로 만들어줬습니다. 물론, 현재는 무작위 추출이 아니고 이 배열의 순서대로 문제가 출력되는 형식으로 했습니다.
        mapOf("question" to "이동 - 응용\n아래 두 블록은 캐릭터가 무작위 위치로 움직일 수 있도록 해주는 블록이에요.\n 또한, 마우스 포인터가 있는 위치로도 움직일 수 있답니다."),
        mapOf("question" to "방향 전환 - 응용\n아래 세 블록은 캐릭터가 바라보는 방향을 바꿀 수 있도록 해주는 블록이에요.\n 회전방식은 왼쪽-오른쪽, 회전하기, 회전하지 않기 3가지가 존재하는데, 세 경우 모두 실제로 캐릭터가 바라보는 방향은 바뀌지만 각각 캐릭터 이미지가 왼쪽-오른쪽으로 바뀌거나, 같이 회전하던가, 일절 회전하지 않는 모습을 보여줘요."),
        mapOf("question" to "소리- 기초\n아래 블록들은 소리를 재생시키고 전체 음량(소리) 크기를 조절하는 블록이에요\n"),
        mapOf("question" to "모양- 기초\n다음 블록들은 캐릭터의 모양을 바꿔주는 블록이에요.\n다음 모양으로 바꾸기는 현재 모양의 다음 모양으로(맨 아래 모양일시 맨 위 모양으로 ) 바뀌어요. 같은 방식으로 배경도 바꿀 수 있어요."),
        mapOf("question" to "이벤트 - 응용\n밑 블록들은 기본적인 이벤트 블록들과는 달리, 조금 더 복잡한 조건이 만족되면 실행되어요.\n ‘메시지’ 부분을 제어하는 블록들도 있답니다.\n‘메시지1’ 신호 보내고 기다리기는 해당 신호를 받는 블록이 전부 실행이 끝난 후, 그 뒤의 블록이 실행되고, 기다리기가 없는 블록은 해당 신호를 받는 블록의 진행 상황과 관계없이 실행되어요."),
        mapOf("question" to "연산 – 응용\n아래 블록들은 여러 가지 조건을 한 번에 비교할 수 있게 만들어주는 블록들이에요.\n그리고 블록은 양쪽 모두 만족해야 하고, 또는 블록은 한쪽만 만족해도 되요. ~가 아니다 블록은 안쪽 조건의 만족 / 불만족을 반대로 바꿔줘요!\n"),
        mapOf("question" to "감지 – 기초\n아래 블록들은 감지를 통해 조건을 만족시키거나, 묻고 답하며 사용자가 직접 값을 입력 가능해요."),
        mapOf("question" to "크기&색깔- 기초\n아래 블록들은 캐릭터의 크기와 색깔을 바꿔주는 블록들이에요"),
        mapOf("question" to "숨김 처리 –기초\n아래 블록으로 캐릭터를 보이게 하거나 숨길 수 있어요.\n숨기기 상태에서도 캐릭터의 다른 블록들은 작동해요.")
    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIntroduceTwoBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(chapterNumber == 1){
            showEducation(chapterNumber!!)
            //binding?.imgIntro?.setImageResource(R.drawable.introduce1)  //아직, 이미지 파일은 넣지 않았음
        }

        if(chapterNumber == 2){
            showEducation(chapterNumber!!)
            //binding?.imgIntro?.setImageResource(R.drawable.introduce2)
        }

        if(chapterNumber == 3){
            showEducation(chapterNumber!!)
            //binding?.imgIntro?.setImageResource(R.drawable.introduce3)
        }

        if(chapterNumber == 4){
            showEducation(chapterNumber!!)
            //binding?.imgIntro?.setImageResource(R.drawable.introduce4)
        }

        if(chapterNumber == 5){
            showEducation(chapterNumber!!)
            //binding?.imgIntro?.setImageResource(R.drawable.introduce5)
        }

        if(chapterNumber == 6){
            showEducation(chapterNumber!!)
            //binding?.imgIntro?.setImageResource(R.drawable.introduce6)
        }

        if(chapterNumber == 7){
            showEducation(chapterNumber!!)
            //binding?.imgIntro?.setImageResource(R.drawable.introduce7)
        }

        if(chapterNumber == 8){
            showEducation(chapterNumber!!)
            //binding?.imgIntro?.setImageResource(R.drawable.introduce8)
        }

        if(chapterNumber == 9){
            showEducation(chapterNumber!!)
            //binding?.imgIntro?.setImageResource(R.drawable.introduce9)
        }


        binding?.btnBack?.setOnClickListener {  //다시 메인화면으로 보내줍니다
            findNavController().popBackStack()  //해당 프래그먼트를 뒤로 보내는 역할을 해준다.
        }

        binding?.btnQuiz?.setOnClickListener {
            val bundle = Bundle().apply {
                putInt("chapterNumber", chapterNumber!!)
            }
            findNavController().navigate(R.id.action_introduceTwoFragment_to_OXQuizTwoFragment, bundle)
        }
    }

    fun showEducation(pn: Int) {
        binding?.txtQuestion?.text = explanation[pn - 1]["question"].toString()
        binding?.txtQuestion?.movementMethod = ScrollingMovementMethod.getInstance()
    }
}
package com.example.codingnatorpoject

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.codingnatorpoject.databinding.FragmentIntroduceBinding

class IntroduceFragment : Fragment() {
    private var chapterNumber: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            chapterNumber = it.getInt("chapterNumber")
        }
    }

    var binding: FragmentIntroduceBinding? = null
    var explanation = arrayOf( //mapOf를 사용해서 문제를 추출합니다.... 배열의 형태로 만들어줬습니다. 물론, 현재는 무작위 추출이 아니고 이 배열의 순서대로 문제가 출력되는 형식으로 했습니다.
        mapOf("question" to "이동 - 기초.....아래 두 블록은 캐릭터가 움직일 수 있도록 해주는 블록이에요. 캐릭터가 바라보는 방향으로 10만큼 움직이는 블록으로, 숫자를 바꿔 이동거리를 바꿀 수 있어요."),
        mapOf("question" to "방향 전환 - 기초.......아래 세 블록은 캐릭터가 바라보는 방향을 바꿀 수 있도록 해주는 블록이에요. 캐릭터가 바라보는 방향을 90도로 직접 바꿔주거나, 현재 바라보고 있는 방향에서 화살표 방향으로 15도 만큼 방향을 바꿔주는 블록으로, 숫자를 바꿔 이동방향을 자유롭게 바꿀 수 있어요."),
        mapOf("question" to "이벤트 - 기초.....아래 블록들은 캐릭터에 있는 블록들을 작동할 수 있도록 해주는 블록이에요. 클릭하거나 버튼을 누르면 해당 블록에 연결되어있는 블록들이 차래대로 작동한답니다. * 스프라이트, 오브젝트는 해당 코드가 있는 캐릭터를 의미해요."),
        mapOf("question" to "이동 - 기본....... 아래 블록들은 캐릭터가 움직일 수 있도록 해주는 블록이에요. x좌표는 좌우, y좌표는 상하의 움직임이고, 숫자를 바꿔 이동거리를 바꿀 수 있어요. * 이동하기와 정하기는 해당 좌표로 이동하는 블록이고, ~만큼 바꾸기는 현재 좌표에서 안의 값만큼 더해주는 블록이에요. 헷갈리지 않도록 주의!")
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

        binding?.btnBack?.setOnClickListener {  //다시 메인화면으로 보내줍니다
            findNavController().navigate(R.id.action_introduceFragment_to_stageOneFragment)
        }

        binding?.btnQuiz?.setOnClickListener {
            val bundle = Bundle().apply {
                putInt("chapterNumber", chapterNumber!!)
            }
            findNavController().navigate(R.id.action_introduceFragment_to_OXQuizFragment, bundle)
        }
    }

    fun showEducation(pn: Int) {
        binding?.txtQuestion?.text = explanation[pn - 1]["question"].toString()
    }
}
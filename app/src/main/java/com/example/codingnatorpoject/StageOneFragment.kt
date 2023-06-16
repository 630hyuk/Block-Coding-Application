package com.example.codingnatorpoject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import com.example.codingnatorpoject.databinding.FragmentStageOneBinding
import com.example.codingnatorpoject.DBConnection.UserManager

class StageOneFragment : Fragment() {
    private var restart : String? = null //LastResultFragment에서 재시작 신호를 받았을때

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            restart = it.getString("restart")
        }
    }

    var binding: FragmentStageOneBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStageOneBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var imageIslandList: Array<ImageView?>
        var imageStarList: Array<Array<ImageView?>>
        var resIdList: Array<Int>

        with (binding!!) {
            imageIslandList = arrayOf(
                null,
                btnChapter1, btnChapter2, btnChapter3,
                btnChapter4, btnChapter5, btnChapter6,
                btnChapter7, btnChapter8, btnChapter9,
                btnChapter10
            )

            imageStarList = arrayOf(
                arrayOf(null,null,null),
                arrayOf(imgStarOne11, imgStarOne12, imgStarOne13),
                arrayOf(imgStarOne21, imgStarOne22, imgStarOne23),
                arrayOf(imgStarOne31, imgStarOne32, imgStarOne33),
                arrayOf(imgStarOne41, imgStarOne42, imgStarOne43),
                arrayOf(imgStarOne51, imgStarOne52, imgStarOne53),
                arrayOf(imgStarOne61, imgStarOne62, imgStarOne63),
                arrayOf(imgStarOne71, imgStarOne72, imgStarOne73),
                arrayOf(imgStarOne81, imgStarOne82, imgStarOne83),
                arrayOf(imgStarOne91, imgStarOne92, imgStarOne93),
                arrayOf(imgStarOne101, imgStarOne102, imgStarOne103)
            )

            resIdList = arrayOf(
                0,
                R.drawable.island1,
                R.drawable.island1_2,
                R.drawable.island1_3,
                R.drawable.island1_4,
                R.drawable.island1_5,
                R.drawable.island1_6,
                R.drawable.island1_7,
                R.drawable.island1_8,
                R.drawable.island1_9,
                R.drawable.island1_10
            )
        }

        //우선 1챕터를 제외해서 9챕터까지 클릭못하게 막기
        for (i: ImageView? in imageIslandList) {
            i?.isEnabled = false
        }
        imageIslandList[1]?.isEnabled = true

        for (chapter: Int in 1..9) {
            val star = UserManager.getStarAt(1, chapter)

            if (star < 1) break     // 이후의 챕터 모두 플레이 한 적 없음이 자명함

            imageIslandList[chapter+1]?.isEnabled = true
            imageIslandList[chapter+1]?.setImageResource(resIdList[chapter+1])
            for (st: Int in 0 until star) {
                imageStarList[chapter][st]?.setImageResource(android.R.drawable.btn_star_big_on)
            }
        }

        // 10챕터의 별 표시
        for (st: Int in 0 until UserManager.getStarAt(1, 10)) {
            imageStarList[10][st]?.setImageResource(android.R.drawable.btn_star_big_on)
        }

        val bundle = Bundle()  //몇 챕터를 선택할지 이 번들에 넣어서 알려줍니다.
        val totalCorrect = 0  //모든 시작을 여기서 한다고 치자.

        binding?.btnGoTo2?.setOnClickListener {
            findNavController().navigate(R.id.action_stageOneFragment_to_stageTwoFragment)
        }

        for (chapter: Int in 1..9) {
            imageIslandList[chapter]?.setOnClickListener {
                bundle.putInt("chapterNumber", chapter)
                bundle.putInt("totalCorrect", totalCorrect)
                findNavController().navigate(R.id.action_stageOneFragment_to_introduceFragment, bundle)
            }
        }

        binding?.btnChapter10?.setOnClickListener {
            bundle.putInt("order", 1)  //챕터10의 1번부터 10번까지를 호출하기 위한 order값입니다
            bundle.putString("restart", restart)  // 만약 챕터10 재시작이 아닐 경우, 이 안에는 null값이 들어가지만, 재시작을 할 경우 "restart"가 들어간다.
            bundle.putInt("totalCorrect", totalCorrect)  //초기의 시작은 0이니까
            findNavController().navigate(R.id.action_stageOneFragment_to_stageOneChapterTenQuizOXFragment, bundle)
        }

        binding?.btnReturnToSelect?.setOnClickListener {  //혹시 여기를 이렇게 만든 이유가 있나요? intent로 한 Activiy로 하는데 문제가 있었나요?
            //findNavController().navigate(R.id.action_stageOneFragment_to_selectQuizFragment, bundle)
            findNavController().popBackStack()  //해당 프래그먼트를 뒤로 보내는 역할을 해준다.
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
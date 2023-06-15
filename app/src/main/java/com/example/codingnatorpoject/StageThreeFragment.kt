package com.example.codingnatorpoject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import com.example.codingnatorpoject.DBConnection.User
import com.example.codingnatorpoject.databinding.FragmentStageThreeBinding

class StageThreeFragment : Fragment() {
    private var restart : String? = null //LastResultFragment에서 재시작 신호를 받았을때

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            restart = it.getString("restart")
        }
    }

    var binding: FragmentStageThreeBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStageThreeBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var imageIslandList: Array<ImageView?>
        var imageStarList: Array<Array<ImageView?>>
        var resIdList: Array<Int>

        // Array들의 초기화
        with (binding!!) {
            imageIslandList = arrayOf(
                null,
                btnChapter31, btnChapter32, btnChapter33,
                btnChapter34, btnChapter35, btnChapter36,
                btnChapter37, btnChapter38, btnChapter39,
                btnChapter310
            )

            imageStarList = arrayOf(
                arrayOf(null,null,null),
                arrayOf(imgStarThree11, imgStarThree12, imgStarThree13),
                arrayOf(imgStarThree21, imgStarThree22, imgStarThree23),
                arrayOf(imgStarThree31, imgStarThree32, imgStarThree33),
                arrayOf(imgStarThree41, imgStarThree42, imgStarThree43),
                arrayOf(imgStarThree51, imgStarThree52, imgStarThree53),
                arrayOf(imgStarThree61, imgStarThree62, imgStarThree63),
                arrayOf(imgStarThree71, imgStarThree72, imgStarThree73),
                arrayOf(imgStarThree81, imgStarThree82, imgStarThree83),
                arrayOf(imgStarThree91, imgStarThree92, imgStarThree93),
                arrayOf(imgStarThree101, imgStarThree102, imgStarThree103)
            )

            resIdList = arrayOf(
                0,
                R.drawable.island3_1,
                R.drawable.island3_2,
                R.drawable.island3_3,
                R.drawable.island3_4,
                R.drawable.island3_5,
                R.drawable.island3_6,
                R.drawable.island3_7,
                R.drawable.island3_8,
                R.drawable.island3_9,
                R.drawable.island3_10
            )
        }

        //우선 1챕터를 제외해서 9챕터까지 클릭못하게 막기
        for (i: ImageView? in imageIslandList) {
            i?.isEnabled = false;
        }

        // 2-10의 별 갯수를 확인한 후에 1챕터 활성화
        val previousChapterTenStar = User.getStarAt(2,10)
        if (previousChapterTenStar > 0) {
            imageIslandList[1]?.isEnabled = true
            imageIslandList[1]?.setImageResource(resIdList[1])
        }

        for (chapter: Int in 1..9) {
            val star = User.getStarAt(3, chapter)

            if (star < 1) break     // 이후의 챕터 모두 플레이 한 적 없음이 자명함

            imageIslandList[chapter+1]?.isEnabled = true
            imageIslandList[chapter+1]?.setImageResource(resIdList[chapter+1])
            for (st: Int in 0 until star) {
                imageStarList[chapter][st]?.setImageResource(android.R.drawable.btn_star_big_on)
            }
        }

        // 10챕터의 별 표시
        for (st: Int in 0 until User.getStarAt(3, 10)) {
            imageStarList[10][st]?.setImageResource(android.R.drawable.btn_star_big_on)
        }

        val bundle = Bundle()  //몇 챕터를 선택할지 이 번들에 넣어서 알려줍니다.
        val totalCorrect = 0  //모든 시작을 여기서 한다고 치자.

        binding?.btnGoTo2?.setOnClickListener {
            findNavController().navigate(R.id.action_stageThreeFragment_to_stageTwoFragment)
        }

        binding?.btnReturnToSelect?.setOnClickListener {
            //findNavController().navigate(R.id.action_stageThreeFragment_to_selectQuizFragment)
            findNavController().popBackStack()  //해당 프래그먼트를 뒤로 보내는 역할을 해준다.
        }

        for (chapter: Int in 1..9) {
            imageIslandList[chapter]?.setOnClickListener {
                bundle.putInt("chapterNumber", chapter)
                bundle.putInt("totalCorrect", totalCorrect)
                findNavController().navigate(R.id.action_stageThreeFragment_to_introduceThreeFragment, bundle)
            }
        }

        binding?.btnChapter310?.setOnClickListener {
            bundle.putInt("order", 1)  //챕터10의 1번부터 10번까지를 호출하기 위한 order값입니다
            bundle.putString("restart", restart)  // 만약 챕터10 재시작이 아닐 경우, 이 안에는 null값이 들어가지만, 재시작을 할 경우 "restart"가 들어간다.
            bundle.putInt("totalCorrect", totalCorrect)  //초기의 시작은 0이니까
            findNavController().navigate(R.id.action_stageThreeFragment_to_stageThreeChapterTenQuizOXFragment, bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
package com.example.codingnatorpoject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import com.example.codingnatorpoject.DBConnection.UserManager
import com.example.codingnatorpoject.databinding.FragmentStageTwoBinding

class StageTwoFragment : Fragment() {
    private var restart : String? = null //LastResultFragment에서 재시작 신호를 받았을때

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            restart = it.getString("restart")
        }
    }

    var binding: FragmentStageTwoBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStageTwoBinding.inflate(inflater)
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
                btnChapter21, btnChapter22, btnChapter23,
                btnChapter24, btnChapter25, btnChapter26,
                btnChapter27, btnChapter28, btnChapter29,
                btnChapter210
            )

            imageStarList = arrayOf(
                arrayOf(null,null,null),
                arrayOf(imgStarTwo11, imgStarTwo12, imgStarTwo13),
                arrayOf(imgStarTwo21, imgStarTwo22, imgStarTwo23),
                arrayOf(imgStarTwo31, imgStarTwo32, imgStarTwo33),
                arrayOf(imgStarTwo41, imgStarTwo42, imgStarTwo43),
                arrayOf(imgStarTwo51, imgStarTwo52, imgStarTwo53),
                arrayOf(imgStarTwo61, imgStarTwo62, imgStarTwo63),
                arrayOf(imgStarTwo71, imgStarTwo72, imgStarTwo73),
                arrayOf(imgStarTwo81, imgStarTwo82, imgStarTwo83),
                arrayOf(imgStarTwo91, imgStarTwo92, imgStarTwo93),
                arrayOf(imgStarTwo101, imgStarTwo102, imgStarTwo103)
            )

            resIdList = arrayOf(
                0,
                R.drawable.island2_1,
                R.drawable.island2_2,
                R.drawable.island2_3,
                R.drawable.island2_4,
                R.drawable.island2_5,
                R.drawable.island2_6,
                R.drawable.island2_7,
                R.drawable.island2_8,
                R.drawable.island2_9,
                R.drawable.island2_10
            )
        }


        //우선 1챕터를 제외해서 9챕터까지 클릭못하게 막기
        for (i: ImageView? in imageIslandList) {
            i?.isEnabled = false;
        }

        // 1-10의 별 갯수를 확인한 후에 1챕터 활성화
        val previousChapterTenStar = UserManager.getStarAt(1,10)
        if (previousChapterTenStar > 0) {
            imageIslandList[1]?.isEnabled = true
            imageIslandList[1]?.setImageResource(resIdList[1])
        }

        for (chapter: Int in 1..9) {
            val star = UserManager.getStarAt(2, chapter)

            if (star < 1) break     // 이후의 챕터 모두 플레이 한 적 없음이 자명함

            imageIslandList[chapter+1]?.isEnabled = true
            imageIslandList[chapter+1]?.setImageResource(resIdList[chapter+1])
            for (st: Int in 0 until star) {
                imageStarList[chapter][st]?.setImageResource(android.R.drawable.btn_star_big_on)
            }
        }

        // 10챕터의 별 표시
        for (st: Int in 0 until UserManager.getStarAt(2, 10)) {
            imageStarList[10][st]?.setImageResource(android.R.drawable.btn_star_big_on)
        }

        val bundle = Bundle()  //몇 챕터를 선택할지 이 번들에 넣어서 알려줍니다.
        val totalCorrect = 0  //모든 시작을 여기서 한다고 치자.

        binding?.btnGoTo1?.setOnClickListener {
            findNavController().navigate(R.id.action_stageTwoFragment_to_stageOneFragment)
        }
        binding?.btnGoTo3?.setOnClickListener {
            findNavController().navigate(R.id.action_stageTwoFragment_to_stageThreeFragment)
        }

        for (chapter: Int in 1..9) {
            imageIslandList[chapter]?.setOnClickListener {
                bundle.putInt("chapterNumber", chapter)
                bundle.putInt("totalCorrect", totalCorrect)
                findNavController().navigate(R.id.action_stageTwoFragment_to_introduceTwoFragment, bundle)
            }
        }

        binding?.btnChapter210?.setOnClickListener {
            bundle.putInt("order", 1)  //챕터10의 1번부터 10번까지를 호출하기 위한 order값입니다
            bundle.putString("restart", restart)  // 만약 챕터10 재시작이 아닐 경우, 이 안에는 null값이 들어가지만, 재시작을 할 경우 "restart"가 들어간다.
            bundle.putInt("totalCorrect", totalCorrect)  //초기의 시작은 0이니까
            findNavController().navigate(R.id.action_stageTwoFragment_to_stageTwoChapterTenQuizOXFragment, bundle)
        }

        binding?.btnReturnToSelect?.setOnClickListener {
            //findNavController().navigate(R.id.action_stageTwoFragment_to_selectQuizFragment)
            findNavController().popBackStack()  //해당 프래그먼트를 뒤로 보내는 역할을 해준다.
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
package com.example.codingnatorpoject

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.codingnatorpoject.databinding.FragmentStageOneBinding
import com.example.codingnatorpoject.databinding.FragmentStageTwoBinding

class StageTwoFragment : Fragment() {
    private var restart : String? = null //LastResultFragment에서 재시작 신호를 받았을때
    private var chapterNumber: Int? = null  //어떤 챕터가 lastResult에서 넘어왔는지 확인해야 함
    private var fromLastReslutTotalCorrect: Int? = null  //해당 챕터에서 얼마나 맞았는지 확인해야 함

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            restart = it.getString("restart")
            chapterNumber = it.getInt("chapterNumber")
            fromLastReslutTotalCorrect = it.getInt("totalCorrect")
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

        if(chapterNumber == 1){  //이들을 이용해 각 챕터별로 별의 개수를 바꿔줍니다.
            if(fromLastReslutTotalCorrect == 1){
                binding?.imgStarTwo11?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
            }

            if(fromLastReslutTotalCorrect == 2){
                binding?.imgStarTwo11?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.imgStarTwo12?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
            }

            if(fromLastReslutTotalCorrect == 3) {
                binding?.imgStarTwo11?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.imgStarTwo12?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.imgStarTwo13?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
            }
        }

        if(chapterNumber == 2){  //이들을 이용해 각 챕터별로 별의 개수를 바꿔줍니다.
            if(fromLastReslutTotalCorrect == 1){
                binding?.imgStarTwo21?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
            }

            if(fromLastReslutTotalCorrect == 2){
                binding?.imgStarTwo21?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.imgStarTwo22?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
            }

            if(fromLastReslutTotalCorrect == 3) {
                binding?.imgStarTwo21?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.imgStarTwo22?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.imgStarTwo23?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
            }
        }

        if(chapterNumber == 3){  //이들을 이용해 각 챕터별로 별의 개수를 바꿔줍니다.
            if(fromLastReslutTotalCorrect == 1){
                binding?.imgStarTwo31?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
            }

            if(fromLastReslutTotalCorrect == 2){
                binding?.imgStarTwo31?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.imgStarTwo32?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
            }

            if(fromLastReslutTotalCorrect == 3) {
                binding?.imgStarTwo31?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.imgStarTwo32?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.imgStarTwo33?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
            }
        }

        if(chapterNumber == 4){  //이들을 이용해 각 챕터별로 별의 개수를 바꿔줍니다.
            if(fromLastReslutTotalCorrect == 1){
                binding?.imgStarTwo41?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
            }

            if(fromLastReslutTotalCorrect == 2){
                binding?.imgStarTwo41?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.imgStarTwo42?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
            }

            if(fromLastReslutTotalCorrect == 3) {
                binding?.imgStarTwo41?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.imgStarTwo42?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.imgStarTwo43?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
            }
        }

        if(chapterNumber == 5){  //이들을 이용해 각 챕터별로 별의 개수를 바꿔줍니다.
            if(fromLastReslutTotalCorrect == 1){
                binding?.imgStarTwo51?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
            }

            if(fromLastReslutTotalCorrect == 2){
                binding?.imgStarTwo51?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.imgStarTwo52?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
            }

            if(fromLastReslutTotalCorrect == 3) {
                binding?.imgStarTwo51?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.imgStarTwo52?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.imgStarTwo53?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
            }
        }

        if(chapterNumber == 6){  //이들을 이용해 각 챕터별로 별의 개수를 바꿔줍니다.
            if(fromLastReslutTotalCorrect == 1){
                binding?.imgStarTwo61?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
            }

            if(fromLastReslutTotalCorrect == 2){
                binding?.imgStarTwo61?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.imgStarTwo62?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
            }

            if(fromLastReslutTotalCorrect == 3) {
                binding?.imgStarTwo61?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.imgStarTwo62?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.imgStarTwo63?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
            }
        }

        if(chapterNumber == 7){  //이들을 이용해 각 챕터별로 별의 개수를 바꿔줍니다.
            if(fromLastReslutTotalCorrect == 1){
                binding?.imgStarTwo71?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
            }

            if(fromLastReslutTotalCorrect == 2){
                binding?.imgStarTwo71?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.imgStarTwo72?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
            }

            if(fromLastReslutTotalCorrect == 3) {
                binding?.imgStarTwo71?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.imgStarTwo72?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.imgStarTwo73?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
            }
        }

        if(chapterNumber == 8){  //이들을 이용해 각 챕터별로 별의 개수를 바꿔줍니다.
            if(fromLastReslutTotalCorrect == 1){
                binding?.imgStarTwo81?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
            }

            if(fromLastReslutTotalCorrect == 2){
                binding?.imgStarTwo81?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.imgStarTwo82?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
            }

            if(fromLastReslutTotalCorrect == 3) {
                binding?.imgStarTwo81?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.imgStarTwo82?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.imgStarTwo83?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
            }
        }

        if(chapterNumber == 9){  //이들을 이용해 각 챕터별로 별의 개수를 바꿔줍니다.
            if(fromLastReslutTotalCorrect == 1){
                binding?.imgStarTwo91?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
            }

            if(fromLastReslutTotalCorrect == 2){
                binding?.imgStarTwo91?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.imgStarTwo92?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
            }

            if(fromLastReslutTotalCorrect == 3) {
                binding?.imgStarTwo91?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.imgStarTwo92?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.imgStarTwo93?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
            }
        }

        val bundle = Bundle()  //몇 챕터를 선택할지 이 번들에 넣어서 알려줍니다.
        val totalCorrect = 0  //모든 시작을 여기서 한다고 치자.

        binding?.btnGoTo1?.setOnClickListener {
            findNavController().navigate(R.id.action_stageTwoFragment_to_stageOneFragment)
        }
        binding?.btnGoTo3?.setOnClickListener {
            findNavController().navigate(R.id.action_stageTwoFragment_to_stageThreeFragment)
        }

        binding?.btnChapter21?.setOnClickListener {
            //binding?.imgStarOne11?.setImageResource(android.R.drawable.btn_star_big_off)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
            bundle.putInt("chapterNumber", 1)  //챕터 1임을 알려줍니다.
            bundle.putInt("totalCorrect", totalCorrect)  //초기의 시작은 0이니까
            findNavController().navigate(R.id.action_stageTwoFragment_to_introduceTwoFragment, bundle)
        }

        binding?.btnChapter22?.setOnClickListener {
            bundle.putInt("chapterNumber", 2)  //챕터 2임을 알려줍니다.
            bundle.putInt("totalCorrect", totalCorrect)  //초기의 시작은 0이니까
            findNavController().navigate(R.id.action_stageTwoFragment_to_introduceTwoFragment, bundle)
        }

        binding?.btnChapter23?.setOnClickListener {
            bundle.putInt("chapterNumber", 3)  //챕터 3임을 알려줍니다.
            bundle.putInt("totalCorrect", totalCorrect)  //초기의 시작은 0이니까
            findNavController().navigate(R.id.action_stageTwoFragment_to_introduceTwoFragment, bundle)
        }

        binding?.btnChapter24?.setOnClickListener {
            bundle.putInt("chapterNumber", 4)  //챕터 4임을 알려줍니다.
            bundle.putInt("totalCorrect", totalCorrect)  //초기의 시작은 0이니까
            findNavController().navigate(R.id.action_stageTwoFragment_to_introduceTwoFragment, bundle)
        }

        binding?.btnChapter25?.setOnClickListener {
            bundle.putInt("chapterNumber", 5)  //챕터 5임을 알려줍니다.
            bundle.putInt("totalCorrect", totalCorrect)  //초기의 시작은 0이니까
            findNavController().navigate(R.id.action_stageTwoFragment_to_introduceTwoFragment, bundle)
        }

        binding?.btnChapter26?.setOnClickListener {
            bundle.putInt("chapterNumber", 6)  //챕터 6임을 알려줍니다.
            bundle.putInt("totalCorrect", totalCorrect)  //초기의 시작은 0이니까
            findNavController().navigate(R.id.action_stageTwoFragment_to_introduceTwoFragment, bundle)
        }

        binding?.btnChapter27?.setOnClickListener {
            bundle.putInt("chapterNumber", 7)  //챕터 7임을 알려줍니다.
            bundle.putInt("totalCorrect", totalCorrect)  //초기의 시작은 0이니까
            findNavController().navigate(R.id.action_stageTwoFragment_to_introduceTwoFragment, bundle)
        }

        binding?.btnChapter28?.setOnClickListener {
            bundle.putInt("chapterNumber", 8)  //챕터 8임을 알려줍니다.
            bundle.putInt("totalCorrect", totalCorrect)  //초기의 시작은 0이니까
            findNavController().navigate(R.id.action_stageTwoFragment_to_introduceTwoFragment, bundle)
        }

        binding?.btnChapter29?.setOnClickListener {
            bundle.putInt("chapterNumber", 9)  //챕터 9임을 알려줍니다.
            bundle.putInt("totalCorrect", totalCorrect)  //초기의 시작은 0이니까
            findNavController().navigate(R.id.action_stageTwoFragment_to_introduceTwoFragment, bundle)
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
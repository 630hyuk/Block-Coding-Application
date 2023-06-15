package com.example.codingnatorpoject

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.codingnatorpoject.databinding.FragmentStageOneBinding

class StageOneFragment : Fragment() {
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

        //우선 1, 10챕터를 제외해서 9챕터까지 클릭못하게 막기
        binding?.btnChapter2?.isEnabled = false  //다음 챕터를 선택할 수 없게 만들어줍니다.
        binding?.btnChapter3?.isEnabled = false  //다음 챕터를 선택할 수 없게 만들어줍니다.
        binding?.btnChapter4?.isEnabled = false  //다음 챕터를 선택할 수 없게 만들어줍니다.
        binding?.btnChapter5?.isEnabled = false  //다음 챕터를 선택할 수 없게 만들어줍니다.
        binding?.btnChapter6?.isEnabled = false  //다음 챕터를 선택할 수 없게 만들어줍니다.
        binding?.btnChapter7?.isEnabled = false  //다음 챕터를 선택할 수 없게 만들어줍니다.
        binding?.btnChapter8?.isEnabled = false  //다음 챕터를 선택할 수 없게 만들어줍니다.
        binding?.btnChapter9?.isEnabled = false  //다음 챕터를 선택할 수 없게 만들어줍니다.


        //밑의 과정들은 현재 딱 한번씩만 실행들이 됩니다. 예를 들어 챕터1을 끝마치고 챕터2를 푼 뒤에 지도를 보면, 챕터2에 대한 별의 정보만 남아있을뿐 챕터1에 대한 별의 정보는 초기화됩니다. 따라서 이를 클라우드 쪽에서 해결해야 할 거 같습니다.
        if(chapterNumber == 1){  //이들을 이용해 각 챕터별로 별의 개수를 바꿔줍니다.
            if(fromLastReslutTotalCorrect == 1){
                binding?.imgStarOne11?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.btnChapter2?.setImageResource(R.drawable.island1_2)  //다음 챕터섬을 보이도록 만들어줍니다.
                binding?.btnChapter2?.isEnabled = true  //다음 챕터를 선택할 수 있게 만들어줍니다.
            }

            if(fromLastReslutTotalCorrect == 2){
                binding?.imgStarOne11?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.imgStarOne12?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.btnChapter2?.setImageResource(R.drawable.island1_2)  //다음 챕터섬을 보이도록 만들어줍니다.
                binding?.btnChapter2?.isEnabled = true  //다음 챕터를 선택할 수 있게 만들어줍니다.
            }

            if(fromLastReslutTotalCorrect == 3) {
                binding?.imgStarOne11?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.imgStarOne12?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.imgStarOne13?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.btnChapter2?.setImageResource(R.drawable.island1_2) //다음 챕터섬을 보이도록 만들어줍니다.
                binding?.btnChapter2?.isEnabled = true  //다음 챕터를 선택할 수 있게 만들어줍니다.
            }
        }

        if(chapterNumber == 2){  //이들을 이용해 각 챕터별로 별의 개수를 바꿔줍니다.
            if(fromLastReslutTotalCorrect == 1){
                binding?.imgStarOne21?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.btnChapter3?.setImageResource(R.drawable.island1_3) //다음 챕터섬을 보이도록 만들어줍니다.
                binding?.btnChapter3?.isEnabled = true  //다음 챕터를 선택할 수 있게 만들어줍니다.
            }

            if(fromLastReslutTotalCorrect == 2){
                binding?.imgStarOne21?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.imgStarOne22?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.btnChapter3?.setImageResource(R.drawable.island1_3) //다음 챕터섬을 보이도록 만들어줍니다.
                binding?.btnChapter3?.isEnabled = true  //다음 챕터를 선택할 수 있게 만들어줍니다.
            }

            if(fromLastReslutTotalCorrect == 3) {
                binding?.imgStarOne21?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.imgStarOne22?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.imgStarOne23?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.btnChapter3?.setImageResource(R.drawable.island1_3) //다음 챕터섬을 보이도록 만들어줍니다.
                binding?.btnChapter3?.isEnabled = true  //다음 챕터를 선택할 수 있게 만들어줍니다.
            }
        }

        if(chapterNumber == 3){  //이들을 이용해 각 챕터별로 별의 개수를 바꿔줍니다.
            if(fromLastReslutTotalCorrect == 1){
                binding?.imgStarOne31?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.btnChapter4?.setImageResource(R.drawable.island1_4) //다음 챕터섬을 보이도록 만들어줍니다.
                binding?.btnChapter4?.isEnabled = true  //다음 챕터를 선택할 수 있게 만들어줍니다.
            }

            if(fromLastReslutTotalCorrect == 2){
                binding?.imgStarOne31?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.imgStarOne32?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.btnChapter4?.setImageResource(R.drawable.island1_4) //다음 챕터섬을 보이도록 만들어줍니다.
                binding?.btnChapter4?.isEnabled = true  //다음 챕터를 선택할 수 있게 만들어줍니다.
            }

            if(fromLastReslutTotalCorrect == 3) {
                binding?.imgStarOne31?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.imgStarOne32?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.imgStarOne33?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.btnChapter4?.setImageResource(R.drawable.island1_4) //다음 챕터섬을 보이도록 만들어줍니다.
                binding?.btnChapter4?.isEnabled = true  //다음 챕터를 선택할 수 있게 만들어줍니다.
            }
        }

        if(chapterNumber == 4){  //이들을 이용해 각 챕터별로 별의 개수를 바꿔줍니다.
            if(fromLastReslutTotalCorrect == 1){
                binding?.imgStarOne41?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.btnChapter5?.setImageResource(R.drawable.island1_5) //다음 챕터섬을 보이도록 만들어줍니다.
                binding?.btnChapter5?.isEnabled = true  //다음 챕터를 선택할 수 있게 만들어줍니다.
            }

            if(fromLastReslutTotalCorrect == 2){
                binding?.imgStarOne41?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.imgStarOne42?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.btnChapter5?.setImageResource(R.drawable.island1_5) //다음 챕터섬을 보이도록 만들어줍니다.
                binding?.btnChapter5?.isEnabled = true  //다음 챕터를 선택할 수 있게 만들어줍니다.
            }

            if(fromLastReslutTotalCorrect == 3) {
                binding?.imgStarOne41?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.imgStarOne42?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.imgStarOne43?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.btnChapter5?.setImageResource(R.drawable.island1_5) //다음 챕터섬을 보이도록 만들어줍니다.
                binding?.btnChapter5?.isEnabled = true  //다음 챕터를 선택할 수 있게 만들어줍니다.
            }
        }

        if(chapterNumber == 5){  //이들을 이용해 각 챕터별로 별의 개수를 바꿔줍니다.
            if(fromLastReslutTotalCorrect == 1){
                binding?.imgStarOne51?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.btnChapter6?.setImageResource(R.drawable.island1_6) //다음 챕터섬을 보이도록 만들어줍니다.
                binding?.btnChapter6?.isEnabled = true  //다음 챕터를 선택할 수 있게 만들어줍니다.
            }

            if(fromLastReslutTotalCorrect == 2){
                binding?.imgStarOne51?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.imgStarOne52?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.btnChapter6?.setImageResource(R.drawable.island1_6) //다음 챕터섬을 보이도록 만들어줍니다.
                binding?.btnChapter6?.isEnabled = true  //다음 챕터를 선택할 수 있게 만들어줍니다.
            }

            if(fromLastReslutTotalCorrect == 3) {
                binding?.imgStarOne51?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.imgStarOne52?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.imgStarOne53?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.btnChapter6?.setImageResource(R.drawable.island1_6) //다음 챕터섬을 보이도록 만들어줍니다.
                binding?.btnChapter6?.isEnabled = true  //다음 챕터를 선택할 수 있게 만들어줍니다.
            }
        }

        if(chapterNumber == 6){  //이들을 이용해 각 챕터별로 별의 개수를 바꿔줍니다.
            if(fromLastReslutTotalCorrect == 1){
                binding?.imgStarOne61?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.btnChapter7?.setImageResource(R.drawable.island1_7) //다음 챕터섬을 보이도록 만들어줍니다.
                binding?.btnChapter7?.isEnabled = true  //다음 챕터를 선택할 수 있게 만들어줍니다.
            }

            if(fromLastReslutTotalCorrect == 2){
                binding?.imgStarOne61?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.imgStarOne62?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.btnChapter7?.setImageResource(R.drawable.island1_7) //다음 챕터섬을 보이도록 만들어줍니다.
                binding?.btnChapter7?.isEnabled = true  //다음 챕터를 선택할 수 있게 만들어줍니다.
            }

            if(fromLastReslutTotalCorrect == 3) {
                binding?.imgStarOne61?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.imgStarOne62?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.imgStarOne63?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.btnChapter7?.setImageResource(R.drawable.island1_7) //다음 챕터섬을 보이도록 만들어줍니다.
                binding?.btnChapter7?.isEnabled = true  //다음 챕터를 선택할 수 있게 만들어줍니다.
            }
        }

        if(chapterNumber == 7){  //이들을 이용해 각 챕터별로 별의 개수를 바꿔줍니다.
            if(fromLastReslutTotalCorrect == 1){
                binding?.imgStarOne71?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.btnChapter8?.setImageResource(R.drawable.island1_8) //다음 챕터섬을 보이도록 만들어줍니다.
                binding?.btnChapter8?.isEnabled = true  //다음 챕터를 선택할 수 있게 만들어줍니다.
            }

            if(fromLastReslutTotalCorrect == 2){
                binding?.imgStarOne71?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.imgStarOne72?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.btnChapter8?.setImageResource(R.drawable.island1_8) //다음 챕터섬을 보이도록 만들어줍니다.
                binding?.btnChapter8?.isEnabled = true  //다음 챕터를 선택할 수 있게 만들어줍니다.
            }

            if(fromLastReslutTotalCorrect == 3) {
                binding?.imgStarOne71?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.imgStarOne72?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.imgStarOne73?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.btnChapter8?.setImageResource(R.drawable.island1_8) //다음 챕터섬을 보이도록 만들어줍니다.
                binding?.btnChapter8?.isEnabled = true  //다음 챕터를 선택할 수 있게 만들어줍니다.
            }
        }

        if(chapterNumber == 8){  //이들을 이용해 각 챕터별로 별의 개수를 바꿔줍니다.
            if(fromLastReslutTotalCorrect == 1){
                binding?.imgStarOne81?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.btnChapter9?.setImageResource(R.drawable.island1_9) //다음 챕터섬을 보이도록 만들어줍니다.
                binding?.btnChapter9?.isEnabled = true  //다음 챕터를 선택할 수 있게 만들어줍니다.
            }

            if(fromLastReslutTotalCorrect == 2){
                binding?.imgStarOne81?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.imgStarOne82?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨

                binding?.btnChapter9?.setImageResource(R.drawable.island1_9) //다음 챕터섬을 보이도록 만들어줍니다.
                binding?.btnChapter9?.isEnabled = true  //다음 챕터를 선택할 수 있게 만들어줍니다.
            }

            if(fromLastReslutTotalCorrect == 3) {
                binding?.imgStarOne81?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.imgStarOne82?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.imgStarOne83?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨

                binding?.btnChapter9?.setImageResource(R.drawable.island1_9) //다음 챕터섬을 보이도록 만들어줍니다.
                binding?.btnChapter9?.isEnabled = true  //다음 챕터를 선택할 수 있게 만들어줍니다.
            }
        }

        if(chapterNumber == 9){  //이들을 이용해 각 챕터별로 별의 개수를 바꿔줍니다.
            if(fromLastReslutTotalCorrect == 1){
                binding?.imgStarOne91?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.btnChapter10?.setImageResource(R.drawable.island1_10) //다음 챕터섬을 보이도록 만들어줍니다.
                binding?.btnChapter10?.isEnabled = true  //다음 챕터를 선택할 수 있게 만들어줍니다.
            }

            if(fromLastReslutTotalCorrect == 2){
                binding?.imgStarOne91?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.imgStarOne92?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨

                binding?.btnChapter10?.setImageResource(R.drawable.island1_10) //다음 챕터섬을 보이도록 만들어줍니다.
                binding?.btnChapter10?.isEnabled = true  //다음 챕터를 선택할 수 있게 만들어줍니다.
            }

            if(fromLastReslutTotalCorrect == 3) {
                binding?.imgStarOne91?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.imgStarOne92?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
                binding?.imgStarOne93?.setImageResource(android.R.drawable.btn_star_big_on)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨

                binding?.btnChapter10?.setImageResource(R.drawable.island1_10) //다음 챕터섬을 보이도록 만들어줍니다.
                binding?.btnChapter10?.isEnabled = true  //다음 챕터를 선택할 수 있게 만들어줍니다.
            }
        }

        val bundle = Bundle()  //몇 챕터를 선택할지 이 번들에 넣어서 알려줍니다.
        val totalCorrect = 0  //모든 시작을 여기서 한다고 치자.

        binding?.btnGoTo2?.setOnClickListener {
            findNavController().navigate(R.id.action_stageOneFragment_to_stageTwoFragment)
        }

        binding?.btnChapter1?.setOnClickListener {
            //binding?.imgStarOne11?.setImageResource(android.R.drawable.btn_star_big_off)  //이 기능을 이용해서 별 색깔을 바꿔주면 됨
            bundle.putInt("chapterNumber", 1)  //챕터 1임을 알려줍니다.
            bundle.putInt("totalCorrect", totalCorrect)  //초기의 시작은 0이니까
            findNavController().navigate(R.id.action_stageOneFragment_to_introduceFragment, bundle)
        }

        binding?.btnChapter2?.setOnClickListener {
            bundle.putInt("chapterNumber", 2)  //챕터 2임을 알려줍니다.
            bundle.putInt("totalCorrect", totalCorrect)  //초기의 시작은 0이니까
            findNavController().navigate(R.id.action_stageOneFragment_to_introduceFragment, bundle)
        }

        binding?.btnChapter3?.setOnClickListener {
            bundle.putInt("chapterNumber", 3)  //챕터3임을 알려줍니다.
            bundle.putInt("totalCorrect", totalCorrect)  //초기의 시작은 0이니까
            findNavController().navigate(R.id.action_stageOneFragment_to_introduceFragment, bundle)
        }

        binding?.btnChapter4?.setOnClickListener {
            bundle.putInt("chapterNumber", 4)  //탭터4임을 알려줍니다다
            bundle.putInt("totalCorrect", totalCorrect)  //초기의 시작은 0이니까
            findNavController().navigate(R.id.action_stageOneFragment_to_introduceFragment, bundle)
        }

        binding?.btnChapter5?.setOnClickListener {
            bundle.putInt("chapterNumber", 5)  //챕터5임을 알려줍니다
            bundle.putInt("totalCorrect", totalCorrect)  //초기의 시작은 0이니까
            findNavController().navigate(R.id.action_stageOneFragment_to_introduceFragment, bundle)
        }

        binding?.btnChapter6?.setOnClickListener {
            bundle.putInt("chapterNumber", 6)  //챕터6임을 알려줍니다
            bundle.putInt("totalCorrect", totalCorrect)  //초기의 시작은 0이니까
            findNavController().navigate(R.id.action_stageOneFragment_to_introduceFragment, bundle)
        }

        binding?.btnChapter7?.setOnClickListener {
            bundle.putInt("chapterNumber", 7)  //챕터7임을 알려줍니다
            bundle.putInt("totalCorrect", totalCorrect)  //초기의 시작은 0이니까
            findNavController().navigate(R.id.action_stageOneFragment_to_introduceFragment, bundle)
        }

        binding?.btnChapter8?.setOnClickListener {
            bundle.putInt("chapterNumber", 8)  //챕터8임을 알려줍니다
            bundle.putInt("totalCorrect", totalCorrect)  //초기의 시작은 0이니까
            findNavController().navigate(R.id.action_stageOneFragment_to_introduceFragment, bundle)
        }

        binding?.btnChapter9?.setOnClickListener {
            bundle.putInt("chapterNumber", 9)  //챕터9임을 알려줍니다
            bundle.putInt("totalCorrect", totalCorrect)  //초기의 시작은 0이니까
            findNavController().navigate(R.id.action_stageOneFragment_to_introduceFragment, bundle)
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
package com.example.codingnatorpoject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.codingnatorpoject.databinding.FragmentIntroduceThreeBinding

class IntroduceThreeFragment : Fragment() {
    private var chapterNumber: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            chapterNumber = it.getInt("chapterNumber")
        }
    }

    var binding: FragmentIntroduceThreeBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIntroduceThreeBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(chapterNumber == 1){
            binding?.imgIntro?.setImageResource(R.drawable.stagethreeintroduce1)  //UI가 잘 작동하는지 확인차 stagethreeintroduce1만 넣어봄
        }

        if(chapterNumber == 2){
            //binding?.imgIntro?.setImageResource(R.drawable.introduce2)
        }

        if(chapterNumber == 3){
            //binding?.imgIntro?.setImageResource(R.drawable.introduce3)
        }

        if(chapterNumber == 4){
            //binding?.imgIntro?.setImageResource(R.drawable.introduce4)
        }

        if(chapterNumber == 5){
            //binding?.imgIntro?.setImageResource(R.drawable.introduce5)
        }

        if(chapterNumber == 6){
            //binding?.imgIntro?.setImageResource(R.drawable.introduce6)
        }

        if(chapterNumber == 7){
            //binding?.imgIntro?.setImageResource(R.drawable.introduce7)
        }

        if(chapterNumber == 8){
            //binding?.imgIntro?.setImageResource(R.drawable.introduce8)
        }

        if(chapterNumber == 9){
            //binding?.imgIntro?.setImageResource(R.drawable.introduce9)
        }

        binding?.btnBack?.setOnClickListener {  //다시 메인화면으로 보내줍니다
            findNavController().popBackStack()  //해당 프래그먼트를 뒤로 보내는 역할을 해준다.
        }

        binding?.btnQuiz?.setOnClickListener {
            val bundle = Bundle().apply {
                putInt("chapterNumber", chapterNumber!!)
            }
            findNavController().navigate(R.id.action_introduceThreeFragment_to_OXQuizThreeFragment, bundle)
        }
    }
}
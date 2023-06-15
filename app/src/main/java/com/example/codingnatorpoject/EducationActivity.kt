package com.example.codingnatorpoject

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.OnBackPressedCallback
import android.app.AlertDialog

class EducationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_education)

        val thisActivity = this
        // 뒤로 가는 버튼(디바이스 버튼)을 눌렀을 때 확인 메시지를 띄운 뒤 MainActivity로 돌아가게 함
        // 문제풀이 중 뒤로 가는 행위를 반복해 정답 문제의 수를 의도하지 않은 범위까지 늘릴 수 있는 문제를 해결하기 위함
        this.onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                AlertDialog.Builder(thisActivity)
                    .setMessage("문제풀이를 종료하시겠습니까?")
                    .setNegativeButton("아니오") { _, _ ->
                        Log.i("EduActivity", "Back key Canceled")}
                    .setPositiveButton("예") { _, _ ->
                        Log.i("EduActivity", "Back key Confirmed")
                        thisActivity.finish()
                    }
                    .setTitle("돌아가기")
                    .create()
                    .show()
            }
        })
    }
}
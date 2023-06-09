package com.example.codingnatorpoject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.codingnatorpoject.DBConnection.QuestionRepository

class LogActivity : AppCompatActivity() {

    lateinit var repo: QuestionRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repo = QuestionRepository(
            applicationContext
        )
        setContentView(R.layout.activity_log)
    }
}
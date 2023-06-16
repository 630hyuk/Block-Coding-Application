package com.example.codingnatorpoject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.codingnatorpoject.DBConnection.QuestionRepository
import com.example.codingnatorpoject.DBConnection.UserManager

class LogActivity : AppCompatActivity() {

    lateinit var repo: QuestionRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repo = QuestionRepository(
            applicationContext
        )
        UserManager.getUserList()
        setContentView(R.layout.activity_log)
    }
}
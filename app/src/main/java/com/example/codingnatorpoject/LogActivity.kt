package com.example.codingnatorpoject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.codingnatorpoject.DBConnection.QuestionRepository
import com.example.codingnatorpoject.DBConnection.User

class LogActivity : AppCompatActivity() {

    lateinit var repo: QuestionRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repo = QuestionRepository(
            applicationContext
        )
        User.getUserList()
        setContentView(R.layout.activity_log)
    }
}
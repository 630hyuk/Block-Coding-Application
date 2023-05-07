package com.example.codingnatorpoject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.codingnatorpoject.DBConnection.DatabaseConnector

class LogActivity : AppCompatActivity() {

    lateinit var dbc: DatabaseConnector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dbc = DatabaseConnector(applicationContext)
        setContentView(R.layout.activity_log)
    }
}
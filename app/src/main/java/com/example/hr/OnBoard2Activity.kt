package com.example.hr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class OnBoard2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_board2)
        supportActionBar?.hide()

        val btnOnboard2 = findViewById<Button>(R.id.btn_onboard2)
        btnOnboard2.setOnClickListener{
            val intentBoard2 = Intent(this, OnBoard3Activity::class.java)
            startActivity(intentBoard2)
        }
    }
}
package com.example.hr.onboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.hr.R

class OnBoard1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_board1)
        supportActionBar?.hide()

        val btnOnboard1 = findViewById<Button>(R.id.btn_onboard1)

        btnOnboard1.setOnClickListener{
            val intentBoard1 = Intent(this, OnBoard2Activity::class.java)
            startActivity(intentBoard1)
        }
    }
}
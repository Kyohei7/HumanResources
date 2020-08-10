package com.example.hr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class OnBoard3Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_board3)
        supportActionBar?.hide()

        val btnOnboard3 = findViewById<Button>(R.id.btn_onboard3)
        btnOnboard3.setOnClickListener{
            val intentBoard3 = Intent(this, RegisterActivity::class.java)
            startActivity(intentBoard3)
        }
    }
}
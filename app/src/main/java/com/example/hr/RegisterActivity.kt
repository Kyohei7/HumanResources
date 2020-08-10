package com.example.hr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        supportActionBar?.hide()

        val btnRegister = findViewById<Button>(R.id.btn_register)
        btnRegister.setOnClickListener{
            val intentRegister = Intent(this, LoginActivity::class.java)
            startActivity(intentRegister)
        }

    }
}
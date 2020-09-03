package com.example.hr.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.hr.login.LoginActivity
import com.example.hr.R

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
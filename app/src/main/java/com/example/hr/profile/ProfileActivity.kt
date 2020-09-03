package com.example.hr.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.hr.R

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        supportActionBar?.hide()

        val intent = intent
        val fullname = intent.getStringExtra("Fullnamesform")
        val email = intent.getStringExtra("Emailsform")
        val skill = intent.getStringExtra("Skillsform")
        val phone = intent.getStringExtra("Phonesform")
        val github = intent.getStringExtra("Githubsform")

        val resultFullname = findViewById<TextView>(R.id.tv_fullname)
        resultFullname.text = fullname

        val resultSkill = findViewById<TextView>(R.id.tv_skill)
        resultSkill.text = skill

        val resultEmail = findViewById<TextView>(R.id.tv_email)
        resultEmail.text = email

        val resultPhone = findViewById<TextView>(R.id.tv_phone)
        resultPhone.text = phone

        val resultGithub = findViewById<TextView>(R.id.tv_github)
        resultGithub.text = github




    }
}
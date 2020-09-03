package com.example.hr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.hr.profile.ProfileActivity

class InputActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)
        supportActionBar?.hide()

        // create variabel for components
        val fullnames = findViewById<EditText>(R.id.et_fullnames)
        val emails = findViewById<EditText>(R.id.et_emails)
        val skills = findViewById<EditText>(R.id.et_skills)
        val phones = findViewById<EditText>(R.id.et_phones)
        val githubs = findViewById<EditText>(R.id.et_githubs)
        val btnSubmit = findViewById<Button>(R.id.btn_submit)

        btnSubmit.setOnClickListener{
            val fullnamesEt = fullnames.text.toString()
            val emailsEt = emails.text.toString()
            val skillsEt = skills.text.toString()
            val phonesEt = phones.text.toString()
            val githubEt = githubs.text.toString()

            val intent = Intent(this, ProfileActivity::class.java)
            intent.putExtra("Fullnamesform", "$fullnamesEt" )
            intent.putExtra("Emailsform", "$emailsEt" )
            intent.putExtra("Skillsform", "$skillsEt")
            intent.putExtra("Phonesform", "$phonesEt")
            intent.putExtra("Githubsform", "$githubEt")
            startActivity(intent)

        }




    }
}
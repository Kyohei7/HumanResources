package com.example.hr

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.example.hr.onboard.OnBoard1Activity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        // Splash Screen
        Handler().postDelayed({
            val intent = Intent(this@MainActivity, OnBoard1Activity::class.java)
            startActivity(intent)
        }, 3000 )
    }
}
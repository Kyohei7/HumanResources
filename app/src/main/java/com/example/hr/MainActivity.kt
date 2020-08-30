package com.example.hr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.core.content.ContextCompat.startActivity

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
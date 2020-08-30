package com.example.hr

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    lateinit var mActivity: AppCompatActivity

    override fun onCreate(saveInstanceState: Bundle?) {
        super.onCreate(saveInstanceState)
        mActivity = this
    }

    protected inline fun <reified ClassActivity>
            baseStartActivity(content: Context) {
            content.startActivity(Intent(content, ClassActivity::class.java))
    }
}
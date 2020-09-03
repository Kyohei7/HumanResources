package com.example.hr.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputBinding
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil
import com.example.hr.R
import com.example.hr.databinding.ActivityWebViewGithubBinding
import kotlinx.android.synthetic.main.activity_web_view_github.*

class WebViewGithub : AppCompatActivity() {

    private lateinit var binding: ActivityWebViewGithubBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view_github)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_web_view_github)


        binding.wvGithub.loadUrl("https://github.com/Kyohei7")
    }
}
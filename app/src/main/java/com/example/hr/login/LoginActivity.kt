package com.example.hr.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.hr.R
import com.example.hr.helper.Constant
import com.example.hr.helper.PreferencesHelper
import com.example.hr.home.HomeActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    lateinit var sharepreferences: PreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()
        sharepreferences = PreferencesHelper(this)

        btn_login.setOnClickListener {
            if (et_signin_username.text.isNotEmpty() && et_signin_password.text.isNotEmpty()) {
                saveSession(et_signin_username.text.toString(), et_signin_password.text.toString())
                showMessage("Login Success!")
                moveIntent()
            }
        }
    }


    override fun onStart() {
        super.onStart()
        if (sharepreferences.getBoolean( Constant.PREFERENCES_IS_LOGIN)) {
            moveIntent()
        }
    }

    private fun moveIntent() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }

    private fun saveSession(username: String, password: String ) {
        sharepreferences.put(Constant.PREFERENCES_IS_USERNAME, username)
        sharepreferences.put(Constant.PREFERENCES_IS_PASSWORD, password)
        sharepreferences.put(Constant.PREFERENCES_IS_LOGIN, true)
    }

    private fun showMessage(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

}
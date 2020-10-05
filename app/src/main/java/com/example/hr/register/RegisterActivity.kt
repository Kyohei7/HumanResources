package com.example.hr.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.hr.login.LoginActivity
import com.example.hr.R
import com.example.hr.auth.ApiClient
import com.example.hr.auth.AuthApiService
import com.example.hr.auth.RegisterResponse
import com.example.hr.databinding.ActivityRegisterBinding
import com.example.hr.util.SharePreference
import kotlinx.coroutines.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var sharedpref: SharePreference



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        sharedpref = SharePreference(this)
        supportActionBar?.hide()

        binding.btnRegister.setOnClickListener {
            callAuthApi()
        }

    }

    private fun callAuthApi() {

        val service = ApiClient.getApiClient(this)?.create(AuthApiService::class.java)
        val coroutineScope = CoroutineScope(Job() + Dispatchers.Main)

        coroutineScope.launch {
            Log.d("test", "register = ${Thread.currentThread().name}")
            val response = withContext(Dispatchers.IO) {
                Log.d("test", "Call API = ${Thread.currentThread().name}")
                try {
                    service?.registerRequest(
                        binding.etSignupUsername.text.toString(),
                        binding.etSignupEmail.text.toString(),
                        binding.etSignupPassword.text.toString(),
                        binding.etSignupRole.text.toString()
                    )
                } catch (e: Throwable) {
                    e.printStackTrace()
                }
            }

            if (response is RegisterResponse) {
                Toast.makeText(this@RegisterActivity, "Register Success", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this@RegisterActivity, "Register Failed", Toast.LENGTH_SHORT).show()
            }
            Log.d("test", "response = $response")
        }
    }
}


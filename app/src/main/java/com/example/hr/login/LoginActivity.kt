package com.example.hr.login
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.hr.R
import com.example.hr.auth.ApiClient
import com.example.hr.auth.AuthApiService
import com.example.hr.auth.LoginResponse
import com.example.hr.databinding.ActivityLoginBinding
import com.example.hr.home.HomeActivity
import com.example.hr.util.SharePreference
import com.example.hr.util.SharedPreferencesKey
import kotlinx.coroutines.*


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var sharedpref: SharePreference



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        sharedpref = SharePreference(this)

        binding.btnLogin.setOnClickListener {
            callAuthApi()
        }

    }

    private fun callAuthApi() {
        val service = ApiClient.getApiClient(this)?.create(AuthApiService::class.java)

        val coroutineScope = CoroutineScope(Job() + Dispatchers.Main)
        coroutineScope.launch {
            Log.d("test", "login = ${Thread.currentThread().name}")

            val response = withContext(Dispatchers.IO) {
                Log.d("test", "call API = ${Thread.currentThread().name}")

                try {
                    service?.loginRequest(
                        binding.etSigninUsername.text.toString(),
                        binding.etSigninPassword.text.toString()
                    )
                } catch (e: Throwable) {
                    e.printStackTrace()
                }
            }

            if (response is LoginResponse) {
                if (response.message == "Success to Login") {
                    sharedpref.put(SharedPreferencesKey.PREF_TOKEN, response.token.toString())
                    Toast.makeText(this@LoginActivity, "Login Success", Toast.LENGTH_SHORT).show()
                    val intentLogin = Intent(this@LoginActivity, HomeActivity::class.java)
                    startActivity(intentLogin)
                } else {
                    Toast.makeText(
                        this@LoginActivity,
                        "${response.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                Log.d("test", "response = $response")
            }

        }
    }
}






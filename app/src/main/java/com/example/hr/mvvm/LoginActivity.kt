package com.example.hr.mvvm
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.hr.R
import com.example.hr.databinding.ActivityLoginBinding
import com.example.hr.helper.Constant
import com.example.hr.helper.PreferencesHelper
import com.example.hr.home.HomeActivity
import com.example.hr.remote.ApiClient


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var sharePreferencesHelper: PreferencesHelper
    private lateinit var viewModel: LoginViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        sharePreferencesHelper = PreferencesHelper(this)

        val service = ApiClient.getApiClient(this)?.create(AuthApiService::class.java)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        viewModel.setSharePreference(sharePreferencesHelper)

        if (service != null) {
            viewModel.setLoginService(service)
        }

        binding.btnLogin.setOnClickListener {
            viewModel.CallApiLogin(binding.etSigninUsername.text.toString(), binding.etSigninPassword.text.toString())
        }

        subscribeLiveData()
    }


    private fun moveIntent() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }

    override fun onStart() {
        super.onStart()
        if (sharePreferencesHelper.getBoolean(Constant.PREFERENCES_IS_LOGIN)) {
            moveIntent()
        }
    }

    override fun onDestroy() {
        if (!sharePreferencesHelper.getBoolean(Constant.PREFERENCES_IS_LOGIN))
        super.onDestroy()
    }

    private fun subscribeLiveData() {
        viewModel.isLoginLiveData.observe(this, Observer {
            Log.d("android1", "$it")

            if (it) {
                Toast.makeText(this, "Login Succcess", Toast.LENGTH_SHORT).show()
                moveIntent()
                finish()
            } else {
                Toast.makeText(this, "Login Failed!", Toast.LENGTH_SHORT).show()
            }
        })
    }
}






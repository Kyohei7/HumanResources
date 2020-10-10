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
import com.example.hr.databinding.ActivityRegisterBinding
import com.example.hr.helper.PreferencesHelper
import com.example.hr.remote.ApiClient


class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var sharePreferencesHelper: PreferencesHelper
    private lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        sharePreferencesHelper = PreferencesHelper(this)

        val service = ApiClient.getApiClient(this)?.create(AuthApiService::class.java)

        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
        viewModel.setSharePreference(sharePreferencesHelper)

        if (service != null) {
            viewModel.setRegisterService(service)
        }

        binding.btnRegister.setOnClickListener {
            viewModel.CallApiRegister(
                binding.etSignupUsername.text.toString(),
                binding.etSignupEmail.text.toString(),
                binding.etSignupPassword.text.toString(),
                binding.etSignupRole.text.toString())
        }

        subscribeLiveData()

    }

    private fun subscribeLiveData() {
        viewModel.isRegisterLiveData.observe(this, Observer {
            Log.d("android1", "$it")

            if (it) {
                Toast.makeText(this, "Register Succcess", Toast.LENGTH_SHORT).show()
                // Intent
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)

                // Finish Activity
                finish()
            } else {
                Toast.makeText(this, "Register Failed!", Toast.LENGTH_SHORT).show()
            }
        })
    }
}


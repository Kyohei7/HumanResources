package com.example.hr.mvvm

import android.content.SharedPreferences
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hr.helper.Constant
import com.example.hr.helper.PreferencesHelper
import com.example.hr.util.SharePreferenceActivity
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class LoginViewModel : ViewModel(), CoroutineScope {


    val isLoginLiveData = MutableLiveData<Boolean>()

    private lateinit var service: AuthApiService
    private lateinit var sharedPreferences: PreferencesHelper

    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.Main

    fun setSharePreference(sharedPreferences: PreferencesHelper) {
        this.sharedPreferences = sharedPreferences
    }

    fun setLoginService(service: AuthApiService) {
        this.service = service
    }

    fun CallApiLogin(email: String, password: String) {

        launch {

            val response = withContext(Dispatchers.IO) {

                try {
                    service?.loginRequest(email, password)
                } catch (e: Throwable) {
                    e.printStackTrace()

                    withContext(Dispatchers.Main) {
                        isLoginLiveData.value = false
                    }

                }

            }

            if (response is LoginResponse) {

                if (response.message == "Success to Login") {
                    // Save Token in Share Preferences
                    saveSession(response.token.toString())

                    isLoginLiveData.value = true

                } else {
                    isLoginLiveData.value = false
                }
            }
        }
    }

    private fun saveSession(token: String) {
        sharedPreferences.put(Constant.PREFERENCES_IS_TOKEN, token)
    }
}
package com.example.hr.home.details

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hr.helper.Constant
import com.example.hr.helper.PreferencesHelper
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

class DetailsDeveloperViewModel: ViewModel() {

    val isResponseDetailsDev = MutableLiveData<DetailsDeveloperResponse>()

    private lateinit var service: DetailsDeveloperApiService
    private lateinit var sharePref: PreferencesHelper

    fun setSharePref(sharePref: PreferencesHelper) {
        this.sharePref = sharePref
    }

    fun setDetailsService(service: DetailsDeveloperApiService) {
        this.service = service
    }

    fun callDetailsDevApi() {
        val idDev = sharePref.getString(Constant.PREFERENCE_IS_ID_DEV)
        Log.d("idDev", "${idDev}")
        service.getAllDevelopersById(idDev).enqueue(object : Callback<DetailsDeveloperResponse> {
            override fun onFailure(call: Call<DetailsDeveloperResponse>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<DetailsDeveloperResponse>,
                response: Response<DetailsDeveloperResponse>
            ) {
                isResponseDetailsDev.value = response.body()
                Log.d("Request Body", "${response.body()}")
            }

        })
    }

}
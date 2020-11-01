package com.example.hr.profile

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hr.helper.Constant
import com.example.hr.helper.PreferencesHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetProfileViewModel: ViewModel() {

    val isResponseGetProfile = MutableLiveData<GetProfileResponse>()
    private lateinit var service: ProfileApiService
    private lateinit var sharePref: PreferencesHelper

    fun setSharePref(sharePref: PreferencesHelper) {
        this.sharePref = sharePref
    }

    fun setServiceProfile(service: ProfileApiService) {
        this.service = service
    }

    fun getApiCompanyProfile() {
        service.getProfilebyIdUser(sharePref.getString(Constant.PREFERENCES_ID)).enqueue(object : Callback<GetProfileResponse> {
            override fun onFailure(call: Call<GetProfileResponse>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<GetProfileResponse>,
                response: Response<GetProfileResponse>
            ) {
                isResponseGetProfile.value = response.body()
                val id = sharePref.putString(Constant.PREFERENCE_IS_ID_COMPANY, response.body()?.data?.idCompany)
            }

        })
    }
}
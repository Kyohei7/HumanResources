package com.example.hr.projects.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hr.helper.Constant
import com.example.hr.helper.PreferencesHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsProjectViewModel: ViewModel() {

    val isDetailsProjectResponse = MutableLiveData<DetailsProjectResponse>()
    private lateinit var service: DetailsProjectApiService
    private lateinit var sharePref: PreferencesHelper

    fun setSharePref(sharePref: PreferencesHelper) {
        this.sharePref = sharePref
    }

    fun setServiceDetailsProject(service: DetailsProjectApiService) {
        this.service = service
    }

    fun callApiDetailsProject() {
        val id = sharePref.getString(Constant.PREFERENCE_IS_PROJECT)
        service.getDetailsProject(id).enqueue(object : Callback<DetailsProjectResponse> {
            override fun onFailure(call: Call<DetailsProjectResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(
                call: Call<DetailsProjectResponse>,
                response: Response<DetailsProjectResponse>
            ) {
                isDetailsProjectResponse.value = response.body()
            }
        })
    }
}
package com.example.hr.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hr.helper.Constant
import com.example.hr.helper.PreferencesHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel: ViewModel() {

    val isResponseAdapter = MutableLiveData<List<DevelopersModel>>()

    private lateinit var service: DevelopersApiService
    private lateinit var sharePref: PreferencesHelper

    fun setSharePref(sharePref: PreferencesHelper ) {
        this.sharePref = sharePref
    }

    fun setDeveloperService(service: DevelopersApiService) {
        this.service = service
    }

    fun callApiDev() {
        service.getAllDevelopers().enqueue(object : Callback<DevelopersResponse> {
            override fun onFailure(call: Call<DevelopersResponse>, t: Throwable) {
                Log.e("Home", t.message ?: "error")
            }

            override fun onResponse(
                call: Call<DevelopersResponse>,
                response: Response<DevelopersResponse>
            ) {
                Log.d("response get developer", "${response.body()}")
                val list = response.body()?.data?.map {
                    DevelopersModel(
                        it.id.orEmpty(),
                        it.photo.orEmpty(),
                        it.name.orEmpty(),
                        it.job.orEmpty()
                    )
                } ?: listOf()
                isResponseAdapter.value = list
            }
        })

        val idCompany = sharePref.getString(Constant.PREFERENCE_IS_ID_COMPANY)
        val iduser = sharePref.getString(Constant.PREFERENCES_ID)
        Log.d("idUser", "$iduser")
        if (idCompany == null) {
            service.getCompanyId(iduser).enqueue(object : Callback<CompanyByIdUserResponse>{
                override fun onFailure(call: Call<CompanyByIdUserResponse>, t: Throwable) {
                    Log.d("error", "$t")
                }

                override fun onResponse(
                    call: Call<CompanyByIdUserResponse>,
                    response: Response<CompanyByIdUserResponse>
                ) {
                    Log.d("response id comp", "${response.body()}")
                    val IDCOMPANY = sharePref.putString(Constant.PREFERENCE_IS_ID_COMPANY, response.body()?.data?.idCompany)
                    Log.d("IDCOMPANY", "$IDCOMPANY")
                }

            })
        }
    }
}
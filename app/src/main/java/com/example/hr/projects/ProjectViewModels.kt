package com.example.hr.projects

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

class ProjectViewModels: ViewModel() {

    val isProjectResponse = MutableLiveData<List<ProjectsModel>>()
    val isDeleteProject = MutableLiveData<Void>()
    private lateinit var service: ProjectsApiService
    private lateinit var sharePref : PreferencesHelper

    fun setSharePref(sharePref: PreferencesHelper) {
        this.sharePref = sharePref
    }

    fun setProjectService(service: ProjectsApiService) {
        this.service = service
    }

    fun getProjectByidCompany() {
        val id = sharePref.getString(Constant.PREFERENCE_IS_ID_COMPANY)
        service.getAllProjectData(id).enqueue(object : Callback<ProjectsByIdCompanyResponse> {
            override fun onFailure(call: Call<ProjectsByIdCompanyResponse>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<ProjectsByIdCompanyResponse>,
                response: Response<ProjectsByIdCompanyResponse>
            ) {
                val list = response.body()?.data?.map {
                    ProjectsModel(
                        it.idProject.orEmpty(),
                        it.nameProject.orEmpty(),
                        it.deadline.orEmpty(),
                        it.photo.orEmpty()
                    )
                } ?: listOf()
                isProjectResponse.value = list
            }

        })
    }

    fun delete(id: String) {
        service?.deleteProject(id)?.enqueue(object : Callback<Void> {
            override fun onFailure(call: Call<Void>, t: Throwable) {

            }

            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                isDeleteProject.value = response.body()
            }

        })
    }
}
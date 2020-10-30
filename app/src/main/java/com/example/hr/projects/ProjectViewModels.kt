package com.example.hr.projects

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hr.helper.Constant
import com.example.hr.helper.PreferencesHelper
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class ProjectViewModels: ViewModel(), CoroutineScope {

    private lateinit var service: ProjectsApiService
    val isLoadingLiveData = MutableLiveData<Boolean>()
    val listLiveData = MutableLiveData<List<ProjectsModel>>()
    private lateinit var sharePref : PreferencesHelper

    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.Main

    fun setProjectService(service: ProjectsApiService) {
        this.service = service
    }

    fun callApiProject() {
//        val id = sharePref.getString(Constant.PREFERENCE_IS_ID_COMPANY)
        launch {
            isLoadingLiveData.value = true
            val response = withContext(Dispatchers.IO) {
                try {
                    service.getAllProjectData("3")
                } catch (e: Throwable) {
                    e.printStackTrace()
                }
            }
            if (response is ProjectsByIdCompanyResponse) {
                val list = response.data?.map {
                    ProjectsModel(
                        it.idProject.toString(),
                        it.nameProject.toString(),
                        it.deadline.toString(),
                        it.photo.toString()
                    )
                }
                listLiveData.value = list
            }
                isLoadingLiveData.value = false
        }
    }
}
package com.example.hr.projects.edit

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hr.helper.Constant
import com.example.hr.helper.PreferencesHelper
import com.example.hr.projects.ProjectsApiService
import com.example.hr.projects.details.DetailsProjectResponse
import com.example.hr.projects.post.PostProjectResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditProjectViewModel: ViewModel() {

    private lateinit var service: ProjectsApiService
    private lateinit var sharePref: PreferencesHelper

    val isResponseGetProject = MutableLiveData<ProjectByIdResponse>()
    val isResponsePostProject = MutableLiveData<PostProjectResponse>()
    val isResponsePutProject = MutableLiveData<Boolean>()

    fun setSharePref(sharePref: PreferencesHelper) {
        this.sharePref = sharePref
    }

    fun setEditProjectService(service: ProjectsApiService) {
        this.service = service
    }

    fun getDataProject() {
        val idProject = sharePref.getString(Constant.PREFERENCE_IS_PROJECT)

        service.getProjectById(idProject)?.enqueue(object : Callback<ProjectByIdResponse> {
            override fun onFailure(call: Call<ProjectByIdResponse>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<ProjectByIdResponse>,
                response: Response<ProjectByIdResponse>
            ) {
                isResponseGetProject.value = response.body()
            }

        })

    }

    fun postDataProject(
        idComp: RequestBody,
        Name: RequestBody,
        Description: RequestBody,
        Deadline: RequestBody,
        photo: MultipartBody.Part?
    ) {
        service.postDataProject(idComp, Name, Description, Deadline, photo)?.enqueue(object : Callback<PostProjectResponse> {
            override fun onFailure(call: Call<PostProjectResponse>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<PostProjectResponse>,
                response: Response<PostProjectResponse>
            ) {
                isResponsePostProject.value = response.body()
            }

        })
    }

    fun putDataProject(
        idComp: RequestBody,
        Name: RequestBody,
        Description: RequestBody,
        Deadline: RequestBody,
        photo: MultipartBody.Part?
    ) {
        val idProject = sharePref.getString(Constant.PREFERENCE_IS_PROJECT)
        Log.d("IDPROJECT", "$idProject")
        service?.putDataProject(idProject, idComp, Name, Description, Deadline, photo)?.enqueue(object :Callback<Void> {
            override fun onFailure(call: Call<Void>, t: Throwable) {

            }

            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                isResponsePutProject.value = true
            }

        })
    }
}
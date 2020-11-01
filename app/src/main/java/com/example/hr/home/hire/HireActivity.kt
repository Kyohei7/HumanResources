package com.example.hr.home.hire

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.hr.R
import com.example.hr.databinding.ActivityHireBinding
import com.example.hr.helper.Constant
import com.example.hr.helper.PreferencesHelper
import com.example.hr.projects.ProjectsApiService
import com.example.hr.projects.ProjectsByIdCompanyResponse
import com.example.hr.projects.ProjectsModel
import com.example.hr.remote.ApiClient
import kotlinx.android.synthetic.main.activity_hire.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HireActivity : AppCompatActivity() {

    lateinit var binding: ActivityHireBinding
    private lateinit var sharePref: PreferencesHelper
    private var selectProject = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_hire)
        sharePref = PreferencesHelper(applicationContext)
        projectSpinner()

        binding.btnHire.setOnClickListener {
            callApiHire()
        }
    }

    private fun callApiHire() {
        val idDev = sharePref.getString(Constant.PREFERENCE_IS_ID_DEV)
        val description = binding.description.text.toString()
        val price = binding.price.text.toString()
        val status = binding.status.text.toString()
        val service = ApiClient.getApiClient(this)?.create(HireApiService::class.java)

        service?.postHire(
             "$selectProject",
             "$idDev",
             "$description",
             "${price}",
             "${status}")?.enqueue(object : Callback<PostHireResponse> {
            override fun onFailure(call: Call<PostHireResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(
                call: Call<PostHireResponse>,
                response: Response<PostHireResponse>
            ) {
                Toast.makeText(this@HireActivity, "Sent!", Toast.LENGTH_SHORT).show()
                finish()
            }
        })
    }

    private fun projectSpinner() {
        var spinner = binding.spinner as Spinner
        val service = ApiClient.getApiClient(this)?.create(ProjectsApiService::class.java)
        var idCompany = sharePref.getString(Constant.PREFERENCE_IS_ID_COMPANY)
        service?.getAllProjectData(idCompany)?.enqueue(object : Callback<ProjectsByIdCompanyResponse> {
            override fun onFailure(call: Call<ProjectsByIdCompanyResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(
                call: Call<ProjectsByIdCompanyResponse>,
                response: Response<ProjectsByIdCompanyResponse>
            ) {
                val listProject = response.body()?.data?.map {
                    ProjectsModel(
                        it.idProject.orEmpty(),
                        it.nameProject.orEmpty(),
                        it.deadline.orEmpty(),
                        it.photo.orEmpty()
                    )
                } ?: listOf()
                spinner.adapter = ArrayAdapter<String>(this@HireActivity, R.layout.support_simple_spinner_dropdown_item, listProject.map {
                    it.name_project
                })
                spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(p0: AdapterView<*>?) {

                    }

                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long) {
                        selectProject = listProject[position].id.toString()
                    }

                }
            }
        })
    }
}
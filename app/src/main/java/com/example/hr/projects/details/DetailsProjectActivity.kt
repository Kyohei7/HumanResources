package com.example.hr.projects.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.hr.R
import com.example.hr.databinding.ActivityDetailsDeveloperBinding
import com.example.hr.databinding.ActivityDetailsProjectBinding
import com.example.hr.helper.PreferencesHelper
import com.example.hr.remote.ApiClient
import com.squareup.picasso.Picasso

class DetailsProjectActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsProjectBinding
    private lateinit var viewModel: DetailsProjectViewModel
    private lateinit var sharePref: PreferencesHelper



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details_project)
        sharePref = PreferencesHelper(applicationContext)


        val service = ApiClient.getApiClient(this)?.create(DetailsProjectApiService::class.java)
        viewModel = ViewModelProvider(this).get(DetailsProjectViewModel::class.java)
        viewModel.setSharePref(sharePref)
        if (service != null) {
            viewModel.setServiceDetailsProject(service)
        }

        viewModel.callApiDetailsProject()
        subscribeLiveData()


    }

    private fun subscribeLiveData() {
        viewModel.isDetailsProjectResponse.observe(this, Observer {
            binding.tvNameProject.text = it.data.nameProject
            Picasso.get().load("http://18.234.106.45:8080/uploads" + it.data.photo)
                .into(binding.photoProject)
        })
    }
}
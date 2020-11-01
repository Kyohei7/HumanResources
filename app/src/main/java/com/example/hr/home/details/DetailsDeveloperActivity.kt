package com.example.hr.home.details

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.hr.R
import com.example.hr.databinding.ActivityDetailsDeveloperBinding
import com.example.hr.helper.PreferencesHelper
import com.example.hr.home.HomeFragment
import com.example.hr.home.hire.HireActivity
import com.example.hr.remote.ApiClient
import com.squareup.picasso.Picasso

class DetailsDeveloperActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsDeveloperBinding
    private lateinit var sharePref : PreferencesHelper
    private lateinit var viewModel: DetailsDeveloperViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details_developer)
        sharePref = PreferencesHelper(applicationContext)
        val service = ApiClient.getApiClient(this)?.create(DetailsDeveloperApiService::class.java)

        viewModel = ViewModelProvider(this).get(DetailsDeveloperViewModel::class.java)
        viewModel.setSharePref(sharePref)

        if (service != null) {
            viewModel.setDetailsService(service)
        }

        binding.btnHire.setOnClickListener {
            val intent = Intent(this, HireActivity::class.java)
            startActivity(intent)
        }

        viewModel.callDetailsDevApi()
        subscribeLiveData()


    }

    private fun subscribeLiveData() {
        viewModel.isResponseDetailsDev.observe(this, Observer {
            binding.nameDeveloper.text = it.data.nameDeveloper
            Log.d("Name", "${it.data.nameDeveloper}")
            binding.job.text = it.data.job
            Picasso.get().load("http://18.234.106.45:8080/uploads/" + it.data.photo)
                .into(binding.photoProfile)
            binding.email.text = it.data.email
            binding.location.text = it.data.location
        })
    }
}
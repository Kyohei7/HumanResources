package com.example.hr.util

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.databinding.DataBindingUtil
import com.example.hr.R
import com.example.hr.databinding.ActivitySharePreferenceBinding
import com.example.hr.helper.PreferencesHelper

class SharePreferenceActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySharePreferenceBinding

    companion object {
        const val SHARED_PREFERENCES_NAME = "HumanResource"
        const val KEY_EMAIL = "KEY_EMAIL"
        const val KEY_TOKEN = "KEY_TOKEN"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_share_preference)

        val defaultSharedPref = PreferencesHelper(this)

    }
}
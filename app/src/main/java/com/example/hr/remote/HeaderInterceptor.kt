package com.example.hr.remote

import android.content.Context
import android.util.Log
import com.example.hr.helper.Constant
import com.example.hr.helper.PreferencesHelper
import com.example.hr.mvvm.LoginActivity
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor(var mContext: Context) : Interceptor {

    private lateinit var sharePreferencesHelper: PreferencesHelper

    override fun intercept(chain: Interceptor.Chain): Response = chain.run {

        sharePreferencesHelper = PreferencesHelper(mContext)

        val token = sharePreferencesHelper.getString(Constant.PREFERENCES_IS_TOKEN)
        Log.d("token", token)
        proceed(
            request().newBuilder()
                .addHeader("token", "$token")
                .build()
        )
    }
}
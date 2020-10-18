package com.example.hr.remote

import android.content.Context
import com.example.hr.helper.Constant
import com.example.hr.helper.PreferencesHelper
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor(var mContext: Context) : Interceptor {

    private lateinit var sharePreferencesHelper: PreferencesHelper

    override fun intercept(chain: Interceptor.Chain): Response = chain.run {

        sharePreferencesHelper = PreferencesHelper(mContext)

        val token = sharePreferencesHelper.getString(Constant.PREFERENCES_IS_TOKEN)
        proceed(
            request().newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
        )
    }
}
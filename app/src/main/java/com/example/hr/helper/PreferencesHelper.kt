package com.example.hr.helper

import android.content.Context
import android.content.SharedPreferences
import android.os.Build.VERSION_CODES.M

class PreferencesHelper(context: Context) {

    private val PREFERENCES_NAME = "sharepreferenceshumanresources"
    private var sharepreferences: SharedPreferences
    val editor: SharedPreferences.Editor

    init {
        sharepreferences    = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        editor              = sharepreferences.edit()
    }

    fun put(key: String, value: String) {
        editor.putString(key, value)
            .apply()
    }

    fun getString(key: String): String? {
        return sharepreferences.getString(key, null)
    }

    fun put(key: String, value: Boolean) {
        editor.putBoolean(key, value)
            .apply()
    }

    fun getBoolean(key: String): Boolean {
        return sharepreferences.getBoolean(key, false)
    }

    fun logout() {
        editor.clear().apply()
    }
}
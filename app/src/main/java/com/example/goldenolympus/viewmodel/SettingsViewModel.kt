package com.example.goldenolympus.viewmodel

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel

class SettingsViewModel(application: Application) : AndroidViewModel(application) {
    val codeSher : SharedPreferences = application.getSharedPreferences("pref_pofile", Context.MODE_PRIVATE)

    fun getCode(code : Int) {
        codeSher.edit().apply {
            putInt("code", code)
            apply()
        }
    }
}
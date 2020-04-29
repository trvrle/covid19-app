package com.trevo.covid19app.service

import android.content.Context
import android.preference.PreferenceManager
import javax.inject.Inject

class PreferenceService @Inject constructor(private val context: Context){

    fun savePreference(key: String, value: String) {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = prefs.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getPref(key: String): String? {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        return prefs.getString(key, "Country")
    }
}
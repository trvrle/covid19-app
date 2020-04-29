package com.trevo.covid19app.ui.world

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.trevo.covid19app.R
import com.trevo.covid19app.service.PreferenceService
import javax.inject.Inject

class WorldViewModel @Inject constructor(
    private val preferenceService: PreferenceService
) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "World Page"
    }
    val text: LiveData<String> = _text

    fun load(bottomNavigationView: BottomNavigationView) {
        val countryName = preferenceService.getPref("Country")
        bottomNavigationView.menu.findItem(R.id.navigation_country).title = countryName
    }
}
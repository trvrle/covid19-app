package com.trevo.covid19app.ui.world

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.trevo.covid19app.service.PreferenceService
import com.trevo.covid19app.ui.BaseViewModel
import javax.inject.Inject

class WorldViewModel @Inject constructor(
    private val preferenceService: PreferenceService
) : BaseViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "World Page"
    }
    val text: LiveData<String> = _text

    fun load() {
        val countryName = preferenceService.getPref("Country", defaultCountryValue)!!
        setTitle(countryName)
    }
}
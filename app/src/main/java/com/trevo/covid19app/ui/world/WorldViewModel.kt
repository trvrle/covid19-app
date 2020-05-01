package com.trevo.covid19app.ui.world

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.trevo.covid19app.service.PreferenceService
import javax.inject.Inject

class WorldViewModel @Inject constructor(
    private val preferenceService: PreferenceService
) : ViewModel() {

    private val defaultCountryValue = "Country"

    private val _title = MutableLiveData<String>().apply {
        value = defaultCountryValue
    }
    val title: LiveData<String> = _title

    private val _text = MutableLiveData<String>().apply {
        value = "World Page"
    }
    val text: LiveData<String> = _text

    fun load() {
        val countryName = preferenceService.getPref("Country", defaultCountryValue)
        _title.value = countryName
    }
}
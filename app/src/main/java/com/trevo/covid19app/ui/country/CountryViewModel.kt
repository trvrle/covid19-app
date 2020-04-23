package com.trevo.covid19app.ui.country

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CountryViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Country Page"
    }
    val text: LiveData<String> = _text
}
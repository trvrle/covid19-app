package com.trevo.covid19app.ui.country

import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.trevo.covid19app.service.DialogService
import javax.inject.Inject

class CountryViewModel @Inject constructor(): ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Country Page"
    }

    val text: LiveData<String> = _text
}
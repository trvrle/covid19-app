package com.trevo.covid19app.ui.country

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.trevo.covid19app.service.DialogService
import javax.inject.Inject

class CountryViewModel @Inject constructor(): ViewModel() {

    private lateinit var dialogService: DialogService

    private val _text = MutableLiveData<String>().apply {
        value = "Country Page"
    }

    val text: LiveData<String> = _text

    fun setupSelectCountryDialog(context: Context?) {
        dialogService = DialogService(context)
        dialogService.setupSelectCountryDialog()
    }

    fun showSelectCountryDialog() {
        dialogService.show()
    }
}
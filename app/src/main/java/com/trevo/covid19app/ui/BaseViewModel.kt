package com.trevo.covid19app.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

open class BaseViewModel @Inject constructor(): ViewModel() {

    protected val defaultCountryValue = "Country"

    private val _title = MutableLiveData<String>().apply {
        value = defaultCountryValue
    }
    val title: LiveData<String> = _title

    private val _loading = MutableLiveData<Boolean>().apply {
        value = false
    }
    val loading: LiveData<Boolean> = _loading

    protected fun setTitle(title: String) {
        _title.value = title
    }

    protected fun setLoading(isLoading: Boolean) {
        _loading.value = isLoading
    }
}
package com.trevo.covid19app.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.trevo.covid19app.api.responses.SummaryResponse
import java.text.NumberFormat
import java.util.*
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

    private val _confirmedText = MutableLiveData<String>().apply {
        value = defaultCountryValue
    }
    val confirmedText: LiveData<String> = _confirmedText

    private val _newConfirmedText = MutableLiveData<String>().apply {
        value = defaultCountryValue
    }
    val newConfirmedText: LiveData<String> = _newConfirmedText

    private val _recoveredText = MutableLiveData<String>().apply {
        value = defaultCountryValue
    }
    val recoveredText: LiveData<String> = _recoveredText

    private val _newRecoveredText = MutableLiveData<String>().apply {
        value = defaultCountryValue
    }
    val newRecoveredText: LiveData<String> = _newRecoveredText

    private val _deathsText = MutableLiveData<String>().apply {
        value = defaultCountryValue
    }
    val deathsText: LiveData<String> = _deathsText

    private val _newDeathsText = MutableLiveData<String>().apply {
        value = defaultCountryValue
    }
    val newDeathsText: LiveData<String> = _newDeathsText

    protected fun setTitle(title: String) {
        _title.value = title
    }

    protected fun setLoading(isLoading: Boolean) {
        _loading.value = isLoading
    }

    protected fun setSummary(summary: SummaryResponse) {
        _confirmedText.value = summary.Global.TotalConfirmed.toString()
        _newConfirmedText.value = summary.Global.NewConfirmed.toString()
        _recoveredText.value = summary.Global.TotalRecovered.toString()
        _newRecoveredText.value = summary.Global.NewRecovered.toString()
        _deathsText.value = summary.Global.TotalDeaths.toString()
        _newDeathsText.value = summary.Global.NewDeaths.toString()
    }

    protected fun setAllTextViews(text: String) {
        _confirmedText.value = text
        _newConfirmedText.value = text
        _recoveredText.value = text
        _newRecoveredText.value = text
        _deathsText.value = text
        _newDeathsText.value = text
    }
}
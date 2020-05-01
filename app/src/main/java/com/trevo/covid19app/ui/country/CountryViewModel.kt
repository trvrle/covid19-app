package com.trevo.covid19app.ui.country

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.trevo.covid19app.service.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CountryViewModel @Inject constructor(
    private val preferenceService: PreferenceService,
    private val dialogService: DialogService,
    private val apiService: ApiService,
    private val dispatcherService: IDispatcherService
): ViewModel() {

    private val scope = CoroutineScope(dispatcherService.main + SupervisorJob())

    private val defaultCountryValue = "Country"

    private val _text = MutableLiveData<String>().apply {
        value = ""
    }
    val text: LiveData<String> = _text

    private val _title = MutableLiveData<String>().apply {
        value = defaultCountryValue
    }
    val title: LiveData<String> = _title

    private val _loading = MutableLiveData<Boolean>().apply {
        value = false
    }
    val loading: LiveData<Boolean> = _loading

    fun load() {
        val countryName = preferenceService.getPref("Country", defaultCountryValue)!!
        setTitle(countryName)
        if (countryName != defaultCountryValue)
            displayCasesForCountry(countryName)
    }

    fun setupSelectCountryDialog(dialogBuilder: AlertDialog.Builder, countries: List<String>) {
        dialogService.setupSelectCountryDialog(
            dialogBuilder,
            DialogInterface.OnClickListener { _, _ ->
                applyCountry(countries, dialogService.selectedItem)
            }
        )
    }

    fun showSelectCountryDialog() {
        dialogService.show()
    }

    private fun applyCountry(countries: List<String>, selectedCountry: Int) {
        val countryName = countries[selectedCountry]
        setTitle(countryName)
        preferenceService.savePreference("Country", countryName)
        displayCasesForCountry(countryName)
    }

    private fun displayCasesForCountry(countryName: String) {
        scope.launch {
            _text.value = ""
            setLoading(true)
            val countryCasesResponses = withContext(dispatcherService.background) {
                apiService.getCountryTotal(countryName)
            }
            val confirmedCases = countryCasesResponses.last().Cases
            setLoading(false)
            _text.value = "Confirmed cases for $countryName: $confirmedCases"
        }
    }

    private fun setTitle(title: String) {
        _title.value = title
    }

    private fun setLoading(isLoading: Boolean) {
        _loading.value = isLoading
    }
}
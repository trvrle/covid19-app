package com.trevo.covid19app.ui.country

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.trevo.covid19app.service.*
import com.trevo.covid19app.ui.BaseViewModel
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
): BaseViewModel() {

    private val scope = CoroutineScope(dispatcherService.main + SupervisorJob())

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
            setLoading(true)
//            val countryCasesResponses = withContext(dispatcherService.background) {
//                apiService.getCountryTotal(countryName)
//            }
//            val confirmedCases = countryCasesResponses.last().Cases
            setLoading(false)
        }
    }
}
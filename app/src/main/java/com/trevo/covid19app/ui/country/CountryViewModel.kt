package com.trevo.covid19app.ui.country

import android.app.AlertDialog
import android.content.DialogInterface
import com.trevo.covid19app.api.Api
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
    private val api: Api,
    private val dispatcherService: IDispatcherService
): BaseViewModel() {

    private val scope = CoroutineScope(dispatcherService.main + SupervisorJob())

    fun load() {
        val countryName = preferenceService.getPref("Country", defaultCountryValue)!!
        setTitle(countryName)
        setAllTextViews("-")
        if (countryName != defaultCountryValue)
            displayCasesForCountry(countryName)
    }

    fun setupSelectCountryDialog(dialogBuilder: AlertDialog.Builder) {
        scope.launch {
            setLoading(true)
            val countries = withContext(dispatcherService.background) {
                api.getCountryNamesAlphabetical()
            }
            dialogService.setupSelectCountryDialog(
                dialogBuilder,
                countries.toTypedArray(),
                DialogInterface.OnClickListener { _, _ ->
                    applyCountry(countries, dialogService.selectedItem)
                }
            )
            setLoading(false)
        }
    }

    fun showSelectCountryDialog() {
        dialogService.show()
    }

    private fun applyCountry(countries: List<String>, selectedCountry: Int) {
        if (selectedCountry < 0) return
        val countryName = countries[selectedCountry]
        preferenceService.savePreference("Country", countryName)
        setTitle(countryName)
        setAllTextViews("-")
        displayCasesForCountry(countryName)
    }

    private fun displayCasesForCountry(countryName: String) {
        scope.launch {
            setLoading(true)
            val summary = withContext(dispatcherService.background) {
                api.getSummary()
            }
            val countrySummary = summary.countrySummaries.find { it.countryName == countryName }
            if (countrySummary != null)
                setCountryValues(countrySummary)
            setLoading(false)
        }
    }
}
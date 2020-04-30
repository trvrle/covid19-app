package com.trevo.covid19app.ui.country

import android.app.AlertDialog
import android.content.DialogInterface
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.ActionBar
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.trevo.covid19app.R
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

    private val _text = MutableLiveData<String>().apply {
        value = ""
    }
    val text: LiveData<String> = _text

    fun load(bottomNavigationView: BottomNavigationView, actionBar: ActionBar) {
        val title = preferenceService.getPref("Country", "Country")!!
        setTitle(title, bottomNavigationView, actionBar)
    }

    fun setupSelectCountryDialog(dialogBuilder: AlertDialog.Builder, countries: List<String>,
                                 bottomNavigationView: BottomNavigationView,
                                 actionBar: ActionBar, progressBar: ProgressBar) {
        dialogService.setupSelectCountryDialog(
            dialogBuilder,
            DialogInterface.OnClickListener { _, _ ->
                applyCountry(countries, dialogService.selectedItem, bottomNavigationView, actionBar, progressBar)
            }
        )
    }

    fun showSelectCountryDialog() {
        dialogService.show()
    }

    private fun setTitle(title: String, bottomNavigationView: BottomNavigationView, actionBar: ActionBar) {
        bottomNavigationView.menu.findItem(R.id.navigation_country).title = title
        actionBar.title = title
    }

    private fun applyCountry(countries: List<String>, selectedCountry: Int,
                             bottomNavigationView: BottomNavigationView, actionBar: ActionBar,
                             progressBar: ProgressBar) {
        val countryName = countries[selectedCountry]
        setTitle(countryName, bottomNavigationView, actionBar)
        preferenceService.savePreference("Country", countryName)
        displayCasesForCountry(countryName, progressBar)
    }

    private fun displayCasesForCountry(countryName: String, progressBar: ProgressBar) {
        scope.launch {
            progressBar.visibility = View.VISIBLE
            val countryCasesResponses = withContext(dispatcherService.background) {
                apiService.getCountryTotal(countryName)
            }
            val confirmedCases = countryCasesResponses.last().Cases
            _text.value = "Confirmed cases for $countryName: $confirmedCases"
            progressBar.visibility = View.GONE
        }
    }
}
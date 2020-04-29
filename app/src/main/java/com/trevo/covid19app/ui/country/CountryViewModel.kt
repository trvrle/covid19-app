package com.trevo.covid19app.ui.country

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.ActionBar
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.trevo.covid19app.R
import com.trevo.covid19app.service.DialogService
import javax.inject.Inject

class CountryViewModel @Inject constructor(): ViewModel() {

    private lateinit var dialogService: DialogService

    private val _text = MutableLiveData<String>().apply {
        value = "Country Page"
    }

    val text: LiveData<String> = _text

    fun setupSelectCountryDialog(dialogBuilder: AlertDialog.Builder, countries: List<String>,
                                 bottomNavigationView: BottomNavigationView,
                                 actionBar: ActionBar) {
        dialogService = DialogService()
        dialogService.setupSelectCountryDialog(
            dialogBuilder,
            DialogInterface.OnClickListener { _, _ ->
                applyCountry(countries, dialogService.selectedItem, bottomNavigationView, actionBar)
            }
        )
    }

    fun showSelectCountryDialog() {
        dialogService.show()
    }

    private fun applyCountry(countries: List<String>, selectedCountry: Int,
                             bottomNavigationView: BottomNavigationView, actionBar: ActionBar) {
        val countryName = countries[selectedCountry]
        _text.value = countryName
        bottomNavigationView.menu.findItem(R.id.navigation_country).title = countryName
        actionBar.title = countryName
    }
}
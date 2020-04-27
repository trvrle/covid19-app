package com.trevo.covid19app.service

import android.content.Context
import android.app.AlertDialog
import com.trevo.covid19app.R

class DialogService(private val context: Context?) {

    private var selectedItem = -1
    private var dialog: AlertDialog.Builder? = AlertDialog.Builder(context)

    fun setupSelectCountryDialog() {
        dialog = AlertDialog.Builder(context)
            .setTitle(R.string.select_country)
            .setSingleChoiceItems(R.array.countries, -1) { _, which ->
                selectedItem = which
            }
            .setPositiveButton(R.string.select) { _, _ ->
                userPressedSelect(selectedItem)
            }
            .setNegativeButton(R.string.cancel) { _, _ ->
                // Do nothing
            }
    }

    fun show() {
        dialog?.show()
    }

    private fun userPressedSelect(selectedItem: Int) {

    }
}
package com.trevo.covid19app.service

import android.app.AlertDialog
import android.content.DialogInterface
import com.trevo.covid19app.R
import javax.inject.Inject

class DialogService @Inject constructor() {

    var selectedItem = -1
    private var dialog: AlertDialog.Builder? = null

    fun setupSelectCountryDialog(dialogBuilder: AlertDialog.Builder,
                                 listener: DialogInterface.OnClickListener) {
        dialog = dialogBuilder
            .setTitle(R.string.select_country)
            .setSingleChoiceItems(R.array.countries, -1) { _, which ->
                selectedItem = which
            }
            .setPositiveButton(R.string.select, listener)
            .setNegativeButton(R.string.cancel, null)
    }

    fun show() {
        dialog?.show()
    }
}
package com.trevo.covid19app.service

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.trevo.covid19app.R
import javax.inject.Inject

class DialogService @Inject constructor(
    private val activity: AppCompatActivity
){

//    var alert: AlertDialog = AlertDialog.Builder(activity).create()

    fun showSelectCountryDialog() {
        println("Showing select country dialog")
//        val layoutInflater = LayoutInflater.from(activity)
//        val dialogView = layoutInflater.inflate(R.layout.dialog_select_country, null)
//
//        val titleView = dialogView.findViewById<TextView>(R.id.dialog_header)
//        titleView.text = "Select Country"
//
//        val messageView = dialogView.findViewById<TextView>(R.id.dialog_msg)
//        messageView.text = "Country1"
//
//        alert.setView(dialogView)
//        alert.setCancelable(true)
//        alert.setCanceledOnTouchOutside(true)
//
//        alert.show()
//        alert.window?.setLayout(
//            ViewGroup.LayoutParams.MATCH_PARENT,
//            ViewGroup.LayoutParams.WRAP_CONTENT
//        )
    }
}
package com.trevo.covid19app.ui.world

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WorldViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "World Page"
    }
    val text: LiveData<String> = _text
}
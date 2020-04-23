package com.trevo.covid19app.ui.country

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.trevo.covid19app.R

class CountryFragment : Fragment() {

    private lateinit var countryViewModel: CountryViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        countryViewModel =
                ViewModelProviders.of(this).get(CountryViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_country, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        countryViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}

package com.trevo.covid19app.ui.country

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.trevo.covid19app.R
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class CountryFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val countryViewModel: CountryViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_country, container, false)

        val textView: TextView = root.findViewById(R.id.text_country)
        countryViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        countryViewModel.setupSelectCountryDialog(context)

        return root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_select_country, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.expand_more)
            countryViewModel.showSelectCountryDialog()
        return super.onOptionsItemSelected(item)
    }
}

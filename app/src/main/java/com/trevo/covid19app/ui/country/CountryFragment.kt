package com.trevo.covid19app.ui.country

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
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
        val bottomNavigationView: BottomNavigationView = requireActivity().findViewById(R.id.nav_view)
        val progressBar: ProgressBar = root.findViewById(R.id.progress_loader)
        val actionBar = (activity as AppCompatActivity).supportActionBar!!
        val countries = resources.getStringArray(R.array.countries).toList()
        val dialogBuilder = AlertDialog.Builder(context)

        countryViewModel.load(bottomNavigationView, actionBar)

        countryViewModel.setupSelectCountryDialog(dialogBuilder, countries, bottomNavigationView, actionBar, progressBar)

        val textView: TextView = root.findViewById(R.id.text_country)
        countryViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

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

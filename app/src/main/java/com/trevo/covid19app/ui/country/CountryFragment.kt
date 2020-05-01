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
        countryViewModel.load()
        val root = inflater.inflate(R.layout.fragment_country, container, false)
        setupSelectCountryDialog()
        setupProgressBar(root.findViewById(R.id.progress_loader))
        setupTitle()
        setupText(root.findViewById(R.id.text_country))
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

    private fun setupSelectCountryDialog() {
        val countries = resources.getStringArray(R.array.countries).toList()
        val dialogBuilder = AlertDialog.Builder(context)

        countryViewModel.setupSelectCountryDialog(dialogBuilder, countries)
    }

    private fun setupProgressBar(progressBar: ProgressBar) {
        countryViewModel.loading.observe(viewLifecycleOwner, Observer {
            progressBar.visibility = if(it) View.VISIBLE else View.GONE
        })
    }

    private fun setupTitle() {
        val bottomNavigationView: BottomNavigationView = requireActivity().findViewById(R.id.nav_view)
        val actionBar = (activity as AppCompatActivity).supportActionBar!!
        countryViewModel.title.observe(viewLifecycleOwner, Observer {
            bottomNavigationView.menu.findItem(R.id.navigation_country).title = it
            actionBar.title = it
        })
    }

    private fun setupText(textView: TextView) {
        countryViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
    }
}

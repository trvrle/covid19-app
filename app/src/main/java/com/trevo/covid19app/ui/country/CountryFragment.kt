package com.trevo.covid19app.ui.country

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.trevo.covid19app.R
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class CountryFragment : Fragment(){

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val countryViewModel: CountryViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        countryViewModel.load()
        val root = inflater.inflate(R.layout.fragment_country, container, false)
        setupFragment(root)
        return root
    }

    private fun setupFragment(root: View) {
        setupSelectCountryDialog()
        setupButton(root)
        setupProgressBar()
        setupTitle(root)
        setupStats()
    }

    private fun setupSelectCountryDialog() {
        val dialogBuilder = AlertDialog.Builder(context)
        countryViewModel.setupSelectCountryDialog(dialogBuilder)
    }

    private fun setupButton(root: View) {
        val selectCountryButton: Button = root.findViewById(R.id.button_select_country)
        selectCountryButton.setOnClickListener {
            countryViewModel.showSelectCountryDialog()
        }
    }

    private fun setupProgressBar() {
        val progressBar: ProgressBar = requireActivity().findViewById(R.id.progress_loader)
        countryViewModel.loading.observe(viewLifecycleOwner, Observer {
            progressBar.visibility = if(it) View.VISIBLE else View.GONE
        })
    }

    private fun setupTitle(root: View) {
        val bottomNavigationView: BottomNavigationView = requireActivity().findViewById(R.id.nav_view)
        val selectCountryButton: Button = root.findViewById(R.id.button_select_country)
        countryViewModel.title.observe(viewLifecycleOwner, Observer {
            bottomNavigationView.menu.findItem(R.id.navigation_country).title = it
            selectCountryButton.text = it
        })
    }

    private fun setupStats() {
        val confirmedTextView: TextView = requireActivity().findViewById(R.id.confirmed_text)
        countryViewModel.confirmedText.observe(viewLifecycleOwner, Observer {
            confirmedTextView.text = it
        })
        val newConfirmedTextView: TextView = requireActivity().findViewById(R.id.new_confirmed_text)
        countryViewModel.newConfirmedText.observe(viewLifecycleOwner, Observer {
            newConfirmedTextView.text = it
        })
        val recoveredTextView: TextView = requireActivity().findViewById(R.id.recovered_text)
        countryViewModel.recoveredText.observe(viewLifecycleOwner, Observer {
            recoveredTextView.text = it
        })
        val newRecoveredTextView: TextView = requireActivity().findViewById(R.id.new_recovered_text)
        countryViewModel.newRecoveredText.observe(viewLifecycleOwner, Observer {
            newRecoveredTextView.text = it
        })
        val deathsTextView: TextView = requireActivity().findViewById(R.id.deaths_text)
        countryViewModel.deathsText.observe(viewLifecycleOwner, Observer {
            deathsTextView.text = it
        })
        val newDeathsTextView: TextView = requireActivity().findViewById(R.id.new_deaths_text)
        countryViewModel.newDeathsText.observe(viewLifecycleOwner, Observer {
            newDeathsTextView.text = it
        })
    }
}

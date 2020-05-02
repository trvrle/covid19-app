package com.trevo.covid19app.ui.world

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

class WorldFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val worldViewModel: WorldViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_world, container, false)
        worldViewModel.load()
        setupFragment()
        return root
    }

    private fun setupFragment() {
        setupProgressBar()
        setupTitle()
        setupStats()
    }

    private fun setupProgressBar() {
        val progressBar: ProgressBar = requireActivity().findViewById(R.id.progress_loader)
        worldViewModel.loading.observe(viewLifecycleOwner, Observer {
            progressBar.visibility = if(it) View.VISIBLE else View.GONE
        })
    }

    private fun setupTitle() {
        val bottomNavigationView: BottomNavigationView = requireActivity().findViewById(R.id.nav_view)
        worldViewModel.title.observe(viewLifecycleOwner, Observer {
            bottomNavigationView.menu.findItem(R.id.navigation_country).title = it
        })
    }

    private fun setupStats() {
        val confirmedTextView: TextView = requireActivity().findViewById(R.id.confirmed_text)
        worldViewModel.confirmedText.observe(viewLifecycleOwner, Observer {
            confirmedTextView.text = it
        })
        val newConfirmedTextView: TextView = requireActivity().findViewById(R.id.new_confirmed_text)
        worldViewModel.newConfirmedText.observe(viewLifecycleOwner, Observer {
            newConfirmedTextView.text = it
        })
        val recoveredTextView: TextView = requireActivity().findViewById(R.id.recovered_text)
        worldViewModel.recoveredText.observe(viewLifecycleOwner, Observer {
            recoveredTextView.text = it
        })
        val newRecoveredTextView: TextView = requireActivity().findViewById(R.id.new_recovered_text)
        worldViewModel.newRecoveredText.observe(viewLifecycleOwner, Observer {
            newRecoveredTextView.text = it
        })
        val deathsTextView: TextView = requireActivity().findViewById(R.id.deaths_text)
        worldViewModel.deathsText.observe(viewLifecycleOwner, Observer {
            deathsTextView.text = it
        })
        val newDeathsTextView: TextView = requireActivity().findViewById(R.id.new_deaths_text)
        worldViewModel.newDeathsText.observe(viewLifecycleOwner, Observer {
            newDeathsTextView.text = it
        })
    }
}

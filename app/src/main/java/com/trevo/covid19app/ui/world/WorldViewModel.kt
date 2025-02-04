package com.trevo.covid19app.ui.world

import com.trevo.covid19app.api.Api
import com.trevo.covid19app.service.IDispatcherService
import com.trevo.covid19app.service.PreferenceService
import com.trevo.covid19app.ui.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WorldViewModel @Inject constructor(
    private val api: Api,
    private val dispatcherService: IDispatcherService,
    private val preferenceService: PreferenceService
) : BaseViewModel() {

    private val scope = CoroutineScope(dispatcherService.main + SupervisorJob())

    fun load() {
        val countryName = preferenceService.getPref("Country", defaultCountryValue)!!
        setAllTextViews("-")
        setTitle(countryName)
        displayCasesForWorld()
    }

    private fun displayCasesForWorld() {
        scope.launch {
            setLoading(true)
            val summary = withContext(dispatcherService.background) {
                api.getSummary()
            }
            setWorldwideValues(summary.worldwideSummary)
            setLoading(false)
        }
    }
}
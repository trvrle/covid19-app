package com.trevo.covid19app.api

import com.trevo.covid19app.api.responses.CountryResponse
import com.trevo.covid19app.api.responses.CountrySummaryResponse
import com.trevo.covid19app.api.responses.GlobalSummaryResponse
import com.trevo.covid19app.api.responses.SummaryResponse
import com.trevo.covid19app.model.CountrySummary
import com.trevo.covid19app.model.WorldwideSummary
import com.trevo.covid19app.model.Summary
import java.text.NumberFormat
import java.util.*

class Transformer {

    fun countryResponseListToCountryNameListAlphabetical(countryResponseList: List<CountryResponse>): List<String> {
        val countryNames = mutableListOf<String>()
        for(countryResponse in countryResponseList) {
            countryNames += countryResponse.Country
        }
        return countryNames.sorted()
    }

    fun summaryResponseToSummary(summaryResponse: SummaryResponse): Summary {
        return Summary (globalSummaryResponseToWorldwideSummary(summaryResponse.Global),
        countrySummaryResponseToCountrySummary(summaryResponse.Countries))
    }

    private fun globalSummaryResponseToWorldwideSummary(globalResponse: GlobalSummaryResponse): WorldwideSummary {
        val confirmed = NumberFormat.getNumberInstance(Locale.getDefault()).format(globalResponse.TotalConfirmed)
        val newConfirmed = NumberFormat.getNumberInstance(Locale.getDefault()).format(globalResponse.NewConfirmed)
        val recovered = NumberFormat.getNumberInstance(Locale.getDefault()).format(globalResponse.TotalRecovered)
        val newRecovered = NumberFormat.getNumberInstance(Locale.getDefault()).format(globalResponse.NewRecovered)
        val deaths = NumberFormat.getNumberInstance(Locale.getDefault()).format(globalResponse.TotalDeaths)
        val newDeaths = NumberFormat.getNumberInstance(Locale.getDefault()).format(globalResponse.NewDeaths)
        return WorldwideSummary(confirmed, newConfirmed, recovered, newRecovered, deaths, newDeaths)
    }

    private fun countrySummaryResponseToCountrySummary(countries: List<CountrySummaryResponse>): List<CountrySummary> {
        val countrySummaries = mutableListOf<CountrySummary>()
        for (country in countries) {
            val confirmed = NumberFormat.getNumberInstance(Locale.getDefault()).format(country.TotalConfirmed)
            val newConfirmed = NumberFormat.getNumberInstance(Locale.getDefault()).format(country.NewConfirmed)
            val recovered = NumberFormat.getNumberInstance(Locale.getDefault()).format(country.TotalRecovered)
            val newRecovered = NumberFormat.getNumberInstance(Locale.getDefault()).format(country.NewRecovered)
            val deaths = NumberFormat.getNumberInstance(Locale.getDefault()).format(country.TotalDeaths)
            val newDeaths = NumberFormat.getNumberInstance(Locale.getDefault()).format(country.NewDeaths)
            countrySummaries += CountrySummary(country.Country, confirmed, newConfirmed, recovered, newRecovered, deaths, newDeaths)
        }
        return countrySummaries
    }
}
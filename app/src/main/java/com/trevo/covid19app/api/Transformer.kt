package com.trevo.covid19app.api

import com.trevo.covid19app.api.responses.GlobalResponse
import com.trevo.covid19app.api.responses.SummaryResponse
import com.trevo.covid19app.model.WorldwideSummary
import com.trevo.covid19app.model.Summary
import java.text.NumberFormat
import java.util.*

class Transformer {

    fun summaryResponseToSummary(summaryResponse: SummaryResponse): Summary {
        val s = NumberFormat.getNumberInstance(Locale.getDefault()).format(summaryResponse.Global.TotalConfirmed)
        return Summary (globalResponseToWorldwideSummary(summaryResponse.Global))
    }

    private fun globalResponseToWorldwideSummary(globalResponse: GlobalResponse): WorldwideSummary {
        val confirmed = NumberFormat.getNumberInstance(Locale.getDefault()).format(globalResponse.TotalConfirmed)
        val newConfirmed = NumberFormat.getNumberInstance(Locale.getDefault()).format(globalResponse.NewConfirmed)
        val recovered = NumberFormat.getNumberInstance(Locale.getDefault()).format(globalResponse.TotalRecovered)
        val newRecovered = NumberFormat.getNumberInstance(Locale.getDefault()).format(globalResponse.NewRecovered)
        val deaths = NumberFormat.getNumberInstance(Locale.getDefault()).format(globalResponse.TotalDeaths)
        val newDeaths = NumberFormat.getNumberInstance(Locale.getDefault()).format(globalResponse.NewDeaths)
        return WorldwideSummary(confirmed, newConfirmed, recovered, newRecovered, deaths, newDeaths)
    }
}
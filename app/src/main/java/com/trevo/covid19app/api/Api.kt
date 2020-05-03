package com.trevo.covid19app.api

import com.trevo.covid19app.model.Summary
import javax.inject.Inject

class Api @Inject constructor(
    private val retrofit: CovidRetrofit,
    private val transformer: Transformer
) {

    suspend fun getCountryNamesAlphabetical(): List<String> {
        return transformer.countryResponseListToCountryNameListAlphabetical(retrofit.getCountries().await())
    }

    suspend fun getSummary(): Summary {
        return transformer.summaryResponseToSummary(retrofit.getSummary().await())
    }
}
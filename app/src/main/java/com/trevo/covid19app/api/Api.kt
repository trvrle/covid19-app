package com.trevo.covid19app.api

import com.trevo.covid19app.api.responses.CountryCasesResponse
import com.trevo.covid19app.api.responses.CountryResponse
import com.trevo.covid19app.model.Summary
import javax.inject.Inject

class Api @Inject constructor(
    private val retrofit: CovidRetrofit,
    private val transformer: Transformer
) {

    suspend fun getCountries(): List<CountryResponse> {
        return retrofit.getCountries().await()
    }

    suspend fun getCountryTotal(country: String): List<CountryCasesResponse> {
        return retrofit.getByCountryTotal(country).await()
    }

    suspend fun getSummary(): Summary {
        return transformer.summaryResponseToSummary(retrofit.getSummary().await())
    }
}
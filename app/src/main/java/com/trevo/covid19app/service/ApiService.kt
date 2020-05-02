package com.trevo.covid19app.service

import com.trevo.covid19app.api.CovidRetrofit
import com.trevo.covid19app.api.responses.CountryCasesResponse
import com.trevo.covid19app.api.responses.CountryResponse
import com.trevo.covid19app.api.responses.SummaryResponse
import javax.inject.Inject

class ApiService @Inject constructor(
    private val api: CovidRetrofit
) {

    suspend fun getCountries(): List<CountryResponse> {
        return api.getCountries().await()
    }

    suspend fun getCountryTotal(country: String): List<CountryCasesResponse> {
        return api.getByCountryTotal(country).await()
    }

    suspend fun getSummary(): SummaryResponse {
        return api.getSummary().await()
    }
}
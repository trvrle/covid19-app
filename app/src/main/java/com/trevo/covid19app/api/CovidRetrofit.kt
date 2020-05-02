package com.trevo.covid19app.api

import com.trevo.covid19app.api.responses.CountryCasesResponse
import com.trevo.covid19app.api.responses.CountryResponse
import com.trevo.covid19app.api.responses.SummaryResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface CovidRetrofit {

    @GET("countries")
    fun getCountries(): Deferred<List<CountryResponse>>

    @GET("total/country/{country}/status/confirmed")
    fun getByCountryTotal(
        @Path("country") country: String
    ): Deferred<List<CountryCasesResponse>>

    @GET("summary")
    fun getSummary(): Deferred<SummaryResponse>

//    @GET("world/total")
//    fun getWorldTotal():
}
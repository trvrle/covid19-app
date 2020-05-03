package com.trevo.covid19app.api.responses

import java.util.*

class CountrySummaryResponse (
    val Country: String,
    val CountryCode: String,
    val Slug: String,
    val NewConfirmed: Long,
    val TotalConfirmed: Long,
    val NewDeaths: Long,
    val TotalDeaths: Long,
    val NewRecovered: Long,
    val TotalRecovered: Long,
    val Date: Date
)
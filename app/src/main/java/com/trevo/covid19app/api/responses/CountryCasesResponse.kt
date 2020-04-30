package com.trevo.covid19app.api.responses

import java.util.Date

class CountryCasesResponse (
    val Country: String,
    val CountryCode: String,
    val Province: String,
    val City: String,
    val Lat: String,
    val Lon: String,
    val Cases: Long,
    val Status: String,
    val Date: Date
)
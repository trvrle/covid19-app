package com.trevo.covid19app.api.responses

class SummaryResponse (
    val Global: GlobalSummaryResponse,
    val Countries: List<CountrySummaryResponse>
)
package com.trevo.covid19app.service

import kotlinx.coroutines.CoroutineDispatcher

interface IDispatcherService {
    val main: CoroutineDispatcher
    val background: CoroutineDispatcher
}
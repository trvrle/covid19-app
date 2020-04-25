package com.trevo.covid19app.ui.country

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class CountryProvider {
    @ContributesAndroidInjector
    abstract fun provideCountryFragment(): CountryFragment
}
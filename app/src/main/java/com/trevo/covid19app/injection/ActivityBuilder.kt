package com.trevo.covid19app.injection

import com.trevo.covid19app.MainActivity
import com.trevo.covid19app.ui.country.CountryProvider
import com.trevo.covid19app.ui.world.WorldProvider
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(
        modules = [
            CountryProvider::class,
            WorldProvider::class
        ]
    )
    abstract fun bindMainActivity(): MainActivity
}
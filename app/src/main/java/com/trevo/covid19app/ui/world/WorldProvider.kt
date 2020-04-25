package com.trevo.covid19app.ui.world

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class WorldProvider {
    @ContributesAndroidInjector
    abstract fun provideWorldFragment(): WorldFragment
}
package com.trevo.covid19app.injection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.trevo.covid19app.ui.country.CountryViewModel
import com.trevo.covid19app.ui.world.WorldViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(CountryViewModel::class)
    abstract fun bindCountryViewModel(viewModel: CountryViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(WorldViewModel::class)
    abstract fun bindWorldViewModel(viewModel: WorldViewModel): ViewModel
}
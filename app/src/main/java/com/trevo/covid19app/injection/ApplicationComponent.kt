package com.trevo.covid19app.injection

import android.app.Application
import com.trevo.covid19app.Covid19Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, ApplicationModule::class, ActivityBuilder::class, ViewModelModule::class])
interface ApplicationComponent {

    fun inject(app: Covid19Application)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }
}
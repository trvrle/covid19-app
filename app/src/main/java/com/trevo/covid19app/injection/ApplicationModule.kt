package com.trevo.covid19app.injection

import android.app.Application
import android.content.Context
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.trevo.covid19app.api.CovidRetrofit
import com.trevo.covid19app.service.ApiService
import com.trevo.covid19app.service.DispatcherService
import com.trevo.covid19app.service.IDispatcherService
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
object ApplicationModule {

    @Provides
    @JvmStatic
    fun provideApplicationContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    @JvmStatic
    fun provideGson(): Gson {
        return GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            .create()
    }

    @Provides
    @Singleton
    @JvmStatic
    fun provideRetrofit(
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl("https://api.covid19api.com/")
            .build()
    }

    @Provides
    @Singleton
    @JvmStatic
    fun provideApiService(
        retrofit: Retrofit
    ): ApiService {
        return ApiService(retrofit.create(CovidRetrofit::class.java))
    }

    @Provides
    @Singleton
    @JvmStatic
    fun provideDispatcherService(
        dispatcherService: DispatcherService
    ): IDispatcherService {
        return dispatcherService
    }

//    @JvmStatic @Provides @Singleton
//    fun provideBooksService(): PostsService = Retrofit.Builder()
//        .baseUrl("https://jsonplaceholder.typicode.com/")
//        .addConverterFactory(MoshiConverterFactory.create())
//        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
//        .build()
//        .create(PostsService::class.java)
}
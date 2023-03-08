package com.test.newsapp.di.module

import com.test.newsapp.data.remote.ApiNewsInterface
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


class NetworkModule(){

    val BASE_URL = "https://newsapi.org/v2/"

    internal fun provideRetrofit(): Retrofit {

        return Retrofit.Builder().baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    internal fun provideRetrofitService(): ApiNewsInterface {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiNewsInterface::class.java)
    }

    internal fun provideNetworkModule(): NetworkModule {
        return this
    }

    internal fun provideBaseUrl(): String {
        return BASE_URL
    }
}

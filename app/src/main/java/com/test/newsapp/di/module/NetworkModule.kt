package com.test.newsapp.di.module

import com.test.newsapp.data.remote.ApiNewsInterface
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class NetworkModule {

    private val baseUrl = "https://newsapi.org/v2/"

    internal fun provideRetrofitService(): ApiNewsInterface {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(ApiNewsInterface::class.java)
    }
}

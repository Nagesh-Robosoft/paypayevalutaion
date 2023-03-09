package com.test.newsapp.di.module

import com.test.newsapp.data.remote.ApiNewsInterface
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class NetworkModule {

    private val BASE_URL = "https://newsapi.org/v2/"

    internal fun provideRetrofitService(): ApiNewsInterface {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiNewsInterface::class.java)
    }
}

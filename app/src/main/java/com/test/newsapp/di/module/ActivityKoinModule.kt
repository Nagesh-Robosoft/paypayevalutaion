package com.test.newsapp.di.module

import com.test.newsapp.data.remote.NewsRepository
import org.koin.dsl.module

val appModule = module {
    factory { NetworkModule() }
    factory { NewsRepository(get()) }
}

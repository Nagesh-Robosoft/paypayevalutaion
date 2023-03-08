package com.test.newsapp.di.module

import com.test.newsapp.data.remote.NewsRepository
import com.test.newsapp.ui.main.home.HomeViewModel
import com.test.newsapp.viewmodels.NewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModuleMain = module {
    factory { NetworkModule() }
    factory { NewsRepository(get()) }
    viewModel {
        HomeViewModel( newsRepository= get())
    }
    viewModel {
        NewsViewModel( newsRepository= get())
    }
}

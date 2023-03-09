package com.test.newsapp.di.module

import com.test.newsapp.viewmodels.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModuleMain = module {
    viewModel {
        HomeViewModel(newsRepository = get())
    }
}

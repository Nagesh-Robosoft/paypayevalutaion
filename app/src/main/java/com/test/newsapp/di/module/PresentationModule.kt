package com.test.newsapp.di.module

import com.test.newsapp.viewmodels.HomeViewModel
import com.test.newsapp.viewmodels.NewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModuleMain = module {
    viewModel {
        HomeViewModel( newsRepository= get())
    }
    viewModel {
        NewsViewModel( newsRepository= get())
    }
}

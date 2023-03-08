package com.test.newsapp.di.module

import android.app.Activity
import android.content.Context

import com.test.newsapp.di.ActivityContext
import com.test.newsapp.data.remote.NewsRepository
import com.test.newsapp.viewmodels.NewsViewModel

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class ActivityModule(private val activity: Activity) {

    @ActivityContext
    @Provides
    fun provideContext(): Context = activity

    @Provides
    fun provideNewsRepository(): NewsRepository = NewsRepository(NetworkModule())

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()


}

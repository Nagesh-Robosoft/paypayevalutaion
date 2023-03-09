package com.test.newsapp

import android.app.Application
import com.test.newsapp.di.module.appModule
import com.test.newsapp.di.module.presentationModuleMain
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule + presentationModuleMain)
            androidContext(this@MyApplication)
        }
    }
}

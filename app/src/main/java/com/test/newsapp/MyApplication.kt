package com.test.newsapp

import android.app.Application
import com.test.newsapp.di.component.ApplicationComponent
import com.test.newsapp.di.component.DaggerApplicationComponent
import com.test.newsapp.di.module.ApplicationModule
import com.test.newsapp.di.module.appModule
import com.test.newsapp.di.module.presentationModuleMain
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        getDependencies()
        startKoin {
            modules(appModule + presentationModuleMain)
            androidContext(this@MyApplication)
        }
    }

    private fun getDependencies() {
        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()
        applicationComponent.inject(this)
    }
}

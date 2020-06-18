package com.app.lorempiscum

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class LoremApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        loadKoin()
    }

    private fun loadKoin() {
        startKoin {
            androidContext(this@LoremApplication)
            modules(appModule)
        }
    }
}
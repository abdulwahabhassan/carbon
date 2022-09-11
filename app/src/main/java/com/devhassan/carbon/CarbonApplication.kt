package com.devhassan.carbon

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class CarbonApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initTimberLog()
    }

    private fun initTimberLog() {
        if(BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
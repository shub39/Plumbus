package com.shub39.plumbus

import android.app.Application
import com.shub39.plumbus.di.initKoin
import org.koin.android.ext.koin.androidContext

class PlumbusApp: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@PlumbusApp)
        }
    }
}
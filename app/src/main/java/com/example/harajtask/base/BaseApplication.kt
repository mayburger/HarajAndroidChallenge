package com.example.harajtask.base

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication : Application() {

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    private interface Constants {
        companion object {
            const val MEDIUM = 1
            const val BOLD = 2
            const val UTHMAN = 3
            const val BLACK = 4
            const val CASLON = 5
        }
    }

    companion object {
        @get:Synchronized
        var instance: BaseApplication? = null
            private set
    }
}

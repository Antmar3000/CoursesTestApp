package com.am.coursestest

import android.app.Application
import com.am.coursestest.di.firstLaunchModule
import com.am.screen_mainlist.di.mainListModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CoursesApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(this@CoursesApplication)
            modules(mainListModule, firstLaunchModule)
        }
    }
}
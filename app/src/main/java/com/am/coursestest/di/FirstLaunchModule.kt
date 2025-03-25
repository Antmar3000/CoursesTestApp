package com.am.coursestest.di

import android.content.Context
import android.content.SharedPreferences
import com.am.coursestest.data.IsFirstLaunched
import org.koin.dsl.module

val firstLaunchModule = module {

    single { IsFirstLaunched(get()) }
    single { provideSharedPreferences(get()) }
}

fun provideSharedPreferences (context: Context) : SharedPreferences {
    return context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
}
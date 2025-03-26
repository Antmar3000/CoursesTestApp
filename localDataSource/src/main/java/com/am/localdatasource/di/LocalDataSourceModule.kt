package com.am.localdatasource.di

import androidx.room.Room
import com.am.localdatasource.data.database.LocalDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localDataModule = module {

    single {
        Room.databaseBuilder(androidContext(),
            LocalDatabase::class.java,
            "fav-database")
            .build()
    }
    single { get<LocalDatabase>().localDao() }
}
package com.am.localdatasource.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.am.localdatasource.data.database.models.CourseDBO

@Database(entities = [CourseDBO::class], version = 1)
abstract class LocalDatabase : RoomDatabase() {

    abstract fun localDao () : LocalDAO
}
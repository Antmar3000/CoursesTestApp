package com.am.localdatasource.data.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "local_courses",
    indices = [Index(value = ["course_id"], unique = true)])
data class CourseDBO (
    @PrimaryKey(autoGenerate = true) val id : Int = 0,
    @ColumnInfo (name = "course_id") val courseId : Int,
    val title : String,
    val text : String,
    val price : String,
    val rate : Float,
    val startDate : String,
    val hasLike : Boolean,
    val publishDate : String,
)
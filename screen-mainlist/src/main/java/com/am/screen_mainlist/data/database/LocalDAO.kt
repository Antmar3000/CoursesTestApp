package com.am.screen_mainlist.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.am.screen_mainlist.data.database.models.CourseDBO
import kotlinx.coroutines.flow.Flow

@Dao
interface LocalDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert (item : CourseDBO)

    @Query("SELECT * FROM local_courses WHERE hasLike ORDER BY course_id ASC")
    fun getFavourites () : Flow<List<CourseDBO>>

    @Query("SELECT * FROM local_courses")
    fun getAll () : Flow<List<CourseDBO>>

    @Update
    suspend fun update (item: CourseDBO)
}
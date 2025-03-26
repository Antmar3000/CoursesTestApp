package com.am.screen_mainlist.domain.repository

import com.am.core.domain.entity.Course
import com.am.localdatasource.data.database.models.CourseDBO
import kotlinx.coroutines.flow.Flow

interface LocalRepository {

    suspend fun updateFavourites(item: CourseDBO)

    suspend fun addAll (list : List<Course>)

    fun getFavourites () : Flow<List<CourseDBO>>

    fun getAll() : Flow<List<CourseDBO>>
}